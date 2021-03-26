package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.Scanner;

public class History {
    public static void RecordingHistory() throws FileNotFoundException {

       Stage window = new Stage();
       window.setTitle("History");

        //Adding Grid Pane
        GridPane historyGrid = new GridPane();
        historyGrid.setHgap(20);
        historyGrid.setVgap(20);

        //Creating new Scene to history window
        Scene sceneHistory = new Scene(historyGrid, 800, 600);
        sceneHistory.getStylesheets().add(History.class.getResource("styler.css").toExternalForm());

        //-----------------Simple Savings History ----------------------------------

        //Creating tab pane
        TabPane tabPane = new TabPane();
        tabPane.getStyleClass().add("tab-pane");

        //Creating new Tabs
        Tab tabSavings = new Tab("Simple Savings");

        //Creating scroll pane
        ScrollPane scrollSavings = new ScrollPane();    //<--- Otherwise we can't see all the history

        //Creating pane
        Pane paneSavings = new Pane();

        //Creating a label to display history
        Label labelSavings = new Label();



        //Adding Scroll pane to tab
        tabSavings.setContent(scrollSavings);

        //Adding pane to Scroll pane
        scrollSavings.setContent(paneSavings);

        //Add label to pane
        paneSavings.getChildren().add(labelSavings);
        tabPane.setMaxWidth(1000);


        // ------------------  Compound History  -----------------------

        Tab tabCompound = new Tab("Compound Savings");
        ScrollPane scrollCompound = new ScrollPane();
        Pane paneCompound = new Pane();
        Label labelCompound = new Label();

        tabCompound.setContent(scrollCompound);
        scrollCompound.setContent(paneCompound);
        paneCompound.getChildren().add(labelCompound);


        // ----------------- Loan History -------------------------------

       Tab tabLoan = new Tab("Loan");
       ScrollPane scrollLoan = new ScrollPane();
       Pane paneLoan = new Pane();
       Label labelLoan = new Label();

       tabLoan.setContent(scrollLoan);
       scrollLoan.setContent(paneLoan);
       paneLoan.getChildren().add(labelLoan);


       // ------------------ Mortgage History ---------------------------

        Tab tabMortgage = new Tab("Mortgage");
        ScrollPane scrollMortgage = new ScrollPane();
        Pane paneMortgage = new Pane();
        Label labelMortgage = new Label();

        tabMortgage.setContent(scrollMortgage);
        scrollMortgage.setContent(paneMortgage);
        paneMortgage.getChildren().add(labelMortgage);

        tabSavings.getStyleClass().add("tab");





     //Adding created tabs to tab pane
        tabPane.getTabs().addAll(tabSavings,tabCompound,tabLoan,tabMortgage);
        tabSavings.setClosable(false);      //Then user can't close the tabs
        tabCompound.setClosable(false);
        tabLoan.setClosable(false);
        tabMortgage.setClosable(false);


        window.setScene(sceneHistory);
        window.show();

        //Adding menu bar
        historyGrid.add(ButtonsMenu.DisplayMenuButtons(window,"HistoryWindow"),1,1,8,1);
        historyGrid.add(tabPane,1,4);

        //Reading the files
       labelSavings.setText(FileHandling.readFiles("SavingsData"));
       labelCompound.setText(FileHandling.readFiles("CompoundSavings"));
       labelLoan.setText(FileHandling.readFiles("LoanData"));
       labelMortgage.setText(FileHandling.readFiles("MortgageData"));











    }


}
