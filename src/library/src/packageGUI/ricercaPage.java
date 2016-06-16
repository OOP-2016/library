package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packageBusiness.opera;
import packageBusiness.utente;
import packageController.operaController;
import packageView.acquisizioneView;
import packageView.operaView;
import packageView.ricercaView;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;

public class ricercaPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int keyInTextField = 0; //indispensabile per la disattivazione della comboBox se la TextField è piena
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ricercaPage frame = new ricercaPage(new utente());
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
	public ricercaPage(utente utente) { 
		super("Library"); 			
		
		ricercaPage finestra = this; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 462);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Comandi");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmLogOut = new JMenuItem("LOG OUT");
		mntmLogOut.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia ricercaView alla scelta del menuItem "Log Out"
			 */
			public void actionPerformed(ActionEvent e) {
				new ricercaView().logOut(finestra);
			}
		});

		mnNewMenu.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("EXIT");
		mntmExit.addActionListener(new ActionListener() {
			/**
			 * actionListener che istanzia ricercaView alla scelta del menuItem "Exit"
			 */
			public void actionPerformed(ActionEvent e) {
				new ricercaView().exit(finestra);
			}
		});
		
		mnNewMenu.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
				
		JLabel lblUtente = new JLabel( (utente.getPermessi()==0)?"Ospite":utente.getEmail() );
		
		JLabel lblManoscritti = new JLabel("LISTA OPERE");
		
		JLabel lblRicercaOpere = new JLabel("RICERCA OPERE");
		
		DefaultListModel listModel = new DefaultListModel();
		
		textField = new JTextField();
		
		JList list = new JList(listModel);
		
		JButton btnApri = new JButton("APRI");
		//controllo che disabilita il tasto APRI se l'utente è guest
		if(utente.getPermessi() == 0)
			btnApri.setEnabled(false); 
		btnApri.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
			
				String titoloEautore = (String)list.getSelectedValue();
				String titolo = ""; 
				
				for(int i = 0; i < titoloEautore.length(); i++){
					if(titoloEautore.charAt(i+1) == '-') 
						break; 
					
					titolo+=Character.toString(titoloEautore.charAt(i)); 
				}
				
				opera opera =  new ricercaView().getOpera(titolo, utente); 
				opera.setTitolo(titolo);
				
				if(utente.getPermessi() == 1){
					new ricercaView().apriOpera(finestra, utente, titolo);
					return; 
				} else if(utente.getPermessi() == 2){
					boolean tutteAcquisite = new acquisizioneView().tutteAcquisite(opera.getTitolo(), utente, finestra);
					if(tutteAcquisite){
						return; 
					}
					
					new ricercaView().istanziaAcquisizionePage(finestra,utente,opera);
					return;
				} else if(utente.getPermessi() == 3){
					new ricercaView().istanziaRevisione_aPage(finestra,utente, titolo);
					return; 
				} else if(utente.getPermessi() == 4){
					//new ricercaView().istanziaTrascrizionePage(finestra,utente, titolo);
					return; 
				} else if(utente.getPermessi() == 5){
					new ricercaView().istanziaRevisione_tPage(finestra,utente, titolo);
					return; 					
				} else {
					new ricercaView().errorMessage("Errore utente");
					return; 
				}
				
			}
		});
		
		
		
		/**
		 * ComboBox riempita automaticamente con i simboli dell'alfabeto + ALL
		 */
		JComboBox comboBox = new JComboBox();
			comboBox.addItem("-");
			comboBox.addItem("A");
			comboBox.addItem("B");
			comboBox.addItem("C");
			comboBox.addItem("D");
			comboBox.addItem("E");
			comboBox.addItem("F");
			comboBox.addItem("G");
			comboBox.addItem("H");
			comboBox.addItem("I");
			comboBox.addItem("J");
			comboBox.addItem("K");
			comboBox.addItem("L");
			comboBox.addItem("M");
			comboBox.addItem("N");
			comboBox.addItem("O");
			comboBox.addItem("P");
			comboBox.addItem("Q");
			comboBox.addItem("R");
			comboBox.addItem("S");
			comboBox.addItem("T");
			comboBox.addItem("U");
			comboBox.addItem("V");
			comboBox.addItem("W");
			comboBox.addItem("X");
			comboBox.addItem("Y");
			comboBox.addItem("Z");
			
			new ricercaView().cercaOpera((String)comboBox.getSelectedItem(), listModel, utente);
			
			JButton btnCerca = new JButton("CERCA");
			btnCerca.addActionListener(new ActionListener() {
				/**
				 * 
				 */
				public void actionPerformed(ActionEvent arg0) {
					listModel.clear();
					String filtro;			
					if(comboBox.isEnabled()){ 
						filtro = (String)comboBox.getSelectedItem();
						new ricercaView().cercaOpera(filtro, listModel, utente);
					}
					else {
						filtro= (String)textField.getText();
						new ricercaView().cercaOpera(filtro, listModel, utente);
					}
				}
			});
		
			
			
			/**
			 * KeyListener che viene invocato ad ogni tasto premuto 
			 */
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					
					int strlen = textField.getText().length(); 
					char keyPressed = arg0.getKeyChar(); 
					
					if(keyPressed == KeyEvent.VK_BACK_SPACE && strlen==0){
						keyInTextField = 0; 
					}
					else if (keyPressed == KeyEvent.VK_BACK_SPACE)
						keyInTextField--; 
					else
						keyInTextField++; 
										
					if(keyInTextField != 0)
						comboBox.setEnabled(false);
					
					if(keyInTextField == 0)
						comboBox.setEnabled(true);
				
				}
			});
			textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAggiungiOpera = new JButton("AGGIUNGI OPERA");
		if(utente.getPermessi() != 2){
			btnAggiungiOpera.hide();
		}
		btnAggiungiOpera.addActionListener(new ActionListener() {
			/**
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {
				new ricercaView().apriAggiungiOperaForm(); 
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnAggiungiOpera)
							.addPreferredGap(ComponentPlacement.RELATED, 385, Short.MAX_VALUE)
							.addComponent(btnApri))
						.addComponent(lblUtente, Alignment.TRAILING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
							.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblManoscritti)
						.addComponent(lblRicercaOpere))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUtente)
							.addGap(26))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblRicercaOpere)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCerca)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblManoscritti)
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApri)
						.addComponent(btnAggiungiOpera))
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(list);
		
		contentPane.setLayout(gl_contentPane);
	}
}
