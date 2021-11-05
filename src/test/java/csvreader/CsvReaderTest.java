package csvreader;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class CsvReaderTest {
	
	/**
	 * sut has ',' symbol as default separator.
	 */
	@Test
	void commaIsTheDefaultSeparator() {
		CsvReader sut = new CsvReader() {};
		MatcherAssert.assertThat(sut.getSeparator(), Matchers.is(','));
	}

	/**
	 * when sut reads null, he immediately returns.
	 */
	
	/**
	 * when sut reads empty string, he immediately returns.
	 */
}
