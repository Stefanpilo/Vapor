//script JavaScript per la JSP '../jsp/registration.jsp'

document.addEventListener("DOMContentLoaded", DOMLoaded);

function DOMLoaded() {
	let registrationForm = document.getElementById("registrationForm");
	let loginForm = document.getElementById("loginForm");
	let messageViewer = document.getElementById("messageViewer");
	if (registrationForm) {
		registrationForm.submit_button.addEventListener('click', (event) => {
			event.preventDefault();	
			
			let cliente = {
				username : registrationForm.username.value,
				password : registrationForm.password.value,
				nome: registrationForm.nome.value,
				cognome: registrationForm.cognome.value,
				email: registrationForm.email.value,
				codiceFiscale: registrationForm.codiceFiscale.value
			};
			
			if (!cliente.username.match(/^[a-zA-Z0-9_-]{6,30}$/) && (cliente.username === "admin")) {
				registrationForm.username.setCustomValidity("L'username deve essere composto da 6-30 caratteri alfanumerici. Opzionali dash(-) e underscore(_). Non valido: admin");
				registrationForm.username.reportValidity();
				return;
			}
			else if (!cliente.password.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,30}$/)) {
				registrationForm.password.setCustomValidity("La password deve essere composta da 6-30 caratteri alfanumerici e da almeno una lettera e una cifra");
				registrationForm.password.reportValidity();
				return;
			}
			else if (!cliente.nome.match(/^[a-zA-Z]{2,30}$/)) {
				registrationForm.nome.setCustomValidity("Il nome deve essere composto da 2-30 lettere");
				registrationForm.nome.reportValidity();
				return;
			}
			else if (!cliente.cognome.match(/^[a-zA-Z0-9_-]{2,30}$/)) {
				registrationForm.cognome.setCustomValidity("Il cognome deve essere composta da 2-30 lettere");
				registrationForm.cognome.reportValidity();
				return;
			}
			else if (!cliente.email.match(/^(?=.{1,100}$)[\w.-]+@\w+(\.\w{2,3})+$/)) {
				registrationForm.email.setCustomValidity("L'email deve essere max 100 caratteri e della forma: esempio@email.com");
				registrationForm.email.reportValidity();
				return;
			}
			else if (!cliente.codiceFiscale.match(/^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/)) {
				registrationForm.codiceFiscale.setCustomValidity("Struttura del codice fiscale non valida");
				registrationForm.codiceFiscale.reportValidity();
				return;
			}
			
			
			let xhr = createXMLHTTPRequest();
			if (!xhr)
				return;
				
			xhr.open(registrationForm.method, registrationForm.action, true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE) {
					if (xhr.status === 200) {
						alert("Registrato con successo!\nSarai redirezionato come utente loggato!");
						location.assign("/Vapor");
					}
					else if (xhr.status === 400) {
						messageViewer.style.display = "block";
						messageViewer.innerHTML = "Esiste un account con username " + cliente.username + ". Riprova";
					}
				}
			};
			xhr.send(JSON.stringify(cliente));
		});
	}
	else if (loginForm) {
		loginForm.submit_button.addEventListener('click', (event) => {
			event.preventDefault();

			let cliente = {
				username : loginForm.username.value,
				password : loginForm.password.value,			
			}
			
			if (!cliente.username.match(/^[a-zA-Z0-9_-]{6,30}$/) && !(cliente.username === "admin")) {
				loginForm.username.setCustomValidity("L'username deve essere composto da 6-30 caratteri alfanumerici. Opzionali dash(-) e underscore(_)");
				loginForm.username.reportValidity();
				return;
			}
			else if (!cliente.password.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,30}$/) && !(cliente.password === "admin")) {
				loginForm.password.setCustomValidity("La password deve essere composta da 6-30 caratteri alfanumerici e da almeno una lettera e una cifra");
				loginForm.password.reportValidity();
				return;
			}

			let xhr = createXMLHTTPRequest();
			if (!xhr)
				return;

			xhr.open(loginForm.method, loginForm.action + "?username=" + encodeURIComponent(cliente.username) + "&password=" + encodeURIComponent(cliente.password), true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE) {
					if (xhr.status === 200) {
						alert("Loggato con successo!\nSarai redirezionato come admin!");
						location.assign("/Vapor");
					}
					else if (xhr.status === 201) {
						messageViewer.style.display = "block";
						messageViewer.innerHTML = "devo ancora implementare il login come utente normale";
					}
				}
			};
			xhr.send();
		});
	}
}


function createXMLHTTPRequest() {
	let request;
	try {
		//Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
		request = new XMLHttpRequest();
	}
	catch(e) {
		try {
			//past versions of Internet Explorer
			request = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch(e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
		
	return request;
}