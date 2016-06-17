package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import packageBusiness.utente;
import packageView.loginView;
import packageView.operaView;
import packageView.revisione_aView;
import packageView.revisione_tView;

import javax.swing.ButtonGroup;
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
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

/**
 * Classe JFrame operaPage
 */
public class revisione_tPage extends JFrame {

	private JPanel contentPane;
	private int npagina = 1; /* Current page */ 
	private JLabel lblNewLabel; 
	private JTextField txtTrascrittore;
	private JTextField textField;
	private JLabel lblNewLabel_3;
	private JRadioButton rdbtnValidaTrascrizione; 
	private JRadioButton rdbtnRifiutaTrascrizione;
	private JButton btnConferma; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					revisione_tPage frame = new revisione_tPage(new utente("prova",1), "provatitolo");
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
	public revisione_tPage(utente user, String titolo) {
		super("Library");
		
		revisione_tPage finestra = this; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 724);
		
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
				new revisione_tView().logOut(finestra);
			}
		});
		
		JMenuItem mntmIndietro = new JMenuItem("INDIETRO");
		mntmIndietro.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				new revisione_tView().indietro(finestra, user);
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
				new revisione_tView().exit(finestra);
			}
		});
		mnComandi.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setText("trascrizione validata in precedenza");
		
		//Indietro button
		JButton btnNewButton = new JButton("\u25C4");
		
		//pannello trascrizione
		JTextPane textPane = new JTextPane();
		
		//pannello immagine
		JLabel lblNewLabel_2 = new JLabel("New label");
				
		//Avanti button
		JButton button = new JButton("\u25BA");
		button.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Avanti"
			 */
			public void actionPerformed(ActionEvent arg0) {
				npagina = new revisione_tView().clickAvanti(titolo, lblNewLabel, npagina, button, btnNewButton, user);
				new revisione_tView().vistatrascrizione(textPane,titolo, npagina, user);
				new revisione_tView().vistaimmagine(lblNewLabel_2, titolo, npagina, user);
				new revisione_tView().metadati(txtTrascrittore, textField, titolo, npagina, user);
				lblNewLabel_3.setText(new revisione_tView().getValidazione(titolo, npagina, user));
				
				if(!lblNewLabel_3.getText().equals("TRASCRIZIONE DISPONIBILE PER LA VALIDAZIONE")){
					rdbtnValidaTrascrizione.setSelected(false); 
					rdbtnRifiutaTrascrizione.setSelected(false); 
					rdbtnValidaTrascrizione.setEnabled(false);
					rdbtnRifiutaTrascrizione.setEnabled(false);
					btnConferma.setEnabled(false);
				} else {
					rdbtnValidaTrascrizione.setSelected(false); 
					rdbtnRifiutaTrascrizione.setSelected(false); 
					rdbtnValidaTrascrizione.setEnabled(true);
					rdbtnRifiutaTrascrizione.setEnabled(true);
					btnConferma.setEnabled(true);
				}
				
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Indietro"
			 */
			public void actionPerformed(ActionEvent arg0) {
				npagina = new revisione_tView().clickIndietro(titolo, lblNewLabel, npagina, button, btnNewButton, user);
				new revisione_tView().vistatrascrizione(textPane, titolo, npagina, user);
				new revisione_tView().vistaimmagine(lblNewLabel_2, titolo, npagina, user);
				new revisione_tView().metadati(txtTrascrittore, textField, titolo, npagina, user);
				lblNewLabel_3.setText(new revisione_tView().getValidazione(titolo, npagina, user));
				
				if(!lblNewLabel_3.getText().equals("TRASCRIZIONE DISPONIBILE PER LA VALIDAZIONE")){
					rdbtnValidaTrascrizione.setSelected(false); 
					rdbtnRifiutaTrascrizione.setSelected(false); 
					rdbtnValidaTrascrizione.setEnabled(false);
					rdbtnRifiutaTrascrizione.setEnabled(false);
					btnConferma.setEnabled(false);
				} else {
					rdbtnValidaTrascrizione.setSelected(false); 
					rdbtnRifiutaTrascrizione.setSelected(false);
					rdbtnValidaTrascrizione.setEnabled(true);
					rdbtnRifiutaTrascrizione.setEnabled(true); 
					btnConferma.setEnabled(true);
				}
			}
		});
		
		JLabel lblUser = new JLabel(user.getEmail());
		JLabel lblTooo = new JLabel(titolo);
		
		
		txtTrascrittore = new JTextField();
		txtTrascrittore.setEditable(false);
		txtTrascrittore.setColumns(10);
		
		JLabel lblAcquisitore = new JLabel("TRASCRITTORE");
		
		JLabel lblNewLabel_1 = new JLabel("DATA SCRITTURA");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		rdbtnValidaTrascrizione = new JRadioButton("  VALIDA  TRASCRIZIONE");		
		rdbtnRifiutaTrascrizione = new JRadioButton("  RIFIUTA  TRASCRIZIONE");
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnValidaTrascrizione);
		group.add(rdbtnRifiutaTrascrizione);
		
		
		btnConferma = new JButton("CONFERMA");
		btnConferma.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				boolean validate; 
				
				if(rdbtnValidaTrascrizione.isSelected()){
					validate = true; 
				}
				else if (rdbtnRifiutaTrascrizione.isSelected()){
					validate = false; 
				}
				else{
					new revisione_tView().errorMessage("Scegliere se validare o rifiutare la trascrizione");
					return; 
				}
				
				boolean success = new revisione_tView().conferma(titolo, npagina, validate, user); 
				rdbtnValidaTrascrizione.setSelected(false); 
				rdbtnRifiutaTrascrizione.setSelected(false); 
				rdbtnValidaTrascrizione.setEnabled(false);
				rdbtnRifiutaTrascrizione.setEnabled(false);
				btnConferma.setEnabled(false);
				
				if(success){
					boolean pubblicata = new revisione_tView().validaOpera(titolo, user); 
					if(pubblicata){
						new revisione_tView().infoMessage("Tutte le trascrizioni sono state validate");
						new revisione_tView().istanziaRicercaPage(user);
						new revisione_tView().dispose(finestra);
					}
				} else
					new revisione_tView().errorMessage("Errore");
			}
		});
		
		/**
		 * Caricamento prima pagina 
		 */
		lblNewLabel = new JLabel("estfgswtfbgws");
		npagina = new revisione_tView().firstPage(titolo, button, lblNewLabel , npagina, user);
		new revisione_tView().vistatrascrizione(textPane, titolo, npagina, user);
		new revisione_tView().vistaimmagine(lblNewLabel_2, titolo, npagina, user);
		btnNewButton.setEnabled(false);
		new revisione_tView().metadati(txtTrascrittore, textField, titolo, npagina, user);
		
		lblNewLabel_3.setText(new revisione_tView().getValidazione(titolo, npagina, user));
		
		if(!lblNewLabel_3.getText().equals("TRASCRIZIONE DISPONIBILE PER LA VALIDAZIONE")){
			rdbtnValidaTrascrizione.setSelected(false); 
			rdbtnRifiutaTrascrizione.setSelected(false); 
			rdbtnValidaTrascrizione.setEnabled(false);
			rdbtnRifiutaTrascrizione.setEnabled(false);
			btnConferma.setEnabled(false);
		} else {
			rdbtnValidaTrascrizione.setSelected(false); 
			rdbtnRifiutaTrascrizione.setSelected(false); 
			rdbtnValidaTrascrizione.setEnabled(true);
			rdbtnRifiutaTrascrizione.setEnabled(true);
			btnConferma.setEnabled(true);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTooo)
							.addPreferredGap(ComponentPlacement.RELATED, 918, Short.MAX_VALUE)
							.addComponent(lblUser))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED, 608, Short.MAX_VALUE)
									.addComponent(button))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(33)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3)
										.addComponent(txtTrascrittore)
										.addComponent(lblAcquisitore, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField)
										.addComponent(rdbtnValidaTrascrizione)
										.addComponent(rdbtnRifiutaTrascrizione, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnConferma))))))
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
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblAcquisitore)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtTrascrittore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(69)
									.addComponent(rdbtnValidaTrascrizione)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnRifiutaTrascrizione)
									.addGap(18)
									.addComponent(btnConferma)
									.addGap(116)
									.addComponent(lblNewLabel_3))
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button)
								.addComponent(btnNewButton)
								.addComponent(lblNewLabel))))
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(textPane);
		contentPane.setLayout(gl_contentPane);
	}
}
