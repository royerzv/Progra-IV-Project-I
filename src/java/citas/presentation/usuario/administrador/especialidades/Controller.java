package citas.presentation.usuario.administrador.especialidades;

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


@WebServlet(name = "UsuarioAdministradorEspecialidadesController", urlPatterns = {"/presentacion/usuario/administrador/especialidades/show" , "/presentacion/usuario/administrador/especialidades/registrarE"})
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
            case "/presentacion/usuario/administrador/especialidades/show":
                viewUrl = this.show(request);
            break;
            case "/presentacion/usuario/administrador/especialidades/registrarE":
                viewUrl = this.registrar(request);
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
            model.setEspecialidades(service.getEspecialidades());
            return "/presentacion/usuario/administrador/especialidades/View.jsp";
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
                return "/presentacion/usuario/administrador/especialidades/show";
            }
        }catch (Exception ex) { return ""; }
    }
    
    public String registrarAction(HttpServletRequest request)
    {
        Service service = Service.instance();
        Model model = (Model) request.getAttribute("model");
        service.setEspecialidades(request.getParameter("especialidadFld"));
        return "/presentacion/usuario/administrador/especialidades/View.jsp";
    }
    
    Map<String,String> validar(HttpServletRequest request)
    {
        Map<String,String> errores = new HashMap<>();
        Service service = Service.instance();
        String especialidades = request.getParameter("especialidadFld");
        List<String> e = service.getEspecialidades();
        if(especialidades.equals(""))
        {
            errores.put("especialidadFld","Ciudad requerida");
        }
        else
        {
            int tamano = e.size();
            for(int i=0 ; i< tamano; i++)
            {
                String eMin = e.get(i).toLowerCase();
                String especialidadMin = especialidades.toLowerCase();
                if(eMin.equals(especialidadMin))
                {
                    errores.put("especialidadFld","Ciudad ya registrada");
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