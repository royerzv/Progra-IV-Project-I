package citas.presentacion.usuario.medico;

import citas.logic.Service;
import citas.logic.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "UsuarioMedicoController", urlPatterns = {"/presentacion/medico/show",})
public class Controller extends HttpServlet 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
