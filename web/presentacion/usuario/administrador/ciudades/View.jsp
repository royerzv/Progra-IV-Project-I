<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="citas.presentation.usuario.administrador.ciudades.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Model model = (Model) request.getAttribute("model");
    List<String> ciudades = model.getCiudades();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1 v3/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1 v3/css/style.css" rel="stylesheet" type="text/css" />
        <title>Listado y Registro de Ciudades</title>
    </head>
    <body>
        <%@ include file="/presentacion/Header.jsp" %>
        <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
        <% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>
        <div class="encabezado">Menu de ciudades</div>
        <div style="display: flex; justify-content: space-around;">
            <div style="border: 2px solid #003333;">
                <H1>Lista de ciudades registradas</H1>
                <table>
                    <thead>
                        <tr> 
                            <td>Nombre de la ciudad</td>
                        </tr>
                    </thead>
                    <tbody>
                       <% for(String c:ciudades){%>
                       <tr>
                           <td><%=c%></td>
                       </tr>
                       <%}%>
                    </tbody>
                </table>
            </div>
            <div>
                <br></br>
                <br></br>
                <br></br>
                <br></br>
                <br></br>
                <form name="form" action="/Proyecto 1 v3/presentacion/usuario/administrador/ciudades/registrarC" method="post">
                    <div style="width: 600px; height:200px;">
                        <div class="fila encabezado">Registro de ciudad</div>
                        <div class="fila">                  
                            <div ><label> <%=title("errorFld",errores)%> </label></div>
                        </div>
                        <div style="display: flex; justify-content: space-around; align-items: center; height:50px;">
                            <div>Nombre de la ciudad</div>
                            <div>
                                <input class="<%=erroneo("ciudadFld",errores)%>" placeholder="Ciudad" type="text" name="ciudadFld" value="<%=form.get("ciudadFld")[0]%>" title="<%=title("ciudadFld",errores)%>">
                            </div>
                        </div>
                        <div class="fila encabezado"><button  style="margin-bottom: 15px">Registrar ciudad</button> </div>
                    </div>
                </form>
            </div>
        </div>
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
       values.put("ciudadFld", new String[]{""});
       return values;
    }
%>

