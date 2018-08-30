/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases.secundarias;

/**
 *
 * @author Prueba
 */
public class VLAN {
    
    int number;
    String name_empresa;
    String ciudad;
    
    public void setNumberVLAN(int num){
        this.number=num;
    }
    
    public void setNameEmpresa(String name){
        this.name_empresa=name;
    }
    
    public void setNameCiudad(String ciudad){
        this.ciudad=ciudad;
    }
    
    public int getNumberVLAN(){
        return number;
    }
    
    public String getNameEmpresa(){
        return name_empresa;
    }
    
    public String getNameCiudad(){
        return this.ciudad;
    }
    
}
