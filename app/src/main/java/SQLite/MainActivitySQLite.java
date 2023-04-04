package SQLite;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.survey.GlobalVariables;

import WebService.SurveyFormActivityWebService;

public class MainActivitySQLite extends SQLiteDatabaseTemplate {
    String query;
    Context context;

    public MainActivitySQLite(@Nullable Context context) {
        super(context);
        this.context = context;

    }


    public boolean CheckIfPinExist() {

        Boolean ifPinExist = false;
        String query = "SELECT * " +
                "from pin";

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            cursor = db.rawQuery(query, null);
            {
                if (cursor.getCount() != 0) {
                    return ifPinExist = true;
                }
            }
            cursor.close();
            return ifPinExist;

        } catch (Exception e) {
            return ifPinExist;
        }
    }

    public boolean CheckMerchantVerify() {

        Boolean ifMerchantVerify = false;

        String query = "SELECT * from merchant_verify";

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            cursor = db.rawQuery(query, null);
            {
                if (cursor.getCount() != 0) {
                    return ifMerchantVerify = true;
                }
            }
            cursor.close();
            return ifMerchantVerify;

        } catch (Exception e) {
            Log.d(TAG, "CheckDTRTimeIn: " + e.toString());
            return ifMerchantVerify;
        }
    }

    public void InsertPin(String pin) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String query_searchEmployee = "Select pin " +
                    "from pin";

            cursor = db.rawQuery(query_searchEmployee, new String[]{});

            if (cursor.getCount() == 0) {
                DeletePin();

                ContentValues cv = new ContentValues();
                cv.put("pin", pin);

                db.insert("pin", null, cv);
            } else {
                DeletePin();
                ContentValues cv = new ContentValues();
                cv.put("pin", pin);

                db.insert("pin", null, cv);
            }

            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Errorpin: " + e.toString());
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

    public boolean CheckTimeInOut() {

        Boolean ifDTRTimeIn = false;

        String query = "SELECT * from daily_time_record " +
                "where status_sync = 0";

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
            Log.d(TAG, "CheckTimeIn: " + e.toString());
            return ifDTRTimeIn;
        }
    }

    public void SyncDTRToLive() {

        try {
            String query_getUnsyncDTR = "SELECT * FROM " + GlobalVariables.DTR_TBL +
                    " where dtr_out != '' " +
                    "and status_sync = 0 limit 1";
            SQLiteDatabase db = this.getWritableDatabase();

            Activity activity = (Activity) context;
            WebService.DTRActivityWebService onlineDB = new WebService.DTRActivityWebService(activity);

            cursor = db.rawQuery(query_getUnsyncDTR, null);

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    String id = String.valueOf(cursor.getInt(0));
                    String employee_number = cursor.getString(1);
                    String datex = cursor.getString(2);
                    String dtr_in = cursor.getString(3);
                    String dtr_out = cursor.getString(4);
//                    String lat_in = cursor.getString(4);
//                    String lat_out = cursor.getString(5);
//                    String long_in = cursor.getString(6);
//                    String long_out = cursor.getString(7);
                    String address_in = cursor.getString(9);
                    String address_out = cursor.getString(10);
                    String image_in = cursor.getString(11);
                    String image_out = cursor.getString(12);


                    onlineDB.SyncDTRToLive(id, employee_number, datex, dtr_in, dtr_out, address_in,
                            address_out, image_in, image_out);

                }
            } else {
                GlobalVariables.processListSync = 2;
            }

        } catch (Exception e) {
            Log.d(TAG, "SyncDTRToLive: " + e.toString());
            Toast.makeText(context.getApplicationContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();

        }

    }

    public void AutoSyncDTRToLive() {

        try {
            String query_getUnsyncDTR = "SELECT * FROM " + GlobalVariables.DTR_TBL +
                    " where dtr_out != '' " +
                    "and status_sync = 0 limit 1";
            SQLiteDatabase db = this.getWritableDatabase();

            Activity activity = (Activity) context;
            WebService.DTRActivityWebService onlineDB = new WebService.DTRActivityWebService(activity);

            cursor = db.rawQuery(query_getUnsyncDTR, null);

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    String id = String.valueOf(cursor.getInt(0));
                    String employee_number = cursor.getString(1);
                    String datex = cursor.getString(2);
                    String dtr_in = cursor.getString(3);
                    String dtr_out = cursor.getString(4);
//                    String lat_in = cursor.getString(4);
//                    String lat_out = cursor.getString(5);
//                    String long_in = cursor.getString(6);
//                    String long_out = cursor.getString(7);
                    String address_in = cursor.getString(9);
                    String address_out = cursor.getString(10);
                    String image_in = cursor.getString(11);
                    String image_out = cursor.getString(12);


                    onlineDB.AutoSyncDTRToLive(id, employee_number, datex, dtr_in, dtr_out, address_in,
                            address_out, image_in, image_out);

                }
            } else {
                GlobalVariables.processListSync = 12;
            }

        } catch (Exception e) {
            Log.d(TAG, "SyncDTRToLive: " + e.toString());

        }

    }

    public void SyncMerchantToLive() {

        try {
            String query_getUnsyncMerchant = "SELECT * FROM " + GlobalVariables.MERCHANT_TBL +
                    " where visit_date != '' " +
                    "and sync_status = 0 limit 1";
            SQLiteDatabase db = this.getWritableDatabase();

            Activity activity = (Activity) context;
            WebService.DTRActivityWebService onlineDB = new WebService.DTRActivityWebService(activity);
            WebService.MainActivityWebService mainDB = new WebService.MainActivityWebService(activity);

            cursor = db.rawQuery(query_getUnsyncMerchant, null);

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    String id = String.valueOf(cursor.getInt(0));
                    String visitDate = cursor.getString(1);
                    String encodedBy = cursor.getString(2);
                    String nameOfMall = cursor.getString(3);
                    String uploadCounterPic = cursor.getString(4);
                    String mayaStatus = cursor.getString(5);
                    String dbaName = cursor.getString(6);
                    String regBizName = cursor.getString(7);
                    String subArea = cursor.getString(8);
                    String mercSpocName = cursor.getString(9);
                    String mercSpocDesig = cursor.getString(10);
                    String mercSpocEmail = cursor.getString(11);
                    String mercSpocContact = cursor.getString(12);
                    String gcashAcceptStatic = cursor.getString(13);
                    String gcashAcceptQRPOS = cursor.getString(14);
                    String gcashAcceptBoth = cursor.getString(15);
                    String terminalAvail = cursor.getString(16);
                    String otherMercVisible = cursor.getString(17);
                    String mayaVisibilityHidden = cursor.getString(18);
                    String mayaVisibilityStandee = cursor.getString(19);
                    String mayaVisibilityDoorHanger = cursor.getString(20);
                    String mayaVisibilityNone = cursor.getString(21);
                    String nonmayaVisibilityStandee = cursor.getString(22);
                    String nonmayaVisibilityDoorHanger = cursor.getString(23);
                    String nonmayaVisibilityNone = cursor.getString(24);
                    String qrGreenBird = cursor.getString(25);
                    String qrMayatwo = cursor.getString(26);
                    String sqrPic = cursor.getString(27);
                    String availableSpic = cursor.getString(28);
                    String mercRestriction = cursor.getString(29);
                    String acceptOtherQR = cursor.getString(30);
                    String mayaDeviceCount = cursor.getString(31);
                    String mayaDeviceSN = cursor.getString(32);
                    String mayaSQRCount = cursor.getString(33);
                    String StoreCode = cursor.getString(34);
                    String pisoTest1 = cursor.getString(35);
                    String pisoTest2 = cursor.getString(36);
                    String transactionid = cursor.getString(37);
                    String completeAddress = cursor.getString(38);
                    String remarks = cursor.getString(39);

                    mainDB.SyncMerchantToLive(id, visitDate, encodedBy, nameOfMall, uploadCounterPic, mayaStatus,
                            dbaName, regBizName, subArea, mercSpocName, mercSpocDesig, mercSpocEmail,
                            mercSpocContact, gcashAcceptStatic, gcashAcceptQRPOS, gcashAcceptBoth, terminalAvail, otherMercVisible,
                            mayaVisibilityHidden, mayaVisibilityStandee, mayaVisibilityDoorHanger, mayaVisibilityNone,
                            nonmayaVisibilityStandee, nonmayaVisibilityDoorHanger, nonmayaVisibilityNone, qrGreenBird, qrMayatwo,
                            sqrPic, availableSpic, mercRestriction, acceptOtherQR, mayaDeviceCount, mayaDeviceSN, mayaSQRCount, StoreCode,
                            pisoTest1, pisoTest2, transactionid, completeAddress, remarks);


                }
            } else {
                GlobalVariables.processListSync = 3;
            }
        } catch (Exception e) {
            Log.d(TAG, "SyncDTRToLive: " + e.toString());

            Toast.makeText(context.getApplicationContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();

        }

    }

    public void AutoSyncMerchantToLive() {

        try {
            String query_getUnsyncMerchant = "SELECT * FROM " + GlobalVariables.MERCHANT_TBL +
                    " where visit_date != '' " +
                    "and sync_status = 0 limit 1";
            SQLiteDatabase db = this.getWritableDatabase();

            Activity activity = (Activity) context;
            WebService.DTRActivityWebService onlineDB = new WebService.DTRActivityWebService(activity);
            WebService.MainActivityWebService mainDB = new WebService.MainActivityWebService(activity);

            cursor = db.rawQuery(query_getUnsyncMerchant, null);

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    String id = String.valueOf(cursor.getInt(0));
                    String visitDate = cursor.getString(1);
                    String encodedBy = cursor.getString(2);
                    String nameOfMall = cursor.getString(3);
                    String uploadCounterPic = cursor.getString(4);
                    String mayaStatus = cursor.getString(5);
                    String dbaName = cursor.getString(6);
                    String regBizName = cursor.getString(7);
                    String subArea = cursor.getString(8);
                    String mercSpocName = cursor.getString(9);
                    String mercSpocDesig = cursor.getString(10);
                    String mercSpocEmail = cursor.getString(11);
                    String mercSpocContact = cursor.getString(12);
                    String gcashAcceptStatic = cursor.getString(13);
                    String gcashAcceptQRPOS = cursor.getString(14);
                    String gcashAcceptBoth = cursor.getString(15);
                    String terminalAvail = cursor.getString(16);
                    String otherMercVisible = cursor.getString(17);
                    String mayaVisibilityHidden = cursor.getString(18);
                    String mayaVisibilityStandee = cursor.getString(19);
                    String mayaVisibilityDoorHanger = cursor.getString(20);
                    String mayaVisibilityNone = cursor.getString(21);
                    String nonmayaVisibilityStandee = cursor.getString(22);
                    String nonmayaVisibilityDoorHanger = cursor.getString(23);
                    String nonmayaVisibilityNone = cursor.getString(24);
                    String qrGreenBird = cursor.getString(25);
                    String qrMayatwo = cursor.getString(26);
                    String sqrPic = cursor.getString(27);
                    String availableSpic = cursor.getString(28);
                    String mercRestriction = cursor.getString(29);
                    String acceptOtherQR = cursor.getString(30);
                    String mayaDeviceCount = cursor.getString(31);
                    String mayaDeviceSN = cursor.getString(32);
                    String mayaSQRCount = cursor.getString(33);
                    String StoreCode = cursor.getString(34);
                    String pisoTest1 = cursor.getString(35);
                    String pisoTest2 = cursor.getString(36);
                    String transactionid = cursor.getString(37);
                    String completeAddress = cursor.getString(38);
                    String remarks = cursor.getString(39);

                    mainDB.AutoSyncMerchantToLive(id, visitDate, encodedBy, nameOfMall, uploadCounterPic, mayaStatus,
                            dbaName, regBizName, subArea, mercSpocName, mercSpocDesig, mercSpocEmail,
                            mercSpocContact, gcashAcceptStatic, gcashAcceptQRPOS, gcashAcceptBoth, terminalAvail, otherMercVisible,
                            mayaVisibilityHidden, mayaVisibilityStandee, mayaVisibilityDoorHanger, mayaVisibilityNone,
                            nonmayaVisibilityStandee, nonmayaVisibilityDoorHanger, nonmayaVisibilityNone, qrGreenBird, qrMayatwo,
                            sqrPic, availableSpic, mercRestriction, acceptOtherQR, mayaDeviceCount, mayaDeviceSN, mayaSQRCount, StoreCode,
                            pisoTest1, pisoTest2, transactionid, completeAddress, remarks);


                }
            } else {
                GlobalVariables.processListSync = 13;
            }
        } catch (Exception e) {
            Log.d(TAG, "SyncDTRToLive: " + e.toString());

        }

    }

    public void SyncMerchantSerialToLive() {

        try {
            String query_getUnsyncMerchant = "SELECT * FROM merchant_serials" +
                    " where transaction_id != '' " +
                    "and sync_status = 0 limit 1";
            SQLiteDatabase db = this.getWritableDatabase();

            Activity activity = (Activity) context;
            WebService.DTRActivityWebService onlineDB = new WebService.DTRActivityWebService(activity);
            WebService.MainActivityWebService mainDB = new WebService.MainActivityWebService(activity);

            cursor = db.rawQuery(query_getUnsyncMerchant, null);

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    String id = String.valueOf(cursor.getInt(0));
                    String transactionid = cursor.getString(1);
                    String sn1 = cursor.getString(2);
                    String snimage1 = cursor.getString(3);
                    String sn2 = cursor.getString(4);
                    String snimage2 = cursor.getString(5);
                    String sn3 = cursor.getString(6);
                    String snimage3 = cursor.getString(7);
                    String sn4 = cursor.getString(8);
                    String snimage4 = cursor.getString(9);
                    String sn5 = cursor.getString(10);
                    String snimage5 = cursor.getString(11);
                    String sn6 = cursor.getString(12);
                    String snimage6 = cursor.getString(13);
                    String sn7 = cursor.getString(14);
                    String snimage7 = cursor.getString(15);
                    String sn8 = cursor.getString(16);
                    String snimage8 = cursor.getString(17);
                    String sn9 = cursor.getString(18);
                    String snimage9 = cursor.getString(19);
                    String sn10 = cursor.getString(20);
                    String snimage10 = cursor.getString(21);
                    String terminaltype1 = cursor.getString(22);
                    String terminaltype2 = cursor.getString(23);
                    String terminaltype3 = cursor.getString(24);
                    String terminaltype4 = cursor.getString(25);
                    String terminaltype5 = cursor.getString(26);
                    String terminaltype6 = cursor.getString(27);
                    String terminaltype7 = cursor.getString(28);
                    String terminaltype8 = cursor.getString(29);
                    String terminaltype9 = cursor.getString(30);
                    String terminaltype10 = cursor.getString(31);
                    String sqrimage1 = cursor.getString(32);
                    String sqrimage2 = cursor.getString(33);
                    String sqrimage3 = cursor.getString(34);
                    String sqrimage4 = cursor.getString(35);
                    String sqrimage5 = cursor.getString(36);
                    String extraimage1 = cursor.getString(37);
                    String extraimage2 = cursor.getString(38);
                    String extraimage3 = cursor.getString(39);
                    String extraimage4 = cursor.getString(40);
                    String extraimage5 = cursor.getString(41);

                    mainDB.SyncMerchantSerialsToLive(id, transactionid, sn1, snimage1, sn2, snimage2,
                            sn3, snimage3, sn4, snimage4, sn5, snimage5,
                            sn6, snimage6, sn7, snimage7, sn8, snimage8,
                            sn9, snimage9, sn10, snimage10, terminaltype1, terminaltype2, terminaltype3,
                            terminaltype4, terminaltype5, terminaltype6, terminaltype7, terminaltype8,
                            terminaltype9, terminaltype10, sqrimage1, sqrimage2, sqrimage3, sqrimage4,
                            sqrimage5, extraimage1, extraimage2, extraimage3, extraimage4, extraimage5);


                }
            } else {

                GlobalVariables.processListSync = 0;
            }
        } catch (Exception e) {
            Log.d(TAG, "SyncDTRToLive: " + e.toString());


        }

    }

    public void SyncMerchantVisit() {

        try {


            String query_getUnsyncMerchant = "SELECT * FROM merchant_visit where sync_status = 0 limit 1";

            SQLiteDatabase db = this.getWritableDatabase();

            Activity activity = (Activity) context;
            WebService.MainActivityWebService mainDB = new WebService.MainActivityWebService(activity);

            cursor = db.rawQuery(query_getUnsyncMerchant, null);

            if (cursor.getCount() != 0) {

                if (cursor.moveToFirst()) {
                        String ID = cursor.getString(0);
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
                        String maya_visiblity_none = cursor.getString(20);
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
                        String transaction_id = cursor.getString(33);
                        String complete_delivery_add = cursor.getString(34);
                        String remarks = cursor.getString(35);
                        String agent_id = cursor.getString(36);
                        String agent_name = cursor.getString(37);
                        String status = cursor.getString(38);
                        String merchant_id = cursor.getString(39);
                        String trade_assurance = cursor.getString(40);
                        String tat_remarks = cursor.getString(41);
                        String sync_status = cursor.getString(42);



                   mainDB.SyncMerchantVisitToLive(visit_date, encoded_by, name_of_mall,
                            maya_status, dba_name, reg_business_name, sub_area, merc_spoc_name, merc_spoc_designation,
                            merc_spoc_email, merc_spoc_contact, gcash_accept_statc, gcash_accept_qrinsidepos, terminal_avail,
                            other_merc_visible, maya_visibility_hidden, maya_visibility_standee, maya_visibility_doorhanger,
                            maya_visiblity_none, nonmaya_visiblity_standee, nonmaya_visiblity_doorhanger, nonmaya_visibility_none,
                            qr_green_bird, qr_mayatwo, available_sqr, merc_restriction, accept_other_qr, maya_device_count,
                            maya_device_sn, maya_sqr_count, store_code, transaction_id, complete_delivery_add, remarks,
                            agent_id, agent_name, status, merchant_id, trade_assurance, tat_remarks, sync_status, ID, gcash_accept_both);








                }
            } else {

                GlobalVariables.processListSync = 0;
            }
        } catch (Exception e) {
            Log.d(TAG, "SyncDTRToLive: " + e.toString());


        }

    }

    public void AutoSyncMerchantSerialToLive() {

        try {
            String query_getUnsyncMerchant = "SELECT * FROM merchant_serials" +
                    " where transaction_id != '' " +
                    "and sync_status = 0 limit 1";
            SQLiteDatabase db = this.getWritableDatabase();

            Activity activity = (Activity) context;
            WebService.DTRActivityWebService onlineDB = new WebService.DTRActivityWebService(activity);
            WebService.MainActivityWebService mainDB = new WebService.MainActivityWebService(activity);

            cursor = db.rawQuery(query_getUnsyncMerchant, null);

            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    String id = String.valueOf(cursor.getInt(0));
                    String transactionid = cursor.getString(1);
                    String sn1 = cursor.getString(2);
                    String snimage1 = cursor.getString(3);
                    String sn2 = cursor.getString(4);
                    String snimage2 = cursor.getString(5);
                    String sn3 = cursor.getString(6);
                    String snimage3 = cursor.getString(7);
                    String sn4 = cursor.getString(8);
                    String snimage4 = cursor.getString(9);
                    String sn5 = cursor.getString(10);
                    String snimage5 = cursor.getString(11);
                    String sn6 = cursor.getString(12);
                    String snimage6 = cursor.getString(13);
                    String sn7 = cursor.getString(14);
                    String snimage7 = cursor.getString(15);
                    String sn8 = cursor.getString(16);
                    String snimage8 = cursor.getString(17);
                    String sn9 = cursor.getString(18);
                    String snimage9 = cursor.getString(19);
                    String sn10 = cursor.getString(20);
                    String snimage10 = cursor.getString(21);
                    String terminaltype1 = cursor.getString(22);
                    String terminaltype2 = cursor.getString(23);
                    String terminaltype3 = cursor.getString(24);
                    String terminaltype4 = cursor.getString(25);
                    String terminaltype5 = cursor.getString(26);
                    String terminaltype6 = cursor.getString(27);
                    String terminaltype7 = cursor.getString(28);
                    String terminaltype8 = cursor.getString(29);
                    String terminaltype9 = cursor.getString(30);
                    String terminaltype10 = cursor.getString(31);
                    String sqrimage1 = cursor.getString(32);
                    String sqrimage2 = cursor.getString(33);
                    String sqrimage3 = cursor.getString(34);
                    String sqrimage4 = cursor.getString(35);
                    String sqrimage5 = cursor.getString(36);
                    String extraimage1 = cursor.getString(37);
                    String extraimage2 = cursor.getString(38);
                    String extraimage3 = cursor.getString(39);
                    String extraimage4 = cursor.getString(40);
                    String extraimage5 = cursor.getString(41);

                    mainDB.AutoSyncMerchantSerialsToLive(id, transactionid, sn1, snimage1, sn2, snimage2,
                            sn3, snimage3, sn4, snimage4, sn5, snimage5,
                            sn6, snimage6, sn7, snimage7, sn8, snimage8,
                            sn9, snimage9, sn10, snimage10, terminaltype1, terminaltype2, terminaltype3,
                            terminaltype4, terminaltype5, terminaltype6, terminaltype7, terminaltype8,
                            terminaltype9, terminaltype10, sqrimage1, sqrimage2, sqrimage3, sqrimage4,
                            sqrimage5, extraimage1, extraimage2, extraimage3, extraimage4, extraimage5);


                }
            } else {

                GlobalVariables.processListSync = 0;
            }
        } catch (Exception e) {
            Log.d(TAG, "SyncDTRToLive: " + e.toString());

        }

    }

    public boolean CheckIfUnsycMerchant() {

        Boolean ifunsyncdata = false;

        String query = "SELECT * from merchant " +
                "where status_sync = 0";

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            cursor = db.rawQuery(query, null);
            {
                if (cursor.getCount() != 0) {
                    return ifunsyncdata = true;
                }
            }
            cursor.close();
            return ifunsyncdata;

        } catch (Exception e) {
            Log.d(TAG, "CheckTimeIn: " + e.toString());
            return ifunsyncdata;
        }
    }

    public boolean CheckIfUnsycDTR() {

        Boolean ifunsyncdtr = false;

        String query = "SELECT * from daily_time_record " +
                "where status_sync = 0";

        try {

            SQLiteDatabase db = this.getWritableDatabase();

            cursor = db.rawQuery(query, null);
            {
                if (cursor.getCount() != 0) {
                    return ifunsyncdtr = true;
                }
            }
            cursor.close();
            return ifunsyncdtr;

        } catch (Exception e) {
            Log.d(TAG, "CheckTimeIn: " + e.toString());
            return ifunsyncdtr;
        }
    }


}
