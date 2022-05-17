package application.bookstore.models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import application.bookstore.extra.OverwriteFile;

public class Account extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Status status;
	public static final String FILE_PATH ="data/accounts.ser";
	private static final File DATA_FILE = new File(FILE_PATH);
	private static final ArrayList<Account> accounts = new ArrayList<Account>();
	//array do perdoret vetem pasi fusim objects ne file, ose kur duam te fshijme 1 object nga file
	public Account() {}
	public Account (String username,String password, Status status) {
		this(username,password);
		this.status = status;
		//this.statusString=status.toString();
	}
	public Account (String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Status getStatus() {
    	return status;
    }
    public void setStatus(Status status) {
    	this.status = status;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "username=" + getUsername() +
                ", password=" + getPassword() +
                ", status=" + getStatus() +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account other = (Account) obj;
            return other.getUsername().equals(getUsername());
        }
        return false;
    }
    
    public static ArrayList<Account> getAccounts () {
        if (accounts.size() == 0){//if arraylist of accounts is empty, check file
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    Account temp = (Account) inputStream.readObject();
                    if (temp == null)
                        break;
                    accounts.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of file reached!");
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }
    public static Account getIfExists(Account potentialAccount) {
        for(Account user: getAccounts())
            if (user.equals(potentialAccount) && user.getPassword().equals(potentialAccount.getPassword()))
                return user;
        return null;
    }

    @Override
    public boolean saveInFile() {
    	if (this.exists())return false;
    	boolean saved = super.save(Account.DATA_FILE);
    	if(saved) accounts.add(this);
    	return saved;
    }
    
	@Override
	public boolean isValid() {
		try {
		return getUsername().length() > 0 && getPassword().length()> 0 && getStatus()!= null;}
		catch(NullPointerException ex) {
			return false;
		}
	}
	
	@Override
    public boolean deleteFromFile() {
		if(this.getUsername().equals("admin")) return false;
		accounts.remove(this);
		return OverwriteFile.overwriteCurrentListToFile(DATA_FILE, getAccounts());
    }
	
	@Override
	public boolean exists() {
		for(Account temp:accounts) if( temp.equals(this)) return true;
		return false;
	}
	
	public String getStatusString() {
		return status.toString();
	}
	
	public static boolean saveChanges() {
		return OverwriteFile.overwriteCurrentListToFile(DATA_FILE, getAccounts());
	}
	
}
