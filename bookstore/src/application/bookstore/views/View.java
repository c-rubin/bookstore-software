package application.bookstore.views;

import application.bookstore.models.Account;
import javafx.scene.Parent;

public abstract class View {
    private Account currentAccount;

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public abstract Parent getView();
}