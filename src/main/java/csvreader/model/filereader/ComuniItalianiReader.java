package csvreader.model.filereader;

import java.util.HashMap;
import java.util.Map;

import csvreader.exception.CsvException;
import csvreader.model.CsvReader;
import csvreader.model.CsvRowReader;
import csvreader.model.CsvStringReader;
import csvreader.task.ComuniItalianiInput;
import csvreader.task.ComuniItalianiOutput;
import csvreader.task.CsvTaskExecutor;

public class ComuniItalianiReader extends CsvFileReader  implements CsvTaskExecutor<ComuniItalianiInput, ComuniItalianiOutput> {

	private Map<String, CsvRowReader> codeMap;
	private CsvRowReader rowReader;
	
	public ComuniItalianiReader() {
		codeMap = new HashMap<String, CsvRowReader>();
		rowReader = new CsvRowReader();
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
	}

	public Map<String, CsvRowReader> getCodeMap() {
		return codeMap;
	}

	public CsvReader getCsvReader() {
		return rowReader;
	}

	@Override
	protected void manageTheLine(String line) throws CsvException {
		getCsvReader().read(line);
		try {
			codeMap.put(rowReader.getReaders().get(0).getValue(), (CsvRowReader) rowReader.clone());
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void execute(ComuniItalianiInput input, ComuniItalianiOutput output) throws Exception {
		readFile(input.getURI());
		output.setCodeMap(getCodeMap());
	}
}
