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

<body>
	

<petclinic:layout pageName="search">


<h2>Resultados</h2>

<table class="table table-stripped">
	<thead>
		<tr>
			<th>Título</th>
			<th>Enlace</th>
			<th>Decripción</th>
		</tr>
	</thead>
	<tbody id="data">

	</tbody>
</table>

<script>	

const options = {
		method: 'GET',
		headers: {
			'X-User-Agent': 'desktop',
			'X-Proxy-Location': 'EU',
			'X-RapidAPI-Host': 'google-search3.p.rapidapi.com',
			'X-RapidAPI-Key': '3e8d1fae45msh48519ad2b191e30p1d7736jsn19bf96cc4a8e'
		}
	};
	
const options2 = {
		method: 'GET',
		headers: {
			'X-RapidAPI-Host': 'google-search26.p.rapidapi.com',
			'X-RapidAPI-Key': '3e8d1fae45msh48519ad2b191e30p1d7736jsn19bf96cc4a8e'
		}
	};

	
const url = "${query2}";

	fetch(url, options2).then(
		res => {
			res.json().then(
				data => {
					console.log(data);
					
						var temp = "";
						console.log(data.results);

						data.results.forEach((u) => {
							temp += "<tr>";
							temp += "<td style='width: 250px;'>" + u.title + "</td>";
							temp += "<td style='width: 200px;'><a href = '" + u.link + "'>" + u.link+"</a></td>";
							temp += "<td style='width: 450px;'>" + u.snippet + "</td></tr>";
						})

						document.getElementById("data").innerHTML = temp;
					
				})
		})

</script>
</body>


</petclinic:layout>
