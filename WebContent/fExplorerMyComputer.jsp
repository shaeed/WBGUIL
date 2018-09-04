<%@page import="java.util.Iterator"%>
<%@page import="beans.DriveDetails"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fExplorer</title>

<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/jquery.ui.all.css" />
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>


<jsp:include page="fWindowHeader.jsp"></jsp:include>
<jsp:include page="fButtonHeader.jsp"></jsp:include>
<jsp:include page="fStatusBar.jsp"></jsp:include>

<script>
	$(function() {
		$("input[type=submit] , #a1").button().click(function(event) {
			
		});
	});
</script>

</head>
<body>

	<br>
	<h3><s:text name="lang.UserFolders"></s:text></h3>
	<hr>
	<!-- Getting the userFolders List -->
	<s:set name="x" value="#session.userFolders"></s:set>
	<s:iterator id="userFolder" value="x">

		<s:url action="openFolder" id="open">
			<s:param name="path">
				<s:property value="address"></s:property>
			</s:param>
		</s:url>
		<s:a href="%{open}" id="a1">
			<s:property value="name"></s:property>
		</s:a>
		
	</s:iterator>
	<br>
	<h4><s:text name="lang.Drives"></s:text></h4>
	<hr>
	<table>
		<tr>
			<td>Drive</td>
			<td>File System</td>
			<td>Total Size</td>
			<td>Used space</td>
			<td>Available Space</td>
			<td>Percentage full</td>
		</tr>
		<%
			List<DriveDetails> drives = (List<DriveDetails>) session.getAttribute("drives");
			Iterator<DriveDetails> it = drives.iterator();
			int x = 0;
			while (it.hasNext()) {
				x++;
				DriveDetails temp = (DriveDetails) it.next();

				out.println("<tr>");
				out.println("<td><form action='openFolder' method='get'>");
				out.println("<input type='text' name='path' value='"
						+ temp.address + "' hidden />");
				out.println("<input type='submit' value='" + temp.name
						+ "'></form>");
				out.println("</td>");
				out.println("<td>" + temp.fileSystem + "</td>");
				out.println("<td>" + temp.totalSize + "B</td>");
				out.println("<td>" + temp.usedSpace + "B</td>");
				out.println("<td>" + temp.availableSpace + "B</td>");
				out.println("<td><div id='prog" + x + "'></div>");
				out.println("<script>");
				out.println("$(function() {");
				out.println("$(\"#prog" + x + "\").progressbar({ max : 100,");
				out.println("value :" + temp.usedPercentage + "	});});");
				out.println("</script></td>");
				out.println("</tr>");
			}
		%>
	</table>

</body>
</html>