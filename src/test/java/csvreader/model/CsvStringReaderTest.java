package csvreader.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import csvreader.exception.CsvException;
import csvreader.model.CsvReader;
import csvreader.model.CsvStringReader;

class CsvStringReaderTest {

	/**
	 * sut stores symbols as a String.
	 * @throws CsvException 
	 */
	@Test
	void sutStoresReadSymbolsAsString() throws CsvException {
		CsvReader sut = new CsvStringReader();
		String csvsnippet = "somevalue";
		sut.read(csvsnippet);
		MatcherAssert.assertThat(sut.getValue(), Matchers.is(csvsnippet));
	}
}
