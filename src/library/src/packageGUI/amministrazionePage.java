package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import packageBusiness.utente;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 607);
		
		JLabel lblNewLabel = new JLabel(utente.getEmail());
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblSelezionaTabella = new JLabel("Seleziona Tabella");
		
		listener a = new listener(); 
		
		table = new JTable();
		tm = (DefaultTableModel) table.getModel();
	    
		JButton btnSeleziona = new JButton("SELEZIONA");
		btnSeleziona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				    statement = connect.createStatement();
				    ResultSet rs = statement.executeQuery("SELECT * FROM " + comboBox.getSelectedItem());
				    tm.removeTableModelListener(a);
				    // get columns info
				    ResultSetMetaData rsmd = rs.getMetaData();
				    int columnCount = rsmd.getColumnCount();
				    
				    // for changing column and row model
				   
				    // clear existing columns 
				    tm.setColumnCount(0);

				    // add specified columns to table
				    for (int i = 1; i <= columnCount; i++ ) {
				        tm.addColumn(rsmd.getColumnName(i));
				    }   

				    // clear existing rows
				    tm.setRowCount(0);

				    // add rows to table
				    while (rs.next()) {
				        String[] a = new String[columnCount];
				        for(int i = 0; i < columnCount; i++) {
				            a[i] = rs.getString(i+1);
				        }
				    tm.addRow(a);
				    }
				    tm.fireTableDataChanged();
					tm.fireTableStructureChanged();
					tm.addTableModelListener(a);
					
				    // Close ResultSet and Statement
				    rs.close();
				    statement.close();
				} catch (Exception ex) { 
				    new dialog().errorDialog("db error 2" + ex.getMessage());
				    ex.printStackTrace();
				    return;
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRicarica = new JButton("RICARICA");
		
		JButton btnDelete = new JButton("DELETE");
		
		JButton btnInserisciUtente = new JButton("INSERISCI IMMAGINI");
		btnInserisciUtente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(2);
				new amministrazioneView().istanziaRicercaPage(utente);
			}
		});
		
		JButton btnInserisciOpera = new JButton("INSERISCI OPERA");
		btnInserisciOpera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(2);
				new amministrazioneView().apriAggiungiOperaForm();
			}
		});
		
		JButton btnInserisciImmagine = new JButton("INSERISCI UTENTE");
		btnInserisciImmagine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new amministrazioneView().istanziaRegistrazionePage();
			}
		});
		
		JButton btnRevisionaImmagini = new JButton("REVISIONA IMMAGINI");
		btnRevisionaImmagini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(3);
				new amministrazioneView().istanziaRicercaPage(utente);
			}
		});
		
		JButton btnInserisciTrascrizione = new JButton("INSERISCI TRASCRIZIONI");
		btnInserisciTrascrizione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utente.setPermessi(4);
				new amministrazioneView().istanziaRicercaPage(utente);
			}
		});
		
		JButton btnRevisionaTrascrizione = new JButton("REVISIONA TRASCRIZIONI");
		btnRevisionaTrascrizione.addActionListener(new ActionListener() {
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
		
		// Connect to host
		try {
		    connect = DriverManager.getConnection("jdbc:mysql://localhost/library?" + "user=root&password=");
		} catch (SQLException e) {
			new dialog().errorDialog("Database connection error");
		return;
		}
		
		// Clear combobox with tables names
		comboBox.removeAllItems();
		try{
			// Get all DB's tables and put them to combobox...
			statement = connect.createStatement();
			// !!! and now QUOTES, NOT BACKTICKS !!!
			// !!! because "jComboBox1.getSelectedItem().toString()" is column value, 
			// not column/table/db name !!!
			/*ResultSet rs = statement.executeQuery("SELECT TABLE_NAME FROM information_schema.TABLES" +
	    	" WHERE TABLE_SCHEMA = '" + jComboBox1.getSelectedItem().toString() + "'");*/
	    
	    	ResultSet rs = statement.executeQuery("SHOW TABLES;");
	    
	    	// Add tables list to combobox
	    	while (rs.next()) {
	    		comboBox.addItem(rs.getString(1));
	    	}
	    } catch(SQLException e) {
	    	new dialog().errorDialog("db error");
	    	return;
	    }
	    
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnComandi = new JMenu("Comandi");
		menuBar.add(mnComandi);
		
		JMenuItem mntmIndietro = new JMenuItem("INDIETRO");
		mnComandi.add(mntmIndietro);
		
		JMenuItem mntmLogOut = new JMenuItem("LOG OUT");
		mnComandi.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("EXIT");
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
	
	class listener implements TableModelListener {
		 
		 public void tableChanged(TableModelEvent e) {  
		        int row = e.getFirstRow(); 
		        int column = e.getColumn();  
		        DefaultTableModel model = (DefaultTableModel)e.getSource();  
		        Object data = model.getValueAt(row, column);  
		//now you have the data in the cell and the place in the grid where the   

		//cell is so you can use the data as you want  
		    }
		
	}
	
}
