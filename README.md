# tesla-spring-cloud
Tesla spring cloud 快速环境搭建Parent Project


# Version Describe
| Name                                    | Version                            |
| ---------------------------------------- | ---------------------------------- |
| spring boot                              | 1.5.2                      |  
| spring cloud                             | Dalston.RELEASE |



```
  <plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>0.4.13</version>
    <configuration>
      <imageName>${project.name}:${project.version}</imageName>
      <imageTags>
        <imageTag>${project.version}</imageTag>
        <imageTag>latest</imageTag>
      </imageTags>
      <dockerDirectory>${project.basedir}/</dockerDirectory>
      <resources>
        <resource>
          <targetPath>/</targetPath>
          <directory>${project.build.directory}</directory>
          <include>${project.build.finalName}.jar</include>
        </resource>
      </resources>
    </configuration>
  </plugin>
```


# Build Step

### maven打包并构建镜像
mvn clean package -DskipTests=true docker:build

### maven打包构建镜像并push到仓库
mvn clean package docker:build -DpushImage

### maven打包构建镜像，将指定tag的镜像push到仓库，该命令需使用
`<imageTags><imageTag>...</imageTag></imageTags>标签`

mvn clean package docker:build -DpushImageTag

