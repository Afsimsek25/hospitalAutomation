package Helper;

import javax.swing.JOptionPane;

public class Helper {
	public static void showMsg(String str) {
		String msg;
		switch (str) {
		case "fill":
			msg="L�tfen T�m Alanalr� doldurunuz...!";
			break;
		default:
			msg=str;
		}
		
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
		
	}
}
