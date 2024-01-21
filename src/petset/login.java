package petset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection conne;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public login(Connection conn) {
		
		
		
		this.conne = conn;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LogIn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(64, 11, 167, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kullanıcı Adı:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 119, 84, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Şifre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 163, 75, 25);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("SignUp");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signin sign = new signin(conn);
				sign.setVisible(true);
				setVisible(false);

			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 273, 96, 52);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Giriş yap");
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(162, 273, 135, 52);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(92, 121, 115, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(92, 163, 115, 33);
		contentPane.add(passwordField);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Göster");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                 if(rdbtnNewRadioButton.isSelected()) {
                	 passwordField.setEchoChar((char) 0);
                 }
                 else {
                	 passwordField.setEchoChar('*');
                 }
			}
		});
		rdbtnNewRadioButton.setBounds(213, 166, 65, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 218, 256, 44);
		contentPane.add(lblNewLabel_3);
		
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String kullaniciAdi = textField.getText();
		        String sifre = new String(passwordField.getPassword());
		        int userID = giris(kullaniciAdi, sifre);
		        
		        if (userID != 0) {
		            lblNewLabel_3.setText(String.format("Giriş Başarılı... UserID: %d", userID));
		            
		            
		            User user = new User(userID);
		           
		            
		          		           		            
		            MainMenu menu = new MainMenu(conn,user);
		            menu.setVisible(true);
		            setVisible(false);		    
		            
		        } else {
		            lblNewLabel_3.setText("Kullanıcı adı veya şifre yanlış...");
		        }
		        
		    }
		});

		
	}
	private int giris(String kullaniciAdi, String sifre) {
	    int kullaniciID = 0; // -1, geçersiz kullanıcı ID'sini temsil eder

	    try {
	    	String query = "SELECT login(?, ?) as user_id";
	        PreparedStatement preparedStatement = conne.prepareStatement(query);
	        preparedStatement.setString(1, kullaniciAdi);
	        preparedStatement.setString(2, sifre);

	        ResultSet resultSet = preparedStatement.executeQuery();
	        resultSet.next();
	        kullaniciID = resultSet.getInt("user_id");
	        if (kullaniciID > 0) {	            
	            
	            System.out.println("Oturum açıldı. Kullanıcı ID: " + kullaniciID);
	        } else {
	            System.out.println("Oturum açılamadı. Kullanıcı adı veya şifre hatalı.");
	        }

	        resultSet.close();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return kullaniciID;
	}


}
