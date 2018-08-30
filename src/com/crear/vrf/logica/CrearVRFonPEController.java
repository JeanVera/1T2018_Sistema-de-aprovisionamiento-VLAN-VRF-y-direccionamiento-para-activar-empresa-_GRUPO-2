/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crear.vrf.logica;

import AlertClass.AlertMaker;
import AlertClass.ErroresMensajes;
import Central.publicVariables;
import static Central.publicVariables.ciudadesVRF;
import static Central.publicVariables.datos;
import static Central.publicVariables.empresasVRF;
import static Central.publicVariables.vlansVRF;
import com.clases.secundarias.VLAN;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Administrador
 */
public class CrearVRFonPEController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public JFXComboBox vrfCiudad;
    
    @FXML
    public JFXComboBox vrfEmpresa;
    
    @FXML
    public JFXComboBox vrfVLAN;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("I AM HERE VRF");
    }
    
    
    
    @FXML
    public void onMouseEnteredCiudad(){
        vrfCiudad.setItems(publicVariables.ciudadesVRF);
    }
    
    @FXML
    void onMouseClickedCiudad(){
        empresasVRF.clear();
        vlansVRF.clear();
    }
    
    @FXML
    void onMouseClickedVlan() throws IOException{
        if(vrfCiudad.getValue()==null && vrfEmpresa.getValue()==null){
            ErroresMensajes.texto="Debe seleccionar la ciudad y la empresa.";
            Parent root=FXMLLoader.load(getClass().getResource("/com/error"
                    + "/template/interfaz/alertErrorMaker.fxml"));
            
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();
        }else if(vrfEmpresa.getValue()==null){
            ErroresMensajes.texto="Debe seleccionar la empresa.";
            Parent root=FXMLLoader.load(getClass().getResource("/com/error"
                    + "/template/interfaz/alertErrorMaker.fxml"));
            
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();
        }
    }
    
    @FXML
    public void onMouseEnteredVlan(){
        if (vrfCiudad.getValue() != null && vrfEmpresa.getValue()!=null) {
            for (Object info : datos) {
                VLAN infoVLAN = (VLAN) info;
                String empresa = (String) vrfEmpresa.getValue();
                String ciudad = (String) vrfCiudad.getValue();
                if (infoVLAN.getNameEmpresa().equals(empresa) && infoVLAN.getNameCiudad().equals(ciudad)) {
                    String num_vlan = String.valueOf(infoVLAN.getNumberVLAN());
                    if (vlansVRF.isEmpty()) {
                        vlansVRF.add(num_vlan);
                    } else {
                        if (!vlansVRF.contains(num_vlan)) {
                            vlansVRF.add(num_vlan);
                        }
                    }
                }
            }
        }
        vrfVLAN.setItems(publicVariables.vlansVRF);
    }
    
    @FXML
    public void onMouseEnteredEmpresa() throws IOException {
        if (vrfCiudad.getValue() != null) {
            for (Object info : datos) {
                VLAN infoVLAN = (VLAN) info;
                String ciudad = (String) vrfCiudad.getValue();
                if (infoVLAN.getNameCiudad().equals(ciudad)) {
                    String empresa = infoVLAN.getNameEmpresa();
                    if (empresasVRF.isEmpty()) {
                        empresasVRF.add(empresa);
                    } else {
                        if (!empresasVRF.contains(empresa)) {
                            empresasVRF.add(empresa);
                        }
                    }
                }
            }
        }
        vrfEmpresa.setItems(publicVariables.empresasVRF);
    }
    
     @FXML
    void onMouseClickedEmpresa() throws IOException {
        vlansVRF.clear();
        if(vrfCiudad.getValue()==null){
            ErroresMensajes.texto="Debe seleccionar una ciudad.";
            Parent root=FXMLLoader.load(getClass().getResource("/com/error"
                    + "/template/interfaz/alertErrorMaker.fxml"));
            
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();
        }
    }
    
    @FXML
    public void closeDialog(){
        AlertMaker.dialog.close();
    }
    
}
