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

<style type="text/css">
.folder-outer {
	height: 130px;
	width: 120px; //
	background-color: green;
	float: left;
	margin: 10px;
	text-align: center;
	word-wrap: break-word;
}

.folder-inner {
	margin-bottom: 20px;
	height: 100px;
	width: 120px; //
	background-color: gray;
	text-align: center;
}

.folder-name {
	background-color: light gray;
}

title {
	word-wrap: break-word;
}

#window-footer-status-bar {
	//position: absolute;
	bottom: 5px;
	width: 98%;
	float: left;
	margin-top: 100px;
}

#properties-button {
	float: right;
	font-size: 62.5%;
}
</style>

<script>
	$(function() {
		$(document).tooltip({
			track : true
		});
	});
</script>

</head>
<body>
	<!-- Files and Folders List -->
	<s:set name="filesFolders" value="#session.filesFolders"></s:set>
	<s:iterator id="ff" value="filesFolders">
		<div class="folder-outer"
			title='<s:property value="name"></s:property> (<s:property value="size"></s:property>)'>
			<div class="folder-inner">
				<s:if test="type == 'folder.type'">
					<s:url action="openFolder" id="open">
						<s:param name="path">
							<s:property value="address"></s:property>
						</s:param>
					</s:url>
					<s:a href="%{open}">
						<img src="image/fcon/<s:property value='type' />_64_64.png"
							height="100" width="110" />
					</s:a>
				</s:if>
				<s:else>
					<s:url action="openFile" id="openF">
						<s:param name="path">
							<s:property value="address"></s:property>
						</s:param>
						<s:param name="viewer">
							<s:property value="type"></s:property>
						</s:param>
					</s:url>
					<s:a href="%{openF}" target="_blank">
						<s:if test="#session.icons.contains(type)">
							<img src="image/fcon/<s:property value='type' />_64_64.png"
								height="100" width="110" />
						</s:if>
						<s:else>
							<img src="image/fcon/unknown_64_64.png" height="100" width="110" />
						</s:else>
					</s:a>
				</s:else>
			</div>
			<div class="folder-name">
				<s:property value="name" />
			</div>
		</div>
	</s:iterator>
	<br>

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


	<div id="dialog"
		title="Propreties: <s:property value='#session.windowTitle'
				default='Untitled' />">
		<img src="image/fcon/waitProp.gif" />
	</div>

	<script>
		$(function() {
			$("#pro").button({
				text : false,
				icons : {
					primary : "ui-icon-carat-1-n"
				}
			});
			var x = $(window).width();
			x = x * .99;
			$("#dialog").dialog({
				autoOpen : false,
				width : x,
				show : {
					effect : "clip",
					duration : 1000
				},
				hide : {
					effect : "clip",
					duration : 1000
				},
				position : {
					my : "left bottom",
					at : "left bottom"
				}
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

</body>
</html>