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

    HashMap<String,Usuario> usuarios;
    HashMap<String,Medico> medicos;
    List<String> ciudades;
    List<String> especialidades;
    
    private Service()
    {   
        String path = "/Proyecto 1/images";
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
        especialidades.add("Psicología");
    }

    /*public Usuario usuarioFind(String cedula,String clave,String tipo) throws Exception
    {
        if (usuarios.get(cedula)!= null && usuarios.get(cedula).getClave().equals(clave) && usuarios.get(cedula).getTipo().equals(tipo)) return usuarios.get(cedula);
        else 
        {
            throw new Exception("Usuario no existe");
        }
    }*/
    
    public Usuario usuarioFind(String cedula,String clave, String tipo) throws Exception
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
                int horaInicioObj = rs.getInt("horaInicio");
                int horaFinalObj = rs.getInt("horaFinal");
                
                
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
    
    public void clienteUpdate(Usuario usuario) throws Exception
    {
        if (usuario.getCedula()==null) 
            throw new Exception("Cliente no existe");
        else
        {
            usuarios.get(usuario.getCedula()).setNombre(usuario.getNombre());
        }
    }
    
    public List<String> getCiudades()
    {
        return ciudades;
    }
    
    public void setCiudades(List<String> ciudades)
    {
        this.ciudades = ciudades;
    }
    
    public List<String> getEspecialidades()
    {
        return especialidades;
    }
    
    public void setEspecialidades(List<String> especialidades)
    {
        this.especialidades = especialidades;
    }
    
    public static void main(String args[]){

        LocalTime test = parse("07:00");

        System.out.println(test);
    }
}
