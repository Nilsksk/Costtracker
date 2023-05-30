package costtracker.ut.document.printer.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import costtracker.plugin.in.json.JSONDouble;
import costtracker.plugin.in.json.JSONObject;
import costtracker.plugin.in.json.JSONPair;
import costtracker.plugin.in.json.JSONString;

public class JSONExportUnitTest {
	@Test
	void TestJSONString() {
		String value = "text";
		JSONString string = new JSONString(value);
		String expected = "\"" + value + "\"";
		
		String ret = string.toString();
		
		
		
		assertEquals(expected, ret);
	}
	
	@Test
	void TestJSONDouble() {
		double value = 1.0;
		JSONDouble d = new JSONDouble(value);
		String expected = String.valueOf(value);
		
		String ret = d.toString();
		
		assertEquals(expected, ret);
	}
	
	@Test
	void TestJSONObject() {
		JSONObject jsonObject = new JSONObject();
		
		String description = "desc";
		String text = "text";
		
		String descriptionCaption = "description";
		String textCaption = "t";
		
		JSONString jsonDescription = new JSONString(description);
		JSONString jsonText = new JSONString(text);
		
		jsonObject.addPair(new JSONPair(descriptionCaption, jsonDescription));
		jsonObject.addPair(new JSONPair(textCaption, jsonText));
		
		String expected = 	"{\n"+
						"\"" + descriptionCaption + "\": " + "\"" + description + "\",\n" +
						"\"" + textCaption + "\": " + "\"" + text + "\"\n" +
						"}";
						
		String ret = jsonObject.toString();
		
		assertEquals(expected, ret);
		
	}
	
	@Test
	void TestJSONPair() {
		String text = "text";
		String textCaption = "t";
		JSONString jsonText = new JSONString(text);
		
		JSONPair pair = new JSONPair(textCaption, jsonText);
		
		String expected = "\"" + textCaption + "\": " + "\"" + text + "\"";
		
		String ret = pair.toString();
		
		assertEquals(expected, ret);
	}

	@Test
	void TestJSONPairWithObject() {
		String caption = "caption";
		
		JSONObject jsonObject = new JSONObject();
		
		String description = "desc";
		String text = "text";
		
		String descriptionCaption = "description";
		String textCaption = "t";
		
		JSONString jsonDescription = new JSONString(description);
		JSONString jsonText = new JSONString(text);
		
		jsonObject.addPair(new JSONPair(descriptionCaption, jsonDescription));
		jsonObject.addPair(new JSONPair(textCaption, jsonText));

		JSONPair pair = new JSONPair(caption, jsonObject);
		
		String expected = "\""+ caption + "\": {\n"+
						"\"" + descriptionCaption + "\": " + "\"" + description + "\",\n" +
						"\"" + textCaption + "\": " + "\"" + text + "\"\n" +
						"}";
						
		String ret = pair.toString();
				
		assertEquals(expected, ret);
	}
	
}
