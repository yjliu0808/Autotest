package com.athena.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.testng.annotations.Test;

/**
 * @Project:api_athena
 * @Date: 2022/3/17 23:30
 * @Author:Athena
 * EXCEL-映射实体类
 */
public class CaseInfo {
 //  Name接口名称  CaseId用例ID	Desc用例描述	Url接口地址	ContentType格式	Type接口提交类型	Params参数
    @Excel(name="Name接口名称")
    private String name;//属性的命名遵循小驼峰

    @Excel(name="CaseId用例ID")
    private  int caseId;

    @Excel(name="Desc用例描述")
    private String desc;

    @Excel(name="Url接口地址")
    private String url;

    @Excel(name="ContentType格式")
    private String contentType;

    @Excel(name="Type接口提交类型")
    private String type;

    @Excel(name="Params参数")
    private String params;

    @Override
    public String toString() {
        return "CaseInfo{" +
                "name='" + name + '\'' +
                ", caseId=" + caseId +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", contentType='" + contentType + '\'' +
                ", type='" + type + '\'' +
                ", params='" + params + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}