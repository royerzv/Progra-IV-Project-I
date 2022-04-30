package citas.presentation.login;

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

@WebServlet(name = "LoginController", urlPatterns = {"/presentacion/login/show","/presentacion/login/login","/presentacion/login/logout"})
public class Controller extends HttpServlet
{
  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException 
    {
        request.setAttribute("model", new Model()); 
        
        String viewUrl="";
        switch(request.getServletPath())
        {
            case "/presentacion/login/show":
                viewUrl=this.show(request);
            break;              
            case "/presentacion/login/login":
                viewUrl=this.login(request);
            break;            
            case "/presentacion/login/logout":
                viewUrl=this.logout(request);
            break;
        }
        request.getRequestDispatcher(viewUrl).forward( request, response); 
    }

    private String login(HttpServletRequest request)
    {
        try
        {
            Map<String,String> errores =  this.validar(request);
            if(errores.isEmpty())
            {
                this.updateModel(request);          
                return this.loginAction(request);
            }
            else
            {
                request.setAttribute("errores", errores);
                return "/presentacion/login/View.jsp"; 
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
        if (request.getParameter("cedulaFld").isEmpty())
        {
            errores.put("cedulaFld","Cedula requerida");
        }

        if (request.getParameter("claveFld").isEmpty())
        {
            errores.put("claveFld","Clave requerida");
        }
        
        if (request.getParameter("tipoUsuario") == null)
        {
            errores.put("tipoUsuario", "Tipo de usuario requerido");
        }
        return errores;
    }
    
    void updateModel(HttpServletRequest request)
    {
        Model model= (Model) request.getAttribute("model");
       
        model.getCurrent().setCedula(request.getParameter("cedulaFld"));
        model.getCurrent().setClave(request.getParameter("claveFld"));
        model.getCurrent().setTipo(request.getParameter("tipoUsuario"));
   }
   
    public String loginAction(HttpServletRequest request)
    {
        Model model= (Model) request.getAttribute("model");
        Service  service = Service.instance();
        HttpSession session = request.getSession(true);
        try 
        {
            Usuario real = service.usuarioFind(model.getCurrent().getCedula(),model.getCurrent().getClave(),model.getCurrent().getTipo());
            session.setAttribute("usuario", real);
            String viewUrl="";
            switch(real.getTipo())
            {
                case "cliente":
                    viewUrl = "/presentacion/Index.jsp";
                break;
                case "doctor":
                    viewUrl = "/presentacion/buscar/View.jsp";
                break;
                case "administrador":
                    viewUrl = "/presentacion/usuario/administrador/show";
                break;        
            }
            return viewUrl;
        }
        catch (Exception ex) 
        {
            Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
            errores.put("cedulaFld","Usuario, tipo de usuario o clave incorrectos");
            errores.put("claveFld","Usuario, tipo de usuario o clave incorrectos");
            errores.put("errorFld","Usuario, tipo de usuario o clave incorrectos");
            errores.put("tipoUsuario", "Usuario, tipo de usuario o clave incorrectos");
            return "/presentacion/login/View.jsp"; 
        }        
    }   

    public String logout(HttpServletRequest request)
    {
        return this.logoutAction(request);
    }
    
    public String logoutAction(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.invalidate();
        return "/presentacion/Index.jsp";   
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
        return "/presentacion/login/View.jsp"; 
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
