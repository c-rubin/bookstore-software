package application.bookstore.views;


//import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class LoginView extends View {

	Font font = Font.font("Old English Text MT",FontWeight.BOLD,20);
	
	private final GridPane pane = new GridPane();
	GridPane pane2 = new GridPane();
	
	private final TextField usernameFld = new TextField();
	private final TextField passwordFld = new TextField();
	
	private final Label usernameLbl = new Label("Username:");
	private final Label passwordLbl = new Label("Password:");
	private final Text loginMenuTxt = new Text("Welcome to our Bookstore!");
	private final Button loginBtn = new Button("Log in");
	
	private final Label errorLbl = new Label();
	
	public TextField getUsernameFld (){
		return usernameFld;
	}
	public TextField getPasswordFld() {
		return passwordFld;
	}
	public Button getLoginBtn() {
		return loginBtn;
	}
	
	public LoginView(){
		setView();
	}
	
	private void setView() {
		//HBox usernameHbox = new HBox(10);
		//HBox passwordHbox = new HBox(10);
		

		usernameFld.setPromptText("Enter username");
		passwordFld.setPromptText("Enter password");
		loginMenuTxt.setFont(font);
		
		//usernameHbox.getChildren().addAll(usernameLbl,usernameFld);
		//passwordHbox.getChildren().addAll(passwordLbl,passwordFld);
		pane.add(loginMenuTxt, 0, 0);//col, row,colspan,rowspan
		//pane.add(usernameHbox, 0, 1,3,1);
		pane.add(usernameLbl, 0, 1);
		pane.add(usernameFld, 1, 1,2,1);
		//pane.add(passwordHbox, 0, 2,3,1);
		pane.add(passwordLbl, 0, 2);
		pane.add(passwordFld, 1, 2,2,1);
		pane2.add(pane, 0, 0,3,3);
		pane2.add(loginBtn, 2, 3);
		pane.setHgap(-150);
		
		errorLbl.setBackground(new Background(new BackgroundFill(Color.TOMATO, null, null)));
		pane2.add(errorLbl, 2, 4);
		pane.setVgap(20);
		pane2.setVgap(20);
		pane2.setHgap(29);
		
		pane2.setAlignment(Pos.CENTER);
		//pane.setPadding(new Insets(20,200,200,20));
	}
	
	@Override
	public Parent getView() {
		return pane2;
	}
	public Label getErrorLbl() {
		// TODO Auto-generated method stub
		return errorLbl;
	}

}
