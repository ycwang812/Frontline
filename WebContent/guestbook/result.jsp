<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.frontline.model.Guestbook"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><bean:message key="title.message.result" /></title>

		<!--button的JavaSript-->
		<script type="text/javascript">
			// 取消button功能
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
			<h2><bean:message key="title.message.result" /></h2>
			<hr width="80%" />

			<%-- 錯誤訊息處理開始 --%>
			<html:messages id="resultMsg" property="resultMessage" message="true">
				<table>
					<tr>
						<td align="left" class="class2">${resultMsg}</td>
					</tr>
				</table>
			</html:messages>
			<%-- 錯誤訊息處理結束 --%>
			
			<%--查詢留言結果處理開始--%>
			<c:if test="${not empty requestScope.result}">
				<c:set var="i" value='${pageScope.i+1}' />
				<table border="0" width="600">
					<thead class='class<c:out value="${pageScope.i % 2}" />'>
						<th width="15%">
							<bean:message key="title.date" />
						</th>
						<th width="15%">
							<bean:message key="title.name" />
						</th>
						<th width="50%">
							<bean:message key="title.message" />
						</th>
						<th width="10%"></th>
						<th width="10%"></th>
					</thead>
					<c:forEach var="result" items="${requestScope.result}">
						<c:set var="i" value='${pageScope.i+1}' />
						<tr class='class<c:out value="${pageScope.i % 2}" />'>
							<html:form action="/editMessage" method="post">
								<html:hidden property="queryName" value="${queryName}" />
								<html:hidden property="startDate" value="${startDate}" />
								<html:hidden property="endDate" value="${endDate}" />
								<html:hidden property="queryMessage" value="${queryMessage}" />
								<html:hidden property="id" value="${result.id}" />
								<td align="center">${result.date}</td>
								<td align="center">${result.name}</td>
								<td align="left">${result.message}</td>
								<td align="center">
									<html:submit property="method">
										<bean:message key="title.update" />
									</html:submit>
								</td>
								<td align="center">
									<html:submit property="method">
										<bean:message key="title.delete" />
									</html:submit>
								</td>
							</html:form>
						</tr>
					</c:forEach>
				</table>
			</c:if>

			<%--查詢留言結果處理結束--%>
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