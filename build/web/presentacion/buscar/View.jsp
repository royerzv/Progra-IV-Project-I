<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="citas.presentation.busqueda.Model"%>
<%@page import="citas.logic.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1/css/style.css" rel="stylesheet" type="text/css" />
        <title>Busqueda de citas</title>
    </head>
    <body>
        <%@ include file="../Header.jsp" %>
        <% Model model= (Model) request.getAttribute("model"); %>
        <% List<Medico> medicos = model.getCurrent();%>
        <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
        <% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>
        <form name="form" action="/Proyecto 1/presentacion/buscar/buscar" method="get"> 
            <div class="fila encabezado">Busqueda</div>
            <div style="display: flex">
                <div style="display: flex; justify-content: space-around; align-items: center; width: 25%;">
                    <img src="../../images/Usuario 2.png" alt="" width="20" height="20"/>
                    <div class="etiqueta">Especialidad</div>
                    <div class="campo"><input class="<%=erroneo("especialidadFld",errores)%>" placeholder="Ej: Cardiologia" type="text" name="especialidadFld" value="" title="<%=title("especialidadFld",errores)%>"></div>
                </div>
                <div style="display: flex; justify-content: space-around; align-items: center; width: 25%">
                    <img src="../../images/password.png" alt="" width="20" height="20"/>
                    <div class="etiqueta">Localidad</div>
                    <div class="campo"><input class="<%=erroneo("localidadFld",errores)%>" placeholder="Ej: San Jose" type="text" name="localidadFld" value="" title="<%=title("localidadFld",errores)%>"></div>
                </div>
                <div style="justify-content: space-around; align-items: center;"><button style="margin-bottom: 15px">Buscar</button> </div>
            </div>
            
        </form>
        <div style="width:100%;margin:auto;">
            <%if(medicos.isEmpty() && model.getEspecialidad() != null && model.getLocalidad() != null && model.getEspecialidad() != "" && model.getLocalidad() != ""){%>
                <h1>No existen medicos con especialidad en <%=model.getEspecialidad()%>, ubicacion <%=model.getLocalidad()%></h1>
            <%}
            else if(!medicos.isEmpty() && model.getEspecialidad() != null && model.getLocalidad() != null){%>
                <h1><%=medicos.size()%> medicos encontrados con especialidad <%=model.getEspecialidad()%> en <%=model.getLocalidad()%></h1>
            <%}%>       
        </div>
        <div style="border: 3px solid black">
            <%for(Medico medico:medicos){%>
                <div style="border: 4px solid green">
                    <div style="border: 4px solid red; width: 10%; height: 25%; display: inline-block ">
                        <img src="<%=medico.getUsuario().getFoto()%>" style="width:70%; height:71%">
                    </div>
                    <div style="border: 4px solid blue; display: inline-block; position: relative; ">
                        <div style="border: 4px solid yellow; ">
                            <h2><%=medico.getUsuario().getNombre()%></h2>
                        </div>
                        <div style="border: 4px solid turquoise; ">
                            <h2><%=medico.getNombreEspecialidad()%></h2>
                        </div>
                        <div style="border: 4px solid brown; ">
                            <p><b>Comentarios:</b> <%=medico.getPresentacion()%></p>
                        </div>
                    </div>
                    <%for(DisponibilidadMedico disponibilidad:medico.getDisponibilidades().values()){%>
                        <div style="border: 4px solid rosybrown; display: inline-block; position: relative; width: 10%; height: 15%;">
                            <div style="border: 4px solid teal">
                                <%=disponibilidad.getDia()%>
                            </div>
                            <div style="border: 4px solid indigo">
                                
                            </div>
                        </div>
                    <%}%>
                </div>
            <%}%>
        </div>
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
       values.put("especialidadFld", new String[]{model.getEspecialidad()});
       values.put("localidadFld", new String[]{model.getLocalidad()});
       return values;
    }    
%> 
