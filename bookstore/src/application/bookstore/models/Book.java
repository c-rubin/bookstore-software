package application.bookstore.models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.ArrayList;


import application.bookstore.extra.OverwriteFile;

public class Book extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String isbn;
	private String title;
	private double purchPrice;
	private double sellPrice;
	private String author;
	private static final String FILE_PATH = "data/books.ser";
	private static final File DATA_FILE = new File(FILE_PATH);
	private static final ArrayList<Book> books = new ArrayList<>();
	private int stock;
	
	public Book(){}
	public Book(String isbn,String title,double purchPrice,double sellPrice,String author) {
		this.isbn = isbn;
		this.title = title;
		this.purchPrice = purchPrice;
		this.sellPrice = sellPrice;
		this.author = author;
		
	}
	
	public String getIsbn() {
		return isbn;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public double getPurchPrice() {
		return purchPrice;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setIsbn(String isbn) {
		this.isbn =isbn;
	}
	public void setTitle(String title) {
		this.title =title;
	}
	public void setAutor(String author) {
		this.author =author;
		
	}
	public void setPurchPrice(double purchPrice) {
		this.purchPrice =purchPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice =sellPrice;
	}
	
	@Override
    public String toString() {
        return "Book{" +
                "ISBN=" + getIsbn() +
                ", Title=" + getTitle() +
                ", Author=" + getAuthor() +
                ", Purchase Price="+ getPurchPrice()+
                ", Sell Price="+ getSellPrice()+", Stock ="+getStock()+
                '}';
	}
	
	//another toString method, used in printing the bills
	public String toStringBill() {
        return "Book{" +
                "ISBN=" + getIsbn() +
                ", Title=" + getTitle() +
                ", Author=" + getAuthor() +
                '}';
	}
    
	
	
	public static ArrayList<Book> getBooks(){
		if(books.size()==0) {
			try {
				ObjectInputStream ios = new ObjectInputStream(new FileInputStream(FILE_PATH));
				while(true) {
					Book temp = (Book) ios.readObject();
					if(temp!=null) books.add(temp);
					else break;
				}
				ios.close();
			} catch (EOFException eofException) {
                System.out.println("End of file reached!");
            }catch(IOException | ClassNotFoundException ex) {
				//ex.printStackTrace();
			}
		}

		return books;
	}
	


	
	@Override
    public boolean saveInFile() {
		if(this.exists()) {
			for(Book temp: books) {
				if(temp.equals(this)) {
					temp.stock++;
					return OverwriteFile.overwriteCurrentListToFile(DATA_FILE, books);
				}
			}
		}
		this.stock++;
    	boolean saved = super.save(Book.DATA_FILE);
    	if(saved) books.add(this);
    	return saved;
    }

	@Override
	public boolean isValid() {
		try {
			return getTitle().length()>0 && getIsbn().length()>0 && getAuthor().length() >0 && purchPrice>=0 && sellPrice >=0;
		}
		catch(NullPointerException ex){
			return false;
		}
	}

	@Override
	public boolean deleteFromFile() {
		if(this.exists()) {
			System.out.println("aaa");
			books.remove(this);
			}
		
		return OverwriteFile.overwriteCurrentListToFile(DATA_FILE,books);
	}
	
	public static boolean deleteAll() {
		books.clear();

		return OverwriteFile.overwriteCurrentListToFile(DATA_FILE, books);
	}
	
	public static ArrayList<Book> getSearchResults(String isbn,String title,String author,double maxPurchPrice,double maxSellPrice,double minPurchPrice,double minSellPrice) {
		isbn = formRegEx(isbn);
		title = formRegEx(title);
		author = formRegEx(author);
		
		ArrayList<Book> searchResults = new ArrayList<>();
		
		for(Book book : books) {
			if(book.getIsbn().matches(isbn) && book.getTitle().matches(title) && book.getAuthor().matches(author)    &&     book.getPurchPrice()>minPurchPrice && book.getPurchPrice()<maxPurchPrice  &&  book.getSellPrice()<maxSellPrice && book.getSellPrice() >minSellPrice) {
				searchResults.add(book);
			}
		}
        
        return searchResults;
    }
	
	
	//will help us form a proper regex
	public static String formRegEx(String reg) {
		reg = reg.replaceAll(" ", ".*");
		reg = ".*"+ reg +".*";
		return reg;
	}
	@Override
	public boolean exists() {
			for(Book temp:getBooks()) {

				if( temp.equals(this)) return true;
				}
			return false;
	}
	
	public boolean equals(Book temp) {

		return(temp.getAuthor().equals(author) && temp.getIsbn().equals(isbn) && temp.getPurchPrice() == purchPrice && temp.getSellPrice() == sellPrice && temp.getTitle().equals(title));
	}

	public static boolean saveChanges() {
		return OverwriteFile.overwriteCurrentListToFile(DATA_FILE, getBooks());
	}

	public int getStock() {
		return stock;
	}
	
	public boolean sellStock(int quantity) {
		Book aa=null;
		for(Book temp: books) {
			temp.stock-=quantity;
			if(temp.stock<1) {System.out.println("a"); aa=temp;break;}
			return OverwriteFile.overwriteCurrentListToFile(DATA_FILE, books);
		}
		return aa.deleteFromFile();
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
