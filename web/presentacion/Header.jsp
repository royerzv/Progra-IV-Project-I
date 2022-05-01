<%@page import="citas.logic.Usuario" contentType="text/html" pageEncoding="UTF-8"%>
<% Usuario usuario=  (Usuario) session.getAttribute("usuario");  %>

<header style="background-color: #B5DADA; height:120px">
    <div class="logo" style="display: flex; justify-content: space-around; align-items: center;">
        <p align="left"><img src="/Proyecto 1 v3/images/medical appointment 3.png" alt="" width="115" height="115"/></p>
        <span>Citas Médicas</span>
        <div class="menu">
            <ul> 
                <li>
                    <a href="/Proyecto 1 v3/presentacion/Index.jsp">Inicio</a>
                </li>
                    <% if (usuario!=null){%>
                        <li >
                            <a  href="/Proyecto 1 v3/presentacion/login/logout">Logout</a>
                        </li> 
                        <li>
                            <a  href="/Proyecto 1 v3/presentacion/usuario/datos/show">User: <%=usuario.getNombre()%></a>
                        </li>
                        <% if (usuario.getTipo().equals("Paciente")) {%>
                            <li>
                                <a href="/Proyecto 1 v3/presentacion/usuario/citas/show">Citas</a>
                            </li>    
                        <% }%>
                        <% if (usuario.getTipo().equals("Medico")) {%>
                            <li>
                                <a href="/Proyecto 1 v3/presentacion/usuario/citas/show">Citas</a>
                            </li>  
                        <% }%>
                        <% if (usuario.getTipo().equals("Admin")) {%>
                            <li>
                                <a href="/Proyecto 1 v3/presentacion/usuario/administrador/show">Administración</a>
                            </li>  
                        <% }%>
                    <% }%>
                    <% if (usuario==null){%>
                        <li>
                            <a href="/Proyecto 1 v3/presentacion/login/show">Login</a>
                        </li>
                    <% }%>    
                    <li>
                        <a href="/Proyecto 1 v3/presentacion/buscar/View.jsp">Buscar</a>
                    </li>
            </ul>
        </div>
    </div> 
</header>