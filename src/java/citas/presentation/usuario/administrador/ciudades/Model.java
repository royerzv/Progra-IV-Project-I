package citas.presentation.usuario.administrador.ciudades;

import citas.logic.Usuario;
import java.util.List;

public class Model
{
    List<String> ciudades;
    Usuario current;

    public Model()
    {
        this.reset();
        ciudades=null;
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
