package citas.logic;

import java.util.List;

public class Medico implements java.io.Serializable
{
    Usuario usuario;
    private String cedula;
    private String nombreEspecialidad;
    private String nombreLocalidad;
    private int costoConsulta;
    private String presentacion;
    private int codDisp;
    private int duracionConsulta;
    private String horaInicio;
    private String horaFinal;
    private String[] diasDisponible;
    private boolean medicoActivado;
    
    public Medico() 
    {
        usuario = new Usuario();
    }
	
    public Medico(Usuario usuario, String cedula, String nombreEspecialidad, String nombreLocalidad, int costoConsulta, String presentacion, 
    int codDisp, int duracionConsulta,String horaInicio,String horaFinal,String[] diasDisponible)
    {
        this.usuario = usuario;
        this.cedula = cedula;
        this.nombreEspecialidad = nombreEspecialidad;
        this.nombreLocalidad = nombreLocalidad;
        this.costoConsulta = costoConsulta;
        this.presentacion = presentacion;
        this.codDisp = codDisp;
        this.duracionConsulta = duracionConsulta;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.diasDisponible = diasDisponible;
        this.medicoActivado = false;
    }
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
    
    public void setNombreLocalidad(String nombreLocalidad)
    {
        this.nombreLocalidad=nombreLocalidad;
    }
    
    public int getCostoConsulta()
    {
        return costoConsulta;
    }
    
    public void setCostoConsulta(int costoConsulta)
    {
        this.costoConsulta = costoConsulta;
    }
    
    public String getPresentacion()
    {
        return presentacion;
    }
    
    public void setPresentacion(String presentacion)
    {
        this.presentacion = presentacion;
    }
    
    public int getCodDisp()
    {
        return codDisp;
    }
    
    public void setCodDisp(int codDisp)
    {
        this.codDisp = codDisp;
    }
    
    public int getDuracionConsulta()
    {
        return duracionConsulta;
    }
    
    public void setDuracionConsulta(int duracionConsulta)
    {
        this.duracionConsulta = duracionConsulta;
    }
    
    public String getHoraInicio()
    {
        return horaInicio;
    }
    
    public void  setHoraInicio(String horaInicio)
    {
        this.horaInicio = horaInicio;
    }
    
    public String getHoraFinal()
    {
        return horaFinal;
    }
    
    public void  setHoraFinal(String horaFinal)
    {
        this.horaFinal = horaFinal;
    }
    
    public String[] getDiasDisponible()
    {
        return diasDisponible;
    }
    
    public void setDiasDisponible(String[] diasDisponible)
    {
        this.diasDisponible = diasDisponible;
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
    
    
}