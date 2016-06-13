package packageGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import packageBusiness.immagine;
import packageBusiness.opera;
import packageBusiness.utente;
import packageView.acquisizioneView;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class acquisizionePage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private BufferedImage immagine;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					acquisizionePage frame = new acquisizionePage(new utente(),null);
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
	public acquisizionePage(utente utente, opera opera) {
		super("Library");
		Component acquisizionePage = this;
		acquisizionePage window = this; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 430);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Comandi");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmLogOut = new JMenuItem("LOG OUT");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new acquisizioneView().logOut(window);
			}
		});
		
		JMenuItem mntmIndietro = new JMenuItem("INDIETRO");
		mntmIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new acquisizioneView().indietro(window, utente);
			}
		});
		mnNewMenu.add(mntmIndietro);
		mnNewMenu.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("EXIT");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new acquisizioneView().exit(window);
			}
		});
		mnNewMenu.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel();
		
		JLabel lblUtente = new JLabel(utente.getEmail());
		
		JLabel lblTitolo = new JLabel(opera.getTitolo());
		
		JButton btnNewButton = new JButton("CONFERMA");
		btnNewButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0){
				new acquisizioneView().conferma(textField.getText(), textField_1.getText(), textField_2.getText(), opera, immagine, utente.getEmail());
			}
		});
		
		JButton btnNewButton_1 = new JButton("CARICA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				immagine = new acquisizioneView().carica(lblNewLabel, acquisizionePage); 
			}
		});
		
		JLabel lblRisoluzione = new JLabel("RISOLUZIONE");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblDataScatto = new JLabel("DATA SCATTO");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PAGINA");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitolo)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRisoluzione)
										.addComponent(lblDataScatto)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
									.addComponent(btnNewButton_1)))
							.addGap(102))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton)
								.addComponent(lblUtente))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitolo)
						.addComponent(lblUtente))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblRisoluzione)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDataScatto)
							.addGap(5)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
