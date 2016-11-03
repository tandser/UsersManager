<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
        <title>Users Manager</title>
    </head>

    <body>
        <div class="container">
            <br/>

            <div class="well">
                <strong>
                    <a href="<c:url value='/users'/>">Back</a>&nbsp;&nbsp;|&nbsp;&nbsp;<c:out value="${selection.size()}"/> matches of &laquo;<c:out value="${name}"/>&raquo;
                </strong>
            </div>

            <c:if test="${!empty selection}">
            <table class="table table-stripped">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Admin</th>
                    <th>Created</th>
                    <th></th>
                    <th></th>
                </tr>

                <c:forEach items="${selection}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.age}</td>
                    <td>${item.isAdmin}</td>
                    <td>${item.createdDate}</td>
                    <td></td>
                    <td><a href="<c:url value='/remove/${item.id}'/>">Remove</a></td>
                </tr>
                </c:forEach>
            </table>
            </c:if>
        </div>

        <script type="text/javascript" src="/js/jquery.js"></script>
        <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    </body>
</html>