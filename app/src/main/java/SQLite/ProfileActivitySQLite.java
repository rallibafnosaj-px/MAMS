package SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.survey.GlobalVariables;

public class ProfileActivitySQLite extends SQLiteDatabaseTemplate {
    String query;
    Context context;
    public ProfileActivitySQLite(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public String RetrieveEmp() {

        SQLiteDatabase db = getWritableDatabase();

        try {
            String query_getDrDetails = "Select agent_name, " +
                    "contact_no, "                           +
                    "email "                          +
                    "from " + GlobalVariables.EMPLOYEE_TBL;

            cursor = db.rawQuery(query_getDrDetails, new String[]{});

            if (cursor.moveToFirst()) {

                String agent_name = cursor.getString(0);
                String contact_no = cursor.getString(1);
                String email = cursor.getString(2);

                return agent_name + "=" + contact_no + "=" + email;
            }
            cursor.close();
        } catch (Exception e) {

        }
        return "false";
    }


}
