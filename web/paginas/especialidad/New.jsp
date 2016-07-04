<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Especialidad</title>
            <link rel="stylesheet" type="text/css" href="/ProyectoColegio/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Especialidad</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{especialidad.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
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
                <h:commandLink action="#{especialidad.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{especialidad.listSetup}" value="Show All Especialidad Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
