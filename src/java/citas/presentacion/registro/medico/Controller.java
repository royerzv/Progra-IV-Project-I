package citas.presentacion.registro.medico;

import citas.logic.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig()
@WebServlet(name = "registroMedicoController", urlPatterns = {"/presentacion/registro/medico/show","/presentacion/registro/medico/registrar"})
public class Controller extends HttpServlet 
{
     private final File uploads = new File("J:/UNA/Ing_Sistemas/Progra IV/Proyectos/Proyecto 1/Proyecto 1 v3/web/images/");
    private String[] extens = {".png" , ".jpg" , ".jpeg"};
    protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException
    {
        request.setAttribute("model", new Model());
        
        String viewUrl="";     
        switch (request.getServletPath())
        {
            case "/presentacion/registro/medico/show":
                viewUrl = this.show(request);
            break;
            case "/presentacion/registro/medico/registrar":
                viewUrl =  this.registrar(request);
            break;
        }          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
    }

    public String show(HttpServletRequest request)
    {
        return this.showAction(request);
    }
    
    public String showAction(HttpServletRequest request)
    {
        Model model= (Model) request.getAttribute("model");
        model.getCurrent().getUsuario().setCedula("");
        model.getCurrent().getUsuario().setClave("");
        model.getCurrent().getUsuario().setNombre("");
        model.getCurrent().getUsuario().setTipo("Medico");
        model.getCurrent().setCostoConsulta(0);
        model.getCurrent().setPresentacion("");
        model.getCurrent().setDuracionConsulta(0);
        return "/presentacion/registro/medico/View.jsp";
    }
    
    public String registrar(HttpServletRequest request)
    {
        try
        {
            Map<String,String> errores =  this.validar(request);
            if(errores.isEmpty())
            {
                return this.registrarAction(request);
            }
            else
            {
                request.setAttribute("errores", errores);
                return "/presentacion/registro/medico/View.jsp";
            }
        }catch (Exception ex) { return "/presentacion/Error.jsp"; }
    }
    
    public String registrarAction(HttpServletRequest request)
    {
       Service service = Service.instance();
        try
        {
            Part part = request.getPart("file");
            if(part == null)
            {  
                return "/presentacion/Error.jsp";
            }
            if(isExtension(part.getSubmittedFileName(),extens))
            {
                String nameFile =  saveFile(part,uploads);
                Usuario usuario = new Usuario(request.getParameter("cedulaFld"),request.getParameter("claveFld"),"Medico",request.getParameter("nombreFld"),nameFile);
                service.agregarCliente(usuario);
                HashMap<String, DisponibilidadMedico> disponibilidades = new HashMap<>();
                Medico medico = new Medico(usuario,usuario.getCedula(),request.getParameter("especialidad"),request.getParameter("ciudad"),Integer.valueOf(request.getParameter("costoFld")),
                request.getParameter("presentacionFld"),disponibilidades,Integer.valueOf(request.getParameter("duracionFld")));
                               
                for(String dia:request.getParameterValues("dias")){
                    DisponibilidadMedico disponibilidad = new DisponibilidadMedico(medico.getCedula(), dia, Integer.parseInt(request.getParameter("horaInicioFld")), Integer.parseInt(request.getParameter("horaFinalFld")));
                    medico.getDisponibilidades().put(medico.getCedula()+disponibilidad.getCodigoDisponibilidad(), disponibilidad);
                }
                
                
                
                /*public Medico(Usuario usuario, String cedula, String nombreEspecialidad, String nombreLocalidad, int costoConsulta, String presentacion, 
                        HashMap<String, DisponibilidadMedico> disponibilidades, int duracionConsulta)*/
                
                service.agregarMedico(medico);
                return "/presentacion/login/show";
            }
            return "/presentacion/Error.jsp";
        }
        catch(Exception e) 
        {
            return "/presentacion/Error.jsp"; 
        }
    }
    
    Map<String,String> validar(HttpServletRequest request) throws IOException, ServletException
    {
        Service service = Service.instance();
        Map<String,String> errores = new HashMap<>();
        if (request.getParameter("nombreFld").isEmpty())
        {
            errores.put("nombreFld","Nombre requerida");
        }
        
        if (request.getParameter("cedulaFld").isEmpty())
        {
            errores.put("cedulaFld","Cedula requerida");
        }
        
        if (request.getParameter("claveFld").isEmpty())
        {
            errores.put("claveFld","Clave requerida");
        }
        
        if (request.getParameter("confClaveFld").isEmpty())
        {
            errores.put("confClaveFld","Confirmacion de la clave requerida");
        }
        
        if (!(request.getParameter("confClaveFld").equals(request.getParameter("claveFld"))))
        {
            errores.put("claveFld","No concuerdan las contraseñas");
            errores.put("confClaveFld","No concuerdan las contraseñas");
        }
        
        if (!(request.getParameter("cedulaFld").equals("")) && service.existeUsuario(request.getParameter("cedulaFld")))
        {
            errores.put("cedulaFld","Ya existe un usuario con ese ID requerida");
        }
        
        if (request.getParameter("especialidad").equals("0"))
        {
            errores.put("especialidad","Se requiere una especialidad");
        }
        
        if (request.getParameter("ciudad").equals("0"))
        {
            errores.put("ciudad","Se requiere una ciudad");
        }
        
        if (request.getParameter("costoFld").isEmpty())
        {
            errores.put("costoFld","Costo de consulta requerida");
        }
        
        if (request.getParameter("presentacionFld").isEmpty())
        {
            errores.put("presentacionFld","Presentacion requerida");
        }
        
        if (request.getParameter("duracionFld").isEmpty())
        {
            errores.put("duracionFld","Duracion requerida");
        }
        
        if (0 >= Integer.valueOf(request.getParameter("duracionFld")))
        {
            errores.put("duracionFld","La duracion no es valida");
        }
        
        if(request.getParameterValues("dias") == null)
        {
            errores.put("dias","Dias requerida");
        }
        
        if (request.getParameter("horaInicioFld").isEmpty())
        {
            errores.put("horaInicioFld","Hora de inicio requerida");
        }
        
        if (request.getParameter("horaFinalFld").isEmpty())
        {
            errores.put("horaFinalFld","Hora final requerida");
        }
        
        return errores;
    }
    
    private String saveFile(Part part, File pathUpload)
    {
        String pathAbsolute = "";
        try
        {
            Path path = Paths.get(part.getSubmittedFileName());
            String fileName = path.getFileName().toString();
            InputStream input = part.getInputStream();
            if(input != null)
            {
                File file = new File(pathUpload,fileName);
                pathAbsolute = "/Proyecto 1 v3/images/"+file.getName();
                Files.copy(input, file.toPath());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return pathAbsolute;
    }
    
    private boolean isExtension(String fileName, String[] extensions)
    {
        for(String et: extensions)
        {
            if(fileName.toLowerCase().endsWith(et))
            {
                return true;
            }
        }
        return false;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }// </editor-fold>
}