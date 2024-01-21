package petset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class shopping extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private final DefaultTableModel ürünler;
	private Connection conne;
	private JTextField textField;
	private JTextField textField_1;

	
	public shopping(Connection conn, User user) {
		this.ürünler = new DefaultTableModel();
		this.conne = conn;
		
		Object[] columnNames = {"UrunID", "Isim", "Fiyat"};
		ürünler.setColumnIdentifiers(columnNames);
		ürünler.setRowCount(0);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AlışVeriş");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel.setBounds(127, 11, 239, 70);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Eşleşen Ürünler:");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(69, 193, 137, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("User:");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 11, 57, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(Integer.toString(user.getId()));
		lblNewLabel_3.setBounds(50, 11, 85, 34);
		contentPane.add(lblNewLabel_3);
		JButton btnNewButton_1 = new JButton("AnaMenü");
		
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(376, 11, 121, 53);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 240, 264, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(ürünler);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_4 = new JLabel("Ürün ismi girin: (Örnek: Köpek maması)");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(10, 81, 305, 45);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(10, 137, 228, 45);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("ARA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arama = textField.getText();
				urunAra(arama);
			}
		});
		btnNewButton.setBounds(248, 137, 67, 45);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Almak istediğiniz ürün ID: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(290, 238, 183, 28);
		contentPane.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(284, 265, 57, 36);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Ürünü göster");
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(351, 265, 122, 36);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Satın Al");
		
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(284, 378, 213, 45);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.setVisible(false);
		
		JButton btnNewButton_4 = new JButton("ONAYLA");
		
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_4.setBounds(284, 434, 102, 90);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.setVisible(false);
		
		JButton btnNewButton_5 = new JButton("IPTAL");
		
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_5.setBounds(395, 434, 102, 90);
		contentPane.add(btnNewButton_5);
		btnNewButton_5.setVisible(false);
		
		JLabel lblNewLabel_6 = new JLabel("Ürün etiketi: ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(284, 312, 102, 36);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(376, 312, 121, 36);
		contentPane.add(lblNewLabel_7);
		
		//bul
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arama1 = textField_1.getText();
				lblNewLabel_7.setText(found(arama1));
				if(!(lblNewLabel_7.getText() == "Bulunamadı")) {
					btnNewButton_3.setVisible(true);
				}
			}
		});
		//satın al
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_4.setVisible(true);
				btnNewButton_5.setVisible(true);
			}
		});
		
		//iptal
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_4.setVisible(false);
				btnNewButton_5.setVisible(false);
				btnNewButton_3.setVisible(false);
				lblNewLabel_7.setText("");
				textField_1.setText("");
			}
		});
		//ana menü
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu menu = new MainMenu(conn, user);
            	menu.setVisible(true);
                setVisible(false);
			}
		});
		//Onayla butonu
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				urunEkle(user.getId(), Integer.valueOf(textField_1.getText()));
			}
		});
		
	}
	private void urunAra(String isim) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
	    

	    String query;
	    if ("Hepsi".equals(isim)) {
	        query = "SELECT ürün_id, isim, fiyat FROM ürünler";
	    } else {
	        query = "SELECT ürün_id, isim, fiyat FROM ürünler WHERE isim LIKE ?";
	    }

	    try {
	        PreparedStatement preparedStatement = conne.prepareStatement(query);

	        if (!"Hepsi".equals(isim)) {
	            preparedStatement.setString(1, "%" + isim + "%");
	        }

	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int urunID = resultSet.getInt("ürün_id");
	            String urunIsim = resultSet.getString("isim");
                int fiyat = resultSet.getInt("fiyat");
	            model.addRow(new Object[]{urunID, urunIsim, fiyat});
	        }

	        resultSet.close();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private String found(String urunID) {
	    String query = "SELECT isim, fiyat FROM ürünler WHERE ürün_id = ?";
	    String foundIsim = "Bulunamadı";

	    try {
	        // urunID'yi integer'a çevir
	        int urunIDInt = Integer.parseInt(urunID);

	        // PreparedStatement oluştur
	        PreparedStatement preparedStatement = conne.prepareStatement(query);
	        
	        // setInt metodu ile parametreyi ayarla
	        preparedStatement.setInt(1, urunIDInt);

	        // ResultSet elde et
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            foundIsim = resultSet.getString("isim");
	        }

	        resultSet.close();
	        preparedStatement.close();
	    } catch (SQLException | NumberFormatException e) {
	        e.printStackTrace();
	    }

	    return foundIsim;
	}
	private void urunEkle( int userId, int urunId){
        String query = String.format("INSERT INTO ürün_sahiplik (user_id, ürün_id) VALUES (%d, %d)", userId, urunId);

        try (
        	PreparedStatement preparedStatement = conne.prepareStatement(query)) {
         

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + "Ürün alındı.");
        }
	     catch (SQLException | NumberFormatException e) {
        e.printStackTrace();
    }
    }



}
