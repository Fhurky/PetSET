package petset;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddPet extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblNewLabel_7;
	private Connection conne;
	

	public AddPet(Connection conn, User user) {
		
		this.conne = conn;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hayvan Bilgilerini giriniz:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 23, 252, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hayvan türü:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 100, 85, 29);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(105, 100, 86, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Yaş:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 158, 65, 29);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 158, 86, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Cinsiyet:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 216, 65, 29);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Sağlık Durumu:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(10, 274, 104, 29);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Aşı Durumu:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(10, 332, 86, 29);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Hayvan ismi:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(10, 390, 86, 29);
		contentPane.add(lblNewLabel_6);
		
		textField_2 = new JTextField();
		textField_2.setBounds(105, 216, 86, 29);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(105, 274, 86, 29);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(105, 332, 86, 29);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(105, 390, 86, 29);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		
		
		JButton btnNewButton = new JButton("Hayvan ekle");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(227, 240, 148, 57);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ONAYLA");
		btnNewButton_1.setVisible(false);
		
		btnNewButton_1.setBounds(227, 308, 148, 53);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("IPTAL");
		
		btnNewButton_2.setVisible(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(227, 372, 148, 53);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ana Menü");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu menu = new MainMenu(conn,user);
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBounds(262, 26, 113, 45);
		contentPane.add(btnNewButton_3);
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(227, 100, 113, 29);
		contentPane.add(lblNewLabel_7);
		
		//Onayla butonu
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hayvanTuru = textField.getText();
				String yas = textField_1.getText();
				String cinsiyet = textField_2.getText();
				String saglik = textField_3.getText();
				String asi = textField_4.getText();
				String isim = textField_5.getText();
                String sahip_id = Integer.toString(user.getId());
				makeAdverting(isim,hayvanTuru,yas,cinsiyet,saglik,asi,sahip_id);
			}
		});
		//İptal butonu
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				btnNewButton_1.setVisible(false);
				btnNewButton_2.setVisible(false);
			}
		});		
		//İlan ver butonu
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setVisible(true);
			    btnNewButton_2.setVisible(true);
			}
		});
	}
	private void makeAdverting(String hayvanIsmi, String hayvanTuru, String yas, String cinsiyet, String saglik,String asi, String sahip_id) {
	    String query = "INSERT INTO hayvanlar (hayvan_ismi, hayvan_türü, hayvan_yaşı, cinsiyet, sağlık_durumu, aşı_durumu, sahip_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    int yas1 = 0,sahip_id1 = 0;
	   if(hayvanIsmi.isEmpty()) {
		   hayvanIsmi = null;
	   }
	   if(hayvanTuru.isEmpty()) {
		   hayvanTuru = null;
	   }
	   
	    
       if(yas.isEmpty()) {
    	    yas1 = 0;
       }
       else {
    	   yas1= Integer.valueOf(yas);
       }
       if(sahip_id.isEmpty()) {
    	    sahip_id1 = 0;
       }
       else {
    	   sahip_id1 = Integer.valueOf(sahip_id);
       }
	    try (PreparedStatement preparedStatement = conne.prepareStatement(query)) {
	    	conne.setAutoCommit(false);
	        preparedStatement.setString(1, hayvanIsmi);
	        preparedStatement.setString(2, hayvanTuru);
	        preparedStatement.setInt(3, yas1);
	        preparedStatement.setString(4, cinsiyet);
	        preparedStatement.setString(5, saglik);
	        preparedStatement.setString(6, asi);
	        preparedStatement.setInt(7, sahip_id1);

	        preparedStatement.executeUpdate();
	        conne.commit();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        ErrorGUI error = new ErrorGUI(e.getMessage());
	        error.setVisible(true);
	    }
	}


}
