package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import packageBusiness.utente;
import packageView.operaView;
import packageView.revisione_tView;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
 * Classe JFrame operaPage
 */
public class operaPage extends JFrame {

	private JPanel contentPane;
	private static int npagina = 1; /* Current page */ 
	private JLabel lblNewLabel; /* Page label */
	private static JLabel lblImg; 
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					operaPage frame = new operaPage(new utente("prva",1), "provatitolo");
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
	public operaPage(utente user, String titolo) {
		super("Library");
		
		operaPage finestra = this; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 724);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnComandi = new JMenu("Comandi");
		menuBar.add(mnComandi);
		
		JMenuItem mntmLogOut = new JMenuItem("LOG OUT");
		mntmLogOut.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {
				new operaView().logOut(finestra);
			}
		});
		
		JMenuItem mntmIndietro = new JMenuItem("INDIETRO");
		mntmIndietro.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				new operaView().indietro(finestra, user);
			}
		});
		mnComandi.add(mntmIndietro);
		mnComandi.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("EXIT");
		mntmExit.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				new operaView().exit(finestra);
			}
		});
		mnComandi.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblImg = new JLabel("");
		JTextPane textPane = new JTextPane();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		//Indietro button
		JButton btnNewButton = new JButton("\u25C4");
				
		//Avanti button
		JButton button = new JButton("\u25BA");
		button.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Avanti"
			 */
			public void actionPerformed(ActionEvent arg0) {
				npagina = new operaView().clickAvanti(titolo, lblNewLabel, textField, npagina, button, btnNewButton, user);
				new operaView().vista(lblImg, textPane, titolo, npagina, user);		
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Indietro"
			 */
			public void actionPerformed(ActionEvent arg0) {
				npagina = new operaView().clickIndietro(titolo, lblNewLabel, textField, npagina, button, btnNewButton, user);
				new operaView().vista(lblImg, textPane, titolo, npagina, user);
			}
		});
		
		JLabel lblUser = new JLabel(user.getEmail());
		JLabel lblTooo = new JLabel(titolo);
		
		
		/**
		 * Caricamento prima pagina 
		 */
		lblNewLabel = new JLabel();
		npagina = new operaView().firstPage(titolo, button, lblNewLabel , textField, npagina, user);
		new operaView().vista(lblImg, textPane, titolo, npagina, user);
		btnNewButton.setEnabled(false);
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton_1 = new JButton("VAI");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				npagina = Integer.parseInt(textField.getText());
				new operaView().vista(lblImg, textPane, titolo, npagina, user);
				if(npagina == 1) btnNewButton.setEnabled(false);
				else btnNewButton.setEnabled(true);
				if(npagina == (new operaView().getPageMax(titolo, user))) button.setEnabled(false);
				else button.setEnabled(true); 
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTooo)
							.addPreferredGap(ComponentPlacement.RELATED, 685, Short.MAX_VALUE)
							.addComponent(lblUser))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addComponent(btnNewButton_1)
									.addPreferredGap(ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
									.addComponent(button)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUser)
						.addComponent(lblTooo))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImg, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		

		scrollPane.setViewportView(textPane);
		contentPane.setLayout(gl_contentPane);
	}
}
