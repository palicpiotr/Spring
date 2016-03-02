<%-- 
    Document   : delete
    Created on : 24.02.2016, 3:18:16
    Author     : Piotr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Delete pet</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">Delete the choosen pet</h1>
            <form action="<c:url value="/pet/deleteConfirm"/>" method="POST">
                <p>You really want to delete the pet?</p>
                <input type="hidden" name="id" value="${pet.id}"/>
                <input type="submit" value="Delete"/>
            </form>
            <a class="button" href="<c:url value="/pet/pets"/>">Return to main page</a>
        </div>
    </body>
</html>
