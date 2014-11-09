import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EventManager implements Observer{

	private static EventManager uniqueInstance;
	private static HashMap<EventType, List<Observer>> observers = new HashMap<EventType, List<Observer>>();
	
	private EventManager() {}
	
	public static EventManager getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new EventManager();
		}
		
		return uniqueInstance;
	}
	
	public void Subscribe(Observer observer, EventType type){
		if(type == EventType.All){
			Subscribe(observer, EventType.Create);
			Subscribe(observer, EventType.Update);
		}else {
			if(!observers.containsKey(type)){
				observers.put(type, new ArrayList<Observer>());
			}
			observers.get(type).add(observer);
		}
	}
	
	public void Unsubscribe(Observer observer, EventType type) {
		if(type == EventType.All){
			Unsubscribe(observer, EventType.Create);
			Unsubscribe(observer, EventType.Update);
		}else if(observers.containsKey(type)){
			for(Observer o : observers.get(type)){
				if(o.equals(observer)){
					observers.get(type).remove(o);
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.getClass() != EventType.class){
			System.out.print("The EventManager update recived a arg of class "+arg.getClass().toString()+" Which it does not know how to handle.");
			return;
		}
		EventType notificationType = (EventType) arg;
		if(notificationType == EventType.All){
			this.update(o, EventType.Create);
			this.update(o, EventType.Update);
		}
		if(observers.containsKey(notificationType)){
			for(Observer o1 : observers.get(notificationType)){
				o1.update(o, arg);
			}
		}
	}	
}