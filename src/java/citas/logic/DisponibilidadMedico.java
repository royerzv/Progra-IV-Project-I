package citas.logic;

import citas.logic.*;
import java.util.List;

public class DisponibilidadMedico implements java.io.Serializable {
    
    private int codigoDisponibilidad;
    private String cedulaMedico;
    private String dia;
    private int horaInicio;
    private int horaFinal;
    private List<Cita> listaCitas;
    
    public DisponibilidadMedico(){}
    
    public DisponibilidadMedico(int codigoDisponibilidad, String cedulaMedico, String dia, int horaInicio, int horaFinal){
        this.codigoDisponibilidad = codigoDisponibilidad;
        this.cedulaMedico = cedulaMedico;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
    
    public int getCodigoDisponibilidad(){
        return this.codigoDisponibilidad;
    }
    
    public void setCodigoDisponbilidad(int codigoDisponibilidad){
        this.codigoDisponibilidad = codigoDisponibilidad;
    }
    
    public String getCedulaMedico(){
        return this.cedulaMedico;
    }
    
    public void setCedulaMedico(String cedulaMedico){
        this.cedulaMedico = cedulaMedico;
    }
    
    public String getDia(){
        return this.dia;
    }
    
    public void setDia(String dia){
        this.dia = dia;
    }
    
    public int getHoraInicio(){
        return this.horaInicio;
    }
    
    public void setHoraInicio(int horaInicio){
        this.horaInicio = horaInicio;
    }
    
    public int getHoraFinal(){
        return this.horaFinal;
    }
    
    public void setHoraFinal(int horaFinal){
        this.horaFinal = horaFinal;
    }
    
    public List<Cita> getListaCitas(){
        return this.listaCitas;
    }
    
    public void setListaCitas(List<Cita> listaCitas){
        this.listaCitas = listaCitas;
    }
}
