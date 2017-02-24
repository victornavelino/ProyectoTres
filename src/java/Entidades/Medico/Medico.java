/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Medico;

import Entidades.Base.Archivo;
import Entidades.Caja.MovimientoCaja;
import Entidades.Pago.Pago;
import Entidades.Pago.PlanPago;
import Entidades.Persona.Persona;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author nago
 */
@NamedQueries({
    @NamedQuery(name = "Medico.buscarMedicosEspecialidad", query = "SELECT m FROM Medico m ,m.especialidades esp WHERE esp=:especialidad"),
    @NamedQuery(name = "Medico.buscarXApellido", query = "SELECT m FROM Medico m WHERE m.persona.apellido LIKE :apellido"),
    @NamedQuery(name = "Medico.buscarXMatricula", query = "SELECT m FROM Medico m WHERE m.matriculaProfesional LIKE :matriculaProfesional"),
    @NamedQuery(name = "Medico.buscarUltimaMatricula", query = "SELECT max(m.matriculaProfesional) FROM Medico m "),
    @NamedQuery(name = "Medico.buscarTodosActivos", query = "SELECT m FROM Medico m WHERE m.tipoSocio.descripcion ='ACTIVO'"),
    @NamedQuery(name = "Medico.buscarMedicosDeudores", query = "SELECT med FROM Medico med WHERE med.tipoSocio.id = 1 EXCEPT SELECT m FROM Medico m where m.id NOT IN (SELECT p.medico.id FROM Pago p where p.anio>=:anio AND p.mes>=:mes)"),
    @NamedQuery(name = "Medico.buscarMedicosDeudoresEntre", query = "SELECT med FROM Medico med WHERE med.tipoSocio.id = 1 EXCEPT SELECT m FROM Medico m where m.id NOT IN (SELECT p.medico.id FROM Pago p where p.anio>=:anio AND p.mes>=:mes) EXCEPT SELECT m FROM Medico m where m.id NOT IN (SELECT p.medico.id FROM Pago p where p.anio>=:anio2 AND p.mes>=:mes2) ")
})

@Entity
@Table(name = "medico")
public class Medico implements Serializable {

    @OneToMany(mappedBy = "medico")
    private List<MovimientoCaja> movimientoCajas;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Persona persona;
    private int matriculaProfesional;
    @OneToOne
    private TipoMedico tipoSocio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInscripcion;
    private String titulo;
    @OneToMany
    private List<Especializacion> especializaciones;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRecibido;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaTitulo;
    @OneToOne
    private UnidadFormadora unidadFormadora;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaBaja;
    private String motivoBaja;
    private String resolucionBaja;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaResolucionBaja;
    @OneToOne
    private Organismo organismo;
    private Integer libro;
    private Integer folio;
    private String nroRegistro;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Archivo archivo;
    @OneToMany(mappedBy = "medico")
    private List<PlanPago> planPagos;
    @OneToMany(mappedBy = "medico")
    private List<Pago> pagos;
    @Lob
    private String observaciones;

    public List<MovimientoCaja> getMovimientoCajas() {
        return movimientoCajas;
    }

    public void setMovimientoCajas(List<MovimientoCaja> movimientoCajas) {
        this.movimientoCajas = movimientoCajas;
    }

    public String getResolucionBaja() {
        return resolucionBaja;
    }

    public void setResolucionBaja(String resolucionBaja) {
        this.resolucionBaja = resolucionBaja;
    }

    public Date getFechaResolucionBaja() {
        return fechaResolucionBaja;
    }

    public void setFechaResolucionBaja(Date fechaResolucionBaja) {
        this.fechaResolucionBaja = fechaResolucionBaja;
    }

    public String getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(String nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public List<PlanPago> getPlanPagos() {
        return planPagos;
    }

    public void setPlanPagos(List<PlanPago> planPagos) {
        this.planPagos = planPagos;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getMatriculaProfesional() {
        return matriculaProfesional;
    }

    public void setMatriculaProfesional(int matriculaProfesional) {
        this.matriculaProfesional = matriculaProfesional;
    }

    public TipoMedico getTipoSocio() {
        return tipoSocio;
    }

    public void setTipoSocio(TipoMedico tipoSocio) {
        this.tipoSocio = tipoSocio;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Especializacion> getEspecializaciones() {
        return especializaciones;
    }

    public void setEspecializaciones(List<Especializacion> especializaciones) {
        this.especializaciones = especializaciones;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Date getFechaTitulo() {
        return fechaTitulo;
    }

    public void setFechaTitulo(Date fechaTitulo) {
        this.fechaTitulo = fechaTitulo;
    }

    public UnidadFormadora getUnidadFormadora() {
        return unidadFormadora;
    }

    public void setUnidadFormadora(UnidadFormadora unidadFormadora) {
        this.unidadFormadora = unidadFormadora;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public Integer getLibro() {
        return libro;
    }

    public void setLibro(Integer libro) {
        this.libro = libro;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public List<MovimientoCaja> getAranceles() {
        List<MovimientoCaja> cajas = getMovimientoCajas();
        try {
            for (MovimientoCaja m : cajas) {
                if (m.getTipoMovimiento().equalsIgnoreCase("MANTENIMIENTO")) {
                    cajas.remove(m);
                }
            }
        } catch (Exception e) {
        }
        return cajas;
    }

    @Override
    public String toString() {
        try {
            return matriculaProfesional + " - " + persona.toString();
        } catch (Exception e) {
            return "";
        }
    }

}
