<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Especialidad</title>
            <link rel="stylesheet" type="text/css" href="/ProyectoColegio/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Especialidad</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{especialidad.especialidad.id}" title="Id" />
                    <h:outputText value="Descripcion:"/>
                    <h:inputText id="descripcion" value="#{especialidad.especialidad.descripcion}" title="Descripcion" />
                    <h:outputText value="NombreEspecialidad:"/>
                    <h:inputText id="nombreEspecialidad" value="#{especialidad.especialidad.nombreEspecialidad}" title="NombreEspecialidad" />
                    <h:outputText value="TipoEspecialidad:"/>
                    <h:selectOneMenu id="tipoEspecialidad" value="#{especialidad.especialidad.tipoEspecialidad}" title="TipoEspecialidad" >
                        <f:selectItems value="#{tipoEspecialidad.tipoEspecialidadItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{especialidad.edit}" value="Save">
                    <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad][especialidad.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{especialidad.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad][especialidad.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{especialidad.listSetup}" value="Show All Especialidad Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
