package classes;

import java.util.stream.Stream;

public enum Specialization {
	Alergology("ALERGOLOGY"), Biochemistry("BIOCHEMISTRY"), Hematology("HEMATOLOGY"), Immunology("IMMUNOLOGY"),
	Drugs("DRUGS"), Immunochemistry("IMMUNOCHEMISTRY"), Transfusiology("TRANSFUSIOLOGY"), Serology("SEROLOGY"),
	Microbiology("MICROBIOLOGY"), Genetics("GENETICS");

	private final String fieldDescription;

	private Specialization(String value) {
		fieldDescription = value;
	}

	public String getName() {
		return fieldDescription;
	}
	
    public static Specialization fromString(String text) {
        for (Specialization b : Specialization.values()) {
            if (b.getName().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
    
    public static Stream<Specialization> stream() {
        return Stream.of(Specialization.values()); 
    }
}