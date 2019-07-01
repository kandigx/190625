[TOC]

# Project 625

Launched on 2019.06.25

# 目标

实践 多线程、中间件、大数据相关工具 等技术的使用。

## 功能

数据交换平台，实现数据的接收、校验、处理、统计分析等。

1. 处理请求，处理响应。验证请求中的信息，保证安全性。

### 数据接收

1. 提供一个接口，通过 Http 或者 Https 的协议方式，往此接口发送数据。需要有访问接口的权限，后台会对此接口数据进行校验，校验通过之后才会正式接收。

2. 数据校验过程要防止数据重复，使用 HashMap 的底层原理来对数据进行防重复处理。

3. 使用分布式缓存来保证缓存的有效性。注意保证分布式缓存的一致性。

4. 数据的校验可以通过多线程来进行。对接收的数据量进行判断分析，超出某个限值后即启用线程池中的线程来校验数据，处理完成后合并处理结果，统一返回响应信息。

5. 接口数据接收通过消息中间件来进行。校验通过后即发送数据到中间件，通过中间件来缓冲数据入库。这里要保证数据的不可丢失。要对各种异常情况做好处理，防止服务异常导致数据丢失。

6. 中间件接收端使用多线程来接收数据入库。保证入库的效率。

7. 数据库使用分库分表，自动扩容的方式来保证数据库查询的效率。使用主从复制或者其他备份手段，保证数据库的稳定性。

### 数据共享

1. 公布一个接口，接口提供了对接标准字段信息，对接说明文档。需要有权限才能访问接口获取数据。每个客户端对该接口的访问次数有限制，超出最大限制次数即不允许访问。

### 数据处理

1. 数据接收及共享的日志信息，要通过大数据实时处理分析的方式进行统计分析。实时的方式可以直接显示对接状态，离线处理的方式可以深度分析数据情况。

2. 数据在后台的分析过程，使用分布式大数据处理方式。可以使用 Hadoop、Spark 等方式。



## 实践过程

1. 添加了 spring-boot-starter-web 的 maven 依赖之后，才能够有内置的 tomcat 容器启动该 springboot 项目


