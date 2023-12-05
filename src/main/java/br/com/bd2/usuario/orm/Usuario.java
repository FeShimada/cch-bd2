package br.com.bd2.usuario.orm;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends PanacheEntityBase {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "ulid")
    @Column(name = "id_usuario", nullable = false)
    @GenericGenerator(name = "ulid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID idUsuario;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
