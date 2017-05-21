package Cliente.view.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;

import Cliente.OnCommunEventListener;
import commun.Cliente;
import commun.Conta;
import commun.Emprestimo;
import commun.Movimento;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ClientGui extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel BackgroundPanel;
	private OnClientEventListener clientActionListener;
	private OnCommunEventListener communActionListener;
	private JPanel contentPanel;
	private JPanel resumePanel;
	private JPanel balancePanel;
	private JPanel accountPanel;
	private JPanel movementsPanel;
	private JPanel transactionsPanel;
	private JPanel loansPanel;
	private JTextArea listResumeAcountBalance;
	private JTextArea listResumeMovementsAccount;
	private JTextArea listResumeLoans;
	private JTextArea listResumeAccounts;
	private JTextArea listBalanceAcountBalance;
	private JTextArea listMovementsAccount;
	private JTextArea listLoans;
	private JComboBox<String> loansaccountComboBox;
	private JComboBox<String> transferComboBox;
	private JComboBox<String> mvAccountListComboBox;
	private JComboBox<String> accountComboBox;
	private int accountListSize;
	private JLabel userNameLabel;
	private JPanel menuPanel;
	private JTextField newNameField;
	private JTextField amountToTransferLabel;
	private JTextField targetAccountTransferLabel;
	private JLabel aNameLabel;
	private JLabel aAgeLabel;
	private JLabel aBirthdayLabel;
	private JLabel aMoradaLabel;
	private JLabel aAccountNumberLabel;
	private JLabel accountNameLabel;
	private JLabel nibLabel;
	private JLabel ibanLabel;
	
	public ClientGui(Cliente user) {
		createMenuPanel();
		createGui();
		initUserFillds(user);
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
		
		createResumePanel();
		createBalancePanel();
		createAccountPanel();
		createAccountMovementsPanel();
		createTransfersPanel();
		createLoansPanel();
	}
		
	/*
	 * 
	 * CREATE ACCOUNT LOANS
	 * 
	 */
	private void createLoansPanel() {
		loansPanel = new JPanel();
		loansPanel.setBackground(new Color(97, 212, 195));
		loansPanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		contentPanel.add(loansPanel, "name_991976253863021");
		loansPanel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(148, 154, 350, 2);
		loansPanel.add(separator);
		
		JLabel label = new JLabel("Account");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		label.setBounds(261, 128, 121, 14);
		loansPanel.add(label);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(10, 49, 633, 2);
		loansPanel.add(separator_1);
		
		JLabel lblAccountLoans_1 = new JLabel("Account Loans");
		lblAccountLoans_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountLoans_1.setForeground(Color.WHITE);
		lblAccountLoans_1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblAccountLoans_1.setBounds(244, 11, 191, 36);
		loansPanel.add(lblAccountLoans_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(10, 11, 633, 2);
		loansPanel.add(separator_2);
		
		loansaccountComboBox = new JComboBox<String>();
		loansaccountComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		loansaccountComboBox.setBounds(148, 173, 350, 28);
		loansaccountComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectAccountFromComboBox(loansaccountComboBox);
				fillLoans(listLoans);
			}
		});
		loansPanel.add(loansaccountComboBox);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setBounds(10, 541, 633, 2);
		loansPanel.add(separator_4);
		
		JLabel lblLoans = new JLabel("Loans");
		lblLoans.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoans.setForeground(Color.WHITE);
		lblLoans.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblLoans.setBounds(259, 261, 121, 14);
		loansPanel.add(lblLoans);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(146, 287, 350, 2);
		loansPanel.add(separator_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.WHITE));
		scrollPane.setBounds(20, 320, 611, 180);
		loansPanel.add(scrollPane);
		
		listLoans = new JTextArea();
		listLoans.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		listLoans.setEditable(false);
		scrollPane.setViewportView(listLoans);
	}
	
	/*
	 * 
	 * CREATE ACCOUNT TRANSFERS
	 * 
	 */
	private void createTransfersPanel() {
		transactionsPanel = new JPanel();
		transactionsPanel.setBackground(new Color(97, 212, 195));
		transactionsPanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		contentPanel.add(transactionsPanel, "name_991974265159287");
		transactionsPanel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(148, 154, 350, 2);
		transactionsPanel.add(separator);
		
		JLabel label = new JLabel("Account");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		label.setBounds(261, 128, 121, 14);
		transactionsPanel.add(label);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(10, 49, 633, 2);
		transactionsPanel.add(separator_1);
		
		JLabel lblAccountTransfers = new JLabel("Account Transfers");
		lblAccountTransfers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountTransfers.setForeground(Color.WHITE);
		lblAccountTransfers.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblAccountTransfers.setBounds(244, 11, 191, 36);
		transactionsPanel.add(lblAccountTransfers);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(10, 11, 633, 2);
		transactionsPanel.add(separator_2);
		
		transferComboBox = new JComboBox<String>();
		transferComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		transferComboBox.setBounds(148, 173, 350, 28);
		transferComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectAccountFromComboBox(transferComboBox);
			}
		});
		transactionsPanel.add(transferComboBox);
		
		JLabel lblMoney = new JLabel("Amount");
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblMoney.setBounds(45, 337, 166, 29);
		transactionsPanel.add(lblMoney);
		
		amountToTransferLabel = new JTextField();
		amountToTransferLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		amountToTransferLabel.setColumns(10);
		amountToTransferLabel.setBounds(45, 377, 166, 29);
		transactionsPanel.add(amountToTransferLabel);
		
		targetAccountTransferLabel = new JTextField();
		targetAccountTransferLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		targetAccountTransferLabel.setColumns(10);
		targetAccountTransferLabel.setBounds(291, 377, 314, 29);
		transactionsPanel.add(targetAccountTransferLabel);
		
		JLabel lblTargetAccountId = new JLabel("Target Account ID");
		lblTargetAccountId.setHorizontalAlignment(SwingConstants.CENTER);
		lblTargetAccountId.setForeground(Color.WHITE);
		lblTargetAccountId.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblTargetAccountId.setBounds(374, 337, 166, 29);
		transactionsPanel.add(lblTargetAccountId);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(148, 307, 350, 2);
		transactionsPanel.add(separator_3);
		
		JLabel lblTransfer = new JLabel("Transfer Settings");
		lblTransfer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransfer.setForeground(Color.WHITE);
		lblTransfer.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblTransfer.setBounds(243, 273, 166, 29);
		transactionsPanel.add(lblTransfer);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setForeground(Color.WHITE);
		btnTransfer.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnTransfer.setBackground(new Color(36, 47, 65));
		btnTransfer.setBounds(243, 434, 166, 29);
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(clientActionListener != null){
					clientActionListener.onAmountTransfer(amountToTransferLabel.getText(),targetAccountTransferLabel.getText());
					amountToTransferLabel.setText(null);
					targetAccountTransferLabel.setText(null);
				}
			}
		});
		transactionsPanel.add(btnTransfer);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setBounds(10, 541, 633, 2);
		transactionsPanel.add(separator_4);
	}
	
	/*
	 * 
	 * CREATE ACCOUNT MOVEMENTS
	 * 
	 */
	private void createAccountMovementsPanel(){
		movementsPanel = new JPanel();
		movementsPanel.setBackground(new Color(97, 212, 195));
		movementsPanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		contentPanel.add(movementsPanel, "name_991971766444757");
		movementsPanel.setLayout(null);
		
		JLabel lblAccountMovements_1 = new JLabel("Account Movements");
		lblAccountMovements_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountMovements_1.setForeground(Color.WHITE);
		lblAccountMovements_1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblAccountMovements_1.setBounds(244, 11, 191, 36);
		movementsPanel.add(lblAccountMovements_1);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.WHITE);
		separator_5.setBounds(10, 11, 633, 2);
		movementsPanel.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setBounds(10, 49, 633, 2);
		movementsPanel.add(separator_6);
		
		JLabel lblMovements = new JLabel("Movements");
		lblMovements.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovements.setForeground(Color.WHITE);
		lblMovements.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblMovements.setBounds(260, 246, 121, 14);
		movementsPanel.add(lblMovements);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.WHITE);
		separator_7.setBounds(147, 272, 350, 2);
		movementsPanel.add(separator_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new LineBorder(Color.WHITE));
		scrollPane_1.setBounds(21, 305, 611, 180);
		movementsPanel.add(scrollPane_1);
		
		listMovementsAccount = new JTextArea();
		listMovementsAccount.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		listMovementsAccount.setEditable(false);
		scrollPane_1.setViewportView(listMovementsAccount);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(Color.WHITE);
		separator_8.setBounds(10, 541, 633, 2);
		movementsPanel.add(separator_8);
		
		mvAccountListComboBox = new JComboBox<String>();
		mvAccountListComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mvAccountListComboBox.setBounds(148, 173, 350, 28);
		mvAccountListComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectAccountFromComboBox(mvAccountListComboBox);
				fillMovements(listMovementsAccount);
			}
		});
		movementsPanel.add(mvAccountListComboBox);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setForeground(Color.WHITE);
		separator_9.setBounds(148, 154, 350, 2);
		movementsPanel.add(separator_9);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccount.setForeground(Color.WHITE);
		lblAccount.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblAccount.setBounds(261, 128, 121, 14);
		movementsPanel.add(lblAccount);
	}
	
	/*
	 * 
	 * CREATE ACCOUNT PANEL
	 * 
	 */
	private void createAccountPanel() {
		accountPanel = new JPanel();
		accountPanel.setBackground(new Color(97, 212, 195));
		accountPanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		contentPanel.add(accountPanel, "name_991945231731940");
		accountPanel.setLayout(null);
		
		JLabel lblAccounts_1 = new JLabel("Accounts");
		lblAccounts_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccounts_1.setForeground(Color.WHITE);
		lblAccounts_1.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblAccounts_1.setBounds(257, 11, 152, 36);
		accountPanel.add(lblAccounts_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(10, 11, 633, 2);
		accountPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(10, 49, 633, 2);
		accountPanel.add(separator_1);
		
		JLabel lblYourAccounts = new JLabel("Your Accounts");
		lblYourAccounts.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourAccounts.setForeground(Color.WHITE);
		lblYourAccounts.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblYourAccounts.setBounds(260, 62, 121, 14);
		accountPanel.add(lblYourAccounts);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(147, 88, 350, 2);
		accountPanel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(10, 541, 633, 2);
		accountPanel.add(separator_3);
		
		JLabel lblNewName = new JLabel("New User Name");
		lblNewName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewName.setForeground(Color.WHITE);
		lblNewName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblNewName.setBounds(46, 487, 166, 29);
		accountPanel.add(lblNewName);
		
		newNameField = new JTextField();
		newNameField.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		newNameField.setColumns(10);
		newNameField.setBounds(222, 487, 166, 29);
		accountPanel.add(newNameField);
		
		JButton changeSettingsBtn = new JButton("Change User Name");
		changeSettingsBtn.setBackground(new Color(36, 47, 65));
		changeSettingsBtn.setForeground(Color.WHITE);
		changeSettingsBtn.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		changeSettingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(clientActionListener != null){
					clientActionListener.onNewUserChange(newNameField.getText());
					userNameLabel.setText(newNameField.getText());
					newNameField.setText(null);
				}
			}
		});
		changeSettingsBtn.setBounds(398, 487, 166, 29);
		accountPanel.add(changeSettingsBtn);

		accountComboBox = new JComboBox<String>();
		accountComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		accountComboBox.setBounds(147, 101, 350, 28);
		accountComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(clientActionListener != null){	
					selectAccountFromComboBox(accountComboBox);
					refreshAccountSettings();
				}
			}
		});
		accountPanel.add(accountComboBox);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblName.setBounds(10, 140, 166, 29);
		accountPanel.add(lblName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAge.setBounds(257, 140, 166, 29);
		accountPanel.add(lblAge);
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirthday.setForeground(Color.WHITE);
		lblBirthday.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblBirthday.setBounds(477, 140, 166, 29);
		accountPanel.add(lblBirthday);
		
		aNameLabel = new JLabel("aNameLabel");
		aNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aNameLabel.setForeground(Color.WHITE);
		aNameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		aNameLabel.setBounds(10, 180, 166, 29);
		accountPanel.add(aNameLabel);
		
		aAgeLabel = new JLabel("aAgeLabel");
		aAgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aAgeLabel.setForeground(Color.WHITE);
		aAgeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		aAgeLabel.setBounds(257, 180, 166, 29);
		accountPanel.add(aAgeLabel);
		
		aBirthdayLabel = new JLabel("aBirthdayLabel");
		aBirthdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aBirthdayLabel.setForeground(Color.WHITE);
		aBirthdayLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		aBirthdayLabel.setBounds(477, 180, 166, 29);
		accountPanel.add(aBirthdayLabel);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.WHITE);
		separator_5.setBounds(10, 167, 166, 2);
		accountPanel.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setBounds(257, 167, 166, 2);
		accountPanel.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.WHITE);
		separator_7.setBounds(477, 167, 166, 2);
		accountPanel.add(separator_7);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setForeground(Color.WHITE);
		separator_9.setBounds(243, 249, 166, 2);
		accountPanel.add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setForeground(Color.WHITE);
		separator_10.setBounds(83, 329, 166, 2);
		accountPanel.add(separator_10);
		
		JLabel lblMorada = new JLabel("Morada");
		lblMorada.setHorizontalAlignment(SwingConstants.CENTER);
		lblMorada.setForeground(Color.WHITE);
		lblMorada.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblMorada.setBounds(243, 222, 166, 29);
		accountPanel.add(lblMorada);
		
		aMoradaLabel = new JLabel("aMoradaLabel");
		aMoradaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aMoradaLabel.setForeground(Color.WHITE);
		aMoradaLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		aMoradaLabel.setBounds(10, 262, 633, 29);
		accountPanel.add(aMoradaLabel);
		
		aAccountNumberLabel = new JLabel("aAccountNumberLabel");
		aAccountNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aAccountNumberLabel.setForeground(Color.WHITE);
		aAccountNumberLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		aAccountNumberLabel.setBounds(10, 337, 306, 29);
		accountPanel.add(aAccountNumberLabel);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountNumber.setForeground(Color.WHITE);
		lblAccountNumber.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAccountNumber.setBounds(83, 302, 166, 29);
		accountPanel.add(lblAccountNumber);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setForeground(Color.WHITE);
		separator_11.setBounds(396, 329, 166, 2);
		accountPanel.add(separator_11);
		
		accountNameLabel = new JLabel("name");
		accountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountNameLabel.setForeground(Color.WHITE);
		accountNameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		accountNameLabel.setBounds(337, 337, 306, 29);
		accountPanel.add(accountNameLabel);
		
		JLabel lblAccountName = new JLabel("Account Name");
		lblAccountName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountName.setForeground(Color.WHITE);
		lblAccountName.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAccountName.setBounds(396, 302, 166, 29);
		accountPanel.add(lblAccountName);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setForeground(Color.WHITE);
		separator_13.setBounds(83, 404, 166, 2);
		accountPanel.add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setForeground(Color.WHITE);
		separator_14.setBounds(396, 404, 166, 2);
		accountPanel.add(separator_14);
		
		JLabel lblAccountIban = new JLabel("Account IBAN");
		lblAccountIban.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountIban.setForeground(Color.WHITE);
		lblAccountIban.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAccountIban.setBounds(396, 377, 166, 29);
		accountPanel.add(lblAccountIban);
		
		ibanLabel = new JLabel("iban");
		ibanLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ibanLabel.setForeground(Color.WHITE);
		ibanLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		ibanLabel.setBounds(337, 412, 306, 29);
		accountPanel.add(ibanLabel);
		
		nibLabel = new JLabel("nib");
		nibLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nibLabel.setForeground(Color.WHITE);
		nibLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		nibLabel.setBounds(10, 412, 306, 29);
		accountPanel.add(nibLabel);
		
		JLabel lblAccountNib = new JLabel("Account NIB");
		lblAccountNib.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountNib.setForeground(Color.WHITE);
		lblAccountNib.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAccountNib.setBounds(83, 377, 166, 29);
		accountPanel.add(lblAccountNib);
	}
	
	/*
	 * 
	 * CREATE BALANCE PANEL
	 * 
	 */
	private void createBalancePanel(){
		balancePanel = new JPanel();
		balancePanel.setBackground(new Color(97, 212, 195));
		balancePanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		contentPanel.add(balancePanel, "name_991931529768702");
		balancePanel.setLayout(null);
		
		JLabel lblAccountBalance = new JLabel("Account Balance");
		lblAccountBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountBalance.setForeground(Color.WHITE);
		lblAccountBalance.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblAccountBalance.setBounds(265, 11, 152, 36);
		balancePanel.add(lblAccountBalance);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(10, 11, 633, 2);
		balancePanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(10, 49, 633, 2);
		balancePanel.add(separator_1);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setForeground(Color.WHITE);
		lblBalance.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblBalance.setBounds(261, 195, 121, 14);
		balancePanel.add(lblBalance);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(148, 221, 350, 2);
		balancePanel.add(separator_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.WHITE));
		scrollPane.setBounds(147, 242, 351, 124);
		balancePanel.add(scrollPane);
		
		listBalanceAcountBalance = new JTextArea();
		listBalanceAcountBalance.setEditable(false);
		listBalanceAcountBalance.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		scrollPane.setViewportView(listBalanceAcountBalance);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(10, 541, 633, 2);
		balancePanel.add(separator_3);
	}
	
	/*
	 * 
	 * CREATE RESUME PANEL
	 *
	 */
	private void createResumePanel(){
		resumePanel = new JPanel();
		resumePanel.setBorder(new LineBorder(new Color(36, 47, 65), 2));
		resumePanel.setBackground(new Color(97, 212, 195));
		contentPanel.add(resumePanel, "name_991891601205140");
		resumePanel.setLayout(null);
		
		/* ######## create account balance ####### */
		JLabel acountResumeText = new JLabel("Account Resume");
		acountResumeText.setHorizontalAlignment(SwingConstants.CENTER);
		acountResumeText.setForeground(Color.WHITE);
		acountResumeText.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		acountResumeText.setBounds(289, 12, 135, 36);
		resumePanel.add(acountResumeText);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(10, 12, 633, 2);
		resumePanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(10, 48, 633, 2);
		resumePanel.add(separator_1);
		
		JLabel balanceText = new JLabel("Account Balance");
		balanceText.setHorizontalAlignment(SwingConstants.CENTER);
		balanceText.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		balanceText.setForeground(Color.WHITE);
		balanceText.setBounds(105, 87, 121, 14);
		resumePanel.add(balanceText);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(39, 110, 249, 2);
		resumePanel.add(separator_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.WHITE));
		scrollPane.setBounds(39, 123, 249, 87);
		resumePanel.add(scrollPane);
		
		listResumeAcountBalance = new JTextArea();
		listResumeAcountBalance.setEditable(false);
		listResumeAcountBalance.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		scrollPane.setViewportView(listResumeAcountBalance);
		/* ######################################### */
		
		/* ######## create account movements ####### */
		JLabel lblAccountMovements = new JLabel("Account Movements");
		lblAccountMovements.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountMovements.setForeground(Color.WHITE);
		lblAccountMovements.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAccountMovements.setBounds(245, 239, 146, 14);
		resumePanel.add(lblAccountMovements);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.WHITE);
		separator_3.setBounds(40, 264, 576, 2);
		resumePanel.add(separator_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new LineBorder(Color.WHITE));
		scrollPane_1.setBounds(39, 277, 577, 102);
		resumePanel.add(scrollPane_1);
		
		listResumeMovementsAccount = new JTextArea();
		listResumeMovementsAccount.setEditable(false);
		listResumeMovementsAccount.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		scrollPane_1.setViewportView(listResumeMovementsAccount);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setBounds(10, 541, 633, 2);
		resumePanel.add(separator_4);
		/* ######################################### */
		
		/* ######## create account loans ####### */
		JLabel lblAccountLoans = new JLabel("Account Loans");
		lblAccountLoans.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountLoans.setForeground(Color.WHITE);
		lblAccountLoans.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAccountLoans.setBounds(245, 419, 146, 14);
		resumePanel.add(lblAccountLoans);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.WHITE);
		separator_5.setBounds(40, 444, 576, 2);
		resumePanel.add(separator_5);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new LineBorder(Color.WHITE));
		scrollPane_2.setBounds(39, 457, 576, 73);
		resumePanel.add(scrollPane_2);
		
		listResumeLoans = new JTextArea();
		listResumeLoans.setEditable(false);
		listResumeLoans.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		scrollPane_2.setViewportView(listResumeLoans);
		
		/* ######################################### */
		
		/* ######## create accounts ####### */
		JLabel lblAccounts = new JLabel("Accounts");
		lblAccounts.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccounts.setForeground(Color.WHITE);
		lblAccounts.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAccounts.setBounds(433, 87, 121, 14);
		resumePanel.add(lblAccounts);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setBounds(367, 110, 249, 2);
		resumePanel.add(separator_6);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setViewportBorder(new LineBorder(Color.WHITE));
		scrollPane_3.setBounds(367, 123, 249, 87);
		resumePanel.add(scrollPane_3);
		
		listResumeAccounts = new JTextArea();
		listResumeAccounts.setEditable(false);
		listResumeAccounts.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		scrollPane_3.setViewportView(listResumeAccounts);
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
		JLabel UserNameTextLbl = new JLabel("User Name:");
		UserNameTextLbl.setForeground(Color.WHITE);
		UserNameTextLbl.setHorizontalAlignment(SwingConstants.CENTER);
		UserNameTextLbl.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		UserNameTextLbl.setBounds(0, 360, 194, 14);
		menuPanel.add(UserNameTextLbl);
		
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
		
		JButton resumeBtn = new JButton("Resume");
		resumeBtn.setForeground(Color.WHITE);
		resumeBtn.setBackground(new Color(36, 47, 65));
		resumeBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		resumeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillResumePanelAttributes();
				callNewWindow(resumePanel);
			}
		});
		resumeBtn.setBounds(0, 62, 194, 46);
		menuPanel.add(resumeBtn);
		
		JButton saleBtn = new JButton("Balance");
		saleBtn.setForeground(Color.WHITE);
		saleBtn.setBackground(new Color(36, 47, 65));
		saleBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		saleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillBalancePanelAttributes();
				callNewWindow(balancePanel);
			}
		});
		saleBtn.setBounds(0, 108, 194, 46);
		menuPanel.add(saleBtn);
		
		JButton billsBtn = new JButton("Accounts");
		billsBtn.setForeground(Color.WHITE);
		billsBtn.setBackground(new Color(36, 47, 65));
		billsBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		billsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillAccountsPanelAttributes();
				callNewWindow(accountPanel);
			}
		});
		billsBtn.setBounds(0, 154, 194, 46);
		menuPanel.add(billsBtn);
		
		JButton movementsBtn = new JButton("Movements");
		movementsBtn.setForeground(Color.WHITE);
		movementsBtn.setBackground(new Color(36, 47, 65));
		movementsBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		movementsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillMovementsPanelAttributes();
				callNewWindow(movementsPanel);
			}
		});
		movementsBtn.setBounds(0, 200, 194, 46);
		menuPanel.add(movementsBtn);
		
		JButton transactionsBtn = new JButton("Transfers");
		transactionsBtn.setForeground(Color.WHITE);
		transactionsBtn.setBackground(new Color(36, 47, 65));
		transactionsBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		transactionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillTransfersPanelAttributes();
				callNewWindow(transactionsPanel);
			}
		});
		transactionsBtn.setBounds(0, 246, 194, 46);
		menuPanel.add(transactionsBtn);
		
		JButton loansBtn = new JButton("Loans");
		loansBtn.setForeground(Color.WHITE);
		loansBtn.setBackground(new Color(36, 47, 65));
		loansBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		loansBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillLoansPanelAttributes();
				callNewWindow(loansPanel);
			}
		});
		loansBtn.setBounds(0, 292, 194, 46);
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
	
	public void setOnClientEventListener(OnClientEventListener listener){
		clientActionListener = listener;
		fillResumePanelAttributes(); //coloquei aqui porque para preencher a primeira vez j� � necessario haver um listener; assim preenche o resume board quando se abre a aplicacao do cliente;
		fillComboBoxes();//preenche as combobox todas ao inicio;
	}
	
	public void setOnCommunEventListener(OnCommunEventListener listener){
		communActionListener = listener;
	}
	
	/* ****** preenche as caixas de texto todas ******* */
	private void fillComboBoxes() {
		fillAccountComboBox(loansaccountComboBox);
		fillAccountComboBox(transferComboBox);
		fillAccountComboBox(mvAccountListComboBox);
		fillAccountComboBox(accountComboBox);
	}
	
	private void fillBalance(JTextArea area){
		if(clientActionListener != null){
			ArrayList<String> accountBalanceList = clientActionListener.onGetAccountBalance();
			area.setText(null);
			for(String balance : accountBalanceList){
				area.append(balance + "\n");
			}
		}
	}
	
	private void fillAccounts(JTextArea area){
		if(clientActionListener != null){
			ArrayList<Conta> accountList = clientActionListener.onGetAccounts();
			area.setText(null);
			for(Conta account : accountList){
				area.append(account.getNomeConta() + "\n");
			}
		}
	}
	
	private void fillLoans(JTextArea area){
		if(clientActionListener != null){
			ArrayList<Emprestimo> accountLoansList = clientActionListener.onGetAccountLoans();
			area.setText(null);
			for(Emprestimo loan : accountLoansList){
				area.append(loan.toString() + "\n");
			}
		}
	}
	
	private void fillMovements(JTextArea area){
		if(clientActionListener != null){
			ArrayList<Movimento> accountMovementList = clientActionListener.onGetAccountMovements();
			area.setText(null);
			for(Movimento mov : accountMovementList){
				area.append(mov.toString() + "\n");
			}
		}
	}
	
	private void fillAccountComboBox(JComboBox<String> box){
		if(clientActionListener != null){
			ArrayList<Conta> accountList = clientActionListener.onGetAccounts();
			box.removeAllItems();
			if(accountList != null){
				for(Conta account : accountList){
					box.addItem(account.getNomeConta());
				}
			}
			accountListSize = accountList.size();
		}
	}
	
	private void fillResumePanelAttributes() {
			fillBalance(listResumeAcountBalance);
			fillAllAccountsMovements(listResumeMovementsAccount);			
			fillLoans(listResumeLoans);
			fillAccounts(listResumeAccounts);	
	}

	private void fillAllAccountsMovements(JTextArea area) {
		if(clientActionListener != null){
			ArrayList<Movimento> accountMovementList = clientActionListener.onGetAllAccountsMovements();
			area.setText(null);
			for(Movimento mov : accountMovementList){
				area.append(mov.toString() + "\n");
			}
		}
	}

	private void fillBalancePanelAttributes() {
			fillBalance(listBalanceAcountBalance);		
	}
	
	private void fillAccountsPanelAttributes() {
			accountComboBox.setSelectedIndex(0);
			selectAccountFromComboBox(accountComboBox);
			refreshAccountSettings();
	}

	private void fillTransfersPanelAttributes() {
			transferComboBox.setSelectedIndex(0);
			selectAccountFromComboBox(transferComboBox);
	}
	
	private void fillMovementsPanelAttributes() {
			mvAccountListComboBox.setSelectedIndex(0);
			selectAccountFromComboBox(mvAccountListComboBox);
			fillMovements(listMovementsAccount);
	}
	
	private void fillLoansPanelAttributes() {
			loansaccountComboBox.setSelectedIndex(0);
			selectAccountFromComboBox(loansaccountComboBox);
			fillLoans(listLoans);
	}
	
	/*
	 * faz refresh aos atributos de uma conta. � utilizado quando se troca de conta.
	 */
	private void refreshAccountSettings() {
		if(clientActionListener != null){
			Conta conta = clientActionListener.onGetCurrentAccount();
			aAccountNumberLabel.setText(conta.getNumConta());
			accountNameLabel.setText(conta.getNomeConta());
			nibLabel.setText(""+conta.getNib());
			ibanLabel.setText(""+conta.getIban());
		}
	}
	
	/*
	 * quando selecionamos uma conta la lista, essa conta passa a ser a conta actual.
	 */
	private void selectAccountFromComboBox(JComboBox<String> comboBox) {
		if(clientActionListener != null){	
			if(comboBox.getItemCount() == accountListSize){
				clientActionListener.onSelectedAccount((String)comboBox.getSelectedItem());
			}
		}
	}
	
	/*
	 * quando se abre a aplicacao, este metodo preenche os atributos visiveis com as informa�oes do utilizador.
	 */
	private void initUserFillds(Cliente user) {
		userNameLabel.setText(user.getUserName());
		aNameLabel.setText(user.getNomeCliente());
		//aAgeLabel.setText(user.getAge());
		//aBirthdayLabel.setText(user.getBirthday());
		aMoradaLabel.setText(user.getMorada());
	}
	
	/* ************************************************ */
}
