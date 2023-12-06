package br.com.bd2.funcionario.controller;

import java.util.List;
import java.util.UUID;

import br.com.bd2.funcionario.converter.FuncionarioConverter;
import br.com.bd2.funcionario.dto.FuncionarioDto;
import br.com.bd2.funcionario.orm.Funcionario;
import br.com.bd2.funcionario.repository.FuncionarioRepository;
import br.com.bd2.venda.controller.VendaController;
import br.com.bd2.venda.converter.VendaConverter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class FuncionarioController {
    
    @Inject
    FuncionarioConverter funcionarioConverter;

    @Inject
    FuncionarioRepository funcionarioRepository;

    @Inject
    VendaController vendaController;

    @Inject
    VendaConverter vendaConverter;

    @Transactional
    public FuncionarioDto create(FuncionarioDto funcionarioDto) {
        Funcionario funcionario = funcionarioConverter.dtoToOrm(funcionarioDto);
        funcionarioRepository.persist(funcionario);
        return funcionarioConverter.ormToDto(funcionario, funcionarioDto);
    }

    public FuncionarioDto retrieve(UUID uuid) {

        Funcionario funcionario = funcionarioRepository.findById(uuid);
        FuncionarioDto funcionarioDto = funcionarioConverter.ormToDto(funcionario);
        funcionarioDto.setVendaList(vendaConverter.ormListToDtoList(funcionario.getVendaList()));

        return funcionarioDto;
    }

    public List<FuncionarioDto> retrieveAll() {
        List<Funcionario> funcionarioList = funcionarioRepository.listAll();
        List<FuncionarioDto> funcionarioDtoList = funcionarioConverter.ormListToDtoList(funcionarioList);
        for (FuncionarioDto funcionarioDto : funcionarioDtoList) {
            funcionarioDto.setVendaList(
                vendaController.findVendaByFuncionario(funcionarioDto.getIdFuncionario()));
        }
        return funcionarioDtoList;
    }

    @Transactional
    public FuncionarioDto update(FuncionarioDto funcionarioDto) {
        Funcionario funcionario = funcionarioRepository.findById(UUID.fromString(funcionarioDto.getIdFuncionario()));

        funcionarioConverter.dtoToOrm(funcionarioDto, funcionario);
        funcionarioRepository.persist(funcionario);
        return funcionarioDto;
    }

    @Transactional
    public boolean delete(UUID uuid) {
        try {
            Funcionario funcionario = funcionarioRepository.findById(uuid);
            funcionario.getVendaList().forEach(venda -> vendaController
                    .delete(venda.getIdVenda()));
            funcionarioRepository.delete(funcionario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
