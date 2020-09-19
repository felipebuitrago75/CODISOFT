package co.com.fxmanager.management.persistence.repositories.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.management.domain.entities.Usuario;
import co.com.fxmanager.management.persistence.entities.jpa.UsuarioEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.UsuarioConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.UsuarioEntityConverter;
import co.com.fxmanager.management.persistence.repositories.UsuarioRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.UsuarioSpringRepository;
import lombok.Getter;

@Getter
@Service
public class UsuarioFacadeRepository implements UsuarioRepository {

	private UsuarioSpringRepository usuarioSpringRepository;

	private UsuarioEntityConverter usuarioEntityConverter;

	private UsuarioConverter usuarioConverter;

	@Autowired
	public UsuarioFacadeRepository( UsuarioSpringRepository usuarioSpringRepository, 
			UsuarioEntityConverter usuarioEntityConverter,
			UsuarioConverter usuarioConverter) {
		super();
		this.usuarioSpringRepository = usuarioSpringRepository;
		this.usuarioEntityConverter = usuarioEntityConverter;
		this.usuarioConverter = usuarioConverter;
	}

	@Transactional
	@Override
	public Usuario create(Usuario usuario) {
		Usuario newUsuario;
		try {
			UsuarioEntity usuarioEntity = getUsuarioEntityConverter().convert(usuario);
			UsuarioEntity newUsuarioEntity = getUsuarioSpringRepository().save(usuarioEntity);
			newUsuario = getUsuarioConverter().convert(newUsuarioEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newUsuario;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Usuario> getList(int first, int max) {
		List<Usuario> listaUsuarios;
		try {
			Page<UsuarioEntity> page = getUsuarioSpringRepository().findAll(PageRequest.of(first / max, max));
			listaUsuarios = page.stream().map(usuarioEntity -> getUsuarioConverter().convert(usuarioEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaUsuarios;
	}

	@Transactional
	@Override
	public Usuario update(Long id, Usuario usuario) {
		Usuario newUSuario;
		try {
			System.out.println("update Usuario");
			UsuarioEntity usuarioEntity = getUsuarioEntityConverter().convert(usuario, () -> {
				Optional<UsuarioEntity> usuarioEntityOptioanal = getUsuarioSpringRepository().findById(id);
				return usuarioEntityOptioanal.orElseThrow(NoFoundDataException::new);
			});
			
			UsuarioEntity newUsuarioEntity = getUsuarioSpringRepository().save(usuarioEntity);
			newUSuario = getUsuarioConverter().convert(newUsuarioEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newUSuario;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		try {
			Optional<UsuarioEntity> usuarioEntityOptioanal = getUsuarioSpringRepository().findById(id);
			usuarioEntityOptioanal.ifPresent(getUsuarioSpringRepository()::delete);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Usuario> getUsuario(Long id) {
		Optional<Usuario> usuario;
		try {
			Optional<UsuarioEntity> usuarioEntityOptioanal = getUsuarioSpringRepository().findById(id);
			usuario = usuarioEntityOptioanal.map(cajaEntity -> getUsuarioConverter().convert(cajaEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return usuario;
	}
}
