<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><bean:message key="title.post.message" /></title>
		<html:javascript formName="createMessage" />
		
		<!--button的JavaSript-->
		<script type="text/javascript">
			function back(){
				location.href='http://<%=request.getLocalName() + ":" + request.getLocalPort() + request.getContextPath()%>';
			}
		</script>

		<!-- CSS -->
		<style type="text/css">
			.class1 {background-color: #FFFFCC}
			.class2 {color: red}
		</style>
	</head>
	<body>
		<center>
			<h2><bean:message key="title.post.message" /></h2>
			<hr width="80%" />
			<html:form action="/createMessage" method="post" onsubmit="return validateCreateMessage(this)">
				<table class="class1">
					<tr>
						<td align="right">
							<bean:message key="title.name" />
							<bean:message key="title.colon" />
						</td>
						<td align="left">
							<html:text property="name" />
						</td>
					</tr>

					<%-- name錯誤訊息開始 --%>
					<html:messages id="nameMsg" property="nameErr">
						<tr>
							<td></td>
							<td align="left" class="class2">${nameMsg}</td>
						</tr>
					</html:messages>
					<%-- name錯誤訊息結束 --%>

					<tr>
						<td align="right">
							<bean:message key="title.date" />
							<bean:message key="title.colon" />
						</td>
						<td align="left">
							<html:text property="date" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="title.message" />
							<bean:message key="title.colon" />
						</td>
						<td align="left">
							<html:textarea property="message" rows="6" cols="40" />
						</td>
					</tr>

					<%-- name錯誤訊息開始 --%>
					<html:messages id="msgMsg" property="msgErr">
						<tr>
							<td></td>
							<td align="left" class="class2">${msgMsg}</td>
						</tr>
					</html:messages>
					<%-- name錯誤訊息結束 --%>

					<tr>
						<td></td>
						<td>
							<html:submit>
								<bean:message key="title.submit" />
							</html:submit>
							<html:button property="cancel" onclick="back(this.form)">
								<bean:message key="title.cancel" />
							</html:button>
						</td>
					</tr>
				</table>
			</html:form>
		</center>
	</body>
</html:html>