Öğrenci Otomasyon Sistemi - Backend (Spring Boot + PostgreSQL)

Kısa: Bu proje, basit bir "öğrenci otomasyon sistemi" backend iskeletidir. Java 17, Maven, Spring Boot 3 kullanılarak hazırlanmıştır. Veritabanı: PostgreSQL (örnek ayarlar application.yml içinde).

Proje yapısı (önemli paketler):
- com.ademarli.demo.entity: JPA entity'ler (User, Course, StudentCourseEnrollment, Role)
- com.ademarli.demo.repository: Spring Data JPA repository'ler
- com.ademarli.demo.dto: API istek/yanıt DTO'ları
- com.ademarli.demo.service: Servis arayüzleri
- com.ademarli.demo.service.impl: Servis implementasyonları
- com.ademarli.demo.controller: REST controller'lar

Hızlı başlangıç (lokalde PostgreSQL ile):
1) PostgreSQL oluşturun ve bir veritabanı, kullanıcı oluşturun (örnek):

```sql
-- PostgreSQL side example
CREATE DATABASE ogrenci_db;
CREATE USER demo_user WITH ENCRYPTED PASSWORD 'demo_pass';
GRANT ALL PRIVILEGES ON DATABASE ogrenci_db TO demo_user;
```

2) `src/main/resources/application.yml` içindeki connection bilgilerini kendi ayarlarınıza göre güncelleyin (url, username, password).

3) Derleme ve çalıştırma:

```bash
# Windows (project root)
./mvnw.cmd -DskipTests package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Not: `data.sql` örnek kullanıcılar, dersler ve kayıtları içerir. Uygulama başlarken veritabanına bağlanabilirse bu veriler yüklenecektir.

Ana endpoint'ler (örnekler):
- Auth
  - POST /api/auth/register  -> body: RegisterRequest
  - POST /api/auth/login     -> body: LoginRequest

- Teacher
  - POST /api/teacher/courses -> create course (CourseCreateRequest)
  - GET /api/teacher/{teacherId}/courses -> teacher'ın dersleri
  - PUT /api/teacher/courses/{courseId} -> güncelle
  - DELETE /api/teacher/courses/{courseId} -> sil

- Student
  - POST /api/student/enroll -> EnrollmentRequest
  - GET /api/student/{studentId}/courses
  - GET /api/student/{studentId}/info -> öğrenci bilgisi + dersleri

- Admin
  - GET /api/admin/users?role=STUDENT|TEACHER|ADMIN
  - DELETE /api/admin/users/{id}
  - GET /api/admin/courses

Örnek curl (register):

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Test","lastName":"User","email":"t@test.com","password":"pass","role":"STUDENT"}'
```

Özet kontrol listesi ve durum:
- Java 17, Maven, Spring Boot 3 starterlar eklendi: Done (pom.xml güncellendi). 
- Entity katmanları: User, Course, StudentCourseEnrollment, Role: Done
- Repository'ler: UserRepository, CourseRepository, StudentCourseEnrollmentRepository: Done
- DTO'lar: RegisterRequest, LoginRequest, UserResponse, CourseCreateRequest, CourseResponse, EnrollmentRequest, StudentInfoResponse: Done
- Servis katmanı: AuthService, TeacherService, StudentService, AdminService + implementasyonları: Done
- Controller'lar: AuthController, TeacherController, StudentController, AdminController: Done
- application.yml: örnek PostgreSQL bağlantı ayarları eklendi: Done
- data.sql: örnek admin, teacher, student, courses, enrollments: Done
- Spring Security yok: Done (dependency eklenmedi)

Derleme ve test:
- mvn package (tests skipped) çalıştırıldı: BUILD SUCCESS
- IDE/derleyici uyarıları: bazı kullanılmayan alan/constructor/import uyarıları mevcut (warning). Bunlar runtime'ı engellemiyor.

Notlar / Next steps (öneriler):
- Şifreleri düz metin tutmak demo içindir. Üretimde mutlaka bcrypt/scrypt kullanın.
- Gerçek authentication/token (JWT) ve yetkilendirme eklenmeli.
- Daha fazla validation (örn. @Valid ve DTO field doğrulamaları) ekleyin.
- Unit/integration testleri ekleyin.

Eğer isterseniz şimdi şunları yapabilirim:
- DTO alanlarına bean validation (@NotNull, @Email vb.) ekleyip controller'larda @Valid kullanmak,
- Basit unit testler yazmak (özellikle AuthService happy path + error),
- veya JWT tabanlı basit auth eklemek.

Hangi adımda devam etmemi istersiniz? (örn. validation ekle, JWT auth ekle, unit testler ekle, ya da başka bir şey)

