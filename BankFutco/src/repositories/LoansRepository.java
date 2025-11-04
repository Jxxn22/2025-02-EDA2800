package repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import model.Loans;

public class LoansRepository {
	private final List<Loans> storage = new ArrayList<>();

	public LoansRepository() {
		initData();
	}

	private void initData() {
		storage.add(new Loans(LocalDate.of(2023, 1, 10), "Home", new BigDecimal("150000"), new BigDecimal("30000"), new BigDecimal("120000")));
		storage.add(new Loans(LocalDate.of(2023, 3, 15), "Vehicle", new BigDecimal("25000"), new BigDecimal("10000"), new BigDecimal("15000")));
		storage.add(new Loans(LocalDate.of(2023, 5, 20), "Personal", new BigDecimal("10000"), new BigDecimal("5000"), new BigDecimal("5000")));
		storage.add(new Loans(LocalDate.of(2023, 7, 8), "Home", new BigDecimal("200000"), new BigDecimal("50000"), new BigDecimal("150000")));
		storage.add(new Loans(LocalDate.of(2023, 9, 12), "Vehicle", new BigDecimal("30000"), new BigDecimal("15000"), new BigDecimal("15000")));
		storage.add(new Loans(LocalDate.of(2023, 11, 5), "Personal", new BigDecimal("8000"), new BigDecimal("4000"), new BigDecimal("4000")));
		storage.add(new Loans(LocalDate.of(2024, 1, 18), "Home", new BigDecimal("180000"), new BigDecimal("40000"), new BigDecimal("140000")));
		storage.add(new Loans(LocalDate.of(2024, 3, 22), "Vehicle", new BigDecimal("35000"), new BigDecimal("20000"), new BigDecimal("15000")));
		storage.add(new Loans(LocalDate.of(2024, 5, 14), "Personal", new BigDecimal("12000"), new BigDecimal("6000"), new BigDecimal("6000")));
		storage.add(new Loans(LocalDate.of(2024, 7, 30), "Home", new BigDecimal("250000"), new BigDecimal("60000"), new BigDecimal("190000")));
	}

	public Loans save(Loans loan) {
		if (loan == null || loan.getDate() == null) {
			throw new IllegalArgumentException("Loan o date no puede ser null");
		}
		
		storage.removeIf(l -> l.getDate().equals(loan.getDate()));
		storage.add(loan);
		return loan;
	}

	public Optional<Loans> findById(String id) {
		if (id == null) return Optional.empty();
		try {
			LocalDate searchDate = LocalDate.parse(id);
			return storage.stream()
				.filter(l -> l.getDate().equals(searchDate))
				.findFirst();
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public List<Loans> findAll() {
		return new ArrayList<>(storage);
	}

	public boolean deleteById(String id) {
		if (id == null) return false;
		try {
			LocalDate searchDate = LocalDate.parse(id);
			return storage.removeIf(l -> l.getDate().equals(searchDate));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean existsById(String id) {
		if (id == null) return false;
		try {
			LocalDate searchDate = LocalDate.parse(id);
			return storage.stream()
				.anyMatch(l -> l.getDate().equals(searchDate));
		} catch (Exception e) {
			return false;
		}
	}
}