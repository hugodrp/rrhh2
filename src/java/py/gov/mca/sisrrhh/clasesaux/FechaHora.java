/*
 * Decompiled with CFR 0_122.
 */
package py.gov.mca.sisrrhh.clasesaux;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FechaHora {

    static long milisegundos_dia = 86400000;
    private final SimpleDateFormat dateFormatFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private long horas;
    private long minutos;
    private long segundos;

    public void calcularHorasMinutosMismoDia(String anio, String mes, String dia, String horaInicio, String horaFin) {
        Date fechaInicial = FechaHora.StringToDate(anio + "/" + mes + "/" + dia + " " + horaInicio + ":00", "/", 0);
        Date fechaFinal = FechaHora.StringToDate(anio + "/" + mes + "/" + dia + " " + horaFin + ":00", "/", 0);
        Calendar calFechaInicial = Calendar.getInstance();
        Calendar calFechaFinal = Calendar.getInstance();
        calFechaInicial.setTime(fechaInicial);
        calFechaFinal.setTime(fechaFinal);
        this.setHoras(FechaHora.diferenciaHorasDias(calFechaInicial, calFechaFinal) + FechaHora.diferenciaHoras(calFechaInicial, calFechaFinal));
        this.setMinutos(FechaHora.cantidadTotalMinutos(calFechaInicial, calFechaFinal));
    }

    public static long diferenciaHorasDias(Calendar fechaInicial, Calendar fechaFinal) {
        long diferenciaHoras = 0;
        diferenciaHoras = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / milisegundos_dia;
        if (diferenciaHoras > 0) {
            diferenciaHoras *= 24;
        }
        return diferenciaHoras;
    }

    public static long diferenciaHoras(Calendar fechaInicial, Calendar fechaFinal) {
        long diferenciaHoras = 0;
        diferenciaHoras = fechaFinal.get(11) - fechaInicial.get(11);
        return diferenciaHoras;
    }

    public static long diferenciaMinutos(Calendar fechaInicial, Calendar fechaFinal) {
        long diferenciaHoras = 0;
        diferenciaHoras = fechaFinal.get(12) - fechaInicial.get(12);
        return diferenciaHoras;
    }

    public static long cantidadTotalMinutos(Calendar fechaInicial, Calendar fechaFinal) {
        long totalMinutos = 0;
        totalMinutos = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000 / 60;
        return totalMinutos;
    }

    public static long cantidadTotalHoras(Calendar fechaInicial, Calendar fechaFinal) {
        long totalMinutos = 0;
        totalMinutos = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000 / 60 / 60;
        return totalMinutos;
    }

    public static long cantidadTotalSegundos(Calendar fechaInicial, Calendar fechaFinal) {
        long totalMinutos = 0;
        totalMinutos = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000;
        return totalMinutos;
    }

    public static String DateToString(Date fecha, String caracter, int op) {
        String formatoHora = " HH:mm:ss";
        String formato = "yyyy" + caracter + "MM" + caracter + "dd" + formatoHora;
        if (op == 1) {
            formato = "yyyy" + caracter + "dd" + caracter + "MM" + formatoHora;
        } else if (op == 2) {
            formato = "MM" + caracter + "yyyy" + caracter + "dd" + formatoHora;
        } else if (op == 3) {
            formato = "MM" + caracter + "dd" + caracter + "yyyy" + formatoHora;
        } else if (op == 4) {
            formato = "dd" + caracter + "yyyy" + caracter + "MM" + formatoHora;
        } else if (op == 5) {
            formato = "dd" + caracter + "MM" + caracter + "yyyy" + formatoHora;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());
        String fechaFormato = null;
        sdf.setLenient(false);
        fechaFormato = sdf.format(fecha);
        return fechaFormato;
    }

    public static Date StringToDate(String fecha, String caracter, int op) {
        String formatoHora = " HH:mm:ss";
        String formato = "yyyy" + caracter + "MM" + caracter + "dd" + formatoHora;
        if (op == 1) {
            formato = "yyyy" + caracter + "dd" + caracter + "MM" + formatoHora;
        } else if (op == 2) {
            formato = "MM" + caracter + "yyyy" + caracter + "dd" + formatoHora;
        } else if (op == 3) {
            formato = "MM" + caracter + "dd" + caracter + "yyyy" + formatoHora;
        } else if (op == 4) {
            formato = "dd" + caracter + "yyyy" + caracter + "MM" + formatoHora;
        } else if (op == 5) {
            formato = "dd" + caracter + "MM" + caracter + "yyyy" + formatoHora;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());
        Date fechaFormato = null;
        try {
            sdf.setLenient(false);
            fechaFormato = sdf.parse(fecha);
        } catch (ParseException parseException) {
            // empty catch block
        }
        return fechaFormato;
    }

    public long getHoras() {
        return this.horas;
    }

    public void setHoras(long horas) {
        this.horas = horas;
    }

    public long getMinutos() {
        return this.minutos;
    }

    public void setMinutos(long minutos) {
        this.minutos = minutos;
    }

    public long getSegundos() {
        return this.segundos;
    }

    public void setSegundos(long segundos) {
        this.segundos = segundos;
    }
}
