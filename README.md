# Schedule_management

## API 명세서
### Schedules
|기능|Method|URL|Request|Response|상태코드|
|----|---|---|---|---|---|
|일정 생성|`POST`|/api/scedules|요청 body|등록 정보|200: 정상 등록, 400: 비정상 값|
|전체 일정 조회|`GET`|/api/scedules| X |다건 응답 정보|200: 정상 조회|
|선택 일정 조회|`GET`|/api/scedules/{schedule_id}| X |단건 응답 정보|200: 정상 조회, 404: 일정이 사라짐|
|선택 일정 수정|`PUT`|/api/scedules/{schedule_id}|요청 body|수정 정보|200: 정상등록, 400: 비정상 값, 404: 일정이 사라짐|
|선택 일정 삭제|`DELETE`|/api/scedules/{schedule_id}| X | X |204: 정상 등록, 404: 일정이 사라짐|

<details>
  <summary><b>일정 생성</b></summary>
    
- 기본정보
  
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>POST</td></td>
        <td >/api/schedules</td>
      </tr>
    </table>


- 예제

    - 요청: POST /api/schedules
   
      ```json
      {
          "user_uid":"9788896a-96a4-11ef-a978-005056c00001",
          "title" : "과제",
          "content" : "꼭 해야함",
          "color" : "RED"
      }
      ```

    - 응답
      
       HTTP/1.1 200 OK
      
       ```json
      {
          "schedul_id":"1"
      }
      ```

- 본문
  
  - 요청
    
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>user_uid</td>
          <td>String</td>
          <td>유저 UID</td>
          <td> O </td>
        </tr>
        <tr>
          <td>title</td>
          <td>String</td>
          <td>일정 제목</td>
          <td> O </td>
        </tr>
        <tr>
          <td>content</td>
          <td>String</td>
          <td>일정 내용</td>
          <td> X </td>
        </tr>
        <tr>
          <td>color</td>
          <td>String</td>
          <td>일정에 나타낼 색깔</td>
          <td> X </td>
        </tr>
      </table>

  - 응답
    
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>schedul_id</td>
          <td>String</td>
          <td>스케쥴의 ID</td>
          <td> O </td>
        </tr>
    </table>
  
    
</details>

<details>
  <summary><b>전체 일정 조회 </b></summary>

- 기본정보
  
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>GET</td></td>
        <td >/api/scedules</td>
      </tr>
    </table>


- 예제

  - 요청 : X

  - 응답
 
    HTTP/1.1 200 OK
    
    ```json
    "schedules" :[ {
        "schedule_id": "1",
        "user_uid": "9788896a-96a4-11ef-a978-005056c00001",
        "title" : "과제1",
        "content" : "꼭 해야함",
        "color" : "RED",
        "createdAt":"2024-10-30 18:51:53",
        "updatedAt":"2024-10-30 18:51:53"
    }, {
            "id": "2",
            "user_uid": "9788896a-96a4-11ef-a978-005056c00001",
            "title" : "과제2",
            "content" : "나중에 해야함",
            "color" : "BLUE",
            "createdAt":"2024-10-30 18:52:53"
            "updatedAt":"2024-10-30 18:52:53"
        },
    ]
    ```

- 본문
  
  - 요청 : X

  - 응답
  
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>schedule_id</td>
          <td>String</td>
          <td> 일정의 ID </td>
          <td> O </td>
        </tr>
        <tr>
          <td>user_uid</td>
          <td>String</td>
          <td> 유저 UID </td>
          <td> O </td>
        </tr>
        <tr>
          <td>title</td>
          <td>String</td>
          <td>일정 제목</td>
          <td> O </td>
        </tr>
        <tr>
          <td>content</td>
          <td>String</td>
          <td>일정 내용</td>
          <td> X </td>
        </tr>
        <tr>
          <td>color</td>
          <td>String</td>
          <td>일정에 나타낼 색깔</td>
          <td> X </td>
        </tr>
        <tr>
          <td>createdAt</td>
          <td>String</td>
          <td>일정의 생성 날짜 TIMESTAMP</td>
          <td> O </td>
        </tr>
        <tr>
          <td>updatedAt</td>
          <td>String</td>
          <td>일정의 수정 날짜 TIMESTAMP</td>
          <td> X </td>
        </tr>
      </table>

    
</details>

<details>
  <summary><b>선택 일정 조회 </b></summary>

- 기본정보
  
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>GET</td></td>
        <td >/api/scedules/{schedule_id}</td>
      </tr>
    </table>


- 예제

  - 요청: GET /api/schedules/{schedule_id}

  - 응답
 
    HTTP/1.1 200 OK

    ```json
     {
        "schedule_id": "1",
        "user_uid": "9788896a-96a4-11ef-a978-005056c00001",
        "title" : "과제1",
        "content" : "꼭 해야함",
        "color" : "RED",
        "createdAt":"2024-10-30 18:51:53",
        "updatedAt":"2024-10-30 18:51:53"
    }
    ```
