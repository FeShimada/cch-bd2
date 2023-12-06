package br.com.bd2.item.controller;

import java.util.List;
import java.util.UUID;

import br.com.bd2.item.converter.ItemConverter;
import br.com.bd2.item.dto.ItemDto;
import br.com.bd2.item.orm.Item;
import br.com.bd2.item.repository.ItemRepository;
import br.com.bd2.produto.orm.Produto;
import br.com.bd2.produto.repository.ProdutoRepository;
import br.com.bd2.venda.orm.Venda;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class ItemController {
    
    @Inject
    ItemConverter itemConverter;

    @Inject
    ItemRepository itemRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Transactional
    public ItemDto create(ItemDto itemDto, Venda venda) {
        Item item = itemConverter.dtoToOrm(itemDto);

        Produto produto = Produto.findById(UUID.fromString(itemDto.getProduto().getIdProduto()));
        item.setProduto(produto);

        var quantidade = item.getNrQuantidade();
        var valor = produto.getNrValor();
        var valorParcial = quantidade * valor;
        item.setNrValorParcial(valorParcial);

        item.setVenda(venda);

        produto.setNrQuantidade(produto.getNrQuantidade() - item.getNrQuantidade());

        itemRepository.persist(item);
        produtoRepository.persist(produto);

        return itemConverter.ormToDto(item, itemDto);
    }

    public ItemDto retrieve(UUID uuid) {
        return itemConverter.ormToDto(itemRepository.findById(uuid));
    }

    public List<ItemDto> retrieveAll() {
        List<Item> itemList = itemRepository.listAll();
        return itemConverter.ormListToDtoList(itemList);
    }

    public List<ItemDto> findItemByVenda(String idVenda) {
        List<Item> itemList = itemRepository.find("venda.idVenda", UUID.fromString(idVenda)).list();

        return itemConverter.ormListToDtoList(itemList);
    }

    @Transactional
    public boolean delete(UUID uuid) {
        try {
            Item item = itemRepository.findById(uuid);
            itemRepository.delete(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
