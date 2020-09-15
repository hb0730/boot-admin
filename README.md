# v3.0.0
# 简介
基于SpringBoot 2.3.3,spring Security 5.3,mybatis plus3.4的前后端分离的后台管理系统
# 源码地址
||后端资源|前端资源|
|----|----|----|
|github|https://github.com/hb0730/boot-admin|https://github.com/hb0730/boot-admin-ui|
|码云|https://gitee.com/hb0730/boot-admin/|https://gitee.com/hb0730/boot-admin-ui| 
# 特征
+ 前端采用Vue ,[d2-admin](https://github.com/d2-projects/d2-admin)
+ 支持动态菜单与路由
+ 支持动态化的数据字典
+ 自定义权限认证与Security的结合使用
+ 对一般的操作的:page,save,update等进行抽象封装,进一步减少重复代码的使用
+ 前后端统一异常拦截处理，统一输出异常，避免繁琐的判断
+ mail邮件服务
# 在线预览
http://admin.hb0730.com/  (可能比较慢)

`Administrator/123456`
# 技术
* jdk8
* spring boot 2.3.3.RELEASE
* mybatisPlus 3.4.0
* mysql 8.0.16
* hikaricp 3.4.5
* guava 29.0-jre
* gson 2.8.6
* lombok 18.12
* hb0730/commons-lang
* hb0730/commons-http
* hb0730/commons-cache
* hb0730/commons-json
* hb0730/commons-mail
* quartz 2.3.2
* spring security 5.3.4
* spring data redis 2.3.3
* okHttp 3.14.9
* apache/commons-pool
* apache/commons-lang3

# 系统功能
+ 用户管理：提供用户的相关配置,支持用户的相关操作
+ 岗位管理：配置各个部门的职位
+ 菜单管理：已实现菜单动态化与vue路由动态化,后端可配置化，支持多级菜单,树形展示
+ 角色管理：对权限与菜单进行分配，可根据部门设置角色的数据权限
+ 权限管理: 动态菜单分配权限,菜单与权限的绑定
+ 部门管理：可配置系统组织架构，树形表格展示
+ 字典管理：可维护常用一些固定的数据，如：状态，性别等
+ 系统日志：记录用户操作日志与异常日志，方便开发人员定位拍错
+ 定时任务：整合Quartz做定时任务，可视化操作,以及运行日志信息
+ token存储: 提供redis存储，可自行扩展存储方式
+ 邮件管理: 实现了系统mail服务
# 
# 系统预览
|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_1.png">|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_2.png">|
|----|----|
|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_3.png">|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_4.png">|
|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_5.png">|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_6.png">|
|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_7.png">|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_8.png">|
|<img src="https://github.com/hb0730/boot-admin-ui/blob/v3/docs/view/boot-admin_v3_9.png">||
# 注意
1. quartz: 本项目并未把quartz作为单独的数据源,项目只采用了单一数据源，如需将quartz作为单独的数据源，可以参考[v2](https://github.com/hb0730/boot-admin/blob/v2/src/main/java/com/hb0730/boot/admin/configuration/DataSourceConfiguration.java)
2. 本项目SQL放置在 [doc](https://github.com/hb0730/boot-admin/tree/v3/doc/sql) 下,可以对应版本
3. 本项目可能有过多的个人项目使用,是可以替换成比较认可的:只要符合用法
4. 项目当中domain下规定了很多基础设施,比如(controller,service,entity)等
# thanks
+ [d2-admin](https://github.com/d2-projects/d2-admin)
