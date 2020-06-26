# boot-admin
spring boot Admin management template

# boot-admin 安全
采用spring security认证机制并非Spring security oauth2

#  数据库版本控制pdMan

## boot-admin 用户名与密码
 + Administrator/123456
## oss存储
  + local本地
  + aliyun oss存储 
## 已实现
 * 用户管理：提供用户的相关配置，增删改查导入导出
 * 岗位管理: 增删改查导入导出
 * 菜单管理: 已实现菜单动态路由，后端可配置化，支持多级菜单,树形展示
 * 权限管理: 权限管理与菜单管理绑定
 * 组织管理: 动态组织管理
 * 角色管理: 对权限与菜单进行分配，可根据部门设置角色的数据权限，以及数据权限范围分配
 * 字典管理: 动态字典管理，和字典项管理
 * 登录日志管理: 动态管理
 * 操作日志管理: 动态管理
 * 在线用户管理:  动态管理,强退
 * 定时任务管理: 动态定时任务,使用spring task
 * token存储方式: 提供内存模式与redis存储
 * oss存储方式: 提供本地local与阿里云OSS存储 
 * spring security认证: 支持spring security el表达式
## 未实现
 * 消息管理: 公告,通知等
 * oss对象存储可视化
 * 系统缓存
 * Quartz 定时任务
 
# 前端
 <https://github.com/hb0730/boot-admin-ui>
#  技术
 * jdk 11 
  * spring boot 2.2.5.RELEASE
  * mybatisPlus 3.3.1.tmp
  * ~~pagehelper 1.2.13~~
  * mysql 8.0.16
  * hikaricp 3.4.2
  * guava 28.2-jre
  * gson 2.8.6
  * lombok 1.18.10
  * easyexcel 2.1.6
# 在线访问地址
  http://admin.hb0730.com
  
# thanks
 * [RuoYi-Vue](https://github.com/yangzongzhuan/RuoYi-Vue)
 * [pdMan](https://gitee.com/robergroup/pdman)
 * [halo](https://github.com/halo-dev/halo)
