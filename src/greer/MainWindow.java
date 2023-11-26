package greer;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class MainWindow {

	Library libObject = new Library();
	private JFrame frame;
	private JButton addBookBtn;
	private JButton removeBookBtn;
	private JButton displayBookInfoBtn;
	private JButton displayInventoryBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		displayBookInfoBtn = new JButton("Display Book Info");
		displayBookInfoBtn.setBounds(53, 81, 135, 21);
		frame.getContentPane().add(displayBookInfoBtn);
		
		displayInventoryBtn = new JButton("Display Inventory");
		displayInventoryBtn.setBounds(214, 79, 135, 21);
		frame.getContentPane().add(displayInventoryBtn);
		
		addBookBtn = new JButton("New Book");
		addBookBtn.setBounds(53, 48, 135, 21);
		frame.getContentPane().add(addBookBtn);
		
		removeBookBtn = new JButton("Remove Book");
		removeBookBtn.setBounds(214, 50, 135, 21);
		frame.getContentPane().add(removeBookBtn);
	}
	
	//create action listener for the main menu buttons
	private void createEvents() {
		addBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				JFrame newFrame = new JFrame();
				newFrame.setBounds(100, 100, 350, 200);
				newFrame.getContentPane().setLayout(null);
				
				JTextField titleInput = new JTextField();
				titleInput.setBounds(107, 10, 96, 19);
				newFrame.getContentPane().add(titleInput);
				titleInput.setColumns(10);
				
				JTextField priceInput = new JTextField();
				priceInput.setBounds(107, 30, 96, 19);
				newFrame.getContentPane().add(priceInput);
				priceInput.setColumns(10);
				
				JTextField quantityInput = new JTextField();
				quantityInput.setBounds(107, 50, 96, 19);
				newFrame.getContentPane().add(quantityInput);
				quantityInput.setColumns(10);
				
				JLabel titleText = new JLabel("Title:");
				titleText.setBounds(67, 10, 96, 19);
				newFrame.getContentPane().add(titleText);
				
				JLabel priceText = new JLabel("Price:");
				priceText.setBounds(67, 30, 96, 19);
				newFrame.getContentPane().add(priceText);
				
				JLabel quantityText = new JLabel("Quantity:");
				quantityText.setBounds(47, 50, 96, 19);
				newFrame.getContentPane().add(quantityText);
				
				JButton addBookToInventoryBtn = new JButton("Add Book");
				addBookToInventoryBtn.setBounds(107, 90, 96, 19);
				newFrame.getContentPane().add(addBookToInventoryBtn);
				
				newFrame.setVisible(true);
				
				addBookToInventoryBtn.addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent e) {
						
						//add book to dataset
						boolean createBook = true;
						double price = 0;
						int quantity = 0;
						
						String title = titleInput.getText();
						
						try {
							price = Double.parseDouble(priceInput.getText());
							if(price < 0) {
								throw new Exception();
							}
						}catch(Exception ex) {
							createBook = false;
							System.out.println("Price should be inputted as ##.## or non-negative");
						}
						try {
							quantity = Integer.parseInt(quantityInput.getText());
							if(quantity <= 0) {
								throw new Exception();
							}
						}catch(Exception ex) {
							createBook = false;
							System.out.println("Quantity should be a whole number greater than 0");
						}
						
						if(createBook) {
							libObject.createBookEntry(title, price, quantity);
						}
						
						
						//close window
						newFrame.setVisible(false);
					}
				});
				}
		});
		
		removeBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				JFrame newFrame = new JFrame();
		        newFrame.setBounds(100, 100, 400, 200);
		        newFrame.getContentPane().setLayout(null);
		        
		        JTextField SKUInput = new JTextField();
		        SKUInput.setBounds(150, 20, 96, 19);
				newFrame.getContentPane().add(SKUInput);
				SKUInput.setColumns(10);
				
				JLabel SKUInputText = new JLabel("SKU #:");
				SKUInputText.setBounds(100, 20, 96, 19);
				newFrame.getContentPane().add(SKUInputText);
				
				JButton removeBookFromInventoryBtn = new JButton("Remove Book");
				removeBookFromInventoryBtn.setBounds(150, 40, 96, 19);
				newFrame.getContentPane().add(removeBookFromInventoryBtn);
				
				removeBookFromInventoryBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//remove selected book
						int SKU = Integer.parseInt(SKUInput.getText());
						libObject.removeBook(SKU);
						
						//close window 
						newFrame.setVisible(false);
					}
				});
		        
		        newFrame.setVisible(true);
			}
		});
		
		displayBookInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				JFrame newFrame = new JFrame();
				newFrame.setBounds(100, 100, 450, 200);
				newFrame.getContentPane().setLayout(null);
				
				JTextField SKUInput = new JTextField();
		        SKUInput.setBounds(150, 20, 96, 19);
				newFrame.getContentPane().add(SKUInput);
				SKUInput.setColumns(10);
				
				JLabel SKUInputText = new JLabel("SKU #:");
				SKUInputText.setBounds(100, 20, 96, 19);
				newFrame.getContentPane().add(SKUInputText);
				
				JButton getBookInfoBtn = new JButton("Get Book Info");
				getBookInfoBtn.setBounds(140, 40, 116, 19);
				newFrame.getContentPane().add(getBookInfoBtn);
				
				JLabel SKUOutputHeader = new JLabel("SKU #");
				SKUOutputHeader.setBounds(50, 80, 96, 19);
				newFrame.getContentPane().add(SKUOutputHeader);
				
				JLabel titleOutputHeader = new JLabel("Title");
				titleOutputHeader.setBounds(150, 80, 96, 19);
				newFrame.getContentPane().add(titleOutputHeader);
				
				JLabel costOutputHeader = new JLabel("Cost");
				costOutputHeader.setBounds(250, 80, 96, 19);
				newFrame.getContentPane().add(costOutputHeader);
				
				JLabel quantityOutputHeader = new JLabel("Quantity");
				quantityOutputHeader.setBounds(350, 80, 96, 19);
				newFrame.getContentPane().add(quantityOutputHeader);
				
				JLabel SKUOutput = new JLabel("");
				SKUOutput.setBounds(50, 100, 96, 19);
				newFrame.getContentPane().add(SKUOutput);
				
				JLabel titleOutput = new JLabel("");
				titleOutput.setBounds(150, 100, 96, 19);
				newFrame.getContentPane().add(titleOutput);
				
				JLabel costOutput = new JLabel("");
				costOutput.setBounds(250, 100, 96, 19);
				newFrame.getContentPane().add(costOutput);
				
				JLabel quantityOutput = new JLabel("");
				quantityOutput.setBounds(350, 100, 96, 19);
				newFrame.getContentPane().add(quantityOutput);
				
				getBookInfoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//find book
						int SKU = Integer.parseInt(SKUInput.getText());
						
						Book bookToGet = libObject.getBook(SKU);
						
						if(bookToGet != null) {
							SKUOutput.setText("" + bookToGet.getSKU());
							titleOutput.setText(bookToGet.getTitle());
							costOutput.setText("$" + bookToGet.getPrice());
							quantityOutput.setText("" + bookToGet.getQuantity());
						} else {
							System.out.println("No Book with that SKU exists");
						}
						
					}
				});
				
				newFrame.setVisible(true);
				
			}
		});
		
		displayInventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				JFrame newFrame = new JFrame();
		        newFrame.setBounds(100, 100, 600, 400);

		        // Create a DefaultTableModel with column names
		        DefaultTableModel model = new DefaultTableModel();
		        model.addColumn("SKU");
		        model.addColumn("Title");
		        model.addColumn("Price");
		        model.addColumn("Quantity");

		        
		        //loop through and add rows
		        ArrayList<Book> inventoryList = libObject.getInventoryList();
		        for(int i = 0; i < inventoryList.size(); i++) {
		        	Book currentBookInfo = inventoryList.get(i);
		        	model.addRow(new Object[]{currentBookInfo.getSKU(), currentBookInfo.getTitle(), currentBookInfo.getPrice(), currentBookInfo.getQuantity()});
		        }

		        // Create a JTable with the model
		        JTable myTable = new JTable(model);

		        // Optionally, you can set additional properties for the table, e.g., auto-resize columns
		        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		        // Create a JScrollPane to add the table to
		        JScrollPane scrollPane = new JScrollPane(myTable);

		        // Set layout manager for the content pane
		        newFrame.setLayout(new FlowLayout());

		        // Add the scroll pane to the frame
		        newFrame.add(scrollPane);

		        newFrame.setVisible(true);
			}
		});
	}
}
