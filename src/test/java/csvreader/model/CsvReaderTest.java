package csvreader.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import csvreader.exception.CsvException;

class CsvReaderTest {
	
	private CsvReader sut;

	@BeforeEach
	void initEach() {
		sut = Mockito.spy(new CsvReader() {
			@Override
			protected void setValue(String anyString) {
				// DO NOTHING.
			}

			@Override
			public Object getValue() {
				return null;
			}

			@Override
			protected Object clone() throws CloneNotSupportedException {
				return null;
			}});
	}
	
	/**
	 * when sut reads null, he immediately returns.
	 * 
	 * @throws CsvException 
	 */
	@Test
	void sutImmediatelyReturnsIfGivenStringIsNull() throws CsvException {
		String acsvline = null;
		sut.read(acsvline);
		Mockito.verify(sut, Mockito.never()).setValue(ArgumentMatchers.anyString());
	}
	
	/**
	 * when sut reads empty string, he immediately returns.
	 * 
	 * @throws CsvException 
	 */
	@Test
	void sutImmediatelyReturnsIfGivenStringIsEmpty() throws CsvException {
		String acsvline = "";
		sut.read(acsvline);
		Mockito.verify(sut, Mockito.never()).setValue(ArgumentMatchers.anyString());
	}
}
