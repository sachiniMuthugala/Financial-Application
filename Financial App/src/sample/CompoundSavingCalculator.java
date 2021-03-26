package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class CompoundSavingCalculator {

    //method for check validity of inputs.
    //If user inputs something other than numbers it will gives a warning alert.
    public static void validation(TextField textField1,TextField textField2,TextField textField3,TextField textField4){
        try {
            Double.parseDouble(textField1.getText());
            Double.parseDouble(textField2.getText());
            Double.parseDouble(textField3.getText());
            Double.parseDouble(textField4.getText());
        }
        catch (Exception E){
            Alert alertString = new Alert(Alert.AlertType.WARNING);
            alertString.setHeaderText("Integer Required");
            alertString.setTitle("Invalid Input");
            alertString.setContentText("Enter an integer");
            alertString.showAndWait();

        }
    }

    public static void CompoundSavings(){
        Stage window = new Stage();
        window.setTitle("Compound Interest Saving Calculator");


        Pane paneCompoundSaving = new Pane();
        GridPane compoundSavingGrid = new GridPane();

        // pane --> grid pane
        paneCompoundSaving.getChildren().add(compoundSavingGrid);
        compoundSavingGrid.setAlignment(Pos.CENTER);
        compoundSavingGrid.setHgap(20);
        compoundSavingGrid.setVgap(20);

        Scene sceneCompoundSaving = new Scene(paneCompoundSaving,800,600);

        //Adding a style sheet
        sceneCompoundSaving.getStylesheets().add(CompoundSavingCalculator.class.getResource("styler.css").toExternalForm());

        window.setScene(sceneCompoundSaving);
        window.show();

        //Label Compound Savings Calculator
        Label labelCompound = new Label("Compound Savings Calculator");


        //Present Value
        Label labelCompoundPV = new Label("Present Value ( $ )");
        TextField tfCompoundPV = new TextField();
        tfCompoundPV.setPromptText("$");

        //Future Value
        Label labelCompoundFV = new Label("Future Value ( $ )");
        TextField tfCompoundFV = new TextField();
        tfCompoundFV.setPromptText("$");

        //Interest
        Label labelCompoundInterest = new Label("Interest ( % )");
        TextField tfCompoundInterest = new TextField();
        tfCompoundInterest.setPromptText("%");

        //Payment
        Label labelCompoundPayment = new Label("Payment ( $ )");
        TextField tfCompoundPayment = new TextField();
        tfCompoundPayment.setPromptText("$");

        //Time Period
        Label labelCompoundPeriod = new Label("Number of Years");
        TextField tfCompoundPeriod = new TextField();
        tfCompoundPeriod.setPromptText("Years");

        //button for calculate
        Button calculateCompoundSaving = new Button("Calculate");

        //button for reset the values
        Button buttonCompoundReset = new Button("Reset");

        //Labels for display the answers
        Label labelTag = new Label();
        Label labelAnswer = new Label();

        //Adding classes to labels and buttons for styling purposes
        labelCompound.getStyleClass().add("sub-title");
        labelCompoundPV.getStyleClass().add("heading-label");
        labelCompoundFV.getStyleClass().add("heading-label");
        labelCompoundInterest.getStyleClass().add("heading-label");
        labelCompoundPayment.getStyleClass().add("heading-label");
        labelCompoundPeriod.getStyleClass().add("heading-label");
        calculateCompoundSaving.getStyleClass().add("calculate");
        buttonCompoundReset.getStyleClass().add("reset");
        labelTag.getStyleClass().add("tag");
        labelAnswer.getStyleClass().add("answer");



        //Adding menu bar to window
        compoundSavingGrid.add(ButtonsMenu.DisplayMenuButtons(window,"CompoundSavingsWindow"),1,1,8,1);

        //Adding Buttons and Text fields to Grid
        compoundSavingGrid.add(labelCompound,2,4);

        compoundSavingGrid.add(labelCompoundPV,1,6);
        compoundSavingGrid.add(tfCompoundPV,2,6);

        compoundSavingGrid.add(labelCompoundFV,1,7);
        compoundSavingGrid.add(tfCompoundFV,2,7);

        compoundSavingGrid.add(labelCompoundInterest,1,8);
        compoundSavingGrid.add(tfCompoundInterest,2,8);

        compoundSavingGrid.add(labelCompoundPayment,1,9);
        compoundSavingGrid.add(tfCompoundPayment,2,9);

        compoundSavingGrid.add(labelCompoundPeriod,1,10);
        compoundSavingGrid.add(tfCompoundPeriod,2,10);

        compoundSavingGrid.add(calculateCompoundSaving,1,12);
        compoundSavingGrid.add(buttonCompoundReset,2,12);

        compoundSavingGrid.add(labelTag,1,14);
        compoundSavingGrid.add(labelAnswer,2,14);


        buttonCompoundReset.setOnAction(event -> {
            tfCompoundFV.setText("");
            tfCompoundInterest.setText("");
            tfCompoundPayment.setText("");
            tfCompoundPeriod.setText("");
            tfCompoundPV.setText("");
        });


        //Adding the keyboard
        compoundSavingGrid.add(NumberPad.displayKeyBoard(tfCompoundFV,tfCompoundInterest,tfCompoundPayment,tfCompoundPeriod,tfCompoundPV),7,5,1,8);

        //giving the pattern to decimal points. This pattern gives values in 2 decimal points
        DecimalFormat df = new DecimalFormat("##.##");

        //calculations

        calculateCompoundSaving.setOnAction(click -> {

             /*
        created this programme to calculate the value of empty text field.
        For that first check which text field is empty.
        If more than one text fields are empty or all the text fields are filled it will display an error message.
         */

            // < ---------- Calculating Present Value ----------->

            if ((tfCompoundPV.getText().isEmpty()) && (!(tfCompoundFV.getText().isEmpty())) &&
                    (!(tfCompoundPayment.getText().isEmpty())) && (!(tfCompoundInterest.getText().isEmpty())) && (!(tfCompoundPeriod.getText().isEmpty()))){

                validation(tfCompoundFV,tfCompoundPayment,tfCompoundInterest,tfCompoundPeriod);     //validating filled text fields

                // Convert Strings ---> double
                double dFV = Double.parseDouble(tfCompoundFV.getText());
                double dInterest = Double.parseDouble(tfCompoundInterest.getText());
                double dPayment = Double.parseDouble(tfCompoundPayment.getText());
                double dTime = Double.parseDouble(tfCompoundPeriod.getText());
                int n = 12;

                double upper1 = 1 + ((dInterest * 0.01)/n);
                double upper2 = n*dTime;
                double power = Math.pow(upper1,upper2);
                double upper3 = power - 1;
                double upper4 = upper3/((dInterest * 0.01)/n);
                double upper5 = dPayment * upper4;
                double upper6 = dFV - upper5;

                double dPV = upper6/power;

                // double ---> string
                String stringPV = df.format(dPV);

                tfCompoundPV.setText(stringPV);

                labelTag.setText("Present Value :");
                labelAnswer.setText(stringPV + " $ ");

                //Getting the values in text fields to write the history
                try {
                    FileHandling.writeCompoundHistory("CompoundSavings", tfCompoundPV.getText(), tfCompoundFV.getText(), tfCompoundInterest.getText(),
                            tfCompoundPayment.getText(),tfCompoundPeriod.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            //< ---------------- Calculating Future Value ----------------- >

            else if ((tfCompoundFV.getText().isEmpty()) && (!(tfCompoundPV.getText().isEmpty())) &&
                    (!(tfCompoundPayment.getText().isEmpty())) && (!(tfCompoundInterest.getText().isEmpty())) && (!(tfCompoundPeriod.getText().isEmpty()))) {

                validation(tfCompoundPV,tfCompoundPayment,tfCompoundInterest,tfCompoundPeriod);

                double dPV = Double.parseDouble(tfCompoundPV.getText());
                double dInterest = Double.parseDouble(tfCompoundInterest.getText());
                double dPayment = Double.parseDouble(tfCompoundPayment.getText());
                double dTime = Double.parseDouble(tfCompoundPeriod.getText());
                int n = 12;

                double compoundInterestStep1 = (1 + ((dInterest * 0.01) / n));
                double compoundInterestStep2 = n * dTime;
                double compoundInterestStep3 = Math.pow(compoundInterestStep1, compoundInterestStep2);
                double compoundInterest = dPV * compoundInterestStep3;

                double FVUpper = compoundInterestStep3 - 1;
                double FVDown = (dInterest * 0.01) / n;
                double FV = dPayment * (FVUpper / FVDown);

                double dFV = compoundInterest + FV;

                String stringFV = df.format(dFV);

                tfCompoundFV.setText(stringFV);

                labelTag.setText("Future Value :");
                labelAnswer.setText(stringFV + " $ ");

                try {
                    FileHandling.writeCompoundHistory("CompoundSavings", tfCompoundPV.getText(), tfCompoundFV.getText(), tfCompoundInterest.getText(),
                            tfCompoundPayment.getText(),tfCompoundPeriod.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            // < ----------------- Calculating Payment ------------------>

            else if ((tfCompoundPayment.getText().isEmpty()) && (!(tfCompoundPV.getText().isEmpty())) &&
                    (!(tfCompoundFV.getText().isEmpty())) && (!(tfCompoundInterest.getText().isEmpty())) && (!(tfCompoundPeriod.getText().isEmpty()))){

                validation(tfCompoundPV,tfCompoundFV,tfCompoundInterest,tfCompoundPeriod);

                double dFV = Double.parseDouble(tfCompoundFV.getText());
                double dPV = Double.parseDouble(tfCompoundPV.getText());
                double dInterest = Double.parseDouble(tfCompoundInterest.getText());
                double dTime = Double.parseDouble(tfCompoundPeriod.getText());
                int n = 12;

                double upper1 = 1 + ((dInterest * 0.01)/n);
                double upper2 = n * dTime;
                double power = Math.pow(upper1,upper2);
                double upper3 = dPV * (power);
                double upperFinal = dFV - upper3;

                double down1 = power - 1;
                double down2 = (dInterest * 0.01)/n;
                double downFinal = down1/down2;

                double dPayment = upperFinal/downFinal;

                String stringPayment = df.format(dPayment);

                tfCompoundPayment.setText(stringPayment);

                labelTag.setText("Payment :");
                labelAnswer.setText(stringPayment + " $ ");

                try {
                    FileHandling.writeCompoundHistory("CompoundSavings", tfCompoundPV.getText(), tfCompoundFV.getText(), tfCompoundInterest.getText(),
                            tfCompoundPayment.getText(),tfCompoundPeriod.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            // < ---------------- Time Period (Number of Years) ---------->

            else if ((tfCompoundPeriod.getText().isEmpty()) && (!(tfCompoundPV.getText().isEmpty())) &&
                    (!(tfCompoundFV.getText().isEmpty())) && (!(tfCompoundInterest.getText().isEmpty())) && (!(tfCompoundPayment.getText().isEmpty()))){

                validation(tfCompoundPV,tfCompoundFV,tfCompoundInterest,tfCompoundPayment);
                double dFV = Double.parseDouble(tfCompoundFV.getText());
                double dPV = Double.parseDouble(tfCompoundPV.getText());
                double dInterest = Double.parseDouble(tfCompoundInterest.getText());
                double dPayment = Double.parseDouble(tfCompoundPayment.getText());
                int n = 12;

                double upper1 = (dInterest * 0.01)/n;
                double upper2 = (dFV * upper1) + dPayment;
                double upper3 = (dPV * upper1) + dPayment;
                double upper4 = upper2/upper3;
                double upperFinal = Math.log(upper4);

                double down1 = upper1 + 1;
                double down2 = Math.log(down1);
                double downFinal = n * down2;

                double dTime = upperFinal/downFinal;

                String stringTime = df.format(dTime);

                tfCompoundPeriod.setText(stringTime);

                labelTag.setText("No of years :");
                labelAnswer.setText(stringTime + " Years");

                try {
                    FileHandling.writeCompoundHistory("CompoundSavings", tfCompoundPV.getText(), tfCompoundFV.getText(), tfCompoundInterest.getText(),
                            tfCompoundPayment.getText(),tfCompoundPeriod.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }



            else if ((tfCompoundInterest.getText().isEmpty()) && (!(tfCompoundPV.getText().isEmpty())) &&
                    (!(tfCompoundFV.getText().isEmpty())) && (!(tfCompoundPeriod.getText().isEmpty())) && (!(tfCompoundPayment.getText().isEmpty()))) {

                Alert integerAlert = new Alert(Alert.AlertType.WARNING);
                integerAlert.setHeaderText("Interest rate is not available");
                integerAlert.setContentText("Can't calculate interest rate!");
                integerAlert.showAndWait();
            }

            // < ------------ If more than one text fields are empty ------------------>

            else if ((!(tfCompoundFV.getText().isEmpty())) && (!(tfCompoundPV.getText().isEmpty()))
                    && (!(tfCompoundInterest.getText().isEmpty())) && (!(tfCompoundPayment.getText().isEmpty())) && (!(tfCompoundPeriod.getText().isEmpty()))){
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
