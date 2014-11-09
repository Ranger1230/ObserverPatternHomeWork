import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class StockBroker implements Observer{
	
	private static StockBroker uniqueInstance;
	
	private Map<String,Stock> stocks = new HashMap<String,Stock>(0);
		
	private StockBroker() {
		EventManager.getInstance().Subscribe(this, EventType.Create);
	}
	
	public static StockBroker getInstance() {
		
		if (uniqueInstance == null) {
			uniqueInstance = new StockBroker();
		}
		
		return uniqueInstance;
	}

	@Override
	public void update(Observable stock, Object stockSymbol) {
		Stock newStock = (Stock) stock;
		stocks.put(newStock.getStockSymbol(), newStock); 
	}
	
	// What does he mean by this?
	public void setStock(String stockSymbol, Stock stock) {
		// do something
	}

	public Stock getStock(String stockSymbol) {
		return stocks.get(stockSymbol);
	}
	
	public StockStatus getCurrentStockStatus(String stockSymbol) {
		Stock stock = stocks.get(stockSymbol);
		return stock.getCurrentStockStatus();
	}

}
