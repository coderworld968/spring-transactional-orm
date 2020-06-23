# Spring事务与ORM的自研与集成

根据Spring事务原理,自定义ORM框架,同时除去增强逻辑,实现事务管理,以了解spring事务的工作原里

* MyDataSourceTransactionManager
> 自定义事务管理逻辑
> * 开启事务
> * 提交事务
> * 回滚事务
> * 释放数据库连接

* DatasourceUtils
> 数据库连接封装类,兼容有无事务两种方式

* MyJdbcTemplate
> 自定义ORM框架,实现简单的SQL封装

![公众号](https://github.com/coderworld968/spring-transactional-orm/blob/master/log.png)