package br.com.anotacoes.utils;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class Utils {

    public static final String DIA = ", Dia:";
    public static final String MES = ", Mês:";

    public Utils() {
    }

    public void setDayOfWeek(TextInputLayout textInputLayout) {

        String[] days = new String[]{"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado"};

        Calendar calendar = Calendar.getInstance();

        textInputLayout.getEditText().setText(days[calendar.get(Calendar.DAY_OF_WEEK) - 1] +
                DIA + " " + calendar.get(Calendar.DATE) +
                MES + calendar.get(Calendar.MONTH));
    }
}
