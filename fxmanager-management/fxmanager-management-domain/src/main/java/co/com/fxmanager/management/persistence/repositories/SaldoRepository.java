package co.com.fxmanager.management.persistence.repositories;

import java.util.Optional;

import co.com.fxmanager.management.domain.entities.Saldo;

public interface SaldoRepository extends AbstractRepository<Saldo> {
	
	public Saldo update(Long id, Saldo saldo);

	public Optional<Saldo> getSaldo(Long id);

}
