package citas.presentation.usuario.administrador.controlMedicos;

import citas.logic.Service;
import citas.logic.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "UsuarioAdministradorControlMedicosController", urlPatterns = {"/presentacion/usuario/administrador/controlMedicos/show","/presentacion/usuario/administrador/controlMedicos/activar"})
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
            case "/presentacion/usuario/administrador/controlMedicos/show":
                viewUrl = this.show(request);
            break;  
            case "/presentacion/usuario/administrador/controlMedicos/activar":
                viewUrl = this.activar(request);
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
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        try
        {        
            model.setCurrent(usuario);
            return "/presentacion/usuario/administrador/controlMedicos/View.jsp";
        }
        catch (Exception ex) { return ""; }
    }
    
    public String activar(HttpServletRequest request)
    {
        return this.activarAction(request);
    }
    
    public String activarAction(HttpServletRequest request)
    {
        Service service = Service.instance();
        service.activarMedico(request.getParameter("idMedico"));
        return "/presentacion/usuario/administrador/controlMedicos/View.jsp";
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