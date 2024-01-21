package petset;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class signin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection conne;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	public signin(Connection conn) {
		setResizable(false);
		
		this.conne = conn;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SignIn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(106, 11, 203, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("İsim*:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 105, 93, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Soyisim*:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 150, 93, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-posta*:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 195, 93, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telefon*:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 240, 93, 34);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Adres*:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 285, 93, 34);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Kullanici Adi*:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(10, 369, 152, 34);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Şifre*:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(10, 414, 80, 34);
		contentPane.add(lblNewLabel_7);
		
		textField = new JTextField();
		textField.setBounds(96, 105, 133, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(95, 150, 134, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 195, 133, 29);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(96, 240, 133, 29);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(95, 285, 134, 29);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(143, 369, 133, 29);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 414, 133, 29);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_8 = new JLabel("Şifre Tekrar*:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(10, 464, 125, 29);
		contentPane.add(lblNewLabel_8);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(143, 459, 133, 29);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("LogIn");
		btnNewButton.setFocusable(false);
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(332, 11, 89, 58);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kontrol");
		btnNewButton_1.setFocusable(false);
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(286, 414, 135, 72);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(10, 330, 411, 28);
		contentPane.add(lblNewLabel_9);
		
		JButton btnNewButton_2 = new JButton("Kayıt Oluştur");
		btnNewButton_2.setFocusable(false);
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton_2.setBounds(10, 504, 411, 72);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setVisible(false);
		
		//Login butonu
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login log = new login(conn);
				log.setVisible(true);
				setVisible(false);
				
			}
		});
		// Kontrol butonu
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(kontrol( textField_5.getText())){
					if(hepsiDoluMu(textField,textField_1, textField_2, textField_3, textField_4, textField_5)) {
						if(sifreKontrol(passwordField, passwordField_1)) {	
							btnNewButton_2.setVisible(true);
						}
						else {
							lblNewLabel_9.setText("Şifreler eşleşmedi");
							passwordField.setText("");
							passwordField_1.setText("");
						}
					}
					else {
						lblNewLabel_9.setText("Boş alan bırakmayınız...");
					}
				}
				else {
					lblNewLabel_9.setText("Bu kullanici adı kullanılamaz...");
					textField_5.setText("");
				}
			}
		});
		//Kayıt oluştur butonu
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad = textField.getText();
				String soyad = textField_1.getText();;
				String e_posta = textField_2.getText();
				String telefon = textField_3.getText();
				String adres = textField_4.getText();
				String kullaniciAdi = textField_5.getText();
				@SuppressWarnings("deprecation")
				String sifre = passwordField.getText();
				int userID = kullaniciEkle(ad, soyad, e_posta, telefon, adres, kullaniciAdi, sifre);
				lblNewLabel_9.setText(String.format("Kullanıcı oluşturuldu...UserID: %d", userID));
				btnNewButton_2.setVisible(false);
		
			}
		});
		
	}
	
	private boolean kontrol(String username) {
	    boolean userExists = false;

	    try {
	        // Kullanıcı adının 5 karakterden küçük olmaması kontrolü
	        if (username.length() < 5) {
	            return false;  // 5 karakterden küçükse hemen false döndür
	        }

	        String query = "SELECT COUNT(*) FROM kullanicilar WHERE user_name = ?";
	        PreparedStatement preparedStatement = conne.prepareStatement(query);
	        preparedStatement.setString(1, username);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int userCount = resultSet.getInt(1);
	            userExists = (userCount == 0);
	        }

	        resultSet.close();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userExists;
	}


	private boolean hepsiDoluMu(JTextField... textFields) {
	    for (JTextField textField : textFields) {
	        if (textField.getText().isEmpty()) {
	            return false; // Eğer bir tanesi bile boşsa false döndür.
	        }
	    }
	    return true; // Hiçbiri boş değilse true döndür.
	}
	private boolean sifreKontrol(JPasswordField passwordField, JPasswordField passwordField_1) {
	    String sifre = new String(passwordField.getPassword());
	    String sifreTekrar = new String(passwordField_1.getPassword());

	    return !sifre.isEmpty() && !sifreTekrar.isEmpty() && sifre.equals(sifreTekrar);
	}
	private int kullaniciEkle(String ad, String soyad, String e_posta, String telefon, String adres, String kullaniciAdi, String sifre) {
	    int kullaniciID = -1;

	    try {
	        // Kullanıcı adının 5 karakterden küçük olmaması kontrolü
	        if (kullaniciAdi.length() < 5) {
	            return kullaniciID;  // 5 karakterden küçükse hemen -1 döndür
	        }

	        String query = "INSERT INTO kullanicilar (fname, lname, email, phone_number, address, user_name, user_password) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING user_id";
	        PreparedStatement preparedStatement = conne.prepareStatement(query);
	        preparedStatement.setString(1, ad);
	        preparedStatement.setString(2, soyad);
	        preparedStatement.setString(3, e_posta);
	        preparedStatement.setString(4, telefon);
	        preparedStatement.setString(5, adres);
	        preparedStatement.setString(6, kullaniciAdi);
	        preparedStatement.setString(7, sifre);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            kullaniciID = resultSet.getInt(1);
	            System.out.println("Yeni Kullanıcı ID: " + kullaniciID);
	        }

	        resultSet.close();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return kullaniciID;
	}

}
