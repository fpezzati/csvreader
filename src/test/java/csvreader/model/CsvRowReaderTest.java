package csvreader.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;
import csvreader.model.CsvReader;
import csvreader.model.CsvRowReader;
import csvreader.model.CsvStringReader;

class CsvRowReaderTest {

	private CsvReader sut;
	
	@BeforeEach
	void initEach() {
		sut = new CsvRowReader();
	}
	
	/**
	 * sut comes with no behavior as default.
	 */
	@Test
	void sutHasNoDefaultBehavior() {
		MatcherAssert.assertThat(((CsvRowReader)sut).getReaders(), Matchers.is(Matchers.empty()));
	}
	
	/**
	 * sut has ',' as default separator.
	 */
	@Test
	void sutHasCommaSymbolAsDefaultSeparator() {
		MatcherAssert.assertThat(((CsvRowReader)sut).getSeparator(), Matchers.is(","));
	}
	
	/**
	 * when sut gets more string snippets than encapsulated readers, he throws a {@link CsvUnexpectedFormat} to caller.
	 */
	@Test
	void sutReadsMoreSnippetsThanOwnedReaders() {
		configureSutWithSomeReaders((CsvRowReader)sut, getTwoReaders());
		Assertions.assertThrows(CsvFormatException.class, () -> {
			sut.read("AAA,BBB,CCC");
		});
	}

	private void configureSutWithSomeReaders(CsvRowReader readerToConfigure, CsvReader... readers) {
		// TODO Auto-generated method stub
		for(CsvReader reader : readers) {
			readerToConfigure.getReaders().add(reader);
		}
	}

	private CsvReader[] getTwoReaders() {
		return new CsvReader[] { new CsvStringReader(), new CsvStringReader() };
	}
	
	/**
	 * Once configured, sut splits the read symbols throught all of its readers
	 * 
	 * @throws CsvException 
	 */
	@Test
	void sutReliesOnItsReaderToParseTheCsvRow() throws CsvException {
		CsvStringReader reader1 = Mockito.spy(new CsvStringReader());
		CsvStringReader reader2 = Mockito.spy(new CsvStringReader());
		CsvStringReader reader3 = Mockito.spy(new CsvStringReader());
		configureSutWithSomeReaders((CsvRowReader)sut, reader1, reader2, reader3);
		sut.read("AAA,BBB,CCC");
		allTheReadersHaveBeenUsed(reader1, reader2, reader3);
	}

	private void allTheReadersHaveBeenUsed(CsvReader... readers) throws CsvException {
		for(CsvReader reader : readers) {
			Mockito.verify(reader).read(ArgumentMatchers.anyString());
		}
	}
	
	/**
	 * Reading a row causes sut's rowCount to increase.
	 * 
	 * @throws CsvException 
	 */
	@Test
	void sutIncreasesHisRowCountWhileReadingRows() throws CsvException {
		configureSutWithSomeReaders((CsvRowReader)sut, getTwoReaders());
		sut.read("A,B");
		sut.read("A,B");
		MatcherAssert.assertThat(((CsvRowReader)sut).getRowCount(), Matchers.is(2));
	}
}
