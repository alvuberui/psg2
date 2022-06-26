<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoption">
    <h2>Lista de adopciones posibles</h2>

     <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align: center;">Nombre de la mascota</th>
            <th style="text-align: center;">Nombre del dueño</th>
            <th style="text-align: center;">Eliminar</th>
            <th style="text-align: center;">Ver solicitudes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listAdoptionRequest}" var="listAdoptionRequest">
            <tr>
                <td style="text-align: center;">
                    <c:out value="${listAdoptionRequest.pets}"/>
                </td>
                <td style="text-align: center;">
                    <c:out value="${listAdoptionRequest.pets.owner.firstName} ${listAdoptionRequest.pets.owner.lastName}"/>
                </td>
                <c:choose>
                <c:when test="${isAdmin}">
                <td style="text-align: center;">
                    <spring:url value="/adoptionRequest/delete/{adoptionRequestId}" var="deleteUrl">
							<spring:param name="adoptionRequestId" value="${listAdoptionRequest.id}" />
						</spring:url>
					<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Eliminar adopcion</a>
                </td>
                </c:when>
                <c:otherwise>
                <td style="text-align: center;">
                    
					<a disabled href="" class="btn btn-default">Eliminar adopcion</a>
                </td>
                </c:otherwise>
                </c:choose>
                <td style="text-align: center;">
                 	<spring:url value="/adoptionApplication/{adoptionRequestId}" var="applicationUrl">
							<spring:param name="adoptionRequestId" value="${listAdoptionRequest.id}" />
						</spring:url>
					<a href="${fn:escapeXml(applicationUrl)}" class="btn btn-default">Ver solicitudes</a>
                 </td>
            </tr>
        </c:forEach>
        </tbody>
    </table> 
</petclinic:layout>
