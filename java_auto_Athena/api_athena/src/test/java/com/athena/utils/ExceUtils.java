package com.athena.utils;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.athena.pojo.CaseInfo;
import com.athena.pojo.WriteBackData;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
    //回写响应数据的集合
    public static List<WriteBackData> responseWriteList = new ArrayList<WriteBackData>();
    public static Object[] read(int StartSheetIndex,int SheetNum) throws Exception {
        //1.加载excel文件
        FileInputStream fis = new FileInputStream(Constans.EXCEl_PATH);//注意是斜杠
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
    /**
    * @ Author:Athena
    * @ Date 2022/3/19 16:51
    * @ Description //批量回写数据
    * @ Param[]
    * @ return void
    */
    public static void backWrite() throws Exception{
        //excel批量回写操作
        FileInputStream fis = new FileInputStream(Constans.EXCEl_PATH);
        Workbook sheets = WorkbookFactory.create(fis);
        //循环遍历writeBackData集合
        for(WriteBackData writeBackData:responseWriteList) {
            int rowNum = writeBackData.getRowNum();
            int cellNum = writeBackData.getCellNum();
            int sheetIndex = writeBackData.getSheetIndex();
            String content = writeBackData.getContent();
            //获取sheet
            Sheet sheet = sheets.getSheetAt(sheetIndex);
            //获取row
            Row row = sheet.getRow(rowNum);
            //获取cell
            Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            //回写内容
            cell.setCellValue(content);
        }
        //回写excel
        FileOutputStream fos = new FileOutputStream(Constans.EXCEl_PATH);
        sheets.write(fos);
        fis.close();
        fos.close();

    }
}
