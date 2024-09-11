- springboot 
- spring jpa
- kotlin


# tips
> 请先替换application.yml中的mysql 和 redis配置
> application.yml 中配置了特殊的header 请自定义
>
>项目集成了openapi 请修改config/OpenApi的参数 
> 
> 如需阿里云短信 和 oss 请前往 https://github.com/loyaltly-cn/aliyun-sms ， https://github.com/loyaltly-cn/aliyun-oss 获取shell文件 
>
> 每个实体类在controller层基本都有4个api <br/>
> get post put delete <br/>
> 另外每个实体类都提供了一个 post ('**/query') 为动态分页查询 <br/>
> 使用此接口所有查询结果都是id 倒序 <br>
> body不传值 默认查询表中前1000条数据 <br>

```json
{
      "data":{
        //要什么字段的数据直接传进去就行
        "name": "loyal",
        "phone": "xxxxxxx"
    },
    "page":0, //查询的页数
    "number":1000 //查询的数量
}
```


