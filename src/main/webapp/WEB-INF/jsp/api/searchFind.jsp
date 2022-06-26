<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<petclinic:layout pageName="search">

    <h2 class="text-center">Realizar búsqueda</h2>

	<form:form modelAttribute="search" action="/api/search/list"
		method="get" class="form-horizontal" id="search-find-form">
		<div class="form-group">
			<div class="text-center">
				<input type="text" name="query" id="query" placeholder="Búsqueda">
				<button type="submit" class="btn btn-default">Buscar</button>
				<span class="help-inline"><form:errors path="*" /></span>
			</div>
		</div>

	</form:form>


</petclinic:layout>
