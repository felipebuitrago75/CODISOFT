package co.com.fxmanager.management.persistence.entities.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Entity
@Table(name = CajaEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = CajaEntity.EntityGraph.WITH_SALDOS, attributeNodes = {
		@NamedAttributeNode(CajaEntity.ClassInfo.LISTA_SALDOS)
		 }) })
public class CajaEntity {
	@UtilityClass
	public static final class TableInfo {
		
		public static final String TABLE_NAME = "caja";
		public static final String ID = "id";
		public static final String NOMBRE = "nombre";
		public static final String SUCURSAL = "sucursal";

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String NOMBRE = "nombre";
		public static final String SUCURSAL = "sucursal";
		public static final String LISTA_SALDOS = "listaSaldos";
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_SALDOS = "CAJA_WITH_LISTA_CAJAS";
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;

	@Column(name = TableInfo.NOMBRE)
	@NonNull
	private String nombre;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.SUCURSAL, referencedColumnName = SucursalEntity.TableInfo.ID)
	@NonNull
	private SucursalEntity sucursal;

	@OneToMany(mappedBy = SaldoEntity.ClassInfo.CAJA, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private List<SaldoEntity> listaSaldos;

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
		CajaEntity other = (CajaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

	

	
	

}