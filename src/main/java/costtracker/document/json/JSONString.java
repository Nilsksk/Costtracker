package costtracker.document.json;

public class JSONString extends JSONValue {
	private String value;
	public JSONString(String value) {
		this.value = value;
	}
	
	public String toString() {
		return "\"" + value + "\"";
	}
}
