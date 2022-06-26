<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<petclinic:layout pageName="plan">

    <h1>Información del plan del dueño de clínica</h1>
    
    <br></br>

	<h3>Información básica del propietario</h3>
    <table class="table table-striped">
        <tr>
            <th>Nombre Completo</th>
            <td><c:out value="${clinicOwner.firstName} ${clinicOwner.lastName}"/></td>
        </tr>
        <tr>
            <th>Dirección</th>
            <td><c:out value="${clinicOwner.address}"/></td>
        </tr>
        <tr>
            <th>Ciudad</th>
            <td><c:out value="${clinicOwner.city}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${clinicOwner.telephone}"/></td>
        </tr>
    </table>
    <br></br>
    <hr style="color: black; background-color: black; width:100%; height: 10px" />
    <br></br>
    
    <h3>Información sobre sus clínicas</h3>
    
    <c:forEach items="${clinics}" var="clinic">
    
    <h4>Clínica <c:out value="${clinic.name}"/></h4>
    <table class="table table-striped">
        <tr>
            <th>Dirección</th>
            <td><c:out value="${clinic.address}"/></td>
        </tr>
        <tr>
            <th>Ciudad</th>
            <td><c:out value="${clinic.city}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${clinic.telephone}"/></td>
        </tr>
        <tr>
        	<td>
            	<spring:url value="/clinic/{clinicId}/delete" var="deleteUrl">
                	<spring:param name="clinicId" value="${clinic.id}"/>
                </spring:url>
            	<a href="${fn:escapeXml(deleteUrl)}"><c:out value="Eliminar"/></a>
            		
             </td>
         </tr>
    </table>
   
    
    </c:forEach>
    <c:if test="${clinicOwner.user.plan != 'BASIC'}">
    	<spring:url value="/clinic/new" var="addClinictUrl">
						</spring:url>
					<a href="${fn:escapeXml(addClinictUrl)}" class="btn btn-default">Añadir una clínica</a>
    </c:if>
    
    <c:if test="${clinicOwner.user.plan == 'BASIC' && numberClinics == 0}">
    	<spring:url value="/clinic/new" var="addClinictUrl">
						</spring:url>
					<a href="${fn:escapeXml(addClinictUrl)}" class="btn btn-default">Añadir una clínica</a>
    </c:if>
    
    <br></br>
    <hr style="color: black; background-color: black; width:100%; height: 10px" />
    <br></br>
    
    
    <h3>Información actual sobre tu plan</h3>
    <table class="table table-striped">
        <tr>
            <th>Plan Actual</th>
            <td><c:out value="${clinicOwner.user.plan}"/></td>
        </tr>
    </table>
    <br></br>
    <c:if test="${clinicOwner.user.plan == 'BASIC'}">
    	<h4>Plan Avanzado</h4>
    <table class="table table-striped">
        <tr>
            <th>Precio</th>
            <td>30€</td>
        </tr>
        <tr>
            <th>Ventajas</th>
            <td>Puedes administrar más de una clínica</td>
        </tr>
        <tr>
            <th>Actualizar plan</th>
            <td><spring:url value="/user/updatePlan/{userId}/{plan}" var="editUrl">
							<spring:param name="userId" value="${clinicOwner.user.username}" />
							<spring:param name="plan" value="${1}" />
						</spring:url>
					<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Actualizar a Advanced</a></td>
        </tr>
    </table>
    
    <h4>Plan Profesional</h4>
    <table class="table table-striped">
        <tr>
            <th>Precio</th>
            <td>40€</td>
        </tr>
        <tr>
            <th>Ventajas</th>
            <td>El plan Pro es el único que ofrece soporte para la gestión de incidencias para dueños de mascotas</td>
        </tr>
        <tr>
            <th>Actualizar plan</th>
            <td><spring:url value="/user/updatePlan/{userId}/{plan}" var="editUrl">
							<spring:param name="userId" value="${clinicOwner.user.username}" />
							<spring:param name="plan" value="${2}" />
						</spring:url>
					<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Actualizar a Pro</a></td>
        </tr>
    </table>
    </c:if>
    
    <c:if test="${clinicOwner.user.plan == 'ADVANCED'}">
    </table>
     <h4>Plan Profesional</h4>
    <table class="table table-striped">
        <tr>
            <th>Precio</th>
            <td>40€</td>
        </tr>
        <tr>
            <th>Ventajas</th>
            <td>El plan Pro es el único que ofrece soporte para la gestión de incidencias para dueños de mascotas</td>
        </tr>
        <tr>
            <th>Actualizar plan</th>
            <td><spring:url value="/user/updatePlan/{userId}/{plan}" var="editUrl">
							<spring:param name="userId" value="${clinicOwner.user.username}" />
							<spring:param name="plan" value="${2}" />
						</spring:url>
					<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Actualizar a Pro</a></td>
        </tr>
    </table>
    </c:if>
    
    <c:if test="${clinicOwner.user.plan == 'PRO'}">
    	<h4>¡Estás en el plan más elevado!</h4>
    </c:if>
    

</petclinic:layout>
