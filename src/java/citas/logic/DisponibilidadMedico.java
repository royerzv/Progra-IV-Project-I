package citas.logic;

import citas.logic.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisponibilidadMedico implements java.io.Serializable {
    
    private int codigoDisponibilidad;
    private String cedulaMedico;
    private String dia;
    private String horaInicio;
    private String horaFinal;
    private List<Cita> listaCitas;
    
    public DisponibilidadMedico(){}
    
    public DisponibilidadMedico(int codigoDisponibilidad, String cedulaMedico, String dia, String horaInicio, String horaFinal){
        this.codigoDisponibilidad = codigoDisponibilidad;
        this.cedulaMedico = cedulaMedico;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
    
    public DisponibilidadMedico(String cedulaMedico, String dia, String horaInicio, String horaFinal){
        Service service = Service.instance();
        this.codigoDisponibilidad = service.agregaDisponibilidadesCreaID(cedulaMedico, dia, horaInicio, horaFinal);
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
    
    public String getHoraInicio(){
        return this.horaInicio;
    }
    
    public void setHoraInicio(String horaInicio){
        this.horaInicio = horaInicio;
    }
    
    public String getHoraFinal(){
        return this.horaFinal;
    }
    
    public void setHoraFinal(String horaFinal){
        this.horaFinal = horaFinal;
    }
    
    public List<Cita> getListaCitas(){
        return this.listaCitas;
    }
    
    public void setListaCitas(List<Cita> listaCitas){
        this.listaCitas = listaCitas;
    }
}
