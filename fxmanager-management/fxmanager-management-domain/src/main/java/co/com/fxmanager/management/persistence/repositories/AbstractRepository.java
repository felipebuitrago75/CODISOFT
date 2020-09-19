package co.com.fxmanager.management.persistence.repositories;

import java.util.List;
import java.util.stream.Stream;

public interface AbstractRepository<D> {

	public D create(D domainEntity);

	public List<D> getList(int first, int max);

	public default List<D> getList() {
		return getList(-1, -1);
	}

	public default Stream<D> getAllAsStream() {
		return getList().stream();
	}

	public default Stream<D> getAllAsStream(int first, int max) {
		return getList(first, max).stream();
	}
}
