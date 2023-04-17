package backend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author marme
 */
public class Verificadores {

    public Verificadores() {
    }

    private int[] dateTokenizer(String sFecha) {

        int[] fecha = new int[3];

        StringTokenizer tk = new StringTokenizer(sFecha, "/");
        fecha[0] = Integer.parseInt(tk.nextToken());
        fecha[1] = Integer.parseInt(tk.nextToken());
        fecha[2] = Integer.parseInt(tk.nextToken());
        return fecha;

    }

    public boolean formatoCamposNumericos(String cadena) {

        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher(cadena);
        return (mat.matches());

    }

    public boolean formatoCamposAlfabeticos(String cadena) {

        Pattern pat = Pattern.compile("[a-zA-Z\\s]+");
        Matcher mat = pat.matcher(cadena);
        return (mat.matches());

    }

    public boolean formatoCamposFecha(String cadena) {

        int dia, mes, anio;
        int[] fecha = new int[3];

        Pattern pat = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/[0-9]{1,4}");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            fecha = dateTokenizer(cadena);
            dia = fecha[0];
            mes = fecha[1];
            anio = fecha[2];
            return this.validarFecha(anio, mes, dia);
        } else {
            return validarAnno(cadena);
        }

    }

    private boolean validarFecha(int anio, int mes, int dia) {

        if (anio < 0) {
            return false;
        }
        if ((mes < 0) || (mes > 12)) {
            return false;
        }
        if ((mes == 1) || (mes == 3) || (mes == 5) || (mes == 7) || (mes == 8) || (mes == 10) || (mes == 12)) {
            return dia <= 31;
        }
        if ((anio % 4 == 0) || ((anio % 100 == 0) && (anio % 400 == 0))) {
            if (mes == 2) {
                return dia <= 29;
            } else {
                return dia <= 30;
            }
        } else {
            if (mes == 2) {
                return dia <= 28;
            } else {
                return dia <= 30;
            }
        }

    }

    public boolean formatoCamposCorreo(String cadena) {

        Pattern pat = Pattern.compile("[a-zA-Z0-9\\S]+@[a-z]+\\..*");
        Matcher mat = pat.matcher(cadena);
        return (mat.matches());

    }

    @Override
    public String toString() {
        return "Verificaciones{" + '}';
    }

    private boolean validarAnno(String cadena) {
        Pattern pat = Pattern.compile("(\\d){1,4}");
        Matcher mat = pat.matcher(cadena);
        Date date = new Date();
        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        String año = getYearFormat.format(date);
        return (mat.matches()) && Integer.parseInt(cadena) <= Integer.parseInt(año);
    }

}
