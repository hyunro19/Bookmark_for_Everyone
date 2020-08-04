# 모두의 북마크 : 웹북마크 공유 게시판

### SpringBoot`(REST API)`, Vue.js`(SPA)`, Hibernate, AWS EC2·RDS 활용

<br>

<br>

### 🔖[AWS로 배포된 '모두의 북마크' 방문하기](http://52.79.157.226:8011)

### [📝Notion에서 프로젝트 보고서 읽기](https://www.notion.so/SpringBoot-Vue-js-Hibernate-AWS-EC2-RDS-2d9b1fc267914b75a64242e710072a10)

<br>

<br>

### 목차

1. 프로젝트 개괄
2. 시스템 구성 및 사용 기술

<br>

<br>

---

# 1. 프로젝트 개괄

- **1인** 프로젝트 (`서비스 기획` / `시스템 설계` / `개발 수행` / `디자인` / `보고서 작성` 등)

- 서비스 내용

    - 이용자간 자신의 웹북마크(즐겨찾기)를 공유하는 게시판
    
    - URL에 대한 오픈그래프를 읽어와 title, description, img 정보를 담은 썸네일로 시각화

    - 오픈그래프 정보가 정확하지 않을 경우 사용자가 임의로 수정하여 포스팅 가능
    
      <br>
    
    ![이미지](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fc8bd5cc6-44a7-4b9d-865b-5ba673461c91%2F_038.png?table=block&id=a21a1dbb-d498-45bf-a01e-34f22bbbb71b&width=2180&cache=v2)



<br>

<br>

# 2. 시스템 구성 및 사용기술

### 2.1 시스템 구성도

![시스템 구성도](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F1dd614f2-ac36-452d-a3f4-e81057ed29b2%2FBookmarkForEveryone_System_Structure_Upload.png?table=block&id=0a238cad-cf0a-4c1e-8924-26356a1563f2&width=3130&cache=v2)

- 프론트엔드
:  `Vue.js` (인증: JWT)
- AP서버
: `SpringBoot`, `Hibernate` (배포:  `AWS EC2`)
- DBMS
: `MySQL` (배포:  `AWS RDS`)

<br>

－AP서버는 SpringBoot의 RestController를 이용하여 REST API형식으로 구현

－프론트엔드를 Vue.js를 이용한 클라이언트 사이드 렌더링으로 구현하였기 때문에 세션 인증이 아닌 JWT를 이용하여 사용자 인증 구현

－AP서버와 프론트엔드 모두 AWS EC2로 배포, DB역시 AWS의 RDS를 이용하여 MySQL로 구현·배포

－AP서버 객체와 DB 테이블 간의 연동은 ORM방식으로 Hibernate를 이용

---