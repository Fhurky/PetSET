package petset;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) throws SQLException {
		String user, pass;
		User user1 = new User(2);
        user = "postgres";
        pass = "12345";

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VT_PetSet", user, pass);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu(conn,user1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenu(Connection conn, User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 327, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton profileButton = new JButton("Profil");
		profileButton.setFocusable(false);
		profileButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Profile profil = new Profile(conn,user);
				profil.setVisible(true);
				setVisible(false);
			}
		});
		
		profileButton.setBounds(189, 71, 112, 43);
		contentPane.add(profileButton);
		
		JButton listingsButton = new JButton("İlanlar");
		listingsButton.setFocusable(false);
		listingsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listing ilanlar = new listing(conn, user);
				ilanlar.setVisible(true);
				setVisible(false);				
			}
		});		
		listingsButton.setBounds(10, 125, 112, 43);
		contentPane.add(listingsButton);
		
		JButton logOutButton = new JButton("Oturumu Kapat");
		logOutButton.setFocusable(false);
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login log = new login(conn);
				log.setVisible(true);
			    setVisible(false);
			    
			}
		});
		logOutButton.setBounds(10, 233, 190, 43);
		contentPane.add(logOutButton);
		
		JButton exitButton = new JButton("Çıkış Yap");
		exitButton.setFocusable(false);
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                System.exit(0);
			}
		});
		exitButton.setBounds(10, 287, 190, 43);
		contentPane.add(exitButton);
		
		JButton createListingButton = new JButton("Hayvan Ekle");
		createListingButton.setFocusable(false);
		createListingButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		createListingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPet ilanVer = new AddPet(conn, user);
				ilanVer.setVisible(true);
				setVisible(false);
			}
		});
		createListingButton.setBounds(10, 71, 112, 43);
		contentPane.add(createListingButton);
		
		JButton shopButton = new JButton("Alışveriş");
		shopButton.setFocusable(false);
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopping shop = new shopping(conn, user);
				shop.setVisible(true);
				setVisible(false);
			}
		});
		shopButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shopButton.setBounds(10, 179, 112, 43);
		contentPane.add(shopButton);
		
		JLabel lblNewLabel = new JLabel("Ana Menü");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(75, 8, 190, 52);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("İlan Ver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakeListing makeListing = new MakeListing(conn,user);
				makeListing.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(189, 125, 112, 43);
		contentPane.add(btnNewButton);
	}
}
