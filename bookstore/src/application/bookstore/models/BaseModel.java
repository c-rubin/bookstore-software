package application.bookstore.models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import application.bookstore.extra.AppendObject;

public abstract class BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean save(File dataFile) {
        if (!isValid())
            return false;
        try {
            ObjectOutputStream outputStream;
            FileOutputStream fileOutputStream = new FileOutputStream(dataFile, true);
            //if empty normal OOS, else call it from subclass
            if (dataFile.length() == 0)outputStream = new ObjectOutputStream(fileOutputStream);
            else outputStream = new AppendObject(fileOutputStream);
            outputStream.writeObject(this);
            
            outputStream.close();
        } catch (IOException  e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }
	
	
	
	
	public abstract boolean saveInFile();
	public abstract boolean isValid();
	public abstract boolean deleteFromFile();
	public abstract boolean exists();
	
	
}
