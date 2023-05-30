package costtracker.document.xml;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is an internal class. Don't use, it might not work!
 * @author Florian Felix
 *
 */
public class XMLNode {
	protected String tag;
	
	protected String value;
	
	private List<XMLNode> children;
	
	public XMLNode(String tag, String value) {
		this.tag = tag;
		this.value = value;
		this.children = new ArrayList<>();
	}
	
	public void addChild(XMLNode node) {
		children.add(node);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<" + tag);
		if(value != null && !value.isBlank())
			sb.append(" value=\"" + value +"\"");
		sb.append(">");
		sb.append("\n");
		for (XMLNode xmlNode : children) {
			sb.append(xmlNode);
		}
		sb.append("</" + tag + ">");
		sb.append("\n");
		return sb.toString();
	}
}
