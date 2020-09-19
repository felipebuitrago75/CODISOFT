package co.com.fxmanager.management.persistence.entities.jpa.converters;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.persistence.entities.jpa.UserEntity;
import co.com.fxmanager.auth.persistence.repositories.springdata.UserSpringRepository;
import co.com.fxmanager.management.domain.entities.Usuario;
import co.com.fxmanager.management.persistence.entities.jpa.UsuarioEntity;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import lombok.Getter;

@Getter
@Component
public class UsuarioEntityConverter implements EntityConverter<Usuario, UsuarioEntity> {



	@Autowired
	private UserSpringRepository userSpringRepository;
	
		
	@Override
	public UsuarioEntity convert(Usuario usuario) {
		return convert(usuario, UsuarioEntity::new);
	}

	@Override
	public UsuarioEntity convert(Usuario usuario, Supplier<UsuarioEntity> supplier) {
		UsuarioEntity usuarioEntity = supplier.get();

		if(usuario.getId()!=null) {
			usuarioEntity.setId(usuario.getId());
		}
		
		usuarioEntity.setNombre(usuario.getNombre());
		usuarioEntity.setApellido(usuario.getApellido());
		

		Optional<UserEntity> userEntityOptional = getUserSpringRepository().findByUsername(usuario.getUser().getUsername());
		usuarioEntity.setUser(userEntityOptional.orElseThrow(NoFoundDataException::new));
		
		return usuarioEntity;
	}

}
