# 📖 MBTI 취향에 맞춘 도서대출

- Git : https://github.com/SA030/bitcamp-project3.git
- Account
  <br>
  
  |  ID  | P/W |  비고  |
  | :--: | :--: | :----: |
  | root | 0000 | 관리자 |
  | user | 0000 |  유저  |
  
<br>

## 1. 프로젝트 소개

### 프로젝트 명 : MBTI BOOK

### 프로젝트 개발 기간 : 2024-07-11 ~ 2024-07-17

### 소개

- MBTI BOOK은 사람들이 자신의 MBTI 취향에 따라 책을 추천받고 대출받는 프로그램입니다.
- 회원가입시 간단한 MBTI 테스트를 통하여 본인의 MBTI를 확인할 수 있습니다.
- 유저는 현재 도서관의 책 목록을 확인하며 본인의 MBTI에 맞추어 추천도서가 출력됩니다.
- 관리자는 카테고리별 도서를 등록하여 해당 도서의 추천 MBTI 태그를 추가할 수 있습니다.
<br>

## 2. 팀원 구성

<div align="center">

| **이재욱** | **이선아** |
| :--------: | :--------: |
| <img src="https://avatars.githubusercontent.com/u/66761864?v=4" height=150 width=150> <br/> [@bovob] |<img src="https://avatars.githubusercontent.com/u/170715429?v=4" height=150 width=150> <br/> [@SA030] |

</div>
<br>

## 3. 개발 환경

<img src="https://img.shields.io/badge/Java-007396?style=flastic&logo=OpenJDK&logoColor=white"/>  <img src="https://img.shields.io/badge/IntelliJ-000000?style=flastic&logo=intellijidea&logoColor=white"/>
<br>

## 4. 역할분담

### 이재욱

* **관리자** : 도서등록, 대출관리
* **유저** : 대출, 반납

### 이선아

- **관리자** : 유저관리
- **유저** : 로그인 전반, MBTI 검사
<br>

## 5. 프로젝트 구조

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
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Command.ja정

- 유저의 PW 및 MBTI를 재검사 할 수 있습니다.

![ISFPtoISTJ](https://github.com/user-attachments/assets/8e1f1784-6652-4fac-898b-a0dc2236058f)

### 6. 도서등록

- 관리자로 로그인합니다.
- 도서관리를 통하여 카테고리/도서 명/저자 명 을 입력받습니다.
- 도서수정은 도서번호를 입력받아 카테고리/도서 명/저자 명 및 추천 MBTI를 설정할 수 있습니다.
- 도서삭제는 도서번호를 입력받아 해당 도서를 삭제합니다.

![AdminBook](https://github.com/user-attachments/assets/f485aa13-92e7-461d-92a7-aeb0ab15fc08)

### 7. 도서대출 현황

- 관리자로 로그인합니다.
- 전체 도서의 대출목록을 출력합니다.

![AdminBorrow](https://github.com/user-attachments/assets/eba3d70e-db03-413f-8f86-e6596d4d55c6)

### 8. 유저관리

- 관리자로 로그인합니다.
- 전체 유저목록을 확인 할 수 있습니다.
- 유저번호를 입력하여 삭제할 수 있습니다.

![AdminUserdel](https://github.com/user-attachments/assets/8b1cb9d9-34da-4fef-ab54-c6b2451c9c15)


<br>

## 7. 후기

### 이재욱

이전 프로젝트 들에서 사용하던 CRUD 를 구현하는 코드들에 익숙해지며 싱글톤 패턴을 이용한 인스턴스 생성 이후 객체생성 이후 각 필요 클래스에 전달하는 부분에 있어서 고생을 많이 했습니다.
User와 Admin가 같이 사용하는 도서 리스트와 대출 리스트를 나눌 때 각각 인스턴스를 구현했다가 막상 두 시스템을 합치고 나니 어느 리스트에 값이 전달되었는지 구분이 되지 않아 결국 한번 구조를 정리해보게 되었습니다.
앞으로는 위와 같은 실수를 반복하지 않기 위하여 좀 더 각 객체간의 구조나 각 클래스가 필요로 하는 파라미터를 명시하도록 해야겠습니다.

