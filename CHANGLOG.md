#v4.0.0
基于`java 11` `spring-boot-starter:2.6.3` `mybatis-plus:3.5.1` `Mysql:8`开发

# v3.0.1
 * fix OptionService 189行缓存更新问题
 + add DictCacheUtil新增字典缓存工具类
 + 采用JDK8的LocalDateTime时间工具类
 + 更新hb0730-commons的版本
 + 获取属性映射表字段对应`@FieldClass`和`@FieldInfo`
 + fix password不进行序列化
 + fixed redis Cache 序列化泛型参数问题
 + fixed job参数校验与参数转换问题
 
# v2.0.0
 * update 更改page使用mybatisPlus自带分页，去除pagehelper
 * update 更新token存储
 * update 更新oss存储
 * update 更新异常机制
 * update 更新springboot启动执行机制
 * add  新增字典表缓存
 * update  常量名称
 * add quartz定时任务实现
 * fix 修复菜单禁用失败
 * fix 修复阿里云Oss上传删除方法
 * fix 修复菜单新增无权限以及排序失效
 * add 新增图片资源管理(beta)
 * fix 修复在线用户登录时间排序
 * add 新增sms短信服务
 * add 新增mail邮件服务
 * fix 抽象controller与service
 * fix 菜单树形排序展示
 * fix jobInvoke 参数所支持类型的问题
# v1.0.0
 * 模拟实现spring security认证登录,角色,权限以及注销等
 * 实现了RBAC认证
 * 前后动态菜单
 * 日志记录
 * 在线用户
 * 完善条件筛选
 * 日志记录
 * 提供oss存储
    - 本地local
    - 阿里云oss存储
 * 模板导出
 * 模板导入
## tag v1.0.0-beta.3
 * 提供oss存储
   - 本地local
   - 阿里云oss存储
 * 模板导出
 * 模板导入
## tag v1.0.0-beta.2
 * 完善条件筛选
 * 日志记录
## tag v1.0.0-beta.1
 * 实现了RBAC认证
 * 前后动态菜单
 * 日志记录
 * 在线用户
## tag v1.0.0-beta.0
 * 模拟实现spring security认证登录,角色,权限以及注销等
 
