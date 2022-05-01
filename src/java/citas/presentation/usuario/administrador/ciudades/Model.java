package citas.presentation.usuario.administrador.ciudades;

import citas.logic.Service;
import citas.logic.Usuario;
import java.util.List;

public class Model
{
    List<String> ciudades;
    Usuario current;

    public Model()
    {
        Service service = Service.instance();
        this.reset();
        ciudades=service.getCiudades();
    }
    
    public void reset()
    {
        setCurrent(new Usuario());        
    }
    
    public void setCiudades(List<String> ciudades)
    {
        this.ciudades = ciudades;    
    }

    public List<String> getCiudades()
    {
        return ciudades;
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
