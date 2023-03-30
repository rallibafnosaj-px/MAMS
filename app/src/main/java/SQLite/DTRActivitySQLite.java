package SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.survey.GlobalVariables;

import static android.content.ContentValues.TAG;

public class DTRActivitySQLite extends SQLiteDatabaseTemplate{
    String query;
    Context context;

    public DTRActivitySQLite(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public boolean CheckDTRTimeIn() {

        Boolean ifDTRTimeIn = false;

        String query = "SELECT * from dtr_counter";

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            cursor = db.rawQuery(query, null);
            {
                if (cursor.getCount() != 0) {
                    return ifDTRTimeIn = true;
                }
            }
            cursor.close();
            return ifDTRTimeIn;

        } catch (Exception e) {
            Log.d(TAG, "CheckDTRTimeIn: " + e.toString());
            return ifDTRTimeIn;
        }
    }

    public boolean InsertDTRCounter(String dtrCounter) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put("counter", dtrCounter);

            db.insert("dtr_counter", null, cv);

            return true;
        } catch (Exception e) {

            return false;

        }
    }

    public void InsertDTRIn(String employeeNumber, String datex, String dtrIn, String latitudeIn, String longitudeIn, String addressIn, String imageIn) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String query_searchEmployee = "Select ID "  +
                    "from daily_time_record "             +
                    "where dtr_in is Null";

            cursor = db.rawQuery(query_searchEmployee, new String[]{});

            if (cursor.getCount() == 0) {

                ContentValues cv = new ContentValues();
                cv.put("employee_number", employeeNumber);
                cv.put("datex", datex);
                cv.put("dtr_in", dtrIn);
                cv.put("lat_in", latitudeIn);
                cv.put("long_in", longitudeIn);
                cv.put("address_in", addressIn);
                cv.put("image_in", imageIn);
                cv.put("is_active", "1");
                cv.put("status_sync", "0");

                db.insert("daily_time_record", null, cv);
            } else {

            }

            cursor.close();
        } catch (Exception e) {
            Log.d(TAG, "ErrorTempEmployee: " + e.toString());
        }

    }

    public void DeleteDTR(String ID) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            query = "Delete from " + GlobalVariables.DTR_TBL +
                    " where ID =" + "'" + ID + "'";
            db.execSQL(query);


        } catch (Exception e) {
            Log.d(TAG, "UpdateDTRStatus: " + e.toString());
        }

    }

    public String getEmp() {

        SQLiteDatabase db = getWritableDatabase();

        try {
            String query_getDrDetails = "Select agent_name " +
                    "from " + GlobalVariables.EMPLOYEE_TBL;

            cursor = db.rawQuery(query_getDrDetails, new String[]{});

            if (cursor.moveToFirst()) {

                String agent_name = cursor.getString(0);

                return agent_name;
            }
            cursor.close();
        } catch (Exception e) {

        }
        return "false";
    }

    public boolean InsertDTROut(String dtrOut, String latitudeOut, String longitudeOut, String addressOut, String imageOut) {

        try {

            String query = "Update daily_time_record"  +
                    " set dtr_out = '" + dtrOut + "', "          +
                    "lat_out = '" + latitudeOut + "', "     +
                    "long_out = '" + longitudeOut + "', "  +
                    "address_out = '" + addressOut + "', "       +
                    "image_out = '" + imageOut + "'"           +
                    "where dtr_out IS NULL or dtr_out = ''";

            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL(query);

            DeleteDTRCounter();

            return true;

        } catch (Exception e) {
            Log.d(TAG, "ErrorTempEmployeeDTROut: " + e.toString());
            return false;
        }

    }

    public String getAgentID() {

        SQLiteDatabase db = getWritableDatabase();

        try {
            String query_getDrDetails = "Select agent_id " +
                    "from agent";

            cursor = db.rawQuery(query_getDrDetails, new String[]{});

            if (cursor.moveToFirst()) {

                String agentid = cursor.getString(0);

                return agentid;

            }
            cursor.close();
        } catch (Exception e) {

        }
        return "false";
    }

    public void DeleteDTRCounter() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from dtr_counter";
            db.execSQL(query);
        } catch (Exception e) {

        }
    }
}
