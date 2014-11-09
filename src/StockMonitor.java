import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class StockMonitor extends Observable implements Observer{
	
	private static StockMonitor uniqueInstance;
	
	private StockMonitor() {
		EventManager.getInstance().Subscribe(this, EventType.All);
	}
	
	private List<Stock> stocks = new ArrayList<Stock>();
	
	public static StockMonitor getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new StockMonitor();
		}
		
		return uniqueInstance;
	}

	@Override
	public void update(Observable stock, Object eventType) {
		EventType notificationType = (EventType) eventType;
		if (notificationType == EventType.Create) {
			stocks.add((Stock)stock);
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public String toString(){
		String monitorText = "";
		
		for(Stock s : stocks){
			monitorText += "\n" + s.toString();
		}
		
		return monitorText;
	}
}
