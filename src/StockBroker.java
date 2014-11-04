import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class StockBroker implements Observer{
	
	private static StockBroker uniqueInstance;
	
	private Map<String,Stock> stockList = new HashMap<String,Stock>(0);
		
	private StockBroker() {}
	
	public static StockBroker getInstance() {
		
		if (uniqueInstance == null) {
			uniqueInstance = new StockBroker();
		}
		
		return uniqueInstance;
	}

	@Override
	public void update(Observable o, Object arg) {
		stockList.put(arg.toString(), (Stock) o); 
	}
	
	// What does he mean by this?
	public void setStock(String stockSymbol, Stock stock) {
		// do something
	}

	public Stock getStock(String stockSymbol) {
		return stockList.get(stockSymbol);
	}
	
	public StockStatus getCurrentStockStatus(String stockSymbol) {
		Stock stock = stockList.get(stockSymbol);
		return stock.getCurrentStockStatus();
	}

}
