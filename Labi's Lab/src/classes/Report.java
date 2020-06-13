package classes;

import java.util.ArrayList;

public class Report {
	private int id;
	private ArrayList<Analyze> analyzes;
	private double totalPrice;
	private String currentState;
	private boolean homeVisit;

	public Report() {
		this.id = 0;
		this.analyzes = new ArrayList<Analyze>();
		this.totalPrice = 0;
		this.currentState = "";
		this.homeVisit = false;
	}

	public Report(int number, ArrayList<Analyze> analyzes, String currentState, boolean homeVisit) {
		this.id = number;
		this.analyzes = analyzes;
		this.totalPrice = getTotalPrice();
		this.currentState = currentState;
		this.homeVisit = homeVisit;
	}

	public int getNumber() {
		return id;
	}

	public void setNumber(int number) {
		this.id = number;
	}

	public ArrayList<Analyze> getAnalyzes() {
		return analyzes;
	}

	public void setAnalyzes(ArrayList<Analyze> analyzes) {
		this.analyzes = analyzes;
	}

	public void addAnalyze(Analyze analyze) {
		this.analyzes.add(analyze);
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		for (int a = 0; a < this.analyzes.size(); a++) {
			totalPrice += this.analyzes.get(a).getPrice();
		}
		if (this.homeVisit) {
			// cena kucne posete
			totalPrice += 0;
		}
		this.totalPrice = totalPrice;
		return totalPrice;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public boolean isHomeVisit() {
		return homeVisit;
	}

	public void setHomeVisit(boolean homeVisit) {
		this.homeVisit = homeVisit;
	}

	@Override
	public String toString() {
		String listOfAnalyzes = "";
		if (analyzes.size() > 0) {
			listOfAnalyzes = analyzes.get(0).getId();
		}
		for (int i = 1; i < analyzes.size(); i++) {
			listOfAnalyzes += "|" + analyzes.get(i).getId();
		}
		return String.valueOf(id) + "," + listOfAnalyzes + "," + currentState + ","
				+ String.valueOf(homeVisit) + "\n";
	}
}
