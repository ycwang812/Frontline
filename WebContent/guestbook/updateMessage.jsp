<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><bean:message key="title.update.message" /></title>
		<html:javascript formName="updateMessage" />

		<!--button的JavaSript-->
		<script type="text/javascript">
			// 回首頁button功能	
			function cancel(){
				location.href="http://<%=request.getLocalName() + ":" + request.getLocalPort() + request.getContextPath()%>/goBack.do?method=goBack&queryName=${queryName}&startDate=${startDate}&endDate=${endDate}&queryMessage=${queryMessage}";
			}

			// 回首頁button功能
			function back(){
				location.href="http://<%=request.getLocalName() + ":" + request.getLocalPort() + request.getContextPath()%>";
			}

			// 查詢button功能
			function query(){
				location.href = "http://<%=request.getLocalName() + ":" + request.getLocalPort() + request.getContextPath()%>/guestbook/queryMessage.jsp";
			}

			// 留言button功能
			function create(){
				location.href = "http://<%=request.getLocalName() + ":" + request.getLocalPort() + request.getContextPath()%>/guestbook/createMessage.jsp";
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
			<h2><bean:message key="title.update.message" /></h2>
			<hr width="80%" />
			<html:form action="/updateMessage" method="post" onsubmit="return validateUpdateMessage(this)">
				<html:hidden property="method" value="updateMessage" />
				<html:hidden property="queryName" value="${queryName}" />
				<html:hidden property="startDate" value="${startDate}" />
				<html:hidden property="endDate" value="${endDate}" />
				<html:hidden property="queryMessage" value="${queryMessage}" />
				<html:hidden property="id" value="${requestScope.gb.id}" />
				<table class="class1">
					<tr>
						<td align="right">
							<bean:message key="title.name" />
							<bean:message key="title.colon" />
						</td>
						<td align="left">
							<html:text property="name" readonly="true" value="${requestScope.gb.name}" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="title.date" />
							<bean:message key="title.colon" />
						</td>
						<td align="left">
							<html:text property="date" readonly="true" value="${requestScope.gb.date}" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="title.message" />
							<bean:message key="title.colon" />
						</td>
						<td align="left">
							<html:textarea property="message" rows="6" cols="40" value="${requestScope.gb.message}" />
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
							<html:button property="goBack" onclick="cancel(this.form)">
								<bean:message key="title.cancel" />
							</html:button>
						</td>
					</tr>
				</table>
			</html:form>
			<hr width="80%" />
			<br />
			<html:button property="back" onclick="back()">
				<bean:message key="title.go.home" />
			</html:button>
			<html:button property="query" onclick="query()">
				<bean:message key="title.select" />
			</html:button>
			<html:button property="create" onclick="create()">
				<bean:message key="title.post" />
			</html:button>
		</center>
	</body>
</html:html>