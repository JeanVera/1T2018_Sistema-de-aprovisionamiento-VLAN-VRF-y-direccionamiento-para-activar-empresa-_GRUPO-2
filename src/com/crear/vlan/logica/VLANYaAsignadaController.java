/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crear.vlan.logica;

import Central.publicVariables;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Prueba
 */
public class VLANYaAsignadaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    Label lblNumVLAN;
    
    @FXML
    Label lblTextoWarning;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblNumVLAN.setText(String.valueOf(publicVariables.num_vlan_fin));
        try {
            publicVariables.conexion.buscarDato("ciudad", "clientes_vlans", "numero_vlan", Integer.toString(publicVariables.numero_vlan), Boolean.TRUE);
        } catch (SQLException ex) {
            Logger.getLogger(VLANYaAsignadaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblTextoWarning.setText("LA VLAN ESCOGIDA YA EXISTE EN LA CIUDAD DE "+publicVariables.conexion.getDato("ciudad"));
    }
    
    @FXML
    public void ClickOnSi() throws IOException{
        Central.ScenarioCentral.secondaryWindows.close();
        
        try {
            publicVariables.conexion.insertTo(publicVariables.num_vlan_fin, publicVariables.empresa, publicVariables.city.toUpperCase());
        } catch (SQLException ex) {
            Logger.getLogger(VLANYaAsignadaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Segundo si activo");
        Parent root=FXMLLoader.load(getClass().getResource("/com/crear/vlan/"
            + "interfaz/VLANCreadaBox.fxml"));
        Scene escena = new Scene(root);
        escena.setFill(Color.TRANSPARENT);
        Central.ScenarioCentral.secondaryWindows.setScene(escena);
        Central.ScenarioCentral.secondaryWindows.show();
        
    }
    
    @FXML
    public void ClickOnNo(){
        Central.ScenarioCentral.secondaryWindows.close();
    }
    
}
