package application.bookstore.extra;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TextFieldHandler {
	//checks if empty and nondigits and too large
	public static boolean intFld(TextField fld, Label statusLbl, String intName) {

		String testInt = fld.getText();
		if( testInt.length() == 0) {
			setLabelText(statusLbl,"Please fill all of the fields!",Color.DARKRED);
			return false;
		}
		else {
			//check if test contains non digit characters
			if(! testInt.matches("^[0-9]*$")){
				setLabelText(statusLbl,"Enter a valid "+ intName +" number",Color.DARKRED);
				return false;
			}
			else {
				if(testInt.length() > 9) {
					setLabelText(statusLbl,intName + " number is too large!",Color.DARKRED);
					return false;
				}else {/*
				int intVal = Integer.parseInt(testInt);
				
				
				if(intVal <0) {
					statusLbl.setText("Enter a valid quantity number");
					statusLbl.setTextFill(Color.DARKRED);
				}else {
					Book currentBook = null;
					
					for(Book temp : Book.getBooks()) {
						if(temp.getIsbn().equals(isbn)) {
							currentBook = temp;
							
							if(currentBook.sellStock(quantity)) {
							Bill bill = new Bill(currentAccount,currentBook,quantity);
							
							if(bill.printBill()) {
								statusLbl.setText("Bill successfully printed!");
								statusLbl.setTextFill(Color.DARKGREEN);
								bill.saveInFile();
							}else {
								statusLbl.setText("Bill printing failed!");
								statusLbl.setTextFill(Color.DARKRED);
							}
							}else {
								statusLbl.setText("Books are out of stock!");
								statusLbl.setTextFill(Color.DARKRED);
							}
						}
					}
				}
			
			*/return true;}}
			}
	
	}

	//only checks if nondigits and too large
	public static boolean intFldNull(TextField fld, Label statusLbl, String intName) {
		String testInt = fld.getText();
		if(! testInt.matches("^[0-9]*$")){
			setLabelText(statusLbl,"Enter a valid "+ intName +" number",Color.DARKRED);
			return false;
		}
		else {
			if(testInt.length() > 9) {
				setLabelText(statusLbl,intName + " number is too large!",Color.DARKRED);
				return false;
			}else return true;
		}
	}
		
	
	//checks if empty
	public static boolean stringFld (TextField fld, Label statusLbl) {
		String test = fld.getText();
		
		if(test.length() == 0) {
			setLabelText(statusLbl,"Please fill all of the fields!",Color.DARKRED);
			return false;
		}
		
		return true;
	}
	
	//checks if empty and nondigits and too large
	public static boolean doubleFld(TextField fld, Label statusLbl, String doubleName) {

		String testDouble = fld.getText();
		if( testDouble.length() == 0) {
			setLabelText(statusLbl,"Please fill all of the fields!",Color.DARKRED);
			return false;
		}
		else {
			//check if testQ contains non digit characters
			if(! testDouble.matches("^[0-9.]*$")){
				setLabelText(statusLbl,"Enter a valid "+ doubleName +" number",Color.DARKRED);
				return false;
			}
			else {
				if(testDouble.length() > 9) {
					setLabelText(statusLbl,doubleName + " number is too large!",Color.DARKRED);
					return false;
				}else {return true;}}
			}
	
	}
	
	//only checks if nondigits and too large
	public static boolean doubleFldNull(TextField fld, Label statusLbl, String doubleName) {
		String testDouble = fld.getText();
		if(! testDouble.matches("^[0-9.]*$")){
			setLabelText(statusLbl,"Enter a valid "+ doubleName +" number",Color.DARKRED);
			return false;
		}
		else {
			if(testDouble.length() > 9) {
				setLabelText(statusLbl,doubleName + " number is too large!",Color.DARKRED);
				return false;
			}else return true;
		}
	}
	
	//sets text on labels (mostly used for results, for example TASK 1 FAILED)
	public static void setLabelText(Label statusLbl,String text, Color color) {
		statusLbl.setText(text);
		statusLbl.setTextFill(color);
	}
	
	public static boolean comboBox(ComboBox box,Label statusLbl, String boxName ) {
		if(box.getValue()==null) {
			setLabelText(statusLbl,"Select an item from the "+boxName+" box!",Color.DARKRED);
			return false;
		}
		return true;
	}
}
