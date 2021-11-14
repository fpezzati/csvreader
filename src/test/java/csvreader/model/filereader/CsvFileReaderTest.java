package csvreader.model.filereader;

import java.net.URI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import csvreader.exception.CsvException;

class CsvFileReaderTest {
	
	/**
	 * sut picks an {@link URI} object to find resource to read. If the uri is null, sut throws a NPE. 
	 */
	@Test
	void sutBubblesANPEWhenGivenURIIsNull() {
		CsvFileReader sut = new CsvFileReader() {
			@Override
			protected void manageTheLine(String line) throws CsvException {
				// DO NOTHING.
			}
		};
		URI input = null;
		Assertions.assertThrows(NullPointerException.class, ()-> {
			sut.readFile(input);
		});
	}
}
