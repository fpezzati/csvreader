package csvreader.model.collector;

import csvreader.exception.CsvException;
import csvreader.model.CsvReader;

/**
 * Collector: pick a {@link CsvReader} and does something with it.
 * 
 * @author fpezzati
 *
 */
public interface Collector {

	void collect(CsvReader reader) throws CsvException;
}
