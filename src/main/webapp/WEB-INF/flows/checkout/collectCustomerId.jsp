<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Customer</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Customer</h1>
				<p>Customer ID</p>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="order.customer" class="form-horizontal">
			<fieldset>
				<legend>Customer Id</legend>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="customerId" />Enter Customer Id</label>
					<div class="col-lg-10">
						<form:input id="customerId" path="customerId" type="text" class="form:input-large" />
					</div>
				</div>


				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
				
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="Add" name="_eventId_customerIdCollected" />
						<button id="btnCancel" class="btn btn-default" name="_eventId_cancel">Cancel</button>
					</div>
				</div>

			</fieldset>
		</form:form>
	</section>
</body>
</html>
