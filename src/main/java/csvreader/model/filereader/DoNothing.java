package csvreader.model.filereader;

import csvreader.exception.CsvException;
import csvreader.model.Collector;
import csvreader.model.CsvReader;

/**
 * This is a collector that does nothing. Intended as 'default behavior' component.
 * 
 * @author fpezzati
 *
 */
public class DoNothing implements Collector {

	@Override
	public void collect(CsvReader reader) throws CsvException {
		// NOTHING TO DO.
	}
}
