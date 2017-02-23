package ManagedBeans;

import Entidades.Caja.Caja;
import Entidades.Caja.Egreso;
import Entidades.Caja.Ingreso;
import Entidades.Caja.MovimientoCaja;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.MovimientoCajaFacade;
import Facades.TipoDeEgresoFacade;
import Facades.TipoDeIngresoFacade;
import RN.CajaRNLocal;
import RN.MovimientoCajaRNLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.joda.time.DateTimeComparator;
import org.primefaces.component.datatable.DataTable;

@Named("movimientoCajaController")
@SessionScoped
public class MovimientoCajaController implements Serializable {

    @EJB
    private Facades.MovimientoCajaFacade ejbFacade;
    @EJB
    private CajaRNLocal cajaRNLocal;
    private List<MovimientoCaja> items = null;
    private MovimientoCaja selected;
    @EJB
    private Facades.TipoDeEgresoFacade tipoDeEgresoFacade;
    @EJB
    private Facades.TipoDeIngresoFacade tipoDeIngresoFacade;
    @Inject
    private UsuarioLogerBean usuarioLogerBean;
    @Inject
    private CajaController cajaController;
    BigDecimal calculo;
    BigDecimal calculoParcial;
    DataTable dataTable;

    public MovimientoCajaController() {
    }

    public BigDecimal getCalculoParcial() {
        return calculoParcial;
    }

