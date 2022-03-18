package com.athena.cases;
import com.athena.pojo.CaseInfo;
import com.athena.utils.ExceUtils;
import com.athena.utils.HttpUtils;
import org.testng.annotations.DataProvider;
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
public class RegisterCase {
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:31
    * @ Description //注册接口
    * @ Param
    * @ return
    */
    @Test(dataProvider = "datas")
    public void  RegisterTest(CaseInfo caseInfo){
        //注册接口测试
        //1.请求参数
        String url = caseInfo.getUrl();
        String params = caseInfo.getParams();
        String type = caseInfo.getType();
        Map<String,Object> headers = new HashMap<String, Object>();
        headers.put("Content-Type", "application/json");
        String body = null;
        if("post".equals(type)){
            body = HttpUtils.mypost(url,headers,params);
        }else if("get".equals(type)){
            body = HttpUtils.myget(url,headers);
        }else if("patch".equals(type)){
            body = HttpUtils.mypatch(url,headers,params);
        }
        System.out.println(body);
    }
   @DataProvider
    public Object[]datas() throws Exception {

       return ExceUtils.read(0,1);
   }
    }

