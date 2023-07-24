function startAccountPageClienteScript() {
	let ordiniContainer = document.getElementById("ordiniContainer");
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

	document.getElementById("visualizzaOrdini_button").addEventListener('click', () => {
		document.getElementById("ordiniContainer").querySelector("tbody").innerHTML = "";
		let xhr = createXMLHTTPRequest();
		if (!xhr)
		return;
		
		let jsonToSend = {
			"query type" : "select by username",
			"DAO type" : "OrdineDAO",
			"usernameCliente" : document.getElementById("visualizzaOrdini_button").dataset.username
		}


		xhr.open("get", "/Vapor/AdminServlet?dati=" + encodeURIComponent(JSON.stringify(jsonToSend)), true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
                    //la response sarà un ArrayList<Ordine>
					let ordineAL = JSON.parse(xhr.responseText);
					if (!ordineAL || !ordineAL[0]) {
						ordiniContainer.querySelector("p").innerHTML = "Nessun ordine trovato";
						ordiniContainer.querySelector("p").style.display = "block";
						ordiniContainer.querySelector("table").style.display = "none";
					}
					else {
						let ordiniUL = ordiniContainer.querySelector("tbody");
						ordiniContainer.querySelector("p").style.display = "none";

						ordineAL.forEach( (element) => {
							let tableRow = document.createElement("tr");
							tableRow.setAttribute("id", "id" + element.ID);
							tableRow.addEventListener('click', ordiniTableRowClicked.bind(null, element.ID));

							let tableDataPrezzoTotale = document.createElement("td");
							tableDataPrezzoTotale.innerHTML = element.prezzoTotale;
							let tableDataMetodoPagamento = document.createElement("td");
							tableDataMetodoPagamento.innerHTML = element.metodoPagamento;
							let tableDataData = document.createElement("td");
							tableDataData.innerHTML = element.data;
							let tableDataUsernameCliente = document.createElement("td");
							tableDataUsernameCliente.innerHTML = element.usernameCliente;
							
							tableRow.append(tableDataPrezzoTotale);
							tableRow.append(tableDataMetodoPagamento);
							tableRow.append(tableDataData);
							tableRow.append(tableDataUsernameCliente);
							
							ordiniUL.append(tableRow);
						});
						
						ordiniContainer.querySelector("table").style.display = "table";
					}
				}
			}
		}
		xhr.send();
	});

	let ordiniTableRowClicked = function(OrdineID) {

        //pulisci la tabella di compostoDa
		ordiniContainer.getElementsByTagName("tbody")[0].querySelectorAll("tr:not(#id" + OrdineID + ")").forEach( (element) =>element.remove());

        let jsonToSend = {
            "query type" : "select by id",
            "DAO type" : "CompostoDAO",
			"ID" : OrdineID
        }

        let xhr = createXMLHTTPRequest();
        xhr.open("get", "/Vapor/AdminServlet?dati=" + encodeURIComponent(JSON.stringify(jsonToSend)), true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    //la response sarà un ArrayList<Composto>
                    let compostoAL = JSON.parse(xhr.responseText);
                    if (!compostoAL || !compostoAL[0]) {
                        ordiniContainer.querySelector("p").innerHTML = "Nessun ordine trovato";
                        ordiniContainer.querySelector("p").style.display = "block";
                    }
                    else {
                        let ordiniUL = compostoDa_table.getElementsByTagName("tbody")[0];
                        ordiniUL.innerHTML = "";
                        compostoDa_table.style.display = "table";
                        
                        compostoAL.forEach( (element) => {
                            let tableRow = document.createElement("tr");

                            let tableTitoloVideogioco = document.createElement("td");
                            tableTitoloVideogioco.innerHTML = element.titoloVideogioco;
                            let tablePrezzoVideogioco = document.createElement("td");
                            tablePrezzoVideogioco.innerHTML = element.prezzo;
                    
                            tableRow.appendChild(tableTitoloVideogioco);
                            tableRow.appendChild(tablePrezzoVideogioco);
                    
                            ordiniUL.appendChild(tableRow);

                        });
                    }
                }
            }
        };
        xhr.send();
	}
};
document.addEventListener('DOMContentLoaded', startAccountPageClienteScript);

