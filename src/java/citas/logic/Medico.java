package citas.logic;

import java.util.*;
import citas.logic.*;

public class Medico implements java.io.Serializable{

    Usuario usuario;
    private String cedula;
    private String nombreEspecialidad;
    private String nombreLocalidad;
    private int costoConsulta;
    private String presentacion;
    private HashMap<String, DisponibilidadMedico> disponibilidades;
    private int duracionConsulta;
    private boolean medicoActivado;

    
    public Medico() {}
	
    public Medico(Usuario usuario, String cedula, String nombreEspecialidad, String nombreLocalidad, int costoConsulta, String presentacion, HashMap<String, DisponibilidadMedico> disponibilidades, int duracionConsulta)
    {
       this.usuario = usuario;
       this.cedula = cedula;
       this.nombreEspecialidad = nombreEspecialidad;
       this.nombreLocalidad = nombreLocalidad;
       this.costoConsulta = costoConsulta;
       this.presentacion = presentacion;
       this.disponibilidades = disponibilidades;
       this.duracionConsulta = duracionConsulta;
       this.medicoActivado = false;
    }
    
    /*public Medico(String cedula, String nombreEspecialidad, String nombreLocalidad, int costoConsulta, String presentacion, int codDisp, int duracionConsulta)
    {
       this.cedula = cedula;
       this.nombreEspecialidad = nombreEspecialidad;
       this.nombreLocalidad = nombreLocalidad;
       this.costoConsulta = costoConsulta;
       this.presentacion = presentacion;
       this.codDisp = codDisp;
       this.duracionConsulta = duracionConsulta;
    }
    
    public Medico(Usuario usuario, String cedula, String nombreEspecialidad, String nombreLocalidad, int costoConsulta, String presentacion, int duracionConsulta)
    {
       this.usuario = usuario;
       this.cedula = cedula;
       this.nombreEspecialidad = nombreEspecialidad;
       this.nombreLocalidad = nombreLocalidad;
       this.costoConsulta = costoConsulta;
       this.presentacion = presentacion;
       //this.codDisp = codDisp;
       this.duracionConsulta = duracionConsulta;
    }*/
    
    public Usuario getUsuario()
    {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }
    
    public String getCedula()
    {
        return this.cedula;
    }
    
    public void setCedula(String cedula)
    {
        this.cedula = cedula;
    }
    
    public String getNombreEspecialidad()
    {
        return this.nombreEspecialidad;
    }
    
    public void setNombreEspecialidad(String nombreEspecialidad)
    {
        this.nombreEspecialidad = nombreEspecialidad;
    }
    public String getNombreLocalidad()
    {
        return this.nombreLocalidad;
    }
    
    public String setNombreLocalidad()
    {
        return this.nombreLocalidad;
    }
    
    
    public void setCostoConsulta(int costoConsulta) 
    {
        this.costoConsulta = costoConsulta;
    }
    
    public int getCostoConsulta()
    {
        return this.costoConsulta;
    }
    
    public void setPresentacion(String presentacion)
    {
        this.presentacion = presentacion;
    }

    public String getPresentacion()
    {
        return this.presentacion;
    }
    
    public void setDisponibilidades(HashMap<String, DisponibilidadMedico> disponibilidades)
    {
        this.disponibilidades = disponibilidades;
    }
    
    public HashMap<String, DisponibilidadMedico> getDisponibilidades()
    {
        return this.disponibilidades;
    }
    
    public void setDuracionConsulta(int duracionConsulta)
    {
        this.duracionConsulta = duracionConsulta;
    }
    
    public int getDuracionConsulta()
    {
        return this.duracionConsulta;
    }
    
    public boolean isMedicoActivado()
    {
        return medicoActivado;
    }

    public void setMedicoActivado(boolean medicoActivado)
    {
        this.medicoActivado = medicoActivado;
    }

    boolean isMedicoActivado(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Medico other = (Medico) obj;
        if (!Objects.equals(this.cedula, other.cedula))
        {
            return false;
        }
        return true;
    }
}
