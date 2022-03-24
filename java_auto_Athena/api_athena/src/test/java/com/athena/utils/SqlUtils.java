package com.athena.utils;
import com.athena.pojo.ActivitiModel;
import com.mysql.fabric.xmlrpc.base.Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * @Project:api_athena
 * @Date: 2022/3/20 9:57
 * @Author:Athena
 */
public class SqlUtils {
    private static Logger logger = Logger.getLogger(SqlUtils.class);
   /**
   * @ Author:Athena
   * @ Date 2022/3/21 15:40
   * @ Description //测试方法查询数据库方法的验证
   * @ Param[]
   * @ return void
   */
   @Test
    public static void sqlTest() throws Exception {
        //新增
       /* String sql1 = "INSERT INTO act_re_model\n" +
                 "(ID_,REV_,NAME_,KEY_,CATEGORY_,CREATE_TIME_,LAST_UPDATE_TIME_,\n" +
                 "VERSION_,META_INFO_,DEPLOYMENT_ID_,EDITOR_SOURCE_VALUE_ID_,\n" +
                 "EDITOR_SOURCE_EXTRA_VALUE_ID_,TENANT_ID_)\n" +
                 "VALUES ('1113333',\n" +
                 " '2', 'Athena', '11133', NULL,'2022-03-19 15:05:48.189',\n" +
                 " '2022-03-19 15:05:48.225','0'," +
                "'{\"name\":\"测试同学你好\",\"revision\":0,\"description\":\"111\"}', " +
                "NULL,'00fb8316-a753-11ec-87bc-40b89a76625f',NULL, '')";
        insertSql( sql1);*/
        //删除
       /* String sql3 = "DELETE FROM act_re_model where NAME_='测试同学你好'";
       insertSql( sql3);*/
        //修改
      /*  String sql2 = "UPDATE act_re_model set NAME_='测试同学你好' WHERE ID_='111'";
       insertSql( sql2);*/
        //查询1
       String sql3 = " SELECT count(*) from  act_re_model where NAME_='Athena'";
        //mapHandler(sql3);
        //查询2
       // beanHandler(sql3);
        //查询3
        //beanListHandler(sql3);
        //查询4
        //scalarHandler(sql3);
         getSingleResult(sql3);
    }
    //新增-删除-修改
    public static void insertSql(String sql) throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        runner.update(conn,sql);
        JDBCUtils.close(conn);
    }
    //查询5-获取单个查询结果集
    public static Object getSingleResult(String sql) {
        //如果sql为空直接返回null
        if(StringUtils.isBlank(sql)) {
            return null;
        }
       logger.info("数据库断言SQL语句：" + sql);
        //执行sql语句
        Object result = null;
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            ScalarHandler handler = new ScalarHandler();
            result = runner.query(conn, sql, handler);
            logger.info("查询结果:"+result);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            //finally 无论是否发生异常，finally都会执行，一般用来释放资源
            JDBCUtils.close(conn);
        }
        return result;
    }
    //查询3--需要映射实体类方式获取查询结果(输出每条查询数据)
    private static void beanListHandler( String sql ) throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        BeanListHandler<ActivitiModel> handler = new BeanListHandler<ActivitiModel>(ActivitiModel.class);
        List<ActivitiModel> result = runner.query(conn, sql, handler);
        for (ActivitiModel activitiModel : result) {
            System.out.println(activitiModel);
        }
    }
    //查询4
    private static void scalarHandler( String sql ) throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        ScalarHandler handler = new ScalarHandler();
        Object result = runner.query(conn, sql, handler);
    }
    //查询2---需要映射实体类方式获取查询结果
    private static void beanHandler( String sql ) throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        BeanHandler<ActivitiModel> handler = new BeanHandler<ActivitiModel>(ActivitiModel.class);
        ActivitiModel result = runner.query(conn, sql, handler);
        System.out.println(result);
    }
    //查询1--不用映射实体类,可以直接获取响应结果
    private static void mapHandler(String sql) throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        MapHandler handler = new MapHandler();
        Map<String, Object> result = runner.query(conn, sql, handler);
        System.out.println(result);
    }


}
