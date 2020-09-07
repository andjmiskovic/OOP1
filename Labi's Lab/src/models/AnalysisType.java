package models;

public class AnalysisType {

	private String name, description, unit;
	private Specialization group;
	private double price, lowerBound, upperBound;
	private Discount typeDiscount, groupDiscount;

	public Discount getGroupDiscount() {
		return groupDiscount;
	}

	public void setGroupDiscount(Discount groupDiscount) {
		this.groupDiscount = groupDiscount;
	}

	public AnalysisType(String name, String description, String unit, Specialization group, double price,
			double lowerBound, double upperBound, Discount typeDiscount, Discount groupDiscount) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.group = group;
		this.unit = unit;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.typeDiscount = typeDiscount;
		this.groupDiscount = groupDiscount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

	public double getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Specialization getGroup() {
		return group;
	}

	public void setGroup(Specialization group) {
		this.group = group;
	}

	@Override
	public String toString() {
		String groupD = " ";
		String typeD = " ";
		if (groupDiscount != null) {
			groupD = groupDiscount.getId();
		}
		if (typeDiscount != null) {
			typeD = typeDiscount.getId();
		}
		return name + ',' + description + ',' + unit + ',' + group.toString() + ',' + String.valueOf(price) + ','
				+ String.valueOf(lowerBound) + ',' + String.valueOf(upperBound) + ',' + typeD + ',' + groupD + "\n";
	}

	public Discount getTypeDiscount() {
		return typeDiscount;
	}

	public void setTypeDiscount(Discount typeDiscount) {
		this.typeDiscount = typeDiscount;
	}
}
