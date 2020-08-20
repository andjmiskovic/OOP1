package functions;

import classes.Admin;
import classes.AnalysisType;
import classes.Chemist;
import classes.MedicalTechnicial;
import classes.Patient;
import fileHandler.AllAnalysisTypes;
import fileHandler.Users;

public class Unique {
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
	public static boolean isUniqueAnalysisTypeName(String name) {
		for (AnalysisType analysisType : AllAnalysisTypes.listOfAnalysisTypes) {
			if (name.equals(analysisType.getName())) {
				return false;
			}
		}
		return true;
	}
}
