package co.com.fxmanager.management.persistence.entities.jpa;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = TurnoEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class TurnoEntity {
	@UtilityClass
	public static final class TableInfo {
		
		public static final String TABLE_NAME = "turno";
		public static final String ID = "id";
		public static final String CAJA = "caja";
		public static final String USUARIO = "usuario";
		public static final String FECHA_INICIO = "fecha_inicio";
		public static final String FECHA_FIN = "fecha_fin";

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String CAJA = "caja";
		public static final String USUARIO = "usuario";
		public static final String FECHA_INICIO = "fechaInicio";
		public static final String FECHA_FIN = "fechaFin";
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;

	@Column(name = TableInfo.FECHA_INICIO)
	@NonNull
	private LocalDateTime fechaInicio;
	
	@Column(name = TableInfo.FECHA_FIN)
	private LocalDateTime fechaFin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = TableInfo.USUARIO, referencedColumnName = UsuarioEntity.TableInfo.ID)
	@NonNull
	private UsuarioEntity usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.CAJA, referencedColumnName = CajaEntity.TableInfo.ID)
	@NonNull
	private CajaEntity caja;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnoEntity other = (TurnoEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

	

	
	

}
