<%-- 
    Document   : show
    Created on : 24.02.2016, 3:21:41
    Author     : Piotr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>View the pet</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">About the pet</h1>
            <p>The pet <b>${pet.name}</b> is animal of owner <b>${pet.owner}</b> 
                and this animal is the <b>${pet.species}</b> and the sex of the pet is <b>${pet.sex}</b>
                so this pat was born in <b>${pet.birth}</b> and unfortunately the animal  <b>${pet.name}</b>
            was died in  <b>${pet.death}</b></p>
            <a class="button" href="<c:url value="/pet/pets"/>">Return to main page</a>
        </div>
    </body>
</html>
