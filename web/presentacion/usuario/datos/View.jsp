<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="citas.presentation.usuario.datos.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1 v3/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1 v3/css/style.css" rel="stylesheet" type="text/css" />
        <title>Datos</title> 
    </head>
    
        <%@ include file="/presentacion/Header.jsp" %>
        <% Model model= (Model) request.getAttribute("model"); %>
        <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
        <% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>
        
        <form name="form" action="/Proyecto 1 v3/presentacion/usuario/datos/update" method="post" >
            <div class="panel" style="width:30%;">
                <div class="fila encabezado">Datos</div>
                
                    <div ><img src="<%=model.getCurrent().getFoto()%>" style="margin-left: 100px; height: 100px;"></div>
                    <div class="etiqueta">Nombre</div>
                  <div class="campo"><input class="<%=erroneo("nombreFld",errores)%>" placeholder="Nombre del usuario" type="text" name="nombreFld" value="<%=form.get("nombreFld")[0]%>" title="<%=title("nombreFld",errores)%>"></div>
                
              <div class="fila encabezado"><button  style="margin-bottom: 15px">Actualizar</button> </div>
            </div>
        </form>   
        <%@ include file="/presentacion/Footer.jsp" %>


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
       values.put("cedulaFld", new String[]{model.getCurrent().getCedula()});
       values.put("nombreFld", new String[]{model.getCurrent().getNombre()});
       return values;
    }
%> 