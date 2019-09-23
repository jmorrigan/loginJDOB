/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.ejbs.UsuarioFacadeLocal;
import com.mycompany.usuarios.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author juan
 */

@Named (value = "controllerLogin")
@SessionScoped
public class controllerLogin implements Serializable{
        
        @EJB
        private UsuarioFacadeLocal EUsuario;
        private Usuario user;
        
        @PostConstruct
        public void init(){
            user = new Usuario();
        }
   
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public String login(){
        Usuario us;
        String redirect = null;
        
        try{
            us = EUsuario.iniciarSesion(user);
            if (us != null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", us);
                redirect= "/reservado/principal";
            }else{
                  FacesMessage msg = new FacesMessage("INCORRECTO", "Credenciales incorrectas");
                  FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "lo sentimos", "No hay conexion"));
        }
        
        return redirect;
        
    }
}
