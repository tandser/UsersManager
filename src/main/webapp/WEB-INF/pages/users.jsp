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
        <nav class="navbar navbar-default">
            <div class="container">
                <form:form method="post" action="/users/selection" modelAttribute="template">
                    <table>
                        <tr>
                            <td height="50" width="165"><form:input path="name" placeholder=" Search by name"/></td>
                            <td width="70"><input type="submit" value="Search"/></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </nav>

        <div class="container">
            <br/>

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

                <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.age}</td>
                    <td>${item.isAdmin}</td>
                    <td>${item.createdDate}</td>
                    <td>
                        <c:if test="${empty offset}"><a href="<c:url value='/update/${item.id}/offset/0'/>">Edit</a></c:if>
                        <c:if test="${!empty offset}"><a href="<c:url value='/update/${item.id}/offset/${offset}'/>">Edit</a></c:if>
                    </td>
                    <td><a href="<c:url value='/remove/${item.id}'/>">Remove</a></td>
                </tr>
                </c:forEach>

                <c:set var="size" value="${list.size()}"/>

                <c:forEach begin="1" end="${10 - size}">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                </c:forEach>
            </table>

            <tag:paginate max="15" offset="${offset}" count="${count}" uri="/users" next="&raquo;" previous="&laquo;"/>

            <div class="well"><strong>Users Manager</strong></div>

            <form:form method="post" action="/users/add" modelAttribute="user">
            <table>
                <tr>
                    <td width="50" height="35"><form:label path="id">ID</form:label></td>
                    <td><form:input path="id" readonly="true" disabled="true"/><form:hidden path="id"/></td>
                    <td></td>
                </tr>

                <tr>
                    <td width="50" height="35"><form:label path="name">Name</form:label></td>
                    <td><form:input path="name"/></td>
                    <td>&nbsp;&nbsp;Name must match pattern "\w{1,25}"</td>
                </tr>

                <tr>
                    <td width="50" height="35"><form:label path="age">Age</form:label></td>
                    <td><form:input path="age"/></td>
                    <td>&nbsp;&nbsp;Age value must be between 1 and 150</td>
                </tr>

                <tr>
                    <td width="50" height="35"><form:label path="isAdmin">Admin</form:label></td>
                    <td>
                        <form:select path="isAdmin">
                            <form:option value="0">false</form:option>
                            <form:option value="1">true</form:option>
                        </form:select>
                    </td>
                    <td></td>
                </tr>

                <tr>
                    <td width="50" height="35"></td>
                    <td>
                        <c:if test="${!empty user.name}"><input type="submit" value="Update"/></c:if>
                        <c:if test="${empty user.name}"><input type="submit" value="Create"/></c:if>
                    </td>
                    <td></td>
                </tr>
            </table>
            </form:form>

        </div>

        <script type="text/javascript" src="/js/jquery.js"></script>
        <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    </body>
</html>