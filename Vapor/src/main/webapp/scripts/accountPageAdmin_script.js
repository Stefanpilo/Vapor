document.addEventListener("DOMContentLoaded" , startScript);
			
function startScript(){
	let aggiungiVideogioco_button = document.getElementById("aggiungiVideogioco_button");
	let visualizzaOrdini_button = document.getElementById("visualizzaOrdini_button");
	let visualizzaOrdiniPerData_button = document.getElementById("visualizzaOrdiniPerData_button");
	let visualizzaOrdiniPerCliente_button = document.getElementById("visualizzaOrdiniPerCliente_button");
	
	let aggiungiVideogioco_form = document.getElementById("aggiungiVideogioco_form");
	let ordiniContainer = document.getElementById("ordiniContainer");
	let compostoDa_table = document.getElementById("compostoDa_table");
	let messageViewer = document.getElementById("messageViewer");
	
	let adminServet = aggiungiVideogioco_form.action;
	
	aggiungiVideogioco_button.addEventListener('click', () => {
		messageViewer.style.display = "none";
		ordiniContainer.style.display = "none";
		aggiungiVideogioco_form.style.display = "block";
	});		
	
	aggiungiVideogioco_form.submit_button.addEventListener('click', () => {

		let videogioco = {
			immagine : aggiungiVideogioco_form.immagine.value,
			titolo : aggiungiVideogioco_form.titolo.value,
			prezzo : aggiungiVideogioco_form.prezzo.value,
			sconto : aggiungiVideogioco_form.sconto.value,
			descrizione : aggiungiVideogioco_form.descrizione.value,
			categoria : aggiungiVideogioco_form.categoria.value
		}
		
		if (!videogioco.immagine.match(/^\/Vapor\/images\/[^/]+\.[a-zA-Z0-9]+$/)) {
			aggiungiVideogioco_form.immagine.setCustomValidity("Immagine deve essere della forma: /Vapor/images/nomeImmagine.estensione");
			aggiungiVideogioco_form.immagine.reportValidity();
			return;
		}
		
		else if (videogioco.titolo.length > 50 || videogioco.titolo.length == 0) {
			aggiungiVideogioco_form.titolo.setCustomValidity("Titolo deve avere da 1 a 50 caratteri");
			aggiungiVideogioco_form.titolo.reportValidity();
			return;
		}
		else if (!videogioco.prezzo.match(/^\d{1,10}(?:[.,]\d{1,2})?$/)) {
			aggiungiVideogioco_form.prezzo.setCustomValidity("Prezzo accetta massimo 10 cifre prima del punto/virgola, e massimo due cifre decimali");
			aggiungiVideogioco_form.prezzo.reportValidity();
			return;
		}
		else if (!videogioco.sconto.match(/^(100(\.00?)?|\d{1,2}(\.\d{1,2})?)$/)) {
			aggiungiVideogioco_form.sconto.setCustomValidity("Sconto accetta un valore da 0 a 100 con massimo due cifre decimali");
			aggiungiVideogioco_form.sconto.reportValidity();
			return;
		}
		else if (videogioco.descrizione.length > 500 || videogioco.descrizione.length == 0) {
			aggiungiVideogioco_form.descrizione.setCustomValidity("La descrizione deve avere da 1 a 500 caratteri");
			aggiungiVideogioco_form.descrizione.reportValidity();
			return;
		}
		
		
		let xhr = createXMLHTTPRequest();
		if (!xhr)
			return;
		
		xhr.open(aggiungiVideogioco_form.method, adminServet, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					messageViewer.style.display = "block";
					messageViewer.innerHTML = xhr.responseText;
				}
				else if (xhr.status === 400) {
					messageViewer.style.display = "block";
					messageViewer.innerHTML = xhr.responseText;
				}
			}
		};
		let jsonToSend = {
			"query type" : "insert",
			"videogioco" : videogioco
		}
		xhr.send(JSON.stringify(jsonToSend));

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
        xhr.open("get", adminServet + "?dati=" + encodeURIComponent(JSON.stringify(jsonToSend)), true);
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

	let visualizzaOrdini_buttonClicked = function() {
		messageViewer.style.display = "none";
		aggiungiVideogioco_form.style.display = "none";
		ordiniContainer.querySelectorAll("*:not(table *)").forEach( (element) => element.style.display = "none");
		ordiniContainer.querySelector("tbody").innerHTML = "";
		ordiniContainer.style.display = "block";
		
		let xhr = createXMLHTTPRequest();
		if (!xhr)
		return;
		
		let jsonToSend = {
			"query type" : "select all",
			"DAO type" : "OrdineDAO"
		}
		xhr.open("get", adminServet + "?dati=" + encodeURIComponent(JSON.stringify(jsonToSend)), true);
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
		};
		xhr.send();
	};
	visualizzaOrdini_button.addEventListener('click', visualizzaOrdini_buttonClicked);

	let startDate = document.getElementById("startDate");
	let endDate = document.getElementById("endDate");
	let visualizzaOrdiniPerData_buttonClicked = function() {
		messageViewer.style.display = "none";
		aggiungiVideogioco_form.style.display = "none";
		ordiniContainer.querySelectorAll("*:not(table *)").forEach( (element) => element.style.display = "none");
		ordiniContainer.querySelector("tbody").innerHTML = "";
		ordiniContainer.style.display = "block";
		
		startDate.style.display = "inline";
		endDate.style.display = "inline";
		document.getElementById("ordiniByDataSubmit_button").style.display = "inline";
	};
	visualizzaOrdiniPerData_button.addEventListener('click', visualizzaOrdiniPerData_buttonClicked);

	let ordiniByDataSubmit_buttonClicked = function() {
		visualizzaOrdiniPerData_buttonClicked();
		ordiniContainer.querySelector("tbody").innerHTML = "";
		//validazione data inserita
		if (!Date.parse(startDate.value)) {
			startDate.setCustomValidity("Data non valida");
			startDate.reportValidity();
			return;
		}
		if (!Date.parse(endDate.value)) {
			endDate.setCustomValidity("Data non valida");
			endDate.reportValidity();
			return;
		}
		if (startDate.value > endDate.value) {
			endDate.setCustomValidity("Intervallo non valido");
			endDate.reportValidity();
			return;
		}
		
		let xhr = createXMLHTTPRequest();
		if (!xhr)
		return;
		
		let jsonToSend = {
			"query type" : "select by data",
			"DAO type" : "OrdineDAO",
			"startDate" : startDate.value,
			"endDate" : endDate.value
		}
		xhr.open("get", adminServet + "?dati=" + encodeURIComponent(JSON.stringify(jsonToSend)), true);
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
						ordiniContainer.querySelector("p").style.display = "none";
						let ordiniUL = ordiniContainer.querySelector("tbody");
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
		};
		xhr.send();
	};
	document.getElementById("ordiniByDataSubmit_button").addEventListener('click', ordiniByDataSubmit_buttonClicked);
	
	let inputUsernameCliente = document.getElementById("searchByCliente");
	visualizzaOrdiniPerCliente_button.addEventListener('click', () => {
		messageViewer.style.display = "none";
		aggiungiVideogioco_form.style.display = "none";
		ordiniContainer.querySelectorAll("*:not(table *)").forEach( (element) => element.style.display = "none");
		ordiniContainer.querySelector("tbody").innerHTML = "";
		ordiniContainer.style.display = "block";
		
		inputUsernameCliente.style.display = "inline";
		document.getElementById("ordiniByUsernameSubmit_button").style.display = "inline";
	});

	let ordiniByUsernameSubmit_buttonClicked = function() {
		ordiniContainer.querySelector("tbody").innerHTML = "";
		//validazione username inserita
		if (!inputUsernameCliente.value.match(/^[a-zA-Z0-9_-]{6,30}$/) && (inputUsernameCliente.value === "admin")) {
			registrationForm.username.setCustomValidity("L'username deve essere composto da 6-30 caratteri alfanumerici. Opzionali dash(-) e underscore(_). Non valido: admin");
			registrationForm.username.reportValidity();
			return;
		}
		
		let xhr = createXMLHTTPRequest();
		if (!xhr)
		return;
		
		let jsonToSend = {
			"query type" : "select by username",
			"usernameCliente" : inputUsernameCliente.value,
			"DAO type" : "OrdineDAO"
		}
		xhr.open("get", adminServet + "?dati=" + encodeURIComponent(JSON.stringify(jsonToSend)), true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					let ordineAL = JSON.parse(xhr.responseText);
					if (!ordineAL || !ordineAL[0]) {
						ordiniContainer.querySelector("p").innerHTML = "Nessun ordine trovato";
						ordiniContainer.querySelector("p").style.display = "block";
						ordiniContainer.querySelector("table").style.display = "none";
					}
					else {
						ordiniContainer.querySelector("p").style.display = "none";
						let ordiniUL = ordiniContainer.querySelector("tbody");
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
		};
		xhr.send();
	};
	document.getElementById("ordiniByUsernameSubmit_button").addEventListener('click', ordiniByUsernameSubmit_buttonClicked);
}