package csvreader.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import csvreader.exception.CsvException;
import csvreader.exception.CsvFormatException;

class UserIdReaderTest {

	@BeforeEach

	/**
	 * When given string is too long, sut raises a {@link CsvFormatException} error.
	 */
	@Test
	void givenATooLongStringSutRaisesASpecificCsvFormatException() {
		CsvStringReader sut = new UserIdReader();
		assertThrows(CsvFormatException.class, () -> {
			sut.read("USER000001");
		});
	}

	/**
	 * When given string has not 'USER' as prefix, sut raises a
	 * {@link CsvFormatException} error.
	 */
	@Test
	void givenStringWithWrongSuffixSutRaisesASpecificCsvFormatException() {
		CsvStringReader sut = new UserIdReader();
		assertThrows(CsvFormatException.class, () -> {
			sut.read("USEX00001");
		});
	}

	/**
	 * When given string has not a valid number expressed by five chars as suffix,
	 * sut raises a {@link CsvFormatException} error.
	 */
	@Test
	void givenStringWithInvalidNumberAsPrefixSutRaisesASpecificCsvFormatException() {
		CsvStringReader sut = new UserIdReader();
		assertThrows(CsvFormatException.class, () -> {
			sut.read("USER00XYZ");
		});
	}

	/**
	 * when given string is 9 symbols long has a 'USER' prefix and a suffix of five
	 * representing a number, then sut encapsulates it.
	 * 
	 * @throws CsvException
	 */
	@Test
	void givenFeasibleStringSutEncapsulatesIt() throws CsvException {
		CsvStringReader sut = new UserIdReader();
		String feasibleString = "USER00091";
		sut.read(feasibleString);
		MatcherAssert.assertThat(sut.getValue(), Matchers.is(feasibleString));
	}
}
