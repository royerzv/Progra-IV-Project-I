/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package citas.logic;
import citas.logic.Medico;
import citas.logic.Paciente;
import java.util.List;
import java.util.ArrayList;


public class Cita {

    int idCita;
    Medico medico; 
    Paciente paciente;
    String fechaCita;
    String estado;
    
    public Cita() {}
    
    public Cita(int idCita, Medico medico, Paciente paciente, String fechaCita, String estado)
    {
       this.idCita = idCita;
       this.medico = medico;
       this.paciente = paciente;
       this.fechaCita = fechaCita;
       this.estado = estado;
    }
    
    public Cita(int idCita, Paciente paciente, String fechaCita, String estado)
    {
       this.idCita = idCita;
       this.paciente = paciente;
       this.fechaCita = fechaCita;
       this.estado = estado;
    }
    
    public void setIdCita(int idCita){
        this.idCita = idCita;
    }
    
    public int getIdCita(){
        return idCita;
    }
    
    public void setMedico(Medico medico){
        this.medico = medico;
    }
    
    public Medico getMedico(){
        return medico;
    }
    
    public void setPaciente(Paciente paciente){
        this.paciente = paciente;
    }
    
    public Paciente getPaciente(){
        return paciente;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public String getEstado(){
        return estado;
    }
}
