package edu.ort.dcomp.ute.controller;

import edu.ort.dcomp.ute.modelo.Factura;
import edu.ort.dcomp.ute.controller.util.JsfUtil;
import edu.ort.dcomp.ute.controller.util.PaginationHelper;
import edu.ort.dcomp.ute.managers.FacturaFacade;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean (name="facturaController")
@SessionScoped
public class FacturaController {

  private Factura current;
  private DataModel items = null;
  @EJB private edu.ort.dcomp.ute.managers.FacturaFacade ejbFactura;
  @EJB private edu.ort.dcomp.ute.managers.CuentaFacade ejbCuenta;
  private PaginationHelper pagination;
  private int selectedItemIndex;

  public FacturaController() {
  }

  public Factura.Estado[] getEstados() {
    return Factura.Estado.values();
  }
  public Factura getSelected() {
    if (current == null) {
      current = new Factura();
      selectedItemIndex = -1;
    }
    return current;
  }

  private FacturaFacade getFacade() {
    return ejbFactura;
  }

  public PaginationHelper getPagination() {
    if (pagination == null) {
      pagination = new PaginationHelper(10) {

        @Override
        public int getItemsCount() {
          return getFacade().count();
        }

        @Override
        public DataModel createPageDataModel() {
          return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem()+getPageSize()}));
        }
      };
    }
    return pagination;
  }

  public String prepareList() {
    recreateModel();
    return "List";
  }

  public String prepareView() {
    current = (Factura)getItems().getRowData();
    selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    return "View";
  }

  public String prepareCreate() {
    current = new Factura();
    selectedItemIndex = -1;
    return "Create";
  }

  public String create() {
    try {
      getFacade().create(current);
      if (Factura.Estado.PENDIENTE.equals(current.getEstado())) {
        ejbCuenta.cargarFactura(current);
      }
      JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaCreated"));
      return prepareCreate();
    } catch (Exception e) {
      JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      return null;
    }
  }

  public String prepareEdit() {
    current = (Factura)getItems().getRowData();
    selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    return "Edit";
  }

  public String update() {
    try {
      if (Factura.Estado.ANULADA.equals(current.getEstado())) {
        getFacade().edit(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaUpdated"));
        ejbCuenta.quitarFactura(current);
      } else if (Factura.Estado.PAGA.equals(current.getEstado())) {
        getFacade().edit(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaUpdated"));
        ejbCuenta.pagarFactura(current);
      }
      return "View";
    } catch (Exception e) {
      JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      return null;
    }
  }

  public String destroy() {
    current = (Factura)getItems().getRowData();
    selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    performDestroy();
    recreateModel();
    return "List";
  }

  public String destroyAndView() {
    performDestroy();
    recreateModel();
    updateCurrentItem();
    if (selectedItemIndex >= 0) {
      return "View";
    } else {
      // all items were removed - go back to list
      recreateModel();
      return "List";
    }
  }

  private void performDestroy() {
    try {
      getFacade().remove(current);
      JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaDeleted"));
    } catch (Exception e) {
      JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
    }
  }

  private void updateCurrentItem() {
    int count = getFacade().count();
    if (selectedItemIndex >= count) {
      // selected index cannot be bigger than number of items:
      selectedItemIndex = count-1;
      // go to previous page if last page disappeared:
      if (pagination.getPageFirstItem() >= count) {
        pagination.previousPage();
      }
    }
    if (selectedItemIndex >= 0) {
      current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex+1}).get(0);
    }
  }

  public DataModel getItems() {
    if (items == null) {
      items = getPagination().createPageDataModel();
    }
    return items;
  }

  private void recreateModel() {
    items = null;
  }

  public String next() {
    getPagination().nextPage();
    recreateModel();
    return "List";
  }

  public String previous() {
    getPagination().previousPage();
    recreateModel();
    return "List";
  }

  public SelectItem[] getItemsAvailableSelectMany() {
    return JsfUtil.getSelectItems(ejbFactura.findAll(), false);
  }

  public SelectItem[] getItemsAvailableSelectOne() {
    return JsfUtil.getSelectItems(ejbFactura.findAll(), true);
  }

  @FacesConverter(forClass=Factura.class)
  public static class FacturaControllerConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
      if (value == null || value.length() == 0) {
        return null;
      }
      FacturaController controller = (FacturaController)facesContext.getApplication().getELResolver().
          getValue(facesContext.getELContext(), null, "facturaController");
      return controller.ejbFactura.find(getKey(value));
    }

    java.lang.Long getKey(String value) {
      java.lang.Long key;
      key = Long.valueOf(value);
      return key;
    }

    String getStringKey(java.lang.Long value) {
      StringBuffer sb = new StringBuffer();
      sb.append(value);
      return sb.toString();
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
      if (object == null) {
        return null;
      }
      if (object instanceof Factura) {
        Factura o = (Factura) object;
        return getStringKey(o.getId());
      } else {
        throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+FacturaController.class.getName());
      }
    }

  }

}
