<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Especialidad Items</title>
            <link rel="stylesheet" type="text/css" href="/ProyectoColegio/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Especialidad Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Especialidad Items Found)<br />" rendered="#{especialidad.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{especialidad.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{especialidad.pagingInfo.firstItem + 1}..#{especialidad.pagingInfo.lastItem} of #{especialidad.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{especialidad.prev}" value="Previous #{especialidad.pagingInfo.batchSize}" rendered="#{especialidad.pagingInfo.firstItem >= especialidad.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{especialidad.next}" value="Next #{especialidad.pagingInfo.batchSize}" rendered="#{especialidad.pagingInfo.lastItem + especialidad.pagingInfo.batchSize <= especialidad.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{especialidad.next}" value="Remaining #{especialidad.pagingInfo.itemCount - especialidad.pagingInfo.lastItem}"
                                   rendered="#{especialidad.pagingInfo.lastItem < especialidad.pagingInfo.itemCount && especialidad.pagingInfo.lastItem + especialidad.pagingInfo.batchSize > especialidad.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{especialidad.especialidadItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Descripcion"/>
                            </f:facet>
                            <h:outputText value="#{item.descripcion}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="NombreEspecialidad"/>
                            </f:facet>
                            <h:outputText value="#{item.nombreEspecialidad}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TipoEspecialidad"/>
                            </f:facet>
                            <h:outputText value="#{item.tipoEspecialidad}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{especialidad.detailSetup}">
                                <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][especialidad.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{especialidad.editSetup}">
                                <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][especialidad.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{especialidad.remove}">
                                <f:param name="jsfcrud.currentEspecialidad" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][especialidad.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{especialidad.createSetup}" value="New Especialidad"/>
                <br />


            </h:form>
        </body>
    </html>
</f:view>
