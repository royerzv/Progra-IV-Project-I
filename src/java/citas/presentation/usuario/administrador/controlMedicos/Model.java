package citas.presentation.usuario.administrador.controlMedicos;

import citas.logic.Medico;
import citas.logic.Service;
import citas.logic.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model
{
    Usuario current;

    public Model()
    {
        this.reset();
    }
    
    public void reset()
    {
        setCurrent(new Usuario());        
    }
    
    public Usuario getCurrent()
    {
        return current;
    }

    public void setCurrent(Usuario current)
    {
        this.current = current;
    }
    
    public List<Medico> getMedicos()
    {
        Service service = Service.instance();
        return service.getMedicos();
    }
}
