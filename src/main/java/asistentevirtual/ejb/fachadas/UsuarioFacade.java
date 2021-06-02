/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.ejb.fachadas;

import asistentevirtual.jpa.entidades.Usuario;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "sena.ccys.proyectosalud_asistentevirtual_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    /*
    Alexis Alvarado
    23/04/2021
    Permite buscar un usuario dado su login... ademas verifica la igualdad del password
    */
    
    public Usuario buscarUsuario(String login, String pass){
        Query consulta = em.createNamedQuery("Usuario.findByNombreusuario");
        consulta.setParameter("nombreusuario", login);
        consulta.setParameter("contrasena", pass);
        
        try{
            Usuario oUsuario = (Usuario) consulta.getSingleResult();
            em.refresh(oUsuario);
            return oUsuario;
        }catch(NoResultException e){
            return null;
        }      
    }
    
    /*
    Alexis Alvarado
    23/04/2021
    Permite obtener el nombre de un usuario por su id
    */
    
    public String obtenerNombreCompletoUsuarioPorId(BigDecimal UsuarioId){
        StringBuilder sbSQL = new StringBuilder("Select login from Usuario where pk_usuario = ");
        sbSQL.append(UsuarioId);
        
        Query consulta = em.createNativeQuery(sbSQL.toString());
        String strNombreCompleto = "";
        
        try{
            strNombreCompleto = consulta.getSingleResult().toString();
        }catch(NoResultException e){
            return strNombreCompleto;
        } 
        return strNombreCompleto;
    }
    
    /*
    Alexis Alvarado
    23/04/2021
    Retorma el usuario que esta logueado en el sistema
    */
    
    public Usuario autenticar (String login, String password, asistentevirtual.ejb.fachadas.EstadousuarioFacade fachadaEstadoUsuario){
        
        FacesMessage msg = null;
        
        Usuario oUsuario = buscarUsuario(login, password);
        if(oUsuario != null){
            //Obtenemos el estado del usuario
            boolean blnEstadoUsuario = fachadaEstadoUsuario.estadoUsuario(oUsuario.getPkUsuario());
            
            if(blnEstadoUsuario){
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", login);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return oUsuario;
            }else{
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Inactivo", login);
            }
        }else{
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Credenciales Invalidas");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }
}
