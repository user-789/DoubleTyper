import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class Listener implements KeyListener {
	
	JTextField field;
	
	Listener(JTextField field) {
		this.field = field;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		char c = e.getKeyChar();
		String text = field.getText();
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (text.length() > 0) {
				field.setText(text.substring(0, text.length()-1));
			}
		} else if (c != KeyEvent.CHAR_UNDEFINED) {
			field.setText(text + c);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
