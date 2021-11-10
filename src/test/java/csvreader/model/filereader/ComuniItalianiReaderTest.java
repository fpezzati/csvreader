package csvreader.model.filereader;

import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import csvreader.model.CsvRowReader;
import csvreader.model.CsvStringReader;

class ComuniItalianiReaderTest {
	
	private ComuniItalianiReader sut;

	@BeforeEach
	void initEach() {
		sut = Mockito.spy(new ComuniItalianiReader());
	}
	
	/**
	 * sut comes with an empty code:name map.
	 */
	@Test
	void sutComesWithAnEmptyCodeMap() {
		MatcherAssert.assertThat(sut.getCodeMap(), Matchers.is(Matchers.anEmptyMap()));
	}
	
	@Test
	void sutComesWithFourStringReadersRowReader() {
		MatcherAssert.assertThat(sut.getCsvReader(), Matchers.is(getAFourCsvReadersRow()));
	}

	private CsvRowReader getAFourCsvReadersRow() {
		CsvRowReader rowReader = new CsvRowReader();
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
		rowReader.getReaders().add(new CsvStringReader());
		return rowReader;
	}

	/**
	 * when inputstream is null sut raises a NPE.
	 */
	@Test
	void sutDoesNothingWhenGivenURIIsNull() {
		URI input = null;
		Assertions.assertThrows(NullPointerException.class, ()->{
			sut.readFile(input);
		});
	}
}
