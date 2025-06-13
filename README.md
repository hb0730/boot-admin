# BOOT-ADMIN

BOOT-ADMIN是一个基于Spring Boot和Vue.js的后台管理系统，旨在提供一个简单易用的管理界面，适用于各种业务场景。

## V7 版本说明

回归单体应用，去除微服务架构，简化项目结构，提升开发效率。

如果需要微服务架构可以参考[zoom-projects](https://github.com/zoom-projects),

如果需要SAAS相关功能可以参考V6相关分支。

## 特性

- 基于Spring Boot3和Mybatis3的后端
- 基于Vue3和Element Plus的前端
- 使用Vite构建前端项目
- 使用Spring Security进行安全认证
- 使用MySQL作为数据库
- 使用Redis作为缓存
- 使用Docker进行容器化部署
- 使用SpringDoc生成API文档
- 使用Lombok简化代码

## 快速开始

```shell
# 克隆项目
git clone https://github.com/hb0730/boot-admin.git

# 进入项目目录
cd boot-admin
# 启动后端服务
./mvnw spring-boot:run
```

## 前端

```shell
```

## 用户名/密码

`admin/123456`

## 目录说明

```shell
├── app  主应用目录 SpringApplication
├── config 相关配置&spring-security配置
├── core  核心模块 （crud封装)
├── infra  基础设施模块(本地启动 docker-compose)
├── modules 业务模块
│   └── sys 系统模块
│       ├── sys-app controller应用
│       └── sys-service service应用
├── plugins 插件模块
│   ├── plugin-base  基础设施模块
│   ├── plugin-cache 缓存模块
│   ├── plugin-desensitize 脱敏模块
│   ├── plugin-email 邮件模块
│   ├── plugin-mybatis Mybatis模块  
│   ├── plugin-mybatis-query Mybatis-Query模块
│   ├── plugin-operator-log 操作日志模块
│   ├── plugin-oss 对象存储模块
│   ├── plugin-poi Excel模块
│   ├── plugin-spring-security Spring Security模块
│   ├── plugin-springdoc API文档模块
│   ├── plugin-web Web模块
└── sql 数据库脚本

```
