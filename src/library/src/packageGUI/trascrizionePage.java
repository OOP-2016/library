package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;

import packageBusiness.utente;
import packageView.acquisizioneView;
import packageView.operaView;
import packageView.trascrizioneView;

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
public class trascrizionePage extends JFrame {

	private JPanel contentPane;
	private static int npagina = 1; /* Current page */ 
	private JLabel lblNewLabel; /* Page label */
	private static JLabel lblImg; 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextPane textPane_1;
	private JTextPane textPane;
	private StringBuilder stringa; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trascrizionePage frame = new trascrizionePage(new utente("prva",1), "provatitolo");
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
	public trascrizionePage(utente user, String titolo) {
		super("Library");
		
		trascrizionePage finestra = this; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1247, 735);
		
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
				new trascrizioneView().logOut(finestra);
			}
		});
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textPane = new JTextPane();
		textPane_1 = new JTextPane();
		textPane.setContentType("text/html");
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JMenuItem mntmIndietro = new JMenuItem("INDIETRO");
		mntmIndietro.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				new trascrizioneView().indietro(finestra, user);
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
				new trascrizioneView().exit(finestra);
			}
		});
		mnComandi.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblImg = new JLabel("");
		
		//Conferma button 
		JButton btnNewButton_1 = new JButton("CONFERMA");
		
		//Indietro button
		JButton btnNewButton = new JButton("\u25C4");
				
		//Avanti button
		JButton button = new JButton("\u25BA");
		button.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Avanti"
			 */
			public void actionPerformed(ActionEvent arg0) {
				npagina = new trascrizioneView().clickAvanti(titolo, lblNewLabel, textField_3, npagina, button, btnNewButton, user);
				new trascrizioneView().vista(lblImg, titolo, npagina, user);
				
				if(new trascrizioneView().esistePagina(npagina, titolo, user)){
					textPane.setText("<h2>trascrizione già inserita</h2>");
					btnNewButton_1.setEnabled(false);
				} else {
					btnNewButton_1.setEnabled(true);
					textPane.setText(null);
				}
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia la classe loginView al click del bottone "Indietro"
			 */
			public void actionPerformed(ActionEvent arg0) {
				npagina = new trascrizioneView().clickIndietro(titolo, lblNewLabel, textField_3, npagina, button, btnNewButton, user);
				new trascrizioneView().vista(lblImg, titolo, npagina, user);
				
				if(new trascrizioneView().esistePagina(npagina, titolo, user)){
					textPane.setText("<h2>trascrizione già inserita</h2>");
					btnNewButton_1.setEnabled(false);
				} else {
					btnNewButton_1.setEnabled(true);
					textPane.setText(null);
				}
			}
		});
		
		JLabel lblUser = new JLabel(user.getEmail());
		JLabel lblTooo = new JLabel(titolo);
		
		
		/**
		 * Caricamento prima pagina 
		 */
		lblNewLabel = new JLabel();
		npagina = new trascrizioneView().firstPage(titolo, button, lblNewLabel, textField_3, npagina, user);
		new dialog().infoDialog(String.format("%d", npagina));
		new trascrizioneView().vista(lblImg, titolo, npagina, user);	
		textField_3.setText(String.format("%d", npagina));
		btnNewButton.setEnabled(false);
		
		if(new trascrizioneView().esistePagina(npagina, titolo, user)){
			textPane.setText("<h2>trascrizione già inserita</h2>");
			btnNewButton_1.setEnabled(false);
		} else {
			btnNewButton_1.setEnabled(true);
			textPane.setText(null);
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new trascrizioneView().conferma(textField_2.getText(), textPane.getText(), npagina, titolo, user);
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblTesto = new JLabel("CORPO");
		
		JLabel lblTitolo = new JLabel("TITOLO");
		
		JLabel lblTitolo_1 = new JLabel("SOTTOTITOLO");
		
		stringa = new StringBuilder();
		
		JButton btnAnteprima = new JButton("APPLICA");
		btnAnteprima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				if(!(textField_1.getText().length() == 0))
					stringa.append("<h1>" + textField_1.getText() + "</h1>");
				if(!(textField.getText().length() == 0))
					stringa.append("<h2>" + textField.getText() + "</h2>");
				if(!(textPane_1.getText().length() == 0))
					stringa.append("<p>" + textPane_1.getText() + "</p>");
				
				
				//new dialog().infoDialog(stringa.toString());
				textPane.setText(stringa.toString());
				
				textField_1.setText(null);
				textField.setText(null);
				textPane_1.setText(null);
						
			}
		});
		
		
		
		JButton btnVai = new JButton("VAI");
		btnVai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				npagina = Integer.parseInt(textField_3.getText());
				new trascrizioneView().vista(lblImg, titolo, npagina, user);
				if(npagina == 1) btnNewButton.setEnabled(false);
				else btnNewButton.setEnabled(true);
				if(npagina == (new trascrizioneView().getPageMax(titolo, user))) button.setEnabled(false);
				else button.setEnabled(true); 
				
				if(new trascrizioneView().esistePagina(npagina, titolo, user)){
					textPane.setText("<h2>trascrizione già inserita</h2>");
					btnNewButton_1.setEnabled(false);
				} else {
					btnNewButton_1.setEnabled(true);
					textPane.setText(null);
				}
				
			}
		});
		
		JLabel lblData = new JLabel("DATA");
		
		JButton btnCancella = new JButton("CANCELLA");
		btnCancella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stringa = new StringBuilder(); 
				textPane.setText(null);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTooo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(50)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(btnVai)
									.addPreferredGap(ComponentPlacement.RELATED, 599, Short.MAX_VALUE)
									.addComponent(button))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblData)
										.addComponent(textField_1, Alignment.TRAILING)
										.addComponent(textField, Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnAnteprima)
											.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
											.addComponent(btnCancella)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnNewButton_1))
										.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
										.addComponent(lblTesto)
										.addComponent(lblTitolo_1)
										.addComponent(lblTitolo)
										.addComponent(textField_2)))))
						.addComponent(lblUser))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTooo)
						.addComponent(lblUser))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblData)
							.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTitolo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTitolo_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblTesto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1)
								.addComponent(btnAnteprima)
								.addComponent(btnCancella))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
						.addComponent(lblImg, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVai))
					.addContainerGap())
		);
		
		
		scrollPane_1.setViewportView(textPane_1);
		scrollPane.setViewportView(textPane);
		contentPane.setLayout(gl_contentPane);
	}
}
