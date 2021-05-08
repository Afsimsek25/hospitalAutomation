package Helper;

import javax.swing.JOptionPane;

public class Helper {
	public static void showMsg(String str) {
		String msg;
		switch (str) {
		case "fill":
			msg="Lütfen Tüm Alanalrý doldurunuz...!";
			break;
		default:
			msg=str;
		}
		
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
		
	}
}
