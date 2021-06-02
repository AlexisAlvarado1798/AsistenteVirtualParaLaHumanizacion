/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.ejb.fachadas;

import asistentevirtual.dto.Menu.ItemMenu;
import asistentevirtual.dto.Menu.ItemMenuTips;
import asistentevirtual.jpa.entidades.Tips;
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
public class TipsFacade extends AbstractFacade<Tips> {

    @PersistenceContext(unitName = "sena.ccys.proyectosalud_asistentevirtual_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipsFacade() {
        super(Tips.class);
    }
    
    /**
     * Alexis Alvarado @funcion: Retorna el menu
     * 29/04/2021
     * @param idRol
     * @return
     */

    public List<ItemMenuTips> obtenerArbolDeMenusPorRol(BigDecimal idRol) {
        StringBuilder sbSQL = new StringBuilder();
        
        sbSQL.append(" SELECT LEVEL, TIPS.*");
        sbSQL.append(" FROM ( ");
        sbSQL.append(" select ti.pk_tips, ti.codigo, ti.descripcion, ti.fk_tips ");
        sbSQL.append(" from TIPS TI ");
        sbSQL.append(" inner join TIPSPORROL TP on (ti.pk_tips = tp.fk_tips) ");
        sbSQL.append(" where tp.fk_rol = ").append(idRol);
        sbSQL.append(" ) TIPS ");
        sbSQL.append(" START WITH FK_TIPS is NULL ");
        sbSQL.append(" CONNECT BY PRIOR PK_TIPS = FK_TIPS ");
        sbSQL.append(" order by PK_TIPS"); 
        
        try{
            List<ItemMenuTips> listaMenu = new ArrayList<>();
            Query consulta = em.createNativeQuery(sbSQL.toString());
            
            List<Object[]> listaTips = consulta.getResultList();
            for(Object[] tips : listaTips){
                ItemMenuTips item = new ItemMenuTips();
                item.setLevel(((BigDecimal) tips[0]).intValue());
                item.setId(((BigDecimal) tips[1]).intValue());
                item.setCodigo((String) tips[2]);
                item.setDescripcion((String) tips[3]);
                listaMenu.add(item);            
            }
            return  listaMenu;
        }catch(NoResultException e){
            return null;        
        }
    }

    public List<Tips> obtenerArbolDeTips(BigDecimal idTips) {
        StringBuilder consulta = new StringBuilder("SELECT * FROM TIPS TI ");
        consulta.append("where TI.FK_TIPS = ");
        consulta.append(idTips);
        
        Query sql = em.createNativeQuery(consulta.toString(), Tips.class);
        List<Tips> lstTips;
        try{
            lstTips = (List<Tips>) sql.getResultList();
        }catch(NoResultException e){
            return null;        
        }
        return lstTips;
    }
    
}
