package classes;

public enum CurrentStateOfRequest {
	PROCESS_STARTED("start"),
	SAMPLE_TAKING("taking samples"),
	PROCESSING("processing"),
	FINISHED_REPORT("done");
	
	private final String fieldDescription;

    private CurrentStateOfRequest(String value) {
        fieldDescription = value;
    }

    public String getName() {
    	return fieldDescription;
    }
}
