package co.com.fxmanager.management.persistence.entities.jpa.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.com.fxmanager.auth.persistence.entities.jpa.converters.UserConverter;
import co.com.fxmanager.management.domain.entities.Usuario;
import co.com.fxmanager.management.persistence.entities.jpa.UsuarioEntity;
import lombok.Getter;

@Component
@Getter
public class UsuarioConverter implements Converter<UsuarioEntity, Usuario> {

	@Autowired
	private UserConverter userConverter;
	
	public UsuarioConverter() {
		super();
	}

	@Override
	public Usuario convert(UsuarioEntity usuarioEntity) {
		return new Usuario(usuarioEntity.getId(), usuarioEntity.getNombre(),
				usuarioEntity.getApellido(), 
				userConverter.convert(usuarioEntity.getUser()));
	}

}
