package com.athena.utils;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.athena.pojo.CaseInfo;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.List;

/**
 * @Project:api_athena
 * @Date: 2022/3/18 8:49
 * @Author:Athena
 */
public class ExceUtils {
    /**
    * @ Author:Athena
    * @ Date 2022/3/18 8:50
    * @ Description //读取excel数据
    * @ Param[]
    * @ return void
    */
@Test
    public static Object[] read(int StartSheetIndex,int SheetNum) throws Exception {
        //1.加载excel文件
        FileInputStream fis = new FileInputStream("src/test/resources/Athena1.xlsx");//注意是斜杠
        //2.创建easypoi导入参数对象
        ImportParams params = new ImportParams();
        /**
         * 开始读取的sheet位置,默认为0
         */
        params.setStartSheetIndex(StartSheetIndex);
        /**
         * 上传表格需要读取的sheet数量,默认为1
         */
        params.setSheetNum(SheetNum);

        //3.importExcel(文件流,映射实体类.class,easyPOI导入参数)
        List<CaseInfo> caseinfolist = ExcelImportUtil.importExcel(fis, CaseInfo.class,params);
        Object[] objects = caseinfolist.toArray();
        fis.close();
        return  objects;

    }
}
