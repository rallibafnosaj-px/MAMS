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
import com.example.survey.URL;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ItineraryWebService {

    Activity activity;
    StringRequest stringRequest;

    public ItineraryWebService(Activity activity) {
        this.activity = activity;
    }

    public void UpdateAssignStatus(String id, String status) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.UPDATEASSIGNSTATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("0")) {

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

                params.put("ID", id);
                params.put("Status", String.valueOf(status));

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
