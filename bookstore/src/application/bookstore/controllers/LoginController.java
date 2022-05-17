package application.bookstore.controllers;

import application.bookstore.models.Account;
import application.bookstore.views.LoginView;
import application.bookstore.views.View;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
	private final Stage stage;
	private final View nextView;
	private Account currentAccount;
	
	public Account getCurrentAccount() {
		return currentAccount;	
	}
	
	public LoginController(LoginView view, View nextView,Stage stage) {
		this.stage = stage;
		this.nextView = nextView;
		addListener(view);
	}
	
	private void addListener(LoginView view) {
		view.getLoginBtn().setOnAction(e->{
			String username = view.getUsernameFld().getText();
			String password = view.getPasswordFld().getText();
			
			Account potentialAccount = new Account(username,password);
			if((currentAccount = Account.getIfExists(potentialAccount))!= null) {
				nextView.setCurrentAccount(currentAccount);
				stage.setScene(new Scene(nextView.getView()));
			}else view.getErrorLbl().setText("Wrong username or password");
		});
	}
}
