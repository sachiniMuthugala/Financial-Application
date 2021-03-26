package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.*;
import java.text.DecimalFormat;

public class SimpleSavingCalculator {


    //method for check validity of inputs.
    //If user inputs something other than numbers it will gives a warning alert.
    public static void validation(TextField textField1, TextField textField2, TextField textField3){
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


    public static void SimpleSavings()  {

        Stage window = new Stage();
        window.setTitle("Simple Saving Calculator");


        //pane --> grid pane
        Pane paneSimpleSaving = new Pane();
        GridPane simpleSavingGrid = new GridPane();
        paneSimpleSaving.getChildren().add(simpleSavingGrid);
        simpleSavingGrid.setAlignment(Pos.CENTER);

        //Setting the vertical and horizontal gap in grid pane
        simpleSavingGrid.setHgap(35);
        simpleSavingGrid.setVgap(20);

        Scene sceneSimpleSaving = new Scene(paneSimpleSaving, 800, 600);

        //Adding the style sheet to scene
        sceneSimpleSaving.getStylesheets().add(SimpleSavingCalculator.class.getResource("styler.css").toExternalForm());

        window.setScene(sceneSimpleSaving);
        window.show();



        //Title Label Simple Savings Calculator
        Label labelSimple = new Label("Simple Savings Calculator");


        //Present Value
        Label labelSimplePV = new Label("Present Value ( $ )");
        TextField tfSimplePV = new TextField();
        tfSimplePV.setPromptText("$");

        //Future Value
        Label labelSimpleFV = new Label("Future Value ( $ )");
        TextField tfSimpleFV = new TextField();
        tfSimpleFV.setPromptText("$");

        //Interest
        Label labelSimpleInterest = new Label("Interest ( % )");
        TextField tfSimpleInterest = new TextField();
        tfSimpleInterest.setPromptText("%");

        //Time Period in years
        Label labelSimplePeriod = new Label("Number of Years");
        TextField tfSimplePeriod = new TextField();
        tfSimplePeriod.setPromptText("Years");

        //button for calculate
        Button calculateSimpleSaving = new Button("Calculate");

        //Button for reset the values. this clear all the text fields in the current window
        Button buttonSimpleReset = new Button("Reset");

        //Label for display Calculated answer
        Label labelTag = new Label();
        Label labelAnswer = new Label();


        //Adding classes to labels and buttons for styling purposes
        labelSimple.getStyleClass().add("sub-title");
        labelSimplePV.getStyleClass().add("heading-label");
        labelSimpleFV.getStyleClass().add("heading-label");
        labelSimpleInterest.getStyleClass().add("heading-label");
        labelSimplePeriod.getStyleClass().add("heading-label");
        calculateSimpleSaving.getStyleClass().add("calculate");
        buttonSimpleReset.getStyleClass().add("reset");
        labelTag.getStyleClass().add("tag");
        labelAnswer.getStyleClass().add("answer");



        //Adding menu bar to window
        simpleSavingGrid.add(ButtonsMenu.DisplayMenuButtons(window,"SimpleSavingsWindow"),1,1,8,1);

        //Adding Buttons and Text fields to Grid
        simpleSavingGrid.add(labelSimple, 2, 4);

        simpleSavingGrid.add(labelSimplePV, 1, 6);
        simpleSavingGrid.add(tfSimplePV, 2, 6);

        simpleSavingGrid.add(labelSimpleFV, 1, 7);
        simpleSavingGrid.add(tfSimpleFV, 2, 7);

        simpleSavingGrid.add(labelSimpleInterest, 1, 8);
        simpleSavingGrid.add(tfSimpleInterest, 2, 8);

        simpleSavingGrid.add(labelSimplePeriod, 1, 9);
        simpleSavingGrid.add(tfSimplePeriod, 2, 9);

        simpleSavingGrid.add(calculateSimpleSaving, 1, 12);
        simpleSavingGrid.add(buttonSimpleReset, 2, 12);

        simpleSavingGrid.add(labelTag,1,14);
        simpleSavingGrid.add(labelAnswer,2,14);




        //Setting Action for Reset Button
        buttonSimpleReset.setOnAction(event -> {
            tfSimpleFV.setText("");
            tfSimpleInterest.setText("");
            tfSimplePeriod.setText("");
            tfSimplePV.setText("");
        });

        //Adding the keyboard
        simpleSavingGrid.add(NumberPad.displayKeyBoard(tfSimpleFV,tfSimplePV,tfSimpleInterest,tfSimplePeriod),5,5,1,8);

        //giving the pattern to decimal points. This pattern gives values in 2 decimal points
        DecimalFormat df = new DecimalFormat("##.##");



        //Calculations

        /*
        created this programme to calculate the value of empty text field.
        For that first check which text field is empty.
        If more than one text fields are empty or all the text fields are filled it will display an error message.
         */

        calculateSimpleSaving.setOnAction(click -> {

            //Future Value

            //This will calculate only if future value text field is empty

            if ((tfSimpleFV.getText().isEmpty()) && (!(tfSimpleInterest.getText().isEmpty()))
                    && (!(tfSimplePeriod.getText().isEmpty())) && (!(tfSimplePV.getText().isEmpty()))) {

                validation(tfSimpleInterest,tfSimplePeriod,tfSimplePV);     //validating filled text fields

                //text fields gives string values. So we need to convert it into Double before the calculations
                double dPV = Double.parseDouble(tfSimplePV.getText());
                double dInterest = Double.parseDouble(tfSimpleInterest.getText());
                double dTime = Double.parseDouble(tfSimplePeriod.getText());
                int n = 12;

                //double dFV = dPV * (Math.pow(1 + (dInterest * 0.01 / n), (n * dTime)));   <-- calculation in one line
                double upperSet = n * dTime;
                double bracket = 1 + (dInterest * 0.01 / n);
                double power = Math.pow(bracket, upperSet);
                double dFV = dPV * power;

                //Convert double ---> String
                //to display the answer in appropriate text field
                String stringFV = df.format(dFV);

                tfSimpleFV.setText(stringFV);

                //display the answer in a label
                labelTag.setText("Future Value :");
                labelAnswer.setText(stringFV + " $");

                //File handling
                //Getting the values in text fields to write the history
                try {
                    FileHandling.writeSavingsHistory("SavingsData",tfSimplePV.getText(),tfSimpleFV.getText(),
                            tfSimpleInterest.getText(),tfSimplePeriod.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            //<--------------- Calculating Present Value ------------------------>

            else if ((tfSimplePV.getText().isEmpty()) && (!(tfSimpleInterest.getText().isEmpty()))
                    && (!(tfSimplePeriod.getText().isEmpty())) && (!(tfSimpleFV.getText().isEmpty()))) {

                validation(tfSimpleInterest,tfSimplePeriod,tfSimpleFV);

                double dFV = Double.parseDouble(tfSimpleFV.getText());
                double dInterest = Double.parseDouble(tfSimpleInterest.getText());
                double dTime = Double.parseDouble(tfSimplePeriod.getText());
                int n = 12;

                //double dPV = dFV / (Math.pow(1 + (dInterest * 0.01 / n), (n * dTime)));

                double downSetPower = n * dTime;
                double downSet1 = 1 + ((dInterest * 0.01) / n);
                double downSet2 = Math.pow(downSet1, downSetPower);
                double dPV = dFV / downSet2;

                String stringPV = df.format(dPV);

                tfSimplePV.setText(stringPV);

                labelTag.setText("Present Value :");
                labelAnswer.setText(stringPV + " $ ");

                try {
                    FileHandling.writeSavingsHistory("SavingsData",tfSimplePV.getText(),tfSimpleFV.getText(),
                            tfSimpleInterest.getText(),tfSimplePeriod.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            // < ------------------ Calculating Interest ----------------------->

            else if ((tfSimpleInterest.getText().isEmpty()) && (!(tfSimpleFV.getText().isEmpty()))
                    && (!(tfSimplePeriod.getText().isEmpty())) && (!(tfSimplePV.getText().isEmpty()))) {

                validation(tfSimplePV,tfSimplePeriod,tfSimpleFV);

                double dFV = Double.parseDouble(tfSimpleFV.getText());
                double dPV = Double.parseDouble(tfSimplePV.getText());
                double dTime = Double.parseDouble(tfSimplePeriod.getText());
                int n = 12;

                //double dInterest = n * ((Math.pow((dFV/dPV),(1/(n*dTime)))) - 1 );
                double interestStep1 = dFV / dPV;
                double interestStep2 = 1 / (n * dTime);
                double interestStep3 = Math.pow(interestStep1, interestStep2);
                double interestStep4 = n * (interestStep3 - 1);
                double interestStep5 = interestStep4 * 100;

                String stringInterest = df.format(interestStep5);

                tfSimpleInterest.setText(stringInterest);

                labelTag.setText("Interest Rate :");
                labelAnswer.setText(stringInterest + "%");

                try {
                    FileHandling.writeSavingsHistory("SavingsData",tfSimplePV.getText(),tfSimpleFV.getText(),
                            tfSimpleInterest.getText(),tfSimplePeriod.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            //< ---------------Calculating Time Period (Number Of years) ----------->


            else if ((tfSimplePeriod.getText().isEmpty()) && (!(tfSimpleInterest.getText().isEmpty()))
                    && (!(tfSimpleFV.getText().isEmpty())) && (!(tfSimplePV.getText().isEmpty()))) {

                validation(tfSimplePV,tfSimpleInterest,tfSimpleFV);

                double dFV = Double.parseDouble(tfSimpleFV.getText());
                double dPV = Double.parseDouble(tfSimplePV.getText());
                double dInterest = Double.parseDouble(tfSimpleInterest.getText());
                int n = 12;

                double upperSet = Math.log(dFV / dPV);
                double downSet1 = Math.log(1 + (dInterest * 0.01 / n));
                double downSet2 = n * downSet1;
                double dTime = upperSet / downSet2;
                String stringTime = df.format(dTime);

                tfSimplePeriod.setText(stringTime);

                labelTag.setText("No of Years :");
                labelAnswer.setText(stringTime + " years");

                try {
                    FileHandling.writeSavingsHistory("SavingsData",tfSimplePV.getText(),tfSimpleFV.getText(),
                            tfSimpleInterest.getText(),tfSimplePeriod.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            // < ------------ If more than one text fields are empty ------------------>

            else if ((!(tfSimpleInterest.getText().isEmpty())) && (!(tfSimpleFV.getText().isEmpty()))
                    && (!(tfSimplePV.getText().isEmpty())) && (!(tfSimplePeriod.getText().isEmpty()))){
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


