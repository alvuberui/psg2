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

<petclinic:layout pageName="causes">
    <h2>Detalles de la causa</h2>
    <br>
    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripción</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>Presupuesto alcanzado</th>
            <td><c:out value="${budgetAchieved}"/></td>
        </tr>
        <tr>
            <th>Presupuesto a alcanzar</th>
            <td><c:out value="${cause.budgetTarget}"/></td>
        </tr>

        <tr>
            <th>Organización</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
    </table>

    <h3>Donaciones realizadas a la causa:</h3>
    <c:if test="${ownerId!=null}">
        <spring:url value="/donations/new/cause/{causeId}/owner/{ownerId}" var="donationUrl">
            <spring:param name="causeId" value="${cause.id}"/>
            <spring:param name="ownerId" value="${ownerId}"/>
        </spring:url>
        <a class="btn btn-warning" href="${fn:escapeXml(donationUrl)}">Realizar nueva donación</a>
    </c:if>
    <c:if test="${ownerId==null}">
        <a disabled class="btn btn-warning" href="${fn:escapeXml(donationUrl)}">Realizar nueva donación</a>
        <p><i> Usted no es un usuario válido </i></p>
    </c:if>
    <h1></h1>
    
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Cantidad</th>
                <th>Cliente que realizó la donación</th>
                <th>Fecha</th>
            </tr>
            </thead>
        <tbody>

            <c:forEach items="${donations}" var="donation">
                <tr>
                    <td>
                        <c:out value="${donation.amount}"/>
                    </td>

                    <td>
                        <c:out value="${donation.owner.firstName} ${donation.owner.lastName}"/>
                    </td>

                    <td>
                        <c:out value="${donation.date}"/>
                    </td>

                </tr>
            </c:forEach>
        </tbody>

    </table>

</petclinic:layout>