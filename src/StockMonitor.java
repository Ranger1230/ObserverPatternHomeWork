import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class StockMonitor implements Observer{
	
	private static StockMonitor uniqueInstance;
	
	private StockMonitor() {}
	
	private Map<String,Stock> stocks = new HashMap<String,Stock>(0);
	
	public static StockMonitor getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new StockMonitor();
		}
		
		return uniqueInstance;
	}

	@Override
	public void update(Observable stock, Object stockSymbol) {
		if (!stocks.containsKey(stockSymbol)) {
			stocks.put((String)stockSymbol, (Stock)stock);
		}
	}
}
