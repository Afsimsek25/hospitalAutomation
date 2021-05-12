package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void optionPaneChangeButtonText() {
		 UIManager.put("OptionPane.cancelButtonText", "Cancel");  // ingilizce kalmasýný istiyorum ancak deðiþtirme opsiyonumun bulunmasýný istiyorum...
		 UIManager.put("OptionPane.noButtonText", "No");
		 UIManager.put("OptionPane.okButtonText", "Ok");
		 UIManager.put("OptionPane.yesButtonText", "Yes");
		 
		
	}
	public static void showMsg(String str) {
		String msg;
		switch (str) {
		case "fill":
			msg="Pleasee fill in all fields...!";
			break;
		default:
			msg=str;
			case"success":
			msg="Transaction is Successfull";
			break;
		}
		
		
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
		
	}
	public static boolean confirm(String str) {
		String msg;
        optionPaneChangeButtonText();
		switch (str) {
		case "sure":
			msg="Are you sure?";
			
			break;

		default:
			msg=str;
			break;
		}
		int res=JOptionPane.showConfirmDialog(null, msg,"Athention !", JOptionPane.YES_NO_OPTION);
		if (res==0) {
			return true;
		}else {
			return false;
		}
	}
}
