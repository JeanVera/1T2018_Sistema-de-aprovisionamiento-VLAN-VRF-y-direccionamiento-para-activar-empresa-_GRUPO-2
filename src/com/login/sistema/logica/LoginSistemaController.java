/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.sistema.logica;

import AlertClass.AlertMaker;
import AlertClass.ErroresMensajes;
import Central.ScenarioCentral;
import Central.publicVariables;
import static Central.publicVariables.conexion;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Administrador
 */
public class LoginSistemaController implements Initializable {

    @FXML
    private JFXTextField nameUser;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private Pane paneContainer;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        publicVariables.altura=391;
        publicVariables.ancho=655;
        ScenarioCentral.secondaryWindows.initStyle(StageStyle.UNDECORATED);
    }

    @FXML
    public void verifyUserCert() throws SQLException, IOException {

        String usuario = nameUser.getText();
        String pass = (String) txtPassword.getText();

        /*
         SE VERIFICA LA EXISTENCIA DEL PE EN LA BASE DE DATOS, SI EL PE EXISTE
         SE CREA UN OBJETO USUARIO QUE GUARDARA LA INFORMACION DEL USUARIO
         RELACIONADA A ESE DISPOSITIVO PE.
         CASO CONTRARIO MOSTRARA UN ERROR.
         */
        conexion.buscarDato("*", "sistema_usuario", "nombre_usuario", usuario, true);
        final Boolean state = conexion.estado_busqueda;
        //VERIFICACION DE USUARIO EN EL SISTEMA
        
//        (!(usuario.isEmpty() || pass.isEmpty()))
//                && (!(conexion.getDato("nombre_usuario").equals(usuario))
//                || !(conexion.getDato("password").equals(pass)))
        
        if ( (!(usuario.isEmpty() || pass.isEmpty())) && (!(state) || !(conexion.getDato("nombre_usuario").equals(usuario)) || !(conexion.getDato("password").equals(pass)))  ) {

            ErroresMensajes.texto = "nombre de usuario o contraseña incorrecta.";
            Parent root = FXMLLoader.load(getClass().getResource("/com/error/"
                    + "template/interfaz/alertErrorMaker.fxml"));
            
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();

        }else if(usuario.isEmpty() || pass.isEmpty()){
            ErroresMensajes.texto = "Hay datos incompletos, todos los campos son obligatorio.";
            Parent root = FXMLLoader.load(getClass().getResource("/com/error/"
                    + "template/interfaz/alertErrorMaker.fxml"));
            
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();
        }else{//SI EL USUARIO EXISTE SE CONCEDE ACCESO AL SISTEMA
            Parent root = FXMLLoader.load(getClass().getResource("/com/login/app/interfaz/LoginUser.fxml"));
            paneContainer.getChildren().removeAll();
            paneContainer.getChildren().setAll(root);
        }

    }
}
