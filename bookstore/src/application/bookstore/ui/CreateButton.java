package application.bookstore.ui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CreateButton extends Button {
	public CreateButton() {
        super.setText("Create");
        super.setGraphic(getImage());
        super.setTextFill(Color.WHITE);
        super.setStyle("-fx-background-color: green");
    }

	private ImageView getImage() {
    	Image im = new Image("file:images/save_icon.png");
        ImageView imageView = new ImageView(im);
        return imageView;
    }
}
