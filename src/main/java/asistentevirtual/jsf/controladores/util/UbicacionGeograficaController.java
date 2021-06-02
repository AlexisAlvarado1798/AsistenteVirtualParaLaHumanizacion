/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.jsf.controladores.util;

import asistentevirtual.jpa.entidades.Departamento;
import asistentevirtual.jpa.entidades.Municipio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

/**
 *
 * @author AdminAlexisAlvarado
 */

@Named("ubicacionGeograficaController")
@ViewScoped
public class UbicacionGeograficaController implements Serializable{
    @EJB
    private asistentevirtual.ejb.fachadas.DepartamentoFacade oDepartamentoFacade;
    @EJB
    private asistentevirtual.ejb.fachadas.MunicipioFacade oMunicipioFacade;
    
    private List<Departamento> departamentos;
    private List<Municipio> municipios;
    
    public List<Departamento> getDepartamentos(){
        if(departamentos == null){
            departamentos = oDepartamentoFacade.findAll();
        }
        return departamentos;
    }
    
    public List<Municipio> getMunicipios(){
        return municipios;
    }
    
    public void onDepartamentChange(Departamento depto){
        if(depto != null){
            municipios = oMunicipioFacade.obtenerMunicipiosPorDepartamento(depto.getCodigo());
        
        }else{
            municipios = null;
        }
    
    }
    
}
