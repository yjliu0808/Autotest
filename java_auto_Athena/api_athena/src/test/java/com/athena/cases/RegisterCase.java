package com.athena.cases;
import com.athena.pojo.CaseInfo;
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
    public void  RegisterTest(CaseInfo caseInfo){
        //发起请求
        HttpUtils.call(caseInfo,HttpUtils.getDefaulHeaders());

    }
   @DataProvider
    public Object[]datas() throws Exception {
       return ExceUtils.read(0,1);
   }
    }

