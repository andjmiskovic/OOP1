package functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import functions.Unique;

public class Validations {
	public static boolean valideName(String Name) {
		if (Name.length() == 0)
			return false;
		if (Name.length() > 40)
			return false;
		if (Name.contains(","))
			return false;
		return true;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean valideUserName(String UserName) {
		if (!valideName(UserName))
			return false;
		if (!Unique.isUniqueUserName(UserName))
			return false;
		return true;
	}

	public static boolean valideLBO(String LBO) {
		if (isNumeric(LBO))
			return false;
		if (LBO.length() != 11)
			return false;
		if (!Unique.isUniqueLBO(LBO))
			return false;
		return true;
	}

	public static boolean validePhoneNumber(String phoneNumber) {
		String regex = "[0-9*#+() -]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		if (!matcher.matches())
			return false;
		if (phoneNumber.length() < 8)
			return false;
		return true;
	}
	
	public static boolean valideAdress(String adress) {
		String regex = "[a-zA-Z/0-9* -]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(adress);
		if (adress.length() == 0)
			return false;
		if (!matcher.matches())
			return false;
		return true;
	}
	
	public static String validationTextForSignUp(String userName, String password, String password2, 
			String LBOString, String name, String lastName, String adress, String phoneNumber) {
		if (!valideName(userName))
			return "Username is not valid.";
		if (!Unique.isUniqueUserName(userName))
			return "Username is not unique.";
		if (!valideName(password))
			return "Password is not valid.";
		if (!password.equals(password2))
			return "Password and confirmation password are not equal.";
		if(!valideLBO(LBOString))
			return "LBO needs to have 11 digits and to be unique.";
		if (!valideName(name))
			return "Name is not valid.";
		if (!valideName(lastName))
			return "Last name is not valid.";
		if (!valideAdress(adress))
			return "Address is not valid.";
		if (!validePhoneNumber(phoneNumber))
			return "Phone number is not valid.";
		return "";
	}
}
