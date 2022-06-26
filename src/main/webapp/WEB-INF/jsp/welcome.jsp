<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<petclinic:layout pageName="home">
    <h1>Bievenido, somos el grupo G6-63</h1>
    <spring:url value="adoptionRequest" var="adoptionRequest"/>
    <spring:url value="api/search/find" var="api"/>
    <p>Observa nuestras mascotas en adopción <a href="${fn:escapeXml(adoptionRequest)}" >aquí</a></p>
    <spring:url value="custumer-agreement" var="planVersions"/>
    <p>Observa nuestros planes contratados <a href="${fn:escapeXml(planVersions)}" >aquí</a></p>
    <p>Haz una fácilmente consulta en internet mediante nuestro buscador <a href="${fn:escapeXml(api)}" >aquí</a></p>
    <spring:url value="support" var="support"/>
    <p>Contacta con nosotros <a href="${fn:escapeXml(support)}" >aquí</a></p>
    
    <spring:url value="changelog" var="changelog"/>
    <p>Consulta los cambios realizados en la última actualización <a href="${fn:escapeXml(changelog)}" >aquí</a></p>

    <c:if test="${clinicOwner.user.plan == 'ADVANCED' || clinicOwner.user.plan == 'PRO'}">
    	<p>Haz una fácilmente consulta en internet mediante nuestro buscador <a href="${fn:escapeXml(api)}" >aquí</a></p>
    </c:if>

    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/gatito.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>
        </div>
    </div>
</petclinic:layout>
