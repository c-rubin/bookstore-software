package application.bookstore.extra;

import application.bookstore.models.BaseModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OverwriteFile {
    // write any model to the appropriate file
    public static<T extends BaseModel> boolean overwriteCurrentListToFile(File file, ArrayList<T> data) {
        try {
        	FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        	ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        	for (T entity : data) {
        		outputStream.writeObject(entity);
        		}
        	}catch(IOException ex) {
        		return false;
        	}
        return true;
    }
}