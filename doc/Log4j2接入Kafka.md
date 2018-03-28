# Log4j2接入Kafka
使用Log4j2日志框架将程序产生的日志发送到Kafka，后续接入Elastic Stack对日志进行清洗分析。本文介绍log4j2接入Kafka的步骤。

## 导入依赖
pom.xml



		<!--采用 log4j2 start-->
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter</artifactId>
		    <exclusions>
		        <exclusion>
		            <groupId>org.springframework.boot</groupId>
		            <artifactId>spring-boot-starter-logging</artifactId>
		        </exclusion>
		    </exclusions>
		</dependency>
		
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-log4j2</artifactId>
		</dependency>
		
		<!--log4j2 异步需要该依赖-->
		<dependency>
		    <groupId>com.lmax</groupId>
		    <artifactId>disruptor</artifactId>
		    <version>3.3.4</version>
		</dependency>
		
		<!--Kafka Client-->
		<dependency>
		    <groupId>org.apache.kafka</groupId>
		    <artifactId>kafka-clients</artifactId>
		    <version>1.0.1</version>
		</dependency>
		<!--采用 log4j2 end-->
    


## log4j2配置
**Log4j2.xml**

- Kafka日志输出设置

		<Appenders>
		    <!--Kafka日志输出设置 start-->
		    <!--设置topic-->
		    <Kafka name="KafkaLog" topic="log" ignoreExceptions="false">
		        <PatternLayout pattern="{[%d{yyyy-MM-dd HH:mm:ss.SSS}] - [%p] - [%c:%L] - [Method = %M] - [%m]}"/>
		        <!--设置Kafka连接-->
		        <Property name="bootstrap.servers">http://xxx.xxx.xxx:xxx</Property>
		        <!--设置最大阻塞时间-->
		        <Property name="max.block.ms">2000</Property>
		    </Kafka>
		    
		    <!--当Kafka宕机时采取写本地文件-->
		    <RollingFile name="failoverKafkaLog" fileName="${basedir}/kafka/failoverKafkaLog.log" append="true"
		                 filePattern="${basedir}/kafka/$${date:yyyy-MM}/failoverKafkaLog-%d{yyyy-MM-dd}-%i.log.gz">
		        <ThresholdFilter level="INFO" onMatch="ACCEPT" onMismatch="DENY"/>
		        <PatternLayout
		                pattern="%highlight{[%d{yyyy-MM-dd HH:mm:ss.SSS}] - [%p] - [%c:%L] - [Method = %M] - [%m]}%n"/>
		        <Policies>
		            <OnStartupTriggeringPolicy/>
		            <SizeBasedTriggeringPolicy size="50 MB"/><!-- 每到50M生成一个日志文件 -->
		            <TimeBasedTriggeringPolicy/>
		        </Policies>
		        <!-- 最大保存文件数，超过的删除 -->
		        <DefaultRolloverStrategy max="30" compressionLevel="9">
		            <Delete basePath="${basedir}" maxDepth="2">
		                <IfFileName glob="*/*.log.gz"/>
		                <IfLastModified age="7d"/>
		            </Delete>
		        </DefaultRolloverStrategy>
		    
		    </RollingFile>
		    
		    <!--设置策略-->
		    <Failover name="KafkaFailover" primary="KafkaLog" retryIntervalSeconds="600">
		        <Failovers>
		            <AppenderRef ref="failoverKafkaLog"/>
		        </Failovers>
		    </Failover>
		    <!--Kafka日志输出设置 end-->
		</Appenders>


- 异步发送
  
		<Loggers>
		    <!--采用异步记录-->
		    <AsyncRoot level="info" additivity="false" includeLocation="true">
		            <AppenderRef ref="KafkaFailover"/>
		    </AsyncRoot>
		</Loggers>



