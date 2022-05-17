package application.bookstore.views;

import application.bookstore.controllers.BookController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import application.bookstore.models.Book;
import application.bookstore.ui.CreateButton;
import application.bookstore.ui.DeleteButton;
import application.bookstore.ui.EditButton;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class BookView extends View {
	private final BorderPane borderPane = new BorderPane();

	private final HBox formPane = new HBox();
	private final TableView<Book> tableView = new TableView<>();
	private final TextField isbnFld = new TextField();
	private final TextField titleFld = new TextField();
	private final TextField authorFld = new TextField();
	private final TextField purchPriceFld = new TextField();
	private final TextField sellPriceFld = new TextField();

	
	private final Button saveBtn = new CreateButton();
    private final Button deleteBtn = new DeleteButton();
    private final Button editBtn = new EditButton();
    private final Button deleteAllBtn = new DeleteButton();
    
    private final TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
    private final TableColumn<Book, String> titleCol = new TableColumn<>("Title");
    private final TableColumn<Book, String> authorCol = new TableColumn<>("Author");
    private final TableColumn<Book, Double> purchPriceCol = new TableColumn<>("Purchase Price");
    private final TableColumn<Book, Double> sellPriceCol = new TableColumn<>("Sell Price");
    private final TableColumn<Book, Integer> quantityCol = new TableColumn<>("Quantity");
    private final Label resultLabel = new Label("");

	
	
	
	private void setTableView() {
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(Book.getBooks()));

        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("isbn")
        );
        // to edit the value inside the table view
        isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        purchPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("purchPrice")
        );
        purchPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        sellPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("sellPrice")
        );
        sellPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        authorCol.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );
        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tableView.getColumns().addAll(isbnCol, titleCol, purchPriceCol, sellPriceCol, authorCol, quantityCol);
    }
	
	public BookView() {
        setTableView();
        setForm();
        // inject the controller
        new BookController(this);
    }
	
	private void setForm() {
		deleteAllBtn.setText("Delete All");
        formPane.setPadding(new Insets(20));
        formPane.setSpacing(20);
        formPane.setAlignment(Pos.CENTER);
        
        isbnFld.setPromptText("Enter ISBN");
		titleFld.setPromptText("Enter Title");
		authorFld.setPromptText("Enter Author");
		purchPriceFld.setPromptText("Enter Purchase Price");
		sellPriceFld.setPromptText("Enter Sell Price");
        
        Label isbnLbl = new Label("ISBN ", isbnFld);
        isbnLbl.setContentDisplay(ContentDisplay.TOP);
        Label titleLbl = new Label("Title ", titleFld);
        titleLbl.setContentDisplay(ContentDisplay.TOP);
        Label purchPriceLbl = new Label("Purchase price", purchPriceFld);
        purchPriceLbl.setContentDisplay(ContentDisplay.TOP);
        Label sellPriceLbl = new Label("Sell price", sellPriceFld);
        sellPriceLbl.setContentDisplay(ContentDisplay.TOP);
        Label authorLbl = new Label("Author",authorFld);
        authorLbl.setContentDisplay(ContentDisplay.TOP);

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        

        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        
        hbox.getChildren().addAll(saveBtn, deleteBtn, editBtn);
        vbox.getChildren().addAll(hbox,deleteAllBtn);
        formPane.getChildren().addAll(isbnLbl, titleLbl, purchPriceLbl, sellPriceLbl,authorLbl, vbox);
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

	
	
	
	
	
	
	
	public TextField getIsbnFld() {
		return isbnFld;
	}
	
	public TextField getTitleFld() {
		return titleFld;
	}
	
	public TextField getAuthorFld() {
		return authorFld;
	}
	
	public TextField getPurchPriceFld() {
		return purchPriceFld;
	}
	
	public TextField getSellPriceFld() {
		return sellPriceFld;
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
    
    public TableView<Book> getTableView() {
        return tableView;
    }
    
    public Label getResultLabel() {
        return resultLabel;
    }

	public Button getDeleteAllBtn() {
		return deleteAllBtn;
		
	}

}
