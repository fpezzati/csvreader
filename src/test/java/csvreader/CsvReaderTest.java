package csvreader;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

class CsvReaderTest {
	
	private CsvReader sut;

	@BeforeEach
	void initEach() {
		sut = Mockito.spy(new CsvReader() {
			@Override
			protected void setValue(String anyString) {
				// DO NOTHING.
			}});
	}
	
	/**
	 * sut has ',' symbol as default separator.
	 */
	@Test
	void commaIsSutDefaultSeparator() {
		MatcherAssert.assertThat(sut.getSeparator(), Matchers.is(','));
	}

	/**
	 * when sut reads null, he immediately returns.
	 */
	@Test
	void sutImmediatelyReturnsIfGivenStringIsNull() {
		String acsvline = null;
		sut.read(acsvline);
		Mockito.verify(sut, Mockito.never()).setValue(ArgumentMatchers.anyString());
	}
	
	/**
	 * when sut reads empty string, he immediately returns.
	 */
	@Test
	void sutImmediatelyReturnsIfGivenStringIsEmpty() {
		String acsvline = "";
		sut.read(acsvline);
		Mockito.verify(sut, Mockito.never()).setValue(ArgumentMatchers.anyString());
	}
}
