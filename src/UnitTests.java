import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;


public class UnitTests {

	@Test
	public void test() {
		new Stock("123", new StockStatus(new Date(), 123));
	}

}
