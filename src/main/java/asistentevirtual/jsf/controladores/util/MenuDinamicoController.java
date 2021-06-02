/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.jsf.controladores.util;

import asistentevirtual.dto.Menu.ItemMenu;
import asistentevirtual.ejb.fachadas.AccionpermitidaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.inject.Named;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Named("menuDinamicoController")
@SessionScoped


public class MenuDinamicoController implements Serializable{
    @EJB
    private AccionpermitidaFacade oAccionpermitidaFacade;
    private Menubar model;
    private Session oSession;
    
    private void createModel(){
    }
    
    public Session getoSession(){
        return oSession;
    }
    
    /**
     * Alexis Alvarado
     * 29/04/2021
     * Funcion que retona las opciones del menu
     * @return 
     * @throws java.io.IOException
    */
    
    public Menubar getModel() throws IOException{
        BigDecimal idUsuario = null;
        oSession = new Session();
        
        if(oSession.obtenerUsuarioLogueado() != null){
            idUsuario = oSession.obtenerUsuarioLogueado().getPkUsuario();        
        }
        
        if(idUsuario != null){
            List<ItemMenu> listaAcciones = oAccionpermitidaFacade.obtenerArbolAPPorUsuario(idUsuario);
            int level;
            Menubar tempMenu = new Menubar();
            UIComponent hijo = tempMenu;
            
            for(ItemMenu accionPermitida : listaAcciones){
                level = accionPermitida.getLevel();
                if(level == 1){
                    hijo = tempMenu;                
                }else{
                    hijo = ObtenerUltimoHijo(tempMenu);
                }
                while (level > 2){
                    hijo = ObtenerUltimoHijo(hijo);
                    level--;                
                }
                hijo.getChildren().add(CrearItemMenu(accionPermitida));            
            }
            this.model = tempMenu;
        }
        return model;
    }
    
    /**
     * Alexis Alvarado
     * 29/04/2021
     * Funcion que retorna el ultimo hijo de una rama, si no tiene hijos, retorna el 
     * mismo objeto que llega como parametro
     * @param menu
     * @return 
     */
    
    public UIComponent ObtenerUltimoHijo (UIComponent menu){
        int tamanio = menu.getChildren().size();
        if(tamanio == 0){
            return menu;        
        }else{
            UIComponent hijo = menu.getChildren().get(tamanio - 1);
            return hijo;
        }
    }
    
    /**
     * Alexis Alvarado
     * 29/04/2021
     * Funcion que dependiendo de la accion, si es una raiz o item, crea el 
     * respectivo objeto para ser adicionado al menu 
     * @param accion
     * @return 
     */
    
    public UIComponent CrearItemMenu(ItemMenu accion){
        String descripcion = accion.getDescripcion();
        String ruta = accion.getRuta();
        
        if(ruta == null || ruta.equals("") || ruta.equals("#")) {
            UISubmenu padreItem = new UISubmenu();
            padreItem.setLabel(descripcion);
            padreItem.setStyle("color: #036fab; font-size: 10pt; font-weight: bold;");
            return padreItem;
        }else{
            UIMenuItem hijo = new UIMenuItem();
            hijo.setValue(descripcion);
            hijo.setUrl(ruta);
            hijo.setStyle("color: #036fab; font-size: 10pt; font-weight: bold;");
            return hijo;
        }
    }
    
    public void setModel (Menubar model){
        this.model = model;    
    }
    
    public MenuDinamicoController() throws IOException{
        createModel();
    }
    
    
}
