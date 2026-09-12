# Java JDBC CRUD – PostgreSQL

A simple Java JDBC project that demonstrates **CRUD operations with PostgreSQL** using `Connection`, `PreparedStatement`, `ResultSet`, `try-with-resources`, and a repository-style architecture.

> **Learning project:** This module is intentionally focused on understanding core JDBC concepts before moving to frameworks such as Spring Boot / Spring Data JPA.

## ✨ Features

* Create a student record
* Read a student by ID
* Read all students
* Update a student
* Delete a student
* Map PostgreSQL rows to Java `Student` objects
* Use `PreparedStatement` for parameterized SQL
* Use try-with-resources for automatic resource management
* Demonstrate the generic `PreparedStatement.execute()` approach

## 🛠️ Tech Stack

| Technology   | Version / Details        |
| ------------ | ------------------------ |
| Java         | 23                       |
| Build Tool   | Maven                    |
| Database     | PostgreSQL               |
| JDBC Driver  | PostgreSQL JDBC `42.7.2` |
| Architecture | Model + Repository       |

## 📁 Project Structure

```text
javajdbc/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── ravi/
                    ├── Main.java
                    ├── model/
                    │   └── Student.java
                    └── repository/
                        └── StudentRepository.java
```

## 🧱 Architecture

```text
Main
  │
  ▼
StudentRepository
  │
  ├── DriverManager
  ├── Connection
  ├── PreparedStatement
  └── ResultSet
          │
          ▼
     PostgreSQL Database
```

### `Student`

The model contains:

* `id` – `Long`
* `name` – `String`
* `email` – `String`
* `age` – `int`

### `StudentRepository`

The repository contains methods for:

* Create
* Read by ID
* Read all
* Update
* Delete
* `mapRow()` conversion from `ResultSet` to `Student`

## 🗄️ Database Setup

Create a PostgreSQL database and a `students` table.

```sql
CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    age INTEGER CHECK (age >= 0)
);
```

### Sample Data

```sql
INSERT INTO students (name, email, age)
VALUES
    ('Ravikishan', 'ravi@kishan.com', 17),
    ('Ankit Kumar', 'ankit@ankit.com', 23);
```

## 🔐 Configure Database Connection

Update the values in `StudentRepository.java`:

```java
String url = "YOUR_DATABASE_URL";
String username = "YOUR_USERNAME";
String password = "YOUR_PASSWORD";
```

For Neon PostgreSQL, use the JDBC connection details supplied by Neon.

> **Security:** Never commit real database passwords or private connection strings to GitHub. Prefer environment variables or external configuration.

## ▶️ Run the Project

### 1. Clone Repository

```bash
git clone https://github.com/raviranjancs/spring-boot.git
cd spring-boot/javajdbc
```

### 2. Build

```bash
mvn clean compile
```

### 3. Run

Run:

```text
com.ravi.Main
```

from your IDE.

## 🔄 CRUD Operations

### Create

```java
studentRepository.createStudent(
    new Student("Ravikishan", "ravi@kishan.com", 17)
);
```

SQL:

```sql
INSERT INTO students(name, email, age)
VALUES (?, ?, ?);
```

### Read by ID

```java
studentRepository.getStudentById(6L);
```

SQL:

```sql
SELECT * FROM students WHERE id = ?;
```

### Read All

```java
studentRepository.getStudent();
```

SQL:

```sql
SELECT * FROM students;
```

### Update

```java
studentRepository.updateStudent(
    new Student("Ankit Kumar", "ankit@ankit.com", 23),
    4L
);
```

SQL:

```sql
UPDATE students
SET name = ?,
    email = ?,
    age = ?
WHERE id = ?;
```

### Delete

```java
studentRepository.deleteStudent(6L);
```

SQL:

```sql
DELETE FROM students WHERE id = ?;
```

## 🧠 JDBC Concepts

### `Connection`

Creates communication between the Java application and PostgreSQL.

### `PreparedStatement`

Executes parameterized SQL:

```java
PreparedStatement ps = connection.prepareStatement(sql);
```

Parameters are supplied using:

```java
ps.setString(1, value);
ps.setInt(2, value);
ps.setLong(3, value);
```

