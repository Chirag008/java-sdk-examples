# Packaging

## Using Maven

You can use [maven assembly plugin](http://maven.apache.org/plugins/maven-assembly-plugin/) to build you test or add-on. The configuration is done in the *pom.xml* file.

* Create (or alter) the *\<dependencies\>* section to include TestProject SDK dependency and adjust the *systemPath* property accordingly.
* Add any other dependency that you require. In this example the Googles [GSON](https://mvnrepository.com/artifact/com.google.code.gson/gson) dependency is added.
* Create (or alter) the *\<build\>* section to use the maven assembly plugin.

The exported jar file will contain the GSON library alongside with your code.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>io.testproject</groupId>
    <artifactId>maven-test</artifactId>
    <version>0.1</version>

    <dependencies>
        <!-- TestProject SDK -->
        <dependency>
            <groupId>io.testproject</groupId>
            <artifactId>java-sdk</artifactId>
            <version>0.0.1</version>
            <!-- Update the location of the sdk as required -->
            <systemPath>${basedir}/../io.testproject.sdk.java.jar</systemPath>
            <scope>system</scope> <!-- Local file won't be assembled by maven-assembly-plugin -->
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.7</version>
        </dependency>
    </dependencies>

    <build>
        <sourceDirectory>src</sourceDirectory>
        <plugins>
            <!-- Assembly Plugin - Create a JAR with dependencies for uploading to TestProject -->
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <!-- Compile Plugin -->
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

> NOTE: The jar task excludes TestProject SDK as it is not necessary when uploading your test or add-ons to TestProject.
#### Packaging Test With Addon Proxy
If you want to use your addon (or shared one) in your test, you need to follow these steps:
1. Download addon proxy on addon page (click on `Download Proxy`)
2. Add this proxy as dependency in `pom.xml` file:
```xml
<dependency>
    <groupId>io.testproject</groupId>
    <artifactId>exampleaddonproxy</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <systemPath>/*path to addon proxy*/</systemPath>
    <scope>system</scope>
</dependency>
```
3. In `src` folder create `main` package, and in that package create `descriptor.xml` file. This file should contain the following:
```xml
<assembly
    xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.0 http://maven.apache.org/xsd/assembly-1.1.0.xsd">
    <id>jar-with-dependencies</id>
    <formats>
        <format>jar</format>
    </formats>
    <includeBaseDirectory>false</includeBaseDirectory>
    <dependencySets>
        <dependencySet>
            <outputDirectory>/</outputDirectory>
            <useProjectArtifact>true</useProjectArtifact>
            <unpack>true</unpack>
            <scope>runtime</scope>
        </dependencySet>
        <dependencySet>
            <outputDirectory>/</outputDirectory>
            <unpack>true</unpack>
            <scope>system</scope>
            <excludes>
                <exclude>io.testproject:java-sdk</exclude>
            </excludes>
        </dependencySet>
    </dependencySets>
</assembly>
```

And now you can code your test and package it afterwards as usual.