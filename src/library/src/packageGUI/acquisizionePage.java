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
import packageBusiness.pagina;
import packageBusiness.utente;
import packageDAO.paginaDAO;
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

public class acquisizionePage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					acquisizionePage frame = new acquisizionePage(null,null);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel();
		
		JLabel lblUtente = new JLabel(utente.getEmail());
		
		JLabel lblTitolo = new JLabel(opera.getTitolo());
		
		JButton btnNewButton = new JButton("CONFERMA");
		btnNewButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0){
				if(Integer.parseInt(textField_2.getText())>opera.getNumero_pagine()||Integer.parseInt(textField_2.getText())<1){
					new dialog().errorDialog("pagina non valida");
					return;
				}
				if(new acquisizioneView().esistePagina(Integer.parseInt(textField_2.getText()),opera.getTitolo())){
					new dialog().errorDialog("acquisizione già effettuata per questa pagina");
					return;
				}			
			boolean p;
			ImageIcon icon = (ImageIcon)lblNewLabel.getIcon();
			Image immagine3 = icon.getImage();
			BufferedImage immagine = (BufferedImage)immagine3; 
			immagine immagine2 = new immagine(immagine,textField_1.getText(),textField.getText());	
			pagina pagina = new pagina(Integer.parseInt(textField_2.getText()), opera.getTitolo(),immagine2,false,null,false);
			ArrayList<Object> paginaDef = new ArrayList<Object>();
			paginaDef.add(pagina);
			p = new paginaDAO().insert(paginaDef); 
			if(p) new dialog().infoDialog("acquisizione effettuata con successo");
			else new dialog().errorDialog("acquisizione fallita");
			}
		});
		
		JButton btnNewButton_1 = new JButton("CARICA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Image img = null;
			BufferedImage immagine = null;
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(acquisizionePage);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File imported = fileChooser.getSelectedFile();
				try{
				immagine = ImageIO.read(imported);
				img = immagine.getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(),java.awt.Image.SCALE_SMOOTH);
				lblNewLabel.setIcon(new ImageIcon(img));
				//new dialog().infoDialog(lblNewLabel.getWidth()+"x"+lblNewLabel.getHeight());
				}
				catch(Exception e){
				}
			}
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
