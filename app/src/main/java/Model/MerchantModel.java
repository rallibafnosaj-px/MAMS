package Model;

public class MerchantModel {


    String visit_date;
    String encoded_by;
    String name_of_mall;
    String maya_status;
    String dba_name;
    String reg_business_name;
    String sub_area;
    String merc_spoc_name;
    String merc_spoc_designation;
    String merc_spoc_email;
    String merc_spoc_contact;
    String gcash_accept_statc;
    String gcash_accept_qrinsidepos;
    String terminal_avail;
    String other_merc_visible;
    String maya_visibility_hidden;
    String maya_visibility_standee;
    String maya_visibility_doorhanger;
    String maya_visiblity_none;
    String nonmaya_visiblity_standee;
    String nonmaya_visiblity_doorhanger;
    String nonmaya_visibility_none;
    String qr_green_bird;
    String qr_mayatwo;
    String available_sqr;
    String merc_restriction;
    String accept_other_qr;
    String maya_device_count;
    String maya_device_sn;
    String maya_sqr_count;
    String store_code;
    String transaction_id;
    String complete_delivery_add;
    String remarks;
    String agent_id;
    String agent_name;
    String status;
    String merchant_id;
    String trade_assurance;
    String tat_remarks;
    String sync_status;


    String gcash_accept_both;

    String ID;




    public MerchantModel(String visit_date, String encoded_by, String name_of_mall, String maya_status, String dba_name, String reg_business_name,
                         String sub_area, String merc_spoc_name, String merc_spoc_designation, String merc_spoc_email, String merc_spoc_contact, String gcash_accept_statc,
                         String gcash_accept_qrinsidepos, String terminal_avail, String other_merc_visible, String maya_visibility_hidden,
                         String maya_visibility_standee, String maya_visibility_doorhanger, String maya_visiblity_none, String nonmaya_visiblity_standee,
                         String nonmaya_visiblity_doorhanger, String nonmaya_visibility_none, String qr_green_bird, String qr_mayatwo, String available_sqr,
                         String merc_restriction, String accept_other_qr, String maya_device_count, String maya_device_sn, String maya_sqr_count, String store_code, String transaction_id,
                         String complete_delivery_add, String remarks, String agent_id, String agent_name, String status, String merchant_id, String trade_assurance, String tat_remarks,
                         String sync_status, String ID, String gcash_accept_both) {

        this.visit_date = visit_date;
        this.encoded_by = encoded_by;
        this.name_of_mall = name_of_mall;
        this.maya_status = maya_status;
        this.dba_name = dba_name;
        this.gcash_accept_both = gcash_accept_both;
        this.reg_business_name = reg_business_name;
        this.sub_area = sub_area;
        this.merc_spoc_name = merc_spoc_name;
        this.merc_spoc_designation = merc_spoc_designation;
        this.merc_spoc_email = merc_spoc_email;
        this.merc_spoc_contact = merc_spoc_contact;
        this.gcash_accept_statc = gcash_accept_statc;
        this.gcash_accept_qrinsidepos = gcash_accept_qrinsidepos;
        this.terminal_avail = terminal_avail;
        this.other_merc_visible = other_merc_visible;
        this.maya_visibility_hidden = maya_visibility_hidden;
        this.maya_visibility_standee = maya_visibility_standee;
        this.maya_visibility_doorhanger = maya_visibility_doorhanger;
        this.maya_visiblity_none = maya_visiblity_none;
        this.nonmaya_visiblity_standee = nonmaya_visiblity_standee;
        this.nonmaya_visiblity_doorhanger = nonmaya_visiblity_doorhanger;
        this.nonmaya_visibility_none = nonmaya_visibility_none;
        this.qr_green_bird = qr_green_bird;
        this.qr_mayatwo = qr_mayatwo;
        this.available_sqr = available_sqr;
        this.merc_restriction = merc_restriction;
        this.accept_other_qr = accept_other_qr;
        this.maya_device_count = maya_device_count;
        this.maya_device_sn = maya_device_sn;
        this.maya_sqr_count = maya_sqr_count;
        this.store_code = store_code;
        this.transaction_id = transaction_id;
        this.complete_delivery_add = complete_delivery_add;
        this.remarks = remarks;
        this.agent_id = agent_id;
        this.agent_name = agent_name;
        this.status = status;
        this.merchant_id = merchant_id;
        this.trade_assurance = trade_assurance;
        this.tat_remarks = tat_remarks;
        this.sync_status = sync_status;
        this.ID = ID;
    }


    public String getGcash_accept_both() {
        return gcash_accept_both;
    }

