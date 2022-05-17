package application.bookstore;

import application.bookstore.controllers.LoginController;
import application.bookstore.views.LoginView;
import application.bookstore.views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		LoginView login = new LoginView();
		LoginController loginCont = new LoginController(login,new MainView(),stage);
		Scene scene = new Scene(login.getView(),1100,600);
		
		
		
		stage.setMinHeight(600);
		stage.setMinWidth(1100.0);
		stage.setScene(scene);
		stage.setTitle("Bookstore");
		stage.getIcons().add(new Image("file:images/stage_icon.png"));
		stage.show();
	}
	
	public static void showParameters(double w, double h){
		System.out.println("weight: "+w+"     height: "+h);
	}

	public static void main(String[] args) {
		
		
		launch();
	}
	
	
}
