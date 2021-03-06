package com.athena.cases;
import com.athena.pojo.CaseInfo;
import com.athena.utils.Constans;
import com.athena.utils.ExceUtils;
import com.athena.utils.HttpUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Project:api_athena
 * @Date: 2022/3/17 22:23
 * @Author:Athena
 * 注册类
 */
public class RegisterCase extends BaseCase{
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:31
    * @ Description //注册接口
    * @ Param
    * @ return
    */
    @Test(dataProvider = "datas")
    public void  registerTest(CaseInfo caseInfo){
        //  1、参数化替换
        //	2、数据库前置查询结果(数据断言必须在接口执行前后都查询)
        //	3、调用接口:发起请求
        String body = HttpUtils.call(caseInfo,  HttpUtils.getDefaulHeaders());
        //响应体重获取的token存储到map
         //responseUserData(body,"$.data.token","token");
        //	4、json响应结果断言  (设置多字段的断言)
        responseAssert(caseInfo,body);
        //	5、添加接口响应回写内容:回写=excel修改操作
        addresDataList(caseInfo.getCaseId(), Constans.RESPONSE_CELLNUM,startSheetIndex,body);
        //	6、数据库后置查询结果
        //	7、据库断言
        //	8、添加断言回写内容
        //	9、添加日志
        //	10、报表断言

    }
   @DataProvider
    public Object[]datas() throws Exception {
       return ExceUtils.read(0,1);
   }
    }

