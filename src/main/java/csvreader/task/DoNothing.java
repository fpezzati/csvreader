package csvreader.task;

import csvreader.exception.CsvException;

/**
 * This is a collector that does nothing. Intended as 'default behavior' component.
 * 
 * @author fpezzati
 *
 */
public class DoNothing implements CsvTaskExecutor<Object, Object> {

	@Override
	public void execute(Object input, Object output) throws CsvException {
		// DO NOTHING.
	}
}
