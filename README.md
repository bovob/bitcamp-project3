# 📖 MBTI 취향에 맞춘 도서대출

- Git : https://github.com/SA030/bitcamp-project3.git
- Account
  <br>|  ID  | P/W |  비고  |
  | :--: | :--: | :----: |
  | root | 0000 | 관리자 |
  | user | 0000 |  유저  |

<br>

## 1.프로젝트 소개

### 프로젝트 명 : MBTI BOOK

### 프로젝트 개발 기간 : 2024-07-11 ~ 2024-07-17

### 소개

- MBTI BOOK은 사람들이 자신의 MBTI 취향에 따라 책을 추천받고 대출받는 프로그램입니다.
- 회원가입시 간단한 MBTI 테스트를 통하여 본인의 MBTI를 확인할 수 있습니다.
- 유저는 현재 도서관의 책 목록을 확인하며 본인의 MBTI에 맞추어 추천도서가 출력됩니다.
- 관리자는 카테고리별 도서를 등록하여 해당 도서의 추천 MBTI 태그를 추가할 수 있습니다.

## 2.팀원 구성

<div align="center">

| **이재욱** | **이선아** |
| :--------: | :--------: |
|  |  |

</div>
<br>

## 3.개발 환경

<img src="https://img.shields.io/badge/Java-007396?style=flastic&logo=OpenJDK&logoColor=white"/> 
<img src="https://img.shields.io/badge/IntelliJ-000000?style=flastic&logo=intellijidea&logoColor=white"/>

## 4.역할분담

### 이재욱

* **관리자** : 도서등록, 대출관리
* **유저** : 대출, 반납

### 이선아

- **관리자** : 유저관리
- **유저** : 로그인 전반, MBTI 검사

## 5.프로젝트 구조

``````
📦app
 ┃ ┣ 📂libs
 ┃ ┃ ┗ 📜app.jar
 ┣ 📂doc
 ┃ ┗ 📜mbtiTest.txt
 ┣ 📂src
 ┃ ┗ 📂main
 ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┗ 📂bitcamp
 ┃ ┃ ┃ ┃ ┗ 📂project3
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BookCommand.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BorrowCommand.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Command.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MbtiCommand.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReturnCommand.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserCommand.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂Monitor
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminMonitor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Membership.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Monitor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserMonitor.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂util
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GetHtml.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MenuFormat.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Prompt.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SystemMsg.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TableFormat.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Book.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Borrow.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜App.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜DummyData.java
 ┗ 📜build.gradle
``````

## 6.기능

## 7.후기

### 이재욱

이전 프로젝트 들에서 사용하던 CRUD 를 구현하는 코드들에 익숙해지며 싱글톤 패턴을 이용한 인스턴스 생성 이후 객체생성 이후 각 필요 클래스에 전달하는 부분에 있어서 고생을 많이 했습니다.
User와 Admin가 같이 사용하는 도서 리스트와 대출 리스트를 나눌 때 각각 인스턴스를 구현했다가 막상 두 시스템을 합치고 나니 어느 리스트에 값이 전달되었는지 구분이 되지 않아 결국 한번 구조를 정리해보게 되었습니다.
앞으로는 위와 같은 실수를 반복하지 않기 위하여 좀 더 각 객체간의 구조나 각 클래스가 필요로 하는 파라미터를 명시하도록 해야겠습니다.

