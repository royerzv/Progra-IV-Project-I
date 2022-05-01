package citas.presentation.usuario.administrador.especialidades;

import citas.logic.Service;
import citas.logic.Usuario;
import java.util.List;

public class Model
{
    List<String> especialidades;
    Usuario current;

    public Model()
    {
        Service service = Service.instance();
        this.reset();
        especialidades=service.getEspecialidades();
    }
    
    public void reset()
    {
        setCurrent(new Usuario());        
    }
    
    public void setEspecialidades(List<String> especialidades)
    {
        this.especialidades = especialidades;    
    }

    public List<String> getEspecialidades()
    {
        return especialidades;
    }
    
    public Usuario getCurrent()
    {
        return current;
    }

    public void setCurrent(Usuario current)
    {
        this.current = current;
    }
}
