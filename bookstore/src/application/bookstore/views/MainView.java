package application.bookstore.views;

import application.bookstore.models.Status;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class MainView extends View {

	
	 @Override
	    public Parent getView() {
	        BorderPane borderPane = new BorderPane();
	        TabPane tabPane = new TabPane();
	        Tab accountTab = new Tab("Accounts");
	        accountTab.setContent(new AccountView(getCurrentAccount()).getView());
	        Tab bookTab = new Tab("Books");
	        BookView bookView = new BookView();
	        ManagerView managerView = new ManagerView();
	        bookTab.setContent(bookView.getView());

	       Tab librarianTab = new Tab("Librarian");
	        librarianTab.setContent(new LibrarianView(getCurrentAccount(), bookView, managerView).getView());
	        Tab managerTab = new Tab("Management & Statistics");
	        managerTab.setContent(managerView.getView());
	        Status currentStatus = (getCurrentAccount() != null ? getCurrentAccount().getStatus() : null);
	        
	        
	        if (currentStatus != null) {
	        	
	        	
	        	
	            if (currentStatus == Status.ADMIN) {

	                tabPane.getTabs().add(accountTab);
	            }
	            if (currentStatus == Status.MANAGER || currentStatus == Status.ADMIN) {
	                tabPane.getTabs().add(managerTab);
	                tabPane.getTabs().add(bookTab);
	            }
	            tabPane.getTabs().add(librarianTab);
	        }
	        
	        //disable closing the tabs
	        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	        borderPane.setTop(tabPane);
	        borderPane.setBottom(new StackPane(new Text("Hello "+getCurrentAccount().getUsername() + ", welcome to our bookstore!")));
	        return borderPane;
	    }

}
