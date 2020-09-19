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
@Table(name = CierreTurnoEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class CierreTurnoEntity {
	
	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "cierre_turno";
		public static final String ID = "id";
		public static final String TURNO = "turno";
		public static final String MONEDA = "moneda";
		public static final String NOMINAL = "nominal";
		public static final String PRECIO_PROMEDIO = "precio_promedio";

	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String ID = "id";
		public static final String TURNO = "turno";
		public static final String MONEDA = "moneda";
		public static final String NOMINAL = "nominal";
		public static final String PRECIO_PROMEDIO = "precioPromedio";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.TURNO, referencedColumnName = TurnoEntity.TableInfo.ID)
	@NonNull
	private TurnoEntity turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.MONEDA, referencedColumnName = FxEntity.TableInfo.ID)
	@NonNull
	private FxEntity moneda;

	@Column(name = TableInfo.NOMINAL)
	@NonNull
	private BigDecimal nominal;

	@Column(name = TableInfo.PRECIO_PROMEDIO)
	private BigDecimal precioPromedio;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moneda == null) ? 0 : moneda.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
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
		CierreTurnoEntity other = (CierreTurnoEntity) obj;
		if (moneda == null) {
			if (other.moneda != null)
				return false;
		} else if (!moneda.equals(other.moneda))
			return false;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		return true;
	}

}
