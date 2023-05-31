package costtracker.domain.in.json;

import java.util.ArrayList;
import java.util.List;

public class JSONObject extends JSONValue {

	private List<JSONPair> value;

	public JSONObject() {
		value = new ArrayList<JSONPair>();
	}

	public void addPair(JSONPair jsonPair) {
		value.add(jsonPair);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\n");
		for (JSONPair jsonPair : value) {
			sb.append(jsonPair.toString());
			if (isNotLastJsonPair(jsonPair))
				sb.append(",");
			sb.append("\n");
		}
		sb.append("}");
		return sb.toString();

	}

	private boolean isNotLastJsonPair(JSONPair jsonPair) {
		int lastIndex = this.value.size() - 1;
		JSONPair lastPair = this.value.get(lastIndex);
		return !jsonPair.equals(lastPair);
	}
}
