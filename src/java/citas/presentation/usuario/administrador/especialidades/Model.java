package citas.presentation.usuario.administrador.especialidades;

import citas.presentation.usuario.administrador.ciudades.*;
import citas.logic.Usuario;

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
}
