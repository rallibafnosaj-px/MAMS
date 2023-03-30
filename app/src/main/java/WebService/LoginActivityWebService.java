package WebService;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.survey.GlobalVariables;
import com.example.survey.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class LoginActivityWebService {

    Activity activity;
    StringRequest stringRequest;

    public LoginActivityWebService(Activity activity) {
        this.activity = activity;

    }

    public void LoginAccount(String username, String password) {

        GlobalVariables.processList = 0;

        GlobalVariables.progressDialogMessage = "Preparing data...";

//        SQLite.LoginActivity offlineDB = new SQLite.LoginActivity(activity);
        SQLite.SQLiteDatabaseTemplate offdb = new SQLite.SQLiteDatabaseTemplate(activity);
        SQLite.LoginActivitySQLite offlineDB = new SQLite.LoginActivitySQLite(activity);

        stringRequest = new StringRequest(Request.Method.POST, URL.LOGINACCOUNT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    if (!response.equals("1")) {
                        offlineDB.DeleteAgentDetails();
                        GlobalVariables.successLogin = true;
                        GlobalVariables.processList = 2;
                        Log.i("Error", "Success Login");
                        offlineDB.InsertLoginVerify("1");
                    } else {
                        GlobalVariables.successLogin = false;
                        GlobalVariables.thread = true;

//                        Toast.makeText(activity.getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "login: " + response);
                    }
                } catch (Exception e) {
                    GlobalVariables.thread = true;
                    Log.e(TAG, "login : " + e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                GlobalVariables.thread = true;
                Log.e(TAG, "login : " + error.toString());
//                Toast.makeText(activity, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("AgentID", username);
                params.put("Password", password);

                return params;
            }
        };
        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity.getApplicationContext()).add(stringRequest);
    }

    public void GetEmployee(String userid) {
        GlobalVariables.processList = 0;
        GlobalVariables.progressDialogMessage = "Retrieving Accounts From Server...";

        SQLite.LoginActivitySQLite offlineDB = new SQLite.LoginActivitySQLite(activity);

        stringRequest = new StringRequest(Request.Method.POST, URL.GETEMPLOYEEDETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    if (!response.equals("1")) {
                        JSONArray datas = new JSONArray(response);

                        for (int i = 0; i < datas.length(); i++) {

                            JSONObject dataObject = datas.getJSONObject(i);
                            String agentId = dataObject.getString("AgentID").trim();
                            String agentName = dataObject.getString("AgentName").trim();
                            String contactNo = dataObject.getString("ContactNo").trim();
                            String email = dataObject.getString("Email").trim();
                            String password = dataObject.getString("Password").trim();

                            offlineDB.InsertEmployee(agentId, agentName, contactNo, email, password);

                            Log.e("GetEmployee:", agentId);

                        }
                        GlobalVariables.successGetAccount = true;
                        GlobalVariables.processList = 3;

                    } else {
                        Log.d(TAG, "Get Account : " + response);
                    }

                } catch (Exception e) {

                    GlobalVariables.thread = true;
                    GlobalVariables.successGetAccount = false;
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Get Employee: " + e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                GlobalVariables.thread = true;
                GlobalVariables.successGetAccount = false;
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("AgentID", userid);

                return params;
            }
        };
        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity).add(stringRequest);
    }

    public void GetMayaStatus() {
        GlobalVariables.processList = 0;
        GlobalVariables.progressDialogMessage = "Retrieving data references...";

        SQLite.LoginActivitySQLite offlineDB = new SQLite.LoginActivitySQLite(activity);

        stringRequest = new StringRequest(Request.Method.POST, URL.GETMAYASTATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    if (!response.equals("1")) {
                        JSONArray datas = new JSONArray(response);

                        for (int i = 0; i < datas.length(); i++) {

                            JSONObject dataObject = datas.getJSONObject(i);
                            String status = dataObject.getString("Status").trim();

                            offlineDB.InsertStatus(status);

                            Log.e("GetStatus:", status);

                        }
                        GlobalVariables.successGetMayaStatus = true;
                        GlobalVariables.processList = 4;

                    } else {
                        Log.d(TAG, "Get Status : " + response);
                    }

                } catch (Exception e) {

                    GlobalVariables.thread = true;
                    GlobalVariables.successGetMayaStatus = false;
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "GetStatus: " + e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                GlobalVariables.thread = true;
                GlobalVariables.successGetMayaStatus = false;
            }
        });
        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity).add(stringRequest);
    }

    public void GetTerminals() {
        GlobalVariables.processList = 0;
        GlobalVariables.progressDialogMessage = "Retrieving data references...";

        SQLite.LoginActivitySQLite offlineDB = new SQLite.LoginActivitySQLite(activity);

        stringRequest = new StringRequest(Request.Method.POST, URL.GETTERMINALS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    if (!response.equals("1")) {
                        JSONArray datas = new JSONArray(response);

                        for (int i = 0; i < datas.length(); i++) {

                            JSONObject dataObject = datas.getJSONObject(i);
                            String terminals = dataObject.getString("Terminals").trim();

                            offlineDB.InsertTerminals(terminals);

                            Log.e("GetTerminals:", terminals);

                        }
                        GlobalVariables.successGetTerminals = true;
                        GlobalVariables.processList = 5;

                    } else {
                        Log.d(TAG, "Get Terminals : " + response);
                    }

                } catch (Exception e) {

                    GlobalVariables.thread = true;
                    GlobalVariables.successGetTerminals = false;
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "GetTerminals: " + e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                GlobalVariables.thread = true;
                GlobalVariables.successGetTerminals = false;
            }
        });
        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity).add(stringRequest);
    }

    public void GetMerchantVisit(String agentID) {
        GlobalVariables.processList = 0;
        GlobalVariables.progressDialogMessage = "Retrieving data references...";

        SQLite.LoginActivitySQLite offlineDB = new SQLite.LoginActivitySQLite(activity);

        stringRequest = new StringRequest(Request.Method.POST, URL.GETMERCHANTVISIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    if (!response.equals("1")) {
                        JSONArray datas = new JSONArray(response);

                        for (int i = 0; i < datas.length(); i++) {

                            JSONObject dataObject = datas.getJSONObject(i);
                            String merchantId = dataObject.getString("MerchantID").trim();
                            String visitDate = dataObject.getString("VisitDate").trim();
                            String encodedBy = dataObject.getString("EncodedBy").trim();
                            String nameOfMall = dataObject.getString("NameOfMall").trim();
                            String mayaStatus = dataObject.getString("MayaStatus").trim();
                            String dbaName = dataObject.getString("DBAName").trim();
                            String regBizNam = dataObject.getString("RegisteredBusinessname").trim();
                            String subArea = dataObject.getString("SubArea").trim();
                            String mercSPOCName = dataObject.getString("MercSPOCName").trim();
                            String mercSPOCDesig = dataObject.getString("MercSPOCDesignation").trim();
                            String mercSPOCEmail = dataObject.getString("MercSPOCEmail").trim();
                            String mercSPOCContactNo = dataObject.getString("MercSPOCContactNo").trim();
                            String gcashStatic = dataObject.getString("GCashAcceptStatic").trim();
                            String gcashAccept = dataObject.getString("GCashAcceptQRInsidePOS").trim();
                            String gcashBoth = dataObject.getString("GCashAcceptBoth").trim();
                            String terminalAvail = dataObject.getString("TerminalAvailable").trim();
                            String otherMercVisible = dataObject.getString("OtherMercVisible").trim();
                            String mayaHidden = dataObject.getString("MayaVisibilityHidden").trim();
                            String mayaStandee = dataObject.getString("MayaVisibilityStandee").trim();
                            String mayaDoor = dataObject.getString("MayaVisibilityDoorHanger").trim();
                            String mayaNone = dataObject.getString("MayaVisibilityNone").trim();
                            String nonMayaStandee = dataObject.getString("NonMayaVisibilityStandee").trim();
                            String nonMayaDoor = dataObject.getString("NonMayaVisibilityDoorHanger").trim();
                            String nonMayaNone = dataObject.getString("NonMayaNone").trim();
                            String qrbird = dataObject.getString("QRVersionGreenBird").trim();
                            String qrMayatwo = dataObject.getString("QRVersionMaya2").trim();
                            String availSQR = dataObject.getString("AvailableSQR").trim();
                            String mercRestrict = dataObject.getString("MercRestriction").trim();
                            String acceptOtherQR = dataObject.getString("AcceptOtherQR").trim();
                            String mayaDeviceCount = dataObject.getString("MayaDevicesCount").trim();
                            String deviceSerial = dataObject.getString("DeviceSerialNo").trim();
                            String mayaSQRCount = dataObject.getString("MayaSQRCount").trim();
                            String storeCode = dataObject.getString("StoreCode").trim();
                            String transactionID = dataObject.getString("TransactionID").trim();
                            String completeDelAdd = dataObject.getString("CompleteDeliveryAdd").trim();
                            String remarks = dataObject.getString("Remarks").trim();
                            String agentID = dataObject.getString("AgentID").trim();
                            String agentName = dataObject.getString("AgentName").trim();
                            String status = dataObject.getString("Status").trim();
                            String tradeAssuranceType = dataObject.getString("TradeAssuranceType").trim();
                            String tatRemarks = dataObject.getString("TATRemarks").trim();


                            offlineDB.InsertMerchantVisit(merchantId, visitDate, encodedBy, nameOfMall, mayaStatus,
                                    dbaName, regBizNam, subArea, mercSPOCName, mercSPOCDesig, mercSPOCEmail, mercSPOCContactNo,
                                    gcashStatic, gcashAccept, gcashBoth, terminalAvail, otherMercVisible, mayaHidden, mayaStandee,
                                    mayaDoor, mayaNone, nonMayaStandee, nonMayaDoor, nonMayaNone, qrbird, qrMayatwo, availSQR,
                                    mercRestrict, acceptOtherQR, mayaDeviceCount, deviceSerial, mayaSQRCount, storeCode,
                                    transactionID, completeDelAdd, remarks, agentID, agentName, status, tradeAssuranceType, tatRemarks);

                            Log.e("Get Merchant Visit:", nameOfMall);

                        }
                        GlobalVariables.successGetMerchantVisit = true;
                        GlobalVariables.thread = true;
                        GlobalVariables.processList = 0;

                    } else {
                        Log.d(TAG, "Get Error  Merchant Visit: " + response);
                    }

                } catch (Exception e) {

                    GlobalVariables.thread = true;
                    GlobalVariables.successGetMerchantVisit = false;
//                    Toast.makeText(activity, "" + response, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Get Error Merchant Visit:" + e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                GlobalVariables.thread = true;
                GlobalVariables.successGetMerchantVisit = false;
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("AgentID", agentID);

                return params;
            }
        };
        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity).add(stringRequest);
    }

    private void retryPolicy(StringRequest stringRequest) {
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
