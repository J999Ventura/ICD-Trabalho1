package Cliente.view.manager;

import java.util.List;

public interface OnManagerEventListener {

	void createAccount(String nib, String accountName, String accountType);

	void closeAccount(String nib, String accountNumber);

	void createLoan(String nib, String amount, String payment, String rate);

	List<String> onGetAccountTypes();

}
