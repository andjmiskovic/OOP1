package classes;

import java.time.format.DateTimeFormatter;

public class Analyze {
	private String id, type, description, unit;
	private double minValue, maxValue, price, discount;
	
	public Analyze() {
		this.id = "";
		this.type = "";
		this.description = "";
		this.unit = "";
		this.minValue = 0;
		this.maxValue = 0;
		this.price = 0;
		this.discount = 0;
	}
	
	public Analyze(String id, String type, String description, String unit,
			double minValue, double maxValue, double price, double discount) {
		this.id = id;
		this.type = type;
		this.description = description;
		this.unit = unit;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.price = price;
		this.discount = discount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getMinValue() {
		return this.minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getPrice() {
		return this.price*this.discount;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		return id + "," + type + "," + description + "," + unit + "," + String.valueOf(minValue) + "," 
				+ String.valueOf(maxValue) + "," + String.valueOf(price) + "," + String.valueOf(discount) + "\n";
	}
}
