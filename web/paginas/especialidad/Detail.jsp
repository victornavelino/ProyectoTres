<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Especialidad Detail</title>
            <link rel="stylesheet" type="text/css" href="/ProyectoColegio/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Especialidad Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{especialidad.especialidad.id}" title="Id" />
                    <h:outputText value="Descripcion:"/>
                    <h:outputText value="#{especialidad.especialidad.descripcion}" title="Descripcion" />
                    <h:outputText value="NombreEspecialidad:"/>
                    <h:outputText value="#{especialidad.especialidad.nombreEspecialidad}" title="NombreEspecialidad" />
                    <h:outputText value="TipoEspecialidad:"/>
                    <h:panelGroup>
                        <h:outputText value="#{especialidad.especialidad.tipoEspecialidad}"/>
                        <h:panelGroup rendered="#{especialidad.especialidad.tipoEspecialidad != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tipoEspecialidad.detailSetup}">
                                <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad][especialidad.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTipoEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad.tipoEspecialidad][tipoEspecialidad.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="especialidad"/>
                                <f:param name="jsfcrud.relatedControllerType" value="ManagedBeans.EspecialidadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tipoEspecialidad.editSetup}">
                                <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad][especialidad.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTipoEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad.tipoEspecialidad][tipoEspecialidad.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="especialidad"/>
                                <f:param name="jsfcrud.relatedControllerType" value="ManagedBeans.EspecialidadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tipoEspecialidad.destroy}">
                                <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad][especialidad.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTipoEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad.tipoEspecialidad][tipoEspecialidad.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="especialidad"/>
                                <f:param name="jsfcrud.relatedControllerType" value="ManagedBeans.EspecialidadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>


                </h:panelGrid>
                <br />
                <h:commandLink action="#{especialidad.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad][especialidad.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{especialidad.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][especialidad.especialidad][especialidad.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{especialidad.createSetup}" value="New Especialidad" />
                <br />
                <h:commandLink action="#{especialidad.listSetup}" value="Show All Especialidad Items"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
