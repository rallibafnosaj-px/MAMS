package SQLite;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.survey.GlobalVariables;
import com.example.survey.Itinerary;

import java.util.ArrayList;
import java.util.List;

import Adapter.MerchantAdapter;
import Adapter.UnsyncDTRAdapter;
import Model.DTRModel;
import Model.MerchantModel;

import static android.content.ContentValues.TAG;

public class DashboardActivitySQLite extends SQLiteDatabaseTemplate{
    String query;
    Context context;

    List<Model.DTRModel> dtrList;
    List<Model.MerchantModel> merchantModelList;

    UnsyncDTRAdapter dtrAdapter;
    MerchantAdapter mercAdapter;


    public DashboardActivitySQLite(@Nullable Context context) {
        super(context);
        this.context = context;

    }

//    public String getPinCode() {
//
//        SQLiteDatabase db = getWritableDatabase();
//
//        try {
//            String query_getDrDetails = "Select * from pin";
//
//            cursor = db.rawQuery(query_getDrDetails, new String[]{});
//
//            if (cursor.moveToFirst()) {
//
//                String id = String.valueOf(cursor.getInt(0));
//                String visitDate = cursor.getString(1);
//                String encodedBy = cursor.getString(2);
//                String nameOfMall = cursor.getString(3);
//                String uploadCounterPic = cursor.getString(4);
//                String mayaStatus = cursor.getString(5);
//                String dbaName = cursor.getString(6);
//                String regBizName = cursor.getString(7);
//                String subArea = cursor.getString(8);
//                String mercSpocName = cursor.getString(9);
//                String mercSpocDesig = cursor.getString(10);
//                String mercSpocEmail = cursor.getString(11);
//                String mercSpocContact = cursor.getString(12);
//                String gcashAcceptStatic = cursor.getString(13);
//                String gcashAcceptQRPOS= cursor.getString(14);
//                String gcashAcceptBoth = cursor.getString(15);
//                String terminalAvail = cursor.getString(16);
//                String otherMercVisible = cursor.getString(17);
//                String mayaVisibilityHidden = cursor.getString(18);
//                String mayaVisibilityStandee = cursor.getString(19);
//                String mayaVisibilityDoorHanger = cursor.getString(20);
//                String  mayaVisibilityNone = cursor.getString(21);
//                String  nonmayaVisibilityStandee= cursor.getString(22);
//                String nonmayaVisibilityDoorHanger = cursor.getString(23);
//                String nonmayaVisibilityNone = cursor.getString(24);
//                String qrGreenBird = cursor.getString(25);
//                String qrMayatwo = cursor.getString(26);
//                String  sqrPic = cursor.getString(27);
//                String availableSpic = cursor.getString(28);
//                String  mercRestriction= cursor.getString(29);
//                String  acceptOtherQR= cursor.getString(30);
//                String  mayaDeviceCount= cursor.getString(31);
//                String  mayaDeviceSN= cursor.getString(32);
//                String  mayaSQRCount= cursor.getString(33);
//                String  StoreCode = cursor.getString(34);
//                String  pisoTest1 = cursor.getString(35);
//                String  pisoTest2 = cursor.getString(36);
//                String  transactionid = cursor.getString(37);
//                String  completeAddress = cursor.getString(38);
//                String  remarks = cursor.getString(39);
//
//
//                return id, visitDate, encodedBy, nameOfMall, uploadCounterPic, mayaStatus, dbaName, regBizName, subArea,
//                        mercSpocName, mercSpocDesig, mercSpocEmail, mercSpocContact, gcashAcceptStatic, gcashAcceptQRPOS,
//                        gcashAcceptBoth,terminalAvail, otherMercVisible, mayaVisibilityHidden, mayaVisibilityStandee, mayaVisibilityDoorHanger,
//                mayaVisibilityNone, nonmayaVisibilityStandee, nonmayaVisibilityDoorHanger, nonmayaVisibilityNone, qrGreenBird,
//                        qrMayatwo, sqrPic, availableSpic, mercRestriction, acceptOtherQR, mayaDeviceCount, mayaDeviceSN, mayaSQRCount,
//                        StoreCode, pisoTest1, pisoTest2, transactionid, completeAddress, remarks;
//            }
//
//            cursor.close();
//        } catch (Exception e) {
//
//        }
//        return "empty";
//    }

    public UnsyncDTRAdapter RetrieveUnsyncDTR() {

        SQLiteDatabase db = getWritableDatabase();

        dtrList = new ArrayList<>();
        try {
            query = "Select datex, dtr_in, dtr_out  from " + GlobalVariables.DTR_TBL + "";


            cursor = db.rawQuery(query, null);


            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {

                    do {

                        String timein = cursor.getString(1);
                        String date = cursor.getString(0);
                        String timeout = cursor.getString(2);

                        Model.DTRModel dtrModel = new Model.DTRModel(date, timein, timeout);
                        dtrList.add(dtrModel);


                    } while (cursor.moveToNext());

                    dtrAdapter = new UnsyncDTRAdapter(context, dtrList);

                    return dtrAdapter;

                }
            } else {

            }
        } catch (Exception e) {

        }
        cursor.close();
        return dtrAdapter;
    }

    public MerchantAdapter RetrieveUnsyncMerchant() {

        SQLiteDatabase db = getWritableDatabase();

        merchantModelList = new ArrayList<>();
        try {
            query = "Select * from " + "merchant_visit";


            cursor = db.rawQuery(query, null);


            if (cursor.getCount() != 0) {
                if (cursor.moveToFirst()) {

                    do {
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
                        merchantModelList.add(mercModel);


                    } while (cursor.moveToNext());

                    mercAdapter = new MerchantAdapter(context, merchantModelList);

                    return mercAdapter;

                }
            } else {

            }
        } catch (Exception e) {
            Log.d(TAG, "RetrieveUnsyncMerchant: " + e.toString());
        }
        cursor.close();
        return mercAdapter;
    }

    public Cursor readMerchantVisitData(){
        String query = "Select * from merchant_visit";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
