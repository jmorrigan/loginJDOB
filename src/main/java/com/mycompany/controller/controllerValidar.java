/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.usuarios.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author juan
 */
@Named(value = "controllerValidar")
@ViewScoped
public class controllerValidar implements Serializable {

    public void validarUser(){
        Usuario user;
        try{
            user = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            
            if (user == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../block.xhtml");
            }
        }catch(Exception ex){
            
        }
    }
    
    
    
}
