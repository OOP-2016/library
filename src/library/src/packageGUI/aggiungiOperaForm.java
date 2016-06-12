package packageGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packageView.ricercaView;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class aggiungiOperaForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aggiungiOperaForm frame = new aggiungiOperaForm();
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
	public aggiungiOperaForm() {
		super("Library"); 
		aggiungiOperaForm finestra = this; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 289, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAggiungiOpera = new JLabel("AGGIUNGI OPERA");
		
		JLabel lblTitolo = new JLabel("TITOLO");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblAutore = new JLabel("AUTORE");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblAnnoDiPubblicazione = new JLabel("ANNO DI PUBBLICAZIONE");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNumeroTotaleDi = new JLabel("NUMERO TOTALE DI PAGINE");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new ricercaView().aggiungiOpera(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), finestra); 
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
								.addComponent(lblAnnoDiPubblicazione, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
								.addComponent(lblAutore, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
								.addComponent(lblNumeroTotaleDi, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTitolo)
								.addComponent(textField)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addComponent(lblAggiungiOpera))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(87)
							.addComponent(btnAggiungi)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAggiungiOpera)
					.addGap(18)
					.addComponent(lblTitolo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(lblAutore)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAnnoDiPubblicazione)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(lblNumeroTotaleDi)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(btnAggiungi)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
