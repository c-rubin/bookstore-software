package application.bookstore.extra;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AppendObject extends ObjectOutputStream {

	public AppendObject(FileOutputStream fileOutputStream) throws IOException {
		super(fileOutputStream);
	}
	public void writeStreamHeader()//no headers, so now Im able to append
    {
        // don't do anything
    }

}
