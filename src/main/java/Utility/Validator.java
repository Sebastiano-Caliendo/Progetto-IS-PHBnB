package Utility;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public Validator() {
    }

    public String validateEmail(String email) {
        String regex = "^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,10}$";

        // Crea un Pattern dall'espressione regolare
        Pattern pattern = Pattern.compile(regex);

        // Crea un Matcher dalla stringa
        Matcher matcher = pattern.matcher(email);

        if(matcher.matches())
            return email;
        else
            throw new RuntimeException();
    }

    public String validatePassword(String password) {

        if(password != null && !password.isEmpty() && password.length() >= 8)
            return password;
        else
            throw new RuntimeException();
    }

    public String validateNomeCognome(String s) {
        String regex = "^[A-zÀ-ù ‘-]{2,20}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        if(matcher.matches())
            return s;
        else
            throw new RuntimeException();
    }

    public String validateCittaVia(String s) {
        String regex = "^[A-zÀ-ù ‘-]{2,50}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        if(matcher.matches())
            return s;
        else
            throw new RuntimeException();
    }

    public String validateNumeroCivico(String numeroCivico) {
        String regex = "^[0-9A-z ‘-]{1,5}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numeroCivico);

        if(matcher.matches())
            return numeroCivico;
        else
            throw new RuntimeException();
    }

    public String validateRecapitoTelefonico(String recapitoTelefonico) {
        String regex = "^\\d{10}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(recapitoTelefonico);

        if(matcher.matches())
            return recapitoTelefonico;
        else
            throw new RuntimeException();
    }

    public LocalDate validateData(String data) {
        String regex = "^(19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        if(matcher.matches()){
            return LocalDate.parse(data);
        }
        else
            throw new RuntimeException();
    }

    public int validateInt(String integerValue) {
        String regex = "^\\d+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(integerValue);

        if(matcher.matches()){
            return Integer.parseInt(integerValue);
        }
        else
            throw new RuntimeException();
    }

    public double validateDouble(String doubleValue) {
        String regex = "^\\d+(\\.\\d+)?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(doubleValue);

        if(matcher.matches()){
            return Double.parseDouble(doubleValue);
        }
        else
            throw new RuntimeException();
    }

    public String validateTipoAlloggio(String tipoAlloggio) {

        if(tipoAlloggio != null && !tipoAlloggio.isEmpty() && tipoAlloggio.length() <= 30) {
            return tipoAlloggio;
        } else {
            throw new RuntimeException();
        }
    }

    public String validateNumeroCarta(String numeroCarta) {
        String regex = "^\\d{16}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numeroCarta);

        if(matcher.matches()){
            return numeroCarta;
        }
        else
            throw new RuntimeException();
    }

    public String validateCVICarta(String cviCart) {
        String regex = "^\\d{3}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cviCart);

        if(matcher.matches()){
            return cviCart;
        }
        else
            throw new RuntimeException();
    }

    public String validateDescrizione(String descrizione) {

        if(descrizione != null && !descrizione.isEmpty()) {
            return descrizione;
        } else {
            throw new RuntimeException();
        }
    }

    public String validateNomeStruttura(String nomeStruttura) {

        if(nomeStruttura != null && !nomeStruttura.isEmpty() && nomeStruttura.length() <= 30) {
            return nomeStruttura;
        } else {
            throw new RuntimeException();
        }
    }
}
