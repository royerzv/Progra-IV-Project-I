package citas.presentacion.registro.medico;

import citas.logic.Medico;
import citas.logic.Usuario;

public class Model
{
    Medico current;

    public Model()
    {
        this.reset();
    }
    
    public void reset()
    {
        setCurrent(new Medico());        
    }
    
    public Medico getCurrent()
    {
        return current;
    }

    public void setCurrent(Medico current)
    {
        this.current = current;
    }
}
