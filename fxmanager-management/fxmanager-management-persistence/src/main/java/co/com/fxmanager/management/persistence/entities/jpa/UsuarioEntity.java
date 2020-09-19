package co.com.fxmanager.management.persistence.entities.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.com.fxmanager.auth.persistence.entities.jpa.UserEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = UsuarioEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class UsuarioEntity  {

	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "usuario";
		public static final String ID = "id";
		public static final String NOMBRE = "nombre";
		public static final String APELLIDO = "apellido";
		public static final String USER = "user";
	

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String NOMBRE = "nombre";
		public static final String APELLIDO = "apellido";
		public static final String USER = "user";
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	
	@Column(name = TableInfo.NOMBRE)
	@NonNull
	private String nombre;
	
	@Column(name = TableInfo.APELLIDO)
	@NonNull
	private String apellido;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.USER, referencedColumnName = UserEntity.TableInfo.ID)
	@NonNull
	private UserEntity user;

}
