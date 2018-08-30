/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opciones.interfaz;

import Central.publicVariables;
import static Central.publicVariables.ciudadesVRF;
import static Central.publicVariables.datos;
import com.clases.secundarias.VLAN;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author caroline
 */
public class OpcionesController implements Initializable {
    
    @FXML
    private AnchorPane mainContainer;
    
    AnchorPane vlanPane, vrfPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            vlanPane = FXMLLoader.load(getClass().getResource("/com/crear/vlan/interfaz/createdVLANonPEForm.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(OpcionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
        
    /**
     * METODO QUE MUESTRA EL FORMULARIO DE CREAR VLAN.
     */
    @FXML
    public void openVLANPane(){
        /**
         * mainContainer ES LA BASE SOBRE LA CUAL SE MONTAN LAS DEMAS
         *INTERFACES
         */
        mainContainer.getChildren().clear();
        mainContainer.getChildren().setAll((Node) vlanPane);
        
        animarPane((Node) vlanPane);
    }
    
    @FXML
    public void openVRFPane() throws SQLException, IOException{
        /**
         * mainContainer ES LA BASE SOBRE LA CUAL SE MONTAN LAS DEMAS
         *INTERFACES
         */
        vrfPane = FXMLLoader.load(getClass().getResource("/com/crear/vrf/interfaz/crearVRFonPE.fxml"));
        mainContainer.getChildren().clear();
        mainContainer.getChildren().setAll((Node) vrfPane);
        
        animarPane((Node) vrfPane);
        
        /**
         * COMPROBAMOS LOS DATOS EN VLAN ESTRUCTURA
         */
        System.out.println("I PRESSED VRF BUTTON");
        publicVariables.conexion.buscarDato("*", "clientes_vlans", null, null, Boolean.FALSE);
        System.out.println("VRF total datos "+publicVariables.conexion.getTotalDatos());
        publicVariables.conexion.createListData();
        
        
        ciudadesVRF.clear();
        for(Object info:datos){
            VLAN infoVLAN = (VLAN) info;
            String ciudad = infoVLAN.getNameCiudad();
            if(ciudadesVRF.isEmpty()){
                ciudadesVRF.add(ciudad);
            }else{
                if(!ciudadesVRF.contains(ciudad)){
                    ciudadesVRF.add(ciudad);
                }
            }
        }
        
    }
    
    /**
     * ESTE METODO AGREGA UNA ANIMACION CUANDO SE PRESIONA SOBRE ALGUNA
     * OPERACION EN LA VENTANA DE OPCIONES DE LA APP
     * @param nodoPane 
     */
    private void animarPane(Node nodoPane){
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(nodoPane);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }
}
