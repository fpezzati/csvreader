package csvreader.task;

import java.net.URI;
import java.util.Map;

import csvreader.model.CsvRowReader;

public interface UtentiInput {

	URI getURI();

	Map<String, CsvRowReader> getCodeMap();
}
