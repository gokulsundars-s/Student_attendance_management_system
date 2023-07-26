package com.example.student_attendance_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InvalidPropertiesFormatException;

public class mainpage_page1_report_controller {

    @FXML
    private Button bkout;

    @FXML
    private DatePicker fromdte;

    @FXML
    private Button loadfile;

    @FXML
    private TextField rpcls;

    @FXML
    private DatePicker todte;

    @FXML
    private Label setmsg;

    @FXML
    void bckot(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainpage.class.getResource("mainpage_page1.fxml"));
        Stage window = (Stage) bkout.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 1530, 790));
    }

    @FXML
    void genfile(ActionEvent event) {
        DB_file connectnow = new DB_file();
        Connection connectDB = connectnow.getConnection();
        String verifylogin = "SELECT * FROM attendance WHERE Class = ('"+ rpcls.getText() + "') AND Date BETWEEN '" + fromdte.getValue() + "' AND '" + todte.getValue()+"';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);

            PrintWriter pw = new PrintWriter(new File("G:\\Student Attendence Management System\\CSV Files\\2-CSE-A.csv"));
            StringBuilder sb = new StringBuilder();

            sb.append("Date");
            sb.append(",");
            sb.append("ABSENT - 1");
            sb.append(",");
            sb.append("ABSENT - 2");
            sb.append(",");
            sb.append("ABSENT - 3");
            sb.append(",");
            sb.append("ABSENT - 4");
            sb.append(",");
            sb.append("ABSENT - 5");
            sb.append(",");
            sb.append("ABSENT - 6");
            sb.append(",");
            sb.append("ABSENT - 7");
            sb.append(",");
            sb.append("ON-DUTY - 1");
            sb.append(",");
            sb.append("ON-DUTY - 2");
            sb.append(",");
            sb.append("ON-DUTY - 3");
            sb.append(",");
            sb.append("ON-DUTY - 4");
            sb.append(",");
            sb.append("ON-DUTY - 5");
            sb.append(",");
            sb.append("ON-DUTY - 6");
            sb.append(",");
            sb.append("ON-DUTY - 7");
            sb.append("\n");


            while (queryResult.next()) {
                String date = queryResult.getString("Date");
                String ab1 = queryResult.getString("AB1");
                String ab2 = queryResult.getString("AB2");
                String ab3 = queryResult.getString("AB3");
                String ab4 = queryResult.getString("AB4");
                String ab5 = queryResult.getString("AB5");
                String ab6 = queryResult.getString("AB6");
                String ab7 = queryResult.getString("AB7");
                String od1 = queryResult.getString("OD1");
                String od2 = queryResult.getString("OD2");
                String od3 = queryResult.getString("OD3");
                String od4 = queryResult.getString("OD4");
                String od5 = queryResult.getString("OD5");
                String od6 = queryResult.getString("OD6");
                String od7 = queryResult.getString("OD7");

                sb.append(date);
                sb.append(",");
                sb.append("\""+ ab1 +"\"");
                sb.append(",");
                sb.append("\""+ ab2 +"\"");
                sb.append(",");
                sb.append("\""+ ab3 +"\"");
                sb.append(",");
                sb.append("\""+ ab4 +"\"");
                sb.append(",");
                sb.append("\""+ ab5 +"\"");
                sb.append(",");
                sb.append("\""+ ab6 +"\"");
                sb.append(",");
                sb.append("\""+ ab7 +"\"");
                sb.append(",");
                sb.append("\""+ od1 +"\"");
                sb.append(",");
                sb.append("\""+ od2 +"\"");
                sb.append(",");
                sb.append("\""+ od3 +"\"");
                sb.append(",");
                sb.append("\""+ od4 +"\"");
                sb.append(",");
                sb.append("\""+ od5 +"\"");
                sb.append(",");
                sb.append("\""+ od6 +"\"");
                sb.append(",");
                sb.append("\""+ od7 +"\"");
                sb.append("\n");
            }

            pw.write(sb.toString());
            pw.close();
            setmsg.setText("THE FILE IS GENERATED SUCCESSFULLY..!!");
        } catch (Exception e) {
            setmsg.setText("NO DATA FOUND BETWEEN THE DATES..!!");
            e.printStackTrace();
            e.getCause();
        }


    }

}
