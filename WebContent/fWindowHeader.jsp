<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fWindowHeader</title>

<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/jquery.ui.all.css" />
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>

<script>
	$(function() {
		$("#close").button({
			text : false,
			icons : {
				primary : "ui-icon-circle-close"
			}
		});
		$("#minimize").button({
			text : false,
			icons : {
				primary : "ui-icon-minus"
			}
		});
		$("#maximize").button({
			text : false,
			icons : {
				primary : "ui-icon-arrow-4-diag"
			}
		}).click(function() {
			var options;
			if ($(this).text() === '<s:text name="lang.Maximize"></s:text>') {
				options = {
					label : '<s:text name="lang.Restore"></s:text>',
					icons : {
						primary : "ui-icon-newwin"
					}
				};
			} else {
				options = {
					label : '<s:text name="lang.Maximize"></s:text>',
					icons : {
						primary : "ui-icon-arrow-4-diag"
					}
				};
			}
			$(this).button("option", options);
		});
	});
</script>
<style type="text/css">
#main-toolbar {
	height: 38px;
	margin-top: -8px;
	margin-left: -8px;
	margin-right: -8px;
}

#toolbar {
	width: 170px;
	margin-top: 0px;
	margin-right: 0px;
	float: right;
	padding: 4px;
	display: inline-block;
	font-size: 62.5%;
	font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana",
		"sans-serif";
}

#language {
	margin-right: 25px;
}

#window-title {
	margin-top: 10px;
	margin-left: 50px;
	position: absolute;
}

.close-icon {
	background-image: url(image/icon/close.png) !important;
	width: 16px;
	height: 16px;
}
</style>
</head>
<body>

	<div id="main-toolbar" class="ui-widget-header">
		<b id="window-title"> <s:property value="#session.windowTitle"
				default="Untitled" /></b>
		<div id="toolbar" class="ui-widget-header">
			<s:url action="changeLang" id="english">
				<s:param name="request_locale">en</s:param>
			</s:url>
			<s:url action="changeLang" id="hindi">
				<s:param name="request_locale">hi</s:param>
			</s:url>
			<s:a href="%{english}">English</s:a>
			<s:a href="%{hindi}">Hindi</s:a>


			<button id="minimize" onclick="minimize12()">
				<s:text name="lang.Minimize"></s:text>
			</button>
			<button id="maximize" onclick="maximize12()">
				<s:text name="lang.Maximize"></s:text>
			</button>
			<button id="close" onclick="closeMe()">
				<s:text name="lang.Close"></s:text>
			</button>
			<script>
				function closeMe() {
					var win = window.open("", "_self");
					win.close();
				}
				function minimize12() {
					window.moveTo(1280, 1024);
					window.resizeTo(screen.width, screen.height);
				}
				function maximize12() {
					  window.moveTo(0, 0);
					  window.resizeTo(screen.width, screen.height);
				}
			</script>
		</div>
	</div>

</body>
</html>