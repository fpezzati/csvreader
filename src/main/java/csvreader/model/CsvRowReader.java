package csvreader.model;

import java.util.ArrayList;
import java.util.List;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

public class CsvRowReader extends CsvReader {

	private List<CsvReader> readers;
	private String separator;
	private int rowCount;
	
	public CsvRowReader() {
		readers = new ArrayList<>();
		this.setSeparator(",");
	}
	
	@Override
	protected void setValue(String anyString) throws CsvException {
		rowCount = getRowCount() + 1;
		String[] snippets = anyString.split(getSeparator());
		if(snippets.length != readers.size()) throw new CsvFormatException("unexpected number of columns in row.");
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((readers == null) ? 0 : readers.hashCode());
		result = prime * result + rowCount;
		result = prime * result + ((separator == null) ? 0 : separator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CsvRowReader other = (CsvRowReader) obj;
		if (readers == null) {
			if (other.readers != null)
				return false;
		} else if (!readers.equals(other.readers))
			return false;
		if (rowCount != other.rowCount)
			return false;
		if (separator == null) {
			if (other.separator != null)
				return false;
		} else if (!separator.equals(other.separator))
			return false;
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		CsvRowReader crr = new CsvRowReader();
		for(CsvReader reader : getReaders()) {
			crr.getReaders().add((CsvReader) reader.clone());
		}
		return crr;
	}
}
