package View;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


import Helper.Helper;
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
		
		JLabel lblAdSoyad = new JLabel("Full Name");
		lblAdSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblAdSoyad.setBounds(505, 10, 128, 24);
		w_doctor.add(lblAdSoyad);
		
		fld_dName = new JTextField();
		fld_dName.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_dName.setColumns(10);
		fld_dName.setBounds(505, 34, 196, 30);
		w_doctor.add(fld_dName);
		
		JButton btn_delDoctor = new JButton("Delete");
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length()==0) {
					Helper.showMsg("Please Select a Doctor...!");
				}else {
					if (Helper.confirm("sure")) {
						int selectID=Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					
				}
			}
		});
		btn_delDoctor.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		btn_delDoctor.setBounds(505, 302, 196, 30);
		w_doctor.add(btn_delDoctor);
		
		JButton btn_addDoctor = new JButton("Add");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length()==0||fld_dPass.getText().length()==0||fld_dTcno.getText().length()==00) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(),fld_dPass.getText(),fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoctor.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		btn_addDoctor.setBounds(505, 198, 196, 30);
		w_doctor.add(btn_addDoctor);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(505, 96, 196, 30);
		w_doctor.add(fld_dTcno);
		
		JLabel label_1 = new JLabel("Identity ID");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_1.setBounds(505, 74, 86, 24);
		w_doctor.add(label_1);
		
		fld_dPass = new JTextField();
		fld_dPass.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(505, 158, 196, 30);
		w_doctor.add(fld_dPass);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_2.setBounds(505, 136, 86, 24);
		w_doctor.add(label_2);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setFont(new Font("Yu Gothic Light", Font.PLAIN, 10));
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(505, 262, 196, 30);
		w_doctor.add(fld_doctorID);
		
		JLabel label_3 = new JLabel("User ID");
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_3.setBounds(505, 238, 86, 24);
		w_doctor.add(label_3);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 10, 485, 322);
		w_doctor.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
					
				}
				
			}
		});
		table_doctor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				
				if (e.getType()==TableModelEvent.UPDATE) {
					int selectedID=Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectedName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectedTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectedPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
					bashekim.updateDoctor(selectedID, selectedTcno, selectedPass, selectedName);
					} catch (Exception e2) {
						
					}
					
				}
				
			}
		});
	}
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel)table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}
}
