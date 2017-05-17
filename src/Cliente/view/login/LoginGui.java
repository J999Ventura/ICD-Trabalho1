package Cliente.view.login;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class LoginGui extends JFrame{
	
	private OnLoginEventListener actionListener;

	private JPanel panel;
	private JTextField usernameText;
	private JPasswordField passwordField;
	private JButton loginBtn;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblNewLabel;
	private JLabel lblNeverLoseTrack;
	private JLabel lblNewLabel_1;
	private JPanel panel_IselText;
	private JButton closeBtn;
	private JLabel lblTrackOf;

	public LoginGui() {
		setBackground(new Color(51, 51, 102));	
		createGui();	
	}

	private void createGui() {
		createPanel();
		createTextLabels();
		createInputTextFields();
		createButtons();
		createSeparators();
	}
	
	private void createSeparators() {
		separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(193, 90, 215, 1);
		panel.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(193, 158, 215, 1);
		panel.add(separator_1);
	}

	private void createPanel() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 295);
		setUndecorated(true);
		//setResizable(false); //doesn't allow the maximize or minimize option on the window
		
		panel = new JPanel();
		panel.setBackground(new Color(36, 47, 65));
		panel.setForeground(new Color(51, 51, 102));
		panel.setBorder(null);
		setContentPane(panel);
		panel.setLayout(null);
		
		
		panel_IselText = new JPanel();
		panel_IselText.setBorder(null);
		panel_IselText.setBackground(new Color(97, 212, 195));
		panel_IselText.setBounds(0, 0, 156, 295);
		panel.add(panel_IselText);
		panel_IselText.setLayout(null);
	}

	private void createButtons() {
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		loginBtn.setIcon(null);
		loginBtn.setBackground(new Color(97, 212, 195));
		loginBtn.setForeground(Color.WHITE);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(actionListener != null){
					actionListener.onActionLogin(usernameText.getText(), passwordField.getText());
					usernameText.setText(null);
					passwordField.setText(null);
				}
			}
		});
		loginBtn.setBounds(213, 185, 177, 32);
		panel.add(loginBtn);
		
		closeBtn = new JButton("Close");
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		closeBtn.setBackground(new Color(36, 47, 65));
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		closeBtn.setBounds(213, 228, 177, 32);
		panel.add(closeBtn);
	}

	private void createInputTextFields() {
		usernameText = new JTextField();
		usernameText.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		usernameText.setForeground(Color.WHITE);
		usernameText.setBackground(new Color(36, 47, 65));
		usernameText.setBounds(193, 59, 215, 25);
		panel.add(usernameText);
		usernameText.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(new Color(36, 47, 65));
		passwordField.setBounds(193, 127, 215, 25);
		panel.add(passwordField);
	}

	private void createTextLabels() {		
		lblNewLabel = new JLabel("ISEL Bank");
		lblNewLabel.setBounds(10, 39, 134, 20);
		panel_IselText.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.WHITE);
		
		lblNeverLoseTrack = new JLabel("NEVER");
		lblNeverLoseTrack.setBounds(10, 173, 124, 37);
		panel_IselText.add(lblNeverLoseTrack);
		lblNeverLoseTrack.setHorizontalAlignment(SwingConstants.LEFT);
		lblNeverLoseTrack.setForeground(Color.WHITE);
		lblNeverLoseTrack.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		
		lblNewLabel_1 = new JLabel("OF YOUR MONEY!");
		lblNewLabel_1.setBounds(10, 230, 124, 65);
		panel_IselText.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		
		lblTrackOf = new JLabel("LOSE TRACK");
		lblTrackOf.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrackOf.setForeground(Color.WHITE);
		lblTrackOf.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblTrackOf.setBounds(10, 207, 124, 37);
		panel_IselText.add(lblTrackOf);
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setBackground(Color.WHITE);
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		usernameLabel.setBounds(193, 34, 68, 14);
		panel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		passwordLabel.setBounds(193, 102, 68, 14);
		panel.add(passwordLabel);
	}
	
	public void setOnLoginEventListener(OnLoginEventListener listener){
		actionListener = listener;
	}
}
