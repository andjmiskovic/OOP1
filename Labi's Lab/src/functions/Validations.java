package functions;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import fileHandler.Users;
import models.Admin;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.MedicalTechnicial;
import models.Patient;

public class Validations {
	public static boolean isUniqueLBO(String value) {
		for (Patient patient : Users.listOfPatients) {
			if (value.equals(patient.getLBO())) {
				return false;
			}
		}
		for (Chemist chemist : Users.listofChemists) {
			if (value.equals(chemist.getLBO())) {
				return false;
			}
		}

		for (MedicalTechnicial medicalTechnicial : Users.listOfMedicalTechnicials) {
			if (value.equals(medicalTechnicial.getLBO())) {
				return false;
			}
		}
		for (Admin admin : Users.listOfAdmins) {
			if (value.equals(admin.getLBO())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isUniqueLBO(String allowedLBO, String value) {
		if (value.equals(allowedLBO))
			return true;
		return isUniqueLBO(value);
	}

	public static boolean isUniqueUserName(String value) {
		for (Patient patient : Users.listOfPatients) {
			if (value.equals(patient.getUserName())) {
				return false;
			}
		}
		for (Chemist chemist : Users.listofChemists) {
			if (value.equals(chemist.getUserName())) {
				return false;
			}
		}

		for (MedicalTechnicial medicalTechnicial : Users.listOfMedicalTechnicials) {
			if (value.equals(medicalTechnicial.getUserName())) {
				return false;
			}
		}
		for (Admin admin : Users.listOfAdmins) {
			if (value.equals(admin.getUserName())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isUniqueUserName(String allowedUserName, String value) {
		if (value.equals(allowedUserName))
			return true;
		return isUniqueUserName(value);
	}

	public static boolean isUniqueAnalysisTypeName(String name) {
		for (AnalysisType analysisType : AllAnalysisTypes.listOfAnalysisTypes) {
			if (name.equals(analysisType.getName())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isUniqueAnalysisRequestId(String name) {
		for (AnalysisRequest analysisRequest : AllAnalysisRequests.listOfRequests) {
			if (name.equals(analysisRequest.getId())) {
				return false;
			}
		}
		return true;
	}

	public static boolean validName(String Name) {
		if (Name.length() == 0)
			return false;
		if (Name.length() > 20)
			return false;
		if (Name.contains(","))
			return false;
		return Name.matches("[a-zA-Z]+");
	}

	public static boolean validPassword(String password) {
		if (password.length() < 8)
			return false;
		if (password.length() > 20)
			return false;
		if (password.contains(","))
			return false;
		return true;
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null)
			return false;
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean validUserName(String UserName) {
		if (!validName(UserName))
			return false;
		if (!isUniqueUserName(UserName))
			return false;
		return true;
	}

	public static boolean validLBO(String LBO) {
		if (!isNumeric(LBO))
			return false;
		if (LBO.length() != 11)
			return false;
		return true;
	}

	public static boolean validPhoneNumber(String phoneNumber) {
		String regex = "[0-9*#+() -]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		if (!matcher.matches())
			return false;
		if (phoneNumber.length() < 8)
			return false;
		return true;
	}

	public static boolean validAddress(String address) {
		if (address.length() == 0)
			return false;
		if (address.length() > 20)
			return false;
		if (address.contains(","))
			return false;
		return true;
	}

	public static String validationTextForSignUp(boolean uniqueUserName, String allowedUserName, String allowedLBO,
			String userName, String password, String password2, String LBOString, String name, String lastName,
			String adress, String phoneNumber, LocalDate date) {
		if (!validName(userName))
			return "Username is not valid. It is only allowed to contains letters.";
		if (uniqueUserName) {
			if (!isUniqueUserName(userName))
				return "Username is not unique.";
		} else {
			if (!isUniqueUserName(allowedUserName, userName))
				return "Username is not unique.";
		}
		if (!validPassword(password))
			return "Password is not valid. It is supposed to have at least 8 characters.";
		if (!password.equals(password2))
			return "Password and confirmation password are not equal.";
		if (!validLBO(LBOString))
			return "LBO is not valid. It is supposed to have at least 11 digits.";
		if (uniqueUserName) {
			if (!isUniqueLBO(LBOString))
				return "LBO is not unique.";
		} else {
			if (!isUniqueLBO(allowedLBO, LBOString))
				return "LBO is not unique.";
		}
		if (!validName(name))
			return "Name is not valid.";
		if (!validName(lastName))
			return "Last name is not valid.";
		if (!validAddress(adress))
			return "Address is not valid.";
		if (!validPhoneNumber(phoneNumber))
			return "Phone number is not valid. It is supposed to have at least 8 digits.";
		if (date.isBefore(LocalDate.of(1900, 1, 1)))
			return "Are you really that old?";
		return "";
	}
}
