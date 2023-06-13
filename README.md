# Boot-Admin V5

基于Java17,SpringBoot3,Spring security6,Mysql8,MybatisPlus的前后端分离的后台管理系统

|                                                                                |                                                                                |                                                                                |
|--------------------------------------------------------------------------------|--------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| ![](https://github.com/hb0730/boot-admin/raw/v5/doc/aseet/boot-admin_v5_1.png) | ![](https://github.com/hb0730/boot-admin/raw/v5/doc/aseet/boot-admin_v5_2.png) | ![](https://github.com/hb0730/boot-admin/raw/v5/doc/aseet/boot-admin_v5_2.png) |
| ![](https://github.com/hb0730/boot-admin/raw/v5/doc/aseet/boot-admin_v5_3.png) | ![](https://github.com/hb0730/boot-admin/raw/v5/doc/aseet/boot-admin_v5_5.png) | ![](https://github.com/hb0730/boot-admin/raw/v5/doc/aseet/boot-admin_v5_6.png) |


## 开源地址
|        | 后端                                   | 前端                                       |
|--------|--------------------------------------|------------------------------------------|
| Github | https://github.com/hb0730/boot-admin | 	https://github.com/hb0730/boot-admin-ui |
| Gitee  | https://gitee.com/hb0730/boot-admin/ | https://gitee.com/hb0730/boot-admin-ui   |

## 特征
* 前端采用vue-pure-admin (Vue3,Element-Plus,Vite)
* 支持动态菜单与路由
* 自定义权限认证与Security的结合使用
## 在线预览
https://boot.hb0730.com/next

**admin/123456 能不能访问凭运气,Java Server: HK,Redis Server: HK,Mysql Server:HK**


## SQL 所在地
> doc/sql/boot-admin.sql

## question
* 为什么实时请求routes
   >1. 首先我们后端已经缓存了当前用户路由
   >2. 我们采用EventListener,当管理者更改了用户权限能够实时响应，
   >3. 如果要前端缓存，可以开启前端配置: `CachingAsyncRoutes`