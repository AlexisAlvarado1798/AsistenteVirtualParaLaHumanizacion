package asistentevirtual.jsf.controladores;

import asistentevirtual.jpa.entidades.Usuario;
import asistentevirtual.jsf.controladores.util.JsfUtil;
import asistentevirtual.jsf.controladores.util.PaginationHelper;
import asistentevirtual.ejb.fachadas.UsuarioFacade;
import asistentevirtual.jpa.entidades.Rol;
import asistentevirtual.jsf.controladores.util.Session;
import asistentevirtual.jsf.controladores.util.AlgortimoMD5;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario current;
    private DataModel items = null;
    @EJB
    private asistentevirtual.ejb.fachadas.UsuarioFacade ejbFacade;
    @EJB
    private asistentevirtual.ejb.fachadas.EstadousuarioFacade oEstadoUsuarioFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Session oSession;
    
    private Boolean administrador = null;

    public Boolean getAdministrador() {
        return administrador;
    }

    
    public Session getoSession(){
        return oSession;
    }
    
    public void setoSession(){
        this.oSession = oSession;
    }

    public UsuarioController() {
        oSession = new Session();
    }

    public Usuario getSelected() {
        if (current == null) {
            current = new Usuario();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Usuario();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("UsuarioCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("UsuarioUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("resources/Bundle").getString("UsuarioDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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

    private void recreatePagination() {
        pagination = null;
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
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Usuario getUsuario(java.math.BigDecimal id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getPkUsuario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }        
    }
    /*
        Alexis Alvarado
        23/04/2021
        Retorna la ruta raiz respectiva dependiendo si el suario es administrador o Usuario
    */
    
    public void autenticarUsuario(){
        String strContrasena = current.getContrasena();
        String strContrasenaMD5 = AlgortimoMD5.cifrarMD5(strContrasena);
        Usuario oUsuario = ejbFacade.autenticar(current.getNombreusuario(), strContrasenaMD5, oEstadoUsuarioFacade);
        String strUrlMenu = null;
        
        if(oUsuario != null){
            strUrlMenu = obtenerRutaMenuPrincipal(oUsuario);
            if(oSession != null){
                oSession.crearSesion_JSF_HTTP(true);
                oSession.getoSession().setAttribute("oSesionUsuario", oUsuario);
                oSession.redireccionarUrl(strUrlMenu);
            }
        }
    }
    
    /**
     * Alexis Alvarado
     * 23/04/2021
     * Destruye la sesion del usuario actual y lo redirecciona a la pagina de login 
     */
    
    
    public void cerrarSesion() throws IOException{
        if(oSession != null){
            oSession.crearSesion_JSF_HTTP(false);
            oSession.redireccionarUrl("/faces/index.xhtml");
        
        }
    }
    
    /*
    Alexis Alvarado
    23/04/2021
    Retorna la ruta respectiva dependiendo si el usuario es administrador o usuario normal 
    */
    
    public String obtenerRutaMenuPrincipal(){
        String strRutaActual = "/faces/index.xhtml";
        
        if(oSession.getoSession() != null){
            Usuario oU = (Usuario) oSession.getoSession().getAttribute("oSesionUsuario");
            BigDecimal valor = oU.getFkRol().getPkRol();
            int intTipoUsuario = valor.intValue();
            switch(intTipoUsuario){
                case 1: 
                    //Administrador
                    strRutaActual = "/faces/catalogos/menuAdmin.xhtml?faces-redirect=true";
                    break;
                case 2:
                    //Usuario Normal
                    strRutaActual = "/faces/catalogos/menuAdmin.xhtml?faces-redirect=true";
                    break;
                default:
                    strRutaActual = "/faces/catalogos/menuAdmin.xhtml?faces-redirect=true";
                    break;
            }
        
        }
        return strRutaActual;
    }
    
    public String obtenerRutaMenuPrincipal(Usuario oU){
        String strRutaActual = "/faces/index.xhtml";        
        BigDecimal valor = oU.getFkRol().getPkRol(); 
        int intTipoUsuario = valor.intValue();
        switch(intTipoUsuario){
            case 1: 
                //Administrador
                strRutaActual = "/faces/catalogos/menuAdmin.xhtml?faces-redirect=true";
                break;
            case 2:
                //Usuario Normal
                strRutaActual = "/faces/catalogos/menuAdmin.xhtml?faces-redirect=true";
                break;
            default:
                strRutaActual = "/faces/catalogos/menuAdmin.xhtml?faces-redirect=true";
                break;
            }
        return strRutaActual;
    }
    
    /*
    Alexis Alvarado
    permite obtener un usuario dado su id
    */
    
    public String obtenerNombreCompletoUsuarioPorId(BigDecimal UsuarioId){
        return ejbFacade.obtenerNombreCompletoUsuarioPorId(UsuarioId);
    
    }
    
    public Usuario obtenerUsuarioLogueado() {
        return oSession.obtenerUsuarioLogueado();
    }

}
