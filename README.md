# omgg

[![EC2 Status](https://img.shields.io/badge/EC2-success-blue?logo=Amazon%20AWS&style=for-the-badge)](https://aws.amazon.com/ko/ec2/?nc2=type_a&ec2-whats-new.sort-by=item.additionalFields.postDateTime&ec2-whats-new.sort-order=desc)
[![RDS DBMS engine](https://img.shields.io/badge/RDS-MySQL-9cf?logo=Amazon%20AWS&style=for-the-badge)](https://aws.amazon.com/ko/rds/?nc2=type_a)

![메인화면-검색-시연](https://user-images.githubusercontent.com/24715292/117240401-afda8700-ae6b-11eb-8739-304838e1ddec.gif)

omgg는 리그 오브 레전드의 전적 검색 데이터를 제공해주는 웹사이트입니다.  
이 [링크](http://ec2-18-116-207-31.us-east-2.compute.amazonaws.com:8080)에서직접 확인해보세요!

## Environment

+ JDK 11.0.6
+ Spring boot 2.3.7.RELEASE
+ gradle 6.7.1
+ MySQL 8.0.20

## Installation

### Source Download

```bash
> git clone https://github.com/eari100/omgg.git
```

### application.yml

`omgg/src/main/resources/` 경로에 application.yml 파일을 만들고 아래와 같이 작성합니다.

```yml
spring:
  profiles:
    active: dev-h2

---

spring:
  profiles: dev-h2
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE
  h2:
    console:
      enabled: true
  livereload:
    enabled: true
  thymeleaf:
    cache: false

dialect: MySQL5InnoDBDialect

logging:
  level:
    org:
      hibernate:
        type:
          descriptor:
            sql: trace

```

### Riot API key 발급 받기

전적 데이터는 라이엇에서 제공하는 REST API를 이용하여 데이터를 가져옵니다.   
API를 사용하기 위해서는 API key를 발급받아야 됩니다.  

![Riot-Caution](https://user-images.githubusercontent.com/24715292/117745741-0ec24680-b246-11eb-8148-1b6a3e055c16.png)

Riot은 API key를 타인과 공유를 금지하기 때문에 작성자의 키를 첨부할 수 없었습니다.  
번거롭더라도 아래의 가이드대로 진행해주세요.

1. [Riot Developer Portal](https://developer.riotgames.com/)에 접속한 후 로그인을 합니다.

![API-key-issued-01](https://user-images.githubusercontent.com/24715292/117746481-775df300-b247-11eb-9e63-efca063f4e34.png)

2. 로그인이 되면 메인 페이지에서 Development API KEY 항목에서 발급된 키를 확인하실 수 있습니다.

![API-key-issued-02](https://user-images.githubusercontent.com/24715292/117746753-dde31100-b247-11eb-9c9c-90d97c340b8e.png)

Development API Key를 사용해도 문제는 없으나 24시간마다 발급받아야 되는 번거로움이 있습니다.   
장기적으로 프로젝트에 기여하실 분들은 **Personal API key**를 사용하시길 추천드립니다.  

3. 메인 페이지에서 Register Product 버튼을 클릭합니다.

![API-key-issued-03](https://user-images.githubusercontent.com/24715292/117747271-cf492980-b248-11eb-9776-184819f71b2d.png)

4. Personal API key 항목의 Register Product 버튼을 클릭합니다.

![API-key-issued-04](https://user-images.githubusercontent.com/24715292/117747415-17684c00-b249-11eb-9002-17018b8ad780.png)

5. 동의 버튼을 클릭합니다.

![API-key-issued-05](https://user-images.githubusercontent.com/24715292/117747548-58606080-b249-11eb-9605-89dccb6fa20d.png)

6. API Key를 사용할 소프트웨어의 정보들을 작성하라는 항목이 나옵니다. 내용을 작성한 후 Submit 버튼을 클릭합니다.
배포한 소프트웨어가 없어도 해당 내용을 성실히 작성하면 문제없이 키를 발급해줍니다. 보통 발급되는 데 2~4일 정도 걸립니다.

![API-key-issued-06](https://user-images.githubusercontent.com/24715292/117747700-9e1d2900-b249-11eb-9b78-d924533f4b0c.png)

### Key.java 작성하기

등록 받은 API KEY를 사용하는 Key 클래스를 작성해주면 소스코드 내에서 Riot REST API를 사용할 준비가 끝납니다.

```JAVA
package gg.om.omgg.api.riot;

public class Key {
    public static final String api_key = "(발급받은 키)";

}
```

## Run

```bash
> ./gradlew bootRun
```
localhost:8080으로 접속 가능합니다.

## Build & Deploy

```bash
> cd omgg
> ./gradlew build
> > cd build/libs
> java -jar omgg-0.0.1-SNAPSHOT.jar
```

## Library used

### back-end

+ spring data jpa 2.3.7
+ QueryDSL 4.3.1
+ junit 4.13.1
+ jackson 2.11.3
+ apache httpcomponents httpclient 4.5.13

### front-end
+ jquery 3.5.1
+ thymeleaf 2.3.7

## Contributing

기여는 항상 환영합니다. 프로젝트를 발전 시켜주세요.

## License

재배포 및 교육적, 상업적 목적으로 사용하실 수 있습니다. 마음껏 가져가세요!
