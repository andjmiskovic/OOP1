package classes;

import static java.util.stream.Collectors.joining;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalysisRequest extends IDObject {
	private double totalPrice;
	private boolean homeVisit, homeVisitWithTime;
	private LocalDate homeVisitDate;
	private LocalTime homeVisitTime;
	private CurrentStateOfRequest currentState;
	private Map<AnalysisType, Double> analyses;
	private Patient patient;
	private LocalDate requestedDate, processedDate;
	private MedicalTechnicial medicalTechnicial;
	private Map<Specialization, Chemist> processedGroups;
	private ArrayList<Specialization> unprocessedGroups;

	public AnalysisRequest(String id, double totalPrice, boolean homeVisit, LocalDate homeVisitDate,
			boolean homeVisitWithTime, LocalTime homeVisitTime, CurrentStateOfRequest currentState,
			Map<AnalysisType, Double> analyses, Patient patient, LocalDate requestedDate, LocalDate processedDate,
			MedicalTechnicial medicalTechnicial, Map<Specialization, Chemist> processedGroups,
			ArrayList<Specialization> unprocessedGroups) {
		super(id);
		this.totalPrice = totalPrice;
		this.homeVisit = homeVisit;
		this.homeVisitDate = homeVisitDate;
		this.homeVisitWithTime = homeVisitWithTime;
		this.homeVisitTime = homeVisitTime;
		this.currentState = currentState;
		this.analyses = analyses;
		this.patient = patient;
		this.requestedDate = requestedDate;
		this.processedDate = processedDate;
		this.medicalTechnicial = medicalTechnicial;
		this.processedGroups = processedGroups;
		this.unprocessedGroups = unprocessedGroups;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isHomeVisit() {
		return homeVisit;
	}

	public void setHomeVisit(boolean homeVisit) {
		this.homeVisit = homeVisit;
	}

	public LocalDate getHomeVisitDate() {
		return homeVisitDate;
	}

	public void setHomeVisitDate(LocalDate homeVisitDate) {
		this.homeVisitDate = homeVisitDate;
	}

	public boolean isHomeVisitWithTime() {
		return homeVisitWithTime;
	}

	public void setHomeVisitWithTime(boolean homeVisitWithTime) {
		this.homeVisitWithTime = homeVisitWithTime;
	}

	public LocalTime getHomeVisitTime() {
		return homeVisitTime;
	}

	public void setHomeVisitTime(LocalTime homeVisitTime) {
		this.homeVisitTime = homeVisitTime;
	}

	public CurrentStateOfRequest getCurrentState() {
		return currentState;
	}

	public void setCurrentState(CurrentStateOfRequest currentState) {
		this.currentState = currentState;
	}

	public Map<AnalysisType, Double> getAnalyses() {
		return analyses;
	}

	public void setAnalyses(Map<AnalysisType, Double> analyses) {
		this.analyses = analyses;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(LocalDate requestedDate) {
		this.requestedDate = requestedDate;
	}

	public LocalDate getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(LocalDate processedDate) {
		this.processedDate = processedDate;
	}

	public MedicalTechnicial getMedicalTechnicial() {
		return medicalTechnicial;
	}

	public void setMedicalTechnicial(MedicalTechnicial medicalTechnicial) {
		this.medicalTechnicial = medicalTechnicial;

	}

	public ArrayList<Specialization> getUnprocessedGroups() {
		return unprocessedGroups;
	}

	public void setUnprocessedGroups(ArrayList<Specialization> unprocessedGroups) {
		this.unprocessedGroups = unprocessedGroups;
	}

	public String analysisTypesToString(ArrayList<AnalysisType> listOsAnalysisTypes) {
		ArrayList<String> list = new ArrayList<String>();
		for (AnalysisType analysisType : listOsAnalysisTypes) {
			list.add(analysisType.getName());
		}
		return list.stream().collect(Collectors.joining("|"));
	}

	public String localDateToString(LocalDate date) {
		if (date == null) 
			return " ";
		return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
	}

	public String specializationsToString(ArrayList<Specialization> listOfSpecializations) {
		ArrayList<String> list = new ArrayList<String>();
		for (Specialization specialization : listOfSpecializations) {
			list.add(specialization.getName());
		}
		return list.stream().collect(Collectors.joining("|"));
	}

	public String chemistsToString(ArrayList<Chemist> listOfChemists) {
		ArrayList<String> list = new ArrayList<String>();
		for (Chemist chemist : listOfChemists) {
			list.add(chemist.getUserName());
		}
		return list.stream().collect(Collectors.joining("|"));
	}

	public String specializationMapToString(Map<Specialization, Chemist> map) {
		return map.entrySet().stream().map(e -> e.getKey().getName() + ":" + e.getValue().getUserName())
				.collect(joining("|"));
	}

	public String analysisTypeMapToString(Map<AnalysisType, Double> map) {
		return map.entrySet().stream().map(e -> e.getKey().getName() + ":" + e.getValue()).collect(joining("|"));
	}
	
	public static String localTimeToString(LocalTime time) {
		if (time != null) 
			return time.format(DateTimeFormatter.ofPattern("HH:mm"));
		return " ";
	}

	@Override
	public String toString() {
		String medicalTechnicialUserName = " ";
		if (medicalTechnicial != null) 
			medicalTechnicialUserName = medicalTechnicial.getUserName();
		return super.toString() + ',' + 
				String.valueOf(totalPrice) + ',' + 
				String.valueOf(homeVisit) + ',' +
				localDateToString(homeVisitDate) + ',' + 
				String.valueOf(homeVisitWithTime) + ',' +
				localTimeToString(homeVisitTime) + ',' + 
				currentState.toString() + ',' + 
				analysisTypeMapToString(analyses)+ ',' + 
				patient.getUserName() + ',' + 
				localDateToString(requestedDate) + ',' + 
				localDateToString(processedDate) + ',' +
				medicalTechnicialUserName + ',' + 
				specializationMapToString(processedGroups) + ',' +
				specializationsToString(unprocessedGroups);
	}

	public int compareTo(AnalysisRequest o) {
		if (getProcessedDate() == null || o.getProcessedDate() == null)
			return 0;
		return getProcessedDate().compareTo(o.getProcessedDate());
	}

}
