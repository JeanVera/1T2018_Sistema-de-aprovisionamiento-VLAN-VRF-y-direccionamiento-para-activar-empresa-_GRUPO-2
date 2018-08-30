/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login.app.logica;

import AlertClass.AlertMaker;
import Central.ScenarioCentral;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import AlertClass.*;
import Central.publicVariables;
import static Central.publicVariables.conexion;
import com.clases.secundarias.Usuario;
import java.sql.SQLException;
import static javafx.application.Platform.exit;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jimmy G
 */


public class LoginUserController implements Initializable {
    
    /**
     * Initializes the controller class.
     * @param event
     * @param url
     * @param rb
     * @throws java.io.IOException
     * 
     */
    @FXML
    private JFXTextField namePE;
    @FXML
    private JFXTextField nameUser;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    Label nombreAplicacion;
    @FXML
    Label texto1;
    @FXML
    Label texto2;
    @FXML
    Pane paneContainer;
    
    double x=0;
    double y=0;
    Node node;
    Stage stage;
    
    @FXML
    public void dragged(MouseEvent event){
        node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
        
    }
    
    @FXML
    public void pressed(MouseEvent event){
        x = event.getSceneX();
        y=event.getSceneY();
        System.out.println("HE PRESIONADO");
        stage.setOpacity(0.6);
    }
    
