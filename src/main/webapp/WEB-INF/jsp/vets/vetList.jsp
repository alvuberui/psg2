<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<petclinic:layout pageName="vets">
    <h2>Veterinarios</h2>
	
	<spring:url value="/vets/new" var="vetUrl"></spring:url>
    <a href="${fn:escapeXml(vetUrl)}" class="btn btn-default">Crear nuevo veterinario</a>
	<br></br>
	
    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Especialidades</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td>
                    <c:out value="${vet.firstName} ${vet.lastName}"/>
                </td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">ninguna</c:if>
                </td>
                <td>
                	<spring:url value="/vets/{vetId}/" var="editUrl">
                    	<spring:param name="vetId" value="${vet.id}"/>
                    </spring:url>
               		<a href="${fn:escapeXml(editUrl)}"><c:out value="Editar"/></a>
               		
               		<spring:url value="/vets/{vetId}/delete" var="deleteUrl">
                    	<spring:param name="vetId" value="${vet.id}"/>
                    </spring:url>
               		<a href="${fn:escapeXml(deleteUrl)}"><c:out value="Borrar"/></a>
               		
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">Ver como XML</a>
            </td>            
        </tr>
    </table>
</petclinic:layout>
