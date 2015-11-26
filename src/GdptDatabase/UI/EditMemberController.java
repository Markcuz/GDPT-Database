/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GdptDatabase.UI;


import GdptDatabase.Data.Groups;
import GdptDatabase.Data.Member;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class EditMemberController implements Initializable{
    
    @FXML
    Button finishEditMemberButton;
    
    @FXML
    TextField editFirstName;
    @FXML
    TextField editLastName;
    @FXML
    TextField editEnglishName;
    @FXML
    TextField editPhapDanh;
    @FXML
    TextField editAddress;

    @FXML
    TextField editPhone;
    @FXML
    TextField editSchool;
    @FXML
    TextField editYear;
    @FXML
    DatePicker editDOB;
    
    @FXML
    ChoiceBox<Groups.VNClass> editVietNgu;
    @FXML
    ChoiceBox<Groups.PPClass> editPhatPhap;
    
    @FXML
    ChoiceBox<Groups.Status> editStatus;
    @FXML
    ChoiceBox<Groups.Doan> editDoan;
    
    @FXML
    Button editImage;

    public EditMemberController () {
    }

    private MainController mainWindow;


    public void setMainWindow(MainController mainWindow){
        this.mainWindow = mainWindow;
    }
    
    public void setUpMember(Member member) {
        editFirstName.setText(member.firstName);
        editLastName.setText(member.lastName);
        editEnglishName.setText(member.englishName);
        editPhapDanh.setText(member.phapDanh);
        editAddress.setText(member.address);
        editPhone.setText(member.phoneNumber);
        editDOB.setValue(member.DOB);
        editDoan.setValue(member.nganh);
        editVietNgu.setValue(member.vn);
        editPhatPhap.setValue(member.pp);
        editSchool.setText(member.school);
        editYear.setText(member.year);
        editStatus.setValue(member.status);
    }
    
    @FXML 
    protected void onFinishEditMember(ActionEvent event) throws IOException {        
        Member me = new Member(editFirstName.getText(), editLastName.getText(), editEnglishName.getText(), editPhapDanh.getText(), editAddress.getText(), 
                editPhone.getText(), editDOB.getValue(), (Groups.Doan)editDoan.getValue(), (Groups.VNClass)editVietNgu.getValue(), (Groups.PPClass)editPhatPhap.getValue(), 
                editSchool.getText(), editYear.getText(), (Groups.Status)editStatus.getValue());
        
        String member = editFirstName.getText() +  "," + editLastName.getText() + "," +  editEnglishName.getText() + "," + editPhapDanh.getText()
                + "," + editAddress.getText() + "," + editPhone.getText() + "," + editDOB.getValue().toString() + "," + editDoan.getValue().toType()
                + "," + editVietNgu.getValue().toType() + "," + editPhatPhap.getValue().toType() + "," + editSchool.getText() + "," + editYear.getText()
                + "," + editStatus.getValue().toType();
        
        BufferedWriter bw;
        BufferedReader br;
        bw = new BufferedWriter(new FileWriter("members.csv", true));
        br = new BufferedReader(new FileReader("members.csv"));
        
        if(br.readLine() == null) {
            bw.write(member);
        }
        else {
            bw.newLine();
            bw.write(member);
        }
        
        bw.flush();
        
        Stage stage = (Stage) finishEditMemberButton.getScene().getWindow();
        stage.close();
        
        mainWindow.removeMember();
        mainWindow.addNewMember(me);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpChoiceBoxes();
    }
    
    
    public void setUpChoiceBoxes() {
        
        editDoan.getItems().clear();
        editDoan.getItems().addAll(Groups.Doan.values());
        
        editVietNgu.getItems().clear();
        editVietNgu.getItems().addAll(Groups.VNClass.values());
        
        editPhatPhap.getItems().clear();
        editPhatPhap.getItems().addAll(Groups.PPClass.values());
        
        editStatus.getItems().clear();
        editStatus.getItems().addAll(Groups.Status.values());
    }
    
    @FXML
    private void onAddImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file;
        
        file = fileChooser.showOpenDialog(new Stage());
    }
    
    
}
