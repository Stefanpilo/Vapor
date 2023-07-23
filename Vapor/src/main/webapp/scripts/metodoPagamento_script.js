function loadMetodoPagamentoScript() {
	let form = document.getElementById("form");
	document.getElementById("submit_button").addEventListener('click', () => {
		let metodoPagamento = {
			numeroCarta : form.numeroCarta.value,
			cvv : form.cvvCarta.value,
			circuito : form.circuitoCarta.value,
			expDate : form.scadenzaCarta.value,
			usernameCliente : form.dataset.username
		}

		console.log(metodoPagamento.expDate);

		if (!metodoPagamento.numeroCarta.match(/^[0-9]{16}$/)) {
			form.numeroCarta.setCustomValidity("Il numero carta deve essere di 16 numeri");
			form.numeroCarta.reportValidity();
			return;
		}
		if (!metodoPagamento.cvv.match(/^[0-9]{3,4}$/)) {
			form.cvvCarta.setCustomValidity("Il cvv deve essere di 3 o 4 numeri");
			form.cvvCarta.reportValidity();
			return;
		}
		if (!Date.parse(form.scadenzaCarta.value)) {
			form.scadenzaCarta.setCustomValidity("Data non valida");
			form.scadenzaCarta.reportValidity();
			return;
		}

		let jsonToSend = {
			"query type" : "",
			"DAO type" : "MetodoPagamentoDAO",
			"metodoPagamento" : metodoPagamento
		}

		let xhr = createXMLHTTPRequest();
		if (!xhr)
			return;

		xhr.open(form.method, form.action, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					location.reload(true);
				}
			}
		};
		xhr.send(JSON.stringify(jsonToSend));

	});


	document.querySelectorAll(".rimuovi_button").forEach( (element) => {
		element.addEventListener('click', () => {
			let jsonToSend = {
				"query type" : "remove",
				"DAO type" : "MetodoPagamentoDAO",
				"username" : form.dataset.username,
				"numeroCarta" : event.target.dataset.numerocarta
			}

			let xhr = createXMLHTTPRequest();
			if (!xhr)
				return;

			xhr.open(form.method, form.action, true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE) {
					if (xhr.status === 200) {
						location.reload(true);
					}
				}
			};
			xhr.send(JSON.stringify(jsonToSend));
			});
	});
};
document.addEventListener('DOMContentLoaded', loadMetodoPagamentoScript);