import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.EncodedKeySpec;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64.*;

import java.io.*;
import java.awt.Font;

public class des {
	
	JFileChooser fileChooser =new JFileChooser();
	StringBuilder sb= new StringBuilder();
	
	
	public void PickMe() throws Exception{
		
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			java.io.File file=fileChooser.getSelectedFile();
			
			Scanner input=new Scanner(file);
			
			while(input.hasNext()){
				sb.append(input.nextLine());
				sb.append("\n");
			
			}
			input.close();
		}
		else sb.append("Ju lutemi selektojenii nje fajll");
	}

	private JFrame frame;
	private JTextField txtFile;
	private JLabel lblCelesi;
	private JTextField txtCelesi;
	private JButton btnEnkripto;
	private JButton btnDekripto;
	private JTextField txtCiphertext;
	private JTextField txtDecript;
	private JLabel lblDesAlgoritmi;

	/**
	 * Launch the application.
	 * 
	 * 
	 */
	public static String encryptDES(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return 	Base64.getEncoder().encodeToString(encryptedBytes);
    }
	
	

	public static String decryptDES(String cipherText, String encryptionKey) throws Exception{
        Cipher cipher = Cipher.getInstance("DES");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherTextBytes =  Base64.getDecoder().decode(cipherText);
        byte[] decValue = cipher.doFinal(cipherTextBytes);
        return new String(decValue);
    }
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					des window = new des();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public des() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 655, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnFile = new JButton("OpenFile");
		btnFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				des of=new des();
				
				try{
					of.PickMe();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					
				}
				txtFile.setText(of.sb.toString());
			}
		});
		btnFile.setBounds(32, 79, 115, 29);
		frame.getContentPane().add(btnFile);
		
		txtFile = new JTextField();
		txtFile.setBounds(177, 80, 331, 26);
		frame.getContentPane().add(txtFile);
		txtFile.setColumns(10);
		
		lblCelesi = new JLabel("Celesi");
		lblCelesi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCelesi.setBounds(60, 143, 107, 20);
		frame.getContentPane().add(lblCelesi);
		
		txtCelesi = new JTextField();
		txtCelesi.setBounds(177, 141, 331, 26);
		frame.getContentPane().add(txtCelesi);
		txtCelesi.setColumns(10);
		
		//btnEncrypt
		
		btnEnkripto = new JButton("Enkripto");
		btnEnkripto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEnkripto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String plaintext,key;
				plaintext=txtFile.getText();
				key=txtCelesi.getText();
				try{
					String ciphertext=encryptDES(plaintext,key);
					txtCiphertext.setText(ciphertext);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
					
			}
		});
		btnEnkripto.setBounds(283, 245, 115, 29);
		frame.getContentPane().add(btnEnkripto);
		
		
		//btnDecrypt
		
		btnDekripto = new JButton("Dekripto");
		btnDekripto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDekripto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String ciphertext,key;
				ciphertext=txtCiphertext.getText();
				key=txtCelesi.getText();
				try{
					String plaintext=decryptDES(ciphertext,key);
					txtDecript.setText(plaintext);
					
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
					
				}
				
				
				
				
			}
		});
		btnDekripto.setBounds(283, 362, 115, 29);
		frame.getContentPane().add(btnDekripto);
		
		txtCiphertext = new JTextField();
		txtCiphertext.setBounds(177, 194, 331, 26);
		frame.getContentPane().add(txtCiphertext);
		txtCiphertext.setColumns(10);
		
		JLabel lblEnkriptuar = new JLabel("Teksti i enkriptuar");
		lblEnkriptuar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnkriptuar.setBounds(32, 183, 132, 46);
		frame.getContentPane().add(lblEnkriptuar);
		
		JLabel lblDekriptuar = new JLabel("Teksti i dekriptuar");
		lblDekriptuar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDekriptuar.setBounds(32, 290, 178, 60);
		frame.getContentPane().add(lblDekriptuar);
		
		txtDecript = new JTextField();
		txtDecript.setBounds(177, 308, 331, 26);
		frame.getContentPane().add(txtDecript);
		txtDecript.setColumns(10);
		
		lblDesAlgoritmi = new JLabel("DES Algoritmi");
		lblDesAlgoritmi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDesAlgoritmi.setBounds(263, 21, 150, 29);
		frame.getContentPane().add(lblDesAlgoritmi);
	}
}
