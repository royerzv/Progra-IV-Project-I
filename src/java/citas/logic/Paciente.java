/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package citas.logic;
import citas.logic.Usuario;


public class Paciente {

    Usuario usuario;
    
    public Paciente()
    {
        this.reset();
    }
    
    public Paciente(Usuario usuario)
    {
        this.usuario = usuario;
    }
    
    public void reset()
    {
        setCurrent(new Usuario());        
    }
    
    public Usuario getCurrent()
    {
        return usuario;
    }   

    public void setCurrent(Usuario current) 
    {
        this.usuario = current;
    }
}
