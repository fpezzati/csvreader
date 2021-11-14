package csvreader.model.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import csvreader.exception.CsvException;

/**
 * Bare bone for every component reading a file
 * @author fpezzati
 *
 */
public abstract class CsvFileReader {

	public void readFile(URI input) throws CsvException, IOException {
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(input)))) {
			for(String line; (line = reader.readLine()) != null; ) {
				manageTheLine(line);
			}
		}		
	}

	protected abstract void manageTheLine(String line) throws CsvException;
}
