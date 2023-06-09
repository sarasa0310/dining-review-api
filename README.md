# 다이닝 리뷰 API 프로젝트 🍰

### 개요 ✨
- 레스토랑의 맛, 가격, 서비스를 평가하는 다이닝 리뷰 API 입니다.
- **Java 11**과 **Spring Boot 2.7.12**을 기반으로 하고 있습니다.
- **Codecademy** 사이트의 [**Create REST APIs with Spring and Java**](https://www.codecademy.com/learn/paths/create-rest-apis-with-spring-and-java) 코스의 포트폴리오 프로젝트 과제를 기반으로 하고 있습니다. 
- 다만, 주제와 관련하여 약간의 변형(리뷰 항목 변경 등)을 가하였습니다.

<br/>

### 주요 기능 🤖
1. 회원은 레스토랑의 맛, 가격, 서비스를 각각 1점에서 5점 사이의 점수로 평가하여 제출할 수 있고, 특정 레스토랑의 승인된 다이닝 리뷰를 조회할 수도 있습니다.
2. 관리자는 승인 대기중인 다이닝 리뷰를 조회하고(다이닝 리뷰는 대기중, 승인됨, 거절됨 상태를 가짐), 승인 or 거절할 수 있습니다.
3. 다이닝 리뷰가 관리자에 의해 승인되면 이벤트가 발생하고, 해당 다이닝 리뷰가 작성된 레스토랑의 점수가 자동으로 업데이트됩니다.
4. 신규 레스토랑을 등록하고, 정보도 조회할 수 있으며, 우편번호를 통해 적어도 한 번 이상 평가받은 레스토랑 목록도 찾을 수 있습니다.
5. 평균 점수가 높은 레스토랑 순서대로 조회도 가능합니다.
