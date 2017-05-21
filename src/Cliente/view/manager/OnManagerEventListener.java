package Cliente.view.manager;

public interface OnManagerEventListener {

	void createAccount(String clientID, String accountName, String accountType);

	void closeAccount(String clientID, String accountNumber);

	void createLoan(String clientID, String amount, String payment, String rate);

}
