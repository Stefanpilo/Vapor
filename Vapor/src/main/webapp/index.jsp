<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Vapor - Home</title>
    
    <link rel="stylesheet" href="/Vapor/styles/genericStyle.css">
    <link rel="stylesheet" href="/Vapor/styles/homePage.css">
</head>
<body>
	
	<%@include file="/jsp/header.jsp" %>
	
	
    <div class="leader-banner">
        <img id="banner" src="/Vapor/images/LeaderBanner.png" alt="logo">
    </div>

    <section class="hero-section">
        <div class="hero-content">
            <h1>Benvenuto su Vapor</h1>
            <p>Esplora un vasto catalogo di videogiochi digitali e scopri nuove avventure.</p>
        </div>
    </section>

    <div class="container">
        <section class="section about-section">
            <div class="about-content">
                <h2>Chi siamo</h2>
                <p>
                    Vapor è un sito di e-commerce specializzato nella vendita di videogiochi digitali.
                    Con un vasto catalogo di titoli per tutte le piattaforme, troverai sicuramente i giochi
                    che ami. Scopri le ultime uscite, le offerte speciali e.
                    Siamo appassionati di videogiochi e vogliamo condividere questa passione con te.
                </p>
            </div>
        </section>

        <section class="section games-section">
            <div class="games-content">
                <h2>I nostri giochi</h2>
                <p>
                    Abbiamo una vasta selezione di giochi per PC, console e altro ancora. Dai classici
                    intramontabili agli ultimi titoli in uscita, troverai tutto ciò che desideri nel nostro
                    negozio. I nostri giochi sono disponibili in formato digitale, quindi potrai iniziare a
                    giocare immediatamente dopo l'acquisto. Scopri i titoli e le offerte del momento!
                </p>
            </div>
        </section>

        <section class="section contact-section">
            <div class="contact-content">
                <h2>Contattaci</h2>
                <p>
                    Se hai domande, suggerimenti o hai bisogno di assistenza, non esitare a contattarci.
                    Il nostro team di supporto clienti è sempre pronto ad aiutarti. Puoi contattarci tramite
                    email all'indirizzo info@vapor.com o chiamarci al numero +39 39 404 5027890. Siamo qui per te!
                </p>
            </div>
        </section>
    </div>
    
	<%@include file="/jsp/footer.jsp" %>	
	</body>


</html>