/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdpt.database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Markcuz
 */
public class FXMLDocumentController implements Initializable {
    
    public enum Doan {
        OVNam, OVNu, TNam, TNu, NThanh, Htr
    }

    public enum VNClass {
        lop1, lop2, lop3, lop4, lop5, NONE
    }

    public enum PPClass {
        senNon, moMat, canhMem, chanCung, tungBay, huongThien, soThien, trungThien, chanhThien, bacHoa, bacTruc, bacKien, bacTri, bacDinh, bacLuc, NONE
    }   

    public enum Status {
        Active, Probation, NonActive
    }

    public class Member {
        private final String firstName;
        private final String lastName;
        private final String englishName;
        private final String phapDanh;
        private final String address;
        private final String phoneNumber;
        private final String DOB;
        private final Doan nganh;
        private final VNClass vn;
        private final PPClass pp;
        private final String school;
        private final String year;
        private final Status status;  

        public Member(String fName, String lName, String eName, String phapDanh, String add, String ph, String dob, Doan doan, VNClass vn, PPClass pp, String school, String year, Status stat) {
            this.firstName = fName;
            this.lastName = lName;
            this.englishName = eName;
            this.phapDanh = phapDanh;
            this.address = add;
            this.phoneNumber = ph;
            this.DOB = dob;
            this.nganh = doan;
            this.vn = vn;
            this.pp = pp;
            this.school = school;
            this.year = year;
            this.status = stat;
        }
    }
    
    public class MemberEntry {
        private SimpleStringProperty firstName = new SimpleStringProperty();
        private SimpleStringProperty lastName = new SimpleStringProperty();
        private SimpleStringProperty englishName = new SimpleStringProperty();
        private SimpleStringProperty phapDanh = new SimpleStringProperty();
        private SimpleStringProperty contact = new SimpleStringProperty();
        
        
        public MemberEntry(String fName, String lName, String eName, String pDanh, String cont) { 
            this.firstName.set(fName);
            this.lastName.set(lName);
            this.englishName.set(eName);
            this.phapDanh.set(pDanh);
            this.contact.set(cont);
            
        }
        
        public String getFirstName() {
            return firstName.get();
        }
        
        public String getLastName() {
            return lastName.get();
        }
        
        public String getEnglishName() {
            return englishName.get();
        }
        
        public String getPhapDanh() {
            return phapDanh.get();
        }
        
        public String getContact() {
            return contact.get();
        }
    }
    
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
    ChoiceBox<String> searchType;
    
    //the type list
    @FXML
    TreeView<String> typeTree;
    
    ObservableList<MemberEntry> data;
    
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

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
            setupSearchType();
            setupTypeTree();
            loadCSV();
            setupTable();
    } 
    
    public void onAddMember(ActionEvent event) {
        System.out.println("Add pressed!");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLNewMember.fxml"));
           
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add New Member");
            stage.show();
        }
        catch (IOException e) {
            System.out.println("Add pressed2!");
            e.printStackTrace();
        }
        
        
        /*
        String value = typeTree.getSelectionModel().getSelectedItem().toString();
        System.out.println(value);
        
        Member me = new Member("Duy Khang", "Pham", "Marcus", "Van Tan", "4 Hannans Street Morley", "0401321785", "030491", Doan.Htr, VNClass.NONE, PPClass.bacDinh, "UWA", "EECE", Status.Active);
        
        MemberEntry meEntry = new MemberEntry(me.firstName, me.lastName, me.englishName, me.phapDanh, me.phoneNumber);
        data.add(meEntry);
        System.out.println(me.firstName);*/
    }
    
    @FXML
    Button finishAddMemberButton;
        
    public void onFinishAddMember(ActionEvent event) {
        Stage stage = (Stage) finishAddMemberButton.getScene().getWindow();
        stage.close();
        
        System.out.println("Add pressed!");
    } 

    public void onExportList(ActionEvent event) {
        System.out.println("Export pressed!");
    }
    
    public void onSearch(ActionEvent event) {
        System.out.println("Search pressed!");
        String type = searchType.getValue();
        System.out.println(type);
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
        
    }
    
    public void setupTable() {
      
        firstNameCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("lastName"));
        englishNameCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("englishName"));
        phapDanhCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("phapDanh"));
        contactCol.setCellValueFactory(new PropertyValueFactory<MemberEntry, String>("contact"));
        
        data = FXCollections.observableArrayList();
        nameTable.setItems(data);
        
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
