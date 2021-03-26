package sample;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomePage {
    public static void Home() throws FileNotFoundException {

        Stage window = new Stage();
        window.setTitle("Financial Calculator");

        //Adding a pane for main page
        Pane paneHome = new Pane();

        //Adding grid pane
        GridPane mainPageGrid = new GridPane();

        //set size
        mainPageGrid.setMinSize(800,600);

        //setting grid alignment
        mainPageGrid.setAlignment(Pos.CENTER);
        mainPageGrid.setVgap(20);

        //pane --> grid pane
        paneHome.getChildren().add(mainPageGrid);


        //Label for Title in the main page
        Label labelTitle = new Label("Financial Calculator");


        //Button for Mortgage Calculator
        Button buttonMortgage = new Button("Mortgage");

        //Button for Simple Savings Calculator
        Button buttonSimpleSavings = new Button("Simple Savings");

        //Button for Compound Interest Savings Calculator
        Button buttonCompoundSavings = new Button("Compound Interest Savings");

        //Button for Loan Calculator
        Button buttonLoan = new Button("Loan");

        //Button for view History
        Button buttonHistory = new Button("History");

        //Button for Help
        Button buttonHelp = new Button("Help");

        //Button for Quit
        Button buttonQuit = new Button("Quit");


        //setting the columns and rows in grid pane
        mainPageGrid.add(labelTitle,2,0);
        mainPageGrid.add(buttonSimpleSavings,12,2);
        mainPageGrid.add(buttonCompoundSavings,12,3);
        mainPageGrid.add(buttonLoan,12,4);
        mainPageGrid.add(buttonMortgage,12,5);
        mainPageGrid.add(buttonHistory,12,6);
        mainPageGrid.add(buttonHelp,12,7);
        mainPageGrid.add(buttonQuit,12,8);


        //Adding image for main page
        Image homePageImage = new Image(new FileInputStream("E:\\pp2 - cw\\cw 1\\src\\sample\\download1.jpg"));
        ImageView homeImage = new ImageView(homePageImage);
        paneHome.getChildren().add(homeImage);
        homeImage.setFitHeight(300);
        homeImage.setFitWidth(350);
        homeImage.setLayoutX(50);
        homeImage.setLayoutY(180);


        Scene sceneMain = new Scene(paneHome);
        window.setScene(sceneMain);
        window.show();

        //Add the classes for styling
        labelTitle.getStyleClass().add("main-title");
        buttonMortgage.getStyleClass().add("main-Button");
        buttonSimpleSavings.getStyleClass().add("main-Button");
        buttonCompoundSavings.getStyleClass().add("main-Button");
        buttonLoan.getStyleClass().add("main-Button");
        buttonHistory.getStyleClass().add("main-Button");
        buttonHelp.getStyleClass().add("main-Button");
        buttonQuit.getStyleClass().add("main-Button");

        //Style sheet
        sceneMain.getStylesheets().add(HomePage.class.getResource("Styler.css").toExternalForm());


        //Setting button actions

        buttonMortgage.setOnAction(event -> {
            window.close();
            MortgageCalculator.mortgage();
    });

        buttonSimpleSavings.setOnAction(event -> {
            window.close();
            SimpleSavingCalculator.SimpleSavings();
        });

        buttonCompoundSavings.setOnAction(event -> {
            window.close();
            CompoundSavingCalculator.CompoundSavings();
        });

        buttonLoan.setOnAction(event -> {
            window.close();
           LoanCalculator.AutoLoan();
        });

        buttonHistory.setOnAction(event -> {
            window.close();
            try {
                History.RecordingHistory();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        buttonHelp.setOnAction(event -> {
            window.close();
            try {
                HelpView.Help();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });


        buttonQuit.setOnAction(event -> {window.close();});




    }
}
