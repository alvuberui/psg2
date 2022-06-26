<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="planVersion">
    <h2>Hitorial de CA</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align: center;" width="33%">Previa versión</th>
            <th style="text-align: center;" width="33%">Previa versión</th>
            <th style="text-align: center;" width="33%">Versión actual</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td style="text-align: center;">
                    <c:choose>
                    	<c:when test="${firstPlan == 1}">
                    		<c:out value="Plan Básico"/> <spring:url value="/resources/images/plan/basic.png" htmlEscape="true" var="basicImage"/>
            				<img class="img-responsive" src="${basicImage}"/>
                    	</c:when>
                    	<c:when test="${firstPlan == 2}">
                    		<c:out value="Plan Avanzado"/> <spring:url value="/resources/images/plan/advanced.png" htmlEscape="true" var="advanceImage"/>
            				<img class="img-responsive" src="${advanceImage}"/>
                    	</c:when>
                    	<c:when test="${firstPlan == 3}">
                    		<c:out value="Plan Profesional"/> <spring:url value="/resources/images/plan/pro.png" htmlEscape="true" var="proImage"/>
            				<img class="img-responsive" src="${proImage}"/>
                    	</c:when>
                    	<c:otherwise>
                    		<c:out value="No tienes una versión más antigua"/> 
                    	</c:otherwise>
                    </c:choose>
                </td>
                
                <td style="text-align: center;">
                    <c:choose>
                    	<c:when test="${secondPlan == 1}">
                    		<c:out value="Plan Básico"/> <spring:url value="/resources/images/plan/basic.png" htmlEscape="true" var="basicImage"/>
            				<img class="img-responsive" src="${basicImage}"/>
                    	</c:when>
                    	<c:when test="${secondPlan == 2}">
                    		<c:out value="Plan Avanzado"/> <spring:url value="/resources/images/plan/advanced.png" htmlEscape="true" var="advanceImage"/>
            				<img class="img-responsive" src="${advanceImage}"/>
                    	</c:when>
                    	<c:when test="${secondPlan == 3}">
                    		<c:out value="Plan Profesional"/> <spring:url value="/resources/images/plan/pro.png" htmlEscape="true" var="proImage"/>
            				<img class="img-responsive" src="${proImage}"/>
                    	</c:when>
                    	<c:otherwise>
                    		<c:out value="No tienes una versión más antigua"/> 
                    	</c:otherwise>
                    </c:choose>
                </td>
                
                <td style="text-align: center;">
                    <c:choose>
                    	<c:when test="${actualPlan == 1}">
                    		<c:out value="Plan Básico"/> <spring:url value="/resources/images/plan/basic.png" htmlEscape="true" var="basicImage"/>
            				<img class="img-responsive" src="${basicImage}"/>
                    	</c:when>
                    	<c:when test="${actualPlan == 2}">
                    		<c:out value="Plan Avanzado"/> <spring:url value="/resources/images/plan/advanced.png" htmlEscape="true" var="advanceImage"/>
            				<img class="img-responsive" src="${advanceImage}"/>
                    	</c:when>
                    	<c:when test="${actualPlan == 3}">
                    		<c:out value="Plan Profesional"/> <spring:url value="/resources/images/plan/pro.png" htmlEscape="true" var="proImage"/>
            				<img class="img-responsive" src="${proImage}"/>
                    	</c:when>
                    </c:choose>
                </td>
            </tr>
            
            <c:if test="${isAut}">
            <tr>
                <td style="text-align: center;">
                	<c:choose>
                    	<c:when test="${firstPlan == 1}">
                    		<ul>
		                		<li>
		                			No ofrece SLA.
		                		</li>
		                		<li>
		                			Hasta 3 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 200 visitas/mes.
		                		</li>
		                		<li>
		                			Permite perros y gatos.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:when test="${firstPlan == 2}">
                    		<ul>
		                		<li>
		                			Ofrece SLA.
		                		</li>
		                		<li>
		                			Gestión de más de una clínica.
		                		</li>
		                		<li>
		                			Hasta 10 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 700 visitas / mes.
		                		</li>
		                		<li>
		                			Permite perros, gatos y conejos.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:when test="${firstPlan == 3}">
                    		<ul>
                    			<li>
		                			Ofrece SLA.
		                		</li>
		                		<li>
		                			Gestión de más de una clínica.
		                		</li>
		                		<li>
		                			Reportar incidentes a los dueños de las mascotas.
		                		</li>
		                		<li>
		                			Hasta 45 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 1500 visitas / mes.
		                		</li>
		                		<li>
		                			Permite perros, gatos, conejos y peces.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:otherwise>
                    		
                    	</c:otherwise>
                    </c:choose>
                	
                </td>
                <td style="text-align: center;">
                	<c:choose>
                    	<c:when test="${secondPlan == 1}">
                    		<ul>
		                		<li>
		                			No ofrece SLA.
		                		</li>
		                		<li>
		                			Hasta 3 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 200 visitas/mes.
		                		</li>
		                		<li>
		                			Permite perros y gatos.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:when test="${secondPlan == 2}">
                    		<ul>
		                		<li>
		                			Ofrece SLA.
		                		</li>
		                		<li>
		                			Gestión de más de una clínica.
		                		</li>
		                		<li>
		                			Hasta 10 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 700 visitas / mes.
		                		</li>
		                		<li>
		                			Permite perros, gatos y conejos.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:when test="${secondPlan == 3}">
                    		<ul>
                    			<li>
		                			Ofrece SLA.
		                		</li>
		                		<li>
		                			Gestión de más de una clínica.
		                		</li>
		                		<li>
		                			Reportar incidentes a los dueños de las mascotas.
		                		</li>
		                		<li>
		                			Hasta 45 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 1500 visitas / mes.
		                		</li>
		                		<li>
		                			Permite perros, gatos, conejos y peces.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:otherwise>
                    		
                    	</c:otherwise>
                    </c:choose>
                </td>
                <td style="text-align: center;">
                	<c:choose>
                    	<c:when test="${actualPlan == 1}">
                    		<ul>
		                		<li>
		                			No ofrece SLA.
		                		</li>
		                		<li>
		                			Hasta 3 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 200 visitas/mes.
		                		</li>
		                		<li>
		                			Permite perros y gatos.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:when test="${actualPlan == 2}">
                    		<ul>
		                		<li>
		                			Ofrece SLA.
		                		</li>
		                		<li>
		                			Gestión de más de una clínica.
		                		</li>
		                		<li>
		                			Hasta 10 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 700 visitas / mes.
		                		</li>
		                		<li>
		                			Permite perros, gatos y conejos.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:when test="${actualPlan == 3}">
                    		<ul>
                    			<li>
		                			Ofrece SLA.
		                		</li>
		                		<li>
		                			Gestión de más de una clínica.
		                		</li>
		                		<li>
		                			Reportar incidentes a los dueños de las mascotas.
		                		</li>
		                		<li>
		                			Hasta 45 veterinarios.
		                		</li>
		                		<li>
		                			Hasta 1500 visitas / mes.
		                		</li>
		                		<li>
		                			Permite perros, gatos, conejos y peces.
		                		</li>
		                	</ul>
                    	</c:when>
                    	<c:otherwise>
                    		
                    	</c:otherwise>
                    </c:choose>
                </td>
            </tr>
            
            
            <tr>
            	<td style="text-align: center;">
            		<c:choose>
                    	<c:when test="${firstPlan == 1}">
                    		<c:out value="20 €/mes"></c:out>
                    	</c:when>
                    	<c:when test="${firstPlan == 2}">
                    		<c:out value="30 €/mes"></c:out>
                    	</c:when>
                    	<c:when test="${firstPlan == 3}">
                    		<c:out value="40 €/mes"></c:out>
                    	</c:when>
                    	<c:otherwise>
                    	
                    	</c:otherwise>
                    </c:choose>
            	</td>
            	<td style="text-align: center;">
            		<c:choose>
                    	<c:when test="${secondPlan == 1}">
                    		<c:out value="20 €/mes"></c:out>
                    	</c:when>
                    	<c:when test="${secondPlan == 2}">
                    		<c:out value="30 €/mes"></c:out>
                    	</c:when>
                    	<c:when test="${secondPlan == 3}">
                    		<c:out value="40 €/mes"></c:out>
                    	</c:when>
                    	<c:otherwise>
                    	
                    	</c:otherwise>
                    </c:choose>
            	</td>
            	<td style="text-align: center;">
            		<c:choose>
                    	<c:when test="${actualPlan == 1}">
                    		<c:out value="20 €/mes"></c:out>
                    	</c:when>
                    	<c:when test="${actualPlan == 2}">
                    		<c:out value="30 €/mes"></c:out>
                    	</c:when>
                    	<c:when test="${actualPlan == 3}">
                    		<c:out value="40 €/mes"></c:out>
                    	</c:when>
                    	<c:otherwise>
                    	
                    	</c:otherwise>
                    </c:choose>
            	</td>
            </tr>
            </c:if>
        </tbody>
    </table>
</petclinic:layout>
