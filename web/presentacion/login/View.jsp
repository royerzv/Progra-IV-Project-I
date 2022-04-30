<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="citas.presentation.login.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1/css/style.css" rel="stylesheet" type="text/css" />
        <title>Login</title>
    </head>
    <body>
        <%@ include file="/presentacion/Header.jsp" %>
        <% Model model= (Model) request.getAttribute("model"); %>
        <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
        <% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>

        <form name="form" action="/Proyecto 1/presentacion/login/login" method="post" > 
            <div class="panel" style="width:30%;">
                <div class="fila encabezado">Login</div>
                <div class="fila">                  
                <div ><label> <%=title("errorFld",errores)%> </label></div>
                </div>
                    <div style="display: flex; justify-content: space-around; align-items: center;">
                        <img src="../../images/Usuario 2.png" alt="" width="20" height="20"/>
                        <div class="etiqueta">Cedula</div>
                        <div class="campo"><input class="<%=erroneo("cedulaFld",errores)%>" placeholder="Cedula del usuario" type="text" name="cedulaFld" value="<%=form.get("cedulaFld")[0]%>" title="<%=title("cedulaFld",errores)%>"></div>
                    </div>
                    <div style="display: flex; justify-content: space-around; align-items: center;">
                        <img src="../../images/password.png" alt="" width="20" height="20"/>
                        <div class="etiqueta">Clave</div>
                        <div class="campo"><input class="<%=erroneo("claveFld",errores)%>" placeholder="Clave del usuario" type="password" name="claveFld" value="<%=form.get("claveFld")[0]%>" title="<%=title("claveFld",errores)%>"></div>
                    </div>
                    <div style="display: flex; justify-content: space-around; align-items: center;">
                        <img src="../../images/Usuario 1.png" alt="" width="20" height="20"/>
                        <input type="radio" id="cliente" name="tipoUsuario" value="cliente">
                        <label for="cliente">Cliente</label>
                        <img src="../../images/Doctor.png" alt="" width="20" height="20"/>
                        <input type="radio" id="doctor" name="tipoUsuario" value="doctor">
                        <label for="doctor">Doctor</label>
                        <img src="../../images/Administrador.png" alt="" width="20" height="20"/>
                        <input type="radio" id="administrador" name="tipoUsuario" value="administrador">
                        <label for="administrador">Administrador</label>
                    </div>
                <div class="fila encabezado"><button  style="margin-bottom: 15px">Ingresar</button> </div>
                <a href="/Proyecto 1/presentacion/usuario/registro/show" style="color: rgb(39, 77, 122); text-decoration: none;">>>>Si aun no tiene cuenta se puede registrar aquí<<<</a>
            </div>
        </form>
        <%@ include file="../Footer.jsp" %> 
    </body>
</html>
<%!
    private String erroneo(String campo, Map<String,String> errores)
    {
      if ( (errores!= null) && (errores.get(campo)!=null) )
        return "is-invalid";
      else
        return "";
    }

    private String title(String campo, Map<String,String> errores)
    {
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return errores.get(campo);
      else
        return "";
    }

    private Map<String,String[]> getForm(Model model)
    {
       Map<String,String[]> values = new HashMap<>();
       values.put("cedulaFld", new String[]{model.getCurrent().getCedula()});
       values.put("claveFld", new String[]{model.getCurrent().getClave()});
       //values.put("tipoUsuario", new String[]{model.getCurrent().getTipo()});
       return values;
    }
    
%> 
