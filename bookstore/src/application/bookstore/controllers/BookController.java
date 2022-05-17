package application.bookstore.controllers;

import java.util.List;

import application.bookstore.extra.TextFieldHandler;
import application.bookstore.models.Book;
import application.bookstore.views.BookView;
import javafx.scene.paint.Color;

public class BookController {
    private final BookView bookView;
    public BookController(BookView bookView) {
        this.bookView = bookView;
        setSaveListener();
        setDeleteListener();
        setEditListener();
        setDeleteAllListener();
    }

    private void setSaveListener() {
        bookView.getSaveBtn().setOnAction(e -> {
        	//make sure fields arent empty and correct format in prices
        	if(   (TextFieldHandler.stringFld(bookView.getIsbnFld(),bookView.getResultLabel())  &&  TextFieldHandler.stringFld(bookView.getTitleFld(), bookView.getResultLabel())  &&  TextFieldHandler.stringFld(bookView.getAuthorFld(), bookView.getResultLabel())  &&   TextFieldHandler.doubleFld(bookView.getPurchPriceFld(), bookView.getResultLabel(),"Purchase Price")   && TextFieldHandler.doubleFld(bookView.getSellPriceFld(), bookView.getResultLabel(),"Sell Price"))) {
        		
        		String isbn = bookView.getIsbnFld().getText();
        		String title = bookView.getTitleFld().getText();
        		String author = bookView.getAuthorFld().getText();
        		double purchPrice = Double.parseDouble(bookView.getPurchPriceFld().getText());
        		double sellPrice = Double.parseDouble(bookView.getSellPriceFld().getText());
        		
        		Book book = new Book(isbn, title, purchPrice, sellPrice,author);
        		if(book.exists()) {
	                if (book.saveInFile()) {
	                    //bookView.getTableView().getItems().add(book);
	                	bookView.getTableView().refresh();
	                	//bookView.getTableView().reload();
	                	TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book created successfully", Color.DARKGREEN);
	                    resetFields();
	                } else TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book creation failed", Color.DARKRED);
	        	}
        		else {
        			if (book.saveInFile()) {
                        bookView.getTableView().getItems().add(book);
                        TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book created successfully", Color.DARKGREEN);
                        resetFields();
        			}
        			else TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book creation failed", Color.DARKRED);
        		}
        	}

            
        });
    }

    private void resetFields() {
        bookView.getIsbnFld().setText("");
        bookView.getTitleFld().setText("");
        bookView.getPurchPriceFld().setText("");
        bookView.getSellPriceFld().setText("");
        bookView.getAuthorFld().setText("");
        
        bookView.getIsbnFld().setPromptText("Enter ISBN");
        bookView.getTitleFld().setPromptText("Enter Title");
		bookView.getAuthorFld().setPromptText("Enter Author");
		bookView.getPurchPriceFld().setPromptText("Enter Purchase Price");
		 bookView.getSellPriceFld().setPromptText("Enter Sell Price");
    }
    
    private void setDeleteListener() {
        bookView.getDeleteBtn().setOnAction(e -> {
        	//System.out.println("a");
        	//multiple selection not allowed, so only 1 selection can be made, this means that the list will only have 1 element, accessable with .get(0)
            List<Book> itemsToDelete = List.copyOf(bookView.getTableView().getSelectionModel().getSelectedItems());
           if (itemsToDelete.get(0).exists()) {
        	   if(itemsToDelete.get(0).getStock()>1) {
        		   if (itemsToDelete.get(0).sellStock(1)) {
            		   bookView.getTableView().refresh();//
            		   TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book removed successfully", Color.DARKGREEN);
            	   } else TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book couldn't be deleted", Color.DARKRED);
        	   }else {
        		   if (itemsToDelete.get(0).deleteFromFile()) {
            		   bookView.getTableView().getItems().remove(itemsToDelete.get(0));
            		   TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book removed successfully", Color.DARKGREEN);
            	   } else TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book couldn't be deleted", Color.DARKRED);
        	   }
        	   
        	   
           }else TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book doesn't exist", Color.DARKRED);
            
            
        });
    }
    
    private void setEditListener() {
    	bookView.getEditBtn().setOnAction(e -> {
    		try {
    		//select book to edit
    		List<Book> itemsToEdit = List.copyOf(bookView.getTableView().getSelectionModel().getSelectedItems());
    		//get new info, this time user fills only areas which he wants to edit, so we only check that doubles are entered correctly
    		if( ( TextFieldHandler.doubleFldNull(bookView.getPurchPriceFld(), bookView.getResultLabel(),"Purchase Price")   && TextFieldHandler.doubleFldNull(bookView.getSellPriceFld(), bookView.getResultLabel(),"Sell Price")) ) {
    			String isbn = bookView.getIsbnFld().getText();
                String title = bookView.getTitleFld().getText();
                String author = bookView.getAuthorFld().getText();
        		
        		if (itemsToEdit.get(0).exists()) {
             	   for(Book temp:Book.getBooks()) {
             		   if(temp.equals(itemsToEdit.get(0))) {
             			   
             			   if( !(author.equals(""))) itemsToEdit.get(0).setAutor(author);
             			   if( !(title.equals(""))) itemsToEdit.get(0).setTitle(title);
             			   if( !(isbn.equals(""))) itemsToEdit.get(0).setIsbn(isbn);
            			   if( !(bookView.getPurchPriceFld().getText().equals(""))) itemsToEdit.get(0).setPurchPrice(Double.parseDouble(bookView.getPurchPriceFld().getText()));
            			   if( !(bookView.getSellPriceFld().getText().equals(""))) itemsToEdit.get(0).setSellPrice(Double.parseDouble(bookView.getSellPriceFld().getText()));
            			   
            			   if(Book.saveChanges()) {
            				   bookView.getTableView().refresh();
            				   TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book edited successfully", Color.DARKGREEN);
            				   resetFields();
            			   } else TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book couldn't be edited", Color.DARKRED);

             		   }
             	   }
                }else TextFieldHandler.setLabelText(bookView.getResultLabel(), "Book doesn't exist", Color.DARKRED);
    		}
    	}catch(ArrayIndexOutOfBoundsException ex) {
    		TextFieldHandler.setLabelText(bookView.getResultLabel(), "Select a book by clicking on it!", Color.DARKRED);
    	}/*catch(java.lang.NullPointerException ex) {
    		TextFieldHandler.setLabelText(bookView.getResultLabel(), "Please fill at least one text field!", Color.DARKRED);
    	}*/
    	});
    }
    
    public void setDeleteAllListener() {
    	bookView.getDeleteAllBtn().setOnAction(e->{
    		if (Book.deleteAll()) {
    		bookView.getTableView().getItems().clear();
    		TextFieldHandler.setLabelText(bookView.getResultLabel(), "All of the books have been successfuly deleted!", Color.DARKGREEN);
    		}else TextFieldHandler.setLabelText(bookView.getResultLabel(), "Books couldn't be deleted!", Color.DARKRED);
    		
    		
    	});
    }
    
    /*public static void reloadTable(Book book, TableView tableView) {
    	if (book.getStock()>1) tableView.refresh();
    	else
    }*/
    
    //private void 
}