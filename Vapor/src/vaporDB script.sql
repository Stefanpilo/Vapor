CREATE DATABASE IF NOT EXISTS vapor;

USE vapor;

CREATE TABLE IF NOT EXISTS Cliente (
  Username VARCHAR(30) PRIMARY KEY,
  Password VARCHAR(30),
  Nome VARCHAR(30),
  Cognome VARCHAR(30),
  Email VARCHAR(100),
  CodiceFiscale VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS MetodoPagamento (
  NumeroCarta VARCHAR(16) PRIMARY KEY,
  CVV VARCHAR(4),
  Circuito VARCHAR(20),
  ExpDate DATE,
  UsernameCliente VARCHAR(60),
  FOREIGN KEY (UsernameCliente) REFERENCES Cliente(Username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Ordine (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  PrezzoTotale DECIMAL(10,2),
  MetodoPagamento VARCHAR(16),
  Data DATE,
  UsernameCliente VARCHAR(255),
  FOREIGN KEY (UsernameCliente) REFERENCES Cliente (Username)
);

CREATE TABLE IF NOT EXISTS Fattura (
  SDI VARCHAR(255) PRIMARY KEY,
  DataEmissione DATE,
  DataScadenza DATE,
  Importo DECIMAL(10,2),
  StatoPagamento VARCHAR(50),
  IDOrdine INT,
  FOREIGN KEY (IDOrdine) REFERENCES Ordine(ID)
);

CREATE TABLE IF NOT EXISTS Videogioco (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  Immagine VARCHAR(255),
  Titolo VARCHAR(50),
  Prezzo DECIMAL(10,2),
  Sconto DECIMAL(5,2),
  Descrizione TEXT,
  Categoria ENUM('Azione','Avventura','Horror','Indie','Puzzle','RPG','Simulazione','Sparatutto')
);

CREATE TABLE IF NOT EXISTS CompostoDa (
  IDOrdine INT,
  IDVideogioco INT,
  Prezzo DECIMAL(10,2),
  Quantita INT,
  PRIMARY KEY (IDOrdine, IDVideogioco),
  FOREIGN KEY (IDOrdine) REFERENCES Ordine(ID),
  FOREIGN KEY (IDVideogioco) REFERENCES Videogioco(ID)
);


