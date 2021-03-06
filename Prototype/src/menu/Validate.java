package menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Validate checks all the user input when eiter modifying or creating an account and checks if it is valid
 * @author elliot
 *
 */
public class Validate {

	boolean realNameValid = false;;
	boolean usernameValid = false;
	boolean passwordValid = false;
	boolean passwordSame = false;
	
	/**
	 * Return whether the attempted created a new user with realName,username,password,retypePassword.
	 * It calls the other validate methods in this class to check each by itself if they are valid
	 * So the user will know where the error is 
	 * @param realName
	 * @param username
	 * @param password
	 * @param retypePassword
	 * @return
	 */

	public boolean validateCreateAccount(String realName, String username,
			String password, String retypePassword) {
		if (isRealNameValid(realName) && isUserNameValid(username)
				&& isPasswordValid(password)
				&& arePasswordSame(password, retypePassword)) {
			return true;
		}
		return false;
	}
	/**
	 * checks if realName follows correct input only letters
	 * @param realName
	 * @return
	 */

	public boolean isRealNameValid(String realName) {
		// checks if more than one word otherwise just checks one word
		if (realName.indexOf(" ") > 0) {
			String realNameSplit[] = realName.split(" ");
			System.out.println(realNameSplit.length);
			for (int i = 0; i < realNameSplit.length; i++) {
				// latin characters \\p{L}]
				// dont need to use matching cause will only match specific
				if (realNameSplit[i].matches("\\p{L}+")) {
					realNameValid = true;

				} else {
					return false;
				}
			}

		} else {
			if (realName.matches("\\p{L}+")) {
				realNameValid = true;
			} else {
				return false;
			}
		}
		return realNameValid;

	} // create indentical method without input as getters

	
	/**
	 * checks if username is valid only contains letters and numbers
	 * @param username
	 * @return
	 */
	public boolean isUserNameValid(String username) {
		// latin characters problems\\p{L}
		// split i
		String whitespaceInUserName[] = username.split(" ");
		if (username.matches("[\\p{L}\\s\\d]+") && username.length() >= 6
				&& whitespaceInUserName.length == 1) {
			usernameValid = true;
			System.out.println("username is valid");
			return true;
		}
		return false;

	}

	
	/**
	 * checks if the password is valid that it contains at least 8 letters a uppercase,lowercase,digit, and a special character
	 * This is done using pattern matching and regular expressions.
	 * @param password
	 * @return
	 */
	public boolean isPasswordValid(String password) {
		Pattern number = Pattern.compile("[0-9]{1,}");
		Pattern letterLowerCase = Pattern.compile("[a-z]");
		Pattern letterUpperCase = Pattern.compile("[A-Z]");
		Pattern specialCharacter = Pattern.compile("[\\p{Punct}]");
		Matcher hasNumber = number.matcher(password);
		Matcher hasLetterLowerCase = letterLowerCase.matcher(password);
		Matcher hasLetterUpperCase = letterUpperCase.matcher(password);
		Matcher hasSpecialCharacter = specialCharacter.matcher(password);

		if (((password.length() >= 8) && password
				.matches("[0-9a-zA-Z\\p{Punct}]{8,}"))
				&& ((hasNumber.find() && hasLetterLowerCase.find()) && hasLetterUpperCase
						.find()) && hasSpecialCharacter.find()) {
			passwordValid = true;
			return true;
		}
		return false;
	}

	
	/**
	 * this checks if the passwords submitted are the same.
	 * @param password
	 *   password is of type string
	 * @param retypePassword
	 * 		
	 * @return
	 */
	public boolean arePasswordSame(String password, String retypePassword) {
		System.out.println(password.equals(retypePassword));

		if (password.equals(retypePassword)) {
			passwordSame = true;
			return true;
		}
		return false;
	}
	public boolean isRealNameValid() {
		return realNameValid;
	}
	public boolean isUserNameValid() {
		return usernameValid;
	}

	public boolean arePasswordSame() {
		return passwordSame;
	}
	public boolean isPasswordValid() {
		return passwordValid;
	}

}