- 본문
  
  - 요청 
      
      <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td> 필수 </td>
        </tr>
        <tr>
          <td>schedule_id</td>
          <td>String</td>
          <td> 일정 id </td>
          <td> O </td>
        </tr>
      </table>

  - 응답
    
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td> O </td>
        </tr>
        <tr>
          <td>schedule_id</td>
          <td>String</td>
          <td> 일정 id </td>
          <td> O </td>
        </tr>
        <tr>
          <td>user_uid</td>
          <td>String</td>
          <td> 유저 UID </td>
          <td> O </td>
        </tr>
        <tr>
          <td>title</td>
          <td>String</td>
          <td>일정 제목</td>
          <td> O </td>
        </tr>
        <tr>
          <td>content</td>
          <td>String</td>
          <td>일정 내용</td>
          <td> X </td>
        </tr>
        <tr>
          <td>color</td>
          <td>String</td>
          <td>일정에 나타낼 색깔깔</td>
          <td> X </td>
        </tr>
        <tr>
          <td>createdAt</td>
          <td>String</td>
          <td>일정의 생성 날짜 timestamp</td>
          <td> O </td>
        </tr>
        <tr>
          <td>updatedAt</td>
          <td>String</td>
          <td>일정의 수정 날짜 timestamp</td>
          <td> X </td>
        </tr>
      </table>

    
</details>

<details>
  <summary><b>선택 일정 수정 </b></summary>

- 기본정보
  
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>GET</td></td>
        <td >/api/scedules/{schedule_id}</td>
      </tr>
    </table>


- 예제

  - 요청: PUT /api/schedules/{schedule_id}
  
    ```json
       {
          "schedule_id": "1",
          "user_uid": "9788896a-96a4-11ef-a978-005056c00001",
          "title" : "과제1",
          "content" : "꼭 해야함",
          "color" : "Red"
      }
    ```
  - 응답

    HTTP/1.1 200 OK

    ```json
     {
        "schedul_id":"1"
      }
    ```

- 본문

  - 요청 
    
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td> O </td>
        </tr>
        <tr>
          <td>schedule_id</td>
          <td>String</td>
          <td> 일정 id </td>
          <td> O </td>
        </tr>
        <tr>
          <td>user_uid</td>
          <td>String</td>
          <td> 유저 UID </td>
          <td> O </td>
        </tr>
        <tr>
          <td>title</td>
          <td>String</td>
          <td>일정 제목</td>
          <td> O </td>
        </tr>
        <tr>
          <td>content</td>
          <td>String</td>
          <td>일정 내용</td>
          <td> X </td>
        </tr>
        <tr>
          <td>color</td>
          <td>String</td>
          <td>일정에 나타낼 색깔</td>
          <td> X </td>
        </tr>
      </table>
  
  - 응답
    
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>schedul_id</td>
          <td>String</td>
          <td>스케쥴의 ID</td>
          <td> O </td>
        </tr>
    </table>

</details>

<details>
  <summary><b>선택 일정 삭제 </b></summary>

- 기본정보
  
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>GET</td></td>
        <td >/api/scedules/{schedule_id}</td>
      </tr>
    </table>

- 예제

  - 요청: DELETE /api/schedules/{schedule_id}
 
  - 응답
 
    HTTP/1.1 200 OK

    ```json
     {
        "schedul_id":"1"
      }
    ```
- 본문
    
  - 요청
      
      <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>schedule_id</td>
          <td>String</td>
          <td> 일정 id </td>
          <td> O </td>
        </tr>
      </table>
  
  - 응답
 
     <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>schedule_id</td>
          <td>String</td>
          <td> 일정 id </td>
          <td> O </td>
        </tr>
      </table>

    
</details>

### Users
|기능|Method|URL|Request|Response|상태코드|
|---|---|---|---|---|---|
|유저 등록|`POST`|/api/users|요청 body|등록 정보|200: 정상 등록, 400: 비정상 값|
|유저 정보 조회|`GET`|/api/users/{user_id}| X |단건 응답 정보|200: 정상 조회, 404: 일정이 사라짐|
|유저 수정|`PUT`|/api/users/{user_id}|요청 body|수정 정보|200: 정상등록, 400: 비정상 값, 404: 일정이 사라짐|

<details>
  <summary><b>유저 등록</b></summary>
    
- 기본정보
  
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>POST</td></td>
        <td >/api/users</td>
      </tr>
    </table>


- 예제

  - 요청
    
    ```json
     {
        "user_id": "tyh343",
        "password": "tghf",
        "name": "kim",
        "email" : "kimsparta@sparta.co.kr"
    }
    ```

  - 응답
    
    HTTP/1.1 200 OK
    
    ```json
     {
        "uid": "9788896a-96a4-11ef-a978-005056c00001",
      }
    ```

- 본문
  
  - 요청
  
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>user_id</td>
          <td>String</td>
          <td>유저 ID</td>
          <td> O </td>
        </tr>
        <tr>
          <td>password</td>
          <td>String</td>
          <td>유저 PW</td>
          <td> O </td>
        </tr>
        <tr>
          <td>name</td>
          <td>String</td>
          <td>유저 이름</td>
          <td> O </td>
        </tr>
        <tr>
          <td>email</td>
          <td>String</td>
          <td>유저 EMAIL</td>
          <td> X </td>
        </tr>
      </table>

  - 응답
  
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>uid</td>
          <td>String</td>
          <td> 유저 UID</td>
          <td> O </td>
        </tr>
      </table>

    
