//script JavaScript per la JSP '../jsp/registration.jsp'

document.addEventListener("DOMContentLoaded", DOMLoaded);

function DOMLoaded() {
	let form = document.getElementById("registrationForm");
	
	form.submit_button.addEventListener('click', (event) => {
	event.preventDefault();	
	
	let cliente = {
		username : form.username.value,
		password : form.password.value,
		nome: form.nome.value,
		cognome: form.cognome.value,
		email: form.email.value,
		codiceFiscale: form.codiceFiscale.value
	};
	
	if (!cliente.username.match(/^[a-zA-Z0-9_-]{6,30}$/)) {
		form.username.setCustomValidity("L'username deve essere composto da 6-30 caratteri alfanumerici. Opzionali dash(-) e underscore(_)");
		form.username.reportValidity();
		return;
	}
	else if (!cliente.password.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,30}$/)) {
		form.password.setCustomValidity("La password deve essere composta da 6-30 caratteri alfanumerici e da almeno una lettera e una cifra");
		form.password.reportValidity();
		return;
	}
    else if (!cliente.nome.match(/^[a-zA-Z]{2,30}$/)) {
        form.nome.setCustomValidity("Il nome deve essere composto da 2-30 lettere");
        form.nome.reportValidity();
        return;
    }
    else if (!cliente.cognome.match(/^[a-zA-Z0-9_-]{2,30}$/)) {
        form.cognome.setCustomValidity("Il cognome deve essere composta da 2-30 lettere");
        form.cognome.reportValidity();
        return;
    }
    else if (!cliente.email.match(/^(?=.{1,100}$)[\w.-]+@\w+(\.\w{2,3})+$/)) {
        form.email.setCustomValidity("L'email deve essere max 100 caratteri e della forma: esempio@email.com");
        form.email.reportValidity();
        return;
    }
    else if (!cliente.codiceFiscale.match(/^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/)) {
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
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200)
            //l'utente si è registrato con successo. Ora bisogna vedere cosa fare
            console.log("Insert query completata");
        else if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 400)
            document.getElementById("formSubmitResultMessage").textContent = "Esiste un account con username " + cliente.username + ". Riprova";
    };
	xhr.send(JSON.stringify(cliente));
	});
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