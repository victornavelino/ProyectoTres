/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Usuario.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nago
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findUserByNombreContrasena(String nombre, String contrasena) {
        try {
            Query q = em.createNamedQuery("Usuario.findUserByNombreContrasena");
            q.setParameter("usuario", nombre);
            q.setParameter("contrasena", contrasena);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
