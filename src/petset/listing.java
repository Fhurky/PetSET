package petset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class listing extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JTable table;
    private Connection conne;
    private final DefaultTableModel ilanlar;
    private String arama;
    private JTextField textField_1;
    private JTextField textField_2;


    public listing(Connection conn, User user) {
    	
	
        this.conne = conn;
        Object[] columnNames = {"İlanID","Sahipİsim","Hayvanİsmi","Türü","Cinsiyet","Yaş","SaglikDurumu","AşiDurumu","Adres","telefon"};
        this.ilanlar = new DefaultTableModel();
        ilanlar.setColumnIdentifiers(columnNames);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 706, 619);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.menu);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBounds(120, 28, 147, 46);
        contentPane.add(textField);
        textField.setColumns(10);
        

        btnNewButton = new JButton("Main Menu");
        btnNewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	MainMenu menu = new MainMenu(conn,user);
            	menu.setVisible(true);
                setVisible(false);
            }
        });
        
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setBounds(552, 11, 115, 46);
        contentPane.add(btnNewButton);

        btnNewButton_1 = new JButton("Ara");
        
        
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_1.setBounds(277, 28, 104, 107);
        contentPane.add(btnNewButton_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(31, 183, 624, 230);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setCellSelectionEnabled(true);
        table.setModel(ilanlar);
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel = new JLabel("Hayvan sahiplenmek ister misiniz ? ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblNewLabel.setBounds(31, 424, 515, 52);
        contentPane.add(lblNewLabel);
        lblNewLabel.setVisible(false);
        
        JLabel lblNewLabel_1 = new JLabel("Şehir:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(62, 29, 70, 46);
        contentPane.add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textField_1.setBounds(120, 477, 37, 32);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        textField_1.setVisible(false);
        
        JLabel lblNewLabel_2 = new JLabel("İlan ID:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(31, 475, 104, 32);
        contentPane.add(lblNewLabel_2);
        lblNewLabel_2.setVisible(false);
        
        JButton btnNewButton_2 = new JButton("Bul");
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        btnNewButton_2.setBounds(167, 477, 70, 77);
        contentPane.add(btnNewButton_2);
        btnNewButton_2.setVisible(false);
        
        JLabel lblNewLabel_3 = new JLabel("İsim:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(31, 518, 42, 51);
        contentPane.add(lblNewLabel_3);
        lblNewLabel_3.setVisible(false);
        
        JLabel lblNewLabel_4 = new JLabel("...");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(83, 520, 142, 49);
        contentPane.add(lblNewLabel_4);
        lblNewLabel_4.setVisible(false);
        
        JButton btnNewButton_3 = new JButton("Sahiplenmek istiyorum");
        
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
        btnNewButton_3.setBounds(248, 475, 281, 77);
        contentPane.add(btnNewButton_3);
        btnNewButton_3.setVisible(false);
        
        JButton btnNewButton_4 = new JButton("ONAYLA");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		basvur(user.getId(),textField_1.getText());	
        	}
        });
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_4.setBounds(539, 477, 128, 77);
        contentPane.add(btnNewButton_4);
        btnNewButton_4.setVisible(false);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField_2.setBounds(120, 89, 147, 46);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Hayvan Türü:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(10, 85, 194, 46);
        contentPane.add(lblNewLabel_5);
        
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		arama = textField.getText(); 		
        		String arama2 = textField_2.getText();
        		tabloDoldur(arama,arama2);  
        		//-------------------------------------------
        		lblNewLabel.setVisible(true);
        		lblNewLabel_2.setVisible(true);
        		textField_1.setVisible(true);
        		btnNewButton_2.setVisible(true);
        		lblNewLabel_3.setVisible(true);
        		
        	}
        });
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblNewLabel_4.setText(bul(textField_1.getText()));
        		lblNewLabel_4.setVisible(true);
        		if(lblNewLabel_4.getText() != "...") {
        			btnNewButton_3.setVisible(true);
        		}
        	}
        });
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNewButton_4.setVisible(true);
        		
        	}
        });
    }
    private void basvur(int user_id, String ilan_id) {
    	String query = "INSERT INTO basvurulan VALUES (?,?);";
    	
    	try {
            PreparedStatement preparedStatement = conne.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, Integer.valueOf(ilan_id));
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    }

    private void tabloDoldur(String address, String hayvan_türü) {
        String query = "SELECT listing(?,?);";
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        if(address.isEmpty()) {
        	address = null;
        }
        if(hayvan_türü.isEmpty()) {
        	hayvan_türü = null;
        }

        try {
            PreparedStatement preparedStatement = conne.prepareStatement(query);
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, hayvan_türü);
            ResultSet resultSet = preparedStatement.executeQuery();

            // ilanlar modelini temizle
            ilanlar.setRowCount(0);

            while (resultSet.next()) {
                // Get the array as a string
                String arrayString = resultSet.getString(1);
                
                		
                // Remove curly braces and split values
                String[] values = arrayString.substring(1, arrayString.length() - 1).split(",");

                // Declare local variables to store the values
                String ilan_id = values[0].trim();
                String nickname = values[1].trim();
                String hayvan_isim = values[2].trim();
                String hayvan_türü1 = values[3].trim();
                String cinsiyet = values[4].trim();
                String yaş  = values[5].trim(); 
                String sağlık_durumu = values[6].trim();
                String aşı_durumu = values[7].trim();
                String address1 = values[8].trim();
                String telefon = values[9].trim();
                
               
                
                Object[] columnNames = {ilan_id,nickname,hayvan_isim,hayvan_türü1,cinsiyet,yaş,sağlık_durumu,aşı_durumu,address1,telefon};
                model.addRow(columnNames);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    private String bul(String ilan_id) {
        String isim = null;
        String sorgu = "SELECT hayvan_ismi FROM  ilanlar i,hayvanlar h WHERE ilan_id = ? AND  i.hayvan_id = h.hayvan_id";

        try {
            PreparedStatement preparedStatement = conne.prepareStatement(sorgu);
            preparedStatement.setInt(1, Integer.parseInt(ilan_id));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isim = resultSet.getString("hayvan_ismi");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isim;
    }
    }