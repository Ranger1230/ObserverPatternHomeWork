import java.util.Date;

public class StockStatus {

	private Date date;
	private double price;
	
	public StockStatus(Date date, double price) {
		this.date = date;
		this.price = price;
	}
	
	public Date getDate() {
		return date;
	}
	
	public double getPrice() {
		return price;
	}
	
}
