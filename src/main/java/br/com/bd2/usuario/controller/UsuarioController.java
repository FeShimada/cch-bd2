package br.com.bd2.usuario.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bd2.exception.PermitionException;
import br.com.bd2.usuario.dto.UsuarioDto;
import br.com.bd2.usuario.orm.Usuario;
import br.com.bd2.usuario.repository.UsuarioRepository;

@RequestScoped
public class UsuarioController {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EntityManager em;

    @Transactional
    public UsuarioDto login(UsuarioDto usuarioDto) {

        try {
            Usuario usuario = new Usuario();
            usuario.setUsuario(usuarioDto.getUsuario());
            usuario.setSenha(usuarioDto.getSenha());
            
            if (Usuario.count() != 0) {
                Usuario.deleteAll();
            }
            
            
            usuarioRepository.persist(usuario);
            em.createNativeQuery("SET ROLE " + usuarioDto.getUsuario()).executeUpdate();
            return usuarioDto;
        }catch (Exception e) {
            System.out.println(e);
            throw new PermitionException("Permissão negada!"); 
        }
    }

    public String retrieveDatabaseUser() {
        String url = "jdbc:postgresql://localhost:5432/trabalho-pratico-bd2";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT current_user";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
                    } else {
                        return "Usuário não encontrado";
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter usuário do banco de dados", e);
        }
    }

    @Transactional
    public String changeDatabaseUser(String newUser, String newPassword) {
        String url = "jdbc:postgresql://localhost:5432/trabalho-pratico-bd2";
        String user = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement setRoleStatement = connection.prepareStatement("SET ROLE " + newUser)) {
                setRoleStatement.executeUpdate();
            }

            String sql = "SELECT current_user";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
                    } else {
                        return "Usuário não encontrado";
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter usuário do banco de dados", e);
        }
    }

}
