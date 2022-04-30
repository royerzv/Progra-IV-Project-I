package citas.logic;

import java.util.Objects;

public class Usuario  implements java.io.Serializable {

    private String cedula;
    private String clave;
    private String tipo;
    private String nombre;
    private String foto;

    public Usuario() 
    {
    }
	
    public Usuario(String cedula)
    {
        this.cedula = cedula;
    }
    public Usuario(String cedula, String clave, String tipo, String nombre, String foto)
    {
       this.cedula = cedula;
       this.clave = clave;
       this.tipo = tipo;
       this.nombre = nombre;
       this.foto = foto;
    }
   
    public String getCedula()
    {
        return this.cedula;
    }
    
    public void setCedula(String cedula)
    {
        this.cedula = cedula;
    }
    public String getClave()
    {
        return this.clave;
    }
    
    public void setClave(String clave)
    {
        this.clave = clave;
    }
    public String getTipo()
    {
        return this.tipo;
    }
    
    public void setTipo(String tipo) 
    {
        this.tipo = tipo;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getFoto()
    {
        return this.foto;
    }
    
    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.cedula, other.cedula))
        {
            return false;
        }
        return true;
    }
}
