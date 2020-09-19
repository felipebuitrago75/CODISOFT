package co.com.fxmanager.management.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Usuario;

public interface UsuarioRepository extends AbstractRepository<Usuario> {

	public Usuario update(Long id, Usuario usuario);

	public void delete(Long id);

	public Optional<Usuario> getUsuario(Long id);
	
}
