- springboot 
- spring jpa
- kotlin

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