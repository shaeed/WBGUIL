<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fButtonHeader</title>
<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/jquery.ui.all.css" />
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>

<style type="text/css">
#fbutton-header {
	position: relative;
	margin-left: -8px;
	margin-right: -8px;
	height: 38px;
}

#back-forward {
	margin-left: 10px;
	width: 140px;
	float: left;
}

#address-bar {
	float: left;
	margin-left: 20px;
	margin-top: 10px;
	float: left;
}
​
</style>

</head>
<body>
	<!-- Back, Forward And refresh Button -->
	<s:if test="backButton != ''">
	<s:url action="back" id="backButton1">
		<s:param name="backButton">
			<s:property value="backButton"></s:property>
		</s:param>
	</s:url>
	</s:if>
	<s:if test="backButton != ''">
	<s:url action="forward" id="forwardButton1">
		<s:param name="forwardButton">
			<s:property value="forwardButton"></s:property>
		</s:param>
	</s:url>
	</s:if>
	<s:if test="backButton != ''">
	<s:url action="refresh" id="refresh1">
		<s:param name="refresh">
			<s:property value="refresh"></s:property>
		</s:param>
	</s:url>
	</s:if>

	<div id="fbutton-header" class="ui-widget-header">
		<div id="back-forward" class="ui-widget-header ui-corner-all">
			<s:a href="%{backButton1}" id="back">
				<s:text name="lang.Back"></s:text>
			</s:a>
			<s:a href="%{forwardButton1}" id="forward">
				<s:text name="lang.Forward"></s:text>
			</s:a>
			<s:a href="%{refresh1}" id="refresh">
				<s:text name="lang.Refresh"></s:text>
			</s:a>
		</div>
		<div id="address-bar">
			<s:property value="#session.fpath" default="" />	
		</div>
	</div>
	
	<script>
	$(function() {
		$("#back").button({
			text : false,
			icons : {
				primary : "ui-icon-circle-arrow-w"
			}
		});
		$("#forward").button({
			text : false,
			icons : {
				primary : "ui-icon-circle-arrow-e" 
			}
		});
		$("#refresh").button({
			text : false,
			icons : {
				primary : "ui-icon-refresh"
			}
		});
	});
</script>
	
</body>
</html>