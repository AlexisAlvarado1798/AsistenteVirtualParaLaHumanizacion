/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.jsf.controladores.util;

import asistentevirtual.jpa.entidades.Usuario;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author AdminAlexisAlvarado
 */
public class Session {
    private HttpServletRequest oRequest;
    private HttpSession oSession;
    private FacesContext oFctx;
    private ExternalContext oEctx;
    
    public HttpSession getoSession(){
        return oSession;
    }
    /*
    Alexis Alvarado 
    23/04/2021
    Crear Sesiones a travez del acceso a las prioridades de la aplicacion y de la 
    peticion HTTP.
    Lo anterior por medio del contexto JSF o por medio del contexto HTTP 
    */
    
    public void crearSesion_JSF_HTTP(boolean blnCrearSesion){
        oRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(!blnCrearSesion){
            oSession.invalidate();        
        }
        oSession = oRequest.getSession(blnCrearSesion);
    
    }
    
    /*
    Alexis Alvarado 
    23/04/2021
    Redirecciona la Url que entra como parametro a su pagina respectiva
    */
    
    public void redireccionarUrl(String strUrlMenu){
        oFctx = FacesContext.getCurrentInstance();
        oEctx = oFctx.getExternalContext();
        String strUrlBase = oEctx.getRequestContextPath();
        try{
            oEctx.redirect(strUrlBase + strUrlMenu);
        }catch(IOException e){
            e.getMessage();
        }
        oFctx.responseComplete();    
    }
    
    /*
    Alexis Alvarado
    23/04/2021
    Obtiene el usuario que se encuentra loguado en la aplicacion
    */
    
    public Usuario obtenerUsuarioLogueado(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        Usuario usuarioBO = (Usuario) session.getAttribute("oSesionUsuario");
        return usuarioBO;
    }
    
}
