package citas.presentation.usuario.medico.gestioncita;

import citas.logic.Medico;
import citas.logic.Usuario;
import citas.logic.Cita;
import java.util.List;
import java.util.ArrayList;

public class Model {

    private Medico currentMedico;
    private Usuario currentPaciente;
    private List<Cita> listaCitasEstado;
    private List<Cita> listaCitasNomPaciente;
    private Cita cita;
    

    public Model()
    {
        this.reset();
    }
    
    public void reset()
    {
        setListaCitasEstado(new ArrayList<Cita>());
        setListaCitasNomPaciente(new ArrayList<Cita>());
        setCita(new Cita());
        setCurrentPaciente(new Usuario());
    }
    
    public Medico getCurrentMedico()
    {
        return currentMedico;
    }

    public void setCurrentMedico(Medico current) 
    {
        this.currentMedico = currentMedico;
    }
    
    public Usuario getCurrentPaciente()
    {
        return currentPaciente;
    }

    public void setCurrentPaciente(Usuario currentPaciente) 
    {
        this.currentPaciente = currentPaciente;
    }
    
    public List<Cita> getListaCitasEstado()
    {
        return listaCitasEstado;
    }
    
    public void setCita(Cita cita) 
    {
        this.cita = cita;
    }
    
    public Cita getCita()
    {
        return cita;
    }

    public void setListaCitasEstado(List<Cita> listaCitasEstado) 
    {
        this.listaCitasEstado = listaCitasEstado;
    }
    
    public List<Cita> getListaCitasNomPaciente()
    {
        return listaCitasNomPaciente;
    }

    public void setListaCitasNomPaciente(List<Cita> listaCitasNomPaciente) 
    {
        this.listaCitasNomPaciente = listaCitasNomPaciente;
    }

}
