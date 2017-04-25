<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><bean:message key="title.select.message" /></title>

		<!--JavaSript-->
		<script type="text/javascript">
			// 日期防呆功能
			function changeStartDate(startYear, startMonth, startDay){ // 參數傳遞代表表單欄位
				// 先把每個月應有的天數定義成陣列
				var MonthDay = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
				var x = new Date(startYear.value + '/' + startMonth.value + '/' + startDay.value).getFullYear();

				if((x % 3200 != 0) && (x % 400 == 0) || (x % 4 == 0 && x % 100 != 0)){
					MonthDay[1] = 29;
				}

				startDay.length = MonthDay[startMonth.value-1];
				for(var i = 1; i <= startDay.length; i++){
					startDay.options[i-1].text = i;
					startDay.options[i-1].value = i;
				}
			}

			function changeEndDate(endYear, endMonth, endDay){ // 參數傳遞代表表單欄位
				// 先把每個月應有的天數定義成陣列
				var MonthDay = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
				var x = new Date(endYear.value + '/' + endMonth.value + '/' + endDay.value).getFullYear();

				if((x % 3200 != 0) && (x % 400 == 0) || (x % 4 == 0 && x % 100 != 0)){
					MonthDay[1] = 29;
				}

				endDay.length = MonthDay[endMonth.value-1];
				for(var i = 1; i <= endDay.length; i++){
					endDay.options[i-1].text = i;
					endDay.options[i-1].value = i;
				}
			}
		
			// 取消button功能
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
			<h2><bean:message key="title.select.message" /></h2>
			<hr width="80%" />
			<html:form styleId="form1" action="/queryMessage" method="post">
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
					<tr>
						<td align="right">
							<bean:message key="title.date" />
							<bean:message key="title.colon" />
						</td>
						<td align="left">
							<bean:message key="title.from" />
							<bean:message key="title.space" />
							<select name="startYear" id="startYear"	onChange="var form2 = document.form1; changeStartDate(form.startYear, form.startMonth, form.startDay);">
								<script>
									document.write('<option value="">');
									var date = new Date();
									var year = date.getFullYear();
									for (var i = (year - 4); i <= year; i++){
										document.write('<option value="' + i + '">' + i);
		       						}
								</script>
							</select>
							<bean:message key="title.space" />
							<bean:message key="title.year" />
							<bean:message key="title.space" />
							<select name="startMonth" id="startMonth" onChange="var form3 = document.form1; changeStartDate(form.startYear, form.startMonth, form.startDay);">
								<script>
									document.write('<option value="">');
									for(var i = 1; i < 13; i++){
										document.write('<option value="' + i + '">' + i);
									}
								</script>
							</select>
							<bean:message key="title.space" />
							<bean:message key="title.month" />
							<bean:message key="title.space" />
							<select name="startDay" id="startDay">
								<script>
									document.write('<option value="">');
									for(var i = 1; i <= 31; i++){
										document.write('<option value="' + i + '">' + i);
									}
								</script>
							</select>
							<bean:message key="title.space" />
							<bean:message key="title.day.to" />
							<bean:message key="title.space" />
							<select name="endYear" id="endYear" onChange="var form4 = document.form1; changeEndDate(form.endYear, form.endMonth, form.endDay);">
								<script>
									document.write('<option value="">');									
									for (var i = (year - 4); i <= year; i++){
										document.write('<option value="' + i + '">' + i);
		       						}
								</script>
							</select>
							<bean:message key="title.space" />
							<bean:message key="title.year" />
							<bean:message key="title.space" />
							<select name="endMonth" id="endMonth" onChange="var form5 = document.form1; changeEndDate(form.endYear, form.endMonth, form.endDay);">
								<script>
								document.write('<option value="">');
									for(var i = 1; i < 13; i++){
										document.write('<option value="' + i + '">' + i);
									}
								</script>
							</select>
							<bean:message key="title.space" />
							<bean:message key="title.month" />
							<bean:message key="title.space" />
							<select name="endDay" id="endDay">
								<script>
									document.write('<option value="">');
									for(var i = 1; i <= 31; i++){
										document.write('<option value="' + i + '">' + i);
									}
								</script>
							</select>
							<bean:message key="title.space" />
							<bean:message key="title.day.end" />
						</td>
					</tr>

					<%-- date錯誤訊息開始 --%>
					<html:messages id="dateMsg" property="dateErr">
						<tr>
							<td></td>
							<td align="left" class="class2">${dateMsg}</td>
						</tr>
					</html:messages>
					<%-- name錯誤訊息結束 --%>

					<tr>
						<td align="right">
							<bean:message key="title.message" />
							<bean:message key="title.colon" />
						</td>
						<td align="left">
							<html:textarea property="message" rows="6" cols="40" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<html:submit>
								<bean:message key="title.submit" />
							</html:submit>
							<input type="button" value='<bean:message key="title.cancel"/>'	onclick="back(this.form)" />
						</td>
					</tr>
				</table>
			</html:form>
		</center>
	</body>
</html:html>