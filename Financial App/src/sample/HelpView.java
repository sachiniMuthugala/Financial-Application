package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelpView {
    public static void Help() throws FileNotFoundException {
        Pane paneHelp = new Pane();

        Scene sceneHelp = new Scene(paneHelp,800,600);

        Stage window = new Stage();

        GridPane helpGrid = new GridPane();
        helpGrid.setAlignment(Pos.CENTER);
        helpGrid.setHgap(20);
        helpGrid.setVgap(20);
        paneHelp.getChildren().add(helpGrid);

        //Adding menu buttons
        helpGrid.add(ButtonsMenu.DisplayMenuButtons(window,"HelpWindow"),1,2,8,1);


        window.setTitle("Help");
        window.setScene(sceneHelp);
        window.show();

        //Adding the image of Help Menu
        Image helpImage = new Image(new FileInputStream("E:\\pp2 - cw\\cw 1\\src\\sample\\HelpImage.jpg"));
        ImageView helpView = new ImageView(helpImage);
        paneHelp.getChildren().add(helpView);
        helpView.setFitHeight(650);
        helpView.setFitWidth(800);

        helpView.setLayoutY(140);

        //Setting the image
        helpView.setPreserveRatio(true);

        //Adding the style sheet
        sceneHelp.getStylesheets().add(HelpView.class.getResource("styler.css").toExternalForm());

    }
}
