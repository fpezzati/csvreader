package csvreader.model;

import java.util.ArrayList;
import java.util.List;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

class CsvRowReader extends CsvReader {

	private List<CsvReader> readers;
	private String separator;
	private int rowCount;
	
	CsvRowReader() {
		readers = new ArrayList<>();
		this.setSeparator(",");
	}
	
	@Override
	protected void setValue(String anyString) throws CsvException {
		rowCount = getRowCount() + 1;
		String[] snippets = anyString.split(getSeparator());
		if(snippets.length > readers.size()) throw new CsvFormatException("too many columns in row.");
		for(int i=0; i<snippets.length; i++) {
			readers.get(i).read(snippets[i]);
		}

	}
	
	public String getSeparator() {
		return separator;
	}
	
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	@Override
	public List<CsvReader> getValue() {
		return readers;
	}

	public List<CsvReader> getReaders() {
		return readers;
	}

	public int getRowCount() {
		return rowCount;
	}
}
