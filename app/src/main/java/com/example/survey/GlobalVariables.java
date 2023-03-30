package com.example.survey;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariables {

    public static final String DB_NAME = "LoginTemplate.db";
    public static final String LOGINVERIFY_TBL = "login_verify";
    public static final String MERCHANTVERIFY_TBL = "merchant_verify";
    public static final String EMPLOYEE_TBL = "agent";
    public static final String DTR_TBL = "daily_time_record";
    public static final String DTR_COUNTER_TBL = "dtr_counter";
    public static final String STATUS_TBL = "maya_status";
    public static final String TERMINAL_TBL = "terminals";
    public static final String MERCHANT_TBL = "merchant";
    public static final String MERCHANTVISIT_TBL = "merchant_visit";

//    public static final String EMPLOYEE_SCHEDULE_TBL = "EmployeeSchedule";
//    public static final String STORE_LOCATION = "Store";
//    public static final String EXPENSES = "Expenses";
//    public static final String LEAVE_TYPE = "LeaveType";
//    public static final String EXPENSE_TYPE = "ExpenseType";
//    public static final String STORE_AREA = "StoreArea";
//    public static final String LOGIN_VERIFY = "LoginVerify";
//    public static final String TANDC_VERIFY = "TandCVerify";
//    public static final String LAST_SYNC = "LastSync";
//    public static final String USER_TBL = "User";

    public static List<String> deliveryPhotos = new ArrayList<>();
    public static List<String> extraPhotos = new ArrayList<>();
    public static List<String> pisoTestPhotos = new ArrayList<>();

    public static String progressDialogMessage = "";
    public static boolean thread = false;
    public static boolean successLogin = false;
    public static boolean successGetAccount = false;
    public static boolean fromSQLite = false;

    public static boolean successGetMayaStatus = false;

    public static boolean successGetTerminals = false;

    public static boolean successGetMerchantVisit = false;

    public static String forMayaSetup = "";


    public static int processList = 0;
    public static int processListSync = 0;

    public static boolean fromDashboard = false;
}
