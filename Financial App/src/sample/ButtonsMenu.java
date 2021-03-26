package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ButtonsMenu {


    public static Pane DisplayMenuButtons(Stage window, String name){
        Pane paneMenu = new Pane();

        //Button for back
        Button buttonBack = new Button("Back");
        buttonBack.setOnAction(event -> {
            window.close();
            try {
                HomePage.Home();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        //Button for Simple Saving Calculator
        Button buttonSSaving = new Button("Simple Savings");
        buttonSSaving.setOnAction(event -> {
            if (!(name.equals("SimpleSavingsWindow"))){
                window.close();
                SimpleSavingCalculator.SimpleSavings();

            }

        });

        //Button for compound savings calculator
        Button buttonCSavings = new Button("Compound Savings");
        buttonCSavings.setOnAction(event -> {
            if (!(name.equals("CompoundSavingsWindow"))){
                window.close();
                CompoundSavingCalculator.CompoundSavings();
            }
        });

        //Button for Loan Calculator
        Button buttonLoan = new Button("Loan");
        buttonLoan.setOnAction(event -> {
            if (!(name.equals("LoanWindow"))){
                window.close();
                LoanCalculator.AutoLoan();
            }
        });

        //Button for Mortgage Calculator
        Button buttonMortgage = new Button("Mortgage");
        buttonMortgage.setOnAction(event -> {
           if (!(name.equals("MortgageWindow"))){
               window.close();
               MortgageCalculator.mortgage();
           }
        });

        //Button for See History
        Button buttonHistory = new Button("History");
        buttonHistory.setOnAction(event -> {
            if (!(name.equals("HistoryWindow"))) {
                window.close();
                try {
                    History.RecordingHistory();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        //Button for Help
        Button buttonHelp = new Button("Help");
        buttonHelp.setOnAction(event -> {
            if (!(name.equals("HelpWindow"))){
                window.close();
                try {
                    HelpView.Help();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });


        //placing the buttons in grid pane

        GridPane menuGrid = new GridPane();
        paneMenu.getChildren().add(menuGrid);
        menuGrid.setHgap(20);
        menuGrid.add(buttonBack,1,1);
        menuGrid.add(buttonSSaving,2,1);
        menuGrid.add(buttonCSavings,3,1);
        menuGrid.add(buttonLoan,4,1);
        menuGrid.add(buttonMortgage,5,1);
        menuGrid.add(buttonHistory,6,1);
        menuGrid.add(buttonHelp,7,1);

        //Adding classes
        buttonBack.getStyleClass().add("button-menu");
        buttonSSaving.getStyleClass().add("button-menu");
        buttonCSavings.getStyleClass().add("button-menu");
        buttonLoan.getStyleClass().add("button-menu");
        buttonMortgage.getStyleClass().add("button-menu");
        buttonHistory.getStyleClass().add("button-menu");
        buttonHelp.getStyleClass().add("button-menu");


        return paneMenu;

    }

}
