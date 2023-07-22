package model;

import java.sql.Date;

public class MetodoPagamento {
    public MetodoPagamento(String numeroCarta, String cvv, String circuito, Date expDate, String usernameCliente) {
        this.numeroCarta = numeroCarta;
        this.cvv = cvv;
        this.circuito = circuito;
        this.expDate = expDate;
        this.usernameCliente = usernameCliente;
    }


    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    private String numeroCarta;
    private String cvv;
    private String circuito;
    private Date expDate;
    private String usernameCliente;
}