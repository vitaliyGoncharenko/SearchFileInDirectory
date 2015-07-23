<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title></title>
</head>
<body>

<h2>New Report</h2>

<h1>Start Search</h1>>
<form:form action="startSearch" method="post" modelAttribute="directory">
<table>
<tr>
    <td  valign=top>
        Input directory*

        <input type="text" name="nameDirectory" value="" size=15 maxlength=20></td>
<tr>
<td colspan="2" align="center"><input type="submit" value="StartSearch"></td>
</tr>
</table>
</form:form>

</body>
</html>
