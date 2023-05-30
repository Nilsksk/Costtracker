package costtracker.ut.document.printer.xml;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import costtracker.application.in.xml.XMLAttribute;
import costtracker.application.in.xml.XMLNode;


public class XMLNodeUnitTest {
	@Test
	void TestXMLNode() {
		String tag = "test";
		String value = "text";
		XMLNode xml = new XMLNode(tag, value);
		String expected = "<"+ tag + " value=" + "\"" + value + "\">\n"+
						"</" + tag + ">\n";
		
		String ret = xml.toString();
		
		
		
		assertEquals(expected, ret);
	}
	
	@Test
	void TestXMLAttribute() {
		String tag = "test";
		String value = "text";
		XMLNode xml = new XMLAttribute(tag, value);
		String expected = "<"+ tag + ">"+value + "</" + tag + ">\n";
		
		String ret = xml.toString();
		
		assertEquals(expected, ret);
	}
	
	@Test
	void TestNodeWithChildNode() {
		String tag = "test";
		String value = "text";
		String childTag = "childTest";
		String childValue = "chhildText";
		XMLNode xml = new XMLNode(tag, value);
		XMLNode childXml = new XMLNode(childTag, childValue);
		xml.addChild(childXml);
		String expected = "<"+ tag + " value=" + "\"" + value + "\">\n"+
						"<" + childTag + " value=" + "\"" + childValue + "\">\n"+
						"</" + childTag + ">\n" +
						"</" + tag + ">\n";
		
		String ret = xml.toString();
		
		
		
		assertEquals(expected, ret);
	}
	
	@Test
	void TestNodeWithChildAttribute() {
		String tag = "test";
		String value = "text";
		String childTag = "childTest";
		String childValue = "chhildText";
		XMLNode xml = new XMLNode(tag, value);
		XMLNode childXml = new XMLAttribute(childTag, childValue);
		xml.addChild(childXml);
		String expected = "<"+ tag + " value=" + "\"" + value + "\">\n"+
						"<" + childTag + ">" + childValue  + "</" + childTag + ">\n" +
						"</" + tag + ">\n";
		
		String ret = xml.toString();
		
		
		
		assertEquals(expected, ret);
	}
}
