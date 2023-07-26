package com.example.student_attendance_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class server_page1_adduser_controller {

    @FXML
    private Button adduser;

    @FXML
    private PasswordField cpss;
    @FXML
    private Button backpage;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField mailid;

    @FXML
    private PasswordField pss;

    @FXML
    private Label inmsg;

    @FXML
    void backto (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainpage.class.getResource("server_page1.fxml"));
        Stage window = (Stage) backpage.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1530, 790));
    }

    @FXML
    void useradd(ActionEvent event) throws IOException {
        if(pss.getText().equals(cpss.getText()) && pss.getLength()>8 && fname.getText()!=null && lname.getText()!=null && mailid.getText()!=null && pss.getText()!=null && cpss.getText()!=null  && (mailid.getText().contains("@kongu.edu") || mailid.getText().contains("@kongu.ac.in"))) {
            DB_file connectnow = new DB_file();
            Connection connectDB = connectnow.getConnection();
            String verifylogin = "INSERT INTO users VALUES('" + fname.getText() + "','" + lname.getText() + "','"  + mailid.getText() + "','" + pss.getText() + "');";
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(verifylogin);
                inmsg.setText("NEW USER CREATED SUCCESSFULLY.!!");

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

        else if(fname.getText()==null || lname.getText()==null || mailid.getText()==null || pss.getText()==null || cpss.getText()==null){
            inmsg.setText("SOME OF THE FIELDS ARE EMPTY.!");
        }

        else if(!mailid.getText().contains("@kongu.edu")){
            inmsg.setText("INVALID MAIL ID..!!!");
        }

        else if(!pss.getText().equals(cpss.getText())) {
            inmsg.setText("PASSWORD DOESN'T MATCH.!!");
        }

        else if(pss.getLength()<=7){
            inmsg.setText("PASSWORD IS WEAKER..!!");
        }
    }

}
