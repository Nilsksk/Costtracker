package costtracker.document.linewriter;

public interface LineWriter {
	public void appendToLine(String text);
	
	public String returnLine();
	
	public void newLine();
}
