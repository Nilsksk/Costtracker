package test.document.linewriter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import costtracker.document.linewriter.CSVLineWriter;

class CSVLineWriterUt {

	@Test
	void TestAppendToLine() {
		String first = "1";
		String second = "2";
		CSVLineWriter lineWriter = new CSVLineWriter();
		lineWriter.appendToLine(first);
		lineWriter.appendToLine(second);
		var ret = lineWriter.returnLine();
		
		var chars = ret.toCharArray();
		assertTrue(chars.length >= 4);
		assertEquals(chars[0], first.toCharArray()[0]);
		assertEquals(chars[1], ';');
		assertEquals(chars[2], second.toCharArray()[0]);
		assertEquals(chars[3], ';');
	}
	@Test
	void TestNewLine() {
		String first = "1";
		String second = "2";
		CSVLineWriter lineWriter = new CSVLineWriter();
		lineWriter.appendToLine(first);
		lineWriter.appendToLine(second);
		
		lineWriter.newLine();
		
		var ret = lineWriter.returnLine();
		
		var chars = ret.toCharArray();
		assertTrue(chars.length == 1);
		assertEquals(chars[0], '\n');
	}

}
