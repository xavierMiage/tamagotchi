package plugins;

import javax.swing.JDialog;

import ui.ErrorDialog;
import interfaces.IErrorPlug;

public class ErrorPlug implements IErrorPlug {
	
	@Override
	public void showError(String msg) {
		try {
			ErrorDialog dialog = new ErrorDialog(msg);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
