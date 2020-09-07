package models;

public enum Qualification {
	One("1. degree"), Two("2. degree"), Three("3. degree"), Four("4. degree"), Five("5. degree"), SixA("6.1 degree"),
	SixB("6.2 degree"), SevenA("7.1 degree"), SevenB("7.2 degree"), Eight("8. degree");

	private final String fieldDescription;

	private Qualification(String value) {
		fieldDescription = value;
	}

	public String getName() {
		return fieldDescription;
	}
	
    public static Qualification fromString(String text) {
        for (Qualification b : Qualification.values()) {
            if (b.getName().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}