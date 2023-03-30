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

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class DTRActivityWebService {
    Activity activity;
    StringRequest stringRequest;


    public DTRActivityWebService(Activity activity) {
        this.activity = activity;
    }

    public void SyncDTRToLive(String id, String employeeNumber, String datex, String timeIn, String timeOut, String locationIn, String locationOut,
                              String imageIn, String imageOut) {

        GlobalVariables.processListSync = 0;
        GlobalVariables.progressDialogMessage = "Syncing DTR...";

        SQLite.DTRActivitySQLite offlineDB = new SQLite.DTRActivitySQLite(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.ADDDTR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    offlineDB.DeleteDTR(id);

                    GlobalVariables.processListSync = 1;

                } else {
                    GlobalVariables.thread = true;
                    Log.d(TAG, "onResponse: " + response);
//                    SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                GlobalVariables.thread = true;
                Log.d(TAG, "onErrorResponse: " + error.toString());
//                SyncDTRToLive(id, employeeNumber, dtrIn, dtrOut, storeLoc, latIn, latOut, longIn, longOut, addressin, addressout, imageIn, imageOut, dutyType);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();

                params.put("employee_number", employeeNumber);
                params.put("employee_name", offlineDB.getEmp());
                params.put("datex", datex);
                params.put("time_in", timeIn);
                params.put("time_out", timeOut);
                params.put("address_in", locationIn);
                params.put("address_out", locationOut);
                params.put("image_in", imageIn);
                params.put("image_out", imageOut);

                return params;
            }
        };

        retryPolicy(stringRequest);
        Volley.newRequestQueue(activity.getApplicationContext()).add(stringRequest);
    }

    public void AutoSyncDTRToLive(String id, String employeeNumber, String datex, String timeIn, String timeOut, String locationIn, String locationOut,
                              String imageIn, String imageOut) {

        GlobalVariables.processListSync = 0;

        SQLite.DTRActivitySQLite offlineDB = new SQLite.DTRActivitySQLite(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.ADDDTR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {
                    offlineDB.DeleteDTR(id);

                    GlobalVariables.processListSync = 11;

                } else {
                    Log.d(TAG, "onResponse: " + response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();

                params.put("employee_number", employeeNumber);
                params.put("employee_name", offlineDB.getEmp());
                params.put("datex", datex);
                params.put("time_in", timeIn);
                params.put("time_out", timeOut);
                params.put("address_in", locationIn);
                params.put("address_out", locationOut);
                params.put("image_in", imageIn);
                params.put("image_out", imageOut);

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
}
