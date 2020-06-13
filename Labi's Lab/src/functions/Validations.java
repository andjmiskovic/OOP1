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
	
	public static boolean valideUserName(String UserName) {
		if (!valideName(UserName))
			return false;
		if (!Unique.isUniqueUserName(UserName))
			return false;
		return true;
	}

	public static boolean valideLBO(String LBO) {
		Pattern pattern = Pattern.compile("0123456789");
		Matcher matcher = pattern.matcher(LBO);
		if (matcher.matches())
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
		String regex = "[0-9* -]*";
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
			return "Korisničko ime nije validno.";
		if (!Unique.isUniqueUserName(userName))
			return "Korisničko ime nije jedinstveno.";
		if (!valideName(password))
			return "Lozinka nije validna.";
		if (!password.equals(password2))
			return "Potvrdjena lozinka nije ista kao i lozinka.";
		if(!valideLBO(LBOString))
			return "LBO mora sadržati tačno 11 brojeva i mora biti jedinstven.";
		if (!valideName(name))
			return "Ime nije validno.";
		if (!valideName(lastName))
			return "Prezime nije validno.";
		if (!valideAdress(adress))
			return "Adresa nije validna.";
		if (!validePhoneNumber(phoneNumber))
			return "Broj telefona nije validan.";
		return "";
	}
}
