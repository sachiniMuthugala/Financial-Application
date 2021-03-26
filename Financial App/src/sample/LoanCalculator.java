package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class LoanCalculator {

    //method for check validation of inputs.
    //If user inputs something other than numbers it will gives a warning alert.
    public static void validation(TextField textField1,TextField textField2,TextField textField3){
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

    public static void AutoLoan(){

        Stage window = new Stage();
        window.setTitle("Auto Loan Calculator");



        Pane paneLoan = new Pane();
        GridPane loanGrid = new GridPane();

        //pane --> grid pane
        paneLoan.getChildren().add(loanGrid);

        Scene sceneLoan = new Scene(paneLoan,800,600);

        //Adding a style sheet
        sceneLoan.getStylesheets().add(LoanCalculator.class.getResource("styler.css").toExternalForm());

        window.setScene(sceneLoan);
        window.show();

        //Setting grid pane alignment
        loanGrid.setAlignment(Pos.CENTER);
        loanGrid.setHgap(20);
        loanGrid.setVgap(20);


        //Label Auto Loan Calculator
        Label labelLoan = new Label("Auto Loan Calculator");

        //Loan Amount
        Label labelLoanAmount = new Label("Loan Amount ( $ )");
        TextField tfLoanAmount = new TextField();
        tfLoanAmount.setPromptText("$");

        //Interest Rate
        Label labelInterestRate = new Label("Interest Rate ( % )");
        TextField tfInterestRate = new TextField();
        tfInterestRate.setPromptText("%");

        //Number of Payments
        Label labelLLoanNOP = new Label("Number Of Payments");
        TextField tfLLoanNOP = new TextField();

        //monthly payment
        Label labelMonthlyPay = new Label("Monthly Payment ( $ )");
        TextField tfMonthlyPay = new TextField();
        tfMonthlyPay.setPromptText("$");

        //Calculate
        Button calculateLoan = new Button("Calculate");

        //Reset button
        Button buttonResetLoan = new Button("Reset");

        //Label for display Calculated function
        Label labelTag = new Label();
        Label labelAnswer = new Label();

        //Adding css classes
        labelLoan.getStyleClass().add("sub-title");
        labelLoanAmount.getStyleClass().add("heading-label");
        labelInterestRate.getStyleClass().add("heading-label");
        labelLLoanNOP.getStyleClass().add("heading-label");
        labelMonthlyPay.getStyleClass().add("heading-label");
        calculateLoan.getStyleClass().add("calculate");
        buttonResetLoan.getStyleClass().add("reset");
        labelTag.getStyleClass().add("tag");
        labelAnswer.getStyleClass().add("answer");



        //Adding menu bar to window
        loanGrid.add(ButtonsMenu.DisplayMenuButtons(window,"LoanWindow"),1,1,8,1);

        //Adding buttons and text fields
        loanGrid.add(labelLoan,2,4);

        loanGrid.add(labelLoanAmount,1,6);
        loanGrid.add(tfLoanAmount,2,6);

        loanGrid.add(labelInterestRate,1,7);
        loanGrid.add(tfInterestRate,2,7);

        loanGrid.add(labelLLoanNOP,1,8);
        loanGrid.add(tfLLoanNOP,2,8);

        loanGrid.add(labelMonthlyPay,1,9);
        loanGrid.add(tfMonthlyPay,2,9);

        loanGrid.add(calculateLoan,1,11);
        loanGrid.add(buttonResetLoan,2,11);

        loanGrid.add(labelTag,1,13);
        loanGrid.add(labelAnswer,2,13);

        //Setting Action for Reset Button
        buttonResetLoan.setOnAction(event -> {
            tfLoanAmount.setText("");
            tfInterestRate.setText("");
            tfLLoanNOP.setText("");
            tfMonthlyPay.setText("");
        });


        //Adding a keyboard
        loanGrid.add(NumberPad.displayKeyBoard(tfInterestRate,tfLLoanNOP,tfLoanAmount,tfMonthlyPay),7,5,1,8);

        //Rounding values to two decimal places
        DecimalFormat df = new DecimalFormat("##.##");      //giving the pattern to decimal points

        //Calculations

        calculateLoan.setOnAction(click -> {

            // < -------- Calculating Loan Amount -------------- >

            if ((tfLoanAmount.getText().isEmpty()) && (!(tfInterestRate.getText().isEmpty()))
                    && (!(tfLLoanNOP.getText().isEmpty())) && (!(tfMonthlyPay.getText().isEmpty()))){

                validation(tfInterestRate,tfLLoanNOP,tfMonthlyPay);     //validating filled text fields


                //Strings --> double
                double dInterestRate = Double.parseDouble(tfInterestRate.getText());
                double dNOP = Double.parseDouble(tfLLoanNOP.getText());
                double dMonthlyPay = Double.parseDouble(tfMonthlyPay.getText());

                double monthlyInterest =  (dInterestRate*0.01)/12;
                double down1 = 1 + monthlyInterest;
                double power = Math.pow(down1,dNOP);
                double step1 = 1 - (1/power);
                double step2 = dMonthlyPay/monthlyInterest;

                double dLoanAmount = step1 *step2;

                String stringLoanAmount = df.format(dLoanAmount);

                tfLoanAmount.setText(stringLoanAmount);

                labelTag.setText("Loan Amount :");
                labelAnswer.setText(stringLoanAmount + " $ ");

                //Getting the values in text fields to write the history
                try {
                    FileHandling.writeLoanHistory("LoanData",tfLoanAmount.getText(),tfInterestRate.getText(),tfLLoanNOP.getText(),tfMonthlyPay.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            //< -------------- Calculating Number of Payments ----------->

            else if ((tfLLoanNOP.getText().isEmpty()) && (!(tfInterestRate.getText().isEmpty()))
                    && (!(tfLoanAmount.getText().isEmpty())) && (!(tfMonthlyPay.getText().isEmpty()))){

                validation(tfInterestRate,tfLoanAmount,tfMonthlyPay);

                double dLoanAmount = Double.parseDouble(tfLoanAmount.getText());
                double dInterestRate = Double.parseDouble(tfInterestRate.getText());
                double dMonthlyPay = Double.parseDouble(tfMonthlyPay.getText());

                double monthlyInterest =  (dInterestRate*0.01)/12;

                double upper1 = dMonthlyPay/monthlyInterest;
                double upper2 = upper1 - dLoanAmount;
                double upper3 = upper1/upper2;
                double upperFinal = Math.log(upper3);

                double downFinal = Math.log(1+monthlyInterest);

                double dNOP = upperFinal/downFinal;

                long roundNOP = Math.round(dNOP);

                String stringNOP = Long.toString(roundNOP);

                tfLLoanNOP.setText(stringNOP);

                labelTag.setText("Number of Payments :");
                labelAnswer.setText(stringNOP);

                try {
                    FileHandling.writeLoanHistory("LoanData",tfLoanAmount.getText(),tfInterestRate.getText(),tfLLoanNOP.getText(),tfMonthlyPay.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            // < ------------ Calculating Monthly Payment -------------->

            else if ((tfMonthlyPay.getText().isEmpty()) && (!(tfInterestRate.getText().isEmpty()))
                    && (!(tfLoanAmount.getText().isEmpty())) && (!(tfLLoanNOP.getText().isEmpty()))){

                validation(tfInterestRate,tfLoanAmount,tfLLoanNOP);

                double dLoanAmount = Double.parseDouble(tfLoanAmount.getText());
                double dInterestRate = Double.parseDouble(tfInterestRate.getText());
                double dNOP = Double.parseDouble(tfLLoanNOP.getText());

                double monthlyInterest =  (dInterestRate*0.01)/12;

                double upper1 = 1 + monthlyInterest;
                double power = Math.pow(upper1,dNOP);
                double upperFinal = dLoanAmount * monthlyInterest * power;

                double downFinal = power - 1;

                double dMonthlyPay = upperFinal/downFinal;

                String stringMonthlyPay = df.format(dMonthlyPay);

                tfMonthlyPay.setText(stringMonthlyPay);

                labelTag.setText("Monthly Payment :");
                labelAnswer.setText(stringMonthlyPay + " $ ");


                try {
                    FileHandling.writeLoanHistory("LoanData",tfLoanAmount.getText(),tfInterestRate.getText(),tfLLoanNOP.getText(),tfMonthlyPay.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



            else if ((tfInterestRate.getText().isEmpty()) && (!(tfMonthlyPay.getText().isEmpty()))
                    && (!(tfLoanAmount.getText().isEmpty())) && (!(tfLLoanNOP.getText().isEmpty()))){

                Alert integerAlert = new Alert(Alert.AlertType.WARNING);
                integerAlert.setContentText("Can't calculate interest rate!");
                integerAlert.showAndWait();
            }


            // < ------------ If more than one text fields are empty ------------------>

            else if (!(tfInterestRate.getText().isEmpty()) && (!(tfMonthlyPay.getText().isEmpty()))
                    && (!(tfLoanAmount.getText().isEmpty())) && (!(tfLLoanNOP.getText().isEmpty()))){
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
