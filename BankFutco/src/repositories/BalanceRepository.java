package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import model.Balance;;

public class BalanceRepository {
	private final List<Balance> storage = new ArrayList<>();

	public BalanceRepository() {
		initData();
	}

	private void initData() {
		storage.add(new Balance(LocalDate.of(2024, 1, 15), "Depósito inicial", new BigDecimal("5000"), BigDecimal.ZERO, new BigDecimal("5000")));
		storage.add(new Balance(LocalDate.of(2024, 2, 10), "Transferencia recibida", new BigDecimal("2000"), BigDecimal.ZERO, new BigDecimal("7000")));
		storage.add(new Balance(LocalDate.of(2024, 3, 5), "Pago de servicios", BigDecimal.ZERO, new BigDecimal("500"), new BigDecimal("6500")));
		storage.add(new Balance(LocalDate.of(2024, 4, 20), "Salario", new BigDecimal("3000"), BigDecimal.ZERO, new BigDecimal("9500")));
		storage.add(new Balance(LocalDate.of(2024, 5, 12), "Compra supermercado", BigDecimal.ZERO, new BigDecimal("800"), new BigDecimal("8700")));
		storage.add(new Balance(LocalDate.of(2024, 6, 8), "Bonificación", new BigDecimal("1500"), BigDecimal.ZERO, new BigDecimal("10200")));
		storage.add(new Balance(LocalDate.of(2024, 7, 25), "Retiro cajero", BigDecimal.ZERO, new BigDecimal("300"), new BigDecimal("9900")));
		storage.add(new Balance(LocalDate.of(2024, 8, 15), "Transferencia enviada", BigDecimal.ZERO, new BigDecimal("1000"), new BigDecimal("8900")));
		storage.add(new Balance(LocalDate.of(2024, 9, 3), "Depósito efectivo", new BigDecimal("2500"), BigDecimal.ZERO, new BigDecimal("11400")));
		storage.add(new Balance(LocalDate.of(2024, 10, 18), "Pago tarjeta", BigDecimal.ZERO, new BigDecimal("1200"), new BigDecimal("10200")));
	}

	public Balance save(Balance balance) {
		if (balance == null || balance.getDate() == null) {
			throw new IllegalArgumentException("Balance o date no puede ser null");
		}
		
		storage.removeIf(b -> b.getDate().equals(balance.getDate()));
		storage.add(balance);
		return balance;
	}

	public Optional<Balance> findById(String id) {
		if (id == null) return Optional.empty();
		try {
			LocalDate searchDate = LocalDate.parse(id);
			return storage.stream()
				.filter(b -> b.getDate().equals(searchDate))
				.findFirst();
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public List<Balance> findAll() {
		return new ArrayList<>(storage);
	}

	public boolean deleteById(String id) {
		if (id == null) return false;
		try {
			LocalDate searchDate = LocalDate.parse(id);
			return storage.removeIf(b -> b.getDate().equals(searchDate));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean existsById(String id) {
		if (id == null) return false;
		try {
			LocalDate searchDate = LocalDate.parse(id);
			return storage.stream()
				.anyMatch(b -> b.getDate().equals(searchDate));
		} catch (Exception e) {
			return false;
		}
	}
}