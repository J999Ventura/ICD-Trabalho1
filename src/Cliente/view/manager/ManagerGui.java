package Cliente.view.manager;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;

import Cliente.OnCommunEventListener;
import commun.Cliente;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ManagerGui extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel BackgroundPanel;
	private OnManagerEventListener actionListener;
	private OnCommunEventListener communActionListener;
	private JPanel contentPanel;
	private JPanel closeOrOpenAccountsPanel;
	private JPanel makeLoansPanel;
	private JLabel userNameLabel;
	private JPanel menuPanel;
	
	public ManagerGui(Cliente user) {
		createMenuPanel();
		createGui();
	}
	
	private void createGui() {
		createPanel();
	}

	private void createPanel() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 848, 565);
		setUndecorated(true);
		setResizable(false); //doesn't allow the maximize or minimize option on the window
		BackgroundPanel = new JPanel();

		BackgroundPanel.setBackground(new Color(36, 47, 65));
		BackgroundPanel.setForeground(Color.BLACK);
		BackgroundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BackgroundPanel);
		BackgroundPanel.setLayout(null);
		
		BackgroundPanel.add(menuPanel);
		
		contentPanel = new JPanel();
		contentPanel.setBounds(195, 0, 653, 565);
		BackgroundPanel.add(contentPanel);
		contentPanel.setLayout(new CardLayout(0, 0));
		
		closeOrOpenAccountsPanel();
		makeLoansPanel();
	}
	
	/*
	 * 
	 * CREATE makeLoansPanel
	 * 
	 */
	private void makeLoansPanel(){
		makeLoansPanel = new JPanel();
		makeLoansPanel.setBackground(new Color(97, 212, 195));
		makeLoansPanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		contentPanel.add(makeLoansPanel, "name_991931529768702");
		makeLoansPanel.setLayout(null);
		
		JLabel makeLoanstxt = new JLabel("Make Loans");
		makeLoanstxt.setHorizontalAlignment(SwingConstants.CENTER);
		makeLoanstxt.setForeground(Color.WHITE);
		makeLoanstxt.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		makeLoanstxt.setBounds(265, 11, 152, 36);
		makeLoansPanel.add(makeLoanstxt);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(10, 11, 633, 2);
		makeLoansPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(10, 49, 633, 2);
		makeLoansPanel.add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(10, 541, 633, 2);
		makeLoansPanel.add(separator_3);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(10, 120, 633, 2);
		makeLoansPanel.add(separator_2);
		
		JLabel lblLoan = new JLabel("Loan");
		lblLoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoan.setForeground(Color.WHITE);
		lblLoan.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblLoan.setBounds(281, 94, 121, 14);
		makeLoansPanel.add(lblLoan);
		
		JLabel lblLoanAmount = new JLabel("Loan Amount");
		lblLoanAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoanAmount.setForeground(Color.WHITE);
		lblLoanAmount.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblLoanAmount.setBounds(244, 146, 161, 14);
		makeLoansPanel.add(lblLoanAmount);
		
		JTextArea amountLabel = new JTextArea();
		amountLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		amountLabel.setEditable(false);
		amountLabel.setBounds(244, 171, 161, 23);
		makeLoansPanel.add(amountLabel);
		
		JTextArea clientIdLabel = new JTextArea();
		clientIdLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		clientIdLabel.setEditable(false);
		clientIdLabel.setBounds(10, 171, 161, 23);
		makeLoansPanel.add(clientIdLabel);
		
		JLabel label_2 = new JLabel("Client ID");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_2.setBounds(10, 146, 155, 14);
		makeLoansPanel.add(label_2);
		
		JLabel lblMonthlyPayment = new JLabel("Monthly Payment");
		lblMonthlyPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthlyPayment.setForeground(Color.WHITE);
		lblMonthlyPayment.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblMonthlyPayment.setBounds(482, 146, 161, 14);
		makeLoansPanel.add(lblMonthlyPayment);
		
		JTextArea paymentLabel = new JTextArea();
		paymentLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		paymentLabel.setEditable(false);
		paymentLabel.setBounds(482, 171, 161, 23);
		makeLoansPanel.add(paymentLabel);
		
		JTextArea rateLabel = new JTextArea();
		rateLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		rateLabel.setEditable(false);
		rateLabel.setBounds(244, 236, 161, 23);
		makeLoansPanel.add(rateLabel);
		
		JLabel lblInterestRate = new JLabel("Interest Rate");
		lblInterestRate.setHorizontalAlignment(SwingConstants.CENTER);
		lblInterestRate.setForeground(Color.WHITE);
		lblInterestRate.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblInterestRate.setBounds(244, 211, 161, 14);
		makeLoansPanel.add(lblInterestRate);
		
		JButton btnAllowLoan = new JButton("Allow Loan");
		btnAllowLoan.setForeground(Color.WHITE);
		btnAllowLoan.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnAllowLoan.setBackground(new Color(36, 47, 65));
		btnAllowLoan.setBounds(244, 325, 161, 29);
		btnAllowLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(actionListener != null){
					actionListener.createLoan(clientIdLabel.getText(), amountLabel.getText(), paymentLabel.getText(), rateLabel.getText());
					clientIdLabel.setText(null);
					amountLabel.setText(null);
					paymentLabel.setText(null);
					rateLabel.setText(null);
				}
			}
		});
		makeLoansPanel.add(btnAllowLoan);
	}
	
	/*
	 * 
	 * CREATE closeOrOpenAccountsPanel
	 *
	 */
	private void closeOrOpenAccountsPanel(){
		closeOrOpenAccountsPanel = new JPanel();
		closeOrOpenAccountsPanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		closeOrOpenAccountsPanel.setBackground(new Color(97, 212, 195));
		contentPanel.add(closeOrOpenAccountsPanel, "name_991891601205140");
		closeOrOpenAccountsPanel.setLayout(null);
		
		/* ######## create account balance ####### */
		JLabel openOrCloseAccountsText = new JLabel("Open or Close Accounts");
		openOrCloseAccountsText.setHorizontalAlignment(SwingConstants.CENTER);
		openOrCloseAccountsText.setForeground(Color.WHITE);
		openOrCloseAccountsText.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		openOrCloseAccountsText.setBounds(227, 12, 212, 36);
		closeOrOpenAccountsPanel.add(openOrCloseAccountsText);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(10, 12, 633, 2);
		closeOrOpenAccountsPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(10, 48, 633, 2);
		closeOrOpenAccountsPanel.add(separator_1);
		
		JLabel balanceText = new JLabel("Create Account");
		balanceText.setHorizontalAlignment(SwingConstants.CENTER);
		balanceText.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		balanceText.setForeground(Color.WHITE);
		balanceText.setBounds(279, 85, 121, 14);
		closeOrOpenAccountsPanel.add(balanceText);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(39, 110, 577, 2);
		closeOrOpenAccountsPanel.add(separator_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setBounds(10, 541, 633, 2);
		closeOrOpenAccountsPanel.add(separator_4);
		
		JTextArea clientIDLabel = new JTextArea();
		clientIDLabel.setBounds(39, 161, 161, 23);
		closeOrOpenAccountsPanel.add(clientIDLabel);
		clientIDLabel.setEditable(false);
		clientIDLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		
		JLabel lblClientId = new JLabel("Client ID");
		lblClientId.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientId.setForeground(Color.WHITE);
		lblClientId.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblClientId.setBounds(39, 136, 155, 14);
		closeOrOpenAccountsPanel.add(lblClientId);
		
		JLabel lblNewAccountName = new JLabel("New Account Name");
		lblNewAccountName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewAccountName.setForeground(Color.WHITE);
		lblNewAccountName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblNewAccountName.setBounds(245, 136, 161, 14);
		closeOrOpenAccountsPanel.add(lblNewAccountName);
		
		JLabel lblNewAccountId = new JLabel("New Account Type");
		lblNewAccountId.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewAccountId.setForeground(Color.WHITE);
		lblNewAccountId.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblNewAccountId.setBounds(455, 136, 161, 14);
		closeOrOpenAccountsPanel.add(lblNewAccountId);
		
		JTextArea accountNameLabel = new JTextArea();
		accountNameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		accountNameLabel.setEditable(false);
		accountNameLabel.setBounds(245, 161, 161, 23);
		closeOrOpenAccountsPanel.add(accountNameLabel);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label.setBounds(39, 204, 155, 14);
		closeOrOpenAccountsPanel.add(label);
		
		JLabel label_1 = new JLabel("Client ID");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_1.setBounds(39, 391, 155, 14);
		closeOrOpenAccountsPanel.add(label_1);
		
		JTextArea closeAclientIDLabel = new JTextArea();
		closeAclientIDLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		closeAclientIDLabel.setEditable(false);
		closeAclientIDLabel.setBounds(39, 416, 161, 23);
		closeOrOpenAccountsPanel.add(closeAclientIDLabel);
		
		JTextArea accountNumberLabel = new JTextArea();
		accountNumberLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		accountNumberLabel.setEditable(false);
		accountNumberLabel.setBounds(455, 416, 161, 23);
		closeOrOpenAccountsPanel.add(accountNumberLabel);
		
		JLabel lblAccountId = new JLabel("Account Number");
		lblAccountId.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountId.setForeground(Color.WHITE);
		lblAccountId.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAccountId.setBounds(455, 391, 161, 14);
		closeOrOpenAccountsPanel.add(lblAccountId);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(39, 365, 577, 2);
		closeOrOpenAccountsPanel.add(separator_3);
		
		JLabel lblCloseAccount = new JLabel("Close Account");
		lblCloseAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCloseAccount.setForeground(Color.WHITE);
		lblCloseAccount.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblCloseAccount.setBounds(279, 340, 121, 14);
		closeOrOpenAccountsPanel.add(lblCloseAccount);
		
		JButton btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.setForeground(Color.WHITE);
		btnCloseAccount.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnCloseAccount.setBackground(new Color(36, 47, 65));
		btnCloseAccount.setBounds(240, 487, 166, 29);
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(actionListener != null){
					actionListener.closeAccount(closeAclientIDLabel.getText(), accountNumberLabel.getText());
					clientIDLabel.setText(null);
					accountNumberLabel.setText(null);
				}
			}
		});
		closeOrOpenAccountsPanel.add(btnCloseAccount);
		
		JComboBox<String> typeAccountCB = new JComboBox<String>();
		typeAccountCB.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		typeAccountCB.setBounds(455, 159, 161, 28);
		closeOrOpenAccountsPanel.add(typeAccountCB);
		
		JButton createAccountbtn = new JButton("Create Account");
		createAccountbtn.setForeground(Color.WHITE);
		createAccountbtn.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		createAccountbtn.setBackground(new Color(36, 47, 65));
		createAccountbtn.setBounds(240, 251, 166, 29);
		createAccountbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(actionListener != null){
					actionListener.createAccount(clientIDLabel.getText(), accountNameLabel.getText(), typeAccountCB.getSelectedItem().toString());
					clientIDLabel.setText(null);
					accountNameLabel.setText(null);
				}
			}
		});
		closeOrOpenAccountsPanel.add(createAccountbtn);
		/* ######################################### */
		
	}
	
	/*
	 * 
	 * CREATE MENU PANEL
	 * 
	 */
	
	private void createMenuPanel() {
		menuPanel = new JPanel();
		menuPanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		menuPanel.setBackground(new Color(36, 47, 65));
		menuPanel.setBounds(0, 0, 194, 565);
		menuPanel.setLayout(null);
		
		createMenuButtons();
		createMenuTextLabels();
	}
	
	private void createMenuTextLabels() {
		JLabel AdminNameTextLbl = new JLabel("Admin Name:");
		AdminNameTextLbl.setForeground(Color.WHITE);
		AdminNameTextLbl.setHorizontalAlignment(SwingConstants.CENTER);
		AdminNameTextLbl.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		AdminNameTextLbl.setBounds(0, 360, 194, 14);
		menuPanel.add(AdminNameTextLbl);
		
		JLabel iselTagLabel = new JLabel("clientBanck @ISEL");
		iselTagLabel.setForeground(Color.WHITE);
		iselTagLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iselTagLabel.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		iselTagLabel.setBounds(0, 545, 194, 14);
		menuPanel.add(iselTagLabel);
		
		userNameLabel = new JLabel("");
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		userNameLabel.setBounds(0, 385, 194, 34);
		menuPanel.add(userNameLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 540, 174, 2);
		menuPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 349, 174, 2);
		menuPanel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 444, 174, 2);
		menuPanel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 11, 174, 2);
		menuPanel.add(separator_3);
		
		JLabel lblOptions = new JLabel("OPTIONS");
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setForeground(Color.WHITE);
		lblOptions.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblOptions.setBounds(0, 24, 194, 14);
		menuPanel.add(lblOptions);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 49, 174, 2);
		menuPanel.add(separator_4);
	
	}

	private void createMenuButtons(){
		JButton closeBtn = new JButton("Close");
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		closeBtn.setBackground(new Color(36, 47, 65));
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(communActionListener != null){
					communActionListener.onCloseApp();
				}
			}
		});
		closeBtn.setBounds(0, 493, 194, 36);
		menuPanel.add(closeBtn);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setForeground(Color.WHITE);
		logoutBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		logoutBtn.setBackground(new Color(36, 47, 65));
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(communActionListener != null){
					communActionListener.onLogoutOrCloseApp();
					
				}
			}
		});
		logoutBtn.setBounds(0, 457, 194, 36);
		menuPanel.add(logoutBtn);
		
		JButton openAccounts = new JButton("Open/Close Accounts");
		openAccounts.setForeground(Color.WHITE);
		openAccounts.setBackground(new Color(36, 47, 65));
		openAccounts.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		openAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				callNewWindow(closeOrOpenAccountsPanel);
			}
		});
		openAccounts.setBounds(0, 62, 194, 46);
		menuPanel.add(openAccounts);
		
		JButton loansBtn = new JButton("Make Loans");
		loansBtn.setForeground(Color.WHITE);
		loansBtn.setBackground(new Color(36, 47, 65));
		loansBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		loansBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				callNewWindow(makeLoansPanel);
			}
		});
		loansBtn.setBounds(0, 108, 194, 46);
		menuPanel.add(loansBtn);
	}
	
	private void callNewWindow(JPanel panelToShow) {
		//remove all panels
		contentPanel.removeAll();
		contentPanel.repaint();
		contentPanel.revalidate();
		
		//add panel
		contentPanel.add(panelToShow);
		contentPanel.repaint();
		contentPanel.revalidate();
	}
	
	public void setOnManagerEventListener(OnManagerEventListener listener){
		actionListener = listener;
	}
	
	public void setOnCommunEventListener(OnCommunEventListener listener){
		communActionListener = listener;
	}
}
