/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.ejb.fachadas;

import asistentevirtual.jpa.entidades.Municipio;
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
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "sena.ccys.proyectosalud_asistentevirtual_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }
    
    /**
     * Alexis Alvarado
     * 29/04/2021
     * Permite obtener los municipios a traves del codigo del departamento
     * @param codDepto
     * @return 
     */

    public ArrayList<Municipio> obtenerMunicipiosPorDepartamento (String codDepto) {
        Query consulta = em.createNamedQuery("Municipio.findByDepartamento");
        consulta.setParameter("codDepartamento", codDepto);
        ArrayList<Municipio> lstMunicipios = new ArrayList<>();
        try{
            lstMunicipios = (ArrayList<Municipio>) consulta.getResultList();
        
        }catch(NoResultException e){
            System.out.println(e.getMessage());
        }
        return lstMunicipios;
    }
    
    
    public List<Municipio> obtenerMunicipiosPorDepartamento(BigDecimal codDpartamento){
        
        StringBuilder sbSQL = new StringBuilder();
        sbSQL.append("SELECT * FROM MUNICIPIO M");
        sbSQL.append("INNER JOIN DEPARTAMENTO D on (M.FK_DEPARTAMENTO = D.PK_DEPARTAMENTO)");
        sbSQL.append("WHERE D.CODIGO = '").append(codDpartamento);
        sbSQL.append("' ORDER BY M.nombre");
        
        Query consulta = em.createNativeQuery(sbSQL.toString(), Municipio.class);
        List<Municipio> lstMunicipios = new ArrayList<>();
        
        try{
            lstMunicipios = consulta.getResultList();
        }catch(NoResultException e){
            System.out.println(e.getMessage());
        }
        
        return lstMunicipios;
    
    }
    
}
