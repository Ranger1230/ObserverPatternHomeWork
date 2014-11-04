import java.util.ArrayList;
import java.util.Observable;

public class Stock extends Observable{
	
	private String stockSymbol;
	
	private ArrayList<StockStatus> statusList = new ArrayList<StockStatus>(0);
	
	public Stock(String stockSymbol, StockStatus stockStatus) {
		statusList.add(stockStatus);
		this.stockSymbol = stockSymbol;
		this.addObserver(EventManager.getInstance());
		this.setChanged();
		this.notifyObservers(EventType.Create);
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	
	public void addStatus (StockStatus stockStatus) {
		statusList.add(stockStatus);
		this.setChanged();
		notifyObservers(EventType.Update);
	}
	
	public StockStatus getCurrentStockStatus() {
		return statusList.get(statusList.size()-1);
	}
}
