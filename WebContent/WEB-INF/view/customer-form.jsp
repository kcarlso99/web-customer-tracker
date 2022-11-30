<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 	<!-- taglib required to use Spring tags -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Save Customer</title>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
	
</head>
<body>

	<div id="wrapper">
	
		<div id="header">
		
			<h2>CRM - Customer Relationship Manager</h2>
			
		</div>
			
			<!-- Spring Form tag -->
			<!-- Spring will call saveCustomer method in Controller and pass the Customer model attribute -->
			<form:form action="saveCustomer" modelAttribute="customer" method="POST">
			
				<!-- Need to save the Customer ID. Without this, we would lose the ID. -->
				<form:hidden path="id" />
			
				<label>First Name:</label>
				
				<!-- Spring input tag. Automatically calls getFirstName and setFirstName on customer Model Attribute -->
				<form:input path="firstName" /> <br/><br/>
				
				<label>Last Name:</label>
				<form:input path="lastName" /><br/><br/>
				
				<label>UIN:</label>
				<form:input path="uin" /><br/><br/>
				
				<input type="submit" value="Save" class="save" />
			
			</form:form>
		
		
			<div style="clear;both;">
				<p>
					<a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
				</p>
			</div>
		
	</div>

</body>
</html>