/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.ejb.fachadas;

import asistentevirtual.jpa.entidades.Tipsporrol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Stateless
public class TipsporrolFacade extends AbstractFacade<Tipsporrol> {

    @PersistenceContext(unitName = "sena.ccys.proyectosalud_asistentevirtual_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipsporrolFacade() {
        super(Tipsporrol.class);
    }
    
}
