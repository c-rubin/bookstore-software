package application.bookstore.views;

import application.bookstore.controllers.AccountController;
import application.bookstore.models.Account;
import application.bookstore.models.Status;
import application.bookstore.ui.CreateButton;
import application.bookstore.ui.DeleteButton;
import application.bookstore.ui.EditButton;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AccountView extends View {
	
	private final Account currentAccount;
	
	private final BorderPane borderPane = new BorderPane();
	private final HBox formPane = new HBox();
	private final TextField usernameFld = new TextField();
	private final TextField passwordFld = new TextField();

	

	private final ComboBox<Status> statusBox = new ComboBox<>();
	
	private final Button saveBtn = new CreateButton();
    private final Button deleteBtn = new DeleteButton();
    private final Button editBtn = new EditButton();
	
	private final TableView<Account> tableView = new TableView<>();
	private final TableColumn<Account, String> usernameCol = new TableColumn<>("Username");
	private final TableColumn<Account, String> passwordCol = new TableColumn<>("Password");
	private final TableColumn<Account, String> statusCol = new TableColumn<>("Status");
	
	private final Label resultLabel = new Label("");
	
	
	
	public AccountView(Account currentAccount) {
		this.currentAccount=currentAccount;
		 setTableView();
	     setForm();
	     new AccountController(this,currentAccount);
	}
	
	
	private void setTableView() {
	        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	        tableView.setEditable(true);
	        tableView.setItems(FXCollections.observableArrayList(Account.getAccounts()));

	        usernameCol.setCellValueFactory(
	                new PropertyValueFactory<>("username")
	        );
	        // to edit the value inside the table view
	        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());

	        passwordCol.setCellValueFactory(
	                new PropertyValueFactory<>("password")
	        );
	        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());

	        statusCol.setCellValueFactory(
	                new PropertyValueFactory<>("statusString")
	        );
	        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());

	        tableView.getColumns().addAll(usernameCol, passwordCol, statusCol);
	    }

	public TextField getUsernameFld() {
		return usernameFld;
	}


	public TextField getPasswordFld() {
		return passwordFld;
	}


	public ComboBox<Status> getStatusBox() {
		return statusBox;
	}


	public Button getSaveBtn() {
		return saveBtn;
	}


	public Button getDeleteBtn() {
		return deleteBtn;
	}


	public Button getEditBtn() {
		return editBtn;
	}


	public TableView<Account> getTableView() {
		return tableView;
	}


	public Label getResultLabel() {
		return resultLabel;
	}


	private void setForm() {
		statusBox.getItems().add(Status.LIBRARIAN);
		statusBox.getItems().add(Status.MANAGER);
		statusBox.getItems().add(Status.ADMIN);
		
		
		
		 formPane.setPadding(new Insets(20));
	     formPane.setSpacing(20);
	     formPane.setAlignment(Pos.CENTER);
	     
	    usernameFld.setPromptText("Enter username");
		passwordFld.setPromptText("Enter password");

		
		Label usernameLbl = new Label("Username ", usernameFld);
        usernameLbl.setContentDisplay(ContentDisplay.TOP);
        Label passwordLbl = new Label("Password ", passwordFld);
        passwordLbl.setContentDisplay(ContentDisplay.TOP);
        Label statusLbl = new Label("Status", statusBox);
        statusLbl.setContentDisplay(ContentDisplay.TOP);
        
        formPane.getChildren().addAll(usernameLbl,passwordLbl,statusLbl,saveBtn,deleteBtn,editBtn);
	}
	@Override
	public Parent getView() {
		borderPane.setCenter(tableView);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        vBox.getChildren().addAll(formPane, resultLabel);
        borderPane.setBottom(vBox);

		
		
		
		return borderPane;
	}

}
