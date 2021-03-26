package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandling {

    public static void writeSavingsHistory(String name, String presentVal, String futureVal, String interest,
                                           String timePeriod) throws IOException {
        File fileName = new File(name);

        FileWriter fileWriter = new FileWriter(fileName,true);
        fileWriter.write("Present value : " + presentVal + "\n");
        fileWriter.write("Future value : " + futureVal + "\n");
        fileWriter.write("Interest Rate : " + interest + "\n");
        fileWriter.write("Number Of Years : " + timePeriod + "\n");
        fileWriter.write("\n");

        fileWriter.close();

    }


    public static void writeCompoundHistory(String name, String presentVal, String futureVal, String interest,String payment,
                                           String timePeriod) throws IOException {
        File fileName = new File(name);

        FileWriter fileWriter = new FileWriter(fileName,true);
        fileWriter.write("Present value : " + presentVal + "\n");
        fileWriter.write("Future value : " + futureVal + "\n");
        fileWriter.write("Interest Rate : " + interest + "\n");
        fileWriter.write("Payment : " + payment + "\n");
        fileWriter.write("Number Of Years : " + timePeriod + "\n");
        fileWriter.write("\n");

        fileWriter.close();

    }

    public static void writeLoanHistory(String name, String loanAmount, String interest, String NOP,String monthlyPay) throws IOException {
        File fileName = new File(name);

        FileWriter fileWriter = new FileWriter(fileName,true);
        fileWriter.write("Loan Amount : " + loanAmount + "\n");
        fileWriter.write("Interest : " + interest + "\n");
        fileWriter.write("Number Of payments : " + NOP + "\n");
        fileWriter.write("Monthly Payment : " + monthlyPay + "\n");
        fileWriter.write("\n");

        fileWriter.close();

    }

    public static void writeMortgageHistory(String name, String homePrice, String downPayment, String interestRate,
                                            String loanTerm,String monthlyPay) throws IOException {
        File fileName = new File(name);

        FileWriter fileWriter = new FileWriter(fileName,true);
        fileWriter.write("Home Price : " + homePrice + "\n");
        fileWriter.write("Down Payment : " + downPayment + "\n");
        fileWriter.write("Interest Rate : " + interestRate + "\n");
        fileWriter.write("Loan Term : " + loanTerm + "\n");
        fileWriter.write("Monthly Pay : " + monthlyPay + "\n");
        fileWriter.write("\n");

        fileWriter.close();

    }


    public static String readFiles(String name) throws FileNotFoundException {
            File fileName = new File(name);
            Scanner sc = new Scanner(fileName);
            String readHistory = "";
            while (sc.hasNextLine()) readHistory = readHistory.concat(sc.nextLine() + "\n");
            return readHistory;

    }



}