### `executeQuery()`

Used mainly for `SELECT` operations.

Returns:

```java
ResultSet
```

### `executeUpdate()`

Used for:

```text
INSERT
UPDATE
DELETE
```

Returns the number of affected rows.

### `execute()`

A general-purpose method that can execute SQL and indicate whether the result is a `ResultSet` or an update count.

### `ResultSet`

Used to read rows returned by a query:

```java
while (resultSet.next()) {
    // read current row
}
```

### `try-with-resources`

This project uses:

```java
try (
    Connection connection = ...;
    PreparedStatement preparedStatement = ...;
) {
    // database operation
}
```

Resources are automatically closed when the block finishes.

## 🔁 `mapRow()`

The repository converts a database row into a Java object:

```java
public Student mapRow(ResultSet resultSet) throws SQLException {
    Student student = new Student();

    student.setId(resultSet.getLong("id"));
    student.setName(resultSet.getString("name"));
    student.setEmail(resultSet.getString("email"));
    student.setAge(resultSet.getInt("age"));

    return student;
}
```

This keeps database-to-object mapping in one place.

## 📊 JDBC Method Comparison

| SQL Operation | JDBC Method       | Result        |
| ------------- | ----------------- | ------------- |
| SELECT        | `executeQuery()`  | `ResultSet`   |
| INSERT        | `executeUpdate()` | affected rows |
| UPDATE        | `executeUpdate()` | affected rows |
| DELETE        | `executeUpdate()` | affected rows |
| Generic SQL   | `execute()`       | boolean       |

## 🧪 Generic CRUD Demo

The project contains `complateCRUD()` to demonstrate `PreparedStatement.execute()`.

For a `SELECT`:

```java
boolean result = preparedStatement.execute();

if (result) {
    ResultSet resultSet = preparedStatement.getResultSet();
}
```

For update-style SQL:

```java
int rowAffected = preparedStatement.getUpdateCount();
```

For everyday CRUD, prefer `executeQuery()` and `executeUpdate()` because they make the code easier to understand.

## ⚠️ Project Improvements

This is a good JDBC learning project, but several improvements would make it more production-ready:

### 1. Move Credentials Outside Source Code

Use environment variables instead of:

```java
String password = "YOUR_PASSWORD";
```

### 2. Return Data Instead of Printing

Instead of:

```java
System.out.println(student);
```

prefer:

```java
public Student getStudentById(Long id)
```

and:

```java
public List<Student> getStudents()
```

### 3. Avoid `SELECT *`

Prefer:

```sql
SELECT id, name, email, age
FROM students;
```

### 4. Improve Naming

Rename:

```java
complateCRUD()
```

to:

```java
completeCRUD()
```

### 5. Use Logging

For larger applications, use a logging framework instead of:

```java
System.out.println();
e.printStackTrace();
```

### 6. Ignore Maven Build Output

The Maven `target/` directory is generated output and normally should not be committed.

Example `.gitignore`:

```gitignore
target/
*.class
.idea/
.vscode/
```

### 7. Connection Pooling

A production application should generally use a connection pool instead of opening a new database connection for every repository method call.

## 📚 Learning Goals

After completing this project, you should understand:

* Java → PostgreSQL connection using JDBC
* CRUD SQL operations
* `PreparedStatement`
* `ResultSet`
* `executeQuery()`
* `executeUpdate()`
* `execute()`
* try-with-resources
* Exception handling
* Database row → Java object mapping
* Repository-layer architecture

## 🚀 Next Learning Path

```text
Core JDBC
   ↓
JDBC + Service Layer
   ↓
Connection Pool
   ↓
Spring JDBC / JdbcTemplate
   ↓
Spring Boot REST API
   ↓
Spring Data JPA / Hibernate
```
Project Notes: https://drive.google.com/file/d/10bno5ewaU87bLQCU_T2UGcKxdjY6vIdB/view?usp=sharing
## 👨‍💻 Author

**Raviranjan Kumar**

GitHub: [@raviranjancs](https://github.com/raviranjancs)

## 📄 License

This project is intended for learning and experimentation.
Add a project-specific open-source license before distributing it publicly.
