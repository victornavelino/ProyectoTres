package ManagedBeans;

import Entidades.Pago.CuotaPlanPago;
import Entidades.Pago.PlanPago;
import Facades.PlanPagoFacade;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("planPagoController")
@SessionScoped
public class PlanPagoController implements Serializable {

    @EJB
    private Facades.PlanPagoFacade ejbFacade;
    private List<PlanPago> items = null;
    private PlanPago selected;

    public PlanPagoController() {
    }

    public PlanPago getSelected() {
        return selected;
    }

    public void setSelected(PlanPago selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PlanPagoFacade getFacade() {
        return ejbFacade;
    }

    public PlanPago prepareCreate() {
        selected = new PlanPago();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePlanPagos").getString("PlanPagoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePlanPagos").getString("PlanPagoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePlanPagos").getString("PlanPagoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PlanPago> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months); //minus number would decrement the months
        return cal.getTime();
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (selected.getTipoPlanPago() != null) {
                    int cantidadCuotas = selected.getTipoPlanPago().getCuotas();
                    BigDecimal interes = selected.getTipoPlanPago().getInteres().divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
                    Date vencimiento = selected.getFechaVencimiento();

                    List<CuotaPlanPago> cuotas = new ArrayList<>();
                    BigDecimal importe = selected.getImporte();
                    BigDecimal importeInteres = importe.add(importe.multiply(interes));
                    BigDecimal importeParcial = importeInteres.divide(new BigDecimal(cantidadCuotas), 2, BigDecimal.ROUND_HALF_UP);

                    for (int i = 1; i <= selected.getTipoPlanPago().getCuotas(); i++) {
                        CuotaPlanPago cpp = new CuotaPlanPago();
                        cpp.setCuota(i);
                        cpp.setImporte(importeParcial);
                        cpp.setFechaVencimiento(vencimiento);
                        vencimiento = addMonth(vencimiento, 1);
                        cuotas.add(cpp);
                    }
                    selected.setCuotas(cuotas);
                }
            } catch (Exception e) {
                JsfUtil.addErrorMessage("No se pudieron crear todas las cuotas del plan de pago, por favor revise los datos");
            }
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePlanPagos").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePlanPagos").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PlanPago getPlanPago(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<PlanPago> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PlanPago> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PlanPago.class)
    public static class PlanPagoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlanPagoController controller = (PlanPagoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "planPagoController");
            return controller.getPlanPago(getKey(value));
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
            if (object instanceof PlanPago) {
                PlanPago o = (PlanPago) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PlanPago.class.getName()});
                return null;
            }
        }

    }

}
