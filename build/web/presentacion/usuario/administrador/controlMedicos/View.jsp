<%@page import="citas.logic.Medico"%>
<%@page import="java.util.List"%>
<%@page import="citas.presentation.usuario.administrador.controlMedicos.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Model model= (Model) request.getAttribute("model");
    List<Medico> medicos = model.getMedicos();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1/css/style.css" rel="stylesheet" type="text/css" />
        <title>Control de medicos</title>
    </head>
    <body>
        <%@ include file="/presentacion/Header.jsp" %>
        <div class="encabezado">Control de Medico</div>
        <br></br>
        <div class="panel" style="width:70%; height:60%;">
            <div style="display: flex; justify-content: space-around; align-items: center;">
                <div>
                    <div class="encabezado">Medico con cuenta activada</div>
                    <div>
                        <ul style="list-style: none; display: inline-block">
                            <%  int tamanoListaMedicos = medicos.size();
                            for(int x = 0 ; x < tamanoListaMedicos ; x++)
                            {
                                Medico medico = medicos.get(x);
                                if(medico.isMedicoActivado())
                                {
                            %>
                            <li>
                                <div style="display: flex; align-items: center;">
                                    <div><img src="<%=medico.getUsuario().getFoto()%>" alt="alt" style="margin-left: 50px; height: 60px;"></div>
                                    <div>
                                        <div style="display: flex; align-items: center;">
                                            <div><label><%=medico.getUsuario().getNombre()%></label></div>
                                            <div><label><%=medico.getCostoConsulta()%></label></div>
                                            
                                        </div>
                                        <div style="display: flex; align-items: center;">
                                            <div><label><%=medico.getNombreEspecialidad()%></label></div>
                                            <div><label><%=medico.getNombreLocalidad()%></label></div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <%
                                }
                            }%>
                            <br></br>
                        </ul>
                    </div>    
                </div>
                <div>
                    <div class="encabezado">Medico con cuenta sin activar</div>
                    <div>
                        <ul style="list-style: none;">
                            <%
                            for(int x = 0 ; x < tamanoListaMedicos ; x++)
                            {
                                Medico medico = medicos.get(x);
                                if(!(medico.isMedicoActivado()))
                                {
                            %>
                            <li>
                                <div style="display: flex; align-items: center;">
                                    <div><img src="<%=medico.getUsuario().getFoto()%>" alt="alt" style="margin-left: 50px; height: 60px;"></div>
                                    <div>
                                        <div style="display: flex; align-items: center;">
                                            <div><label><%=medico.getUsuario().getNombre()%></label></div>
                                            <div><label><%=medico.getCostoConsulta()%></label></div>
                                            
                                        </div>
                                        <div style="display: flex; align-items: center;">
                                            <div><label><%=medico.getNombreEspecialidad()%></label></div>
                                            <div><label><%=medico.getNombreLocalidad()%></label></div>
                                        </div>
                                        <div><a href="/Proyecto 1/presentacion/usuario/administrador/controlMedicos/activar?idMedico=<%=medico.getCedula()%>" style="text-align: center; color: aliceblue; background-color: #003333; text-decoration-line: none;">Activar cuenta</a></div>
                                    </div>
                                </div>
                            </li>
                            <%
                                }
                            }%>
                        </ul>
                    </div>   
                </div>
            </div>
        </div>
        <%@ include file="/presentacion/Footer.jsp" %>
    </body>
</html>
