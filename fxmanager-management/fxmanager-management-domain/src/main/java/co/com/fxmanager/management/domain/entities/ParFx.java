package co.com.fxmanager.management.domain.entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
public class ParFx {

	@UtilityClass
	public static final class ClassInfo {
		public static final String FX_BASE = "fxBase";
		public static final String FX_OPERACION = "fxOper";
		public static final String VALOR_FX_OPERACION = "valorFxOperacion";
		public static final String VALOR_VALORACION = "valorValoracion";

	}

	@NonNull
	protected Fx fxBase;

	@NonNull
	protected Fx fxOper;

	@NonNull
	protected BigDecimal valorFxOperacion;

	@NonNull
	protected BigDecimal valorValoracion;

	@SuppressWarnings("unused")
	private ParFx() {
	}

	public ParFx(@NonNull Fx fxBase, @NonNull Fx fxOper, @NonNull BigDecimal valorFxOperacion,
			@NonNull BigDecimal valorValoracion) {
		super();
		this.fxBase = fxBase;
		this.fxOper = fxOper;
		this.valorFxOperacion = valorFxOperacion;
		this.valorValoracion = valorValoracion;
	}


}
