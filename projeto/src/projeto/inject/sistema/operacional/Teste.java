package projeto.inject.sistema.operacional;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Teste extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String testeConexaoPing3(String teste) {			
		Process p;
		String resposta = "";
		try {			
			int timeout = 2000;
			  InetAddress[] addresses = InetAddress.getAllByName(teste);
			  for (InetAddress address : addresses) {
			    if (address.isReachable(timeout))
			    	resposta = resposta + "\n" + "Status para o IP %s esta OK".replaceAll("%s", address.toString());			      
			    else
			    	resposta = resposta + "\n" + "Status para o IP %s esta OK".replaceAll("%s", address.toString());
			  }
			
		} catch (IOException e) {
			resposta = "Não Foi possivel conectar";
		}
		return resposta;

	}
	
	public String testeConexaoPing2(String teste) {			
		Process p;
		String resposta = "";
		try {
			p = Runtime.getRuntime().exec("ping " + rotinaListaBranca(teste));
			p.waitFor();
			BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));
			while (in.ready()) {
				resposta = resposta + "\n" + in.readLine();
			}
		} catch (IOException | InterruptedException e) {
			resposta = "Não foi possivel conectar";
		}
		return resposta;

	}
	
	public String rotinaListaBranca(String texto) {		
		texto = texto.replaceAll(" ", "");		
		texto = texto.replaceAll("&", "");		
		return texto;
	}
	
	public String testeConexaoPing1(String teste) {			
		Process p;
		String resposta = "";
		try {
			p = Runtime.getRuntime().exec("ping " + teste);
			p.waitFor();
			BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));
			while (in.ready()) {
				resposta = resposta + "\n" + in.readLine();
			}
		} catch (IOException | InterruptedException e) {
			resposta = "Não foi possivel conectar";
		}
		return resposta;

	}

	/**
	 * Create the frame.
	 */
	public Teste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Quest\u00E3o 2");
		rdbtnNewRadioButton_1.setBounds(150, 5, 109, 23);
		rdbtnNewRadioButton_1.setActionCommand("2");
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Quest\u00E3o 3");
		rdbtnNewRadioButton_2.setBounds(320, 5, 109, 23);
		rdbtnNewRadioButton_2.setActionCommand("3");
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Quest\u00E3o 1");
		rdbtnNewRadioButton.setBounds(6, 5, 109, 23);
		rdbtnNewRadioButton.setActionCommand("1");
		contentPane.add(rdbtnNewRadioButton);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnNewRadioButton_1);
		grupo.add(rdbtnNewRadioButton_2);
		grupo.add(rdbtnNewRadioButton);
		
		textField = new JTextField();
		textField.setBounds(6, 61, 418, 20);
		contentPane.add(textField);
		textField.setColumns(10);			
		
		JTextArea textArea = new JTextArea();
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(6, 142, 423, 108);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		JButton btnNewButton = new JButton("Teste conex\u00E3o");
		btnNewButton.setBounds(135, 92, 130, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){
					String selecionado = grupo.getSelection().getActionCommand();
					String text = textField.getText();
					if (selecionado.equals("1")) {						
						textArea.setText(testeConexaoPing1(text));
					} else if (selecionado.equals("2")) {
						textArea.setText(testeConexaoPing2(text));
					} else {
						textArea.setText(testeConexaoPing3(text));
					}
        		}  
		    });  			
	}
}
