-- ==========================
-- Seed data cho bảng cvs
-- ==========================
INSERT INTO cvs (default_lang, is_active)
VALUES ('vi', true),
       ('en', true) RETURNING id;

-- Lấy ID của CV vừa tạo
-- Ví dụ CV_ID = 1, CV_ID = 2

-- ==========================
-- Seed data cho cv_translations
-- ==========================
INSERT INTO cv_translations (cv_id, lang_code, name, title, summary, email, phone, location, website)
VALUES (1, 'vi', 'Hà Trọng Vũ', 'Software Engineer', 'Tôi là lập trình viên Java.', 'hatrongvu13@gmail.com',
        '+84943561685',
        'Hanoi', 'https://example.com'),
       (2, 'en', 'Ha Trong Vu', 'Software Engineer', 'I am a Java developer.', 'hatrongvu13@gmail.com', '+84943561685',
        'Hanoi', 'github.io/hatrongvu13');

-- Giả sử translation_id = 1 và 2

-- ==========================
-- Seed data cho cv_experiences
-- ==========================
INSERT INTO cv_experiences (translation_id, sort_order, company, position, duration, description)
VALUES (1, 1, 'TDT Asia JSC', 'Intern Java Developer', '2019-2020', 'Phát triển backend bằng Java/Spring'),
       (2, 1, 'TDT Asia JSC', 'Intern Java Developer', '2019-2020', 'Develop backend services with Java/Spring'),
       (1, 1, 'ATI JSC', 'Fresher Java Developer', '2020-2021', 'Phát triển backend bằng Java/Spring, triển khai ứng dụng trên k8s, docker'),
       (2, 1, 'ATI JSC', 'Fresher Java Developer', '2020-2021', 'Develop backend services with Java/Spring, deployment application using k8s, docker'),
       (1, 1, 'CMC Global', 'Java Developer', '2021-2023', 'Phát triển backend bằng Java/Spring, microservice, SQL, NoSQL, Docker, ...'),
       (2, 1, 'CMC Global', 'Java Developer', '2021-2023', 'Develop backend services with Java/Spring, microservice, SQL, NoSQL, Docker, ...'),
       (1, 1, 'MSB Bank', 'Software Engineer', '2023-now', 'Phát triển backend bằng Java/Spring, microservice, camunda'),
       (2, 1, 'MSB Bank', 'Software Engineer', '2023-now', 'Develop backend services with Java/Spring');

-- ==========================
-- Seed data cho cv_educations
-- ==========================
INSERT INTO cv_educations (translation_id, sort_order, school, major, duration, gpa)
VALUES (1, 1, 'Viện CNTT T3H', 'Computer Science', '2018-2021', '3.8'),
       (2, 1, 'T3H IT Institute', 'Computer Science', '2018-2021', '3.9');

-- ==========================
-- Seed data cho cv_skills
-- ==========================
INSERT INTO cv_skills (translation_id, skill)
VALUES (1, 'Java'),
       (1, 'Spring Boot'),
       (1, 'Sql'),
       (1, 'Kubernetes'),
       (1, 'NoSql'),
       (1, 'Grpc'),
       (1, 'GraphQl'),
       (1, 'Docker'),
       (2, 'Java'),
       (2, 'Spring Boot'),
       (2, 'Sql'),
       (2, 'Kubernetes'),
       (2, 'NoSql'),
       (2, 'Grpc'),
       (2, 'GraphQl'),
       (2, 'Docker');

-- ==========================
-- Seed data cho cv_languages
-- ==========================
INSERT INTO cv_languages (translation_id, name, level)
VALUES (1, 'English', 'Intermediate'),
       (1, 'Vietnamese', 'Native'),
       (2, 'English', 'Native'),
       (2, 'Vietnamese', 'Native');

-- ==========================
-- Seed data cho cv_projects
-- ==========================
INSERT INTO cv_projects (translation_id, project_id, title, short_desc, full_desc, tech, link, year, sort_order)
VALUES (1, 'proj1', 'Project A', 'Short description A', 'Full description A', ARRAY['Java', 'Spring Boot'],
        'https://github.com/example/projA', '2021', 1),
       (2, 'proj2', 'Project B', 'Short description B', 'Full description B', ARRAY['Java', 'Quarkus'],
        'https://github.com/example/projB', '2022', 1);
