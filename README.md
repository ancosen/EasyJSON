## <a name='TOC'>Table of Contents</a>

  1. [EasyJSON](#EasyJSON)
  1. [Actual State](#State)
  1. [Build State](#BuildState)
  1. [Examples](#Examples)
  1. [Testing](#Testing)
  1. [Thanks](#Thanks)
  1. [License](#License)

## <a name='EasyJSON'>EasyJSON</a>

EasyJSON is a Java Serializer/Deserializer developed to build and to manage JSON Object in easy way.

## <a name='State'>Actual State</a>

At the moment EasyJSON is only an encoder written in JAVA. The steps of the project are:

- Develop a JSON Serializer (Done, need more features)
- Add/Develop a JSON Deserializer (Done)
- Develop a JSON Validator

This is a really base version, there is so much work to do.

## <a name='BuildState'>Build State</a>

[![Build Status](https://travis-ci.org/ancosen/EasyJSON.png?branch=dev)](https://travis-ci.org/ancosen/EasyJSON)

## <a name='Examples'>Examples</a>

Build a JSON object with EasyJSON is very simple. Take a look at this simple example:

```java
		StringWriter out = new StringWriter();
		List p = new ArrayList();
		for (int i = 0; i < 10; i++) {
			p.add(new Long(i));
		}

		Collection c = new ArrayList();
		String p1 = "Example";
		for (int f = 0; f < 5; f++) {
			c.add(p1);
		}

		Map obj1 = new HashMap();
		obj1.put("name", "foo");
		obj1.put("num", new Long(100));
		obj1.put("balance", new BigDecimal(1000.21));
		obj1.put("list", p);

		LinkedHashMap obj2 = new LinkedHashMap();
		obj2.put("is_vip", new Boolean(true));
		obj2.put("nickname", "anthony");
		obj2.put("anotherlist", c);
		obj2.putAll(obj1);

		JSONSerializer.toJSONString(obj2, out);
```

in the StringWriter out object we have the following JSON string:

```java
{
    "is_vip": true,
    "nickname": "anthony",
    "anotherlist": [
        "Example",
        "Example",
        "Example",
        "Example",
        "Example"
    ],
    "balance": 1000.21,
    "num": 100,
    "name": "foo",
    "list": [
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9
    ]
}
```

Using an OutputStreamWriter it's also possible to write the JSON string on File

```java
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile7.json"),"UTF-8");
		JSONSerializer.toJSONString(obj2, fileout);
```

## <a name='Testing'>Testing</a>

In the Junit testing some useful example are provided. Please if you notice bugs or errors, report as soon as possible.

## <a name='Thanks'>Thanks</a>

Many thanks to @ralfstx for his JSON Parser in his project https://github.com/ralfstx/minimal-json. It's very fast.

## <a name='License'>License</a>

Copyright 2013 Andrea Cosentino and Contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

