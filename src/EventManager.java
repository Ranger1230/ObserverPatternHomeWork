import java.util.Observable;
import java.util.Observer;

public class EventManager extends Observable implements Observer{

	private static EventManager uniqueInstance;
	
	private EventManager() {}
	
	public static EventManager getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new EventManager();
			uniqueInstance.addObserver(StockBroker.getInstance());
			uniqueInstance.addObserver(StockMonitor.getInstance());
		}
		
		return uniqueInstance;
	}

	@Override
	public void update(Observable o, Object arg) {
		//TODO differentiate notifying on creation from notifying on update
		notifyObservers(arg);
	}	
}
