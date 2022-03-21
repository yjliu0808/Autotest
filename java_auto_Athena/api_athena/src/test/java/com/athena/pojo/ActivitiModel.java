package com.athena.pojo;

/**
 * @Project:api_athena
 * @Date: 2022/3/21 15:08
 * @Author:Athena
 */
public class ActivitiModel {
    private String ID_;
    private int REV_;
    private String NAME_;
    private String KEY_;
    private String IDCATEGORY__;
    private String CREATE_TIME_;
    private String LAST_UPDATE_TIME_;
    private int VERSION_;
    private String META_INFO_;
    private String DEPLOYMENT_ID_;
    private String EDITOR_SOURCE_VALUE_ID_;
    private String EDITOR_SOURCE_EXTRA_VALUE_ID_;
    private String TENANT_ID_;

    public ActivitiModel() {
    }

    public ActivitiModel(String ID_, int REV_, String NAME_, String KEY_, String IDCATEGORY__, String CREATE_TIME_, String LAST_UPDATE_TIME_, int VERSION_, String META_INFO_, String DEPLOYMENT_ID_, String EDITOR_SOURCE_VALUE_ID_, String EDITOR_SOURCE_EXTRA_VALUE_ID_, String TENANT_ID_) {
        this.ID_ = ID_;
        this.REV_ = REV_;
        this.NAME_ = NAME_;
        this.KEY_ = KEY_;
        this.IDCATEGORY__ = IDCATEGORY__;
        this.CREATE_TIME_ = CREATE_TIME_;
        this.LAST_UPDATE_TIME_ = LAST_UPDATE_TIME_;
        this.VERSION_ = VERSION_;
        this.META_INFO_ = META_INFO_;
        this.DEPLOYMENT_ID_ = DEPLOYMENT_ID_;
        this.EDITOR_SOURCE_VALUE_ID_ = EDITOR_SOURCE_VALUE_ID_;
        this.EDITOR_SOURCE_EXTRA_VALUE_ID_ = EDITOR_SOURCE_EXTRA_VALUE_ID_;
        this.TENANT_ID_ = TENANT_ID_;
    }

    @Override
    public String toString() {
        return "ActivitiModel{" +
                "ID_='" + ID_ + '\'' +
                ", REV_=" + REV_ +
                ", NAME_='" + NAME_ + '\'' +
                ", KEY_='" + KEY_ + '\'' +
                ", IDCATEGORY__='" + IDCATEGORY__ + '\'' +
                ", CREATE_TIME_='" + CREATE_TIME_ + '\'' +
                ", LAST_UPDATE_TIME_='" + LAST_UPDATE_TIME_ + '\'' +
                ", VERSION_=" + VERSION_ +
                ", META_INFO_='" + META_INFO_ + '\'' +
                ", DEPLOYMENT_ID_='" + DEPLOYMENT_ID_ + '\'' +
                ", EDITOR_SOURCE_VALUE_ID_='" + EDITOR_SOURCE_VALUE_ID_ + '\'' +
                ", EDITOR_SOURCE_EXTRA_VALUE_ID_='" + EDITOR_SOURCE_EXTRA_VALUE_ID_ + '\'' +
                ", TENANT_ID_='" + TENANT_ID_ + '\'' +
                '}';
    }

    public String getID_() {
        return ID_;
    }

    public void setID_(String ID_) {
        this.ID_ = ID_;
    }

    public int getREV_() {
        return REV_;
    }

    public void setREV_(int REV_) {
        this.REV_ = REV_;
    }

    public String getNAME_() {
        return NAME_;
    }

    public void setNAME_(String NAME_) {
        this.NAME_ = NAME_;
    }

    public String getKEY_() {
        return KEY_;
    }

    public void setKEY_(String KEY_) {
        this.KEY_ = KEY_;
    }

    public String getIDCATEGORY__() {
        return IDCATEGORY__;
    }

    public void setIDCATEGORY__(String IDCATEGORY__) {
        this.IDCATEGORY__ = IDCATEGORY__;
    }

    public String getCREATE_TIME_() {
        return CREATE_TIME_;
    }

    public void setCREATE_TIME_(String CREATE_TIME_) {
        this.CREATE_TIME_ = CREATE_TIME_;
    }

    public String getLAST_UPDATE_TIME_() {
        return LAST_UPDATE_TIME_;
    }

    public void setLAST_UPDATE_TIME_(String LAST_UPDATE_TIME_) {
        this.LAST_UPDATE_TIME_ = LAST_UPDATE_TIME_;
    }

    public int getVERSION_() {
        return VERSION_;
    }

    public void setVERSION_(int VERSION_) {
        this.VERSION_ = VERSION_;
    }

    public String getMETA_INFO_() {
        return META_INFO_;
    }

    public void setMETA_INFO_(String META_INFO_) {
        this.META_INFO_ = META_INFO_;
    }

    public String getDEPLOYMENT_ID_() {
        return DEPLOYMENT_ID_;
    }

    public void setDEPLOYMENT_ID_(String DEPLOYMENT_ID_) {
        this.DEPLOYMENT_ID_ = DEPLOYMENT_ID_;
    }

    public String getEDITOR_SOURCE_VALUE_ID_() {
        return EDITOR_SOURCE_VALUE_ID_;
    }

    public void setEDITOR_SOURCE_VALUE_ID_(String EDITOR_SOURCE_VALUE_ID_) {
        this.EDITOR_SOURCE_VALUE_ID_ = EDITOR_SOURCE_VALUE_ID_;
    }

    public String getEDITOR_SOURCE_EXTRA_VALUE_ID_() {
        return EDITOR_SOURCE_EXTRA_VALUE_ID_;
    }

    public void setEDITOR_SOURCE_EXTRA_VALUE_ID_(String EDITOR_SOURCE_EXTRA_VALUE_ID_) {
        this.EDITOR_SOURCE_EXTRA_VALUE_ID_ = EDITOR_SOURCE_EXTRA_VALUE_ID_;
    }

    public String getTENANT_ID_() {
        return TENANT_ID_;
    }

    public void setTENANT_ID_(String TENANT_ID_) {
        this.TENANT_ID_ = TENANT_ID_;
    }
}
