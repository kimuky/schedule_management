# Schedule_management

## API 명세서

### Schedules
|기능|Method|URL|Request|Response|상태코드|
|---|---|---|---|---|---|
|테스트1|테스트2|테스트3|테스트1|테스트2|테스트3|
|테스트1|테스트2|테스트3|테스트1|테스트2|테스트3|
|테스트1|테스트2|테스트3|테스트1|테스트2|테스트3|

### Schedules
|기능|Method|URL|Request|Response|상태코드|
|---|---|---|---|---|---|
|테스트1|테스트2|테스트3|테스트1|테스트2|테스트3|
|테스트1|테스트2|테스트3|테스트1|테스트2|테스트3|
|테스트1|테스트2|테스트3|테스트1|테스트2|테스트3|


## ERD

![image](https://github.com/user-attachments/assets/2c2f5c5c-755d-40b7-a394-1a88c5c28dac)




## SQL

#### 1. 테이블 생성 (Create)

```sql
-- Create users table
CREATE TABLE users (
    id char(36) NOT NULL PRIMARY KEY,
    name varchar(20) NOT NULL,
    email varchar(45),
    create_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP
);

-- Create schedules table
CREATE TABLE schedules (
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id char(36) NOT NULL,
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
INSERT INTO users (id, name, email, create_date)
VALUES (UUID(),"kim", "sparta@teamsparta.co.kr", current_timestamp());

-- Insert schedules
INSERT INTO schedule (user_id, title, content,color, create_date)
VALUES ("9788896a-96a4-11ef-a978-005056c00001","과제", "sql, erd","red", current_timestamp());

-- Insert schedules without color
INSERT INTO schedule (user_id, title, content, create_date)
VALUES ("9788896a-96a4-11ef-a978-005056c00001","코드카타", "dfs, bfs", current_timestamp());


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
UPDATE schedules SET color = "blue" WHERE id = 1;
)

-- Update schedules with id 2
UPDATE schedules SET title = "important subject" WHERE id = 2;
)

-- Update users with id 9788896a-96a4-11ef-a978-005056c00001
UPDATE users SET name = "taehuyn" WHERE id = "9788896a-96a4-11ef-a978-005056c00001";
)

```

#### 6. 선택 일정 삭제  (Delete)

```sql
-- Delete schedules with id 1
DELETE FROM schedules WHERE id = 1;

-- Update schedules with id 2
UPDATE schedules SET title = "important subject" WHERE id = 2;
)
```


