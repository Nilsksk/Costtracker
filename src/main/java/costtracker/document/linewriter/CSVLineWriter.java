package costtracker.document.linewriter;

public class CSVLineWriter implements LineWriter {

	private StringBuilder sb;

	public CSVLineWriter() {
		sb = new StringBuilder();
	}

	@Override
	public void appendToLine(String text) {
		sb.append(text);
		sb.append(";");
	}

	@Override
	public String returnLine() {
		var line = sb.toString();
		if (!line.endsWith("\n"))
			sb.append("\n");
		return sb.toString();
	}

	@Override
	public void newLine() {
		sb = new StringBuilder();

	}

}
