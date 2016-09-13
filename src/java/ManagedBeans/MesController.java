package ManagedBeans;

import Entidades.Pago.Mes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "mesController")
@SessionScoped
public class MesController implements Serializable {

    private List<Mes> items = null;
    private Mes selected;

    public MesController() {
    }

    public Mes getSelected() {
        return selected;
    }

    public void setSelected(Mes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Mes prepareCreate() {
        initializeEmbeddableKey();
        return selected;
    }

    public List<Mes> getItems() {
        if (items == null) {
            items = Arrays.asList(Mes.values());
        }
        return items;
    }

 

     



}
