<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<title>Registration</title>
	</head>
	<body>
		<h1>registrati</h1>
		<form id="registrationForm" action="/Vapor/RegistrationServlet" method="post">
			<input type="text" id="username" name="username" placeholder="username" title="Gianni � scemo" required><br>
			<input type="text" id="password" name="password" placeholder="password" required><br>
			<input type="text" id="nome" name="nome" placeholder="nome" required><br>
			<input type="text" id="cognome" name="cognome" placeholder="cognome" required><br>
			<input type="email" id="email" name="email" placeholder="email" required><br>
			<input type="text" id="codiceFiscale" name="codiceFiscale" placeholder="codiceFiscale" required><br>
			<input type="button" id="submit_button" value="submit">
		</form>
		<p id="formSubmitResultMessage"></p>
		
		
		<script src="../scripts/formSubmissionsHandler.js"></script>
	</body>
</html>