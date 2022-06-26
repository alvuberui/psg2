<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="petHotel">
    <h2>Hotel de mascotas</h2>
    <div style="width: 100%; display: flex; justify-content: flex-end">
			<a href="/petHotel/new" class="btn btn-default">Nueva reserva</a>
		</div>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="text-align: center;">Nombre de la mascota</th>
            <th style="text-align: center;">Fecha de ingreso</th>
            <th style="text-align: center;">Fecha de salida</th>
            <th style="text-align: center;">Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listPetHotel}" var="listPetHotel">
            <tr>
                <td style="text-align: center;">
                    
                    <c:out value="${listPetHotel.pet}"/>
                    
                    
                </td>
                <td style="text-align: center;">
                    <c:out value="${listPetHotel.initialDate}"/>
                </td>
                <td style="text-align: center;">
                    <c:out value="${listPetHotel.finalDate}"/>
                </td>
                
                <td style="text-align: center;">
                 <spring:url value="petHotel/delete/{id_achievement}" var="editUrl">
							<spring:param name="id_achievement" value="${listPetHotel.id}" />
						</spring:url>
					<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Eliminar reserva</a>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
