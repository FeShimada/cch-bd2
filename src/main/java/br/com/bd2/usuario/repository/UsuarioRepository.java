package br.com.bd2.usuario.repository;
import java.util.UUID;

import br.com.bd2.usuario.orm.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{
    public Usuario findById(UUID uuid) {
        return Usuario.findById(uuid);
    }
}
