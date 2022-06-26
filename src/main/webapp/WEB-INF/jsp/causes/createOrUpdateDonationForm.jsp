<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<petclinic:layout pageName="causes">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${donation['new']}">Nueva </c:if>Donación</h2>

        <b>Causa</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Título</th>
                <th>Descripción</th>
                <th>Organización</th>
                <th>Objetivo</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${donation.cause.name}"/></td>
                <td><c:out value="${donation.cause.description}"/></td>
                <td><c:out value="${donation.cause.organization}"/></td>
                <td><c:out value="${donation.cause.budgetTarget}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="donation" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:moneyInputField label="Cantidad a donar" name="amount"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="ownerId" value="${ownerId}"/>
                    <input type="hidden" name="causeId" value="${donation.cause.id}"/>

                    <button class="btn btn-default" type="submit">Donar</button>
                </div>
            </div>
        </form:form>

        <br/>
        <b>Donaciones Anteriores</b>
        <table class="table table-striped">
            <tr>
                <th>Fecha</th>
                <th>Cantidad</th>
            </tr>
            <c:forEach var="donation" items="${donation.cause.donations}">
                <c:if test="${!donation['new']}">
                    <tr>
                        <td><petclinic:localDate date="${donation.date}" pattern="yyyy/MM/dd"/></td>
                        <td><c:out value="${donation.amount}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>

</petclinic:layout>
