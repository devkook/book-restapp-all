# 머리말
* 아래 책을 따라 치고 배포하고 부하를 주면서 이리 저리 살펴 함께 공부하고자 합니다.
* 책의 내용과 같고 실배포하여 동작하는 버전은 v1.0.0-book 으로 tag 되었으며,
* 이후의 마이드스톤 개발내용을 점차 진행하며 추가 하겠습니다.

# 실행
* mvn package -Dmaven.test.skip=true
* *.war -> tomcat/webapps/ROOT.war
* 확인 http://url/books ( http://restapp.devkook.cloudbees.net/books )

![in_chrome](https://raw.githubusercontent.com/devkook/book-restapp-all/master/share/image/test_chrom_books.png)

# 테스트
* http://restapp.devkook.cloudbees.net/books


* http://restapp.devkook.cloudbees.net/books/1

# 책소개
* RESTful 웹 서비스 구축하기 : 실전 예제로 배우는 REST 방식의 스프링 웹 서비스, 2014, 김강우 ( PDF 구매 및 맛보기 : http://www.hanbit.co.kr/ebook/look.html?isbn=9788968486890 )

eBook & DRM-free
RESTful 웹 서비스를 구축하고 싶은 개발자를 위한 실무 가이드

![alt 스프링을 이용한 RESTful 웹 서비스 구축하기 : 실전 예제로 배우는 REST 방식의 스프링 웹 서비스](http://image.hanbit.co.kr/ebookcover/m_9788968486890.gif)

『스프링을 이용한 RESTful 웹 서비스 구축하기』는 자바나 스프링을 사용해 본 개발자를 대상으로 REST의 기본 개념을 소개하고 Spring 3.2를 이용하여 REST 방식의 웹 서비스를 구축하는 방법을 소개한다. 
도서 정보를 처리하는 실전 예제를 중심으로 JavaConfig를 이용하여 스프링 구성 방법, Spring Web MVC를 이용하여 웹 애플리케이션 개발 방법, MockMvc를 이용하여 Controller 테스트하기 등 실무에서 활용 가능한 유익한 정보를 알려준다. 스프링을 알거나 사용해본 개발자라면 이 책을 통해 REST 방식의 특징과 RESTful한 웹 서비스 구축 방법을 쉽고 빠르게 익힐 수 있다. 
또한, 이 책에는 저자가 10년 넘게 SI 업체에서 다양한 프로젝트를 수행하면서 실무에 적용하고 테스트하여 쌓은 노하우와 지식이 녹아있으므로 스프링을 이용하여 RESTful한 웹 서비스 구축 방법을 빠르게 익히고 싶은 개발자에게 많은 도움이 될 것이다.

목차

```
1장. 들어가기
  1.1 개요
  1.2 REST
  1.3 Spring Web MVC

2장. Spring 3.2와 REST
  2.1 요구사항 정의
  2.2 개발 환경
  2.3 개발 환경 구축하기
  2.4 어플리케이션 구조
  2.5 요약

3장. Persistence Layer
  3.1 영속성 계층이란?
  3.2 DAO
  3.3 Mapper 구현하기
  3.5 요약

4장. Business Layer
  4.1 비지니스 계층이란?
  4.2 트랜잭션 관리
  4.3 Service 구현하기
  4.4 요약

5장. Presentation Layer
  5.1 표현 계층이란?
  5.2 ModelAndView와 HTTPMessageConverter
  5.3 URI Template
  5.4 Controller 구현하기
  5.5 JSON
  5.6 XML
  5.7 Content Negotiation
  5.8 HTTP Method Conversion
  5.9 ETag support
  5.10 기타
  5.11 요약

6장. 예외 처리
  6.1 Controller의 예외 처리
  6.2 예외 구현하기
  6.3 요약

7장. HATEOAS
  7.1 Spring HATEOAS
  7.2 요약

8장. REST Client
  8.1 RestTemplate
  8.2 URI Template
  8.3 ClientHttpRequestFactory
  8.4 RestTemplate 예제
  8.5 요약
```

# CloudBees
![apps_architecture](https://raw.githubusercontent.com/devkook/book-restapp-all/master/share/image/cloudbees_AppsPortal.png)

![report](https://raw.githubusercontent.com/devkook/book-restapp-all/master/share/image/cloudbees_report.png)