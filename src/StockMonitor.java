import java.util.Observable;
import java.util.Observer;

public class StockMonitor implements Observer{
	
	private static StockMonitor uniqueInstance;
	
	private StockMonitor() {}
	
	public static StockMonitor getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new StockMonitor();
		}
		
		return uniqueInstance;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
