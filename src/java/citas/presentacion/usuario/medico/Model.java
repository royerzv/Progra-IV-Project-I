/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package citas.presentacion.usuario.medico;

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
