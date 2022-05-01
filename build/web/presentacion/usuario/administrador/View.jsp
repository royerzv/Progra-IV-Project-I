<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1/css/style.css" rel="stylesheet" type="text/css" />
        <title>Administrador</title>
    </head>
    <body>
        <%@ include file="/presentacion/Header.jsp" %>
        <br></br>
        <br></br>
        <br></br>
        <div style="display: flex; justify-content: space-around;">
            <div>
                <p style="text-align:center;">
                    <a href="/Proyecto 1/presentacion/usuario/administrador/controlMedicos/show" style="color: rgb(5, 90, 90); text-decoration: none;">
                        <img src="/Proyecto 1/images/doctores.png" alt="" width="250" height="250"/>
                        <br>Listar médicos</br>
                    </a>
                </p>
            </div>
            <div style="align-items: center;">
                <p style="text-align:center;">
                    <a href="/Proyecto 1/presentacion/usuario/administrador/especialidades/show" style="color: rgb(5, 90, 90); text-decoration: none;">
                        <img src="/Proyecto 1/images/especialidades.JPG" alt="" width="250" height="250"/>
                        <br>Listado y registro de especialidades</br>
                    </a>
                </p>
            </div>
            <div style="align-items: center;">
                <p style="text-align:center;">
                    <a href="/Proyecto 1/presentacion/usuario/administrador/ciudades/show" style="color: rgb(5, 90, 90); text-decoration: none;">
                        <img src="/Proyecto 1/images/ciudades.png" alt="" width="250" height="250"/>
                        <br>Listado y registro de ciudades</br>
                    </a> 
                </p>    
            </div>
        </div>
        <%@ include file="/presentacion/Footer.jsp" %>
    </body>
</html>

