package classes;

import java.io.Console;

import fileHandler.Users;

public class CurrentUser {
	static String classOfTheCurrentUser;
	static Patient currentPatient;
	static Chemist currentChemist;
	static Admin currentAdmin;
	static MedicalTechnicial currentMedicalTechnicial;

	public CurrentUser() {
	}

	public static String setCurrentUser(String userName, String password) {
		classOfTheCurrentUser = "";
		if (Users.listOfPatients.size() > 0) {
			for (Patient patient : Users.listOfPatients) {
				if (userName.equals(patient.getUserName())) {
					classOfTheCurrentUser = "Patient";
					currentPatient = patient;
					return classOfTheCurrentUser;
				}
			}
		}
		if (Users.listofChemists.size() > 0) {
			for (Chemist chemist : Users.listofChemists) {
				if (userName.equals(chemist.getUserName())) {
					classOfTheCurrentUser = "Chemist";
					currentChemist = chemist;
					return classOfTheCurrentUser;
				}
			}
		}
		if (Users.listOfMedicalTechnicials.size() > 0) {
			for (MedicalTechnicial medicalTechnicial : Users.listOfMedicalTechnicials) {
				if (userName.equals(medicalTechnicial.getUserName())) {
					classOfTheCurrentUser = "MedicalTechnicial";
					currentMedicalTechnicial = medicalTechnicial;
					return classOfTheCurrentUser;
				}
			}
		}
		if (Users.listOfAdmins.size() > 0) {
			for (Admin admin : Users.listOfAdmins) {
				if (userName.equals(admin.getUserName())) {
					classOfTheCurrentUser = "Admin";
					currentAdmin = admin;
					return classOfTheCurrentUser;
				}
			}
		}
		return classOfTheCurrentUser;
	}

	public static String getClassOfTheCurrentUser() {
		return classOfTheCurrentUser;
	}

	public static void setClassOfTheCurrentUser(String classOfTheCurrentUser) {
		CurrentUser.classOfTheCurrentUser = classOfTheCurrentUser;
	}

	public static void setCurrentPatient(Patient currentPatient) {
		CurrentUser.currentPatient = currentPatient;
	}

	public static void setCurrentChemist(Chemist currentChemist) {
		CurrentUser.currentChemist = currentChemist;
	}

	public static void setCurrentAdmin(Admin currentAdmin) {
		CurrentUser.currentAdmin = currentAdmin;
	}

	public static void setCurrentMedicalTechnicial(MedicalTechnicial currentMedicalTechnicial) {
		CurrentUser.currentMedicalTechnicial = currentMedicalTechnicial;
	}

	public static Patient getCurrentPatient() {
		return currentPatient;
	}

	public static Chemist getCurrentChemist() {
		return currentChemist;
	}

	public static Admin getCurrentAdmin() {
		return currentAdmin;
	}

	public static MedicalTechnicial getCurrentMedicalTechnicial() {
		return currentMedicalTechnicial;
	}
}
