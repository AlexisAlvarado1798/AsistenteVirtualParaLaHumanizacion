/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.jsf.controladores.util;

import asistentevirtual.dto.Menu.ItemMenuTips;
import asistentevirtual.ejb.fachadas.TipsFacade;
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
import org.primefaces.component.slidemenu.SlideMenu;
import org.primefaces.component.submenu.UISubmenu;

/**
 *
 * @author AdminAlexisAlvarado
 */

@Named("menuTipsController")
@SessionScoped
        
        
public class MenuTipsController implements Serializable{
    @EJB
    private TipsFacade oTipsFacade;
    private Menubar model;
    private Session oSession;
    private SlideMenu model1;
    
    private void createModel(){
    }
    
    private void createModel1(){
    }
    
    public Session getoSession(){
        return oSession;
    }
        
    /**
     * Alexis Alvarado
     * 12/05/2021
     * Funcion que retona las opciones del menu del Asistente
     * @return 
     * @throws java.io.IOException
    */
    
    public Menubar getModel () throws IOException{
        BigDecimal idRol = null;
        oSession = new Session();
        
        if(oSession.obtenerUsuarioLogueado() != null){
            idRol = oSession.obtenerUsuarioLogueado().getFkRol().getPkRol();
        }
        
        if(idRol != null){
            List<ItemMenuTips> listaTips = oTipsFacade.obtenerArbolDeMenusPorRol(idRol);
            int level;
            Menubar tempMenu = new Menubar();
            UIComponent hijo = tempMenu;
            
            for(ItemMenuTips tipsHumanizados : listaTips){
                level = tipsHumanizados.getLevel();
                if(level == 1){
                    hijo = tempMenu;
                }else{
                    hijo = ObtenerUltimoHijo(tempMenu);
                }
                while (level > 2){
                    hijo = ObtenerUltimoHijo(hijo);
                    level--;
                }
                hijo.getChildren().add(CreatItemMenu(tipsHumanizados));
            }
            this.model = tempMenu;
        }
        return model;
    }
    
    
    public SlideMenu getModel1 () throws IOException{
        BigDecimal idRol = null;
        oSession = new Session();
        
        if(oSession.obtenerUsuarioLogueado() != null){
            idRol = oSession.obtenerUsuarioLogueado().getFkRol().getPkRol();
        }
        
        if(idRol != null){
            List<ItemMenuTips> listaTips = oTipsFacade.obtenerArbolDeMenusPorRol(idRol);
            int level;
            SlideMenu tempMenu = new SlideMenu();
            UIComponent hijo = tempMenu;
            
            for(ItemMenuTips tipsHumanizados : listaTips){
                level = tipsHumanizados.getLevel();
                if(level == 1){
                    hijo = tempMenu;
                }else{
                    hijo = ObtenerUltimoHijo(tempMenu);
                }
                while (level > 2){
                    hijo = ObtenerUltimoHijo(hijo);
                    level--;
                }
                hijo.getChildren().add(CreatItemMenu(tipsHumanizados));
            }
            this.model1 = tempMenu;
        }
        return model1;
    }
    
    /**
     * Alexis Alvarado
     * 12/05/2021
     * Funcion que retorna el ultimo hijo de una rama, si no tiene hijos, retorna el 
     * mismo objeto que llega como parametro
     * @param menu
     * @return 
     */

    private UIComponent ObtenerUltimoHijo(UIComponent menu) {
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
    
    private UIComponent CreatItemMenu(ItemMenuTips tips) {
        String descripcion = tips.getDescripcion();
        
        if(descripcion != null){
            UISubmenu padreItem = new UISubmenu();
            padreItem.setLabel(descripcion);
            padreItem.setStyle("color: #036fab; font-size: 10pt; font-weight: bold;");
            padreItem.setStyleClass("myMeineClass");
            return padreItem;
        }else{
            UIMenuItem hijo = new UIMenuItem();
            hijo.setValue(descripcion);
            hijo.setStyle("color: #036fab; font-size: 10pt; font-weight: bold;");
            return hijo;
        }
    }
    
    public void setModel (Menubar model){
        this.model = model;    
    }
    
    public void setModel1 (SlideMenu model1){
        this.model1 = model1;    
    }
    
    public MenuTipsController() throws IOException{
        createModel();
    }
}
