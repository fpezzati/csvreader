package csvreader.model;

import csvreader.exception.CsvException;

/**
 * Base class to read a csv file.
 * 
 * @author fpezzati
 *
 */
abstract class CsvReader {

	public void read(String csvsnippet) throws CsvException {
		if (csvsnippet == null || csvsnippet.isEmpty())
			return;
		setValue(csvsnippet);
	}

	protected abstract void setValue(String anyString) throws CsvException;

	public abstract <T> T getValue();
}
