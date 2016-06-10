package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packageBusiness.opera;
import packageBusiness.utente;
import packageDAO.operaDAO;
import packageView.operaView;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Classe JFrame operaPage
 */
public class operaPage extends JFrame {

	private JPanel contentPane;
	private static int npagina = 1; 
	private int pageMax = 0; 
	private JLabel lblNewLabel; 
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
			public void actionPerformed(ActionEvent arg0) {
				new operaView().logOut(finestra);
			}
		});
		
		JMenuItem mntmIndietro = new JMenuItem("INDIETRO");
		mntmIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new operaView().indietro(finestra, user);
			}
		});
		mnComandi.add(mntmIndietro);
		mnComandi.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("EXIT");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new operaView().exit(finestra);
			}
		});
		mnComandi.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblImg = new JLabel();
		
		JTextPane textPane = new JTextPane();
		
		
		
		ArrayList<Object> args = new ArrayList<Object>(); 
		args.add(titolo); 
		
		opera opera = (opera)new operaDAO().retrieve(args); 
		pageMax = opera.getNumero_pagine(); 
		lblNewLabel = new JLabel(npagina + " / " + pageMax);
		
		//Indietro 
				JButton btnNewButton = new JButton("\u25C4");
				
		//Avanti
				JButton button = new JButton("\u25BA");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						ArrayList<Object> args = new ArrayList<Object>(); 
						args.add(titolo); 
						
						opera opera = (opera)new operaDAO().retrieve(args); 
						pageMax = opera.getNumero_pagine(); 
						
						if(pageMax < 0) return; 
						
						btnNewButton.setEnabled(true); //abilito bottone indietro
						
						npagina+=1; 
						if(pageMax == npagina) button.setEnabled(false);
						
						
						new operaView().vista(lblImg, textPane, titolo, npagina);
						lblNewLabel.setText(npagina + " / " + pageMax); 
						
					}
				});
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				button.setEnabled(true); //abilita bottone avanti
				
				npagina-=1; 
				
				new operaView().vista(lblImg, textPane, titolo, npagina);
				
				lblNewLabel.setText(npagina + " / " + pageMax); 
				
				if(npagina == 1){
					btnNewButton.setEnabled(false); //disabilito bottone indietro 
					return; 
				}
				
				
			}
		});
		
		
		
		JLabel lblUser = new JLabel(user.getEmail());
		
		JLabel lblTooo = new JLabel(titolo);
		
		new operaView().vista(lblImg, textPane, titolo, 1);
		btnNewButton.setEnabled(false);
		
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTooo)
							.addPreferredGap(ComponentPlacement.RELATED, 714, Short.MAX_VALUE)
							.addComponent(lblUser))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(lblImg, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(button)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))))
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
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		

		scrollPane.setViewportView(textPane);
		contentPane.setLayout(gl_contentPane);
	}
}
