/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//NUMERO DE PANTALLA 26
package Entidades.Reporte;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "reporte_generico")
@NamedQueries({
    
    @NamedQuery(name="ReporteGenerico.findById", query="SELECT tp FROM ReporteGenerico tp WHERE tp.id = :idReporte"),
    
    @NamedQuery(name="ReporteGenerico.findAll", query="SELECT tp FROM ReporteGenerico tp ORDER BY tp.nombre"),
    
    @NamedQuery(name="ReporteGenerico.findLikeByNombre", query="SELECT tp FROM ReporteGenerico tp WHERE LOWER(TRIM(tp.nombre)) LIKE :cadena "
        + "ORDER BY tp.nombre"),
    
    @NamedQuery(name="ReporteGenerico.findByNombre", 
            query="SELECT tp FROM ReporteGenerico tp WHERE LOWER(TRIM(tp.nombre)) =:nombre"),
    
    @NamedQuery(name="ReporteGenerico.findByNombreID", 
            query="SELECT tp FROM ReporteGenerico tp WHERE "
                    + "LOWER(TRIM(tp.nombre)) =:nombre AND tp.id <> :id")
})
public class ReporteGenerico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    @Lob
    private String consulta;
    
    @Lob
    private String columnas;
    
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parametro> lstParametros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Parametro> getLstParametros() {
        return lstParametros;
    }

    public void setLstParametros(List<Parametro> lstParametros) {
        this.lstParametros = lstParametros;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColumnas() {
        return columnas;
    }

    public void setColumnas(String columnas) {
        this.columnas = columnas;
    }
    
    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteGenerico)) {
            return false;
        }
        ReporteGenerico other = (ReporteGenerico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.usuario.ReporteGenerico[ id=" + id + " ]";
    }
    
}
