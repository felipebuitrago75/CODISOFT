package co.com.fxmanager.management.persistence.entities.jpa;

import java.math.BigDecimal;
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
@Table(name = SaldoEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
@NamedEntityGraphs({ @NamedEntityGraph(name = SaldoEntity.EntityGraph.WITH_DENOMINACIONES, attributeNodes = {
		@NamedAttributeNode(SaldoEntity.ClassInfo.DENOMINACIONES_CANTIDAD )}) })
public class SaldoEntity {
	@UtilityClass
	public static final class TableInfo {
		
		public static final String TABLE_NAME = "saldo";
		public static final String ID = "id";
		public static final String CAJA = "caja";
		public static final String FX = "moneda";
		public static final String NOMINAL = "nominal";
		public static final String PRECIO_PROMEDIO = "precio_promedio";

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String CAJA = "caja";
		public static final String FX = "fx";
		public static final String NOMINAL = "nominal";
		public static final String PRECIO_PROMEDIO = "precioPromedio";
		public static final String DENOMINACIONES_CANTIDAD = "denominacionesCantidad";
		
	}
	
	@UtilityClass
	public static final class EntityGraph {
		public static final String WITH_DENOMINACIONES = "WITH_DENOMINACIONES";
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.CAJA, referencedColumnName = CajaEntity.TableInfo.ID)
	@NonNull
	private CajaEntity caja;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FX, referencedColumnName = FxEntity.TableInfo.ID)
	@NonNull
	private FxEntity fx;
	
	@Column(name = TableInfo.NOMINAL)
	@NonNull
	private BigDecimal nominal;
	
	@Column(name = TableInfo.PRECIO_PROMEDIO)
	@NonNull
	private BigDecimal precioPromedio;

	@OneToMany(mappedBy = DenominacionCantidadEntity.ClassInfo.SALDO, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	private List<DenominacionCantidadEntity> denominacionesCantidad;
	
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
		SaldoEntity other = (SaldoEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	


}
