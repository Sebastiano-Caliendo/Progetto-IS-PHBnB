package Storage.Utente;

import java.time.LocalDate;

public class Utente {

    private String email;
    private String nome;
    private String cognome;
    private String password;
    private String citta;
    private String numeroCivico;
    private String via;
    private LocalDate dataNascita;
    private String recapitoTelefonico;
    private boolean isAdmin;

    public Utente() {

    }

    public Utente(String email, String nome, String cognome, String password, String citta, String numeroCivico, String via, LocalDate dataNascita, String recapitoTelefonico, boolean isAdmin) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.citta = citta;
        this.numeroCivico = numeroCivico;
        this.via = via;
        this.dataNascita = dataNascita;
        this.recapitoTelefonico = recapitoTelefonico;
        this.isAdmin = isAdmin;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getRecapitoTelefonico() {
        return recapitoTelefonico;
    }

    public void setRecapitoTelefonico(String recapitoTelefonico) {
        this.recapitoTelefonico = recapitoTelefonico;
    }

    public boolean isAdmin() { return isAdmin; }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