    public void setGcash_accept_both(String gcash_accept_both) {
        this.gcash_accept_both = gcash_accept_both;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getEncoded_by() {
        return encoded_by;
    }

    public void setEncoded_by(String encoded_by) {
        this.encoded_by = encoded_by;
    }

    public String getName_of_mall() {
        return name_of_mall;
    }

    public void setName_of_mall(String name_of_mall) {
        this.name_of_mall = name_of_mall;
    }

    public String getMaya_status() {
        return maya_status;
    }

    public void setMaya_status(String maya_status) {
        this.maya_status = maya_status;
    }

    public String getDba_name() {
        return dba_name;
    }

    public void setDba_name(String dba_name) {
        this.dba_name = dba_name;
    }

    public String getReg_business_name() {
        return reg_business_name;
    }

    public void setReg_business_name(String reg_business_name) {
        this.reg_business_name = reg_business_name;
    }

    public String getSub_area() {
        return sub_area;
    }

    public void setSub_area(String sub_area) {
        this.sub_area = sub_area;
    }

    public String getMerc_spoc_name() {
        return merc_spoc_name;
    }

    public void setMerc_spoc_name(String merc_spoc_name) {
        this.merc_spoc_name = merc_spoc_name;
    }

    public String getMerc_spoc_designation() {
        return merc_spoc_designation;
    }

    public void setMerc_spoc_designation(String merc_spoc_designation) {
        this.merc_spoc_designation = merc_spoc_designation;
    }

    public String getMerc_spoc_email() {
        return merc_spoc_email;
    }

    public void setMerc_spoc_email(String merc_spoc_email) {
        this.merc_spoc_email = merc_spoc_email;
    }

    public String getMerc_spoc_contact() {
        return merc_spoc_contact;
    }

    public void setMerc_spoc_contact(String merc_spoc_contact) {
        this.merc_spoc_contact = merc_spoc_contact;
    }

    public String getGcash_accept_statc() {
        return gcash_accept_statc;
    }

    public void setGcash_accept_statc(String gcash_accept_statc) {
        this.gcash_accept_statc = gcash_accept_statc;
    }

    public String getGcash_accept_qrinsidepos() {
        return gcash_accept_qrinsidepos;
    }

    public void setGcash_accept_qrinsidepos(String gcash_accept_qrinsidepos) {
        this.gcash_accept_qrinsidepos = gcash_accept_qrinsidepos;
    }

    public String getTerminal_avail() {
        return terminal_avail;
    }

    public void setTerminal_avail(String terminal_avail) {
        this.terminal_avail = terminal_avail;
    }

    public String getOther_merc_visible() {
        return other_merc_visible;
    }

    public void setOther_merc_visible(String other_merc_visible) {
        this.other_merc_visible = other_merc_visible;
    }

    public String getMaya_visibility_hidden() {
        return maya_visibility_hidden;
    }

    public void setMaya_visibility_hidden(String maya_visibility_hidden) {
        this.maya_visibility_hidden = maya_visibility_hidden;
    }

    public String getMaya_visibility_standee() {
        return maya_visibility_standee;
    }

    public void setMaya_visibility_standee(String maya_visibility_standee) {
        this.maya_visibility_standee = maya_visibility_standee;
    }

    public String getMaya_visibility_doorhanger() {
        return maya_visibility_doorhanger;
    }

    public void setMaya_visibility_doorhanger(String maya_visibility_doorhanger) {
        this.maya_visibility_doorhanger = maya_visibility_doorhanger;
    }

    public String getMaya_visiblity_none() {
        return maya_visiblity_none;
    }

    public void setMaya_visiblity_none(String maya_visiblity_none) {
        this.maya_visiblity_none = maya_visiblity_none;
    }

    public String getNonmaya_visiblity_standee() {
        return nonmaya_visiblity_standee;
    }

    public void setNonmaya_visiblity_standee(String nonmaya_visiblity_standee) {
        this.nonmaya_visiblity_standee = nonmaya_visiblity_standee;
    }

    public String getNonmaya_visiblity_doorhanger() {
        return nonmaya_visiblity_doorhanger;
    }

    public void setNonmaya_visiblity_doorhanger(String nonmaya_visiblity_doorhanger) {
        this.nonmaya_visiblity_doorhanger = nonmaya_visiblity_doorhanger;
    }

    public String getNonmaya_visibility_none() {
        return nonmaya_visibility_none;
    }

    public void setNonmaya_visibility_none(String nonmaya_visibility_none) {
        this.nonmaya_visibility_none = nonmaya_visibility_none;
    }

    public String getQr_green_bird() {
        return qr_green_bird;
    }

    public void setQr_green_bird(String qr_green_bird) {
        this.qr_green_bird = qr_green_bird;
    }

    public String getQr_mayatwo() {
        return qr_mayatwo;
    }

    public void setQr_mayatwo(String qr_mayatwo) {
        this.qr_mayatwo = qr_mayatwo;
    }

    public String getAvailable_sqr() {
        return available_sqr;
    }

    public void setAvailable_sqr(String available_sqr) {
        this.available_sqr = available_sqr;
    }

    public String getMerc_restriction() {
        return merc_restriction;
    }

    public void setMerc_restriction(String merc_restriction) {
        this.merc_restriction = merc_restriction;
    }

    public String getAccept_other_qr() {
        return accept_other_qr;
    }

    public void setAccept_other_qr(String accept_other_qr) {
        this.accept_other_qr = accept_other_qr;
    }

    public String getMaya_device_count() {
        return maya_device_count;
    }

    public void setMaya_device_count(String maya_device_count) {
        this.maya_device_count = maya_device_count;
    }

    public String getMaya_device_sn() {
        return maya_device_sn;
    }

    public void setMaya_device_sn(String maya_device_sn) {
        this.maya_device_sn = maya_device_sn;
    }

    public String getMaya_sqr_count() {
        return maya_sqr_count;
    }

    public void setMaya_sqr_count(String maya_sqr_count) {
        this.maya_sqr_count = maya_sqr_count;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getComplete_delivery_add() {
        return complete_delivery_add;
    }

    public void setComplete_delivery_add(String complete_delivery_add) {
        this.complete_delivery_add = complete_delivery_add;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getTrade_assurance() {
        return trade_assurance;
    }

    public void setTrade_assurance(String trade_assurance) {
        this.trade_assurance = trade_assurance;
    }

    public String getTat_remarks() {
        return tat_remarks;
    }

    public void setTat_remarks(String tat_remarks) {
        this.tat_remarks = tat_remarks;
    }

    public String getSync_status() {
        return sync_status;
    }

    public void setSync_status(String sync_status) {
        this.sync_status = sync_status;
    }






}
