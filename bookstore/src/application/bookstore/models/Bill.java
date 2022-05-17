package application.bookstore.models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import application.bookstore.extra.OverwriteFile;

public class Bill extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final ArrayList<Bill> bills = new ArrayList<>();
	private final static String FILE_PATH = "data/bills.ser";
	private final static File DATA_FILE = new File(FILE_PATH);
	private static final String PRINT_PATH = "bills/Bill_";
	
	
	private final ArrayList<Book> books;
	private final Account acc;
	//private final Book book;
	private final Date date;
	private final int quantity;
	private final double price;
	
	
	
	public Bill(Account acc, ArrayList<Book> books){//,int quantity){
		this.acc=acc;
		this.books = books;
		this.date = new Date();
		price = getTotalPrice();
		int a = getQuantity1();
		System.out.println(a);
		this.quantity = a;
		//this.price = book.getSellPrice() * quantity;
	}
	
	public static ArrayList<Bill> getBills () {
        if (bills.size() == 0){//if arraylist of accounts is empty, check file
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    Bill temp = (Bill) inputStream.readObject();
                    if (temp == null)
                        break;
                    bills.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of file reached!");
            }
            catch (IOException | ClassNotFoundException e) {
                //e.printStackTrace();
            }
        }
        return bills;
    }
	

	@Override
	public boolean saveInFile() {
		boolean saved = super.save(Bill.DATA_FILE);
    	if(saved) bills.add(this);
    	return saved;
	}

	@Override
	public boolean isValid() {
		return acc.isValid() && date != null;
	}

	@Override
	public boolean deleteFromFile() {
		return OverwriteFile.overwriteCurrentListToFile(DATA_FILE, getBills());
	}
	
	public boolean printBill() {
		//String billTxt = account.toString() + "\n\n" + book.toStringBill()+  "/n\n-----------------\n\nPrice: "+book.getSellPrice()+"\nQuantity: "+quantity+"\n\n-----------------\n\nTOTAL: "+price;
		try {
			PrintWriter print = new PrintWriter(new File(PRINT_PATH+getDateString()+".txt"));
			print.println("Sold by:  \" "+acc.getUsername()+" \"");
			print.println();
			for(Book bb : books) { 
				print.println("-----------------");
				print.println();
				print.println();
				print.println(bb.toStringBill());
				print.println("\nPrice: $"+bb.getSellPrice());
				print.println();
				print.println("\nQuantity: "+bb.getStock());
			}
			print.println("-----------------");
			print.println();
			print.println();
			print.println("TOTAL: $"+price);
			print.println("DATE: "+date);
			print.close();
		}catch(IOException ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Bill{username=" + acc.getUsername() +", date="+getDateString()+", price="+price+"}";
	}
	
	public String getDateString() {
		String dateString = date.toString().replaceAll(" ", "_");
		dateString = dateString.replaceAll(":", "_");
		dateString = dateString.replaceAll("_CET", "");
		return dateString;
	}
	

	public Account getAcc() {
		return acc;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public Date getDate() {
		return date;
	}

	/*public int getQuantity() {
		return quantity;
	}*/

	public double getPrice() {
		return price;
	}

	@Override
	public boolean exists() {
		for(Bill temp:bills) if( temp.equals(this)) return true;
		return false;
	}
	
	public void addBooks(ArrayList<Book> books) {
		for(Book b : books) {
			this.books.add(b);
		}
	}
	
	public String printBooksToBill() {
		String print="";
		for(Book temp : books) {
			print+=temp.toStringBill()+"\nPrice: $"+temp.getSellPrice()+"\n\n\n";
		}
		return print;
	}
	
	private double getTotalPrice() {
		double price = 0.0;
		for(Book temp : books) {
			price+= (temp.getSellPrice() * temp.getStock());
		}
		return price;
	}
	
	public int getQuantity1() {
		int total=0;
		for(Book temp :books) {
			total +=temp.getStock();
		}
		return total;
	}
	
	public static boolean deleteAll() {
		bills.clear();
		//System.out.println(getBooks());
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			//System.out.println(getBooks());
			oos.close();
		}catch(IOException ex) {return false;}
		return true;
	}
	
	public String getAccountUsername() {
		return acc.getUsername();
	}
	public String getAccountPassword() {
		return acc.getPassword();
	}
	public String getAccountStatus() {
		return acc.getPassword();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
}
