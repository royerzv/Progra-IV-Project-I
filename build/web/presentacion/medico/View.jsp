<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="citas.logic.Cita"%>
<%@page import="citas.presentation.usuario.medico.gestioncita.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1/css/style.css" rel="stylesheet" type="text/css" />
        <title>Gestion de Citas</title>
    </head>
    <body>
        <%@ include file="/presentacion/Header.jsp" %>
        <% Model model= (Model) request.getAttribute("model"); %>
        <% List<Cita> citasEstado = model.getListaCitasEstado();%>
        <% List<Cita> citasNombrePaciente = model.getListaCitasNomPaciente();%>
        <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>

        <form name="form" action="/Proyecto 1/presentacion/usuario/medico/gestioncita/buscar" method="get"> 
            <div class="fila encabezado">Busqueda de Citas</div>
            <div style="display: flex">
                <div style="display: flex; justify-content: space-around; align-items: center; width: 25%;">
                    <img src="/Proyecto 1/images/Usuario 2.png" alt="" width="20" height="20"/>
                    <div class="etiqueta">Nombre Paciente</div>
                    <div class="campo"><input class="<%=erroneo("nombrePacienteFld",errores)%>" placeholder="Ej: Marta Perez" type="text" name="nombrePacienteFld" value="" title="<%=title("nombrePacienteFld",errores)%>"></div>
                </div>
                <div style="display: flex; justify-content: space-around; align-items: center; width: 25%">
                    <img src="/Proyecto 1/images/question.png" alt="" width="20" height="20"/>
                    <div class="etiqueta">Estado Cita</div>
                    <div class="campo"><input class="<%=erroneo("estadoFld",errores)%>" placeholder="Ej: Pendiente" type="text" name="estadoFld" value="" title="<%=title("estadoFld",errores)%>"></div>
                </div>
                <div style="justify-content: space-around; align-items: center;"><button style="margin-bottom: 15px">Buscar</button> </div>
            </div>
        </form>
        <div style="width:100%;margin:auto;">
            <%if(citasEstado.isEmpty() && model.getCita().getEstado() != null && model.getCita().getEstado()!= ""){%>
                <h1>No existen citas con el estado<%=model.getCita().getEstado()%></h1>
            <%}
            else if(!citasEstado.isEmpty() && model.getCita().getEstado() != null  && model.getCita().getEstado()!= ""){%>
                <h1><%=citasEstado.size()%> Citas encontradas con estado <%=model.getCita().getEstado()%></h1>
            <%}
            else if(citasNombrePaciente.isEmpty() && model.getCurrentPaciente().getNombre() != null  && model.getCurrentPaciente().getNombre()!= ""){%>
                <h1>No existen citas con el nombre de paciente <%=model.getCurrentPaciente().getNombre()%></h1>
            <%}
            else if(!citasNombrePaciente.isEmpty() && model.getCurrentPaciente().getNombre() != null && model.getCurrentPaciente().getNombre()!= ""){%>
                <h1><%=citasNombrePaciente.size()%> Citas encontradas con nombre de paciente <%=model.getCurrentPaciente().getNombre()%></h1>
            <%}%>
        </div>
        <div style="border: 3px solid black">
            <%for(Cita cita:citasEstado){%>
                <div style="border: 4px solid green">
                    <div style="border: 4px solid red; width: 10%; height: 25%; display: inline-block ">
                        <img src="<%=cita.getPaciente().getCurrent().getFoto()%>" style="width:79%; height:80%">
                    </div>
                    <div style="border: 4px solid blue; display: inline-block; position: relative; ">
                        <div style="border: 4px solid yellow; ">
                            <h2><%=cita.getPaciente().getCurrent().getNombre()%></h2>
                        </div>
                        <div style="border: 4px solid turquoise; ">
                            <h2>Id Cita: <%=cita.getIdCita()%></h2>
                        </div>
                        <div style="border: 4px solid brown; ">
                            <p><b>Estado Cita:</b> <%=cita.getEstado()%></p>
                        </div>
                    </div>
                </div>
            <%}%>
            <%for(Cita citaN:citasNombrePaciente){%>
                <h1>Holis <%=citaN.getPaciente().getCurrent().getNombre()%></h1>
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
       values.put("estadoFld", new String[]{model.getCita().getEstado()});
       values.put("nombrePacienteFld", new String[]{model.getCurrentPaciente().getNombre()});
       return values;
    }    
%> 