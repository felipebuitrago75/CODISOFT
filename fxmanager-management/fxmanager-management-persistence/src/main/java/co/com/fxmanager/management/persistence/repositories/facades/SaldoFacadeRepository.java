package co.com.fxmanager.management.persistence.repositories.facades;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.fxmanager.management.domain.entities.Saldo;
import co.com.fxmanager.management.persistence.entities.jpa.SaldoEntity;
import co.com.fxmanager.management.persistence.entities.jpa.converters.SaldoConverter;
import co.com.fxmanager.management.persistence.entities.jpa.converters.SaldoEntityConverter;
import co.com.fxmanager.management.persistence.repositories.SaldoRepository;
import co.com.fxmanager.management.persistence.repositories.exceptions.DatabaseException;
import co.com.fxmanager.management.persistence.repositories.exceptions.NoFoundDataException;
import co.com.fxmanager.management.persistence.repositories.springdata.SaldoSpringRepository;
import lombok.Getter;

@Getter
@Service
public class SaldoFacadeRepository implements SaldoRepository {

	private SaldoSpringRepository saldoSpringRepository;

	private SaldoConverter saldoConverter;

	private SaldoEntityConverter saldoEntityConverter;

	@Autowired
	public SaldoFacadeRepository(SaldoSpringRepository saldoSpringRepository,
			SaldoConverter saldoConverter, SaldoEntityConverter saldoEntityConverter) {
		super();
		this.saldoSpringRepository = saldoSpringRepository;
		this.saldoConverter = saldoConverter;
		this.saldoEntityConverter = saldoEntityConverter;
	}

	@Transactional
	@Override
	public Saldo create(Saldo saldo) {
		Saldo newSaldo;
		try {
			SaldoEntity saldoEntity =  getSaldoEntityConverter().convert(saldo);
			SaldoEntity newSaldoEntity = getSaldoSpringRepository().save(saldoEntity);
			newSaldo = getSaldoConverter().convert(newSaldoEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newSaldo;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Saldo> getList(int first, int max) {
		List<Saldo> listaSaldos;
		try {
			Page<SaldoEntity> page = getSaldoSpringRepository().findAll(PageRequest.of(first / max, max));
			listaSaldos = page.stream().map(saldoEntity -> getSaldoConverter().convert(saldoEntity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return listaSaldos;
	}

	@Transactional
	@Override
	public Saldo update(Long id, Saldo saldo) {
		Saldo newSaldoTurno;
		try {
			SaldoEntity saldoEntity = getSaldoEntityConverter().convert(saldo, () -> {
				Optional<SaldoEntity> saldoEntityOptional = getSaldoSpringRepository().findById(id);
				return saldoEntityOptional.orElseThrow(NoFoundDataException::new);
			});
			
			SaldoEntity newSaldoEntity = getSaldoSpringRepository().save(saldoEntity);
			newSaldoTurno = getSaldoConverter().convert(newSaldoEntity);
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return newSaldoTurno;
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Saldo> getSaldo(Long id) {
		Optional<Saldo> saldo;
		try {
			Optional<SaldoEntity> saldoEntityOptional = getSaldoSpringRepository().findById(id);
			saldo = saldoEntityOptional.map(aperturaTurnoEntity -> getSaldoConverter().convert(aperturaTurnoEntity));
		} catch (Exception e) {
			throw new DatabaseException(e);
		}
		return saldo;
	}

}
