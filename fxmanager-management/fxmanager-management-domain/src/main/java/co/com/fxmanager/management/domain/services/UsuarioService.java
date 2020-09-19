package co.com.fxmanager.management.domain.services;

import java.util.List;
import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Usuario;
import co.com.fxmanager.management.domain.services.constants.MessageConstants;
import co.com.fxmanager.management.domain.services.exceptions.ValidationException;
import co.com.fxmanager.management.persistence.repositories.UsuarioRepository;
import lombok.Getter;
import lombok.NonNull;

public class UsuarioService {

	@Getter
	private UsuarioRepository usuarioRepository;

	public UsuarioService(@NonNull UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario save(@NonNull Usuario usuario) {
		checkDataRequired(usuario);
		return getUsuarioRepository().create(usuario);
	}

	public Usuario update(@NonNull Long id,@NonNull Usuario usuario) {
		checkUsuarioExist(id);
		checkDataRequired(usuario);
		return getUsuarioRepository().update(id, usuario);
	}

	public void delete(@NonNull Long id) {		
		checkUsuarioExist(id);
		getUsuarioRepository().delete(id);
	}

	protected Usuario checkUsuarioExist(Long id) {
		Optional<Usuario> usuario = getUsuarioRepository().getUsuario(id);
		return usuario.orElseThrow( () -> new ValidationException(MessageConstants.CAJA_DOESNT_EXIST));
				
	}

	//TODO: validar los datos obligatorios
	protected void checkDataRequired(Usuario usuario) {
		
	}
	
	public List<Usuario> getUsuarios(Integer first, Integer max){
		return this.getUsuarioRepository().getList(first, max);
	}
	
	public Optional<Usuario> getUsuario(Long id){
		return this.getUsuarioRepository().getUsuario(id);
	}
}
