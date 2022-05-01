package citas.logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import citas.logic.SQLExecutorURL;
import java.sql.*;
        
public final class Service 
{
    private static Service uniqueInstance;
    
    public static Service instance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Service();
        }
        return uniqueInstance; 
    }

    List<Usuario> usuarios;
    List<Medico> medicos;
    List<String> ciudades;
    List<String> especialidades;
    
    private Service()
    {
        usuarios = cargarUsuario();
        medicos = cargarMedicos();
        ciudades = cargarCiudades();
        especialidades = cargarEspecialidades();         
        /*
        usuarios = new HashMap();
        usuarios.put("111", new Usuario("111","111","cliente","Julio", "/Proyecto 1 v3/images/Julio.jpg"));
        usuarios.put("222", new Usuario("222","222","cliente","Uvuewvuew", "/Proyecto 1 v3/images/uv.jpg"));
        usuarios.put("333", new Usuario("333","333","doctor", "Eduardo", "/Proyecto 1 v3/images/Eduardo.jpg"));
        usuarios.put("444", new Usuario("444","444","administrador", "Tony Stark", "/Proyecto 1 v3/images/Tony Stark.jpg"));
        usuarios.put("555",new Usuario("555","555","doctor","Stephen Vincent Strange","/Proyecto 1 v3/images/Stephen Vincent Strange.jpg"));
        
        ciudades = new ArrayList();
        ciudades.add("Heredia");
        ciudades.add("Alajuela");
        ciudades.add("San Jose");
        ciudades.add("Cartago");
        
        especialidades = new ArrayList();
        especialidades.add("Cardiologia");
        especialidades.add("Fisioterapia");
        especialidades.add("Odontologia");
        especialidades.add("Psicologia");
        
        String[] dias = new String[] {"Martes","Viernes"};
        medicos = new HashMap();
        medicos.put("333",new Medico(usuarios.get("333"),usuarios.get("333").getCedula(),"Odontologia", "San Jose",10000,"Medico con 7 años de experiencia",159753,45,"9:00","13:00",dias));
        Medico medicoActivo = new Medico(usuarios.get("555"),usuarios.get("555").getCedula(),"Psicologia","Heredia",15500,"Medico con grandes habilidades",153486,60,"13:30","21:00",dias);
        medicoActivo.setMedicoActivado(true);
        medicos.put("555",medicoActivo);*/
        
    }
    
    public List<Usuario> cargarUsuario()
    {
        List<Usuario> listaUsuario = new ArrayList<>();
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM Usuario");
        try
        {
            while(rs.next())
            {                            
                String cedulaObj = rs.getString("cedula");
                String claveObj = rs.getString("clave");
                String tipoObj = rs.getString("tipo");
                String nombreObj = rs.getString("nombre");
                String fotoObj = "/Proyecto 1/images/" + rs.getString("foto");
                
                Usuario user = new Usuario(cedulaObj, claveObj, tipoObj, nombreObj, fotoObj);
                listaUsuario.add(user);
            }
        } 
        catch (SQLException throwables) 
        {
            throwables.printStackTrace();
        }
        return listaUsuario;
    }
    
    public List<Medico> cargarMedicos()
    {
        List<Medico> listaMedicos = new ArrayList<>();
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM Medico AS M INNER JOIN Usuario AS U ON M.cedula = U.cedula");
        try
        {
            while(rs.next())
            {                      
                String cedulaObj = rs.getString("cedula");
                String nombreEspecialidadObj = rs.getString("nombreEspecialidad");
                String nombreLocalidadObj = rs.getString("nombreLocalidad");
                int costoConsultaObj = rs.getInt("costoConsulta");
                String presentacionObj = rs.getString("presentacion");
                int codDispObj = rs.getInt("codDisp");
                int duracionConsultaObj = rs.getInt("duracionConsulta");
                String claveObj = rs.getString("clave");
                String tipoObj = rs.getString("tipo");
                String nombreObj = rs.getString("nombre");
                String fotoObj = "/Proyecto 1/images/" + rs.getString("foto");
                
                Usuario user = new Usuario(cedulaObj, claveObj, tipoObj, nombreObj, fotoObj);

                //Medico med = new Medico(user, cedulaObj, nombreEspecialidadObj, nombreLocalidadObj, costoConsultaObj, presentacionObj, codDispObj, duracionConsultaObj);
                
                //listaMedicos.add(med);
            }
        } 
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return listaMedicos;
    }
    
    public List<String> cargarCiudades()
    {
        List<String> listaCiudades = new ArrayList<>();
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM Localidades");
        try
        {
            while(rs.next())
            {                     
                String ciudad = rs.getString("nombreLocalidad");
                listaCiudades.add(ciudad);
            }
        } 
        catch (SQLException throwables) 
        {
            throwables.printStackTrace();
        }
        
        return listaCiudades;
    }
    
    public List<String> cargarEspecialidades()
    {
        List<String> listaEspecialidades = new ArrayList<>();
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM Especialidades");
        try
        {
            while(rs.next())
            {                     
                String especialidad = rs.getString("nombreEspecialidad");
                listaEspecialidades.add(especialidad);
            }
        } 
        catch (SQLException throwables) 
        {
            throwables.printStackTrace();
        }
        
        return listaEspecialidades;
    }
    
    public Usuario usuarioFind(String cedula,String clave,String tipo) throws Exception
    {
        for(Usuario u: usuarios)
        {
            if (u.getCedula().equals(cedula))
            {
                if(u.getClave().equals(clave) && u.getTipo().equals(tipo))
                {
                    return u;
                }
                else
                {
                    throw new Exception("No concuerda el tipo, la clave o el usuario");
                }
            }    
        }
        throw new Exception("Usuario no existe");
    }
    
    public boolean existeUsuario(String cedula)
    {
        for(Usuario u: usuarios)
        {
            if (u.getCedula().equals(cedula))
            {
               return true; 
            }
        }  
        return false;
    }
    
    public void clienteUpdate(Usuario usuario) throws Exception
    {
        if (usuario.getCedula()==null) 
            throw new Exception("Cliente no existe");
        else
        {
            for(Usuario u: usuarios)
            {
                if(u.getCedula().equals(usuario.getCedula()))
                {
                    u.setNombre(usuario.getNombre());
                    SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
                    sqlExecutorURL.abreConexion();
                    ResultSet rs = sqlExecutorURL.ejecutaSQL("UPDATE Usuario SET nombre = '"+usuario.getNombre()+"' WHERE cedula = '"+usuario.getCedula()+"'");
                }
            }
        }
    }
    
    public void agregarCliente(Usuario usuario)
    {
        usuarios.add(usuario);
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        sqlExecutorURL.ejecutaSQL("INSERT Usuario (cedula, clave, tipo, nombre, foto) VALUES ('"+usuario.getCedula()+"', '"+
        usuario.getClave()+"', '"+usuario.getTipo()+"', '"+usuario.getNombre()+"','"+usuario.getFoto()+"')");
    }
    
    public void agregarMedico(Medico medico)
    {
        medicos.add(medico);
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        sqlExecutorURL.ejecutaSQL("INSERT Usuario (cedula, clave, tipo, nombre, foto) VALUES ('"+medico.getUsuario().getCedula()+"', '"+
        medico.getUsuario().getClave()+"', '"+medico.getUsuario().getTipo()+"', '"+medico.getUsuario().getNombre()+"','"+medico.getUsuario().getFoto()+"')");
        
        sqlExecutorURL.ejecutaSQL("INSERT Medico (cedula, nombreEspecialidad, nombreLocalidad, costoConsulta, presentacion, duracionConsulta) VALUES"
        + " ('"+medico.getCedula()+"', '"+medico.getNombreEspecialidad()+"', '"+medico.getNombreLocalidad()+"', "+medico.getCostoConsulta()+",'"+medico.getPresentacion()+"', "+medico.getDuracionConsulta()+")");
    }

    public List<String> getCiudades()
    {
        return ciudades;
    }
    
    public void setCiudades(String ciudad)
    {
        ciudades.add(ciudad);
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        sqlExecutorURL.ejecutaSQL("INSERT Localidades (nombreLocalidad, medico) VALUES ('"+ciudad+"', NULL)");
    }
    
    public List<String> getEspecialidades()
    {
        return especialidades;
    }
    
    public void setEspecialidades(String especialidad)
    {
        especialidades.add(especialidad);
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        sqlExecutorURL.ejecutaSQL("INSERT Especialidades (nombreEspecialidad, medico) VALUES ('"+especialidad+"', NULL)");
    }
    
    public int codigo()
    {
        Random random = new Random();
        int codigo = random.nextInt(999999 + 100000) + 100000;
        if(!existeCodigo(codigo))
        {
            return codigo;
        }
        else
        {
           return codigo(); 
        }
    }
    
    public List<Medico> getMedicos()
    {
        return medicos;
    }
    
    public void activarMedico(String cedula)
    {
        for(Medico m: medicos)
        {
            if(m.getCedula().equals(cedula))
            {
                m.setMedicoActivado(true);
            }
        }
    }
    
    public boolean existeCodigo(int codigo)
    {
        for(Medico m: medicos)
        {
            if(m.getCodDisp() == codigo)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean esMedico(String cedula)
    {
        for(Usuario u: usuarios)
        {
            if(u.getCedula().equals(cedula))
            {
                if(u.getTipo().equals("Medico"))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean medicoActivo(String cedula)
    {
        for(Medico m: medicos)
        {
            if(m.getCedula().equals(cedula))
            {
                return m.isMedicoActivado(cedula);
            }
        }
        return false;
    }
}