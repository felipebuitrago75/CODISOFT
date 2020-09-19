package co.com.fxmanager.management.persistence.entities.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = SucursalEntity.TableInfo.TABLE_NAME)
@Getter
@Setter
@NamedEntityGraphs({ @NamedEntityGraph(name = SucursalEntity.EntityGraph.WITH_LISTA_PRECIOS_CAJAS, attributeNodes = {
		@NamedAttributeNode(SucursalEntity.ClassInfo.LISTA_PRECIOS)
		 }) })
public class SucursalEntity  {

	//@NamedAttributeNode(SucursalEntity.ClassInfo.LISTA_CAJAS)
	
	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "sucursal";
		public static final String ID = "id";
		public static final String COD = "cod";
		public static final String NOMBRE = "nombre";
		public static final String DIRECCION = "direccion";
		public static final String ESTADO = "estado";
		public static final String TELEFONOS = "telefonos";
		public static final String CHK_PRINCIPAL = "chk_principal";

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String COD = "cod";
		public static final String NOMBRE = "nombre";
		public static final String DIRECCION = "direccion";
		public static final String ESTADO = "estado";
		public static final String TELEFONOS = "telefonos";
		public static final String CHK_PRINCIPAL = "chk_principal";
		public static final String LISTA_PRECIOS = "listaPrecios";
		public static final String LISTA_CAJAS = "listaCajas";
		
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_LISTA_PRECIOS_CAJAS = "FX_WITH_LISTA_PRECIOS_CAJAS";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;
	
	@Column(name = TableInfo.COD)
	@NonNull
	private String codigo;
	
	@Column(name = TableInfo.NOMBRE)
	@NonNull
	private String nombre;
	
	@Column(name = TableInfo.DIRECCION)
	@NonNull
	private String direccion;
	
	@Column(name = TableInfo.ESTADO)
	@NonNull
	private String estado;
	
	@Column(name = TableInfo.TELEFONOS)
	private String telefonos;
	
	@Column(name = TableInfo.CHK_PRINCIPAL)
	@NonNull
	private Boolean chkPrincipal;
	

	@OneToMany(mappedBy = FxSucursalEntity.ClassInfo.SUCURSAL, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private List<FxSucursalEntity> listaPrecios;
	
	@OneToMany(mappedBy = CajaEntity.ClassInfo.SUCURSAL, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private Set<CajaEntity> listaCajas;

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
		SucursalEntity other = (SucursalEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
