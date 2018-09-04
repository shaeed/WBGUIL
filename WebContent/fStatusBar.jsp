<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fStatusBar</title>
<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/jquery.ui.all.css" />
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>

<style type="text/css">
#window-footer-status-bar {
	position: absolute;
	bottom: 5px;
	width: 98%;
	float: left;
}

#properties-button {
	float: right;
	font-size: 62.5%;
}
</style>

<script>
	$(function() {
		$("#pro").button({
			text : false,
			icons : {
				primary : "ui-icon-carat-1-n"
			}
		});
		var x = $(window).width();
		x = x*.99;
		$("#dialog").dialog({
			autoOpen : false,
			width: x ,
			show : {
				effect : "clip",
				duration : 1000
			},
			hide : {
				effect : "clip",
				duration : 1000
			},
			position: { my: "left bottom", at: "left bottom" }
		});
		$("#pro").click(function() {
			$("#dialog").dialog("open");
		});
		
		//Ajax function for getting the propreties
		$("pro").click(function() {
			$.ajax({
				url : "demo_test.txt",
				success : function(result) {
					$("#div1").html(result);
				}
			});
		});
	});
</script>

</head>
<body>
	<div id="window-footer-status-bar">
		<hr>
		status bar
		<div id="properties-button">
			<button id="pro" onClick="showProperties()">Show Properties
				..</button>
			<script type="text/javascript">
				function showProperties() {
					var xmlhttp = new XMLHttpRequest(); 
					xmlhttp.onreadystatechange = function() {
						if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
							document.getElementById("dialog").innerHTML = xmlhttp.responseText;
						}
					}
					xmlhttp.open("GET", "property.jsp", true);
					xmlhttp.send();

				}
			</script>
		</div>
	</div>
	<div style="position: absolute; bottom: 2px; width: 98%;"
		class="ui-widget-header"></div>


	<div id="dialog" title="Propreties: <s:property value='#session.windowTitle'
				default='Untitled' />">
		<img src="image/fcon/waitProp.gif" />
	</div>

</body>
</html>
