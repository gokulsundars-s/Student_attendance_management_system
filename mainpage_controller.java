package com.example.student_attendance_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class mainpage_controller {

    @FXML
    private Label altmsg;
    @FXML
    private Button goin;

    @FXML
    private PasswordField pw;

    @FXML
    private TextField un;

    @FXML
    void topage(ActionEvent event) throws IOException {
        if(un.getText().toString().equals("sams.admin@kongu.edu") && pw.getText().toString().equals("SamS@123")){
            FXMLLoader fxmlLoader = new FXMLLoader(mainpage.class.getResource("server_page1.fxml"));
            Stage window = (Stage) goin.getScene().getWindow();
            window.setScene(new Scene(fxmlLoader.load(), 1530, 790));
        }

        else{
            DB_file connectnow = new DB_file();
            Connection connectDB = connectnow.getConnection();
            String verifylogin = "SELECT count(1) FROM users WHERE Mail_ID = '"+un.getText()+"' and Password = '"+pw.getText()+"';";
            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifylogin);
                while (queryResult.next())
                {
                    if(queryResult.getInt(1)==1){
                        FXMLLoader fxmlLoader = new FXMLLoader(mainpage.class.getResource("mainpage_page1.fxml"));
                        Stage window = (Stage) goin.getScene().getWindow();
                        window.setScene(new Scene(fxmlLoader.load(), 1530, 790));
                    }else {
                        altmsg.setText("INVALID MAIL - ID OR PASSWORD..!!");
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                e.getCause();
            }

        }

    }

}
