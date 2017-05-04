# Tesla spring cloud config

> spring cloud config配置管理服务，集中化管理所有服务的各种配置环境，其基于使用中心配置仓库的思想，支持本地存储、
Git以及Subversion.分为config-server(服务端)和config-client(客户端)两个部分.



## Build Step

* maven打包并构建镜像
```
mvn clean package -DskipTests=true docker:build

```
* maven打包构建镜像并push到仓库
```
mvn clean package docker:build -DpushImage
```

* maven打包构建镜像，将指定tag的镜像push到仓库，该命令需使用
<imageTags><imageTag>...</imageTag></imageTags>标签
```
mvn clean package docker:build -DpushImageTag
```