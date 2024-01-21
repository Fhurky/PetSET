package petset;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewListing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private Connection conn;
	private int ilan_id;
    private DefaultTableModel kullanicilar;
	/**
	 * Launch the application.
	 */

	public ViewListing(Connection conn,User user, int ilan_id) {
		
		Object[] columnNames = {"ID", "Username", "PhoneNumber", "Adres"};
		this.kullanicilar = new DefaultTableModel();
		kullanicilar.setColumnIdentifiers(columnNames);
		
		this.conn = conn;
		this.ilan_id = ilan_id;

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 519, 218);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
        table.setModel(kullanicilar);
        scrollPane.setViewportView(table);

		
		textField = new JTextField();
		textField.setBounds(45, 240, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Başvuruyu Kabul Et");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user_id = textField.getText();
				//buraya hayvanın userid değitirien fonksiyonu çağıran query ekle
				//buraya ilanı silen çağrıyı ekle	
				if(!user_id.isEmpty()) {
					basvuru_kabul(Integer.valueOf(user_id));
					delete_listing();	
				}						
			}
		});
		btnNewButton.setBounds(141, 240, 125, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 240, 25, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Geri");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile profil = new Profile(conn,user);
				setVisible(false);
				profil.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(450, 240, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Listele");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basvurulariListele();
			}
		});
		btnNewButton_2.setBounds(351, 240, 89, 23);
		contentPane.add(btnNewButton_2);
	}
	private void basvurulariListele() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);


        String query = "SELECT user_id, user_name, phone_number, address FROM kullanicilar k, basvurulan b WHERE ilan_id = ? and k.user_id = b.basvuran_id";
        try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, ilan_id);
            ResultSet r2 = preparedStatement.executeQuery();
            kullanicilar.setRowCount(0);
            while (r2.next()) {
            	
            	int user_id = r2.getInt(1);
            	String user_name = r2.getString(2);
            	String Pnumber = r2.getString(3);
            	String adres = r2.getString(4);


                Object[] columnNames = {user_id, user_name, Pnumber, adres};
                model.addRow(columnNames);
            }

            r2.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	private void delete_listing() {

        String query = "DELETE FROM ilanlar WHERE ilan_id = ?";      
        try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, ilan_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	private void basvuru_kabul(int user_id){

        String query = "UPDATE hayvanlar SET sahip_id = ? WHERE hayvan_id IN (SELECT hayvan_id FROM ilanlar WHERE ilan_id = ?)" ;
        
        try {
        	
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, ilan_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
