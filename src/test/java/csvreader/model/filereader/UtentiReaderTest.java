package csvreader.model.filereader;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import csvreader.model.ComuneCodeReader;
import csvreader.model.CsvReader;
import csvreader.model.CsvRowReader;
import csvreader.model.DateReader;
import csvreader.model.SexReader;
import csvreader.model.UserIdReader;
import csvreader.task.CsvTaskExecutor;
import csvreader.task.DoNothing;

/**
 * {@link UtentiReader}:<br>
 * - picks a csv resource made by four columns,
 * - has a default {@link DoNothing} collector,
 * - read rows if any and move them to his rowreader. Then rowreader is moved to {@link CsvTaskExecutor},
 * 
 * @author fpezzati
 *
 */
class UtentiReaderTest {

	private UtentiReader sut;
	
	@BeforeEach
	void initEach() {
		sut = new UtentiReader();
	}

	/**
	 * sut is shipped with a {@link CsvRowReader} composed by:<br>
	 * - a {@link UserIdReader} object,<br>
	 * - a {@link SexReader} object,<br>
	 * - a {@link DateReader} object,<br>
	 * - a {@link ComuneCodeReader} object.
	 */
	@Test
	void sutReadsARowMadeByFourDifferentFields() {
		sut = new UtentiReader();
		sutReadsARowComposedByFourDifferentFields(sut.getCsvReader());
	}
	
	private void sutReadsARowComposedByFourDifferentFields(CsvReader csvReader) {
		CsvRowReader rowReader = (CsvRowReader) csvReader;
		MatcherAssert.assertThat(rowReader.getReaders().get(0), Matchers.is(Matchers.instanceOf(UserIdReader.class)));
		MatcherAssert.assertThat(rowReader.getReaders().get(1), Matchers.is(Matchers.instanceOf(SexReader.class)));
		MatcherAssert.assertThat(rowReader.getReaders().get(2), Matchers.is(Matchers.instanceOf(DateReader.class)));
		MatcherAssert.assertThat(rowReader.getReaders().get(3), Matchers.is(Matchers.instanceOf(ComuneCodeReader.class)));
	}
}
