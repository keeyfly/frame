/*
package com.hx.novel.frame.dao;


*/
/**
 * Created by 李贺翔 on 2016/12/14.
 * Description:创建模式对象,添加实体表
 *//*

public class Generator {
   */
/* public static void main(String[] args) throws Exception {
        int version = 1;
        String defaultPackage = "com/qdhc/tms/main/login/bean";
        //创建模式对象，指定版本号和自动生成的bean对象的包名
      //  Schema schema = new Schema(version, defaultPackage);
        //指定自动生成的dao对象的包名,不指定则都DAO类生成在"test.greenDAO.bean"包中
        schema.setDefaultJavaPackageDao("com/qdhc/tms/main/login/dao");
        //添加实体
        addEntity(schema);
        String outDir = "D:/adt-bundle-windows-x64/workspace/studio/frame/study_demo/testgreendao/src/main/java-gen";
        //调用DaoGenerator().generateAll方法自动生成代码到之前创建的java-gen目录下
        new DaoGenerator().generateAll(schema, outDir);
    }

    private static void addEntity(Schema schema) {
        //添加一个实体，则会自动生成实体Entity类
        Entity entity = schema.addEntity("Entity");
        //指定表名，如不指定，表名则为 Entity（即实体类名）
        entity.setTableName("user");
        entity.addIdProperty().autoincrement();//添加Id,自增长
        entity.addStringProperty("token").notNull();
        entity.addStringProperty("username");
    }*//*

}
*/
