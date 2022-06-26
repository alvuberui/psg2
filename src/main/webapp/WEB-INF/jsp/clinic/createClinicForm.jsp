<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clinic">
	
	<h2>
		<c:if test="${clinic['new']}">Nueva </c:if> Clinica
	</h2>
        <form:form modelAttribute="clinic"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${clinic.id}"/>
            <div class="form-group has-feedback">
                <petclinic:inputField label="Nombre" name="name"/>
				<petclinic:inputField label="Dirección" name="address"/>
				<petclinic:inputField label="Ciudad" name="city"/>
				<petclinic:inputField label="Teléfono" name="telephone"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${clinic['new']}">
                            <button class="btn btn-default" type="submit">Crear Clinica</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Actualizar Clinica</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        
        
</petclinic:layout>