import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import model.Account;
import model.Balance;
import model.Cards;
import model.Loans;
import services.AccountService;
import services.BalanceService;
import services.CardsService;
import services.LoansService;

public class App {
    private static final AccountService accountService = new AccountService();
    private static final BalanceService balanceService = new BalanceService();
    private static final CardsService cardsService = new CardsService();
    private static final LoansService loansService = new LoansService();

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1" -> runAccountCrud(sc);
                    case "2" -> runBalanceCrud(sc);
                    case "3" -> runLoansCrud(sc);
                    case "4" -> runCardsCrud(sc);
                    case "0" -> {
                        running = false;
                        System.out.println("Saliendo...");
                    }
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // CRUD para Account
    private static void runAccountCrud(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Account");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> {
                    System.out.println("\n--- Crear Account ---");
                    System.out.print("Account Number: ");
                    String accNum = sc.nextLine().trim();
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Mobile Number: ");
                    String mobile = sc.nextLine().trim();
                    System.out.print("Account Type: ");
                    String type = sc.nextLine().trim();
                    System.out.print("Address: ");
                    String address = sc.nextLine().trim();
                    
                    Account account = new Account(accNum, name, email, mobile, type, address);
                    accountService.save(account);
                    System.out.println("✓ Account guardado exitosamente");
                }
                case "2" -> {
                    System.out.print("\nLeer por id - ingrese account number: ");
                    String id = sc.nextLine().trim();
                    accountService.findById(id).ifPresentOrElse(
                            acc -> System.out.println("Encontrado: " + acc),
                            () -> System.out.println("Account con id=" + id + " no encontrado.")
                    );
                }
                case "3" -> {
                    System.out.println("\n--- Listado de Accounts ---");
                    accountService.findAll().forEach(System.out::println);
                }
                case "4" -> {
                    System.out.print("\nActualizar - ingrese account number: ");
                    String idUp = sc.nextLine().trim();
                    if (accountService.findById(idUp).isPresent()) {
                        System.out.print("Name: ");
                        String nameUp = sc.nextLine().trim();
                        System.out.print("Email: ");
                        String emailUp = sc.nextLine().trim();
                        System.out.print("Mobile Number: ");
                        String mobileUp = sc.nextLine().trim();
                        System.out.print("Account Type: ");
                        String typeUp = sc.nextLine().trim();
                        System.out.print("Address: ");
                        String addressUp = sc.nextLine().trim();
                        
                        Account updateAccount = new Account(idUp, nameUp, emailUp, mobileUp, typeUp, addressUp);
                        accountService.save(updateAccount);
                        System.out.println("✓ Account actualizado exitosamente");
                    } else {
                        System.out.println("Account no encontrado");
                    }
                }
                case "5" -> {
                    System.out.print("\nEliminar - ingrese account number: ");
                    String idDel = sc.nextLine().trim();
                    if (accountService.deleteById(idDel)) {
                        System.out.println("✓ Account eliminado exitosamente");
                    } else {
                        System.out.println("Account no encontrado");
                    }
                }
                case "0" -> back = true;
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // CRUD para Balance
    private static void runBalanceCrud(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Balance");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> {
                    System.out.println("\n--- Crear Balance ---");
                    System.out.print("Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(sc.nextLine().trim());
                    System.out.print("Description: ");
                    String desc = sc.nextLine().trim();
                    System.out.print("Cash In: ");
                    BigDecimal cashIn = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Cash Out: ");
                    BigDecimal cashOut = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Closing Balance: ");
                    BigDecimal closing = new BigDecimal(sc.nextLine().trim());
                    
                    Balance balance = new Balance(date, desc, cashIn, cashOut, closing);
                    balanceService.save(balance);
                    System.out.println("✓ Balance guardado exitosamente");
                }
                case "2" -> {
                    System.out.print("\nLeer por id (date YYYY-MM-DD): ");
                    String id = sc.nextLine().trim();
                    balanceService.findById(id).ifPresentOrElse(
                            b -> System.out.println("Encontrado: Date=" + b.getDate() + ", Desc=" + b.getDescription() +
                                    ", CashIn=" + b.getCashIn() + ", CashOut=" + b.getCashOut() +
                                    ", Closing=" + b.getClosingBalance()),
                            () -> System.out.println("Balance no encontrado.")
                    );
                }
                case "3" -> {
                    System.out.println("\n--- Listado de Balances ---");
                    balanceService.findAll().forEach(b ->
                            System.out.println("Date=" + b.getDate() + ", Desc=" + b.getDescription() +
                                    ", CashIn=" + b.getCashIn() + ", CashOut=" + b.getCashOut() +
                                    ", Closing=" + b.getClosingBalance()));
                }
                case "4" -> {
                    System.out.print("\nActualizar - ingrese date (YYYY-MM-DD): ");
                    String idUp = sc.nextLine().trim();
                    System.out.print("Description: ");
                    String descUp = sc.nextLine().trim();
                    System.out.print("Cash In: ");
                    BigDecimal cashInUp = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Cash Out: ");
                    BigDecimal cashOutUp = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Closing Balance: ");
                    BigDecimal closingUp = new BigDecimal(sc.nextLine().trim());
                    
                    Balance updateBalance = new Balance(LocalDate.parse(idUp), descUp, cashInUp, cashOutUp, closingUp);
                    balanceService.save(updateBalance);
                    System.out.println("✓ Balance actualizado exitosamente");
                }
                case "5" -> {
                    System.out.print("\nEliminar - ingrese date (YYYY-MM-DD): ");
                    String idDel = sc.nextLine().trim();
                    if (balanceService.deleteById(idDel)) {
                        System.out.println("✓ Balance eliminado exitosamente");
                    } else {
                        System.out.println("Balance no encontrado");
                    }
                }
                case "0" -> back = true;
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // CRUD para Loans
    private static void runLoansCrud(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Loans");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> {
                    System.out.println("\n--- Crear Loan ---");
                    System.out.print("Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(sc.nextLine().trim());
                    System.out.print("Type (Home/Vehicle/Personal): ");
                    String type = sc.nextLine().trim();
                    System.out.print("Total Loan: ");
                    BigDecimal total = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Amount Paid: ");
                    BigDecimal paid = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Outstanding Amount: ");
                    BigDecimal outstanding = new BigDecimal(sc.nextLine().trim());
                    
                    Loans loan = new Loans(date, type, total, paid, outstanding);
                    loansService.save(loan);
                    System.out.println("✓ Loan guardado exitosamente");
                }
                case "2" -> {
                    System.out.print("\nLeer por id (date YYYY-MM-DD): ");
                    String id = sc.nextLine().trim();
                    loansService.findById(id).ifPresentOrElse(
                            l -> System.out.println("Encontrado: Date=" + l.getDate() + ", Type=" + l.getType() +
                                    ", Total=" + l.getTotalLoan() + ", Paid=" + l.getAmountPaid() +
                                    ", Outstanding=" + l.getOutstandingAmt()),
                            () -> System.out.println("Loan no encontrado.")
                    );
                }
                case "3" -> {
                    System.out.println("\n--- Listado de Loans ---");
                    loansService.findAll().forEach(l ->
                            System.out.println("Date=" + l.getDate() + ", Type=" + l.getType() +
                                    ", Total=" + l.getTotalLoan() + ", Paid=" + l.getAmountPaid() +
                                    ", Outstanding=" + l.getOutstandingAmt()));
                }
                case "4" -> {
                    System.out.print("\nActualizar - ingrese date (YYYY-MM-DD): ");
                    String idUp = sc.nextLine().trim();
                    System.out.print("Type (Home/Vehicle/Personal): ");
                    String typeUp = sc.nextLine().trim();
                    System.out.print("Total Loan: ");
                    BigDecimal totalUp = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Amount Paid: ");
                    BigDecimal paidUp = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Outstanding Amount: ");
                    BigDecimal outstandingUp = new BigDecimal(sc.nextLine().trim());
                    
                    Loans updateLoan = new Loans(LocalDate.parse(idUp), typeUp, totalUp, paidUp, outstandingUp);
                    loansService.save(updateLoan);
                    System.out.println("✓ Loan actualizado exitosamente");
                }
                case "5" -> {
                    System.out.print("\nEliminar - ingrese date (YYYY-MM-DD): ");
                    String idDel = sc.nextLine().trim();
                    if (loansService.deleteById(idDel)) {
                        System.out.println("✓ Loan eliminado exitosamente");
                    } else {
                        System.out.println("Loan no encontrado");
                    }
                }
                case "0" -> back = true;
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // CRUD para Cards
    private static void runCardsCrud(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Cards");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> {
                    System.out.println("\n--- Crear Card ---");
                    System.out.print("Card Number: ");
                    String cardNum = sc.nextLine().trim();
                    System.out.print("Type (Credit/Debit): ");
                    String type = sc.nextLine().trim();
                    System.out.print("Total Limit: ");
                    BigDecimal limit = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Amount Used: ");
                    BigDecimal used = new BigDecimal(sc.nextLine().trim());
                    System.out.print("Available: ");
                    BigDecimal available = new BigDecimal(sc.nextLine().trim());
                    
                    Cards card = new Cards(cardNum, type, limit, used, available);
                    cardsService.save(card);
                    System.out.println("✓ Card guardada exitosamente");
                }
                case "2" -> {
                    System.out.print("\nLeer por id - ingrese card number: ");
                    String id = sc.nextLine().trim();
                    cardsService.findById(id).ifPresentOrElse(
                            c -> System.out.println("Encontrado: CardNum=" + c.getCardNumber() + ", Type=" + c.getType() +
                                    ", Limit=" + c.getTotalLimit() + ", Used=" + c.getAmountUsed() +
                                    ", Available=" + c.getAvailable()),
                            () -> System.out.println("Card no encontrada.")
                    );
                }
                case "3" -> {
                    System.out.println("\n--- Listado de Cards ---");
                    cardsService.findAll().forEach(c ->
                            System.out.println("CardNum=" + c.getCardNumber() + ", Type=" + c.getType() +
                                    ", Limit=" + c.getTotalLimit() + ", Used=" + c.getAmountUsed() +
                                    ", Available=" + c.getAvailable()));
                }
                case "4" -> {
                    System.out.print("\nActualizar - ingrese card number: ");
                    String idUp = sc.nextLine().trim();
                    if (cardsService.findById(idUp).isPresent()) {
                        System.out.print("Type (Credit/Debit): ");
                        String typeUp = sc.nextLine().trim();
                        System.out.print("Total Limit: ");
                        BigDecimal limitUp = new BigDecimal(sc.nextLine().trim());
                        System.out.print("Amount Used: ");
                        BigDecimal usedUp = new BigDecimal(sc.nextLine().trim());
                        System.out.print("Available: ");
                        BigDecimal availableUp = new BigDecimal(sc.nextLine().trim());
                        
                        Cards updateCard = new Cards(idUp, typeUp, limitUp, usedUp, availableUp);
                        cardsService.save(updateCard);
                        System.out.println("✓ Card actualizada exitosamente");
                    } else {
                        System.out.println("Card no encontrada");
                    }
                }
                case "5" -> {
                    System.out.print("\nEliminar - ingrese card number: ");
                    String idDel = sc.nextLine().trim();
                    if (cardsService.deleteById(idDel)) {
                        System.out.println("✓ Card eliminada exitosamente");
                    } else {
                        System.out.println("Card no encontrada");
                    }
                }
                case "0" -> back = true;
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }
}