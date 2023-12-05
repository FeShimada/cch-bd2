package br.com.bd2.fornecedor.controller;

import java.util.List;
import java.util.UUID;

import br.com.bd2.enumeration.SituacaoRegistroEnum;
import br.com.bd2.exception.PermitionException;
import br.com.bd2.fornecedor.converter.FornecedorConverter;
import br.com.bd2.fornecedor.dto.FornecedorDto;
import br.com.bd2.fornecedor.orm.Fornecedor;
import br.com.bd2.fornecedor.repository.FornecedorRepository;
import br.com.bd2.produto.controller.ProdutoController;
import br.com.bd2.produto.converter.ProdutoConverter;
import br.com.bd2.usuario.orm.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RequestScoped
public class FornecedorController {

    @Inject
    FornecedorConverter fornecedorConverter;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    ProdutoController produtoController;

    @Inject
    ProdutoConverter produtoConverter;

    @Transactional
    public FornecedorDto create(FornecedorDto fornecedorDto) {

        try {
            Fornecedor fornecedor = fornecedorConverter.dtoToOrm(fornecedorDto);
            fornecedorRepository.persist(fornecedor);
            fornecedorDto.getProdutoList().forEach(produto -> produtoController.create(produto, fornecedor));
            return fornecedorConverter.ormToDto(fornecedor, fornecedorDto);
        } catch (Exception e) {
            throw new PermitionException("Permissão negada!"); 
        }
    }

    public FornecedorDto retrieve(UUID uuid) {

        Fornecedor fornecedor = fornecedorRepository.findById(uuid);
        FornecedorDto fornecedorDto = fornecedorConverter.ormToDto(fornecedor);
        fornecedorDto.setProdutoList(produtoConverter.ormListToDtoList(fornecedor.getProdutoList()));

        return fornecedorDto;
    }

    public List<FornecedorDto> retrieveAll() {
        List<Fornecedor> fornecedorList = fornecedorRepository.listAll();
        List<FornecedorDto> fornecedorDtoList = fornecedorConverter.ormListToDtoList(fornecedorList);
        for (FornecedorDto fornecedorDto : fornecedorDtoList) {
            fornecedorDto.setProdutoList(
                    produtoController.findProdutoByFornecedor(fornecedorDto.getIdFornecedor()));
        }
        return fornecedorDtoList;
    }

    @Transactional
    public FornecedorDto update(FornecedorDto fornecedorDto) {
        Fornecedor fornecedor = fornecedorRepository.findById(UUID.fromString(fornecedorDto.getIdFornecedor()));

        fornecedorDto.getProdutoList().forEach(produto -> {
            if (produto.getStRegistro() == SituacaoRegistroEnum.CREATE) {
                produtoController.create(produto, fornecedor);
            } else if (produto.getStRegistro() == SituacaoRegistroEnum.UPDATE) {
                produtoController.update(produto);
            } else if (produto.getStRegistro() == SituacaoRegistroEnum.DELETE) {
                produtoController.delete(UUID.fromString(produto.getIdProduto()));
            }
        });

        fornecedorConverter.dtoToOrm(fornecedorDto, fornecedor);
        fornecedorRepository.persist(fornecedor);
        return fornecedorDto;
    }

    @Transactional
    public boolean delete(UUID uuid) {
        try {
            Fornecedor fornecedor = fornecedorRepository.findById(uuid);
            fornecedor.getProdutoList().forEach(produto -> produtoController
                    .delete(produto.getIdProduto()));
            fornecedorRepository.delete(fornecedor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
