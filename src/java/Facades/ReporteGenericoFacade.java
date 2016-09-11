/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Reporte.Parametro;
import Entidades.Reporte.ReporteGenerico;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author diego
 */
@Stateless
public class ReporteGenericoFacade extends AbstractFacade<ReporteGenerico> implements ReporteGenericoFacadeLocal {
    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteGenericoFacade() {
        super(ReporteGenerico.class);
    }
    
    @Override
    public List<ReporteGenerico> findLikeByNombre(String cadena) throws Exception {
        Query query = null;

            
            if(cadena == null || cadena.isEmpty()){
                query = em.createNamedQuery("ReporteGenerico.findAll");
            }else{
                query = em.createNamedQuery("ReporteGenerico.findLikeByNombre");
                query.setParameter("cadena", "%" + cadena + "%" );
            }//fin if-else
            
            return query.getResultList();
    }

    @Override
    public Boolean findByNombre(String nombre, Long id) throws Exception {
        try {
            Query q = null;
            if(id == null){
                q = em.createNamedQuery("ReporteGenerico.findByNombre");
            }else{
                q = em.createNamedQuery("ReporteGenerico.findByNombreID");
                q.setParameter("id", id);
            }
            q.setParameter("nombre", nombre);
            q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;

        }
    }

    @Override
    public List<Object[]> findLstConsultaReporteGenerico(Long idReporte, List<Object> lstValorParametro) throws Exception {
        Query query = null;
        
        query = em.createNamedQuery("ReporteGenerico.findById");
        query.setParameter("idReporte", idReporte);
        ReporteGenerico rg = (ReporteGenerico)query.getSingleResult();
        
        
        query = em.createQuery(rg.getConsulta());
        //validar los parametros
            
            int pos = 0;
            for(Parametro para : rg.getLstParametros()){
                
                switch(para.getTipoParametro().getName()){
                    case "ENTERO":
                        
                        if(!String.valueOf(lstValorParametro.get(pos)).matches("[0-9]+")){
                            throw new Exception(" EL valor para el parametro " + para.getNombre() + " debe ser un entero");
                        }//fin if
                        
                        query.setParameter(para.getNombre(), Integer.parseInt((String)lstValorParametro.get(pos)));
                        break;
                        
                    case "LONG":
                        
                        if(!String.valueOf(lstValorParametro.get(pos)).matches("[0-9]+")){
                            throw new Exception(" EL valor para el parametro " + para.getNombre() + " debe ser un entero");
                        }//fin if
                        
                        query.setParameter(para.getNombre(), Long.parseLong((String)lstValorParametro.get(pos)));
                        break;
                        
                    case "DECIMAL":
                        if(!String.valueOf(lstValorParametro.get(pos)).matches("[0-9]+,[0-9]+")){
                            throw new Exception(" EL valor para el parametro " + para.getNombre() + " debe ser un numero decimal");
                        }//fin if
                        
                        query.setParameter(para.getNombre(), Double.parseDouble((String)lstValorParametro.get(pos)));
                        
                        break;
                    case "TEXTO":
                        
                        
                        break;
                    case "FECHA":
                        try{
                            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                            System.out.println("Fecha: " + formatoDeFecha.parse((String)lstValorParametro.get(pos)));
                            query.setParameter(para.getNombre(), formatoDeFecha.parse((String)lstValorParametro.get(pos)));
                        }catch(Exception e){
                            throw new Exception(" EL valor para el parametro " + para.getNombre() + " debe tener la forma dd/MM/YYYY");
                        }
                        break;
                        
                    
                }//fin switch
                
                
                
            }//fin for
            
        

        return query.getResultList();
     
    }

    @Override
    public Boolean validarConsultaReporteGenerico(ReporteGenerico reporteGenerico) throws Exception {
        try {
            Query query = em.createQuery(reporteGenerico.getConsulta());
            
            int pos = 0;
            for(Parametro para : reporteGenerico.getLstParametros()){
                
                switch(para.getTipoParametro().getName()){
                    case "ENTERO":                       
                        query.setParameter(para.getNombre(), 0);
                        break;
                    case "LONG":
                        System.out.println("Entro long: " + para.getNombre());
                        query.setParameter(para.getNombre(), 1L);
                        break;
                    case "DECIMAL":
                        query.setParameter(para.getNombre(), 1.2);
                        break;
                    case "TEXTO":
                        query.setParameter(para.getNombre(), "a");
                        
                        break;
                    case "FECHA":
                       query.setParameter(para.getNombre(), new Date());
                        break;                       
                    
                }//fin switch
                
                
                
            }//fin for
            
            query.getResultList();
        } catch (Exception e) {
            return false;
        }
        return true;
        
    }

}
