package WebService;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.survey.GlobalVariables;
import com.example.survey.URL;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivityWebService {

    Activity activity;
    StringRequest stringRequest;


    public MainActivityWebService(Activity activity) {
        this.activity = activity;
    }

    public void SyncMerchantToLive(String id, String visitDate, String encodedBy, String nameOfMall, String uploadCounterPic, String mayaStatus, String dbaName,
                                   String regBizName, String subArea, String mercSpocName, String mercSpocDesig, String mercSpocEmail, String mercSpocContact, String gcashAcceptStatic,
                                   String gcashAcceptQRPOS, String gcashAcceptBoth, String terminalAvail, String otherMercVisible, String mayaVisibilityHidden, String mayaVisibilityStandee,
                                   String mayaVisibilityDoorHanger, String mayaVisibilityNone, String nonmayaVisibilityStandee, String nonmayaVisibilityDoorHanger, String nonmayaVisibilityNone,
                                   String qrGreenBird, String qrMayatwo, String sqrPic, String availableSpic, String mercRestriction, String acceptOtherQR, String mayaDeviceCount, String mayaDeviceSN,
                                   String mayaSQRCount, String StoreCode, String pisoTest1, String pisoTest2, String transactionID, String completeAdd, String remarks) {

        GlobalVariables.processListSync = 0;
        GlobalVariables.progressDialogMessage = "Syncing Merchant Details...";

        SQLite.SurveyFormActivitySQLite offlineDB = new SQLite.SurveyFormActivitySQLite(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.ADDMERCHANT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    offlineDB.DeleteMerchant(id);

                    GlobalVariables.processListSync = 2;
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    GlobalVariables.thread = true;
                    GlobalVariables.processListSync = 0;
                    Log.d(TAG, "onResponse: " + response);
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
//                    SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "" + error.toString(), Toast.LENGTH_SHORT).show();
                GlobalVariables.thread = true;
                Log.d(TAG, "onErrorResponse merchant: " + error.toString());
//                SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();

                params.put("VisitDate", visitDate);
                params.put("EncodedBy", encodedBy);
                params.put("NameOfMall", nameOfMall);
                params.put("UploadCounterPicture", uploadCounterPic);
                params.put("MayaStatus", mayaStatus);
                params.put("DBAName", dbaName);
                params.put("RegisteredBusinessname", regBizName);
                params.put("SubArea", subArea);
                params.put("MercSPOCName", mercSpocName);
                params.put("MercSPOCDesignation", mercSpocDesig);
                params.put("MercSPOCEmail", mercSpocEmail);
                params.put("MercSPOCContactNo", mercSpocContact);
                params.put("GCashAcceptStatic", gcashAcceptStatic);
                params.put("GCashAcceptQRInsidePOS", gcashAcceptQRPOS);
                params.put("GCashAcceptBoth", gcashAcceptBoth);
                params.put("TerminalAvailable", terminalAvail);
                params.put("OtherMercVisible", otherMercVisible);
                params.put("MayaVisibilityHidden", mayaVisibilityHidden);
                params.put("MayaVisibilityStandee", mayaVisibilityStandee);
                params.put("MayaVisibilityDoorHanger", mayaVisibilityDoorHanger);
                params.put("MayaVisibilityNone", mayaVisibilityNone);
                params.put("NonMayaVisibilityStandee", nonmayaVisibilityStandee);
                params.put("NonMayaVisibilityDoorHanger", nonmayaVisibilityDoorHanger);
                params.put("NonMayaNone", nonmayaVisibilityNone);
                params.put("QRVersionGreenBird", qrGreenBird);
                params.put("QRVersionMaya2", qrMayatwo);
                params.put("UploadSQPicture", sqrPic);
                params.put("AvailableSQR", availableSpic);
                params.put("MercRestriction", mercRestriction);
                params.put("AcceptOtherQR", acceptOtherQR);
                params.put("MayaDevicesCount", mayaDeviceCount);
                params.put("DeviceSerialNo", mayaDeviceSN);
                params.put("MayaSQRCount", mayaSQRCount);
                params.put("StoreCode", StoreCode);
                params.put("UploadStoreQR", "Sample");
                params.put("PisoTest1", pisoTest1);
                params.put("PisoTest2", pisoTest2);
                params.put("TransactionID", transactionID);
                params.put("CompleteDeliveryAdd", completeAdd);
                params.put("Remarks", remarks);

                return params;
            }
        };

        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity.getApplicationContext()).add(stringRequest);
    }

    public void AutoSyncMerchantToLive(String id, String visitDate, String encodedBy, String nameOfMall, String uploadCounterPic, String mayaStatus, String dbaName,
                                       String regBizName, String subArea, String mercSpocName, String mercSpocDesig, String mercSpocEmail, String mercSpocContact, String gcashAcceptStatic,
                                       String gcashAcceptQRPOS, String gcashAcceptBoth, String terminalAvail, String otherMercVisible, String mayaVisibilityHidden, String mayaVisibilityStandee,
                                       String mayaVisibilityDoorHanger, String mayaVisibilityNone, String nonmayaVisibilityStandee, String nonmayaVisibilityDoorHanger, String nonmayaVisibilityNone,
                                       String qrGreenBird, String qrMayatwo, String sqrPic, String availableSpic, String mercRestriction, String acceptOtherQR, String mayaDeviceCount, String mayaDeviceSN,
                                       String mayaSQRCount, String StoreCode, String pisoTest1, String pisoTest2, String transactionID, String completeAdd, String remarks) {

        GlobalVariables.processListSync = 0;

        SQLite.SurveyFormActivitySQLite offlineDB = new SQLite.SurveyFormActivitySQLite(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.ADDMERCHANT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    offlineDB.DeleteMerchant(id);

                    GlobalVariables.processListSync = 12;
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    GlobalVariables.processListSync = 0;
                    Log.d(TAG, "onResponse: " + response);
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
//                    SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorResponse merchant: " + error.toString());
//                SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();

                params.put("VisitDate", visitDate);
                params.put("EncodedBy", encodedBy);
                params.put("NameOfMall", nameOfMall);
                params.put("UploadCounterPicture", uploadCounterPic);
                params.put("MayaStatus", mayaStatus);
                params.put("DBAName", dbaName);
                params.put("RegisteredBusinessname", regBizName);
                params.put("SubArea", subArea);
                params.put("MercSPOCName", mercSpocName);
                params.put("MercSPOCDesignation", mercSpocDesig);
                params.put("MercSPOCEmail", mercSpocEmail);
                params.put("MercSPOCContactNo", mercSpocContact);
                params.put("GCashAcceptStatic", gcashAcceptStatic);
                params.put("GCashAcceptQRInsidePOS", gcashAcceptQRPOS);
                params.put("GCashAcceptBoth", gcashAcceptBoth);
                params.put("TerminalAvailable", terminalAvail);
                params.put("OtherMercVisible", otherMercVisible);
                params.put("MayaVisibilityHidden", mayaVisibilityHidden);
                params.put("MayaVisibilityStandee", mayaVisibilityStandee);
                params.put("MayaVisibilityDoorHanger", mayaVisibilityDoorHanger);
                params.put("MayaVisibilityNone", mayaVisibilityNone);
                params.put("NonMayaVisibilityStandee", nonmayaVisibilityStandee);
                params.put("NonMayaVisibilityDoorHanger", nonmayaVisibilityDoorHanger);
                params.put("NonMayaNone", nonmayaVisibilityNone);
                params.put("QRVersionGreenBird", qrGreenBird);
                params.put("QRVersionMaya2", qrMayatwo);
                params.put("UploadSQPicture", sqrPic);
                params.put("AvailableSQR", availableSpic);
                params.put("MercRestriction", mercRestriction);
                params.put("AcceptOtherQR", acceptOtherQR);
                params.put("MayaDevicesCount", mayaDeviceCount);
                params.put("DeviceSerialNo", mayaDeviceSN);
                params.put("MayaSQRCount", mayaSQRCount);
                params.put("StoreCode", StoreCode);
                params.put("UploadStoreQR", "Sample");
                params.put("PisoTest1", pisoTest1);
                params.put("PisoTest2", pisoTest2);
                params.put("TransactionID", transactionID);
                params.put("CompleteDeliveryAdd", completeAdd);
                params.put("Remarks", remarks);

                return params;
            }
        };

        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity.getApplicationContext()).add(stringRequest);
    }

    public void SyncMerchantSerialsToLive(String id, String transactionid, String sn1, String snimage1, String sn2, String snimage2, String sn3, String snimage3,
                                          String sn4, String snimage4, String sn5, String snimage5, String sn6, String snimage6, String sn7,
                                          String snimage7, String sn8, String snimage8, String sn9, String snimage9, String sn10,
                                          String snimage10, String terminal1, String terminal2, String terminal3, String terminal4, String terminal5, String terminal6,
                                          String terminal7, String terminal8, String terminal9, String terminal10, String sqrimage1, String sqrimage2,
                                          String sqrimage3, String sqrimage4, String sqrimage5, String extraimage1, String extraimage2, String extraimage3,
                                          String extraimage4, String extraimage5) {

        GlobalVariables.processListSync = 0;
        GlobalVariables.progressDialogMessage = "Syncing Merchant Details...";

        SQLite.SurveyFormActivitySQLite offlineDB = new SQLite.SurveyFormActivitySQLite(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.ADDMERCHANTSERIAL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    offlineDB.DeleteMerchantSerial(id);

                    GlobalVariables.processListSync = 3;
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    GlobalVariables.thread = true;
                    GlobalVariables.processListSync = 0;
                    Log.d(TAG, "onResponse: " + response);
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
//                    SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "" + error.toString(), Toast.LENGTH_SHORT).show();
                GlobalVariables.thread = true;
                Log.d(TAG, "onErrorResponse merchant details: " + error.toString());
//                SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();

                params.put("transaction_id", transactionid);
                params.put("sn1", sn1);
                params.put("sn_image1", snimage1);
                params.put("sn2", sn2);
                params.put("sn_image2", snimage2);
                params.put("sn3", sn3);
                params.put("sn_image3", snimage3);
                params.put("sn4", sn4);
                params.put("sn_image4", snimage4);
                params.put("sn5", sn5);
                params.put("sn_image5", snimage5);
                params.put("sn6", sn6);
                params.put("sn_image6", snimage6);
                params.put("sn7", sn7);
                params.put("sn_image7", snimage7);
                params.put("sn8", sn8);
                params.put("sn_image8", snimage8);
                params.put("sn9", sn9);
                params.put("sn_image9", snimage9);
                params.put("sn10", sn10);
                params.put("sn_image10", snimage10);
                params.put("terminal_type1", terminal1);
                params.put("terminal_type2", terminal2);
                params.put("terminal_type3", terminal3);
                params.put("terminal_type4", terminal4);
                params.put("terminal_type5", terminal5);
                params.put("terminal_type6", terminal6);
                params.put("terminal_type7", terminal7);
                params.put("terminal_type8", terminal8);
                params.put("terminal_type9", terminal9);
                params.put("terminal_type10", terminal10);
                params.put("sqr_image1", sqrimage1);
                params.put("sqr_image2", sqrimage2);
                params.put("sqr_image3", sqrimage3);
                params.put("sqr_image4", sqrimage4);
                params.put("sqr_image5", sqrimage5);
                params.put("extra_image1", extraimage1);
                params.put("extra_image2", extraimage2);
                params.put("extra_image3", extraimage3);
                params.put("extra_image4", extraimage4);
                params.put("extra_image5", extraimage5);

                return params;
            }
        };

        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity.getApplicationContext()).add(stringRequest);
    }

    public void AutoSyncMerchantSerialsToLive(String id, String transactionid, String sn1, String snimage1, String sn2, String snimage2, String sn3, String snimage3,
                                              String sn4, String snimage4, String sn5, String snimage5, String sn6, String snimage6, String sn7,
                                              String snimage7, String sn8, String snimage8, String sn9, String snimage9, String sn10,
                                              String snimage10, String terminal1, String terminal2, String terminal3, String terminal4, String terminal5, String terminal6,
                                              String terminal7, String terminal8, String terminal9, String terminal10, String sqrimage1, String sqrimage2,
                                              String sqrimage3, String sqrimage4, String sqrimage5, String extraimage1, String extraimage2, String extraimage3,
                                              String extraimage4, String extraimage5) {

        GlobalVariables.processListSync = 0;

        SQLite.SurveyFormActivitySQLite offlineDB = new SQLite.SurveyFormActivitySQLite(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.ADDMERCHANTSERIAL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    offlineDB.DeleteMerchantSerial(id);

                    GlobalVariables.processListSync = 13;
                } else {
                    GlobalVariables.processListSync = 0;
                    Log.d(TAG, "onResponse: " + response);
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
//                    SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "" + error.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorResponse merchant details: " + error.toString());
//                SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();

                params.put("transaction_id", transactionid);
                params.put("sn1", sn1);
                params.put("sn_image1", snimage1);
                params.put("sn2", sn2);
                params.put("sn_image2", snimage2);
                params.put("sn3", sn3);
                params.put("sn_image3", snimage3);
                params.put("sn4", sn4);
                params.put("sn_image4", snimage4);
                params.put("sn5", sn5);
                params.put("sn_image5", snimage5);
                params.put("sn6", sn6);
                params.put("sn_image6", snimage6);
                params.put("sn7", sn7);
                params.put("sn_image7", snimage7);
                params.put("sn8", sn8);
                params.put("sn_image8", snimage8);
                params.put("sn9", sn9);
                params.put("sn_image9", snimage9);
                params.put("sn10", sn10);
                params.put("sn_image10", snimage10);
                params.put("terminal_type1", terminal1);
                params.put("terminal_type2", terminal2);
                params.put("terminal_type3", terminal3);
                params.put("terminal_type4", terminal4);
                params.put("terminal_type5", terminal5);
                params.put("terminal_type6", terminal6);
                params.put("terminal_type7", terminal7);
                params.put("terminal_type8", terminal8);
                params.put("terminal_type9", terminal9);
                params.put("terminal_type10", terminal10);
                params.put("sqr_image1", sqrimage1);
                params.put("sqr_image2", sqrimage2);
                params.put("sqr_image3", sqrimage3);
                params.put("sqr_image4", sqrimage4);
                params.put("sqr_image5", sqrimage5);
                params.put("extra_image1", extraimage1);
                params.put("extra_image2", extraimage2);
                params.put("extra_image3", extraimage3);
                params.put("extra_image4", extraimage4);
                params.put("extra_image5", extraimage5);

                return params;
            }
        };

        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity.getApplicationContext()).add(stringRequest);
    }

    public void retryPolicy(StringRequest stringRequest) {

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    public void SyncMerchantVisitToLive(String visit_date, String encoded_by, String name_of_mall,
                                        String maya_status, String dba_name, String reg_business_name,
                                        String sub_area, String merc_spoc_name, String merc_spoc_designation,
                                        String merc_spoc_email, String merc_spoc_contact, String gcash_accept_statc, String gcash_accept_qrinsidepos, String terminal_avail,
                                        String other_merc_visible, String maya_visibility_hidden, String maya_visibility_standee, String maya_visibility_doorhanger, String maya_visibility_none,
                                        String nonmaya_visiblity_standee, String nonmaya_visiblity_doorhanger, String nonmaya_visibility_none, String qr_green_bird, String qr_mayatwo,
                                        String available_sqr, String merc_restriction, String accept_other_qr, String maya_device_count, String maya_device_sn, String maya_sqr_count, String store_code,
                                        String transaction_id, String complete_delivery_add, String remarks, String agent_id, String agent_name, String status, String merchant_id, String trade_assurance,
                                        String tat_remarks, String sync_status, String id, String gcash_accept_both) {

        GlobalVariables.processListSync = 0;
        SQLite.SurveyFormActivitySQLite offlineDB = new SQLite.SurveyFormActivitySQLite(activity);
        GlobalVariables.progressDialogMessage = "Syncing Merchant Visit Details...";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.GETSYNCEDMERCHANTVISIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    offlineDB.DeleteMerchantVisit(id);
                    GlobalVariables.processListSync = 4;
                } else {
                    GlobalVariables.processListSync = 0;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "" + error.toString(), Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onErrorResponse merchant details: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                HashMap<String, String> params = new HashMap<>();

                params.put("visit_date", visit_date);
                params.put("encoded_by", encoded_by);
                params.put("name_of_mall", name_of_mall);
                params.put("maya_status", maya_status);
                params.put("dba_name", dba_name);
                params.put("reg_business_name", reg_business_name);
                params.put("sub_area", sub_area);
                params.put("merc_spoc_name", merc_spoc_name);
                params.put("merc_spoc_designation", merc_spoc_designation);
                params.put("merc_spoc_email", merc_spoc_email);
                params.put("merc_spoc_contact", merc_spoc_contact);
                params.put("gcash_accept_statc", gcash_accept_statc);
                params.put("gcash_accept_qrinsidepos", gcash_accept_qrinsidepos);
                params.put("terminal_avail", terminal_avail);
                params.put("other_merc_visible", other_merc_visible);
                params.put("maya_visibility_hidden", maya_visibility_hidden);
                params.put("maya_visibility_standee", maya_visibility_standee);
                params.put("maya_visibility_doorhanger", maya_visibility_doorhanger);
                params.put("maya_visibility_none", maya_visibility_none);
                params.put("nonmaya_visibility_standee", nonmaya_visiblity_standee);
                params.put("merc_restriction", merc_restriction);
                params.put("nonmaya_visibility_doorhanger", nonmaya_visiblity_doorhanger);
                params.put("nonmaya_visibility_none", nonmaya_visibility_none);
                params.put("qr_green_bird", qr_green_bird);
                params.put("qr_mayatwo", qr_mayatwo);
                params.put("available_sqr", available_sqr);
                params.put("accept_other_qr", accept_other_qr);
                params.put("maya_device_count", maya_device_count);
                params.put("maya_device_sn", maya_device_sn);
                params.put("maya_sqr_count", maya_sqr_count);
                params.put("store_code", store_code);
                params.put("transaction_id", transaction_id);
                params.put("complete_delivery_add", complete_delivery_add);
                params.put("remarks", remarks);
                params.put("agent_name", agent_name);
                params.put("status", status);
                params.put("agent_id", agent_id);
                params.put("merchant_id", merchant_id);
                params.put("trade_assurance", trade_assurance);
                params.put("tat_remarks", tat_remarks);
                params.put("sync_status", sync_status);
                params.put("id", id);
                params.put("gcash_accept_both", gcash_accept_both);


                return params;
            }
        };
        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity.getApplicationContext()).add(stringRequest);
    }
}
