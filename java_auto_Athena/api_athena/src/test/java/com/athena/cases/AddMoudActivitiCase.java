package com.athena.cases;
import com.alibaba.fastjson.JSONPath;
import com.athena.pojo.CaseInfo;
import com.athena.utils.Constans;
import com.athena.utils.ExceUtils;
import com.athena.utils.HttpUtils;
import com.athena.utils.SqlUtils;
import org.apache.commons.codec.language.bm.Lang;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project:api_athena
 * @Date: 2022/3/17 22:23
 * @Author:Athena
 * 注册类
 */
public class AddMoudActivitiCase extends BaseCase{
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:31
    * @ Description //activiti新增模型
    * @ Param
    * @ return
    */

    @Test(dataProvider = "datas")
    public void  RegisterTest(CaseInfo caseInfo){
        //  1、参数化替换
        //	2、数据库前置查询结果(数据断言必须在接口执行前后都查询)
        String sql = " SELECT count(*) from  act_re_model";
        Object beforeSqlResylt = SqlUtils.getSingleResult(sql);
        //	3、调用接口:发起请求
        String body = HttpUtils.call(caseInfo,HttpUtils.getDefaulHeaders());
        //	4、json响应结果断言  (设置多字段的断言)
            responseAssert(caseInfo,body);
        //	5、添加接口响应回写内容:回写=excel修改操作
        addresDataList(caseInfo.getCaseId(), Constans.RESPONSE_CELLNUM,startSheetIndex,body);
        //	6、数据库后置查询结果
        Object afterSqlResylt = SqlUtils.getSingleResult(sql);

        //	7、据库断言
        /*BigDecimal afterSqlResylt_a = (BigDecimal)afterSqlResylt;
        BigDecimal beforeSqlResylt_b = (BigDecimal)beforeSqlResylt;
        String params = caseInfo.getParams();
        String amounStr = JSONPath.read(params,"$.code").toString();
        BigDecimal amounStr_big = new BigDecimal(amounStr);*/


        //	8、添加断言回写内容
        //	9、添加日志
        //	10、报表断言
    }
    @DataProvider
    public Object[]datas() throws Exception {
        return ExceUtils.read(startSheetIndex,sheetNum);
    }
}


