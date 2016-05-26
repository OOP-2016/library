package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import packageView.registrazioneView;
public class loginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage frame = new loginPage();
					frame.setVisible(true);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 295, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLogin = new JLabel("LOGIN");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("E-mail");
		
		JLabel lblPassword = new JLabel("Password");
		
		JButton btnNewButton = new JButton("ACCEDI");
		
		JButton btnAccediComeOspite = new JButton("ACCEDI COME OSPITE");
		
		JButton btnRegistrati = new JButton("REGISTRATI");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new registrazioneView().istanziaController();
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
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addComponent(lblNewLabel)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(99)
							.addComponent(btnNewButton)))
					.addContainerGap(33, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(53, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegistrati, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAccediComeOspite, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
					.addGap(51))
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
					.addGap(36)
					.addComponent(btnAccediComeOspite)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRegistrati)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
