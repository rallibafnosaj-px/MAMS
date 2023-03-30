package com.example.survey;

public class URL {

    //local ip
//    public static String IP = "192.168.1.11:8080";
    //live ip
    public static String IP = "192.168.1.7:8181";

    public static String ROOT_URL = "http://" + IP + "/Survey/";
    public static String LOGINACCOUNT = ROOT_URL + "login_searchacc.php";
    public static String GETEMPLOYEEDETAILS = ROOT_URL + "get_employee.php";
    public static String GETMAYASTATUS = ROOT_URL + "get_status.php";
    public static String GETTERMINALS = ROOT_URL + "get_terminals.php";
    public static String GETMERCHANTVISIT = ROOT_URL + "get_merchant_visit.php";
    public static String ADDDTR = ROOT_URL + "add_time_in_out.php";
    public static String ADDMERCHANT = ROOT_URL + "add_merchant.php";
    public static String GETITINERARY = ROOT_URL + "get_itinerary.php";
    public static String GETSYNCEDMERCHANT = ROOT_URL + "get_synced_merchant.php";
    public static String UPDATEASSIGNSTATUS = ROOT_URL + "update_assignment_status.php";
    public static String ADDMERCHANTSERIAL = ROOT_URL + "add_merchant_serialnumber.php";

}
