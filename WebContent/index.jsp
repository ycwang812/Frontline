<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><bean:message key="title.guestbook" /></title>
	</head>
	<body>
		<center>
			<h2><bean:message key="title.guestbook" /></h2>
			<hr width="80%" />
			
			<%-- Message開始 --%>
			<html:messages id="message" property="successful" message="true">
				<p>${message}</p>
			</html:messages>
			<%-- Message結束 --%>
			
			<br />
			
			<%-- Link開始 --%>
			<p><html:link page="/guestbook/queryMessage.jsp">
				<bean:message key="title.select" />
			</html:link></p>
			<P><html:link page="/guestbook/createMessage.jsp">
				<bean:message key="title.post" />
			</html:link></p>
			<%-- Link結束 --%>

		</center>
	</body>
</html:html>