    @FXML
    public void release(MouseEvent event){
        stage.setOpacity(1);
    }
    @FXML
    private void verifyTextLogin() throws IOException, SQLException{
        
        //OBJETO QUE GUARDA LA INFORMACION DEL USUARIO
        Usuario usuario = null;
        
        //ESTE BLOQUE VERIFICA EL NOMBRE DEL PE
        //TENER PRESENTE QUE LA VERIFICACION ES CON LA INFORMACION
        //ALMACENADA EN LA BASE DE DATOS
        String namePE_ = ((TextField) namePE).getText();
        String nameUser_ = ((TextField) nameUser).getText();
        String pass_ = ((TextField) txtPassword).getText();
        
        /*
        SE VERIFICA LA EXISTENCIA DEL PE EN LA BASE DE DATOS, SI EL PE EXISTE
        SE CREA UN OBJETO USUARIO QUE GUARDARA LA INFORMACION DEL USUARIO
        RELACIONADA A ESE DISPOSITIVO PE.
        CASO CONTRARIO MOSTRARA UN ERROR.
        */
        conexion.buscarDato("*","providers_edges","nombre_pe",namePE_,true);
        final Boolean state = conexion.estado_busqueda;
        //VERIFICACION DEL DISPOSITIVO PE
        if(!(state)&&(!(namePE_.isEmpty()))){//!(state)&&(!(namePE_.isEmpty()))
            ErroresMensajes.texto="nombre de pe incorrecto.";
            Parent root=FXMLLoader.load(getClass().getResource("/com/error"
                    + "/template/interfaz/alertErrorMaker.fxml"));
            
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();
 
        }else{//SE CREA EL USUARIO Y SE EXTRAE LA INFORMACION RESPECTIVA
            usuario = new Usuario();
            //SE EXTRAE LA CIUDAD ASOCIADA AL PE IGRESADO
            
            //SE ASIGNA LA INFOMARCION AL USUARIO
            conexion.buscarDato("*","usuario","","",false);
            usuario.setPE(namePE_);
            usuario.setUser(conexion.getDato("usuario"));
            usuario.setPass(conexion.getDato("password"));
            usuario.setIP(conexion.getDato("ip_address"));
                            
            
        }
        
//        System.out.println(conexion.getDato("usuario")+"\n"+conexion.getDato("password")+"\n"+conexion.getDato("ip_address"));
//            
//            System.out.println("PE correcto");
//            try{
//                Thread.sleep(30000);
//            }catch(Exception e){
//                
//            }
        
        if((!(namePE_.isEmpty()||pass_.isEmpty()))
           &&(!(usuario.getDato("user").equals(nameUser_))
           ||!(usuario.getDato("pass").equals(pass_)))){
            
            ErroresMensajes.texto="nombre de usuario o contraseña incorrecta.";
            Parent root=FXMLLoader.load(getClass().getResource("/com/error/"
                    + "template/interfaz/alertErrorMaker.fxml"));
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();
            
        }else if(namePE_.isEmpty()||nameUser_.isEmpty()||pass_.isEmpty()){//namePE_.isEmpty()||nameUser_.isEmpty()||pass_.isEmpty()
            
            ErroresMensajes.texto="todos los campos son obligatorios.";
            Parent root=FXMLLoader.load(getClass().getResource("/com/error/"
                    + "template/interfaz/alertErrorMaker.fxml"));
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();
            
        }else if((!(namePE_.equals(usuario.getDato("name_pe"))))
               ||(!(nameUser_.equals(usuario.getDato("user"))))||
                 (!(pass_.equals(usuario.getDato("pass"))))){
            

            
            ErroresMensajes.texto="los datos ingresados no constan en la base de"
                    + "datos.";
            Parent root=FXMLLoader.load(getClass().getResource("/com/error/"
                    + "template/interfaz/alertErrorMaker.fxml"));
            Scene escena = new Scene(root);

            escena.setFill(Color.TRANSPARENT);

            Central.ScenarioCentral.secondaryWindows.setScene(escena);
            Central.ScenarioCentral.secondaryWindows.show();
            
        }else{
            /*
            SI LA INFORMACION INGRESADA ES CORRECTA, SE ACCEDE VIA TELNET
            AL DISPOSITIVO, Y SE MUESTRAN LAS OPCIONES A REALIZAR POR PARTE
            DEL USUARIO. PRIMERO SE REALIZA LA CONEXION VIA TELNET, SE NECESITA
            LA DIRECCION IP DEL PE ASI COMO EL USUARIO Y LA CONTRASEÑA
            */
            
            //SE CREA UN OBJETO TELNETCONNECTOR
//            TelnetConnector telnet = new TelnetConnector();
            //SE LLAMA AL METODO QUE REALIZA LA CONEXION
//            telnet.telnetConnectTo(usuario.getDato("ip"));
            
            //SE VERIFICA LA CONEXION
            if(true){//telnet.getStatusConnection()
                System.out.println("estoy aqui");
//                //SE AUTENTICA AL USUARIO CON EL NAME_USER Y PASS
                //telnet.authUser(usuario.getDato("user"),usuario.getDato("pass"));
//                telnet.sendCommand("#", "conf t", false);
//                telnet.sendCommand("#", "hostname ESPOL", false);
//                
//                //SE MUESTRAN LAS OPCIONES AL USUARIO
                ScenarioCentral.centerWindow.hide();
                Parent root=FXMLLoader.load(getClass().getResource("/com/opciones/interfaz/opciones.fxml"));
                Scene scene=new Scene(root);
            
                ScenarioCentral.centerWindow.setScene(scene);
                ScenarioCentral.centerWindow.show();
            }else{
                System.out.println("Error al conectar via telnet."
                        + "Verifique que tenga activo acceso telnet al"
                        + "dispositivo");
                Parent root=FXMLLoader.load(getClass().getResource("/com/error/"
                    + "template/interfaz/alertErrorMaker.fxml"));
                AlertMaker.showMaterialDialog(rootPane, root, mainContainer);
            }
            
        }
        
    }
    
    
    private Boolean verifyNamePE(String _pe){
        Boolean respuesta = false;
        
        return respuesta;
    }
    
    /*
    EN ESTE METODO SE INICIALIZA LA PANTALLA PRINCIPAL
    AQUI SE HA DECIDIDO CONECTAR LA BASE DE DATOS
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            //ESTA LINEA LE QUITA EL BORDE A LAS VENTANAS
            ScenarioCentral.centerWindow.setResizable(false);
    }
    
    @FXML
    public void closeApp(){
        exit();
    }
}