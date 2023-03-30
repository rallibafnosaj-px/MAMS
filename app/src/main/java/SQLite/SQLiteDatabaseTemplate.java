package SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.survey.GlobalVariables;

public class SQLiteDatabaseTemplate extends SQLiteOpenHelper {

    String query;
    Cursor cursor;
    Cursor cursor1;

    public SQLiteDatabaseTemplate(@Nullable Context context) {
        super(context, GlobalVariables.DB_NAME, null, 1);

    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        AddTables(db);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void AddTables(android.database.sqlite.SQLiteDatabase db){

        String tbl_login = "CREATE TABLE IF NOT EXISTS " + GlobalVariables.LOGINVERIFY_TBL +
                " (login_verify INTEGER)";

        String tbl_merchant_verify = "CREATE TABLE IF NOT EXISTS " + GlobalVariables.MERCHANTVERIFY_TBL +
                " (merchant_verify INTEGER)";

        String tbl_employee = "CREATE TABLE IF NOT EXISTS " + GlobalVariables.EMPLOYEE_TBL +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "           +
                "agent_id VARCHAR(50), "                      +
                "agent_name VARCHAR(50),"                             +
                "contact_no VARCHAR(50), "                           +
                "email VARCHAR(50), "                          +
                "password VARCHAR(50))";

        String tbl_dtr = "CREATE TABLE IF NOT EXISTS " + GlobalVariables.DTR_TBL +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "            +
                "employee_number VARCHAR(50), "                      +
                "datex VARCHAR(50),"                                +
                "dtr_in VARCHAR(50) DEFAULT '',"                                +
                "dtr_out VARCHAR(50) DEFAULT '', "                              +
                "lat_in VARCHAR(250) DEFAULT '', "                              +
                "lat_out VARCHAR(250) DEFAULT '', "                             +
                "long_in VARCHAR(250) DEFAULT '', "                             +
                "long_out VARCHAR(250) DEFAULT '', "                            +
                "address_in VARCHAR(250) DEFAULT '', "                          +
                "address_out VARCHAR(250) DEFAULT '', "                         +
                "image_in BLOB DEFAULT '',"                                     +
                "image_out BLOB DEFAULT '', "                                   +
                "is_active VARCHAR(10), "                            +
                "status_sync VARCHAR(10))";

        String tbl_merchant = "CREATE TABLE IF NOT EXISTS " + GlobalVariables.MERCHANT_TBL +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "            +
                "visit_date VARCHAR(50), "                      +
                "encoded_by VARCHAR(50),"                                +
                "name_of_mall VARCHAR(200), "                              +
                "upload_counter_pic BLOB, "                              +
                "maya_status VARCHAR(250), "                             +
                "dba_name VARCHAR(250), "                             +
                "reg_business_name VARCHAR(250), "                            +
                "sub_area VARCHAR(250), "                          +
                "merc_spoc_name VARCHAR(250), "                         +
                "merc_spoc_designation VARCHAR(100), "                         +
                "merc_spoc_email VARCHAR(100), "                         +
                "merc_spoc_contact VARCHAR(30), "                         +
                "gcash_accept_static int, "                         +
                "gcash_accept_qrinsidepos int, "                         +
                "gcash_accept_both int, "                         +
                "terminal_avail VARCHAR(500), "                         +
                "other_merc_visible VARCHAR(500), "                         +
                "maya_visibility_hidden int, "                         +
                "maya_visibility_standee int, "                         +
                "maya_visibility_doorhanger int, "                         +
                "maya_visibility_none int, "                         +
                "nonmaya_visibility_standee int, "                         +
                "nonmaya_visibility_doorhanger int, "                         +
                "nonmaya_visibility_none int, "                         +
                "qr_green_bird int, "                         +
                "qr_mayatwo int, "                         +
                "upload_sqr_pic BLOB,"                                     +
                "available_sqr VARCHAR(500), "                                   +
                "merc_restriction VARCHAR(300), "                                   +
                "accept_other_qr int, "                         +
                "maya_device_count VARCHAR(10), "                            +
                "maya_device_sn VARCHAR(10), "                            +
                "maya_sqr_count VARCHAR(10), "                            +
                "store_code VARCHAR(1000), "                            +
                "piso_test1 VARCHAR(1000) DEFAULT '', "                            +
                "piso_test2 VARCHAR(1000) DEFAULT '', "                            +
                "transaction_id VARCHAR(100), "                            +
                "complete_delivery_add VARCHAR(300), "                            +
                "remarks VARCHAR(100), "                            +
                "sync_status int)";

        String tbl_merchantVisit = "CREATE TABLE IF NOT EXISTS " + GlobalVariables.MERCHANTVISIT_TBL +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "            +
                "visit_date VARCHAR(50), "                      +
                "encoded_by VARCHAR(50),"                                +
                "name_of_mall VARCHAR(200), "                              +
                "maya_status VARCHAR(250), "                             +
                "dba_name VARCHAR(250), "                             +
                "reg_business_name VARCHAR(250), "                            +
                "sub_area VARCHAR(250), "                          +
                "merc_spoc_name VARCHAR(250), "                         +
                "merc_spoc_designation VARCHAR(100), "                         +
                "merc_spoc_email VARCHAR(100), "                         +
                "merc_spoc_contact VARCHAR(30), "                         +
                "gcash_accept_static int, "                         +
                "gcash_accept_qrinsidepos int, "                         +
                "gcash_accept_both int, "                         +
                "terminal_avail VARCHAR(500), "                         +
                "other_merc_visible VARCHAR(500), "                         +
                "maya_visibility_hidden int, "                         +
                "maya_visibility_standee int, "                         +
                "maya_visibility_doorhanger int, "                         +
                "maya_visibility_none int, "                         +
                "nonmaya_visibility_standee int, "                         +
                "nonmaya_visibility_doorhanger int, "                         +
                "nonmaya_visibility_none int, "                         +
                "qr_green_bird int, "                         +
                "qr_mayatwo int, "                         +
                "available_sqr VARCHAR(500), "                                   +
                "merc_restriction VARCHAR(300), "                                   +
                "accept_other_qr int, "                         +
                "maya_device_count VARCHAR(10), "                            +
                "maya_device_sn VARCHAR(10), "                            +
                "maya_sqr_count VARCHAR(10), "                            +
                "store_code VARCHAR(1000), "                            +
                "transaction_id VARCHAR(100), "                            +
                "complete_delivery_add VARCHAR(300), "                            +
                "remarks VARCHAR(100), "                            +
                "agent_id VARCHAR(100), "                            +
                "agent_name VARCHAR(100), "                            +
                "status VARCHAR(100), "                            +
                "merchant_id VARCHAR(100), "                            +
                "trade_assurance VARCHAR(200), "                            +
                "tat_remarks VARCHAR(1000), "                            +
                "sync_status int)";

        String tbl_counter = "CREATE TABLE IF NOT EXISTS " + GlobalVariables.DTR_COUNTER_TBL +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "           +
                "Counter VARCHAR(50))";

        String tbl_pin = "CREATE TABLE IF NOT EXISTS pin" +
                " (pin varchar(4))";

        String tbl_maya_status = "CREATE TABLE IF NOT EXISTS maya_status" +
                " (status varchar(50))";

        String tbl_terminals = "CREATE TABLE IF NOT EXISTS terminals" +
                " (terminals varchar(50))";

        String tbl_merchantserials = "CREATE TABLE IF NOT EXISTS merchant_serials" +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "           +
                "transaction_id VARCHAR(50), "                       +
                "sn1 VARCHAR(50),"                                   +
                "sn_image1 BLOB DEFAULT '',"                                    +
                "sn2 VARCHAR(50),"                                   +
                "sn_image2 BLOB DEFAULT '',"                                    +
                "sn3 VARCHAR(50),"                                   +
                "sn_image3 BLOB DEFAULT '',"                                    +
                "sn4 VARCHAR(50),"                                   +
                "sn_image4 BLOB DEFAULT '',"                                    +
                "sn5 VARCHAR(50),"                                   +
                "sn_image5 BLOB DEFAULT '',"                                    +
                "sn6 VARCHAR(50),"                                   +
                "sn_image6 BLOB DEFAULT '',"                                    +
                "sn7 VARCHAR(50),"                                   +
                "sn_image7 BLOB DEFAULT '',"                                    +
                "sn8 VARCHAR(50),"                                   +
                "sn_image8 BLOB DEFAULT '',"                                    +
                "sn9 VARCHAR(50),"                                   +
                "sn_image9 BLOB DEFAULT '',"                                    +
                "sn10 VARCHAR(50),"                                  +
                "sn_image10 BLOB DEFAULT '',"                                   +
                "terminal_type1 VARCHAR(50),"                                   +
                "terminal_type2 VARCHAR(50),"                                   +
                "terminal_type3 VARCHAR(50),"                                   +
                "terminal_type4 VARCHAR(50),"                                   +
                "terminal_type5 VARCHAR(50),"                                   +
                "terminal_type6 VARCHAR(50),"                                   +
                "terminal_type7 VARCHAR(50),"                                   +
                "terminal_type8 VARCHAR(50),"                                   +
                "terminal_type9 VARCHAR(50),"                                   +
                "terminal_type10 VARCHAR(50),"                                  +
                "sqr_image1 BLOB DEFAULT '',"                                   +
                "sqr_image2 BLOB DEFAULT '',"                                   +
                "sqr_image3 BLOB DEFAULT '',"                                   +
                "sqr_image4 BLOB DEFAULT '',"                                   +
                "sqr_image5 BLOB DEFAULT '',"                                   +
                "extra_image1 BLOB DEFAULT '',"                                   +
                "extra_image2 BLOB DEFAULT '',"                                   +
                "extra_image3 BLOB DEFAULT '',"                                   +
                "extra_image4 BLOB DEFAULT '',"                                   +
                "extra_image5 BLOB DEFAULT '',"                                   +
                "sync_status int)";

        db.execSQL(tbl_login);
        db.execSQL(tbl_employee);
        db.execSQL(tbl_dtr);
        db.execSQL(tbl_counter);
        db.execSQL(tbl_pin);
        db.execSQL(tbl_maya_status);
        db.execSQL(tbl_terminals);
        db.execSQL(tbl_merchant);
        db.execSQL(tbl_merchant_verify);
        db.execSQL(tbl_merchantserials);
        db.execSQL(tbl_merchantVisit);

    }


}