    public void setCalculoParcial(BigDecimal calculoParcial) {
        this.calculoParcial = calculoParcial;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public CajaController getCajaController() {
        return cajaController;
    }

    public void setCajaController(CajaController cajaController) {
        this.cajaController = cajaController;
    }

    public MovimientoCaja getSelected() {
        return selected;
    }

    public MovimientoCajaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(MovimientoCajaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public CajaRNLocal getCajaRNLocal() {
        return cajaRNLocal;
    }

    public void setCajaRNLocal(CajaRNLocal cajaRNLocal) {
        this.cajaRNLocal = cajaRNLocal;
    }

    public BigDecimal getCalculo() {
        return calculo;
    }

    public void setCalculo(BigDecimal calculo) {
        this.calculo = calculo;
    }

    public TipoDeEgresoFacade getTipoDeEgresoFacade() {
        return tipoDeEgresoFacade;
    }

    public void setTipoDeEgresoFacade(TipoDeEgresoFacade tipoDeEgresoFacade) {
        this.tipoDeEgresoFacade = tipoDeEgresoFacade;
    }

    public TipoDeIngresoFacade getTipoDeIngresoFacade() {
        return tipoDeIngresoFacade;
    }

    public void setTipoDeIngresoFacade(TipoDeIngresoFacade tipoDeIngresoFacade) {
        this.tipoDeIngresoFacade = tipoDeIngresoFacade;
    }

    public UsuarioLogerBean getUsuarioLogerBean() {
        return usuarioLogerBean;
    }

    public void setUsuarioLogerBean(UsuarioLogerBean usuarioLogerBean) {
        this.usuarioLogerBean = usuarioLogerBean;
    }

    public void setSelected(MovimientoCaja selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MovimientoCajaFacade getFacade() {
        return ejbFacade;
    }

    public MovimientoCaja prepareCreate() {
        selected = new MovimientoCaja();
        selected.setFecha(new Date());
        initializeEmbeddableKey();
        return selected;
    }

    public MovimientoCaja prepareCreateI() {
        selected = new Ingreso();
        selected.setFecha(new Date());
        initializeEmbeddableKey();
        return selected;
    }

    public MovimientoCaja prepareCreateE() {
        selected = new Egreso();
        selected.setFecha(new Date());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (cajaController.hayCajaAbierta()) {
            selected.setUsuario(usuarioLogerBean.getUsuario());
            selected.setFecha(new Date());
            getFacade().create(selected);
            //persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("MovimientoCajaCreated"));
            Caja cajaAbierta = cajaRNLocal.getCajaAbierta();
            cajaAbierta.getMovimientosCaja().add(selected);
            if (selected.getMedico() != null) {
                selected.getMedico().getMovimientoCajas().add(selected);
            }
            cajaRNLocal.edit(cajaAbierta);
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
            JsfUtil.addSuccessMessage("movimiento de Caja Creado!");
        } else {
            JsfUtil.addErrorMessage("La caja se encuentra cerrada no puede realizar movimientos!");
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("MovimientoCajaUpdated"));
    }

    public void destroy() {

        Caja cajaAbierta = cajaRNLocal.getCajaAbierta();
        cajaAbierta.getMovimientosCaja().remove(selected);
        cajaRNLocal.edit(cajaAbierta);
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("MovimientoCajaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MovimientoCaja> getItems() {
        if (items == null) {
            try {
                items = cajaRNLocal.getCajaAbierta().getMovimientosCaja();
            } catch (Exception e) {
            }
        }
        return items;
    }

    public void calculateTotal(Object valueOfThisSorting) {
        calculo = BigDecimal.ZERO;
        try {

            for (MovimientoCaja i : items) {
                switch (valueOfThisSorting.getClass().getSimpleName()) {
                    case "Date":
                        if (DateTimeComparator.getDateOnlyInstance().compare(i.getFechaOperacion(), valueOfThisSorting) == 0) {
                            switch (i.getClase()) {
                                case "Ingreso":
                                    calculo = calculo.add(i.getImporte());
                                    break;
                                case "Egreso":
                                    calculo = calculo.subtract(i.getImporte());
                                    break;
                            }
                        }
                        break;
                    case "String":
                        if (i.getClass().getSimpleName().equals(valueOfThisSorting)) {
                            switch (i.getClase()) {
                                case "Ingreso":
                                    calculo = calculo.add(i.getImporte());
                                    break;
                                case "Egreso":
                                    calculo = calculo.subtract(i.getImporte());
                                    break;
                            }
                        }
                        break;
                }
            }
        } catch (Exception e) {
        }

    }

    public BigDecimal getValueTotal() {
        return calculo;
    }

    public BigDecimal getTotalGeneral() {
        calculo = BigDecimal.ZERO;
        try {
            calculo = cajaRNLocal.getCajaAbierta().getCajaInicial();
        } catch (Exception e) {
        }
        try {
            for (MovimientoCaja i : items) {
                switch (i.getClase()) {
                    case "Ingreso":
                        calculo = calculo.add(i.getImporte());
                        break;
                    case "Egreso":
                        calculo = calculo.subtract(i.getImporte());
                        break;
                }
            }
        } catch (Exception e) {
        }
        return calculo;
    }

    public BigDecimal getCalculoParcial(MovimientoCaja i) {
        if (dataTable.getRowIndex() == 0) {
            calculoParcial = cajaRNLocal.getCajaAbierta().getCajaInicial();
        }
        switch (i.getClase()) {
            case "Ingreso":
                calculoParcial = calculoParcial.add(i.getImporte());
                break;
            case "Egreso":
                calculoParcial = calculoParcial.subtract(i.getImporte());
                break;
        }

        return calculoParcial;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MovimientoCaja getMovimientoCaja(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<MovimientoCaja> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public <Tipo> List<Tipo> getItemsAvailableSelectOne() {
        List<Tipo> list = new ArrayList<>();
        switch (selected.getClase()) {
            case "Ingreso":
                list.addAll((Collection<? extends Tipo>) tipoDeIngresoFacade.findAll());
                break;
            case "Egreso":
                list.addAll((Collection<? extends Tipo>) tipoDeEgresoFacade.findAll());
                break;
        }
        return list;
    }

    @FacesConverter(forClass = MovimientoCaja.class)
    public static class MovimientoCajaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovimientoCajaController controller = (MovimientoCajaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movimientoCajaController");
            return controller.getMovimientoCaja(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MovimientoCaja) {
                MovimientoCaja o = (MovimientoCaja) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovimientoCaja.class.getName()});
                return null;
            }
        }

    }

}
