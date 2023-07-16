document.addEventListener("DOMContentLoaded", DOMLoaded);

function DOMLoaded() {
	document.getElementById("registrationForm").addEventListener('submit', (event) => {
	event.preventDefault();
	let form = event.target;	
	
	let cliente = {
		username : form.username.value,
		passowrd : form.password.value,
		nome: form.nome.value,
		cognome: form.cognome.value,
		email: form.email.value,
		codiceFiscale: form.codiceFiscale.value
	};
	
	if (cliente.username.length > 60) {
		form.username.setCustomValidity("Non inserire più di 60 caratteri");
		form.username.reportValidity();
		return;
	}
	else if (cliente.password.length > 60) {
		form.password.setCustomValidity("Non inserire più di 60 caratteri");
		form.passowrd.reportValidity();
        return;
	}
    else if (cliente.nome.length > 30) {
        form.nome.setCustomValidity("Non inserire più di 30 caratteri");
        form.nome.reportValidity();
        return;
    }
    else if (cliente.cognome.length > 30) {
        form.cognome.setCustomValidity("Non inserire più di 30 caratteri");
        form.cognome.reportValidity();
        return;
    }
    else if (cliente.email.length > 100) {
        form.email.setCustomValidity("Non inserire più di 100 caratteri");
        form.email.reportValidity();
        return;
    }
    else if (cliente.codiceFiscale.length != 16) {
        form.codiceFiscale.setCustomValidity("La lunghezza del codice fiscale è di 16 caratteri");
        form.codiceFiscale.reportValidity();
        return;
    }
	
	let xhr = createXMLHTTPRequest();
	if (!xhr)
		return;
		
	xhr.open(form.method, form.action, true);
	xhr.setRequestHeader("Content-type", "application/json");
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