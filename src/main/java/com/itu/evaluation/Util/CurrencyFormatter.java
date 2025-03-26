package com.itu.evaluation.Util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
    // public static void main(String[] args) {
    //     double montant = 2034345.75;

    //     String formattedMontant = formatCurrency(montant, Locale.FRANCE);
    //     System.out.println("Format FR : " + formattedMontant);

    //     String formattedUSD = formatCurrency(montant, Locale.US);
    //     System.out.println("Format US : " + formattedUSD);

    //     String formattedMGA = formatCurrency(montant, new Locale("mg", "MG"));
    //     System.out.println("Format MGA : " + formattedMGA);
    // }

    public static String formatCurrency(double montant, Locale locale) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(montant);
    }
}
