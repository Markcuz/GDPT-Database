/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GdptDatabase.UI;

import GdptDatabase.Data.Groups;
import GdptDatabase.Data.Groups.Doan;
import GdptDatabase.Data.Groups.PPClass;
import static GdptDatabase.Data.Groups.PPClass.bacDinh;
import GdptDatabase.Data.Groups.Status;
import static GdptDatabase.Data.Groups.Status.Active;
import GdptDatabase.Data.Groups.VNClass;
import static GdptDatabase.Data.Groups.VNClass.lop1;
import GdptDatabase.Data.Member;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Markcuz
 */
public class AddMemberController implements Initializable{
    
    @FXML
    Button finishAddMemberButton;
    
    @FXML
    TextField addFirstName;
    @FXML
    TextField addLastName;
    @FXML
    TextField addEnglishName;
    @FXML
    TextField addPhapDanh;
    @FXML
    TextField addAddress;

    @FXML
    TextField addPhone;
    @FXML
    TextField addSchool;
    @FXML
    TextField addYear;
    @FXML
    DatePicker addDOB;
    
    @FXML
    ChoiceBox<Groups.VNClass> addVietNgu;
    @FXML
    ChoiceBox<Groups.PPClass> addPhatPhap;
    
    @FXML
    ChoiceBox<Groups.Status> addStatus;
    @FXML
    ChoiceBox<Groups.Doan> addDoan;
    
    @FXML
    Button addImage;

    public AddMemberController () {
    }

    private MainController mainWindow;


    public void setMainWindow(MainController mainWindow){
        this.mainWindow = mainWindow;
    }
    
    @FXML 
    protected void onFinishAddMember(ActionEvent event) throws IOException {        
        Member me = new Member(addFirstName.getText(), addLastName.getText(), addEnglishName.getText(), addPhapDanh.getText(), addAddress.getText(), 
                addPhone.getText(), addDOB.getValue().toString(), (Doan)addDoan.getValue(), (VNClass)addVietNgu.getValue(), (PPClass)addPhatPhap.getValue(), 
                addSchool.getText(), addYear.getText(), (Status)addStatus.getValue());
        
        String member = addFirstName.getText() +  "," + addLastName.getText() + "," +  addEnglishName.getText() + "," + addPhapDanh.getText()
                + "," + addAddress.getText().toString() + "," + addPhone.getText() + "," + addDOB.getValue().toString() + "," + addDoan.getValue().toString()
                + "," + addVietNgu.getValue().toString() + "," + addPhatPhap.getValue().toString() + "," + addSchool.getText() + "," + addYear.getText()
                + "," + addStatus.getValue().toString();
        
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter("members.csv", true));
        
        bw.write(member);
        bw.newLine();
        bw.flush();
        
        Stage stage = (Stage) finishAddMemberButton.getScene().getWindow();
        stage.close();
        
        mainWindow.addNewMember(me);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpChoiceBoxes();
    }
    
    
    public void setUpChoiceBoxes() {
        
        addDoan.getItems().clear();
        addDoan.getItems().addAll(Doan.values());
        
        addVietNgu.getItems().clear();
        addVietNgu.getItems().addAll(VNClass.values());
        
        addPhatPhap.getItems().clear();
        addPhatPhap.getItems().addAll(PPClass.values());
        
        addStatus.getItems().clear();
        addStatus.getItems().addAll(Status.values());
    }
    
    @FXML
    private void onAddImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file;
        
        file = fileChooser.showOpenDialog(new Stage());
    }
    
}
