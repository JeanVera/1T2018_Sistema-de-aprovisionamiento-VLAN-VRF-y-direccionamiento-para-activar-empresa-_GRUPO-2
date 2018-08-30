/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.error.template.logica;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import AlertClass.ErroresMensajes;
import AlertClass.AlertMaker;
import static AlertClass.AlertMaker.dialog;
import static AlertClass.AlertMaker.node_to_be_blur;
import Central.ScenarioCentral;
import Central.publicVariables;
import com.jfoenix.controls.events.JFXDialogEvent;
import static javafx.application.Platform.exit;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author Administrador
 */
public class AlertErrorMakerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public  Label textTemp;
    
    @FXML
    public StackPane base;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTexto();
        
        base.minHeightProperty().set(publicVariables.altura);
        base.minWidthProperty().set(publicVariables.ancho);
        base.maxHeightProperty().set(publicVariables.altura);
        base.maxWidthProperty().set(publicVariables.ancho);
        
        System.out.println("ALTURA: "+base.minHeightProperty().getValue());
        System.out.println("ANCHO: "+base.minWidthProperty().getValue());
        
//        base.minHeight(publicVariables.altura);
//        base.maxHeight(publicVariables.altura);
//        
//        base.minWidth(publicVariables.ancho);
//        base.maxWidth(publicVariables.ancho);
        
        
        if(!publicVariables.transActive){
            publicVariables.transActive=true;
            Central.ScenarioCentral.secondaryWindows.initStyle(StageStyle.TRANSPARENT);
        }
    }
    
    public void setTexto(){
        textTemp.setText(ErroresMensajes.texto.toUpperCase());
    } 
    
    public void closeDialog(){
        ScenarioCentral.secondaryWindows.close();
    }
}
