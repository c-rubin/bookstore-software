package application.bookstore.views;

import application.bookstore.controllers.LibrarianController;
import application.bookstore.models.Account;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LibrarianView extends View {

	private final HBox formPane = new HBox();
	private final TextField isbnFld = new TextField();
	private final TextField quantityFld = new TextField();
	private final Account currentAccount;
	private final BookView bookView;
	private final ManagerView managerView;
	
	private final Button addBtn = new Button("Add to bill");
	private final Button printBtn = new Button("Print");
	
	private final Label resultLbl = new Label("");
	
	
	public LibrarianView(Account currentAccount, BookView bookView, ManagerView managerView) {
		this.currentAccount=currentAccount;
		this.bookView = bookView;
		this.managerView = managerView;
		setForm();
		new LibrarianController(this,currentAccount, bookView,managerView);
	}
	
	
	@Override
	public Parent getView() {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
		vBox.getChildren().addAll(formPane,resultLbl);
		return vBox;
	}
	
	private void setForm() {
		formPane.setPadding(new Insets(20));
        formPane.setSpacing(20);
        formPane.setAlignment(Pos.CENTER);
        isbnFld.setPromptText("Enter ISBN");
        Label isbnLbl = new Label("ISBN", isbnFld);
        isbnLbl.setContentDisplay(ContentDisplay.TOP);
        quantityFld.setPromptText("Enter quantity");
        Label quantityLbl = new Label("Quantity", quantityFld);
        quantityLbl.setContentDisplay(ContentDisplay.TOP);
        printBtn.setDisable(true);
        formPane.getChildren().addAll(isbnLbl,quantityLbl,addBtn,printBtn);
	}
	
	
	
	
	public TextField getIsbnFld() {
		return isbnFld;
	}
	
	public TextField getQuantityFld() {
		return quantityFld;
	}
	
	public Label getResultLbl() {
		return resultLbl;
	}
	
	public Button getPrintBtn() {
		return printBtn;
	}
	
	public Button getAddBtn() {
		return addBtn;
	}

}
