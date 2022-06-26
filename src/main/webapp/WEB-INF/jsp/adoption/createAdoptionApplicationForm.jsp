<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Adoptions">
    
    <jsp:body>
        <h2>
            Nueva Solicitud
        </h2>
        <form:form modelAttribute="adoptionApplication"
                   class="form-horizontal">
            <div class="form-group has-feedback">
                
                
                <petclinic:inputField label="Descripción" name="description"/>
                
                
                <c:choose>
                <c:when test="${isAdmin}">
                <label class="col-sm-2 control-label">Solicitantes</label>
                <div class="col-sm-10">
                    <form:select name="owner" class="form-control" path="owner">
                        <c:forEach items="${owners}" var="o">
                            <form:option value="${o}"><c:out value="${o.firstName} ${o.lastName}"></c:out></form:option>
                        </c:forEach>
                    </form:select>
                </div>
                </c:when>
                <c:otherwise>
                <label class="col-sm-2 control-label">Solicitantes</label>
                <div class="col-sm-10">
                <form:select name="owner" class="form-control" path="owner">
                       <form:option value="${owner}"><c:out value="${owner.firstName} ${owner.lastName}"></c:out></form:option>
                </form:select>
                </div>
                </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                      <button class="btn btn-default" type="submit">Presentar solicitud</button>
                </div>
            </div>
        </form:form>
        
    </jsp:body>
</petclinic:layout>
