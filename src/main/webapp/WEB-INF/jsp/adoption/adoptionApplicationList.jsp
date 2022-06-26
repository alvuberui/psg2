<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoption">
	<h2>
		Lista de solicitudes posibles para
		<c:out value="${petName}"></c:out>
	</h2>
	<div style="width: 100%; display: flex; justify-content: flex-end">
		<spring:url value="new/${adoptionRequestId}"
			var="newApplicationUrl">
		</spring:url>
		<a href="${fn:escapeXml(newApplicationUrl)}" class="btn btn-default">Nueva
			solicitud</a>

	</div>
	<c:choose>
		<c:when test="${anyAdoptionApplication}">

			<table id="ownersTable" class="table table-striped">
				<thead>
					<tr>
						<th style="text-align: center;">Nombre del solicitante</th>
						<th style="text-align: center;">Descripción</th>
						<th style="text-align: center;"></th>
						<th style="text-align: center;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listAdoptionApplication}"
						var="listAdoptionApplication">
						<tr>
							<td style="text-align: center;"><c:out
									value="${listAdoptionApplication.owner.firstName} ${listAdoptionApplication.owner.lastName}" />
							</td>
							<td style="text-align: center;"><c:out
									value="${listAdoptionApplication.description}" />
							</td>
							<c:choose>
							<c:when test="${isAdmin}">
								<td style="text-align: center;"><spring:url
									value="{adoptionRequestId}/delete/{adoptionApplicationId}" var="deleteUrl">
									<spring:param name="adoptionRequestId" value="${listAdoptionApplication.adoptionRequest.id}" />
									<spring:param name="adoptionApplicationId" value="${listAdoptionApplication.id}" />
								</spring:url> <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Rechazar
									solicitud</a></td>
								<td style="text-align: center;"><spring:url
									value="{adoptionRequestId}/accept/{adoptionApplicationId}" var="acceptUrl">
									<spring:param name="adoptionRequestId" value="${listAdoptionApplication.adoptionRequest.id}" />
									<spring:param name="adoptionApplicationId" value="${listAdoptionApplication.id}" />
								</spring:url> <a href="${fn:escapeXml(acceptUrl)}" class="btn btn-default">Aceptar
									solicitud</a></td>
							</c:when>
							<c:otherwise>
							</c:otherwise>
							</c:choose>
							
									
							
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<h1 align=center>Actualmente no hay solicitudes disponibles</h1>
		</c:otherwise>
	</c:choose>


</petclinic:layout>