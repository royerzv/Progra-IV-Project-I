<%@page import="java.util.List"%>
<%@page import="citas.logic.Service"%>
<%@page import="java.util.HashMap"%>
<%@page import="citas.presentacion.registro.medico.Model"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    Service service = Service.instance();
    List<String> especialidades = service.getEspecialidades();
    List<String> ciudades = service.getCiudades();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/Proyecto 1 v3/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/Proyecto 1 v3/css/style.css" rel="stylesheet" type="text/css" />
        <title>Registro de paciente</title>
    </head>
    <body>
        <%@ include file="/presentacion/Header.jsp" %>
        <% Model model= (Model) request.getAttribute("model"); %>
        <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
        <% Map<String,String[]> form = (errores==null)?this.getForm(model):request.getParameterMap();%>
        <div class="encabezado">Registro de Medico</div>
        <form name="form" action="/Proyecto 1 v3/presentacion/registro/medico/registrar" method="post" enctype="multipart/form-data">
            <div ><label> <%=title("errorFld",errores)%> </label></div>
            <div class="panel" style="width:70%;">
                <div style="display: flex; justify-content: space-around; align-items: center;">
                    <div style="width:45%;">
                        <div class="encabezado">Datos personales</div>
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
                        <br></br>
                        <br></br>
                    </div>             
                    <div style="width:45%;">
                        <div class="encabezado">Datos profesionales</div>
                        <div style="display: flex; justify-content:space-around; align-items:center;">
                            <div class="etiqueta">Especialidad</div>
                            <div>
                                <select name="especialidad" style="background: none;">
                                    <option value="0">Selecione</option>
                                    <%for(String e: especialidades){ %>
                                    <option value=<%=e%>><%=e%></option>
                                    <%}%>
                                </select>
                            </div>    
                        </div>      
                        <div style="display: flex; justify-content:space-around;; align-items:center;">
                            <div class="etiqueta">Ciudad del consultorio</div>
                            <div>
                                <select name="ciudad" style="background: none;">
                                    <option value="0">Selecione</option>
                                    <%for(String c: ciudades){%>
                                    <option value=<%=c%>><%=c%></option>
                                    <%}%>
                                </select>
                            </div>    
                        </div>        
                        <div style="display: flex; justify-content:flex-end; align-items:center;">
                            <div class="etiqueta">Costo de la consulta</div>
                            <div class="campo"><input class="<%=erroneo("costoFld",errores)%>" placeholder="Costo de la consulta" type="text" name="costoFld" value="<%=form.get("costoFld")[0]%>" title="<%=title("costoFld",errores)%>"></div>
                        </div>
                        <br></br>
                        <div style="display: flex; justify-content:flex-end; align-items:center;">
                            <div class="etiqueta">Presentación</div>
                            <div class="campo"><input class="<%=erroneo("presentacionFld",errores)%>" placeholder="Breve presentación" type="text" name="presentacionFld" value="<%=form.get("presentacionFld")[0]%>" title="<%=title("presentacionFld",errores)%>"></div>
                        </div>
                        <br></br>
                        <div style="display: flex; justify-content:flex-end; align-items:center;">
                            <div class="etiqueta">Duración de la consulta</div>
                            <div class="campo"><input class="<%=erroneo("duracionFld",errores)%>" placeholder="Duracion aproximada de la consulta (en minutos)" type="text" name="duracionFld" value="<%=form.get("duracionFld")[0]%>" title="<%=title("duracionFld",errores)%>"></div>
                        </div>
                        <br></br>
                        <div style="display: flex; justify-content:flex-end; align-items:center;">
                            <div class="etiqueta">Días de atencion a pacientes</div>
                            <div style="display: flex;">
                                <input type="checkbox" name="dias" value="Lunes">Lunes
                                <input type="checkbox" name="dias" value="Martes">Martes
                                <input type="checkbox" name="dias" value="Miercoles">Miercoles
                                <input type="checkbox" name="dias" value="Jueves">Jueves
                                <input type="checkbox" name="dias" value="Viernes">Viernes
                            </div>     
                        </div>
                        <br></br>
                        <div style="display: flex; justify-content:flex-end; align-items:center;">
                            <div class="etiqueta">Hora de inicio</div>
                            <div class="campo"><input class="<%=erroneo("horaInicioFld",errores)%>" placeholder="Hora de inicio de atención" type="text" name="horaInicioFld" value="<%=form.get("horaInicioFld")[0]%>" title="<%=title("horaInicioFld",errores)%>"></div>
                            <div class="etiqueta">Hora de final de atención</div>
                            <div class="campo"><input class="<%=erroneo("horaFinalFld",errores)%>" placeholder="Hora de final de atención" type="text" name="horaFinalFld" value="<%=form.get("horaFinalFld")[0]%>" title="<%=title("horaFinalFld",errores)%>"></div>
                        </div>
                    </div>
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
        values.put("nombreFld", new String[]{model.getCurrent().getUsuario().getNombre()});
        values.put("cedulaFld", new String[]{model.getCurrent().getUsuario().getCedula()});
        values.put("claveFld", new String[]{model.getCurrent().getUsuario().getClave()});
        values.put("confClaveFld", new String[]{model.getCurrent().getUsuario().getClave()});
        values.put("confClaveFld", new String[]{""});
        values.put("costoFld", new String[]{String.valueOf(model.getCurrent().getCostoConsulta())});
        values.put("presentacionFld", new String[]{model.getCurrent().getPresentacion()});
        values.put("duracionFld", new String[]{String.valueOf(model.getCurrent().getDuracionConsulta())});
        values.put("horaInicioFld", new String[]{model.getCurrent().getHoraInicio()});
        values.put("horaFinalFld", new String[]{model.getCurrent().getHoraFinal()});
        return values;
    }
%>