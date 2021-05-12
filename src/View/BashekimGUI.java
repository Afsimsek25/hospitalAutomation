package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.graalvm.compiler.nodes.NodeView.Default;

import Model.Bashekim;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BashekimGUI extends JFrame {
	static Bashekim bashekim = new Bashekim();

	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel=null;
	private Object[] doctorData=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		doctorModel=new DefaultTableModel();
		Object[] colDoctorName=new Object[4];
		colDoctorName[0]="ID";
		colDoctorName[1]="Full Name";
		colDoctorName[2]="Identity ID";
		colDoctorName[3]="Password";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData=new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		setTitle("Hastane Y\u00F6netim sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome, dear : "+bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 381, 22);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnNewButton.setBounds(641, 10, 85, 21);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 84, 716, 369);
		w_pane.add(w_tab);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setToolTipText("");
		w_doctor.setLayout(null);
		w_tab.addTab("Doctor Management", null, w_doctor, null);
		
		JLabel lblAdSoyad = new JLabel("Name Surname");
		lblAdSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblAdSoyad.setBounds(505, 10, 128, 24);
		w_doctor.add(lblAdSoyad);
		
		fld_dName = new JTextField();
		fld_dName.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_dName.setColumns(10);
		fld_dName.setBounds(505, 34, 196, 30);
		w_doctor.add(fld_dName);
		
		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		btn_delDoctor.setBounds(505, 272, 196, 30);
		w_doctor.add(btn_delDoctor);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_addDoctor.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		btn_addDoctor.setBounds(505, 179, 196, 30);
		w_doctor.add(btn_addDoctor);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(505, 86, 196, 30);
		w_doctor.add(fld_dTcno);
		
		JLabel label_1 = new JLabel("Identity ID");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_1.setBounds(505, 62, 86, 24);
		w_doctor.add(label_1);
		
		fld_dPass = new JTextField();
		fld_dPass.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(505, 139, 196, 30);
		w_doctor.add(fld_dPass);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_2.setBounds(505, 115, 86, 24);
		w_doctor.add(label_2);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(505, 232, 196, 30);
		w_doctor.add(fld_doctorID);
		
		JLabel label_3 = new JLabel("User ID");
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_3.setBounds(505, 208, 86, 24);
		w_doctor.add(label_3);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 10, 485, 322);
		w_doctor.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
	}
}
