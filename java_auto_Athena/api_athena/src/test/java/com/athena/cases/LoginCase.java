package com.athena.cases;
import com.alibaba.fastjson.JSONPath;
import com.athena.pojo.CaseInfo;
import com.athena.pojo.WriteBackData;
import com.athena.utils.Constans;
import com.athena.utils.ExceUtils;
import com.athena.utils.HttpUtils;
import com.athena.utils.UserData;
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
public class LoginCase extends BaseCase{
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:31
    * @ Description //登录接口
    * @ Param
    * @ return
    */

    @Test(dataProvider = "datas")
    public void  RegisterTest(CaseInfo caseInfo){
        //  1、参数化替换
        //	2、数据库前置查询结果(数据断言必须在接口执行前后都查询)
        //	3、调用接口:发起请求
        String body = HttpUtils.call(caseInfo,  HttpUtils.getDefaulHeaders());
        //响应体重获取的token存储到map
        responseUserData(body,"$.data.token","token");
        //	4、断言响应结果
        //	5、添加接口响应回写内容:回写=excel修改操作
        // body ---第8行, CaseId用例ID---对应
        WriteBackData writeBackData = new WriteBackData(caseInfo.getCaseId(), Constans.RESPONSE_CELLNUM,startSheetIndex,body);
        ExceUtils.responseWriteList.add(writeBackData);




        //	6、数据库后置查询结果
        //	7、据库断言
        //	8、添加断言回写内容
        //	9、添加日志
        //	10、报表断言
    }

    @DataProvider
    public Object[]datas() throws Exception {
        return ExceUtils.read(startSheetIndex,sheetNum);
    }
}


