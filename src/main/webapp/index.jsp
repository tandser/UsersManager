<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <div class="well" align="center">
                <strong><a href="<c:url value='/generate'/>">rand</a></strong>
                &nbsp;&nbsp;|&nbsp;&nbsp;
                <strong><a href="<c:url value='/users'/>">next</a></strong>
            </div>
        </div>
    </body>
</html>