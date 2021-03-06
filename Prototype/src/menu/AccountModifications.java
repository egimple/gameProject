package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import gameplay.PlayerInfo;

/**
 * AccountModfications extends JFrame and displays the Gui for modifying a users
 * account. The user can only modify his realname,and password
 * 
 * @author Elliot Gimple
 */
public class AccountModifications extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel userRealNameLabel = new JLabel("Real Name");
	private JTextField realNameText = new JTextField();
	private JLabel passwordLabel = new JLabel("Password");
	private JPasswordField passwordText = new JPasswordField();
	private JLabel verifyPasswordLabel = new JLabel("Verify Password");
	private JPasswordField verifyPasswordText = new JPasswordField();
	private JButton modifyButton = new JButton("Modify");
	private JButton backButton = new JButton("Back");
	private String realName;
	private String username;
	private String password;
	private String retypePassword;

	private JPanel panelA;

	/**
	 * AccountModifications creates the jpanel for displaying the gui.By first
	 * deleteing the old gui and now creating the new one. It calls FileWriting
	 * to check if the account modifications are valid
	 * 
	 * @param panel
	 */

	public AccountModifications(JPanel panel) {

		panel.removeAll();

		panel.setLayout(null);

		AccountMenu.setFrameTitle("Account modification");

		userRealNameLabel.setBounds(10, 10, 80, 25);
		panel.add(userRealNameLabel);

		realNameText.setBounds(130, 10, 160, 25);
		panel.add(realNameText);

		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText.setBounds(130, 40, 160, 25);
		panel.add(passwordText);

		verifyPasswordLabel.setBounds(10, 70, 160, 25);
		panel.add(verifyPasswordLabel);

		verifyPasswordText.setBounds(130, 70, 160, 25);
		panel.add(verifyPasswordText);

		modifyButton.setBounds(165, 105, 80, 25);
		panel.add(modifyButton);

		backButton.setBounds(80, 105, 80, 25);
		panel.add(backButton);

		panel.repaint();
		panelA = panel;

		modifyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.out.println("action comman" + e.getActionCommand());

				realName = realNameText.getText();
				String username = PlayerInfo.getUsername();
				password = String.valueOf(passwordText.getPassword());
				retypePassword = String.valueOf(verifyPasswordText
						.getPassword());
				System.out.println(realName);
				String error = "Error";
				FileWriting writing = new FileWriting();
				writing.openFile();

				if (writing.checkIfValid(realName, username, password,
						retypePassword)) {
					try {
						writing.overwriteToFileString(realName, username,
								password, retypePassword);
						JOptionPane.showMessageDialog(null,
								"Modification Complete.", "Success!",
								JOptionPane.INFORMATION_MESSAGE);
						modifyButton.setEnabled(true);
						getContentPane().removeAll();
						new MainMenu(panelA, username);
					} catch (Exception e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null,
								"Could not write to file.", password,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else {
					if (!writing.isRealNameValid()) {
						JOptionPane
								.showMessageDialog(
										null,
										"Incorrect input. Realname should be two words.",
										error, JOptionPane.INFORMATION_MESSAGE);
					} else if (!writing.isUserNameValid()) {

						JOptionPane
								.showMessageDialog(
										null,
										"Username should consist of one word that is at least 6 characters long.",
										error, JOptionPane.INFORMATION_MESSAGE);

					} else if (!writing.isPasswordValid()) {

						JOptionPane
								.showMessageDialog(
										null,
										"Password should be atleast 8 characters long containing at least one upper case character and number.",
										error, JOptionPane.INFORMATION_MESSAGE);

					} else if (!writing.arePasswordSame()) {
						JOptionPane.showMessageDialog(null,
								"Passwords do not match.", error,
								JOptionPane.INFORMATION_MESSAGE);

					}

					return;
				}

			}
		});

		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				getContentPane().removeAll();
				new MainMenu(panelA, username);
			}
		});

		panel.setVisible(true);

	}

}