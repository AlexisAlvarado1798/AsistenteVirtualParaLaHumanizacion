/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.ejb.fachadas;

import asistentevirtual.jpa.entidades.Estadousuario;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Stateless
public class EstadousuarioFacade extends AbstractFacade<Estadousuario> {

    @PersistenceContext(unitName = "sena.ccys.proyectosalud_asistentevirtual_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadousuarioFacade() {
        super(Estadousuario.class);
    }
    
    /*
     * AlexisAlvarado
     * 22/03/2021
     * Permitir obtener el estado de un usuario
     */
    
    public boolean estadoUsuario(BigDecimal UsuarioId){
        Query consulta = em.createNativeQuery("SELECT CODIGO FROM ESTADO E INNER JOIN ESTADOUSUARIO ES "
                + "ON E.PK_ESTADO = ES.FK_ESTADO WHERE ES.PK_ESTADOUSUARIO = (SELECT MAX "
                + "(ES.PK_ESTADOUSUARIO)"
                + " FROM ESTADOUSUARIO EU WHERE EU.FK_USUARIO = "+ UsuarioId
                +" AND (SYSDATE - 1 BETWEEN EU.FECHAINICIO AND EU.FECHAFIN))");
        
        try{
            String strResult = (String) consulta.getSingleResult();
            return strResult.equals("1");
        }catch(NoResultException e){
            return false;
        }
    
    }
    
    
    
}
