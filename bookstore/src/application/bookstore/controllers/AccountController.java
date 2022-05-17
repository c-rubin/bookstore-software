package application.bookstore.controllers;

import java.util.List;

import application.bookstore.extra.TextFieldHandler;
import application.bookstore.models.Account;
import application.bookstore.models.Status;
import application.bookstore.views.AccountView;
import javafx.scene.paint.Color;

public class AccountController {
	
	private final Account currentAccount;
	private final AccountView accountView;
    public AccountController(AccountView accountView, Account currentAccount) {
        this.accountView = accountView;
        this.currentAccount=currentAccount;
        setSaveListener();
        setDeleteListener();
        setEditListener();
    }
	
	
	
	
	private void setSaveListener() {
		accountView.getSaveBtn().setOnAction(e -> {
        	//make sure fields arent empty and correct format in prices
        	if( (TextFieldHandler.stringFld(accountView.getUsernameFld(), accountView.getResultLabel()) && TextFieldHandler.stringFld(accountView.getPasswordFld(), accountView.getResultLabel()) && TextFieldHandler.comboBox(accountView.getStatusBox(), accountView.getResultLabel(),"Statu"))) {
        		String username = accountView.getUsernameFld().getText();
        		String password = accountView.getPasswordFld().getText();
        		Status status = accountView.getStatusBox().getValue();
        		Account possibleAcc = new Account(username,password,status);
        		if(possibleAcc.exists()) TextFieldHandler.setLabelText(accountView.getResultLabel(), "This username already exists!", Color.DARKRED);
        		else {
        			if(possibleAcc.saveInFile()) {
        				accountView.getTableView().getItems().add(possibleAcc);
        				TextFieldHandler.setLabelText(accountView.getResultLabel(), "Account created successfuly!", Color.DARKGREEN);
        				resetFields();
        			}else TextFieldHandler.setLabelText(accountView.getResultLabel(), "Account creation failed!", Color.DARKRED);
        		}
        	}

            
        });
    }

    private void resetFields() {
    	accountView.getUsernameFld().setText("");
    	accountView.getPasswordFld().setText("");
    	accountView.getStatusBox().setValue(null);
    	
        
    	accountView.getUsernameFld().setPromptText("Enter Username");
    	accountView.getPasswordFld().setPromptText("Enter Password");
    }
    
    private void setDeleteListener() {
    	accountView.getDeleteBtn().setOnAction(e -> {
    		 List<Account> itemsToDelete = List.copyOf(accountView.getTableView().getSelectionModel().getSelectedItems());
    		 if(itemsToDelete.get(0).getUsername().equals("admin")) TextFieldHandler.setLabelText(accountView.getResultLabel(), "[Error] Account \"admin\" is the default account for this program and is not deletable!", Color.DARKRED);//default "admin" account
    		 else if(itemsToDelete.get(0).getUsername().equals(currentAccount.getUsername()))TextFieldHandler.setLabelText(accountView.getResultLabel(), "[Error] You cannot delete the account which you are using!", Color.DARKRED);//prevent possible malfunction of the program
    		 else {
             if (itemsToDelete.get(0).exists()) {
            	 if (itemsToDelete.get(0).deleteFromFile()) {
            		 accountView.getTableView().getItems().remove(itemsToDelete.get(0));
          		   TextFieldHandler.setLabelText(accountView.getResultLabel(), "Account removed successfully", Color.DARKGREEN);
          	   } else TextFieldHandler.setLabelText(accountView.getResultLabel(), "Account couldn't be deleted", Color.DARKRED);
             }else TextFieldHandler.setLabelText(accountView.getResultLabel(), "Account doesn't exist", Color.DARKRED);
    	}});
    }
    
    private void setEditListener() {
    	accountView.getEditBtn().setOnAction(e -> {
    		
    		try {
    		List<Account> itemsToEdit = List.copyOf(accountView.getTableView().getSelectionModel().getSelectedItems());
    		if(itemsToEdit.get(0).getUsername().equals("admin"))TextFieldHandler.setLabelText(accountView.getResultLabel(), "[Error] Account \"admin\" is the default account for this program and is not editable!", Color.DARKRED);
    		else {
    			String username = accountView.getUsernameFld().getText();
        		String password = accountView.getPasswordFld().getText();
        		Status status = accountView.getStatusBox().getValue();
        		if(! itemsToEdit.get(0).exists())TextFieldHandler.setLabelText(accountView.getResultLabel(), "Account doesn't exist", Color.DARKRED);
        		else {
        			for(Account temp: Account.getAccounts()) {
        				if(temp.getUsername().equals(itemsToEdit.get(0).getUsername())) {
        					if(! username.equals("")) itemsToEdit.get(0).setUsername(username);
        					if(! password.equals("")) itemsToEdit.get(0).setPassword(password);
        					try {
        					if(! status.equals(null)) itemsToEdit.get(0).setStatus(status);
        					}catch(NullPointerException ex) {
        						
        					}
        					
        					if(Account.saveChanges()) {
        						accountView.getTableView().refresh();
        						TextFieldHandler.setLabelText(accountView.getResultLabel(), "Account edited successfuly", Color.DARKGREEN);
        						resetFields();
        					}else TextFieldHandler.setLabelText(accountView.getResultLabel(), "Account couldn't be edited", Color.DARKRED);
        				}
        			}
        		}
    		}
    		
    	}catch(ArrayIndexOutOfBoundsException ex) {
    		TextFieldHandler.setLabelText(accountView.getResultLabel(), "Select an account by clicking on it!", Color.DARKRED);
    	}//catch(java.lang.NullPointerException ex) {
    		//TextFieldHandler.setLabelText(accountView.getResultLabel(), "Please fill at least one text field!", Color.DARKRED);
    	//}	
    	});
    }
}
