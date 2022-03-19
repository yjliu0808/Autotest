package com.athena.cases;
import com.alibaba.fastjson.JSONPath;
import com.athena.pojo.CaseInfo;
import com.athena.utils.ExceUtils;
import com.athena.utils.HttpUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project:api_athena
 * @Date: 2022/3/17 22:23
 * @Author:Athena
 * 注册类
 */
public class LoginActivitiCase extends BaseCase{
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:31
    * @ Description //登录接口
    * @ Param
    * @ return
    */

    @Test(dataProvider = "datas")
    public void  RegisterTest(CaseInfo caseInfo){
        //发起请求
        String body = HttpUtils.call(caseInfo,HttpUtils.getDefaulHeaders());

    }
    @DataProvider
    public Object[]datas() throws Exception {
        return ExceUtils.read(startSheetIndex,sheetNum);
    }
}


