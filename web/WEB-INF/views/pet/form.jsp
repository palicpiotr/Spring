<%-- 
    Document   : form
    Created on : 24.02.2016, 3:09:35
    Author     : Piotr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>${title}</title>
    </head>
    <body>
        <div class="main">
            <h1 class="title">${title}</h1> 
            <form action="<c:url value="${sendURL}"/>" method="POST">
                <c:if test="${pet.id != null}">
                    <input type="hidden" name="id" value="${pet.id}"/>
                </c:if>
                <table class="table_form"> 
                    <tr>
                        <td><label for="temp">Name</label></td>
                        <td><input id="name" name="name" type="text" value="${pet.name}"/></td>
                    </tr>
                    <tr>
                        <td><label for="owner">Owner</label></td>
                        <td><input id="owner" name="owner" type="text" value="${pet.owner}"/></td>
                    </tr>
                    <tr>
                        <td><label for="species">Species</label></td>
                        <td><input id="species" name="species" type="text" value="${pet.species}"/></td>
                    </tr>
                    <tr>
                        <td><label for="sex">Sex</label></td>
                        <td><input id="sex" name="sex" type="text" value="${pet.sex}"/></td>
                    </tr>
                    <tr>
                        <td><label for="birth">Date Birth</label></td>
                        <td><input id="birth" name="birth" type="text" value="${pet.birth}"/></td>
                    </tr>
                    <tr>
                        <td><label for="birth">Date Death</label></td>
                        <td><input id="death" name="death" type="text" value="${pet.death}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Save"/></td>
                    </tr>
                </table>
            </form>
            <a class="button" href="<c:url value="/pet/pets"/>">Return to main page</a>
        </div>
    </body>
</html>
