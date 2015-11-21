/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GdptDatabase.UI;

import GdptDatabase.Data.Groups.Doan;
import static GdptDatabase.Data.Groups.Doan.NThanh;
import GdptDatabase.Data.Member;
import GdptDatabase.Data.MemberEntry;
import GdptDatabase.Data.Groups.PPClass;
import static GdptDatabase.Data.Groups.PPClass.bacDinh;
import GdptDatabase.Data.Groups.Status;
import static GdptDatabase.Data.Groups.Status.Active;
import GdptDatabase.Data.Groups.VNClass;
import static GdptDatabase.Data.Groups.VNClass.lop1;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Markcuz
 */
public class MainController implements Initializable {
    
   //the buttons
    @FXML
    Button addMemberButton;
    @FXML
    Button exportListButton;
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
    ChoiceBox<Doan> searchType;
    
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
    
    //LinkedList<Member> allList;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
    } 
    
    @FXML
    public void onAddMember(ActionEvent event) throws Exception{
        System.out.println("Add pressed!");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMember.fxml"));

        Pane pane = (Pane)loader.load();
        
        AddMemberController controller = loader.getController();
        controller.setMainWindow(this);
        
        Stage stage = new Stage();
        
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(addMemberButton.getScene().getWindow());
        
        Scene scene = new Scene(pane);
        
        stage.setScene(scene);
        stage.setTitle("AddMember");
        stage.show();
        
        //String value = typeTree.getSelectionModel().getSelectedItem().getValue();
        //System.out.println(value);
 
    }
    
    @FXML
    public void addNewMember(Member member) {       
        MemberEntry meEntry = new MemberEntry((String)member.firstName, (String)member.lastName, (String)member.englishName, (String)member.phapDanh, (String)member.phoneNumber, 0);
        tableList.add(meEntry); 
    } 
    
    public void onExportList(ActionEvent event) {
        
        
        MemberEntry meEntry = new MemberEntry("hi", "hello", "chao", "yo", "wassup",0);
        
        tableList.add(meEntry); 
        
        System.out.println("Export pressed!");
    }
    
    public void onSearch(ActionEvent event) {
        /*System.out.println("Search pressed!");
        String type = searchType.getValue();
        System.out.println(type);*/
    }   
    
    public void setupSearchType() {
        /*
        searchType.getItems().clear();
        searchType.getItems().addAll("First Name", "Last Name", "Phap Danh");
        searchType.setValue("First Name");
        */
        searchType.getItems().clear();
        searchType.getItems().addAll(Doan.values());
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
        
        VNItem.getChildren().addAll(lop_1, lop_2, lop_3, lop_4, lop_5);
        
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
       // typeTree.getSelectionModel().selectedItemProperty().addListener((InvalidationListener) typeTreeSelected);
    }
    
    public void loadCSV() {
       // allList.clear();
        
    }
        
    public void setupTable() {
      
        firstNameCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("lastName"));
        englishNameCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("englishName"));
        phapDanhCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("phapDanh"));
        contactCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("contact"));
        
        tableList = FXCollections.observableArrayList();
        nameTable.setItems(tableList);
        
    }
    
    /*
    private final ChangeListener<String> typeTreeSelected = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            //change table
        }
        
    };
*/
    
}
