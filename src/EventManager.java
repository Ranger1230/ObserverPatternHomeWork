import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class EventManager extends Observable implements Observer{

	private static EventManager uniqueInstance;
	private HashMap<EventType, ArrayList<Observer>> observers = new HashMap<EventType, ArrayList<Observer>>();
	
	private EventManager() {}
	
	public static EventManager getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new EventManager();
			uniqueInstance.addObserver(StockBroker.getInstance());
			uniqueInstance.addObserver(StockMonitor.getInstance());
		}
		
		return uniqueInstance;
	}
	
	public void Subscribe(Observer observer, EventType type){
		if(!observers.containsKey(type)){
			observers.put(type, new ArrayList<Observer>());
		}
		observers.get(type).add(StockBroker.getInstance());
	}

	@Override
	public void update(Observable o, Object arg) {
		//TODO differentiate notifying on creation from notifying on update
		notifyObservers(arg);
	}	
}