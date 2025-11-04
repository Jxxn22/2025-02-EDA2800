package repositories;

import java.math.BigDecimal;
import java.util.*;
import model.Cards;

public class CardsRepository {
	private final List<Cards> storage = new ArrayList<>();

	public CardsRepository() {
		initData();
	}

	private void initData() {
		storage.add(new Cards("4532123456789001", "Credit", new BigDecimal("5000"), new BigDecimal("1500"), new BigDecimal("3500")));
		storage.add(new Cards("4532123456789002", "Debit", new BigDecimal("10000"), new BigDecimal("2000"), new BigDecimal("8000")));
		storage.add(new Cards("4532123456789003", "Credit", new BigDecimal("8000"), new BigDecimal("3500"), new BigDecimal("4500")));
		storage.add(new Cards("4532123456789004", "Debit", new BigDecimal("15000"), new BigDecimal("5000"), new BigDecimal("10000")));
		storage.add(new Cards("4532123456789005", "Credit", new BigDecimal("12000"), new BigDecimal("6000"), new BigDecimal("6000")));
		storage.add(new Cards("4532123456789006", "Debit", new BigDecimal("7000"), new BigDecimal("1200"), new BigDecimal("5800")));
		storage.add(new Cards("4532123456789007", "Credit", new BigDecimal("20000"), new BigDecimal("8000"), new BigDecimal("12000")));
		storage.add(new Cards("4532123456789008", "Debit", new BigDecimal("5000"), new BigDecimal("800"), new BigDecimal("4200")));
		storage.add(new Cards("4532123456789009", "Credit", new BigDecimal("15000"), new BigDecimal("7500"), new BigDecimal("7500")));
		storage.add(new Cards("4532123456789010", "Debit", new BigDecimal("9000"), new BigDecimal("3000"), new BigDecimal("6000")));
	}

	public Cards save(Cards card) {
		if (card == null || card.getCardNumber() == null) {
			throw new IllegalArgumentException("Card o cardNumber no puede ser null");
		}
		storage.removeIf(c -> c.getCardNumber().equals(card.getCardNumber()));
		storage.add(card);
		return card;
	}

	public Optional<Cards> findById(String cardNumber) {
		if (cardNumber == null) return Optional.empty();
		return storage.stream()
			.filter(c -> cardNumber.equals(c.getCardNumber()))
			.findFirst();
	}

	public List<Cards> findAll() {
		return new ArrayList<>(storage);
	}

	public boolean deleteById(String cardNumber) {
		return storage.removeIf(c -> cardNumber.equals(c.getCardNumber()));
	}

	public boolean existsById(String cardNumber) {
		return storage.stream()
			.anyMatch(c -> cardNumber != null && cardNumber.equals(c.getCardNumber()));
	}
}