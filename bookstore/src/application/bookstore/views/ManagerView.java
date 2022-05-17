package application.bookstore.views;


import application.bookstore.models.Account;
import application.bookstore.models.Bill;
import application.bookstore.models.Book;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class ManagerView extends View {
	private final BorderPane borderPane = new BorderPane();
	private final HBox formPane = new HBox();
	
	private final TableView<Bill> tableView = new TableView<>();
	private final TableColumn<Bill, String> usernameCol = new TableColumn<>("Username");
	private final TableColumn<Bill, String> dateCol = new TableColumn<>("Date");
	private final TableColumn<Bill, String> statusCol = new TableColumn<>("Status");
	private final TableColumn<Bill, Integer> quantityCol = new TableColumn<>("Quantity");
	private final TableColumn<Bill, Double> priceTotalCol = new TableColumn<>("Price");
	private final Button btn = new Button("Refresh");
	
	private final Label statisticsLabel = new Label("");
	
	public ManagerView(){

		 setTableView();
	     setForm();
	     setRefreshListener();

	}
	
	
	private void setTableView() {
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(Bill.getBills()));

        usernameCol.setCellValueFactory(
                new PropertyValueFactory<>("accountUsername")
        );
        // to edit the value inside the table view
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("AccountStatus")
        );
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("dateString")
        );
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        priceTotalCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceTotalCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        tableView.getColumns().addAll(usernameCol, statusCol, dateCol,quantityCol,priceTotalCol);
    }

	
	
	private void setForm() {
		/*statusBox.getItems().add(Status.LIBRARIAN);
		statusBox.getItems().add(Status.MANAGER);
		statusBox.getItems().add(Status.ADMIN);*/
		
		
		
		 formPane.setPadding(new Insets(20));
	     formPane.setSpacing(20);
	     formPane.setAlignment(Pos.CENTER);
	     
	    /*usernameFld.setPromptText("Enter username");
		passwordFld.setPromptText("Enter password");
		//statusFld.setPromptText("Enter status");
		
		Label usernameLbl = new Label("Username ", usernameFld);
        usernameLbl.setContentDisplay(ContentDisplay.TOP);
        Label passwordLbl = new Label("Password ", passwordFld);
        passwordLbl.setContentDisplay(ContentDisplay.TOP);
        Label statusLbl = new Label("Status", statusBox);
        statusLbl.setContentDisplay(ContentDisplay.TOP);*/
        
        //formPane.getChildren().addAll(usernameLbl,passwordLbl,statusLbl,saveBtn,deleteBtn,editBtn);
	}
	@Override
	public Parent getView() {
		
		Text stat = new Text("Statistics");
		Text bills = new Text("Bills");
		
		Font font = Font.font("Old English Text MT",FontWeight.BOLD,20);
		stat.setFont(font);
		bills.setFont(font);
		
		
		//borderPane.setCenter(tableView);
        /*VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        vBox.getChildren().addAll(formPane, resultLabel);*/
		VBox vbox1 = new VBox();
		vbox1.setSpacing(10);
		statisticsLabel.setText(getStatistics());
		vbox1.getChildren().addAll(stat,statisticsLabel,btn);
		VBox vbox2 = new VBox();
		vbox2.setSpacing(10);
		vbox2.getChildren().addAll(bills,tableView);
		formPane.setSpacing(10);
		formPane.getChildren().addAll(vbox2,vbox1);
        //borderPane.setCenter(hbox);
		//borderPane.setLeft(tableView);
		borderPane.setCenter(formPane);
		//borderPane.setPadding(new Insets(10));
        //borderPane.setTop(searchView.getSearchPane());
		
		
		
		return borderPane;
	}
	
	
	public TableView<Bill> getTableView() {
		return tableView;
	}
	
	public static String getStatistics() {
		if(    Book.getBooks().size()>0 && Bill.getBills().size()>0) {
		//Account maxPUser = getMaxPriceUser();
		Account maxSUser = getMaxStockUser();
		//Account minPUser = getMinPriceUser();
		Account minSUser = getMinStockUser();
		double totalIncomes = getTotalIncomes();
		int totalBookSells = getTotalSells();
		double totalBookExpenses = getTotalBookExpenses();
		int totalBookStock = getTotalBookStock();
		return ("Account with most book sales: \""+maxSUser.getUsername()+"\"\nAccount with least book sales: \""+minSUser.getUsername()+"\"\nTotal Incomes: $"+totalIncomes+"\nTotal expenses on books: $"+totalBookExpenses+"\nTotal books sold: "+totalBookSells+"\nTotal books in stock: "+totalBookStock);
		}else return "Error: There isn't enough information for statistics to be made!";
	}
	
	public static Account getMaxPriceUser() {
		//get 
		double maxPrice = Bill.getBills().get(0).getPrice(); 
		Account maxUser = Bill.getBills().get(0).getAcc();
		for(Bill a : Bill.getBills()) {
			if(a.getPrice()>maxPrice) { 
				maxPrice = a.getPrice();
				maxUser = a.getAcc();
			}
		}
		return maxUser;
	}
	

	public static Account getMaxStockUser() {
		//get 
		double maxPrice = Bill.getBills().get(0).getQuantity(); 
		Account maxUser = Bill.getBills().get(0).getAcc();
		for(Bill a : Bill.getBills()) {
			if(a.getQuantity()>maxPrice) { 
				maxPrice = a.getQuantity();
				maxUser = a.getAcc();
			}
		}
		return maxUser;
	}
	public static Account getMinStockUser() {
		//get 
		//mir do ishte ti beja rename nga max to min, po pertoja
		double maxPrice = Bill.getBills().get(0).getQuantity(); 
		Account maxUser = Bill.getBills().get(0).getAcc();
		for(Bill a : Bill.getBills()) {
			if(a.getQuantity()<maxPrice) { 
				maxPrice = a.getQuantity();
				maxUser = a.getAcc();
			}
		}
		return maxUser;
	}
	public static Account getMinPriceUser() {
		//get
		//mir do ishte ti beja rename nga max to min, po pertoja
		double maxPrice = Bill.getBills().get(0).getPrice(); 
		Account maxUser = Bill.getBills().get(0).getAcc();
		for(Bill a : Bill.getBills()) {
			if(a.getPrice()<maxPrice) { 
				maxPrice = a.getPrice();
				maxUser = a.getAcc();
			}
		}
		return maxUser;
	}
	
	public static double getTotalIncomes() {
		double pr=0;
		for(Bill a : Bill.getBills()) {
			pr+=a.getPrice();
		}
		return pr;
	}
	public static int getTotalSells() {
		int pr=0;
		for(Bill a : Bill.getBills()) {
			pr+=a.getQuantity();
		}
		return pr;
	}
	public static double getTotalBookExpenses() {
		double pr=0;
		for(Book a : Book.getBooks()) {
			pr+=a.getPurchPrice();
		}
		for(Bill b : Bill.getBills()) {
			for(Book bb : b.getBooks()) {
				pr+=bb.getSellPrice();
			}
		}
		return pr;
	}
	public static int getTotalBookStock() {
		int a = 0;
		for(Book b : Book.getBooks()) {
			a+=b.getStock();
		}
		return a;
	}


	public Button getRefreshBtn() {
		// TODO Auto-generated method stub
		return btn;
	}


	public Label getStatisticsLabel() {
		return statisticsLabel;
	}


	public void setRefreshListener() {
		btn.setOnAction(e->{
			
			
			String a =getStatistics();
			statisticsLabel.setText(a);
			
			
			
		});
	}
}


