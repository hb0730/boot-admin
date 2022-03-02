# boot-admin
基于Java11,SpringBoot2.6.3,Spring security,Mysql8,MybatisPlus的前后端分离的后台管理系统

## 源码地址
|| 后端     |前端|
|--------|----|----|
| GitHub |https://github.com/hb0730/boot-admin|https://github.com/hb0730/pure-admin-thin|
| Gitee  |	https://gitee.com/hb0730/boot-admin/|

## 特征
* 前端采用[vue-pure-admin](https://github.com/xiaoxian521/vue-pure-admin) (Vue3,Element-Plus,Vite)
* 支持动态菜单与路由
* 支持动态化的数据字典
* 自定义权限认证与Security的结合使用
* 对一般的操作的:page,save,update等进行抽象封装,进一步减少重复代码的使用
* 前后端统一异常拦截处理，统一输出异常，避免繁琐的判断
* mail邮件服务

## 在线预览
https://admin-v4.hb0730.com 

**能不能访问凭运气,server Host: HK,Redis Server: HK,Mysql Server:HK**

## 系统功能
*用户管理：提供用户的相关配置,支持用户的相关操作
* 岗位管理：配置各个部门的职位
* 菜单管理：已实现菜单动态化与vue路由动态化,后端可配置化，支持多级菜单,树形展示
* 角色管理：对权限与菜单进行分配，可根据部门设置角色的数据权限
* 权限管理: 动态菜单分配权限,菜单与权限的绑定
* 部门管理：可配置系统组织架构，树形表格展示
* 字典管理：可维护常用一些固定的数据，如：状态，性别等
* 系统日志：记录用户操作日志与异常日志，方便开发人员定位拍错 
* 定时任务：整合Quartz做定时任务，可视化操作,以及运行日志信息
* token存储: 提供redis存储，可自行扩展存储方式
* 邮件管理: 实现了系统mail服务

## 系统预览
|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_1.png">|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_2.png">|
|----|----|
|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_3.png">|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_4.png">|
|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_5.png">|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_6.png">|
|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_7.png">|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_8.png">|
|<img src="https://raw.githubusercontent.com/hb0730/boot-admin-ui/v3/docs/view/boot-admin_v3_9.png">||

