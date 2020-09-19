package co.com.fxmanager.management.persistence.entities.jpa;

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
@Table(name = DenominacionEntity.TableInfo.TABLE_NAME)
@Setter
@Getter
public class DenominacionEntity {
	@UtilityClass
	public static final class TableInfo {
		public static final String TABLE_NAME = "denominacion";
		public static final String ID = "id";
		public static final String VALOR = "valor";
		public static final String MONEDA = "moneda";
	}

	@UtilityClass
	public static final class ClassInfo {
		public static final String VALOR = "valor";
		public static final String FX = "fx";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = TableInfo.ID)
	@NonNull
	private Long id;

	@Column(name = TableInfo.VALOR)
	@NonNull
	private Integer valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = TableInfo.MONEDA, referencedColumnName = FxEntity.TableInfo.ID)
	@NonNull
	private FxEntity fx;

}
