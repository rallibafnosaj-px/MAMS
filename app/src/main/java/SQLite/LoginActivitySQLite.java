package SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.survey.GlobalVariables;

import static android.content.ContentValues.TAG;

public class LoginActivitySQLite extends SQLiteDatabaseTemplate {

    String query;
    Context context;

    public LoginActivitySQLite(Context context) {
        super(context);
        this.context = context;
    }

    public String CheckIfSomeoneLogin() {

        SQLiteDatabase db = getWritableDatabase();

        try {
            String query = "SELECT login_verify " +
                    "from " + GlobalVariables.LOGINVERIFY_TBL;

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {

                String loginVerify = cursor.getString(0);

                return loginVerify;
            }
            cursor.close();
        } catch (Exception e) {

        }
        return "false";

    }

    public boolean InsertLoginVerify(String login) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put(GlobalVariables.LOGINVERIFY_TBL, login);

            db.insert(GlobalVariables.LOGINVERIFY_TBL, null, cv);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean InsertPin() {

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put("pin", "1234");

            db.insert("pin", null, cv);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void UpdateLoginVerify() {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            query = "Update " + GlobalVariables.LOGINVERIFY_TBL +
                    " set login_verify = 0 ";
            db.execSQL(query);


        } catch (Exception e) {
            Log.d(TAG, "UpdateDTRStatus: " + e.toString());
        }

    }

    public void InsertEmployee(String agentID, String agentName, String contactNo,
                               String email, String password) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String query_searchEmployee = "Select agent_id " +
                    "from " + GlobalVariables.EMPLOYEE_TBL +
                    " where agent_id IS ?";

            cursor = db.rawQuery(query_searchEmployee, new String[]{agentID});

            if (cursor.getCount() == 0) {

                ContentValues cv = new ContentValues();
                cv.put("agent_id", agentID);
                cv.put("agent_name", agentName);
                cv.put("contact_no", contactNo);
                cv.put("email", email);
                cv.put("password", password);

                db.insert(GlobalVariables.EMPLOYEE_TBL, null, cv);
            } else {

            }

            cursor.close();
        } catch (Exception e) {
            Log.d(TAG, "ErrorGetEmployee: " + e.toString());
        }

    }

    public void InsertStatus(String status) {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("status", status);
            db.insert(GlobalVariables.STATUS_TBL, null, cv);

        } catch (Exception e) {
            Log.d(TAG, "ErrorGetStatus: " + e.toString());
        }

    }


    public void InsertTerminals(String terminals) {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("terminals", terminals);
            db.insert(GlobalVariables.TERMINAL_TBL, null, cv);
        } catch (Exception e) {
            Log.d(TAG, "ErrorGetTerminal: " + e.toString());
        }

    }

    public void InsertMerchantVisit(String merchantID, String dateOfVisit, String empName, String nameOfMall,
                                    String mayaStatus, String dbaName, String regBizName, String subArea,
                                    String mercSPOCName, String mercSPOCDesig, String mercSPOCEmail,
                                    String mercSPOCContact, String gcashStaticQR, String gcashQRIPOS, String gcashBoth,
                                    String terminalAvailable, String otherMercVisible, String mayaHidden, String mayaStandee,
                                    String mayaDoorHanger, String mayaNone, String nonMayaStandee, String nonMayaDoorHanger,
                                    String nonMayaNone, String qrGreenBird, String qrMaya2, String availSQR,
                                    String mercRestriction, String q1, String q2, String q3, String q4, String q5,
                                    String transactionID, String completeDelAdd, String Remarks, String agentID,
                                    String agentName, String status, String tradeAssuranceType,
                                    String tatRemarks) {


        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("visit_date", dateOfVisit);
            cv.put("encoded_by", empName);
            cv.put("name_of_mall", nameOfMall);
            cv.put("maya_status", mayaStatus);
            cv.put("dba_name", dbaName);
            cv.put("reg_business_name", regBizName);
            cv.put("sub_area", subArea);
            cv.put("merc_spoc_name", mercSPOCName);
            cv.put("merc_spoc_designation", mercSPOCDesig);
            cv.put("merc_spoc_email", mercSPOCEmail);
            cv.put("merc_spoc_contact", mercSPOCContact);
            cv.put("gcash_accept_static", gcashStaticQR);
            cv.put("gcash_accept_qrinsidepos", gcashQRIPOS);
            cv.put("gcash_accept_both", gcashBoth);
            cv.put("terminal_avail", terminalAvailable);
            cv.put("other_merc_visible", otherMercVisible);
            cv.put("maya_visibility_hidden", mayaHidden);
            cv.put("maya_visibility_standee", mayaStandee);
            cv.put("maya_visibility_doorhanger", mayaDoorHanger);
            cv.put("maya_visibility_none", mayaNone);
            cv.put("nonmaya_visibility_standee", nonMayaStandee);
            cv.put("nonmaya_visibility_doorhanger", nonMayaDoorHanger);
            cv.put("nonmaya_visibility_none", nonMayaNone);
            cv.put("qr_green_bird", qrGreenBird);
            cv.put("qr_mayatwo", qrMaya2);
            cv.put("available_sqr", availSQR);
            cv.put("merc_restriction", mercRestriction);
            cv.put("accept_other_qr", q1);
            cv.put("maya_device_count", q2);
            cv.put("maya_device_sn", q3);
            cv.put("maya_sqr_count", q4);
            cv.put("store_code", q5);
            cv.put("transaction_id", transactionID);
            cv.put("complete_delivery_add", completeDelAdd);
            cv.put("remarks", Remarks);
            cv.put("agent_id", agentID);
            cv.put("agent_name", agentName);
            cv.put("status", status);
            cv.put("merchant_id", merchantID);
            cv.put("trade_assurance", tradeAssuranceType);
            cv.put("tat_remarks", tatRemarks);
            cv.put("sync_status", 0);
            db.insert(GlobalVariables.MERCHANTVISIT_TBL, null, cv);
        } catch (Exception e) {
            Log.d(TAG, "ErrorGetMerchantVisit: " + e.toString());
        }

    }

    public void DeleteStatus() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.STATUS_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteTerminals() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.TERMINAL_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteMerchantVisit() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.MERCHANTVISIT_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeletePin() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from pin";
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public String getPinCode() {

        SQLiteDatabase db = getWritableDatabase();

        try {
            String query_getDrDetails = "Select pin " +
                    "from pin";

            cursor = db.rawQuery(query_getDrDetails, new String[]{});

            if (cursor.moveToFirst()) {

                String pin = cursor.getString(0);

                return pin;
            }

            cursor.close();
        } catch (Exception e) {

        }
        return "empty";
    }

    public void DeleteLoginVerify() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.LOGINVERIFY_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteAgentDetails() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.EMPLOYEE_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteStatusDetails() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.STATUS_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteTerminalsDetails() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.TERMINAL_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteDTR() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.DTR_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteMerchant() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.MERCHANT_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteMerchantVerify() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.MERCHANTVERIFY_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

    public void DeleteDTRCounter() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from " + GlobalVariables.DTR_COUNTER_TBL;
            db.execSQL(query);
        } catch (Exception e) {

        }

    }

}
