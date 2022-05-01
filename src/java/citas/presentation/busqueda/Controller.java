/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package citas.presentation.busqueda;

import citas.logic.Service;
import citas.logic.Usuario;
import citas.logic.Medico;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "BusquedaController", urlPatterns = {"/presentacion/buscar/show", "/presentacion/buscar/buscar"})
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
            case "/presentacion/buscar/show":
                viewUrl = this.show(request);
            break;  
            case "/presentacion/buscar/buscar":
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
        List<Medico> medicos = (List<Medico>) session.getAttribute("medicosFound");
        try
        {   
            if(!medicos.isEmpty()){
                model.setCurrent(medicos);
            }
            model.setEspecialidad(request.getParameter("especialidadFld"));
            model.setLocalidad(request.getParameter("localidadFld"));
            return "/presentacion/buscar/View.jsp";
        }
        catch (Exception ex) { return "/presentacion/buscar/View.jsp"; }
    }
    
    private String update(HttpServletRequest request)
    { 
        try
        {
            Model model= (Model) request.getAttribute("model");
            HttpSession session = request.getSession(true);
            Service  service = Service.instance();
            List<Medico> reales = service.medicosFind(request.getParameter("especialidadFld"), request.getParameter("localidadFld"));
            session.setAttribute("medicosFound", reales);
            
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            //model.getCurrent().setCedula(usuario.getCedula());
            /*Map<String,String> errores =  this.validar(request);
            if(errores.isEmpty())
            {*/
                this.updateModel(request);          
                //return this.updateAction(request);
                return "/presentacion/buscar/show";
            /*}
            else
            {
                request.setAttribute("errores", errores);
                return "/presentacion/usuario/datos/View.jsp"; 
            }    */        
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
    
    void updateModel(HttpServletRequest request) throws Exception
    {
        Model model= (Model) request.getAttribute("model");
        Service  service = Service.instance();
        List<Medico> reales = service.medicosFind(request.getParameter("especialidadFld"), request.getParameter("localidadFld"));

        model.setCurrent(reales);
        model.setEspecialidad(request.getParameter("especialidadFld"));
        model.setLocalidad(request.getParameter("localidadFld"));
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
