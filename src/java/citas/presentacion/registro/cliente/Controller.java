package citas.presentacion.registro.cliente;

import citas.logic.Service;
import citas.logic.Usuario;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@MultipartConfig()
@WebServlet(name = "registroClienteController", urlPatterns = {"/presentacion/registro/cliente/show","/presentacion/registro/cliente/registrar"})
public class Controller extends HttpServlet 
{
    private final File uploads = new File("J:/UNA/Ing_Sistemas/Progra IV/Proyectos/Proyecto 1/Proyecto 1 v3/web/images/");
    private final String[] extens = {".png" , ".jpg" , ".jpeg"};
    protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException
    {
        request.setAttribute("model", new Model());
        
        String viewUrl="";     
        switch (request.getServletPath())
        {
            case "/presentacion/registro/cliente/show":
                viewUrl = this.show(request);
            break;
            case "/presentacion/registro/cliente/registrar":
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
        model.getCurrent().setCedula("");
        model.getCurrent().setClave("");
        model.getCurrent().setNombre("");
        model.getCurrent().setTipo("cliente");
        return "/presentacion/registro/cliente/View.jsp";
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
                return "/presentacion/registro/cliente/View.jsp";
            }
        }catch (Exception ex) { return "/presentacion/Error.jsp"; }
    }
    
    public String registrarAction(HttpServletRequest request) throws ServletException, IOException
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
                Usuario usuario = new Usuario(request.getParameter("cedulaFld"), request.getParameter("claveFld"),"Paciente", request.getParameter("nombreFld"),nameFile);
                service.agregarCliente(usuario);
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
        Part part = request.getPart("file");
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
        if (part == null)
        {
            errores.put("file","Fotografia requerida");
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