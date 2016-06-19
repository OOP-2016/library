package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import packageBusiness.utente;
import packageController.tableListener;
import packageView.amministrazioneView;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class amministrazionePage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection connect = null;
	private Statement statement = null;
	private DefaultTableModel tm; 
	
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					amministrazionePage frame = new amministrazionePage(new utente());
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
	
	public amministrazionePage(utente utente) {
		
		new dialog().warningDialog("Si informa che la modifica irresponsabile dei dati potrebbe causare errori nel sistema");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 607);
		amministrazionePage finestra = this;
		JLabel lblNewLabel = new JLabel(utente.getEmail());
		
		JComboBox comboBox = new JComboBox();
		
		ArrayList <String> columnNames=new ArrayList<String>(); 
		
		JLabel lblSelezionaTabella = new JLabel("Seleziona Tabella");
		
		//listener a = new listener(); 
		
		tableListener listener = new tableListener(); 
		
		table = new JTable();
		tm = (DefaultTableModel) table.getModel();
	    
		JButton btnSeleziona = new JButton("SELEZIONA");
		btnSeleziona.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto seleziona
			 * (permette di selezionare una tabella dal database e caricarla nella jtable)
			 * 
			 */
			
			public void actionPerformed(ActionEvent arg0) {
				new amministrazioneView().seleziona(listener, columnNames, tm, (String)comboBox.getSelectedItem());
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRicarica = new JButton("RICARICA");
		btnRicarica.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto ricarica
			 * (permette il refresh della tabella selezionata del database) 
			 */
			
			public void actionPerformed(ActionEvent arg0) {
				new amministrazioneView().ricarica(listener, columnNames, tm, (String)comboBox.getSelectedItem());
			}
		});
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto delete
			 * (permette la cancellazione di una riga dal database)
			 * 
			 */
			
			public void actionPerformed(ActionEvent arg0) {
				new amministrazioneView().delete(table, (String)comboBox.getSelectedItem());
			}
		});
		
		JButton btnInserisciUtente = new JButton("INSERISCI IMMAGINI");
		btnInserisciUtente.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto inserisci immagini
			 * (apre la pagina di caricamento immagine)
			 * 
			 */
			
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(2);
				new amministrazioneView().istanziaRicercaPage(utente);
			}
		});
		
		JButton btnInserisciOpera = new JButton("INSERISCI OPERA");
		btnInserisciOpera.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto inserisci opera
			 * (apre la pagina di caricamento opera)
			 * 
			 */
			
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(2);
				new amministrazioneView().apriAggiungiOperaForm();
			}
		});
		
		JButton btnInserisciImmagine = new JButton("INSERISCI UTENTE");
		btnInserisciImmagine.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto inserisci utente
			 * (apre la pagina di registrazione)
			 * 
			 */
			
			public void actionPerformed(ActionEvent e) {
				new amministrazioneView().istanziaRegistrazionePage();
			}
		});
		
		JButton btnRevisionaImmagini = new JButton("REVISIONA IMMAGINI");
		btnRevisionaImmagini.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto revisiona immagini
			 * (apre la pagina di revisione immagini)
			 * 
			 */
			
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(3);
				new amministrazioneView().istanziaRicercaPage(utente);
			}
		});
		
		JButton btnInserisciTrascrizione = new JButton("INSERISCI TRASCRIZIONI");
		btnInserisciTrascrizione.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto inserisci trascrizione
			 * (apre la pagina di caricamento trascrizione)
			 * 
			 */
			
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(4);
				new amministrazioneView().istanziaRicercaPage(utente);
			}
		});
		
		JButton btnRevisionaTrascrizione = new JButton("REVISIONA TRASCRIZIONI");
		btnRevisionaTrascrizione.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto revisiona trascrizioni
			 * (apre la pagina di revisione trascrizione)
			 * 
			 */
			
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(5);
				new amministrazioneView().istanziaRicercaPage(utente);
			}
		});
		
		
		// Init MySQL JDBC Driver
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		    new dialog().errorDialog("Database connection error");
		    return;
		}
		
		
		try{
			connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
			
			comboBox.removeAllItems();
			statement = connect.createStatement();
	    	ResultSet rs = statement.executeQuery("SHOW TABLES;");
	    
	    	// Add tables list to combobox
	    	while (rs.next()) {
	    		comboBox.addItem(rs.getString(1));
	    	}
	    	
	    } catch(SQLException e) {
	    	new dialog().errorDialog("Errore");
	    	return;
	    }
	    
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnComandi = new JMenu("Comandi");
		menuBar.add(mnComandi);
		
		JMenuItem mntmLogOut = new JMenuItem("LOG OUT");
		mntmLogOut.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto logout
			 * 
			 */
			
			public void actionPerformed(ActionEvent arg0) {
				new amministrazioneView().logOut(finestra);
			}
		});
		mnComandi.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("EXIT");
		mntmExit.addActionListener(new ActionListener() {
			
			/**
			 * ActionPerformed handler che istanzia la classe amministrazioneView alla pressione del tasto exit
			 * 
			 */
			
			
			public void actionPerformed(ActionEvent arg0) {
				new amministrazioneView().exit(finestra);
			}
		});
		mnComandi.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSelezionaTabella)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSeleziona)
							.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
							.addComponent(btnDelete)
							.addGap(18)
							.addComponent(btnRicarica))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnInserisciImmagine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnInserisciOpera, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRevisionaImmagini, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
								.addComponent(btnInserisciUtente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRevisionaTrascrizione, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addComponent(btnInserisciTrascrizione, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(16)
					.addComponent(lblSelezionaTabella)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSeleziona)
						.addComponent(btnRicarica)
						.addComponent(btnDelete))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInserisciImmagine)
						.addComponent(btnInserisciOpera)
						.addComponent(btnInserisciUtente)
						.addComponent(btnInserisciTrascrizione))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRevisionaImmagini)
						.addComponent(btnRevisionaTrascrizione))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	}
	
	

