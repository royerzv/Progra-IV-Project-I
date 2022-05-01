package citas.presentation.usuario.datos;

import citas.logic.Service;
import citas.logic.Usuario;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "UsuarioDatosController", urlPatterns = {"/presentacion/usuario/datos/show","/presentacion/usuario/datos/update"})
public class Controller extends HttpServlet 
{
  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException
    {
        request.setAttribute("model", new Model());
        
        String viewUrl="";     
        switch (request.getServletPath())
        {
            case "/presentacion/usuario/datos/show":
                viewUrl = this.show(request);
            break;  
            case "/presentacion/usuario/datos/update":
                viewUrl = this.update(request);
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
        Model model = (Model) request.getAttribute("model");
        Service service = Service.instance();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        try
        {        
            model.setCurrent(usuario);
            return "/presentacion/usuario/datos/View.jsp";
        }
        catch (Exception ex) { return ""; }
    }
    
    private String update(HttpServletRequest request)
    { 
        try
        {
            Model model = (Model) request.getAttribute("model");
            HttpSession session = request.getSession(true);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            model.getCurrent().setCedula(usuario.getCedula());
            Map<String,String> errores =  this.validar(request);
            if(errores.isEmpty())
            {
                this.updateModel(request);          
                return this.updateAction(request);
            }
            else
            {
                request.setAttribute("errores", errores);
                return "/presentacion/usuario/datos/View.jsp"; 
            }            
        }
        catch(Exception e)
        {
            return "/presentacion/Error.jsp";             
        }         
    }
    
    Map<String,String> validar(HttpServletRequest request)
    {
        Map<String,String> errores = new HashMap<>();
        if (request.getParameter("nombreFld").isEmpty())
        {
            errores.put("nombreFld","Nombre requerido");
        }
        return errores;
    } 
    
    void updateModel(HttpServletRequest request)
    {
       citas.presentation.usuario.datos.Model model= (citas.presentation.usuario.datos.Model) request.getAttribute("model");
       
        model.getCurrent().setNombre(request.getParameter("nombreFld"));
    }
    
    public String updateAction(HttpServletRequest request) 
    {
        citas.presentation.usuario.datos.Model model= (citas.presentation.usuario.datos.Model) request.getAttribute("model");
        Service  service = Service.instance();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.getCurrent().setCedula(usuario.getCedula());
        try
        {
            service.clienteUpdate(model.getCurrent());
            return "/presentacion/Index.jsp";
        } catch (Exception ex) {
            Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
            errores.put("nombreFld","cedula o nombreincorrectos");
            return "/presentacion/usuario/datos/View.jsp"; 
        }        
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