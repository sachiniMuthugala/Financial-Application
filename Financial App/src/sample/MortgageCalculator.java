package sample;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class MortgageCalculator {

    //method for check validity of inputs.
    //If user inputs something other than numbers it will gives a warning alert.

    public static void validation(TextField textField1,TextField textField2,TextField textField3,TextField textField4){
        try {
            Double.parseDouble(textField1.getText());
            Double.parseDouble(textField2.getText());
            Double.parseDouble(textField3.getText());
        }
        catch (Exception E){
            Alert alertString = new Alert(Alert.AlertType.WARNING);
            alertString.setHeaderText("Integer Required");
            alertString.setTitle("Invalid Input");
            alertString.setContentText("Enter an integer");
            alertString.showAndWait();

        }
    }


    public static void mortgage(){


        Pane paneMortgage = new Pane();

        //Adding Grid Pane
        GridPane mortgageGrid = new GridPane();


        Scene sceneMortgage = new Scene(paneMortgage,800,600);

        Stage window = new Stage();

        //Adding style sheet
        sceneMortgage.getStylesheets().add(MortgageCalculator.class.getResource("styler.css").toExternalForm());

        window.setTitle("Mortgage Calculator");
        window.setScene(sceneMortgage);
        window.show();

        //setting the grid
        mortgageGrid.setAlignment(Pos.CENTER);
        mortgageGrid.setHgap(20);
        mortgageGrid.setVgap(20);

        paneMortgage.getChildren().add(mortgageGrid);


        //Label for mortgage Title
        Label titleMortgage = new Label("Mortgage Calculator");

        //Home Price
        Label labelMortgageHP = new Label("Home Price ( $ )");
        TextField tfMortgageHP = new TextField();
        tfMortgageHP.setPromptText("$");

        //Down Payment
        Label labelDownPayment = new Label("Down Payment ( $ )");
        TextField tfDownPayment = new TextField();
        tfDownPayment.setPromptText("$");

        //Insert Rate
        Label labelInterestRate = new Label("Interest Rate ( % )");
        TextField tfInterestRate = new TextField();
        tfInterestRate.setPromptText("%");


        //Loan Term
        Label labelTimePeriod = new Label("Loan Term ");
        TextField tfTimePeriod = new TextField();
        tfTimePeriod.setPromptText("Years");


        //Monthly Mortgage Payment
        Label labelMortgagePayment = new Label("Monthly Mortgage Payment ( $ )");
        TextField tfMortgagePayment = new TextField();
        tfMortgagePayment.setPromptText("$");

        //button for calculate
        Button calculateMortgage = new Button("Calculate");

        //Button for clear Text Fields
        Button buttonResetMortgage = new Button("Reset");

        //Label for display Calculated function
        Label labelTag = new Label();
        Label labelAnswer = new Label();


        //Adding css classes

        titleMortgage.getStyleClass().add("sub-title");
        labelMortgageHP.getStyleClass().add("heading-label");
        labelDownPayment.getStyleClass().add("heading-label");
        labelInterestRate.getStyleClass().add("heading-label");
        labelTimePeriod.getStyleClass().add("heading-label");
        labelMortgagePayment.getStyleClass().add("heading-label");
        calculateMortgage.getStyleClass().add("calculate");
        buttonResetMortgage.getStyleClass().add("reset");
        labelTag.getStyleClass().add("tag");
        labelAnswer.getStyleClass().add("answer");





        //Adding menu bar to window
        mortgageGrid.add(ButtonsMenu.DisplayMenuButtons(window,"MortgageWindow"),1,1,8,1);

        //setting label and text area places
        mortgageGrid.add(titleMortgage,2,4);

        mortgageGrid.add(labelMortgageHP,1,6);
        mortgageGrid.add(tfMortgageHP,2,6);

        mortgageGrid.add(labelDownPayment,1,7);
        mortgageGrid.add(tfDownPayment,2,7);

        mortgageGrid.add(labelInterestRate,1,8);
        mortgageGrid.add(tfInterestRate,2,8);

        mortgageGrid.add(labelTimePeriod,1,9);
        mortgageGrid.add(tfTimePeriod,2,9);

        mortgageGrid.add(labelMortgagePayment,1,10);
        mortgageGrid.add(tfMortgagePayment,2,10);

        mortgageGrid.add(calculateMortgage,1,12);
        mortgageGrid.add(buttonResetMortgage,2,12);

        mortgageGrid.add(labelTag,1,14);
        mortgageGrid.add(labelAnswer,2,14);


        buttonResetMortgage.setOnAction(event -> {
            tfDownPayment.setText("");
            tfMortgageHP.setText("");
            tfInterestRate.setText("");
            tfTimePeriod.setText("");
            tfMortgagePayment.setText("");
        });


        //Adding the keyboard
        mortgageGrid.add(NumberPad.displayKeyBoard(tfDownPayment,tfInterestRate,tfMortgageHP,tfMortgagePayment,tfTimePeriod),6,5,1,8);



        //Calculations

        DecimalFormat df = new DecimalFormat("##.##");      //giving the pattern to decimal points

        /*
        created this programme to calculate the value of empty text field.
        For that first check which text field is empty.
        If more than one text fields are empty or all the text fields are filled it will display an error message.
         */

        calculateMortgage.setOnAction(click -> {

            // < ------------ Calculating Present Value ------------------>

            if ((tfMortgageHP.getText().isEmpty()) && (!(tfDownPayment.getText().isEmpty()))
                    && (!(tfInterestRate.getText().isEmpty())) && (!(tfMortgagePayment.getText().isEmpty())) && (!(tfTimePeriod.getText().isEmpty()))) {

                validation(tfDownPayment,tfInterestRate,tfMortgagePayment,tfTimePeriod);

                //double dHP = Double.parseDouble(tfMortgageHP.getText());
                double dDownPayment = Double.parseDouble(tfDownPayment.getText());
                double dInterest = Double.parseDouble(tfInterestRate.getText());
                double dPayment = Double.parseDouble(tfMortgagePayment.getText());
                double dTime = Double.parseDouble(tfTimePeriod.getText());
                int n = 12;

                double division = ((dInterest*0.01)/n);
                double multiply = n * dTime;
                double bracket = 1 + division;
                double power = Math.pow(bracket,multiply);
                double upper2 = dPayment * (power - 1);

                double down = division * power;
                double dHP = upper2/down + dDownPayment;

                String stringHP = df.format(dHP);

                tfMortgageHP.setText(stringHP);

                labelTag.setText("Home Price :");
                labelAnswer.setText(stringHP + " $ ");

                try {
                    FileHandling.writeMortgageHistory("MortgageData",tfMortgageHP.getText(),tfDownPayment.getText(),
                            tfInterestRate.getText(),tfTimePeriod.getText(),tfMortgagePayment.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            // < --------------- Calculating Mortgage Monthly payment ------------->

            else if ((tfMortgagePayment.getText().isEmpty()) && (!(tfDownPayment.getText().isEmpty()))
                    && (!(tfInterestRate.getText().isEmpty())) && (!(tfMortgageHP.getText().isEmpty())) && (!(tfTimePeriod.getText().isEmpty()))) {

                validation(tfDownPayment, tfInterestRate, tfMortgageHP, tfTimePeriod);

                double dHP = Double.parseDouble(tfMortgageHP.getText());
                double dDownPayment = Double.parseDouble(tfDownPayment.getText());
                double dInterest = Double.parseDouble(tfInterestRate.getText());
                double dTime = Double.parseDouble(tfTimePeriod.getText());
                int n = 12;

                double division = ((dInterest*0.01)/n);
                double multiply = n * dTime;
                double bracket = 1 + division;
                double power = Math.pow(bracket,multiply);

                double upper1 = dHP - dDownPayment;
                double upperFinal = upper1 * division * power;

                double down = power - 1;
                double dPayment = upperFinal/down;

                String stringPayment = df.format(dPayment);

                tfMortgagePayment.setText(stringPayment);

                labelTag.setText("Monthly Mortgage Payment :");
                labelAnswer.setText(stringPayment + " $ ");

                try {
                    FileHandling.writeMortgageHistory("MortgageData",tfMortgageHP.getText(),tfDownPayment.getText(),
                            tfInterestRate.getText(),tfTimePeriod.getText(),tfMortgagePayment.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            // < -------------- Calculating Down payment ---------------- >

            else if ((tfDownPayment.getText().isEmpty()) && (!(tfMortgagePayment.getText().isEmpty()))
                    && (!(tfInterestRate.getText().isEmpty())) && (!(tfMortgageHP.getText().isEmpty())) && (!(tfTimePeriod.getText().isEmpty()))) {

                validation(tfMortgagePayment, tfInterestRate, tfMortgageHP, tfTimePeriod);

                double dHP = Double.parseDouble(tfMortgageHP.getText());
                double dInterest = Double.parseDouble(tfInterestRate.getText());
                double dPayment = Double.parseDouble(tfMortgagePayment.getText());
                double dTime = Double.parseDouble(tfTimePeriod.getText());
                int n = 12;

                double division = ((dInterest*0.01)/n);
                double multiply = n * dTime;
                double bracket = 1 + division;
                double power = Math.pow(bracket,multiply);

                double upper = dPayment * (power - 1);
                double down = division * power;

                double dDownPayment = dHP - (upper/down);

                String stringDownPayment = df.format(dDownPayment);

                tfDownPayment.setText(stringDownPayment);

                labelTag.setText("Down Payment :");
                labelAnswer.setText(stringDownPayment + " $ ");

                try {
                    FileHandling.writeMortgageHistory("MortgageData",tfMortgageHP.getText(),tfDownPayment.getText(),
                            tfInterestRate.getText(),tfTimePeriod.getText(),tfMortgagePayment.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            // < ---------- Time period ------------------->

            else if ((tfTimePeriod.getText().isEmpty()) && (!(tfMortgagePayment.getText().isEmpty()))
                    && (!(tfInterestRate.getText().isEmpty())) && (!(tfMortgageHP.getText().isEmpty())) && (!(tfDownPayment.getText().isEmpty()))) {

                validation(tfMortgagePayment, tfInterestRate, tfMortgageHP, tfDownPayment);

                double dHP = Double.parseDouble(tfMortgageHP.getText());
                double dDownPayment = Double.parseDouble(tfDownPayment.getText());
                double dInterest = Double.parseDouble(tfInterestRate.getText());
                double dPayment = Double.parseDouble(tfMortgagePayment.getText());
                int n = 12;

                double division = ((dInterest*0.01)/n);
                double upper1 = dHP - dDownPayment;
                double upper2 = dPayment - (division*upper1);
                double upper3 = dPayment/upper2;
                double upperFinal = Math.log(upper3);

                double down = Math.log(1+division);
                double downFinal = n * down;

                double dTime = upperFinal/downFinal;

                String stringTime = df.format(dTime);

                tfTimePeriod.setText(stringTime);

                labelTag.setText("Loan Term :");
                labelAnswer.setText(stringTime + " Years ");


                try {
                    FileHandling.writeMortgageHistory("MortgageData",tfMortgageHP.getText(),tfDownPayment.getText(),
                            tfInterestRate.getText(),tfTimePeriod.getText(),tfMortgagePayment.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            else if (((tfInterestRate.getText().isEmpty())) && (!(tfMortgagePayment.getText().isEmpty()))
                    && (!(tfTimePeriod.getText().isEmpty())) && (!(tfMortgageHP.getText().isEmpty())) && (!(tfDownPayment.getText().isEmpty()))){

                Alert integerAlert = new Alert(Alert.AlertType.WARNING);
                integerAlert.setContentText("Can't calculate interest rate!");
                integerAlert.showAndWait();
            }


            // < ------------ If more than one text fields are empty ------------------>

            else if ((!(tfInterestRate.getText().isEmpty())) && (!(tfMortgagePayment.getText().isEmpty()))
                    && (!(tfTimePeriod.getText().isEmpty())) && (!(tfMortgageHP.getText().isEmpty())) && (!(tfDownPayment.getText().isEmpty()))){
                Alert alertText = new Alert(Alert.AlertType.INFORMATION);
                alertText.setHeaderText("All blanks filled");
                alertText.setContentText("Leave the calculation text field blank");
                alertText.showAndWait();

            }


            // <------------ If All the text fields are filled ----------------------->

            else {
                Alert alertBlanks = new Alert(Alert.AlertType.ERROR);
                alertBlanks.setHeaderText("Fill the blanks");
                alertBlanks.setTitle("Calculation Error");
                alertBlanks.setContentText("Only one value can calculate at a time");
                alertBlanks.showAndWait();

            }


        });


    }

}
