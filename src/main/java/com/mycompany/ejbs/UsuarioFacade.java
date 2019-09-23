/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejbs;

import com.mycompany.usuarios.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "loginPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario iniciarSesion(Usuario us) {
        Usuario usuario = null;
        String consulta;
        try{
            consulta = "SELECT * FROM linea u WHERE u.usuario = ?1 and u.clave = ?2 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsuario());
            query.setParameter(2, us.getClave());
            
            List<Usuario> lista = query.getResultList();
            
            if (!lista.isEmpty()){
                usuario = lista.get(0);
            }
        }catch(Exception ex){
            throw ex;
        }
        return usuario;
    
    }
    
}
