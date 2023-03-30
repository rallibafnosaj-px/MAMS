package SQLite;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.survey.GlobalVariables;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import Adapter.MerchantAdapter;
import Model.MerchantModel;

public class SurveyFormActivitySQLite extends SQLiteDatabaseTemplate{
    String query;
    Context context;
    List<Model.MerchantModel> merchantModel;

    public SurveyFormActivitySQLite(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<String> RetrieveStatus() {


        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<String> listOfStatus = new ArrayList<>();

        try {
            String query = "Select * from " + GlobalVariables.STATUS_TBL;

            cursor = db.rawQuery(query, null);

            listOfStatus.add("Maya Status");

            if (cursor.moveToFirst()) {
                do {

                    String status = cursor.getString(0);

                    listOfStatus.add(status);

                } while (cursor.moveToNext());

                return listOfStatus;

            }

            cursor.close();


            return listOfStatus;
        } catch (Exception e) {
        }

        return listOfStatus;
    }
    public void InsertSurveyFromActivity(String dateOfVisit, String empName, String nameOfMall, String image1,
                                         String mayaStatus, String dbaName, String regBizName, String subArea,
                                         String mercSPOCName, String mercSPOCDesig, String mercSPOCEmail,
                                         String mercSPOCContact, int gcashStaticQR, int gcashQRIPOS, int gcashBoth,
                                         String terminalAvailable, String otherMercVisible, int mayaHidden, int mayaStandee,
                                         int mayaDoorHanger, int mayaNone, int nonMayaStandee, int nonMayaDoorHanger,
                                         int nonMayaNone, int qrGreenBird, int qrMaya2, String image2, String availSQR,
                                         String mercRestriction, int q1, String q2, String q3, String q4, String q5,
                                         String transactionID, String completeDelAdd, String Remarks) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String query_searchEmployee = "Select ID "  +
                    "from merchant "             +
                    "where visit_date = ? and name_of_mall = ?" +
                    " and dba_name = ?";

            cursor = db.rawQuery(query_searchEmployee, new String[]{dateOfVisit,nameOfMall,dbaName});

            if (cursor.getCount() == 0) {

                ContentValues cv = new ContentValues();
                cv.put("visit_date", dateOfVisit);
                cv.put("encoded_by", empName);
                cv.put("name_of_mall", nameOfMall);
                cv.put("upload_counter_pic", image1);
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
                cv.put("upload_sqr_pic", image2);
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
                cv.put("sync_status", "0");

                db.insert("merchant", null, cv);

                for (int a = 0; a <= GlobalVariables.pisoTestPhotos.size() - 1; a++) {
                    int b = a + 1;
                    query = "Update merchant set piso_test" + "" + String.valueOf(b) + "" + " = "
                            + "'" + GlobalVariables.pisoTestPhotos.get(a) + "'" + " where transaction_id like '" + transactionID + "'";
                    db.execSQL(query);
                }

            } else {
                Toast.makeText(context, "Error In Saving merchant", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        } catch (Exception e) {
            Log.d(TAG, "ErrorTempEmployee: " + e.toString());
        }

    }

    public void InsertSerialNumberAndImage(String TransactionID, String SN1, String SNImage1, String SN2, String SNImage2,  String SN3, String SNImage3,
                                           String SN4, String SNImage4,  String SN5, String SNImage5,  String SN6, String SNImage6,  String SN7, String SNImage7,
                                           String SN8, String SNImage8,  String SN9, String SNImage9,  String SN10, String SNImage10,
                                           String terminalType1, String terminalType2, String terminalType3, String terminalType4, String terminalType5,
                                           String terminalType6, String terminalType7, String terminalType8, String terminalType9, String terminalType10) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String query_searchEmployee = "Select transaction_id "  +
                    "from merchant_serials "             +
                    "where transaction_id = ? and sn1 = ?" +
                    " and sn_image1 = ?";

            cursor = db.rawQuery(query_searchEmployee, new String[]{TransactionID,SN1,SNImage1});

            if (cursor.getCount() == 0) {

                ContentValues cv = new ContentValues();
                cv.put("transaction_id", TransactionID);
                cv.put("sn1", SN1);
                cv.put("sn_image1", SNImage1);
                cv.put("sn2", SN2);
                cv.put("sn_image2", SNImage2);
                cv.put("sn3", SN3);
                cv.put("sn_image3", SNImage3);
                cv.put("sn4", SN4);
                cv.put("sn_image4", SNImage4);
                cv.put("sn5", SN5);
                cv.put("sn_image5", SNImage5);
                cv.put("sn6", SN6);
                cv.put("sn_image6", SNImage6);
                cv.put("sn7", SN7);
                cv.put("sn_image7", SNImage7);
                cv.put("sn8", SN8);
                cv.put("sn_image8", SNImage8);
                cv.put("sn9", SN9);
                cv.put("sn_image9", SNImage9);
                cv.put("sn10", SN10);
                cv.put("sn_image10", SNImage10);
                cv.put("terminal_type1", terminalType1);
                cv.put("terminal_type2", terminalType2);
                cv.put("terminal_type3", terminalType3);
                cv.put("terminal_type4", terminalType4);
                cv.put("terminal_type5", terminalType5);
                cv.put("terminal_type6", terminalType6);
                cv.put("terminal_type7", terminalType7);
                cv.put("terminal_type8", terminalType8);
                cv.put("terminal_type9", terminalType9);
                cv.put("terminal_type10", terminalType10);
                cv.put("sync_status", 0);

                db.insert("merchant_serials", null, cv);

                for (int a = 0; a <= GlobalVariables.deliveryPhotos.size() - 1; a++) {
                    int b = a + 1;
                    query = "Update merchant_serials set sqr_image" + "" + String.valueOf(b) + "" + " = "
                            + "'" + GlobalVariables.deliveryPhotos.get(a) + "'" + " where transaction_id like '" + TransactionID + "'";
                    db.execSQL(query);
                }

                for (int a = 0; a <= GlobalVariables.extraPhotos.size() - 1; a++) {
                    int b = a + 1;
                    query = "Update merchant_serials set extra_image" + "" + String.valueOf(b) + "" + " = "
                            + "'" + GlobalVariables.extraPhotos.get(a) + "'" + " where transaction_id like '" + TransactionID + "'";
                    db.execSQL(query);
                }

            } else {
                Toast.makeText(context, "Error In Saving merchant serials", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
        } catch (Exception e) {
            Log.d(TAG, "ErrorTempEmployee: " + e.toString());
        }

    }

    public boolean InsertMerchantCounter(String merchantCounter) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put("merchant_verify", merchantCounter);

            db.insert("merchant_verify", null, cv);

            return true;
        } catch (Exception e) {

            return false;

        }
    }

    public void DeleteMerchantCounter() {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            query = "Delete from merchant_verify";
            db.execSQL(query);
        } catch (Exception e) {

        }
    }

    public void DeleteMerchant(String ID) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            query = "Delete from " + GlobalVariables.MERCHANT_TBL +
                    " where ID =" + "'" + ID + "'";
            db.execSQL(query);


        } catch (Exception e) {
            Log.d(TAG, "UpdateDTRStatus: " + e.toString());
        }

    }

    public void DeleteMerchantSerial(String ID) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            query = "Delete from merchant_serials" +
                    " where ID =" + "'" + ID + "'";
            db.execSQL(query);


        } catch (Exception e) {
            Log.d(TAG, "UpdateDTRStatus: " + e.toString());
        }

    }
    public List<MerchantModel> RetrieveDataMerchant(String ID) {

        SQLiteDatabase db = getWritableDatabase();

        merchantModel = new ArrayList<>();
        try {
            query = "Select * from " + "merchant_visit" + " where ID = '"+ID+"'";


            cursor = db.rawQuery(query, null);


            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {

                    do {

                        String visit_date = cursor.getString(1);
                        String encoded_by = cursor.getString(2);
                        String name_of_mall = cursor.getString(3);
                        String maya_status = cursor.getString(4);
                        String dba_name = cursor.getString(5);
                        String reg_business_name = cursor.getString(6);
                        String sub_area = cursor.getString(7);
                        String merc_spoc_name = cursor.getString(8);
                        String merc_spoc_designation = cursor.getString(9);
                        String merc_spoc_email = cursor.getString(10);
                        String merc_spoc_contact = cursor.getString(11);
                        String gcash_accept_statc = cursor.getString(12);
                        String gcash_accept_qrinsidepos = cursor.getString(13);
                        String gcash_accept_both = cursor.getString(14);
                        String terminal_avail = cursor.getString(15);
                        String other_merc_visible = cursor.getString(16);
                        String maya_visibility_hidden = cursor.getString(17);
                        String maya_visibility_standee = cursor.getString(18);
                        String maya_visibility_doorhanger = cursor.getString(19);
                        String maya_visiblity_none  = cursor.getString(20);
                        String nonmaya_visiblity_standee = cursor.getString(21);
                        String nonmaya_visiblity_doorhanger = cursor.getString(22);
                        String nonmaya_visibility_none = cursor.getString(23);
                        String qr_green_bird = cursor.getString(24);
                        String qr_mayatwo = cursor.getString(25);
                        String available_sqr = cursor.getString(26);
                        String merc_restriction = cursor.getString(27);
                        String accept_other_qr = cursor.getString(28);
                        String maya_device_count = cursor.getString(29);
                        String maya_device_sn = cursor.getString(30);
                        String maya_sqr_count = cursor.getString(31);
                        String store_code = cursor.getString(32);
                        String transaction_id  = cursor.getString(33);
                        String complete_delivery_add = cursor.getString(34);
                        String remarks = cursor.getString(35);
                        String agent_id = cursor.getString(36);
                        String agent_name = cursor.getString(37);
                        String status = cursor.getString(38);
                        String merchant_id = cursor.getString(39);
                        String trade_assurance = cursor.getString(40);
                        String tat_remarks = cursor.getString(41);
                        String sync_status = cursor.getString(42);




                        Model.MerchantModel mercModel = new Model.MerchantModel(visit_date, encoded_by, name_of_mall,
                                maya_status, dba_name, reg_business_name, sub_area, merc_spoc_name, merc_spoc_designation,
                                merc_spoc_email, merc_spoc_contact, gcash_accept_statc, gcash_accept_qrinsidepos, terminal_avail,
                                other_merc_visible, maya_visibility_hidden, maya_visibility_standee, maya_visibility_doorhanger,
                                maya_visiblity_none, nonmaya_visiblity_standee, nonmaya_visiblity_doorhanger, nonmaya_visibility_none,
                                qr_green_bird, qr_mayatwo, available_sqr, merc_restriction, accept_other_qr, maya_device_count,
                                maya_device_sn, maya_sqr_count, store_code, transaction_id, complete_delivery_add, remarks,
                                agent_id, agent_name, status, merchant_id, trade_assurance, tat_remarks, sync_status, ID, gcash_accept_both);
                        merchantModel.add(mercModel);


                    } while (cursor.moveToNext());



                    return merchantModel;

                }
            } else {

            }
        } catch (Exception e) {
            Log.d(TAG, "RetrieveUnsyncMerchant: " + e.toString());
        }
        cursor.close();
        return merchantModel;
    }



    public String RetrieveAgentID() {

        SQLiteDatabase db = getWritableDatabase();

        try {
            String query_getDrDetails = "Select agent_id " +
                    "from " + GlobalVariables.EMPLOYEE_TBL;

            cursor = db.rawQuery(query_getDrDetails, new String[]{});

            if (cursor.moveToFirst()) {

                String agent_id = cursor.getString(0);

                return agent_id;
            }
            cursor.close();
        } catch (Exception e) {

        }
        return "false";
    }

    public void UpdateSurveyFromActivity() {
    }
}
