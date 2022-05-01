package citas.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import citas.logic.SQLExecutorURL;
import citas.logic.Usuario;
import java.sql.*;
import java.time.*;
import static java.time.LocalTime.parse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service 
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
    List<DisponibilidadMedico> disponibilidades;
    
    
    private Service()
    {   
        usuarios = cargarUsuario();
        medicos = cargarMedicos();
        ciudades = cargarCiudades();
        especialidades = cargarEspecialidades();   
        disponibilidades = cargarDisponibilidades();
        /*String path = "/Proyecto 1/images";
        usuarios = new HashMap();
        usuarios.put("111", new Usuario("111","111","cliente","Julio", path + "/Julio.jpg"));
        usuarios.put("222", new Usuario("222","222","cliente","Uvuewvuew", path + "/uv.jpg"));
        usuarios.put("333", new Usuario("333","333","doctor", "Eduardo", path + "/Eduardo.jpg"));
        usuarios.put("444", new Usuario("444","444","administrador", "Tony Stark", path + "/Tony Stark.jpg"));
        
        ciudades = new ArrayList();
        ciudades.add("Heredia");
        ciudades.add("Alajuela");
        ciudades.add("San José");
        ciudades.add("Cartago");
        
        especialidades = new ArrayList();
        especialidades.add("Cardiología");
        especialidades.add("Fisioterapia");
        especialidades.add("Odontología");
        especialidades.add("Psicología");*/
    }

    /*public Usuario usuarioFind(String cedula,String clave,String tipo) throws Exception
    {
        if (usuarios.get(cedula)!= null && usuarios.get(cedula).getClave().equals(clave) && usuarios.get(cedula).getTipo().equals(tipo)) return usuarios.get(cedula);
        else 
        {
            throw new Exception("Usuario no existe");
        }
    }*/
    
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
                //int codDispObj = rs.getInt("codDisp");
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
    
    public List<DisponibilidadMedico> cargarDisponibilidades(){
        List<DisponibilidadMedico> listDispMed = new ArrayList<>();
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM DispMedico");
        try
        {
            while(rs.next())
            {                            
                int codDispObj = rs.getInt("codDisp");
                String idMedicoObj = rs.getString("idMedico");
                String diaObj = rs.getString("dia");
                String horaInicioObj = rs.getString("horaInicio");
                String horaFinalObj = rs.getString("horaFinal");
                
                DisponibilidadMedico disp =  new DisponibilidadMedico(codDispObj, idMedicoObj, diaObj, horaInicioObj, horaFinalObj);
                listDispMed.add(disp);
            }
        } 
        catch (SQLException throwables) 
        {
            throwables.printStackTrace();
        }
        return listDispMed;
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
    
    /*public Usuario usuarioFind(String cedula,String clave, String tipo) throws Exception
    {
        if (usuarios.get(cedula)!=null){
            if(usuarios.get(cedula).getCedula().equals(cedula) && usuarios.get(cedula).getClave().equals(clave) && usuarios.get(cedula).getTipo().equals(tipo)){
                return usuarios.get(cedula);
            } else {
                throw new Exception("Cedula, clave o tipo incorrectos (Al buscar en Service)");
            }
        } else {
            throw new Exception("Usuario no existe");
        }
    }*/
    
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
    
    public List<Medico> medicosFind(String especialidad, String localidad) throws Exception{
        List<Medico> listaMedicosFound = new ArrayList<>();
        boolean doctorFound = false;
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        
        /*SELECT * FROM MEDICO WHERE nombreEspecialidad LIKE 'Cardiologia' AND nombreLocalidad LIKE 'Guadalupe'
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM MEDICO WHERE nombreEspecialidad LIKE '" + especialidad + "' AND nombreLocalidad LIKE '" + localidad + "'");*/
        
        /*SELECT * FROM [dbo].[Medico] AS M INNER JOIN [dbo].[Usuario] AS U ON M.cedula = U.cedula WHERE nombreEspecialidad LIKE 'Cardiologia' AND nombreLocalidad LIKE 'Guadalupe'
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM Medico AS M INNER JOIN Usuario AS U ON M.cedula = U.cedula WHERE nombreEspecialidad LIKE '" + especialidad + "' AND nombreLocalidad LIKE '" + localidad + "'");*/
                                                  
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM Medico AS M INNER JOIN Usuario AS U ON M.cedula = U.cedula INNER JOIN DispMedico AS D ON M.cedula = D.idMedico WHERE nombreEspecialidad LIKE '" + especialidad + "' AND nombreLocalidad LIKE '" + localidad + "'");
        try{
            while(rs.next()){
                //if(rs.getString("cedula").equals(cedula) && rs.getString("nombreEspecialidad").equals(especialidad) && rs.getString("nombreLocalidad").equals(localidad)){
                                        
                String cedulaObj = rs.getString("cedula");
                String nombreEspecialidadObj = rs.getString("nombreEspecialidad");
                String nombreLocalidadObj = rs.getString("nombreLocalidad");
                int costoConsultaObj = rs.getInt("costoConsulta");
                String presentacionObj = rs.getString("presentacion");
                int duracionConsultaObj = rs.getInt("duracionConsulta");
                String claveObj = rs.getString("clave");
                String tipoObj = rs.getString("tipo");
                String nombreObj = rs.getString("nombre");
                String fotoObj = "/Proyecto 1/images/" + rs.getString("foto");
                int codDispObj = rs.getInt("codDisp");
                String diaObj = rs.getString("dia");
                String horaInicioObj = rs.getString("horaInicio");
                String horaFinalObj = rs.getString("horaFinal");
                
                
                if(!listaMedicosFound.isEmpty()){
                    for(Medico m:listaMedicosFound){
                        if(m.getCedula().equals(cedulaObj)){
                            DisponibilidadMedico disponibilidad = new DisponibilidadMedico(codDispObj, cedulaObj, diaObj, horaInicioObj, horaFinalObj);
                            m.getDisponibilidades().put(cedulaObj+codDispObj, disponibilidad);
                            doctorFound = true;
                            break;
                        }
                    }
                    if(!doctorFound){
                        Usuario user = new Usuario(cedulaObj, claveObj, tipoObj, nombreObj, fotoObj);
                        DisponibilidadMedico disponibilidad = new DisponibilidadMedico(codDispObj, cedulaObj, diaObj, horaInicioObj, horaFinalObj);
                        HashMap<String, DisponibilidadMedico> disponibilidades = new HashMap<>();
                        disponibilidades.put(cedulaObj+codDispObj, disponibilidad);

                        //Medico med = new Medico(user, cedulaObj, nombreEspecialidadObj, nombreLocalidadObj, costoConsultaObj, presentacionObj, codDispObj, duracionConsultaObj);
                        Medico med = new Medico(user, cedulaObj, nombreEspecialidadObj, nombreLocalidadObj, costoConsultaObj, presentacionObj, disponibilidades, duracionConsultaObj);
                        listaMedicosFound.add(med);
                    }
                } else{
                    Usuario user = new Usuario(cedulaObj, claveObj, tipoObj, nombreObj, fotoObj);
                    DisponibilidadMedico disponibilidad = new DisponibilidadMedico(codDispObj, cedulaObj, diaObj, horaInicioObj, horaFinalObj);
                    HashMap<String, DisponibilidadMedico> disponibilidades = new HashMap<>();
                    disponibilidades.put(cedulaObj+codDispObj, disponibilidad);
                    Medico med = new Medico(user, cedulaObj, nombreEspecialidadObj, nombreLocalidadObj, costoConsultaObj, presentacionObj, disponibilidades, duracionConsultaObj);
                    listaMedicosFound.add(med);
                }
                
                
                
                //}
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaMedicosFound;
    }
    /*
    public List<Cita> calculateAppointmentsForDoc(DisponibilidadMedico disp, int duracionConsulta){
        int horaInicioInt = disp.getHoraInicio();
        int horaFinalInt = disp.getHoraFinal();
        
        LocalTime horaInicio = parse(Integer.toString(horaInicioInt));
    }
    */
    public List<Cita> citasFindEstado(String estado) throws Exception{
        List<Cita> listaCitasFoundEstado = new ArrayList<>();
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM CITA AS C INNER JOIN Usuario AS U ON C.paciente = U.cedula WHERE estado LIKE '" + estado + "' AND U.tipo LIKE 'Paciente'");
            
        try{
            while(rs.next()){
                //if(rs.getString("cedula").equals(cedula) && rs.getString("nombreEspecialidad").equals(especialidad) && rs.getString("nombreLocalidad").equals(localidad)){
                int idCitaObj = rs.getInt("idCita");
                String cedulaObj = rs.getString("cedula");
                String fechaCitaObj = rs.getString("fechaCita");
                String estadoObj = rs.getString("estado");
                String claveObj = rs.getString("clave");
                String tipoObj = rs.getString("tipo");
                String nombreObj = rs.getString("nombre");
                String fotoObj = "/Proyecto 1/images/" + rs.getString("foto");
                
                Usuario user = new Usuario(cedulaObj, claveObj, tipoObj, nombreObj, fotoObj);
                
                Paciente paciente = new Paciente(user);

                Cita cita = new Cita(idCitaObj, paciente, fechaCitaObj, estadoObj);

                listaCitasFoundEstado.add(cita);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
        return listaCitasFoundEstado;
    }
    
    
    public List<Cita> citasFindPaciente(String nombrePaciente) throws Exception{
        List<Cita> listaCitasFoundNombre = new ArrayList<>();
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT * FROM CITA AS C INNER JOIN Usuario AS U ON C.paciente = U.cedula WHERE U.nombre LIKE '" + nombrePaciente + "' AND U.tipo LIKE 'Paciente'");
            
        try{
            while(rs.next()){
                int idCitaObj = rs.getInt("idCita");
                String cedulaObj = rs.getString("cedula");
                String fechaCitaObj = rs.getString("fechaCita");
                String estadoObj = rs.getString("estado");
                String claveObj = rs.getString("clave");
                String tipoObj = rs.getString("tipo");
                String nombreObj = rs.getString("nombre");
                String fotoObj = "/Proyecto 1/images/" + rs.getString("foto");
                
                Usuario user = new Usuario(cedulaObj, claveObj, tipoObj, nombreObj, fotoObj);
                
                Paciente paciente = new Paciente(user);

                Cita cita = new Cita(idCitaObj, paciente, fechaCitaObj, estadoObj);

                listaCitasFoundNombre.add(cita);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
        return listaCitasFoundNombre;
    }
    
    public int agregaDisponibilidadesCreaID(String cedulaMedico, String dia, String horaInicio, String horaFinal){
        
        //medicos.add(medico); TO COMPLETE WITH THIS
        
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        sqlExecutorURL.ejecutaSQLUpdate("INSERT DispMedico (idMedico, dia, horaInicio, horaFinal) VALUES ('" + cedulaMedico + "', '" + dia + "', " + horaInicio + ", " + horaFinal + ")");
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT D.codDisp FROM DispMedico AS D WHERE D.idMedico LIKE '" + cedulaMedico + "' AND D.dia LIKE '" + dia + "' AND D.horaInicio LIKE " + horaInicio + " AND D.horaFinal LIKE " + horaFinal+ "");
        try {
            return rs.getInt("codDisp");
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int agregaDisponibilidades(int idMedico, String cedulaMedico, String dia, String horaInicio, String horaFinal){
        
        //medicos.add(medico); TO COMPLETE WITH THIS
        
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "CITAS_MEDICAS", "root", "root");
        sqlExecutorURL.abreConexion();
        sqlExecutorURL.ejecutaSQLUpdate("INSERT DispMedico (idMedico, dia, horaInicio, horaFinal) VALUES ('" + cedulaMedico + "', '" + dia + "', " + horaInicio + ", " + horaFinal + ")");
        ResultSet rs = sqlExecutorURL.ejecutaSQL("SELECT D.codDisp FROM DispMedico AS D WHERE D.idMedico LIKE '" + cedulaMedico + "' AND D.dia LIKE '" + dia + "' AND D.horaInicio LIKE " + horaInicio + " AND D.horaFinal LIKE " + horaFinal+ "");
        try {
            return rs.getInt("codDisp");
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    public void setEspecialidades(List<String> especialidades)
    {
        this.especialidades = especialidades;
    }
    
    public static void main(String args[]){

        LocalTime test = parse("07:00");

        System.out.println(test);
    }
    
    /*public int codigo()
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
    }*/
    
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
    
    /*public boolean existeCodigo(int codigo)
    {
        for(Medico m: medicos)
        {
            if(m.getCodDisp() == codigo)
            {
                return true;
            }
        }
        return false;
    }*/
    
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