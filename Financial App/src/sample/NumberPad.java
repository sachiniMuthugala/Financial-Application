package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.InputMethodTextRun;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class NumberPad {
    /*text  --- > text in the button
      x ------> prefix Width
      y ------> prefix height
     */

    public static Button SettingButtons(String text,int x,int y){
        Button keyButton = new Button(text);
        keyButton.setPrefWidth(x);
        keyButton.setPrefHeight(y);
        return keyButton;
    }


        public static Pane displayKeyBoard(TextField ...textFields) {

            Pane paneKeyBoard = new Pane();
            int x = 60;
            int y = 40;

            //Adding Buttons
            Button one = SettingButtons("1",x,y);
            Button two = SettingButtons("2",x,y);
            Button three = SettingButtons("3",x,y);
            Button four = SettingButtons("4",x,y);
            Button five = SettingButtons("5",x,y);
            Button six = SettingButtons("6",x,y);
            Button seven = SettingButtons("7",x,y);
            Button eight = SettingButtons("8",x,y);
            Button nine = SettingButtons("9",x,y);
            Button zero = SettingButtons("0",x,y);
            Button point = SettingButtons(".",x,y);
            Button delete = SettingButtons("C",x,y);
            Button deleteAll = SettingButtons("AC",x,y);



            GridPane keyGrid = new GridPane();
            keyGrid.setHgap(5);
            keyGrid.setVgap(5);
            paneKeyBoard.getChildren().add(keyGrid);

            //Adding the style sheet
            paneKeyBoard.getStylesheets().add(NumberPad.class.getResource("styler.css").toExternalForm());

            //Adding classes to buttons
            one.getStyleClass().add("key-button");
            two.getStyleClass().add("key-button");
            three.getStyleClass().add("key-button");
            four.getStyleClass().add("key-button");
            five.getStyleClass().add("key-button");
            six.getStyleClass().add("key-button");
            seven.getStyleClass().add("key-button");
            eight.getStyleClass().add("key-button");
            nine.getStyleClass().add("key-button");
            zero.getStyleClass().add("key-button");
            point.getStyleClass().add("key-button");
            delete.getStyleClass().add("key-button");
            deleteAll.getStyleClass().add("key-button");


            //setting the grid
            keyGrid.setAlignment(Pos.CENTER_RIGHT);

            //Adding keys to Grid Pane
            keyGrid.add(one,1,3);
            keyGrid.add(two,2,3);
            keyGrid.add(three,3,3);
            keyGrid.add(four,1,4);
            keyGrid.add(five,2,4);
            keyGrid.add(six,3,4);
            keyGrid.add(seven,1,5);
            keyGrid.add(eight,2,5);
            keyGrid.add(nine,3,5);
            keyGrid.add(point,1,6);
            keyGrid.add(zero,2,6);
            keyGrid.add(delete,3,6);
            keyGrid.add(deleteAll,2,7);



            for (TextField textField:textFields){
                textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    TextField mouseClickedTF;
                    if (newValue){
                        mouseClickedTF = textField;

                        one.setOnAction(event -> mouseClickedTF.appendText("1"));
                        two.setOnAction(event -> mouseClickedTF.appendText("2"));
                        three.setOnAction(event -> mouseClickedTF.appendText("3"));
                        four.setOnAction(event -> mouseClickedTF.appendText("4"));
                        five.setOnAction(event -> mouseClickedTF.appendText("5"));
                        six.setOnAction(event -> mouseClickedTF.appendText("6"));
                        seven.setOnAction(event -> mouseClickedTF.appendText("7"));
                        eight.setOnAction(event -> mouseClickedTF.appendText("8"));
                        nine.setOnAction(event -> mouseClickedTF.appendText("9"));
                        zero.setOnAction(event -> mouseClickedTF.appendText("0"));
                        point.setOnAction(event -> mouseClickedTF.appendText("."));
                        delete.setOnAction(click ->  {mouseClickedTF.setText(mouseClickedTF.getText().substring(0, mouseClickedTF.getText ().length() - 1)); });
                        deleteAll.setOnAction(click -> mouseClickedTF.setText(""));
                    }
                });
            }




        return paneKeyBoard;


    }


}



