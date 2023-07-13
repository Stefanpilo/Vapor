<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<title>Registration</title>
	</head>
	<body>
		<h1>registrati</h1>
		<form action="RegistrationServlet" method="post">
			<input type="text" id="name" name="name" placeholder="name"><br>
			<input type="text" id="surname" name="surname" placeholder="surname"><br>
			<input type="text" id="username" name="username" placeholder="username"><br>
			<input type="email" id="email" name="email" placeholder="email">
			<input type="submit" value="submit">
		</form>
	</body>
</html>