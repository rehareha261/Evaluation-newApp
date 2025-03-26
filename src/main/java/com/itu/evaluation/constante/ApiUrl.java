package com.itu.evaluation.constante;

public class ApiUrl {
    public static final String BASE_URL = "http://localhost";

    public static final String API_URL = BASE_URL+"/api";
    
    public static final String LOGIN_API_URL = API_URL+"/login";

    public static final String OFFERS_API_URL = API_URL+"/offers";

    public static final String INVOICES_API_URL = API_URL+"/invoices";

    public static final String INVOICES_API_URL_REMISE = API_URL+"/invoices/remise";

    public static final String INVOICES_TOTAL_API_URL = API_URL+"/invoices/total-price";

    public static final String INVOICES_API_URL_DETAILS = API_URL+"/invoices/details";

    public static final String PAYMENTS_API_URL = API_URL+"/payments";

    public static final String PAYMENTS_TOTAL_API_URL = API_URL+"/payments/total-price";

    public static final String DASHBOARD_API_URL = API_URL+"/dashboard";

    public static final String DASHBOARD_API_URL_MENSUELLE = DASHBOARD_API_URL+"/mensuelle";
    

    public static final String DASHBOARD_API_URL_REPARTITION = DASHBOARD_API_URL+"/payment/repartition";

    public static final String DASHBOARD_API_URL_EVOLUTION = DASHBOARD_API_URL+"/chiffre-affaire/evolution";

}
