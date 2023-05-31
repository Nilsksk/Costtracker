package costtracker.domain.in.json;

public class JSONDouble extends JSONValue {
	
	private double value;
	
	
	public JSONDouble(double value) {
		this.value = value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
}
