/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GdptDatabase.UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Markcuz
 */
public class RemoveMemberController implements Initializable {
    
    private MainController mainWindow;
    
    public void setMainWindow(MainController mainWindow){
        this.mainWindow = mainWindow;
    }
    
    @FXML
    Button removeYes;
    
    @FXML
    Button removeNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void onRemoveYes(ActionEvent event) {
        Stage stage = (Stage) removeYes.getScene().getWindow();
        stage.close();
        
        mainWindow.removeMember();
        
    }
    
    public void onRemoveNo(ActionEvent event) {
        Stage stage = (Stage) removeNo.getScene().getWindow();
        stage.close();
    }
    
}
