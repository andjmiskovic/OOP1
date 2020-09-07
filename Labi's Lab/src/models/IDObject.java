package models;


public class IDObject {
	
	protected String id;
	
	public IDObject() {
	}
	
	public IDObject(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true; 
	    if (o == null || getClass() != o.getClass()) return false; 
	    IDObject that = (IDObject) o;
	    return this.id == null ? that.id == null : this.id.equals(that.id);
	}
	
	@Override
	public String toString() {
		return id;
	}

}