</details>

<details>
  <summary><b>유저 정보 조회</b></summary>
    
- 기본정보
  
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>GET</td></td>
        <td >/api/users/{user_Id}</td>
      </tr>
    </table>


- 예제

  - 요청 : GET /api/schedules/{user_id}

  - 응답
    
    HTTP/1.1 200 OK
    
    ```json
     {
        "user_id": "tyh343",
        "name": "kim",
        "email" : "kimsparta@sparta.co.kr"
    }
    ```

- 본문

  - 요청
    
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>user_id</td>
          <td>String</td>
          <td> 유저 id </td>
          <td> O </td>
        </tr>
      </table>

  - 응답
    
    <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>id</td>
          <td>String</td>
          <td>유저 ID</td>
          <td> O </td>
        </tr>
        <tr>
          <td>name</td>
          <td>String</td>
          <td>유저 이름</td>
          <td> O </td>
        </tr>
        <tr>
          <td>email</td>
          <td>String</td>
          <td>유저 EMAIL</td>
          <td> X </td>
        </tr>
      </table>

    
</details>

<details>
  <summary><b>유저 수정</b></summary>
    
- 기본정보
  
    <table>
      <tr>
        <td ><b>메소드</b></td>
        <td ><b>요청 URL</b></td>
      </tr>
      <tr>
        <td>PUT</td></td>
        <td >/api/users/{user_id}</td>
      </tr>
    </table>


- 예제

  - 요청
    
    ```json
     {
        "name": "kim",
        "email" : "kimsparta@sparta.co.kr"
    }
    ```

  - 응답
 
    HTTP/1.1 200 OK
 
    ```json
     {
        "uid": "9788896a-96a4-11ef-a978-005056c00001",
    }
    ```

- 본문

  - 요청 : PUT /api/schedules/{user_id}
  
      <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>name</td>
          <td>String</td>
          <td>유저 이름</td>
          <td> x </td>
        </tr>
        <tr>
          <td>email</td>
          <td>String</td>
          <td>유저 EMAIL</td>
          <td> x </td>
        </tr>
      </table>

  - 응답
  
      <table>
        <tr>
          <td><b>이름</b></td>
          <td><b>타입</b></td>
          <td><b>설명</b></td>
          <td><b>필수</b></td>
        </tr>
        <tr>
          <td>uid</td>
          <td>String</td>
          <td>유저 UID</td>
          <td> O </td>
        </tr>
      </table>

    
</details>


---

## ERD

![image](https://github.com/user-attachments/assets/8cdeaef2-1212-4b9d-926e-b97402cec4d8)



---

## SQL

#### 1. 테이블 생성 (Create)

```sql
-- Create users table
CREATE TABLE users (
    uid char(36) NOT NULL PRIMARY KEY,
    id varchar(45) NOT NULL,
    name varchar(20) NOT NULL,
    email varchar(45),
    create_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP
);

-- Create schedules table
CREATE TABLE schedules (
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_uid char(36) NOT NULL,
    title varchar(20) NOT NULL,
    content varchar(100),
    color varchar(20),
    create_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
)

```

#### 2. 유저 생성 및 일정 생성 (Insert)

```sql
-- Insert users
INSERT INTO user (uid, id, password, name, email, create_date)
VALUES (UUID(), "tsd123", "tyghbn","kim", "sparta@teamsparta.co.kr", current_timestamp());

-- Insert schedules
INSERT INTO schedule (user_uid, title, content,color, create_date)
VALUES ("f953a76c-9732-11ef-a978-005056c00001","과제", "sql, erd","RED", current_timestamp());

-- Insert schedules without color
INSERT INTO schedule (user_uid, title, content, create_date)
VALUES ("f953a76c-9732-11ef-a978-005056c00001","과제", "sql, erd", current_timestamp());


)

```

#### 3. 전체 일정 조회 (Select)

```sql
-- Select schedules
SELECT * FROM schedules;
)

```

#### 4. 선택 일정 조회 (Select)

```sql
-- Select schedules with id 1
SELECT * FROM schedules WHERE id = 1;
)

```

#### 5. 선택 일정 수정 및 유저 수정 (Update)

```sql
-- Update schedules with id 1
UPDATE schedules SET color = "BLUE", update_date = current_timestamp() WHERE id = 1;


-- Update schedules with id 2
UPDATE schedules SET title = "important subject", update_date = current_timestamp() WHERE id = 2;


-- Update users with uid "9788896a-96a4-11ef-a978-005056c00001"
UPDATE users SET name = "taehuyn", update_date = current_timestamp() WHERE uid = "9788896a-96a4-11ef-a978-005056c00001";


```

#### 6. 선택 일정 삭제  (Delete)

```sql
-- Delete schedules with id 1
DELETE FROM schedules WHERE id = 1;

-- Update schedules with id 2
UPDATE schedules SET title = "important subject" WHERE id = 2;
)
```


