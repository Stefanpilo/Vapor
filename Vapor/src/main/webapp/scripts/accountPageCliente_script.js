function startAccountPageClienteScript() {
	let form = document.getElementById("form");
	let cliente = {
		username : form.username.value,
		password : form.password.placeholder,
		nome : form.nome.placeholder,
		cognome : form.cognome.placeholder,
		email : form.email.placeholder,
		codiceFiscale : form.codiceFiscale.placeholder
	};

	form.password.value = cliente.password;
	form.nome.value = cliente.nome;
	form.cognome.value = cliente.cognome;
	form.email.value = cliente.email;
	form.codiceFiscale.value = cliente.codiceFiscale;

	document.getElementById("submit_button").addEventListener('click', () => {
		event.preventDefault();
		
		cliente.username= form.username.value;
		cliente.password = form.password.value;
		cliente.nome = form.nome.value;
		cliente.cognome = form.cognome.value;
		cliente.email = form.email.value;
		cliente.codiceFiscale = form.codiceFiscale.value;

		let jsonToSend = {
			"DAO type" : "ClienteDAO",
			"cliente" : cliente
		}

		if (!cliente.password.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,30}$/)) {
			form.password.setCustomValidity("La password deve essere composta da 6-30 caratteri alfanumerici e da almeno una lettera e una cifra");
			form.password.reportValidity();
			return;
		}
		if (!cliente.nome.match(/^[a-zA-Z]{2,30}$/)) {
			form.nome.setCustomValidity("Il nome deve essere composto da 2-30 lettere");
			form.nome.reportValidity();
			return;
		}
		if (!cliente.cognome.match(/^[a-zA-Z0-9_-]{2,30}$/)) {
			form.cognome.setCustomValidity("Il cognome deve essere composta da 2-30 lettere");
			form.cognome.reportValidity();
			return;
		}
		if (!cliente.email.match(/^(?=.{1,100}$)[\w.-]+@\w+(\.\w{2,3})+$/)) {
			form.email.setCustomValidity("L'email deve essere max 100 caratteri e della forma: esempio@email.com");
			form.email.reportValidity();
			return;
		}
		if (!cliente.codiceFiscale.match(/^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/)) {
			form.codiceFiscale.setCustomValidity("Struttura del codice fiscale non valida");
			form.codiceFiscale.reportValidity();
			return;
		}

		let xhr = createXMLHTTPRequest();
		if (!xhr)
			return;
		
		xhr.open(form.method, form.action, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					document.getElementById("submitSuccess").style.display = "inline";
					document.getElementById("submitSuccess").innerHTML = xhr.responseText;
				}
			}
		}
		xhr.send(JSON.stringify(jsonToSend));

	});
};
document.addEventListener('DOMContentLoaded', startAccountPageClienteScript);