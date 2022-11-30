<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>List Customers</title>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>

	<div id="wrapper">
		<div id="header">
		
			<h2>CRM - Customer Relationship Manager</h2>
		
		</div>
		
		<div id ="container">
			<div id="content">
			
				<!-- Add Customer Button -->
				<!-- When button clicked, will call Spring controller mapping for showFormForAdd -->
				<input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;"
					class="add-button" />
					
					<!--  add a search box using Spring Search function -->
            		<form:form action="search" method="POST">
                		Search customer by name: <input type="text" name="theSearchName" />
                
                		<input type="submit" value="Search" class="add-button" />
            		</form:form>
			
					<!-- HTML table -->
					<table>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>UIN</th>
							<th>Action</th>
						</tr>
						
						<!-- customers is the Attribute name from our Model -->
				  		<c:forEach var="tempCustomer" items="${customers}" >
				  		
				  			<!--  Create Update Link for each Customer -->
				  			<c:url var="updateLink" value="/customer/showUpdateForm">
				  				<c:param name="customerId" value="${tempCustomer.id}" />
				  			</c:url>
				  			
				  			<!--  Create Delete Link for each Customer -->
				  			<c:url var="deleteLink" value="/customer/delete">
				  				<c:param name="customerId" value="${tempCustomer.id}" />
				  			</c:url>
				  		
							<tr>
								<td>${tempCustomer.firstName}</td>
								<td>${tempCustomer.lastName}</td>
								<td>${tempCustomer.uin}</td>
								<td>
									<a href="${updateLink}">Update</a> <!-- Update Link -->
									|
									<!-- Delete action. Will prompt user with a Confirm window. -->
									<a href="${deleteLink}"
										onclick="if (!(confirm('Are you sure you want to delete this Customer?'))) return false;">Delete</a> 
										
								</td>  
							</tr>
						
						</c:forEach>
					
					</table>
			</div>
			
		</div>
	
	</div>

</body>
</html>