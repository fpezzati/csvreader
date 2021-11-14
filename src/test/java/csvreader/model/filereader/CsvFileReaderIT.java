package csvreader.model.filereader;

import java.io.File;
import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mockito;

import csvreader.exception.CsvException;

public class CsvFileReaderIT {

	private CsvFileReader sut;
	
	@BeforeEach
	void initEach() {
		sut = Mockito.spy(new CsvFileReader() {
			
			@Override
			protected void manageTheLine(String line) throws CsvException {
				// DOES NOTHING.
			}
		});
	}
	
	/**
	 * when {@link URI} indicates an empty resource nothing is managed.
	 * @throws Exception 
	 */
	@Test
	void sutDoesNothingWhenResourceToReadIsEmpty() throws Exception {
		sut.readFile(new File("src/test/resources/filereader/empty_file.csv").toURI());
		Mockito.verify(sut, Mockito.never()).manageTheLine(ArgumentMatchers.anyString());
	}
	
	/**
	 * when {@link URI} indicates a resource with at least a line to manage, sut manages it.
	 * @throws Exception
	 */
	@Test
	void sutManagesEachLineReadInResource() throws Exception {
		sut.readFile(new File("src/test/resources/filereader/few_rows_file.csv").toURI());
		InOrder order = Mockito.inOrder(sut);
		order.verify(sut).manageTheLine("row0");
		order.verify(sut).manageTheLine("row1");
		order.verify(sut).manageTheLine("row2");
	}
}
