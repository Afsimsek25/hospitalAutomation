package View;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Helper.Helper;
import Model.Bashekim;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;

public class LoginGUI extends JFrame {
	private JPanel w_pane;
	private JTextField fld_identity;
	private JTextField fld_password;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn=new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hospital Automation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hospitalLogo.png")));
		lbl_logo.setBounds(193, 10, 100, 55);
		lbl_logo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Welcome to the Hospital Managament System");
		lblNewLabel.setBounds(57, 75, 357, 27);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(57, 132, 357, 205);
		w_pane.add(w_tabpane);
		
		JPanel w_hastalogin = new JPanel();
		w_tabpane.addTab("Patient Enterance", null, w_hastalogin, null);
		w_hastalogin.setLayout(null);
		
		JLabel lblTcNo = new JLabel("Identity ID :");
		lblTcNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTcNo.setBounds(10, 10, 90, 24);
		w_hastalogin.add(lblTcNo);
		
		JLabel lblifre = new JLabel("Password : ");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblifre.setBounds(10, 44, 86, 24);
		w_hastalogin.add(lblifre);
		
		fld_identity = new JTextField();
		fld_identity.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_identity.setBounds(146, 16, 196, 19);
		w_hastalogin.add(fld_identity);
		fld_identity.setColumns(10);
		
		fld_password = new JTextField();
		fld_password.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_password.setColumns(10);
		fld_password.setBounds(146, 49, 196, 19);
		w_hastalogin.add(fld_password);
		
		JButton btn_register = new JButton("Register");
		btn_register.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_register.setBounds(28, 126, 129, 42);
		w_hastalogin.add(btn_register);
		
		JButton btn_patientLogin = new JButton("Login");
		btn_patientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_patientLogin.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		btn_patientLogin.setBounds(187, 126, 129, 42);
		w_hastalogin.add(btn_patientLogin);
		
		JPanel w_doctorLogin = new JPanel();
		w_tabpane.addTab("Doctor Enterance", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(146, 16, 196, 19);
		w_doctorLogin.add(fld_doctorTc);
		
		JLabel lblifre_1 = new JLabel("Password : ");
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblifre_1.setBounds(10, 49, 86, 24);
		w_doctorLogin.add(lblifre_1);
		
		JLabel lblTcNo_1 = new JLabel("Identity ID :");
		lblTcNo_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTcNo_1.setBounds(10, 10, 90, 24);
		w_doctorLogin.add(lblTcNo_1);
		
		JButton btn_doctorLogin = new JButton("Login");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length()==0 || fld_doctorPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con = conn.connectionDb();
						Statement st = con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user");
						
						while(rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))&&fld_doctorPass.getText().equals(rs.getString("password"))) {
								Bashekim bhekim=new Bashekim();
								bhekim.setId(rs.getInt("id"));
								bhekim.setPassword("password");
								bhekim.setTcno(rs.getString("tcno"));
								bhekim.setName(rs.getString("name"));
								bhekim.setType(rs.getString("type"));
								BashekimGUI bGUI=new BashekimGUI(bhekim);
								bGUI.setVisible(true);
								dispose();
								
								
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorLogin.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		btn_doctorLogin.setBounds(10, 126, 332, 42);
		w_doctorLogin.add(btn_doctorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(146, 55, 196, 19);
		w_doctorLogin.add(fld_doctorPass);
	}
}
