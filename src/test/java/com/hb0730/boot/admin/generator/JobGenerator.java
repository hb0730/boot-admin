package com.hb0730.boot.admin.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务调度
 *
 * @author bing_huang
 */
public class JobGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        //数据源
        DataSourceConfig datasourceConfig = new DataSourceConfig();
        datasourceConfig.setDbType(DbType.MYSQL);
        datasourceConfig.setUrl(MysqlProperties.MYSQL_URL);
        datasourceConfig.setDriverName(MysqlProperties.DRIVER_NAME);
        datasourceConfig.setUsername(MysqlProperties.MYSQL_USERNAME);
        datasourceConfig.setPassword(MysqlProperties.MYSQL_PASSWORD);
        mpg.setDataSource(datasourceConfig);
        //数据库表配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setTablePrefix("t_sys");
        strategyConfig.setInclude("t_sys_job");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setSuperEntityClass(MysqlProperties.SUPER_ENTITY_CLASS);
        strategyConfig.setSuperEntityColumns(MysqlProperties.SUPER_ENTITY_COLUMNS);
        strategyConfig.setSuperServiceClass(IBaseService.class);
        strategyConfig.setSuperServiceImplClass(SuperBaseServiceImpl.class);
        strategyConfig.setSuperControllerClass(MysqlProperties.SUPER_CONTROLLER_CLASS);
        strategyConfig.setEntityColumnConstant(true);
        strategyConfig.setEntityBuilderModel(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        strategyConfig.setVersionFieldName(MysqlProperties.VERSION_FILE_NAME);
        strategyConfig.setLogicDeleteFieldName(MysqlProperties.LOGIC_DELETE_FILE_NAME);
        mpg.setStrategy(strategyConfig);
        //包名配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.hb0730.boot.admin.project.system");
        packageConfig.setModuleName("quartz");
        packageConfig.setEntity("model.entity");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("");
        packageConfig.setController("controller");
        mpg.setPackageInfo(packageConfig);

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setFileOverride(true);
        globalConfig.setOpen(false);
        globalConfig.setAuthor("bing_huang");
        globalConfig.setEntityName("%sEntity");
        globalConfig.setMapperName("I%sMapper");
        globalConfig.setServiceName("I%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        mpg.setGlobalConfig(globalConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.execute();
    }
}
