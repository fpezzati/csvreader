package csvreader.model;

import csvreader.exception.CsvException;

/**
 * Base class to read a csv file. It's {@link Cloneable}, it forces you to
 * ovverride clone in concrete classes.
 * 
 * @author fpezzati
 *
 */
public abstract class CsvReader implements Cloneable {

	public void read(String csvsnippet) throws CsvException {
		if (csvsnippet == null || csvsnippet.isEmpty())
			return;
		setValue(csvsnippet);
	}

	protected abstract void setValue(String anyString) throws CsvException;

	public abstract <T> T getValue();

	public abstract Object clone() throws CloneNotSupportedException;
}
