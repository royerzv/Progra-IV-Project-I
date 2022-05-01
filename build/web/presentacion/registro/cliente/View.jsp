<%@page import="java.util.HashMap"%>
<%@page import="citas.presentacion.registro.cliente.Model"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1/css/style.css" rel="stylesheet" type="text/css" />
        <title>Registro de paciente</title>
    </head>
    <body>
        <%@ include file="/presentacion/Header.jsp" %>
        <% Model model= (Model) request.getAttribute("model"); %>
        <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
        <% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>
        <form name="form" action="/Proyecto 1/presentacion/registro/cliente/registrar" method="post" enctype="multipart/form-data">
            <div class="panel" style="width:60%; justify-content: space-around; align-items: center;">
                <div class="encabezado">Registro de Paciente</div>
                <div ><label> <%=title("errorFld",errores)%> </label></div>
                <br></br>
                <div style="display: flex; justify-content: space-around; align-items: center;">
                    <div class="etiqueta">Nombre</div>
                    <div class="campo"><input class="<%=erroneo("nombreFld",errores)%>" placeholder="Nombre del usuario" type="text" name="nombreFld" value="<%=form.get("nombreFld")[0]%>" title="<%=title("nombreFld",errores)%>"></div>
                </div> 
                <br></br>
                <div style="display: flex; justify-content: space-around; align-items: center;">
                    <div class="etiqueta">Cedula</div>
                    <div class="campo"><input class="<%=erroneo("cedulaFld",errores)%>" placeholder="Cedula del usuario" type="text" name="cedulaFld" value="<%=form.get("cedulaFld")[0]%>" title="<%=title("cedulaFld",errores)%>"></div>
                </div>
                <br></br>
                <div style="display: flex; justify-content: space-around; align-items: center;">
                    <div class="etiqueta">Clave</div>
                    <div class="campo"><input class="<%=erroneo("claveFld",errores)%>" placeholder="Clave" type="text" name="claveFld" value="<%=form.get("claveFld")[0]%>" title="<%=title("claveFld",errores)%>"></div>
                </div>
                <br></br>
                <div style="display: flex; justify-content: space-around; align-items: center;">
                    <div class="etiqueta">Confirmació de la clave</div>
                    <div class="campo"><input class="<%=erroneo("confClaveFld",errores)%>" placeholder="Confirmacion de clave" type="text" name="confClaveFld" value="<%=form.get("confClaveFld")[0]%>" title="<%=title("confClaveFld",errores)%>"></div>
                </div>
                <br></br>
                <div style="display: flex; justify-content: space-around; align-items: center;">
                    <div class="etiqueta">Fotografia</div>
                    <div class="campo"><input type="file" name="file" id="fil1"></div>
                </div>
                <br></br>
                <div class="fila encabezado"><button  style="margin-bottom: 15px">Registrar</button> </div>
            </div>    
        </form>
        <%@ include file="/presentacion/Footer.jsp" %>
    </body>
</html>

<%!
    private String erroneo(String campo, Map<String,String> errores)
    {
      if ( (errores!=null) && (errores.get(campo)!=null) )
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
        values.put("nombreFld", new String[]{model.getCurrent().getCedula()});
        values.put("cedulaFld", new String[]{model.getCurrent().getCedula()});
        values.put("claveFld", new String[]{model.getCurrent().getCedula()});
        values.put("confClaveFld", new String[]{model.getCurrent().getCedula()});
        return values;
    }
%>