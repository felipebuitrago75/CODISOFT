package co.com.fxmanager.management.persistence.entities.jpa;

import java.math.BigDecimal;

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
@Table(name = FxSucursalEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class FxSucursalEntity {
	@UtilityClass
	public static final class TableInfo {
		
		public static final String TABLE_NAME = "fx_sucursal";
		public static final String ID = "id";
		public static final String FX = "fx";
		public static final String SUCURSAL = "sucursal";
		public static final String PRECIO_VENTA = "precio_venta";
		public static final String PRECIO_COMPRA = "precio_compra";
		public static final String PRECIO_VALORACION = "precio_valoracion";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String FX = "fx";
		public static final String SUCURSAL = "sucursal";
		public static final String PRECIO_VENTA = "precio_venta";
		public static final String PRECIO_COMPRA = "precio_compra";
		public static final String PRECIO_VALORACION = "precio_valoracion";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;

	@Column(name = TableInfo.PRECIO_COMPRA)
	@NonNull
	private BigDecimal precioCompra;
	
	@Column(name = TableInfo.PRECIO_VENTA)
	@NonNull
	private BigDecimal precioVenta;
	
	@Column(name = TableInfo.PRECIO_VALORACION)
	@NonNull
	private BigDecimal precioValoracion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.FX, referencedColumnName = FxEntity.TableInfo.ID)
	@NonNull
	private FxEntity fx;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.SUCURSAL, referencedColumnName = SucursalEntity.TableInfo.ID)
	@NonNull
	private SucursalEntity sucursal;

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
		FxSucursalEntity other = (FxSucursalEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	

	
	

}
