package packageGUI;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import packageView.loginView;

/**
 * Classe JFrame loginPage
 */

public class loginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		/**
		 * @author Valentino Di Giosaffatte 232411 
		 * @author Riccardo Armando Di Prinzio 229032
		 * @author Flavio Furia 229034
		 */
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					loginPage frame = new loginPage();
					frame.setVisible(true);
					frame.setResizable(false);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginPage() {
		super("Library");
		loginPage finestra = this; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 295, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLogin = new JLabel("LOGIN");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("E-MAIL");
		
		JLabel lblPassword = new JLabel("PASSWORD");
		
		JButton btnNewButton = new JButton("ACCEDI");
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Accedi" 
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				new loginView().accedi(textField.getText(), passwordField.getText(), finestra); 
				
			}
		});
		
		JButton btnAccediComeOspite = new JButton("ACCEDI COME OSPITE");
		btnAccediComeOspite.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Accedi come ospite" 
			 */
			public void actionPerformed(ActionEvent e) {
				
				new loginView().accediComeOspite(finestra);
				
			}
		});
		
		JButton btnRegistrati = new JButton("REGISTRATI");
		
		btnRegistrati.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Registrati" 
			 */
			public void actionPerformed(ActionEvent arg0) {
				
				new loginView().registrati(finestra);
				
			}
		});
		
		passwordField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(115)
							.addComponent(lblLogin))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnRegistrati, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addComponent(lblNewLabel)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField)
								.addComponent(btnAccediComeOspite, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(98)
							.addComponent(btnNewButton)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblLogin)
					.addGap(20)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(35)
					.addComponent(btnAccediComeOspite)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRegistrati)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
