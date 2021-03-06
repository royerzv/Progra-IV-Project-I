package citas.presentation.usuario.administrador.ciudades;

import citas.logic.Service;
import citas.logic.Usuario;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "UsuarioAdministradorCiudadesController", urlPatterns = {"/presentacion/usuario/administrador/ciudades/show","/presentacion/usuario/administrador/ciudades/registrarC"})
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
            case "/presentacion/usuario/administrador/ciudades/show":
                viewUrl = this.show(request);
            break;
            case "/presentacion/usuario/administrador/ciudades/registrarC":
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
        Model model = (Model) request.getAttribute("model");
        Service service = Service.instance();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        try
        {     
            model.setCurrent(usuario);
            model.setCiudades(service.getCiudades());
            return "/presentacion/usuario/administrador/ciudades/View.jsp";
        }
        catch (Exception ex) { return ""; }
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
                return "/presentacion/usuario/administrador/ciudades/show";
            }
        }catch (Exception ex) { return ""; }
    }
    
    public String registrarAction(HttpServletRequest request)
    {
        Service service = Service.instance();
        Model model = (Model) request.getAttribute("model");
        service.setCiudades(request.getParameter("ciudadFld"));
        return "/presentacion/usuario/administrador/ciudades/View.jsp";
    }
    
    Map<String,String> validar(HttpServletRequest request)
    {
        Map<String,String> errores = new HashMap<>();
        Service service = Service.instance();
        String ciudad = request.getParameter("ciudadFld");
        List<String> c = service.getCiudades();
        if(ciudad.equals(""))
        {
            errores.put("ciudadFld","Ciudad requerida");
        }
        else
        {
            int tamano = c.size();
            for(int i=0 ; i< tamano; i++)
            {
                String cMin = c.get(i).toLowerCase();
                String ciudadMin = ciudad.toLowerCase();
                if(cMin.equals(ciudadMin))
                {
                    errores.put("ciudadFld","Ciudad ya registrada");
                } 
            }
        }
        return errores;
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
