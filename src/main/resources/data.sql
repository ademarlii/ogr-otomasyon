-- Sample data for öğrenci otomasyon sistemi
-- 1 admin, 1 teacher, 2 students

INSERT INTO users (id, first_name, last_name, email, password, role) VALUES (1, 'Admin', 'User', 'admin@example.com', 'adminpass', 'ADMIN');
INSERT INTO users (id, first_name, last_name, email, password, role) VALUES (2, 'Ahmet', 'Öğretmen', 'teacher@example.com', 'teacherpass', 'TEACHER');
INSERT INTO users (id, first_name, last_name, email, password, role) VALUES (3, 'Mehmet', 'Öğrenci', 'student1@example.com', 'studentpass1', 'STUDENT');
INSERT INTO users (id, first_name, last_name, email, password, role) VALUES (4, 'Ayşe', 'Öğrenci', 'student2@example.com', 'studentpass2', 'STUDENT');

-- Courses taught by teacher (id=2)
INSERT INTO courses (id, name, code, description, teacher_id) VALUES (1, 'Matematik I', 'MAT101', 'Giriş seviyesinde matematik dersi', 2);
INSERT INTO courses (id, name, code, description, teacher_id) VALUES (2, 'Fizik I', 'FIZ101', 'Temel fizik kavramları', 2);
INSERT INTO courses (id, name, code, description, teacher_id) VALUES (3, 'Kimya I', 'KIM101', 'Temel kimya bilgileri', 2);

-- Enrollments
INSERT INTO student_course_enrollments (id, student_id, course_id, enrollment_date) VALUES (1, 3, 1, now());
INSERT INTO student_course_enrollments (id, student_id, course_id, enrollment_date) VALUES (2, 3, 2, now());
INSERT INTO student_course_enrollments (id, student_id, course_id, enrollment_date) VALUES (3, 4, 2, now());

