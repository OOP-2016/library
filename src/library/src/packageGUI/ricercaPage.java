package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packageBusiness.utente;
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
	public ricercaPage(utente user) { 
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
		
		
		JButton btnCerca = new JButton("CERCA");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
		});
		
		JLabel lblUtente = new JLabel( (user.getPermessi()==0)?"Utente Base":user.getEmail() );
		
		JLabel lblManoscritti = new JLabel("MANOSCRITTI");
		
		JLabel lblRicercaOpere = new JLabel("RICERCA OPERE");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnApri = new JButton("APRI");
		//controllo che disabilita il tasto APRI se l'utente è guest
		/*if(user.getPermessi() == 0)
			btnApri.setEnabled(false);*/
		String title = null; 
		btnApri.addActionListener(new ActionListener() {
			/**
			 * ActionListener che istanzia ricercaView al click del bottone "Apri"
			 */
			public void actionPerformed(ActionEvent e) {
				new ricercaView().apriOpera(finestra, user, title);
			}
		});
		
		DefaultListModel listModel = new DefaultListModel();
		JList list = new JList(listModel);
		scrollPane.setViewportView(list);
		
		/**
		 * ComboBox riempita automaticamente con i simboli dell'alfabeto + ALL
		 */
		JComboBox comboBox = new JComboBox();
			comboBox.addItem("-");
			comboBox.addItem("ALL");
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
		
			textField = new JTextField();
			
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
		//listModel.addElement("yo");
		//listModel.clear();
		//String s = (String) list.getSelectedValue();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
						.addComponent(btnApri, Alignment.TRAILING)
						.addComponent(lblUtente, Alignment.TRAILING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
							.addComponent(btnCerca, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRicercaOpere)
						.addComponent(lblManoscritti))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUtente)
					.addGap(18)
					.addComponent(lblRicercaOpere)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCerca)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(lblManoscritti)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnApri)
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
	}
}
