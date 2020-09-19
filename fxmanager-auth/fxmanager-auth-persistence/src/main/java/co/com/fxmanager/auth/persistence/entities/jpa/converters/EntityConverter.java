package co.com.fxmanager.auth.persistence.entities.jpa.converters;

import java.util.function.Supplier;

import org.springframework.core.convert.converter.Converter;

public interface EntityConverter<S, T> extends Converter<S, T> {

	public T convert(S source, Supplier<T> target);
}
