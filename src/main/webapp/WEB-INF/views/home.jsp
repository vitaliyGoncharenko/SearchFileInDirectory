<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Directory Manager Home</title>
</head>
<body>
<div align="center">

    <h2>Start Search</h2>
    <form:form action="startSearch" method="post" modelAttribute="directory">
        <table>
            <tr>
                <td valign=top>
                    Enter the path on which will be searched: <input type="text" name="nameDirectory" value="" size=20 maxlength=auto></td>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="StartSearch"></td>
            </tr>
        </table>
    </form:form>
    <%
        String report = (String) request.getAttribute("report");
        if (report != null) {
            out.println(report);
        }
    %>

    <h2>Report List</h2>

    <table border="1">
        <th>No</th>
        <th>Name Path</th>
        <th>Count folders</th>
        <th>Count files</th>
        <th>Action</th>

        <c:forEach var="report" items="${listReport}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${report.namePath}</td>
                <td>${report.countFolders}</td>
                <td>${report.countFiles}</td>
                    <%--<td>${report.telephone}</td>--%>
                <td>
                    <a href="editReport?id=${report.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deleteReport?id=${report.id}">Delete</a>
                </td>

            </tr>
        </c:forEach>
    </table>
    <h3><a href="newReport">New Report</a></h3>

</div>

</body>
</html>
