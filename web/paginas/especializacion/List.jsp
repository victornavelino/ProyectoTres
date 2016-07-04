<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Especializacion Items</title>
            <link rel="stylesheet" type="text/css" href="/ProyectoColegio/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Especializacion Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Especializacion Items Found)<br />" rendered="#{especializacion.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{especializacion.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{especializacion.pagingInfo.firstItem + 1}..#{especializacion.pagingInfo.lastItem} of #{especializacion.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{especializacion.prev}" value="Previous #{especializacion.pagingInfo.batchSize}" rendered="#{especializacion.pagingInfo.firstItem >= especializacion.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{especializacion.next}" value="Next #{especializacion.pagingInfo.batchSize}" rendered="#{especializacion.pagingInfo.lastItem + especializacion.pagingInfo.batchSize <= especializacion.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{especializacion.next}" value="Remaining #{especializacion.pagingInfo.itemCount - especializacion.pagingInfo.lastItem}"
                                   rendered="#{especializacion.pagingInfo.lastItem < especializacion.pagingInfo.itemCount && especializacion.pagingInfo.lastItem + especializacion.pagingInfo.batchSize > especializacion.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{especializacion.especializacionItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="MatriculaEspecialidad"/>
                            </f:facet>
                            <h:outputText value="#{item.matriculaEspecialidad}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="FechaMatriculacion"/>
                            </f:facet>
                            <h:outputText value="#{item.fechaMatriculacion}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="FechaVencimiento"/>
                            </f:facet>
                            <h:outputText value="#{item.fechaVencimiento}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Libro"/>
                            </f:facet>
                            <h:outputText value="#{item.libro}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Folio"/>
                            </f:facet>
                            <h:outputText value="#{item.folio}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="UnidadFormadora"/>
                            </f:facet>
                            <h:outputText value="#{item.unidadFormadora}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="FechaRevision"/>
                            </f:facet>
                            <h:outputText value="#{item.fechaRevision}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="FechaVencimientoRevision"/>
                            </f:facet>
                            <h:outputText value="#{item.fechaVencimientoRevision}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{especializacion.detailSetup}">
                                <f:param name="jsfcrud.currentEspecializacion" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][especializacion.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{especializacion.editSetup}">
                                <f:param name="jsfcrud.currentEspecializacion" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][especializacion.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{especializacion.remove}">
                                <f:param name="jsfcrud.currentEspecializacion" value="#{jsfcrud_class['ManagedBeans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][especializacion.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{especializacion.createSetup}" value="New Especializacion"/>
                <br />


            </h:form>
        </body>
    </html>
</f:view>
