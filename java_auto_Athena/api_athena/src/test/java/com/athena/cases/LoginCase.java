package com.athena.cases;
import com.athena.pojo.CaseInfo;
import com.athena.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * @Project:api_athena
 * @Date: 2022/3/17 22:23
 * @Author:Athena
 * 注册类
 */
public class LoginCase extends BaseCase{
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:31
    * @ Description //登录接口
    * @ Param
    * @ return
    */
    @Test(dataProvider = "datas")
    public void  loginCase(CaseInfo caseInfo){
        //  1、参数化替换
        paramsReplace(caseInfo);
        //	2、数据库前置查询结果(数据断言必须在接口执行前后都查询)
        //	3、调用接口:发起请求
        String body = HttpUtils.call(caseInfo,  HttpUtils.getDefaulHeaders());
        //响应体重获取的token存储到map
        responseUserData(body,"$.data.token","token");
        //	4、json响应结果断言  (设置多字段的断言)
        boolean resassertResult = responseAssert(caseInfo,body);
        //	5、添加接口响应回写内容:回写=excel修改操作
        addresDataList(caseInfo.getCaseId(),Constans.RESPONSE_CELLNUM,startSheetIndex,body);
        //	6、数据库后置查询结果
        //Object afterSqlResult = SqlUtils.getSingleResult(); 暂时省略,待云服务器部署后再补全断言
        //	7、据库断言
        boolean sqlResult = true;
        //	8、添加断言回写内容
        String assertResult =resassertResult && sqlResult ? "success":"failed";
        addresDataList(caseInfo.getCaseId(),Constans.RESPONSE_SQL,startSheetIndex,assertResult);
        //	9、添加日志

        //	10、报表断言
    }
    @DataProvider
    public Object[]datas() throws Exception {
        return ExceUtils.read(startSheetIndex,sheetNum);
    }
}

