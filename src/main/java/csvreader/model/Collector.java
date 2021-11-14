package csvreader.model;

import csvreader.exception.CsvException;

/**
 * Collector: pick a {@link CsvReader} and does something with it.
 * 
 * @author fpezzati
 *
 */
public interface Collector {

	void collect(CsvReader reader) throws CsvException;
}
