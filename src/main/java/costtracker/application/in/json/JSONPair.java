package costtracker.application.in.json;

public class JSONPair {
	private String key;	
	private JSONValue value;
	
	public JSONPair(String key, JSONValue value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public JSONValue getValue() {
		return this.value;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"" + this.getKey() + "\"");
		sb.append(": ");
		sb.append(this.getValue().toString());
		return sb.toString();
	}
}
