/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GdptDatabase.UI;

import GdptDatabase.Data.Groups;
import GdptDatabase.Data.Groups.Doan;
import GdptDatabase.Data.Member;
import GdptDatabase.Data.MemberEntry;
import GdptDatabase.Data.Groups.PPClass;
import GdptDatabase.Data.Groups.Status;
import GdptDatabase.Data.Groups.VNClass;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Markcuz
 */
public class MainController implements Initializable {
    
   //the buttons
    @FXML
    Button addMemberButton;
    @FXML
    Button editMemberButton;
    @FXML
    Button removeMemberButton;
    @FXML 
    Button setMembersButton;
    @FXML
    Button searchButton;
    
    //the labels
    @FXML
    Label mainLabel;
    @FXML
    Label subLabel;
    
    //the toggle
    @FXML
    ToggleButton languageToggle;
    
    //the text field
    @FXML
    TextField searchText;
    
    //the choicebox
    @FXML
    ChoiceBox<String> searchType;
    
    @FXML
    MenuBar menuBar;
    
    //the type list
    @FXML
    TreeView<String> typeTree;
    
    //the table
    @FXML
    TableView<MemberEntry> nameTable;
    @FXML
    TableColumn firstNameCol;
    @FXML
    TableColumn lastNameCol;
    @FXML
    TableColumn phapDanhCol;
    @FXML
    TableColumn englishNameCol;
    @FXML
    TableColumn contactCol;

    //standard variable
    ObservableList<MemberEntry> tableList;
    
    LinkedList<Member> allList = new LinkedList<>();
    LinkedList<Member> currentList = new LinkedList<>();
    
    int memberID;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        setupSearchType();
        setupTypeTree();
        setupTable();
        loadCSV();
        
        typeTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String value = typeTree.getSelectionModel().getSelectedItem().getValue();
                searchText.clear();

                    if (value.equals("All")) {
                        tableList.clear();
                        currentList.clear();
                        for(int i =0; i<allList.size(); i++) {
                            currentList.add(allList.get(i));
                            addToList(allList.get(i));
                        }
                    }
                     
