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
public class revisione_aPage extends JFrame {

	private JPanel contentPane;
	private int npagina = 1; /* Current page */ 
	private JLabel lblNewLabel; /* Page label */
	private static JLabel lblImg; 
	private JTextField txtAcquisitore;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_3;
	private JRadioButton rdbtnValidaAcquisizione; 
	private JRadioButton rdbtnRifiutaAcquisizione;
	private JButton btnConferma; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					revisione_aPage frame = new revisione_aPage(new utente("prova",1), "provatitolo");
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
	public revisione_aPage(utente user, String titolo) {
		super("Library");
		
		revisione_aPage finestra = this; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 724);
		
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
				new revisione_aView().logOut(finestra);
			}
		});
		
		JMenuItem mntmIndietro = new JMenuItem("INDIETRO");
		mntmIndietro.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				new revisione_aView().indietro(finestra, user);
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
				new revisione_aView().exit(finestra);
			}
		});
		mnComandi.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblImg = new JLabel("");
		
		lblNewLabel_3 = new JLabel();
		
		//Indietro button
		JButton btnNewButton = new JButton("\u25C4");
				
		//Avanti button
		JButton button = new JButton("\u25BA");
		button.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Avanti"
			 */
			public void actionPerformed(ActionEvent arg0) {
				npagina = new revisione_aView().clickAvanti(titolo, lblNewLabel, npagina, button, btnNewButton, user);
				new revisione_aView().vista(lblImg,titolo, npagina);
				new revisione_aView().metadati(txtAcquisitore, textField, textField_1, titolo, npagina);
				lblNewLabel_3.setText(new revisione_aView().getValidazione(titolo, npagina));
				
				if(!lblNewLabel_3.getText().equals("ACQUISIZIONE DISPONIBILE PER LA VALIDAZIONE")){
					rdbtnValidaAcquisizione.setSelected(false); 
					rdbtnRifiutaAcquisizione.setSelected(false); 
					rdbtnValidaAcquisizione.setEnabled(false);
					rdbtnRifiutaAcquisizione.setEnabled(false);
					btnConferma.setEnabled(false);
				} else {
					rdbtnValidaAcquisizione.setSelected(false); 
					rdbtnRifiutaAcquisizione.setSelected(false); 
					rdbtnValidaAcquisizione.setEnabled(true);
					rdbtnRifiutaAcquisizione.setEnabled(true);
					btnConferma.setEnabled(true);
				}
				
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Indietro"
			 */
			public void actionPerformed(ActionEvent arg0) {
				npagina = new revisione_aView().clickIndietro(titolo, lblNewLabel, npagina, button, btnNewButton, user);
				new revisione_aView().vista(lblImg, titolo, npagina);
				new revisione_aView().metadati(txtAcquisitore, textField, textField_1, titolo, npagina);
				lblNewLabel_3.setText(new revisione_aView().getValidazione(titolo, npagina));
				
				if(!lblNewLabel_3.getText().equals("ACQUISIZIONE DISPONIBILE PER LA VALIDAZIONE")){
					rdbtnValidaAcquisizione.setSelected(false); 
					rdbtnRifiutaAcquisizione.setSelected(false); 
					rdbtnValidaAcquisizione.setEnabled(false);
					rdbtnRifiutaAcquisizione.setEnabled(false);
					btnConferma.setEnabled(false);
				} else {
					rdbtnValidaAcquisizione.setSelected(false); 
					rdbtnRifiutaAcquisizione.setSelected(false);
					rdbtnValidaAcquisizione.setEnabled(true);
					rdbtnRifiutaAcquisizione.setEnabled(true); 
					btnConferma.setEnabled(true);
				}
			}
		});
		
		JLabel lblUser = new JLabel(user.getEmail());
		JLabel lblTooo = new JLabel(titolo);
		
		
		txtAcquisitore = new JTextField();
		txtAcquisitore.setEditable(false);
		txtAcquisitore.setColumns(10);
		
		JLabel lblAcquisitore = new JLabel("ACQUISITORE");
		
		JLabel lblNewLabel_1 = new JLabel("DATA SCATTO");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("RISOLUZIONE");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		rdbtnValidaAcquisizione = new JRadioButton("  VALIDA ACQUISIZIONE");		
		rdbtnRifiutaAcquisizione = new JRadioButton("  RIFIUTA  ACQUISIZIONE");
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnValidaAcquisizione);
		group.add(rdbtnRifiutaAcquisizione);
		
		
		btnConferma = new JButton("CONFERMA");
		btnConferma.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				boolean validate; 
				
				if(rdbtnValidaAcquisizione.isSelected()){
					validate = true; 
				}
				else if (rdbtnRifiutaAcquisizione.isSelected()){
					validate = false; 
				}
				else{
					new revisione_aView().errorMessage("Scegliere se validare o rifiutare l'acquisizione");
					return; 
				}
				
				boolean success = new revisione_aView().conferma(titolo, npagina, validate, user); 
				rdbtnValidaAcquisizione.setSelected(false); 
				rdbtnRifiutaAcquisizione.setSelected(false); 
				rdbtnValidaAcquisizione.setEnabled(false);
				rdbtnRifiutaAcquisizione.setEnabled(false);
				btnConferma.setEnabled(false);
				
				if(success){
					boolean pubblicata = new revisione_aView().validaOpera(titolo, user); 
					if(pubblicata){
						new revisione_aView().infoMessage("Tutte le acquisizioni sono state validate\nL'opera è appena stata pubblicata");
						new revisione_aView().istanziaRicercaPage(user);
						new revisione_aView().dispose(finestra);
					}
				} else
					new revisione_aView().errorMessage("Errore");
			}
		});
		
		/**
		 * Caricamento prima pagina 
		 */
		lblNewLabel = new JLabel("");
		npagina = new revisione_aView().firstPage(titolo, button, lblNewLabel , npagina, user);
		new revisione_aView().vista(lblImg, titolo, npagina);
		btnNewButton.setEnabled(false);
		new revisione_aView().metadati(txtAcquisitore, textField, textField_1, titolo, npagina);
		
		lblNewLabel_3.setText(new revisione_aView().getValidazione(titolo, npagina));
		
		if(!lblNewLabel_3.getText().equals("ACQUISIZIONE DISPONIBILE PER LA VALIDAZIONE")){
			rdbtnValidaAcquisizione.setSelected(false); 
			rdbtnRifiutaAcquisizione.setSelected(false); 
			rdbtnValidaAcquisizione.setEnabled(false);
			rdbtnRifiutaAcquisizione.setEnabled(false);
			btnConferma.setEnabled(false);
		} else {
			rdbtnValidaAcquisizione.setSelected(false); 
			rdbtnRifiutaAcquisizione.setSelected(false); 
			rdbtnValidaAcquisizione.setEnabled(true);
			rdbtnRifiutaAcquisizione.setEnabled(true);
			btnConferma.setEnabled(true);
		}
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTooo)
							.addPreferredGap(ComponentPlacement.RELATED, 687, Short.MAX_VALUE)
							.addComponent(lblUser))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
									.addComponent(button))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(37)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtAcquisitore, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
										.addComponent(lblAcquisitore, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2)
										.addComponent(textField_1)
										.addComponent(rdbtnValidaAcquisizione)
										.addComponent(rdbtnRifiutaAcquisizione)
										.addComponent(btnConferma)
										.addComponent(lblNewLabel_3))))))
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblImg, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button)
								.addComponent(btnNewButton)
								.addComponent(lblNewLabel)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAcquisitore, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAcquisitore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(rdbtnValidaAcquisizione)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnRifiutaAcquisizione)
							.addGap(18)
							.addComponent(btnConferma)
							.addGap(64)
							.addComponent(lblNewLabel_3)
							.addGap(207)))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
