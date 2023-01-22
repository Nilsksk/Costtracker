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
		//TODO If line already ends with \n dont add
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public void newLine() {
		sb = new StringBuilder();
		
	}

}