                    else {                     
                        for (Doan value1 : Doan.values()) {
                            if (value.equals(value1.toString())) {
                                tableList.clear();
                                currentList.clear();
                                for (int j = 0; j<allList.size(); j++) {
                                    if (allList.get(j).nganh == value1) {
                                        currentList.add(allList.get(j));
                                        addToList(allList.get(j));
                                    }
                                }
                            }
                        }
                        
                        for (PPClass value1 : PPClass.values()) {
                            if (value.equals(value1.toString())) {
                                tableList.clear();
                                currentList.clear();
                                for (int j = 0; j<allList.size(); j++) {
                                    if (allList.get(j).pp == value1) {
                                        currentList.add(allList.get(j));
                                        addToList(allList.get(j));
                                    }
                                }
                            }
                        }
                        
                        for (VNClass value1 : VNClass.values()) {
                            if (value.equals(value1.toString())) {
                                tableList.clear();
                                currentList.clear();
                                for (int j = 0; j<allList.size(); j++) {
                                    if (allList.get(j).vn == value1) {
                                        currentList.add(allList.get(j));
                                        addToList(allList.get(j));
                                    }
                                }
                            }
                        }
                    }
            }
        });
    } 
    
    public void onAddMember(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMember.fxml"));

        try {
            Pane pane = (Pane)loader.load();

            AddMemberController controller = loader.getController();
            controller.setMainWindow(this);
        
            Stage stage = new Stage();
        
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(addMemberButton.getScene().getWindow());
        
            Scene scene = new Scene(pane);
        
            stage.setScene(scene);
            stage.setTitle("Add Member");
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Failed to open new Add Member window");
        }
 
    }
    
    public void addToList(Member member) {            
        MemberEntry meEntry = new MemberEntry((String)member.firstName, (String)member.lastName, (String)member.englishName, (String)member.phapDanh, (String)member.phoneNumber, member);
        tableList.add(meEntry); 
    } 
    
    public void addNewMember(Member member) {
        allList.add(member);
        
        String value = typeTree.getSelectionModel().getSelectedItem().getValue();
        
        if(value.equals(member.nganh.toString())) {
            addToList(member);
            currentList.add(member);
        }
        else if (value.equals(member.vn.toString())) {
            addToList(member);
            currentList.add(member);
        }
        else if (value.equals(member.pp.toString())) {
            addToList(member);
            currentList.add(member);
        }
        else if (value.equals("All")) {
            addToList(member);
            currentList.add(member);
        }
    }
      
    public void onEditMember(ActionEvent event) {
        
        MemberEntry selected = nameTable.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditMember.fxml"));

        try {
            Pane pane = (Pane)loader.load();

            EditMemberController controller = loader.getController();
            controller.setMainWindow(this);
            controller.setUpMember(selected.me);
            
            Stage stage = new Stage();
        
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(editMemberButton.getScene().getWindow());
        
            Scene scene = new Scene(pane);
        
            stage.setScene(scene);
            stage.setTitle("Edit Member");
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Failed to open new Edit Member window");
        }
    }
    
    public void onRemoveMember(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveMember.fxml"));

        try {
            Pane pane = (Pane)loader.load();

            RemoveMemberController controller = loader.getController();
            controller.setMainWindow(this);
        
            Stage stage = new Stage();
        
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initOwner(removeMemberButton.getScene().getWindow());
        
            Scene scene = new Scene(pane);
        
            stage.setScene(scene);
            stage.setTitle("Remove Member");
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Failed to open new Remove Member window");
        }
    }
    
    public Member removeMember() {
        MemberEntry selected = nameTable.getSelectionModel().getSelectedItem();
        
        nameTable.getItems().remove(nameTable.getSelectionModel().getSelectedIndex());

        allList.remove(selected.me);
        currentList.remove(selected.me);
        
        File file = new File("members.csv");
        
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file));
        
            if(file.delete()) {
                for(int i =0; i<allList.size(); i++) {
                    Member me = allList.get(i);
                    String member = me.firstName +  "," + me.lastName + "," +  me.englishName + "," + me.phapDanh
                        + "," + me.address + "," + me.phoneNumber + "," + me.DOB.format(DateTimeFormatter.ISO_DATE) + "," + me.nganh.toType()
                        + "," + me.vn.toType() + "," + me.pp.toType() + "," + me.school + "," + me.year
                        + "," + me.status.toType();
                
                    bw.write(member);
                    bw.newLine();  
                }       
            }
            bw.flush();
        } 
        catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selected.me;
    }
    
    /*
    public void onSetMembers(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveMember.fxml"));

        try {
            Pane pane = (Pane)loader.load();

            SetMembersController controller = loader.getController();
            controller.setMainWindow(this);
        
            Stage stage = new Stage();
        
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initOwner(setMembersButton.getScene().getWindow());
        
            Scene scene = new Scene(pane);
        
            stage.setScene(scene);
            stage.setTitle("Set Members");
            stage.show();
        }
        catch (Exception e) {
            System.out.println("Failed to open new Remove Member window");
        }
    }
    */
    
    public void onImportCSV(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        
        File input;
        input = fileChooser.showOpenDialog(new Stage());
        
        File output = new File("members.csv");
        
        try {
            Files.copy(input.toPath(), output.toPath(), REPLACE_EXISTING);
            loadCSV();
        }  
        catch (IOException e) {
            
        }
        
        System.out.println("Import Successful!");
    }
       
    public void onExportCSV(ActionEvent event) {
       
        File folder;
        DirectoryChooser dChooser = new DirectoryChooser();
        
        folder = dChooser.showDialog(new Stage());

        File out = new File(folder.toString()+File.separator+"members.csv");
        
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(out));
        
            for(int i =0 ; i <allList.size(); i++) {
                String member = allList.get(i).firstName +  "," + allList.get(i).lastName + "," +  allList.get(i).englishName + "," + allList.get(i).phapDanh
                + "," + allList.get(i).address + "," + allList.get(i).phoneNumber + "," + allList.get(i).DOB.format(DateTimeFormatter.ISO_DATE) + "," + allList.get(i).nganh
                + "," + allList.get(i).vn+ "," + allList.get(i).pp + "," + allList.get(i).school + "," + allList.get(i).year
                + "," + allList.get(i).status;
        
                bw.write(member);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        }
        catch (IOException e) {
            
        }
        System.out.println("Export Successful!");
    }
    
    public void onSearch(ActionEvent event) {  
        String type = searchType.getValue();

        tableList.clear();
        
        for (int j = 0; j<currentList.size(); j++) {
            switch(type) {
                case "First Name":
                    if (!currentList.get(j).firstName.toLowerCase().contains(searchText.getText().toLowerCase())) {
                       // currentList.remove(j);
                    }
                    else {
                        addToList(currentList.get(j));
                    }
                    break;
                case "Last Name":
                    if (!currentList.get(j).lastName.toLowerCase().contains(searchText.getText().toLowerCase())) {
                        //currentList.remove(j);
                    }
                    else {
                        addToList(currentList.get(j));
                    }
                    break; 
                case "Phap Danh":
                    if (!currentList.get(j).phapDanh.toLowerCase().contains(searchText.getText().toLowerCase())) {
                        //currentList.remove(j);
                    }
                    else {
                        addToList(currentList.get(j));
                    }
                    break;
            }
        }  
    }   
    
    public void onClear(ActionEvent event) {
        searchText.clear();
        
        tableList.clear();
        
        for (int j = 0; j<currentList.size(); j++) {
            addToList(currentList.get(j));
        }
    } 
    
    public void setupSearchType() {
        searchType.getItems().clear();
        searchType.getItems().addAll("First Name", "Last Name", "Phap Danh");
        searchType.setValue("First Name");
    }
    
    /**
     * @brief Sets up the left tree and populates the root
     */
    public void setupTypeTree() {
        //root ie. All members
        TreeItem<String> rootItem= new TreeItem<>("All");
        typeTree.setRoot(rootItem);
        rootItem.setExpanded(false);
        
        //nganh
        TreeItem<String> nganhItem= new TreeItem<>("Nganh");

        TreeItem<String> huynh_truong = new TreeItem<>("Huynh Truong");
        TreeItem<String> nganh_thanh = new TreeItem<>("Nganh Thanh");
        TreeItem<String> thieu_nam = new TreeItem<>("Thieu Nam");
        TreeItem<String> thieu_nu = new TreeItem<>("Thieu Nu");
        TreeItem<String> OV_nam = new TreeItem<>("Oanh Vu Nam");
        TreeItem<String> OV_nu = new TreeItem<>("Oanh Vu Nu");

        nganhItem.getChildren().addAll(huynh_truong, nganh_thanh, thieu_nam, thieu_nu, OV_nam, OV_nu);
        
        rootItem.getChildren().add(nganhItem);
        
        //Viet Ngu
        TreeItem<String> VNItem= new TreeItem<>("Viet Ngu Class");
        
        TreeItem<String> lop_1 = new TreeItem<>("Lop 1");
        TreeItem<String> lop_2 = new TreeItem<>("Lop 2");
        TreeItem<String> lop_3 = new TreeItem<>("Lop 3");
        TreeItem<String> lop_4 = new TreeItem<>("Lop 4");
        TreeItem<String> lop_5 = new TreeItem<>("Lop 5");
        TreeItem<String> none = new TreeItem<>("None");
        
        VNItem.getChildren().addAll(lop_1, lop_2, lop_3, lop_4, lop_5, none);
        
        rootItem.getChildren().add(VNItem);
        
        //Phat Phap
        TreeItem<String> PPItem= new TreeItem<>("Phat Phap Class");
        
        TreeItem<String> sen_non = new TreeItem<>("Sen Non");
        TreeItem<String> mo_mat = new TreeItem<>("Mo Mat");
        TreeItem<String> canh_mem = new TreeItem<>("Canh Mem");
        TreeItem<String> chan_cung = new TreeItem<>("Chan Cung");
        TreeItem<String> tung_bay = new TreeItem<>("Tung Bay");
        
        TreeItem<String> huong_thien = new TreeItem<>("Huong Thien");
        TreeItem<String> so_thien = new TreeItem<>("So Thien");
        TreeItem<String> trung_thien = new TreeItem<>("Trung Thien");
        TreeItem<String> chanh_thien = new TreeItem<>("Chanh Thien");
        
        TreeItem<String> bac_hoa = new TreeItem<>("Bac Hoa");
        TreeItem<String> bac_truc = new TreeItem<>("Bac Truc");
        
        TreeItem<String> bac_kien = new TreeItem<>("Bac Kien");
        TreeItem<String> bac_tri = new TreeItem<>("Bac Tri");
        TreeItem<String> bac_dinh = new TreeItem<>("Bac Dinh");
        TreeItem<String> bac_luc = new TreeItem<>("Bac Luc");
        
        PPItem.getChildren().addAll(sen_non, mo_mat, canh_mem, chan_cung, tung_bay, huong_thien, so_thien, trung_thien, chanh_thien, bac_hoa, bac_truc, bac_kien, bac_tri, bac_dinh, bac_luc);
        
        rootItem.getChildren().add(PPItem);
        
        typeTree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        typeTree.getSelectionModel().selectFirst();
    }
    
    public void loadCSV() {
        allList.clear();
        currentList.clear();
        tableList.clear();
       
        File file = new File("members.csv");
        
        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while((line = br.readLine()) != null) {
                String[] split = line.split(",");
                
                Member member = new Member(split[0], split[1], split[2], split[3], split[4], split[5], LocalDate.parse(split[6]), 
                        (Doan)Doan.valueOf(split[7]), (VNClass)VNClass.valueOf(split[8]), (PPClass)PPClass.valueOf(split[9]), split[10], split[11], (Status)Status.valueOf(split[12]));
                
                addNewMember(member);
                System.out.println(line);
            }
            fr.close();
            br.close();
        } 
        catch (IOException e){
            System.out.println("No members file");
        }
    }
        
    public void setupTable() {
      
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        englishNameCol.setCellValueFactory(new PropertyValueFactory<>("englishName"));
        phapDanhCol.setCellValueFactory(new PropertyValueFactory<>("phapDanh"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        
        tableList = FXCollections.observableArrayList();
        nameTable.setItems(tableList);   
    }
    
}
