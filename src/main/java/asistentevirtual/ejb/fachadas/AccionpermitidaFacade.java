/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.ejb.fachadas;

import asistentevirtual.dto.Menu.ItemMenu;
import asistentevirtual.jpa.entidades.Accionpermitida;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class AccionpermitidaFacade extends AbstractFacade<Accionpermitida> {

    @PersistenceContext(unitName = "sena.ccys.proyectosalud_asistentevirtual_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccionpermitidaFacade() {
        super(Accionpermitida.class);
    }
    
    /**
     * Alexis Alvarado @funcion: Retorna el menu
     * 29/04/2021
     * @param UsuarioId
     * @return
     */
    
    public List<ItemMenu> obtenerArbolAPPorUsuario (BigDecimal UsuarioId) {
        StringBuilder sbSQL = new StringBuilder();
        
        sbSQL.append(" SELECT LEVEL,ACCIONES.* ");
        sbSQL.append(" FROM ( ");
        sbSQL.append(" SELECT ap.pk_accionpermitida, ap.codigo, ap.descripcion, ap.ruta, ap.fk_accionpermitida ");
        sbSQL.append(" FROM ACCIONPERMITIDA AP  ");
        sbSQL.append(" INNER JOIN ACCIONPERMITIDAPORUSUARIO AU ON (ap.pk_accionpermitida=AU.FK_ACCIONPERMITIDA) ");
        sbSQL.append(" WHERE au.fk_usuario= ").append(UsuarioId);
        sbSQL.append(" ) ACCIONES ");
        sbSQL.append(" START WITH FK_ACCIONPERMITIDA is NULL ");
        sbSQL.append(" CONNECT BY PRIOR PK_ACCIONPERMITIDA = FK_ACCIONPERMITIDA ");
        sbSQL.append(" order by PK_ACCIONPERMITIDA");
        
        try{
            List<ItemMenu> listaMenu = new ArrayList<>();
            Query consulta = em.createNativeQuery(sbSQL.toString());
            
            List<Object[]> listaAcciones = consulta.getResultList();
            for(Object[] accion : listaAcciones){
                ItemMenu item = new ItemMenu();
                item.setLevel(((BigDecimal) accion[0]).intValue());
                item.setId(((BigDecimal) accion[1]).intValue());
                item.setCodigo((String) accion[2]);
                item.setDescripcion((String) accion[3]);
                item.setRuta((String) accion[4]);
                listaMenu.add(item);
            }
            return listaMenu;
        }catch(NoResultException e){
            return null;        
        }
    } 
}
