package Storage.Host;

import java.util.Date;

public class Host {

    private String email;
    private String nome;
    private String cognome;
    private String password;
    private Date dataNascita;
    private String recapitoTelefonico;

    public Host() {
    }

    public Host(String email, String nome, String cognome, String password, Date dataNascita, String recapitoTelefonico) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.dataNascita = dataNascita;
        this.recapitoTelefonico = recapitoTelefonico;
    }

    public String getEmail() {
        return email;
    }

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

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getRecapitoTelefonico() {
        return recapitoTelefonico;
    }

    public void setRecapitoTelefonico(String recapitoTelefonico) {
        this.recapitoTelefonico = recapitoTelefonico;
    }
}
