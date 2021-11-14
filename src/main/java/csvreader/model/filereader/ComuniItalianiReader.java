package csvreader.model.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import csvreader.exception.CsvException;
import csvreader.model.CsvReader;
import csvreader.model.CsvRowReader;
import csvreader.model.CsvStringReader;

public class ComuniItalianiReader extends CsvFileReader {

	private Map<String, String> codeMap;
	private CsvRowReader rowReader;
	
	public ComuniItalianiReader() {
		codeMap = new HashMap<String, String>();
		rowReader = new CsvRowReader();
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
	}

	public Map<String, String> getCodeMap() {
		return codeMap;
	}

	public CsvReader getCsvReader() {
		return rowReader;
	}

	@Override
	protected void manageTheLine(String line) throws CsvException {
		getCsvReader().read(line);
		codeMap.put(rowReader.getReaders().get(0).getValue(), rowReader.getReaders().get(1).getValue());
	}
}
