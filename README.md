# 1. 项目背景介绍
内容商城作为例子，它有如下功能：

- 产品管理<br>
产品/购物车/购买情况的增删改查。
- 用户管理<br>
用户的登录、权限管理等。


# 2. 创建项目的组织结构

在这个项目下创建各个子模块，每个自模块都是一个独立的SpringBoot项目：
- Czl-User
用户服务
- Czl-Product
产品服务
- Czl-Controller
本系统的控制层，和以往三层结构中的Controller层的作用一样，都是用作请求调度，只不过在微服务架构中，我们将它抽象成一个单独的系统，可以独立运行。
- Czl-Common-Service-Facade
它处于本系统的最底层，被所有模块依赖，一些公用的类库都放在这里。
- Czl-UI-Frontend
前段展示页面


# 3 运行

## 3. 创建Docker容器
### 3.1 安装Docker
在使用Docker之前，你当然先要安装Docker，安装过程较为简单，基本上就是傻瓜式操作，这里就不作过多介绍了，你可以在Docker的官网下载相应系统的安装包。
https://www.docker.com/

## 3.2 执行docker-compose文件
```aidl
docker-compose -f docker-compose-test.yml build
docker-compose -f docker-compose-test.yml up -d
```
开启的docker容器有redis、zk+dubbo、mysql

## 3.3 查看dubbo管理界面
```aidl
http://localhost:10000/dubbo-admin-2.8.4/
账号：guest 密码：guest
账号：root  密码：root
```

## 3.4

## 4. 总结
aa