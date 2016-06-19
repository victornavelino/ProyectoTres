/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import entidades.persona.DocumentoIdentidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nago
 */
@Stateless
public class DocumentoIdentidadFacade extends AbstractFacade<DocumentoIdentidad> {
    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoIdentidadFacade() {
        super(DocumentoIdentidad.class);
    }
    
}
