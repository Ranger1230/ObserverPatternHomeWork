import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AnyClient implements Observer {

	private JFrame frame;
	private JTextField stockSymboleInput;
	private JTextField stockStatusInput;
	private JTextPane txtpnStockDate;
	private TextArea textArea;

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
		frame.getContentPane().setLayout(null);
		
		JButton btnAddStock = new JButton("Add Stock");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stock existingStock = StockBroker.getInstance().getStock(stockSymboleInput.getText());
				if(existingStock == null){
					new Stock(stockSymboleInput.getText(), new StockStatus(new Date(), Double.parseDouble(stockStatusInput.getText())));
				} else{
					existingStock.addStatus(new StockStatus(new Date(), Double.parseDouble(stockStatusInput.getText())));
				}
				stockSymboleInput.setText("");
				stockStatusInput.setText("");
			}
		});
		btnAddStock.setEnabled(false);
		btnAddStock.setBounds(333, 243, 117, 29);
		frame.getContentPane().add(btnAddStock);
		
		stockSymboleInput = new JTextField();
		stockSymboleInput.setBounds(6, 242, 134, 28);
		stockSymboleInput.getDocument().addDocumentListener(new ButtonDisabler(stockSymboleInput, btnAddStock));
		frame.getContentPane().add(stockSymboleInput);
		stockSymboleInput.setColumns(10);
		
		stockStatusInput = new DoubleTextField();
		stockStatusInput.setBounds(167, 242, 134, 28);
		stockStatusInput.getDocument().addDocumentListener(new ButtonDisabler(stockStatusInput, btnAddStock));
		frame.getContentPane().add(stockStatusInput);
		stockStatusInput.setColumns(10);
		
		JTextPane txtpnStockSymbole = new JTextPane();
		txtpnStockSymbole.setBackground(UIManager.getColor("Button.background"));
		txtpnStockSymbole.setText("Stock Symbol");
		txtpnStockSymbole.setBounds(6, 214, 126, 16);
		frame.getContentPane().add(txtpnStockSymbole);
		
		txtpnStockDate = new JTextPane();
		txtpnStockDate.setText("Stock Price");
		txtpnStockDate.setBackground(UIManager.getColor("Button.background"));
		txtpnStockDate.setBounds(167, 214, 126, 16);
		frame.getContentPane().add(txtpnStockDate);
		
		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(6, 10, 434, 172);
		frame.getContentPane().add(textArea);
		

		new Stock("JPTest", new StockStatus(new Date(), 1.00));
	}

	@Override
	public void update(Observable o, Object arg) {
		textArea.setText(o.toString());
	}
	
	private static class ButtonDisabler implements DocumentListener{
		private static List<JTextField> fields = new ArrayList<JTextField>();;
		private static JButton _button;
		
		public ButtonDisabler(JTextField field, JButton button){
			fields.add(field);
			_button = button;
		}
		
		protected void update() {
			boolean enableButton = true;
			for(JTextField jtf : fields){
				if(jtf.getText().length() == 0){
					enableButton = false;
				}
			}
			_button.setEnabled(enableButton);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            update();
        }
	}
	
	private class DoubleTextField extends JTextField {
		  public DoubleTextField() {
		    super();
		  }

		  protected Document createDefaultModel() {
		    return new DoubleTextDocument();
		  }

		  public boolean isValid() {
		    try {
		      String inputText = this.getText();
			  if(inputText == "" || inputText == null){
				  return true;
			  }
		      Double.parseDouble(inputText);
		      return true;
		    } catch (Exception e) {
		      return false;
		    }
		  }

		  public double getValue() {
		    try {
		      return Double.parseDouble(getText());
		    } catch (NumberFormatException e) {
		      return 0;
		    }
		  }
		  class DoubleTextDocument extends PlainDocument {
		    public void insertString(int offs, String str, AttributeSet a)
		        throws BadLocationException {
		      if (str == null)
		        return;
		      String oldString = getText(0, getLength());
		      String newString = oldString.substring(0, offs) + str
		          + oldString.substring(offs);
		      try {
		    	  Double.parseDouble(newString + "0");
		        super.insertString(offs, str, a);
		      } catch (NumberFormatException e) {
		      }
		    }
		  }

		}
}
