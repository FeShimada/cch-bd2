package br.com.bd2.venda.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.bd2.exception.EstoqueException;
import br.com.bd2.funcionario.orm.Funcionario;
import br.com.bd2.item.controller.ItemController;
import br.com.bd2.item.converter.ItemConverter;
import br.com.bd2.produto.orm.Produto;
import br.com.bd2.venda.converter.VendaConverter;
import br.com.bd2.venda.dto.VendaDto;
import br.com.bd2.venda.orm.Venda;
import br.com.bd2.venda.repository.VendaRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class VendaController {
    
    @Inject
    VendaConverter vendaConverter;

    @Inject
    VendaRepository vendaRepository;

    @Inject
    ItemController itemController;

    @Inject
    ItemConverter itemConverter;

    @Transactional
    public VendaDto create(VendaDto vendaDto) {
        Venda venda = vendaConverter.dtoToOrm(vendaDto);

        Funcionario funcionario = Funcionario.findById(UUID.fromString(vendaDto.getFuncionario().getIdFuncionario()));
        venda.setFuncionario(funcionario);

        venda.setDtVenda(LocalDateTime.now());
        
        vendaDto.getItemList().forEach(item -> {
            Produto produto = Produto.findById(UUID.fromString(item.getProduto().getIdProduto()));

            var estoque = produto.getNrQuantidade();
            var quantidade = item.getNrQuantidade();

            if((estoque - quantidade) < 0) {
                throw new EstoqueException("Não há produtos suficientes no estoque!");
            }

            var valor = produto.getNrValor();
            var valorParcial = quantidade * valor;
            item.setNrValorParcial(valorParcial);
        });

        double valorTotal = vendaDto.getItemList()
                .stream()
                .mapToDouble(item -> item.getNrValorParcial())
                .sum();

        venda.setNrValorTotal(valorTotal);
        
        vendaRepository.persist(venda);
        vendaDto.getItemList().forEach(item -> itemController.create(item, venda));


        return vendaConverter.ormToDto(venda, vendaDto);
    }

    public VendaDto retrieve(UUID uuid) {
        Venda venda = vendaRepository.findById(uuid);
        VendaDto vendaDto = vendaConverter.ormToDto(venda);
        vendaDto.setItemList(itemConverter.ormListToDtoList(venda.getItemList()));
        return vendaDto;
    }

    public List<VendaDto> retrieveAll() {
        List<Venda> vendaList = vendaRepository.listAll();
        List<VendaDto> vendaDtoList = vendaConverter.ormListToDtoList(vendaList);
        for(VendaDto vendaDto : vendaDtoList) {
            vendaDto.setItemList(itemController.findItemByVenda(vendaDto.getIdVenda()));
        }
        return vendaDtoList;
    }

    public List<VendaDto> findVendaByFuncionario(String idFuncionario) {
        List<Venda> vendaList = vendaRepository.find("funcionario.idFuncionario", UUID.fromString(idFuncionario)).list();

        return vendaConverter.ormListToDtoList(vendaList);
    }

    @Transactional
    public boolean delete(UUID uuid) {
        try {
            Venda venda = vendaRepository.findById(uuid);
            venda.getItemList().forEach(item -> itemController
                    .delete(item.getIdItem()));
            vendaRepository.delete(venda);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
