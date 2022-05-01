
package citas.presentation.usuario.medico.gestioncita;

import citas.logic.Service;
import citas.logic.Usuario;
import citas.presentation.usuario.medico.gestioncita.Model;
import citas.logic.Cita;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

                                              
@WebServlet(name = "GestionCitaController", urlPatterns = {"/presentacion/usuario/medico/gestioncita/show", "/presentacion/usuario/medico/gestioncita/buscar"})
public class Controller extends HttpServlet{

    protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException 
    {
        request.setAttribute("model", new Model()); 
        
        String viewUrl="";
        switch(request.getServletPath())
        {
            case "/presentacion/usuario/medico/gestioncita/show":
                viewUrl=this.show(request);
            break; 
                case "/presentacion/usuario/medico/gestioncita/buscar":
                viewUrl=this.update(request);
            break;


        }
        request.getRequestDispatcher(viewUrl).forward( request, response); 
    }
    
    private String update(HttpServletRequest request)
    { 
        try
        {
            Model model= (Model) request.getAttribute("model");
            HttpSession session = request.getSession(true);
            Service  service = Service.instance();
            if(request.getParameter("estadoFld") == "Completo" || request.getParameter("estadoFld") == "Pendiente" || request.getParameter("estadoFld") == "En curso"){
                List<Cita> citasRealesEstado = service.citasFindEstado(request.getParameter("estadoFld"));
                session.setAttribute("citasFoundEstado", citasRealesEstado);
                this.updateModel(request);          
                //return this.updateAction(request);
                return "/presentacion/usuario/medico/gestioncita/show";
            }else{
                System.out.print("ANTESSSS");
                List<Cita> citasRealesNombre = service.citasFindPaciente(request.getParameter("nombrePacienteFld"));
                session.setAttribute("citasFoundNombrePaciente", citasRealesNombre);
                this.updateModel(request);          
                return "/presentacion/usuario/medico/gestioncita/show";
            }      
        }
        catch(Exception e)
        {
            return "/presentacion/Error.jsp";             
        }         
    }
    
    void updateModel(HttpServletRequest request) throws Exception
    {
        Model model= (Model) request.getAttribute("model");
        Service  service = Service.instance();
        List<Cita> citasRealesEstado = service.citasFindEstado(request.getParameter("estadoFld"));
        List<Cita> citasRealesNombre = service.citasFindPaciente(request.getParameter("nombrePacienteFld"));
       
        if(!citasRealesEstado.isEmpty()){
            model.setListaCitasEstado(citasRealesEstado);
            model.getCita().setEstado(request.getParameter("estadoFld"));
        }else{
            model.setListaCitasNomPaciente(citasRealesNombre);
            model.getCurrentPaciente().setNombre(request.getParameter("nombrePacienteFld")); 
        }
    }
    

    public String showAction(HttpServletRequest request)
    {
        System.out.print("Holaaaa");
        Model model = (Model) request.getAttribute("model");
        Service service = Service.instance();
        HttpSession session = request.getSession(true);
        List<Cita> citasRealesEstado = (List<Cita>) session.getAttribute("citasFoundEstado");
        List<Cita> citasRealesNombre = (List<Cita>) session.getAttribute("citasFoundNombrePaciente");
        try
        {   
            if(!citasRealesEstado.isEmpty()){
                model.setListaCitasEstado(citasRealesEstado);

            }else if(!citasRealesNombre.isEmpty()){
                model.setListaCitasNomPaciente(citasRealesNombre);
                
            }
            model.getCurrentPaciente().setNombre(request.getParameter("nombrePacienteFld"));
            model.getCita().setEstado(request.getParameter("estadoFld"));
            return "/presentacion/medico/View.jsp";
        }
        catch (Exception ex) { return "/presentacion/medico/View.jsp"; }
    }
    
    public String show(HttpServletRequest request)
    {
        return this.showAction(request);
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
