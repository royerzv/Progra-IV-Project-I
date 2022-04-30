/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citas.presentation.busqueda;

import citas.logic.Medico;
import java.util.List;
import java.util.ArrayList;

public class Model 
{
    List<Medico> current;
    Medico dummy;
    String especialidad;
    String localidad;

    public Model()
    {
        this.reset();
    }
    
    public void reset()
    {
        setCurrent(new ArrayList<Medico>());        
    }
    
    public List<Medico> getCurrent()
    {
        return current;
    }   

    public void setCurrent(List<Medico> current) 
    {
        this.current = current;
    }
    
    public String getEspecialidad(){
        return especialidad;
    }
    
    public String getLocalidad(){
        return localidad;
    }
    
    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }
    
    public void setLocalidad(String localidad){
        this.localidad = localidad;
    }
}
