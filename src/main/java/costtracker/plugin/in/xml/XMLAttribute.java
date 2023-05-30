package costtracker.plugin.in.xml;

/**
 * This class is an internal class. Don't use, it might not work!
 * @author Florian Felix
 *
 */
public class XMLAttribute extends XMLNode {

	public XMLAttribute(String tag, String value) {
		super(tag, value);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tag + ">");
		sb.append(value);
		sb.append("</" + tag + ">");
		sb.append("\n");
		return sb.toString();
		
	}
}
