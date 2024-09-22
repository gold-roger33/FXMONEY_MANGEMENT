package com.personalmoneymanagement;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddButtonController {
    
    @FXML
    private Button incomeButton;
    @FXML
    private Button expenseButton;

    @FXML
    private TextField amountField;
    
    @FXML
    private TextField nameField;

    @FXML DatePicker datePicker;


    @FXML 
    private String transactionType = "Income";

    Database db = new Database();

    @FXML
    private void initialize() {
        db.createTable();
        //incomeButton.setOnMouseClicked(event -> transactionType = "Income");
        //expenseButton.setOnMouseClicked(event -> transactionType = "Expense");
        
        
        incomeButton.setOnMouseClicked(event -> {
            transactionType = "Income";
            
        incomeButton.setStyle("-fx-background-color: #388E3C; -fx-border-color: #FFFFFF; ");
        expenseButton.setStyle("-fx-background-color: #f44336; -fx-border-color: transparent; ");    
        
        });

        expenseButton.setOnMouseClicked(event -> {
            transactionType = "Expense";

            incomeButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: transparent; ");
            expenseButton.setStyle("-fx-background-color: #D32F2F; -fx-border-color: #FFFFFF; ");
        });
    } 


    @FXML
    private void save(){
        
        String amount = amountField.getText();
        String name = nameField.getText();
        String date = datePicker.getValue().toString();

       

        double amountValue;
        try {
            amountValue = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered: " + e.getMessage());
            return;
        }

        db.insertTransaction(transactionType, amountValue, name, date);
        db.view();
    }


    @FXML
    private void cancel() throws IOException{
        System.out.println("Action canceled.");

         FXMLLoader loader = new FXMLLoader(getClass().getResource("dashbord.fxml"));
         Parent dashboardScreen = loader.load();

         Stage stage = (Stage) amountField.getScene().getWindow();
    

         Scene scene = new Scene(dashboardScreen);
         stage.setScene(scene);
         

    }
}
