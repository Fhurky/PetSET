package petset;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class MakeListing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
    private Connection conne;
	private final DefaultTableModel hayvanlar;
    

	public MakeListing(Connection conn, User user) {
		
        Object[] columnNames = {"Hayvan_id","Hayvan_ismi","Hayvan_türü"};
        this.hayvanlar = new DefaultTableModel();
        hayvanlar.setColumnIdentifiers(columnNames);
        
		
		conne = conn;

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hayvanlarım:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 11, 156, 61);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 367, 401);
		contentPane.add(scrollPane);
		
		table = new JTable();
        table.setCellSelectionEnabled(true);
        table.setModel(hayvanlar);
        scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Hayvn_ID:");
		lblNewLabel_1.setBounds(392, 92, 58, 27);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(460, 89, 41, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Kontrol");
		btnNewButton.setFocusable(false);
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(387, 130, 189, 38);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Hayvan İsmi:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(387, 179, 189, 27);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("İlan Ver");
		btnNewButton_1.setVisible(false);
		btnNewButton_1.setFocusable(false);
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(387, 293, 189, 61);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Onayla");
		btnNewButton_2.setVisible(false);
		btnNewButton_2.setFocusable(false);
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(387, 365, 189, 61);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("İptal");
		btnNewButton_3.setVisible(false);
		btnNewButton_3.setFocusable(false);
		
		//iptal butonu
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(387, 437, 189, 61);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Listele");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabloDoldur(user.getId());
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(176, 13, 189, 61);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("MainMenu");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu menu = new MainMenu(conn,user);
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_5.setBounds(392, 15, 184, 57);
		contentPane.add(btnNewButton_5);
		
		//İlan ver butonu
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2.setVisible(true);
				btnNewButton_3.setVisible(true);
			}
		});
		//Onayla butonu
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ilanVer(user.getId(),Integer.valueOf(textField.getText()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   String hayvan_isim = getHayvanAdi(Integer.parseInt(textField.getText()));
			   lblNewLabel_2.setText("Hayvan İsmi:  "+hayvan_isim);
			   if(isHayvanExists(Integer.parseInt(textField.getText()))) {
				   btnNewButton_1.setVisible(true);
			   }
			   else {
				   lblNewLabel_2.setText("Hayvan bulunamadı...");
				   btnNewButton_1.setVisible(false);
				   btnNewButton_2.setVisible(false);
				   btnNewButton_3.setVisible(false);
			   }			   
			}
		});
	}
	
	private String getHayvanAdi(int hayvanId) {
        String hayvanAdi = null;
        String query = "SELECT hayvan_ismi FROM hayvanlar WHERE hayvan_id = ?";

        try (PreparedStatement preparedStatement = conne.prepareStatement(query)) {
            preparedStatement.setInt(1, hayvanId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    hayvanAdi = resultSet.getString("hayvan_ismi");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hayvanAdi;
    }
	private boolean isHayvanExists(int hayvanId) {
	    boolean result = false;
	    String query = "SELECT COUNT(*) FROM hayvanlar WHERE hayvan_id = ?";

	    try (PreparedStatement preparedStatement = conne.prepareStatement(query)) {
	        preparedStatement.setInt(1, hayvanId);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                result = count > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	private void tabloDoldur(int id) {
	    String query = String.format("SELECT hayvan_id, hayvan_ismi, hayvan_türü FROM hayvan WHERE sahip_id = %s", Integer.toString(id));

	    try (PreparedStatement p2 = conne.prepareStatement(query)) {
	        ResultSet r2 = p2.executeQuery();

	        hayvanlar.setRowCount(0);

	        while (r2.next()) {
	            String hayvanID = r2.getString(1);
	            String isim = r2.getString(2);
	            String tur = r2.getString(3);

	            Object[] hayvanlar1 = {hayvanID, isim, tur};
	            hayvanlar.addRow(hayvanlar1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	private void ilanVer(int id, int hayvan_id) throws SQLException {
		String query = String.format("INSERT INTO ilanlar(user_id, hayvan_id) VALUES (%d,%d);",id,hayvan_id );
		
		try (Statement s = conne.createStatement()) {
	        int rowsAffected = s.executeUpdate(query);
	        conne.setAutoCommit(false);
	        conne.commit();
	        System.out.println(rowsAffected + " satır güncellendi.");
	    }
	}

}
