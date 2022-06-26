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
    <div id="center-section">
        <div id="causes">
            <h2>Causas</h2>

            <spring:url value="causes/new" var="addUrl">
            </spring:url>
            <c:if test="${ownerId!=null}">
                <a href="${fn:escapeXml(addUrl)}" class="btn btn-warning">Crear Nueva Causa Benéfica</a>
            </c:if>
            <c:if test="${ownerId==null}">
                <a disabled class="btn btn-warning">Crear Nueva Causa Benéfica</a>
                <p><i> Usted no es un usuario válido </i></p>
            </c:if>
            
            <p></p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Presupuesto alcanzado</th>
                    <th>Presupuesto a alcanzar</th>
                    <th>Organización</th>
                    <th>Hacer donación</th>
                    <th>Detalles de la causa</th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${causes}" var="cause">
                        <c:if test="${cause.isClosed==false}">
                        <tr>
                            <td>
                                <c:out value="${cause.name}"/>
                            </td>

                            <td>
                                <c:out value="${cause.description}"/>
                            </td>

                            <td>
                                <c:out value="${cause.budgetAchieved}"/>
                            </td>

                            <td>
                                <c:out value="${cause.budgetTarget}"/>
                            </td>

                            <td>
                                <c:out value="${cause.organization}"/>
                            </td>

                            <td>
                                <c:if test="${ownerId!=null}">
                                    <spring:url value="/donations/new/cause/{causeId}/owner/{ownerId}" var="donationUrl">
                                        <spring:param name="causeId" value="${cause.id}"/>
                                        <spring:param name="ownerId" value="${ownerId}"/>
                                    </spring:url>
                                    <a class="btn btn-warning" href="${fn:escapeXml(donationUrl)}">Donar</a>
                                </c:if>
                                <c:if test="${ownerId==null}">
                                    <a disabled class="btn btn-warning" href="${fn:escapeXml(donationUrl)}">Realizar nueva donación</a>
                                    <p><i> Usted no es un usuario válido </i></p>
                                </c:if>
                            </td>

                            <td>
                                <spring:url value="/causes/details/{causeId}" var="causeUrl">
                                    <spring:param name="causeId" value="${cause.id}"/>
                                </spring:url>
                                <a class="btn btn-warning" href="${fn:escapeXml(causeUrl)}">Ver Detalles</a>
                            </td>

                        </tr>
                        </c:if>
                    </c:forEach>
                </tbody>

            </table>
        </div>


    </div>




</petclinic:layout>