<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<petclinic:layout pageName="changelog">

    <h1>Changelog</h1>

    <ol class="circle-list">
        <li>
            <h2>Historial de cambios</h2>
            <p>Proporcionar una vista que liste los principales cambios realizados a la aplicación Petclinic durante este Sprint. No es necesario usar iTop para gestionar esta petición.</p>
        </li>
        <li>
            <h2>Página de soporte</h2>
            <p>Proporcionar los detalles de contacto (página web, emails y teléfonos) del equipo de soporte de la aplicación Petclinic.</p>
        </li>
        <li>
            <h2>Plan de mejora</h2>
            <p>Permitir a los propietarios de la clínica elegir un nuevo plan de precios más elevado del actual (los propietarios de mascotas no están autorizados a hacer esto). Por defecto, todos los usuarios de Petclinic empezarán en el plan básico. Desde el momento en el que el plan es actualizado, las características y los términos de uso de la aplicación deberán ajustarse al nuevo plan elegido.</p>
        </li>
        <li>
            <h2>GUI acorde con el contrato</h2>
            <p>Ajustar la interfaz de la la aplicación al plan de precios asociado con el usuario logueado. Esta característica será decrementada incrementalmente, significando que para la primera petición de actualización solo soportará el plan Básico, después en una actualización posterior el plan Avanzado, y la actualización final soportará el plan Pro.</p>
        </li>
        <li>
            <h2>Acuerdo del actual cliente</h2>
            <p>Muestre el Acuerdo de Cliente actualmente activo y las dos versiones anteriores del mismo (consulte el punto anterior) en la web sin necesidad de iniciar sesión. Los detalles de precios deben mostrarse por separado del CA. En una actualización posterior, mostrar solo a los usuarios autenticados los términos de uso derivados del plan de precios elegido.</p>
        </li>
        <li>
            <h2>Extensiones basadas en APIs</h2>
            <p>Agregue funciones que necesiten el uso de al menos dos APIs externas. No es necesario integrar realmente las APIs dentro del código base (la integración real se propone como tareas adicionales, consulte a continuación), pero al menos la interfáz de usuario debería simular lo que ofrecerían esas API. Estas funciones solo deben estar disponibles para usuarios Avanzados o Pro.</p>
        </li>
    </ol>		

</petclinic:layout>
