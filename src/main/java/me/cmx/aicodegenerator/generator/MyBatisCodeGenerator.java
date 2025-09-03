package me.cmx.aicodegenerator.generator;

import cn.hutool.core.lang.Dict;
import cn.hutool.setting.yaml.YamlUtil;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Map;

public class MyBatisCodeGenerator {
    // 需要生成的表名
    private static final String[] TABLE_NAMES = {"chat_history"};

    public static void main(String[] args) {
        // 从yaml中获取数据源信息
        Dict dict = YamlUtil.loadByPath("application.yml");
        Map<String, String> datasourceConfig = dict.getByPath("spring.datasource");
        // 检查yml文件 发现这几个字段最后都要取出为字符串 所以一开始就直接使用字符串
        // 如果字段的类型有很多不一样的类型 再使用Object来获取 再按需转型
        String url = datasourceConfig.get("url");
        String username = datasourceConfig.get("username");
        String password = datasourceConfig.get("password");

        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        //创建配置内容，两种风格都可以。
        //GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        // 第二种是链式的写法 看个人喜好
        GlobalConfig globalConfig = createGlobalConfig();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    //public static GlobalConfig createGlobalConfigUseStyle1() {
    //    //创建配置内容
    //    GlobalConfig globalConfig = new GlobalConfig();
    //
    //    //设置根包
    //    globalConfig.setBasePackage("com.test");
    //
    //    //设置表前缀和只生成哪些表
    //    globalConfig.setTablePrefix("tb_");
    //    globalConfig.setGenerateTable("tb_account", "tb_account_session");
    //
    //    //设置生成 entity 并启用 Lombok
    //    globalConfig.setEntityGenerateEnable(true);
    //    globalConfig.setEntityWithLombok(true);
    //    //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
    //    globalConfig.setEntityJdkVersion(17);
    //
    //    //设置生成 mapper
    //    globalConfig.setMapperGenerateEnable(true);
    //
    //    //可以单独配置某个列
    //    ColumnConfig columnConfig = new ColumnConfig();
    //    columnConfig.setColumnName("tenant_id");
    //    columnConfig.setLarge(true);
    //    columnConfig.setVersion(true);
    //    globalConfig.setColumnConfig("tb_account", columnConfig);
    //
    //    return globalConfig;
    //}

    public static GlobalConfig createGlobalConfig() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包 生成内容先放在一个临时的目录下 检查后 再移动到项目目录中
        globalConfig.getPackageConfig()
                .setBasePackage("me.cmx.aicodegenerator.generesult");

        //设置表前缀和只生成哪些表，setGenerateTable 未配置时，生成所有表
        globalConfig.getStrategyConfig()
                .setGenerateTable(TABLE_NAMES)
                // 设置逻辑删除的默认字段名称
                .setLogicDeleteColumn("isDelete");

        //设置生成 entity 并启用 Lombok
        globalConfig.enableEntity()
                .setWithLombok(true)
                .setJdkVersion(21);

        //设置生成 mapper
        globalConfig.enableMapper();
        globalConfig.enableMapperXml();

        // 设置生成Controller
        globalConfig.enableController();

        // 设置生成Service
        globalConfig.enableService();
        globalConfig.enableServiceImpl();

        //可以单独配置某个列
        //ColumnConfig columnConfig = new ColumnConfig();
        //columnConfig.setColumnName("tenant_id");
        //columnConfig.setLarge(true);
        //columnConfig.setVersion(true);
        //globalConfig.getStrategyConfig()
        //        .setColumnConfig("tb_account", columnConfig);

        // 设置生成时间和字符串为空，避免多余的代码改动
        globalConfig.getJavadocConfig()
                .setSince("");

        return globalConfig;
    }
}
