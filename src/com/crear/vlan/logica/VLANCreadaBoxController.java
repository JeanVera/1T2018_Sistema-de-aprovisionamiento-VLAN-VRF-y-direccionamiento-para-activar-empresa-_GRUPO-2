/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crear.vlan.logica;

import Central.ScenarioCentral;
import Central.publicVariables;
//import static com.crear.vlan.logica.CreatedVLANonPEFormController.txtCiudad;
//import static com.crear.vlan.logica.CreatedVLANonPEFormController.txtEmpresa;
//import static com.crear.vlan.logica.CreatedVLANonPEFormController.txtNumVlan;
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

/**
 * FXML Controller class
 *
 * @author Prueba
 */
public class VLANCreadaBoxController implements Initializable {
    
    @FXML
    Label lblNumVLAN;
            
    @FXML
    Label lblCiudad;
    
    @FXML
    Label lblEmpresa;
            
    @FXML
    Label lblFecha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            publicVariables.conexion.insertTo(publicVariables.num_vlan_fin, publicVariables.empresa, publicVariables.city);
//        } catch (SQLException ex) {
//            Logger.getLogger(VLANCreadaBoxController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        lblNumVLAN.setText(String.valueOf(publicVariables.num_vlan_fin));
        lblCiudad.setText(publicVariables.city);
        lblEmpresa.setText(publicVariables.empresa);
        /**
         *EN ESTA SECCION SE DEBE AGREGAR LA VLAN A LA BASE DE DATOS
         * Y SE LA DEBE CREAR EN EL DISPOSITIVO PERTINENTE
         */
    }    
    
    @FXML
    public void btnCrearNewVlan(){
        Central.ScenarioCentral.secondaryWindows.close();
//        txtCiudad.clear();
//        txtEmpresa.clear();
//        txtNumVlan.clear();
    }
    
    @FXML
    public void btnVolverOpciones() throws IOException{
        ScenarioCentral.centerWindow.hide();
        ScenarioCentral.secondaryWindows.close();
        Parent root = FXMLLoader.load(getClass().getResource("/com/menu"
                + "/opciones/grafico/OptionsMenu.fxml"));
        Scene scene = new Scene(root);

        ScenarioCentral.centerWindow.setScene(scene);
        ScenarioCentral.centerWindow.show();
    }
}