<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="PetHotel">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#initialDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
        <script>
            $(function () {
                $("#finalDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            Nueva Reserva
        </h2>
        <form:form modelAttribute="petHotel"
                   class="form-horizontal">
            <div class="form-group has-feedback">
                
                <petclinic:inputField label="Fecha de admision" name="initialDate"/>
                
                <petclinic:inputField label="Fecha de salida" name="finalDate"/>
                
                <label class="col-sm-2 control-label">Mascota</label>

                <div class="col-sm-10">
                    <form:select name="pet" class="form-control" path="pet">
                        <c:forEach items="${pets}" var="p">
                            <form:option value="${p}"><c:out value="${p.name}"></c:out></form:option>
                        </c:forEach>
                    </form:select>
                    <p>${duplicatedPet}</p>
                </div> 

            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                      <button class="btn btn-default" type="submit">Reservar</button>
                </div>
            </div>
        </form:form>
    </jsp:body>
</petclinic:layout>