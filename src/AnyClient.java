import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;


public class AnyClient implements Observer {

	private JFrame frame;
	private JTextPane txtpnDisplayingSomeText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnyClient window = new AnyClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnyClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		StockBroker.getInstance();
		StockMonitor.getInstance().addObserver(this);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtpnDisplayingSomeText = new JTextPane();
		frame.getContentPane().add(txtpnDisplayingSomeText, BorderLayout.CENTER);
		

		new Stock("JPTest", new StockStatus(new Date(), 1.00));
	}

	@Override
	public void update(Observable o, Object arg) {
		txtpnDisplayingSomeText.setText(o.toString());
	}
}
