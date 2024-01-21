package petset;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JScrollPane;



public class Profile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField mailTextField;
	private Connection conne;
	private JTextField textField;
	private final DefaultTableModel urunler;
	private final DefaultTableModel ilanlar;
	private final DefaultTableModel basvuru;

	private JTable table;
	private JTable listings_table;
	private JTable basvur_table;
	private JTextField textField_1;

	
	public static void main(String[] args) throws SQLException {
		String user, pass;
		User user1 = new User(2);
        user = "postgres";
        pass = "12345";

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VT_PetSet", user, pass);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile(conn,user1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Profile(Connection conn, User user) {
		

		Object[] columnNames = {"ID","Ürün","Adet"};		
        this.urunler = new DefaultTableModel();
        urunler.setColumnIdentifiers(columnNames);
        
        Object[] columnNames1 = {"İlanID","Sahipİsim","Hayvanİsmi","Türü","Cinsiyet","Yaş","SaglikDurumu","AşiDurumu","Adres","telefon"};	
        this.ilanlar = new DefaultTableModel();
        ilanlar.setColumnIdentifiers(columnNames1);
        
        Object[] columnNames2 = {"İlanID","Sahipİsim","Hayvanİsmi","Türü","Cinsiyet","Yaş","SaglikDurumu","AşiDurumu","Adres","telefon"};		
        this.basvuru = new DefaultTableModel();
        basvuru.setColumnIdentifiers(columnNames2);
		
		conne = conn;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 698, 433);
		contentPane.add(tabbedPane);
		
		JPanel myListingsPanel = new JPanel();
		tabbedPane.addTab("İlanlarım", null, myListingsPanel, null);
		myListingsPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 673, 330);
		myListingsPanel.add(scrollPane_1);
		
		listings_table = new JTable();
		listings_table.setCellSelectionEnabled(true);
        listings_table.setModel(ilanlar);
        scrollPane_1.setViewportView(listings_table);
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 352, 54, 42);
		myListingsPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("İlan id:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 348, 84, 42);
		myListingsPanel.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Göster");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String ilan_id = textField_1.getText();
				if(!ilan_id.isEmpty()) {
				    ViewListing listing = new ViewListing(conn, user, Integer.valueOf(ilan_id));
				    listing.setVisible(true);
				    setVisible(false);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(178, 352, 117, 42);
		myListingsPanel.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Listele");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabloDoldur1(user.getId());
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(547, 352, 136, 42);
		myListingsPanel.add(btnNewButton_4);
		
		JPanel appliedListingsPanel = new JPanel();
		tabbedPane.addTab("Başvurduklarım", null, appliedListingsPanel, null);
		appliedListingsPanel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 673, 332);
		appliedListingsPanel.add(scrollPane_2);
		
		basvur_table = new JTable();
		basvur_table.setCellSelectionEnabled(true);
		basvur_table.setModel(basvuru);
        scrollPane_2.setViewportView(basvur_table);
		
		JButton btnNewButton_3 = new JButton("Listele");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabloDoldur2(user.getId());
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(10, 354, 130, 40);
		appliedListingsPanel.add(btnNewButton_3);
		
		JPanel accountInfoPanel = new JPanel();
		tabbedPane.addTab("Bilgilerim", null, accountInfoPanel, null);
		accountInfoPanel.setLayout(null);
		
		JButton saveButton = new JButton("Kaydet");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					update(user.getId());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		saveButton.setBounds(118, 180, 127, 29);
		accountInfoPanel.add(saveButton);
		
		JLabel usernameLbl = new JLabel("Kullanıcı Adı");
		usernameLbl.setBounds(27, 35, 79, 16);
		accountInfoPanel.add(usernameLbl);
		
		JLabel passwordLbl = new JLabel("Şifre");
		passwordLbl.setBounds(27, 63, 79, 16);
		accountInfoPanel.add(passwordLbl);
		
		JLabel addressLbl = new JLabel("Adres");
		addressLbl.setBounds(27, 91, 61, 16);
		accountInfoPanel.add(addressLbl);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(118, 30, 130, 26);
		accountInfoPanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(118, 58, 130, 26);
		accountInfoPanel.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(118, 86, 130, 26);
		accountInfoPanel.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel phoneNumberLbl = new JLabel("Telefon No.");
		phoneNumberLbl.setBounds(27, 119, 79, 16);
		accountInfoPanel.add(phoneNumberLbl);
		
		JLabel emailLbl = new JLabel("E-Mail");
		emailLbl.setBounds(27, 148, 79, 16);
		accountInfoPanel.add(emailLbl);
		
		phoneTextField = new JTextField();
		phoneTextField.setBounds(118, 114, 130, 26);
		accountInfoPanel.add(phoneTextField);
		phoneTextField.setColumns(10);
		
		mailTextField = new JTextField();
		mailTextField.setBounds(118, 143, 130, 26);
		accountInfoPanel.add(mailTextField);
		mailTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Hesabı Sil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteAccount(user.getId());
					login log = new login(conne);
					log.setVisible(true);
					setVisible(false);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(10, 352, 96, 42);
		accountInfoPanel.add(btnNewButton);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Ürünlerim", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Minimum ürün sayisi:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(429, 11, 148, 36);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(587, 12, 96, 39);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Ara");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				tabloDoldur(textField.getText(), user.getId());
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(587, 62, 96, 36);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 409, 383);
		panel.add(scrollPane);
		
		table = new JTable();
        table.setCellSelectionEnabled(true);
        table.setModel(urunler);
        scrollPane.setViewportView(table);
		
		
		JButton backButton = new JButton("Geri");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				MainMenu main = new MainMenu(conn, user);
				main.setVisible(true);
				setVisible(false);
			}
		});
		backButton.setBounds(587, 450, 117, 65);
		contentPane.add(backButton);
	}
	private void update(int id) throws SQLException {
			
	    String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		String address = addressTextField.getText();
		if(address.isEmpty()) {
			address = null;
		}
		String phoneNumber = phoneTextField.getText();
		if(phoneNumber.isEmpty()) {
			phoneNumber = null;
		}
		String mail = mailTextField.getText();
		if(mail.isEmpty()) {
			mail = null;
		}
		String query = "UPDATE kullanicilar SET "
		        + "user_name = ?, "
		        + "user_password = ?, "
		        + "address = COALESCE(?, address), "
		        + "phone_number = COALESCE(?, phone_number), "
		        + "email = COALESCE(?, email) WHERE user_id = ?";
			
		try {
            PreparedStatement preparedStatement = conne.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, mail);
            preparedStatement.setInt(6, id);          
            preparedStatement.executeUpdate();
            preparedStatement.close();
		} catch (SQLException e){
		    e.printStackTrace();
		    ErrorGUI error = new ErrorGUI(e.getMessage());
		    error.setVisible(true);
           //hata mesajı gui
		}
	}
    
	private void tabloDoldur(String min, int id) {
        String query = "SELECT * FROM itemCount(?) WHERE user_id = ?";
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        int min2;
        
        if(min.isEmpty()) {
        	min2 = 0;
        }
        else {
        	min2 = Integer.valueOf(min);
        }

        try {
            PreparedStatement preparedStatement = conne.prepareStatement(query);
            preparedStatement.setInt(1, min2);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            urunler.setRowCount(0);

            while (resultSet.next()) {
            	int urun_id = resultSet.getInt("ürün_id");
            	String urun_name = resultSet.getString("ürün_name");
            	int urun_count = resultSet.getInt("owned_count");
                
                        
                Object[] columnNames = {urun_id, urun_name, urun_count};
                model.addRow(columnNames);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	private void tabloDoldur2(int id) {
		String query = "SELECT i.ilan_id, k.user_name, h.hayvan_ismi, h.hayvan_türü, h.cinsiyet, h.hayvan_yaşı, h.sağlık_durumu, h.aşı_durumu, k.address, k.phone_number "
			    + "FROM ilanlar i "
			    + "JOIN kullanicilar k ON i.user_id = k.user_id "
			    + "JOIN hayvanlar h ON i.hayvan_id = h.hayvan_id "
			    + "JOIN basvurulan b ON b.ilan_id = i.ilan_id "
			    + "WHERE b.basvuran_id = ?";
        
        DefaultTableModel model = (DefaultTableModel) basvur_table.getModel();
        model.setRowCount(0);

        try {
            PreparedStatement preparedStatement = conne.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            basvuru.setRowCount(0);

            while (resultSet.next()) {

                
            	 int ilan_id = resultSet.getInt("ilan_id");
                 String nickname = resultSet.getString("user_name");
                 String hayvan_isim = resultSet.getString("hayvan_ismi");
                 String hayvan_türü1 = resultSet.getString("hayvan_türü");
                 String cinsiyet = resultSet.getString("cinsiyet");
                 int yaş  = resultSet.getInt("hayvan_yaşı");
                 String sağlık_durumu = resultSet.getString("sağlık_durumu");
                 String aşı_durumu = resultSet.getString("aşı_durumu");
                 String address1 = resultSet.getString("address");
                 String telefon = resultSet.getString("phone_number");
                 
                
                 
                 Object[] columnNames = {ilan_id,nickname,hayvan_isim,hayvan_türü1,cinsiyet,yaş,sağlık_durumu,aşı_durumu,address1,telefon};
                model.addRow(columnNames);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	private void tabloDoldur1(int id) {
        String query = "SELECT i.ilan_id, k.user_name, h.hayvan_ismi, h.hayvan_türü, h.cinsiyet, h.hayvan_yaşı, h.sağlık_durumu, h.aşı_durumu, k.address, k.phone_number "
        		+ "FROM ilanlar i "
        		+ "JOIN kullanicilar k ON i.user_id = k.user_id "
        		+ "JOIN hayvanlar h ON i.hayvan_id = h.hayvan_id "
        		+ "WHERE i.user_id = ? ";
        
        DefaultTableModel model = (DefaultTableModel) listings_table.getModel();
        model.setRowCount(0);

        try {
            PreparedStatement preparedStatement = conne.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ilanlar.setRowCount(0);

            while (resultSet.next()) {

                
            	 int ilan_id = resultSet.getInt("ilan_id");
                 String nickname = resultSet.getString("user_name");
                 String hayvan_isim = resultSet.getString("hayvan_ismi");
                 String hayvan_türü1 = resultSet.getString("hayvan_türü");
                 String cinsiyet = resultSet.getString("cinsiyet");
                 int yaş  = resultSet.getInt("hayvan_yaşı");
                 String sağlık_durumu = resultSet.getString("sağlık_durumu");
                 String aşı_durumu = resultSet.getString("aşı_durumu");
                 String address1 = resultSet.getString("address");
                 String telefon = resultSet.getString("phone_number");
                 
                
                 
                 Object[] columnNames = {ilan_id,nickname,hayvan_isim,hayvan_türü1,cinsiyet,yaş,sağlık_durumu,aşı_durumu,address1,telefon};
                model.addRow(columnNames);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 

	
	private void deleteAccount(int user_id) throws SQLException {
	    String query = String.format("DELETE FROM kullanicilar WHERE user_id = %d", user_id);
	    
	    try (Statement s = conne.createStatement()) {
	        int rowsAffected = s.executeUpdate(query);
	        conne.setAutoCommit(false);
	        conne.commit();
	        System.out.println(rowsAffected + " Hesap silindi.");
	    }
	}
}
