package application.bookstore.controllers;

import application.bookstore.extra.TextFieldHandler;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import application.bookstore.models.Account;
import application.bookstore.models.Bill;
import application.bookstore.models.Book;
import application.bookstore.views.BookView;
import application.bookstore.views.LibrarianView;
import application.bookstore.views.ManagerView;
import javafx.scene.paint.Color;

public class LibrarianController {
	
	private final LibrarianView libView;
	private final ArrayList<Book> books = new ArrayList<>();
	private final BookView bookView;
	private final ManagerView managerView;
	private final Account currentAccount;

	public LibrarianController(LibrarianView libView, Account currentAccount, BookView bookView,ManagerView managerView) {
		this.libView = libView;
		this.currentAccount = currentAccount;
		this.bookView = bookView;
		this.managerView=managerView;
		setAddListener();
		setPrintListener();
	}
	
	private void setAddListener() {
		libView.getAddBtn().setOnAction(ev -> {
			
			try {
			
			
			
			//make sure fields arent empty
			if(TextFieldHandler.intFld(libView.getQuantityFld(), libView.getResultLbl(),"quantity")  &&  TextFieldHandler.stringFld(libView.getIsbnFld(), libView.getResultLbl())) {
				int quantity = Integer.parseInt(libView.getQuantityFld().getText());
				String isbn = libView.getIsbnFld().getText();
				//make sure valid qunatity
				if(quantity <= 0) {TextFieldHandler.setLabelText(libView.getResultLbl(),"Enter a valid quantity number",Color.DARKRED);}
				else {boolean found = false;
					for(Book temp : Book.getBooks()) {
						if(temp.getIsbn().equals(isbn)) {
							found=true;
							if(temp.getStock()==0) TextFieldHandler.setLabelText(libView.getResultLbl(),"This book is of stock!",Color.DARKRED);
							else if(temp.getStock()<quantity) TextFieldHandler.setLabelText(libView.getResultLbl(),"Quantity ("+quantity+") is larger than stock ("+temp.getStock()+")!",Color.DARKRED);
							else {
								
								
								if(temp.getStock()-quantity==0) {
									temp.sellStock(quantity);
									temp.deleteFromFile();
									bookView.getTableView().getItems().remove(temp);
									
									
									
									Book b = new Book(temp.getIsbn(),temp.getTitle(),temp.getPurchPrice(), temp.getSellPrice(),temp.getAuthor());
									
									b.setStock(quantity);
									if(books.add(b) && Book.saveChanges()) {
										TextFieldHandler.setLabelText(libView.getResultLbl(),"Books have been added to the bill! Now you can add other books or print the bill!",Color.DARKGREEN);
										libView.getPrintBtn().setDisable(false);
										resetFields();
									} else books.remove(b);
								} else {
									System.out.println("a");
									temp.sellStock(quantity);
									bookView.getTableView().refresh();
									
									
									
									Book b = new Book(temp.getIsbn(),temp.getTitle(),temp.getPurchPrice(), temp.getSellPrice(),temp.getAuthor());
									b.setStock(quantity);
									if(books.add(b) && Book.saveChanges()) {
										TextFieldHandler.setLabelText(libView.getResultLbl(),"Books have been added to the bill! Now you can add other books or print the bill!",Color.DARKGREEN);
										libView.getPrintBtn().setDisable(false);
										resetFields();
									} else books.remove(b);
								}
								
								
								
								
								
								
								
								
								
							}
						}
					}if(!found)TextFieldHandler.setLabelText(libView.getResultLbl(),"This book couldn't be found!",Color.DARKRED);
				}
			}
		
		
		
		
		}catch(ConcurrentModificationException ex) {
			
		}
		
		});
	}
	
	private void setPrintListener() {
		libView.getPrintBtn().setOnAction(ev->{
			Bill bill = new Bill(currentAccount, books);
			if(bill.saveInFile()&&bill.printBill()) { 
				managerView.getTableView().getItems().add(bill);
				managerView.getTableView().refresh();
				managerView.getTableView().refresh();
				resetFields();
				TextFieldHandler.setLabelText(libView.getResultLbl(), "Bill successfully printed!", Color.DARKGREEN);
				books.clear();
				}
			else TextFieldHandler.setLabelText(libView.getResultLbl(), "Bill printing failed!", Color.DARKRED);
			
			
			
			
			libView.getPrintBtn().setDisable(true);
					
					/*for(Book temp : Book.getBooks()) {
						if(temp.getIsbn().equals(isbn)) {
							currentBook = temp;
							
							//make sure that we arent running out of stock
							//if(currentBook.sellStock(quantity)) {
								/*Bill bill = new Bill(currentAccount,currentBook,quantity);
								
								if(bill.printBill() && currentBook.deleteFromFile()) {
									
									libView.getResultLbl().setText("Bill successfully printed!");
									libView.getResultLbl().setTextFill(Color.DARKGREEN);
									bill.saveInFile();
									
									System.out.println(currentBook.getStock());
								}else {
									libView.getResultLbl().setText("Bill printing failed!");
									libView.getResultLbl().setTextFill(Color.DARKRED);
								}
							/*}else {
								libView.getResultLbl().setText("Books are out of stock!");
								libView.getResultLbl().setTextFill(Color.DARKRED);
							}
						//}
					//}
				}
			}*/
		});
	}
	
	private void resetFields() {
        libView.getIsbnFld().setText("");
        libView.getQuantityFld().setText("");
        
        libView.getIsbnFld().setPromptText("Enter ISBN");
        libView.getQuantityFld().setPromptText("Enter Quantity");
    }
	
}
