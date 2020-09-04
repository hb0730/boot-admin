# v3.0.0
# boot-admin
spring boot Admin management template
# boot-admin 安全
采用spring security认证机制并非Spring security oauth2
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


# thanks
+ [d2-admin](https://github.com/d2-projects/d2-admin)
