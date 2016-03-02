<%-- 
    Document   : pets
    Created on : 24.02.2016, 3:14:49
    Author     : Piotr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>       
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Список животных</h1>
            <table class="data_table">
                <tr>
                    <th>Name</th>
                    <th>Owner</th>
                    <th>Species</th>
                    <th>Sex</th>
                    <th>Date Birth</th>
                    <th>Date Death</th>
                    
                </tr>
                <c:forEach var="pet" items="${pets}">
                    <tr>
                        <td>${pet.name}</td>
                        <td>${pet.owner}</td>
                        <td>${pet.species}</td>
                        <td>${pet.sex}</td>
                        <td>${pet.birth}</td>
                        <td>${pet.death}</td>
                        <td><a href="<c:url value="${pet.id}"/>">View all</a></td>
                        <td><a href="<c:url value="${pet.id}/edit"/>">Edit</a></td>
                        <td><a href="<c:url value="${pet.id}/delete"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a class="button" href="<c:url value="/add"/>">Add new pet</a>
        </div>
    </body>
</html>
