import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Main {
	
	static JFrame frame;
	static JPanel[] panels = new JPanel[5];
	static JTextField[] fields = new JTextField[] {
			new JTextField(),
			new JTextField(),
			new JPasswordField(),
			new JPasswordField(),
			new JTextField(),
	};
	static String[] info = new String[] {
			"Username:",
			"Email:",
			"Password:",
			"Confirm password:",
			"Enter captcha:"
	};
	
	public static void main(String[] args) {
		
		Arrays.setAll(panels, i -> new JPanel());
		
		frame = new JFrame("REGISTER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 450);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(7, 1));
		
		for (int i = 0; i < 5; i++) {
			JPanel panel = panels[i];
			panel.setLayout(new GridLayout(2, 1));
			JLabel txt = new JLabel(info[i]);
			fields[i].addKeyListener(new Listener(fields[i]));
			panel.add(txt);
			panel.add(fields[i]);
			frame.add(panel);
		}
		
		JButton loginbutton = new JButton("Register");
		loginbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {tryToRegister();}
		});

		JLabel cpchimg = new JLabel(new ImageIcon("captchas/captcha1.png"));
		frame.add(cpchimg);
		frame.add(loginbutton);
		frame.setVisible(true);


	}
	
	public static void tryToRegister() {
		boolean fill = allFilled();
		boolean pass = passwordsMatch();
		boolean cpch = validCaptcha();
		if (fill && pass && cpch) {
			JDialog success = new JDialog(Main.frame, "Success!", false);
			success.setSize(250, 80);
			success.setLocationRelativeTo(null);
			success.add(new JLabel(String.format("Login successful!")));
			success.setVisible(true);
		} else {
			JDialog success = new JDialog(Main.frame, "", true);
			success.setSize(250, 80);
			success.setLocationRelativeTo(null);
			if (!fill) {
				success.add(new JLabel("You left some fields empty, please try again"));
			} else if (!pass) {
				success.add(new JLabel("Passwords do not match, please try again"));
			} else {
				success.add(new JLabel("Failed captcha, please try again"));
			}
			for (JTextField field: fields) {
				field.setText("");
			}
			success.setVisible(true);
		}

	}
	
	static boolean allFilled() {
		return !fields[0].getText().isEmpty() &&
				!fields[1].getText().isEmpty() &&
				((JPasswordField) fields[2]).getPassword().length > 0 &&
				((JPasswordField) fields[3]).getPassword().length > 0 &&
				!fields[4].getText().isEmpty();
	}
	
	static boolean passwordsMatch() {
		String pass1 = new String(((JPasswordField) fields[2]).getPassword());
		String pass2 = new String(((JPasswordField) fields[3]).getPassword());
		return pass1.equals(pass2);
	}
	
	static boolean validCaptcha() {
		return Main.fields[4].getText().equals("quxg4h");
	}

}
