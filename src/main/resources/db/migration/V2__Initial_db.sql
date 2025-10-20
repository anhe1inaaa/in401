-- === V2__Initial_db.sql ===
-- Система "Факультатив":
-- Курси (кожен має одного викладача), студенти, реєстрації на курси, підсумкові оцінки.

-- 1) Викладачі
CREATE TABLE IF NOT EXISTS teachers (
    id        BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(200) NOT NULL,
    email     VARCHAR(200),
    CONSTRAINT uq_teachers_email UNIQUE (email)
);

-- 2) Студенти
CREATE TABLE IF NOT EXISTS students (
    id        BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(200) NOT NULL,
    email     VARCHAR(200),
    CONSTRAINT uq_students_email UNIQUE (email)
);

-- 3) Курси (кожен курс закріплений за ОДНИМ викладачем)
CREATE TABLE IF NOT EXISTS courses (
    id          BIGSERIAL PRIMARY KEY,
    code        VARCHAR(32)  NOT NULL, -- унікальний шифр курсу, напр. CS101
    title       VARCHAR(200) NOT NULL,
    description TEXT,
    teacher_id  BIGINT NOT NULL REFERENCES teachers(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE UNIQUE INDEX IF NOT EXISTS uq_courses_code   ON courses(code);
CREATE INDEX        IF NOT EXISTS ix_courses_teacher ON courses(teacher_id);

-- 4) Реєстрації студентів на курси (many-to-many)
CREATE TABLE IF NOT EXISTS enrollments (
    id          BIGSERIAL PRIMARY KEY,
    student_id  BIGINT NOT NULL REFERENCES students(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    course_id   BIGINT NOT NULL REFERENCES courses(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    enrolled_at TIMESTAMP   NOT NULL DEFAULT NOW(),
    status      VARCHAR(16) NOT NULL DEFAULT 'ENROLLED'
        CHECK (status IN ('ENROLLED','DROPPED','COMPLETED')),
    UNIQUE (student_id, course_id) -- один активний запис реєстрації на курс
);

CREATE INDEX IF NOT EXISTS ix_enrollments_student ON enrollments(student_id);
CREATE INDEX IF NOT EXISTS ix_enrollments_course  ON enrollments(course_id);

-- 5) Архів оцінок (викладач виставляє підсумкову оцінку після завершення)
-- Якщо зберігається лише фінальна оцінка на курс — робимо 1:1 з enrollment через UNIQUE.
CREATE TABLE IF NOT EXISTS grades (
    id            BIGSERIAL PRIMARY KEY,
    enrollment_id BIGINT NOT NULL REFERENCES enrollments(id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    teacher_id    BIGINT NOT NULL REFERENCES teachers(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    grade_value   NUMERIC(4,2) NOT NULL CHECK (grade_value >= 0 AND grade_value <= 100),
    graded_at     TIMESTAMP    NOT NULL DEFAULT NOW(),
    comment       VARCHAR(500),
    CONSTRAINT uq_grades_enrollment UNIQUE (enrollment_id)
);

-- (Опційно) Перевірка узгодженості: той, хто ставив оцінку, має бути викладачем курсу.
-- Це правило краще перевіряти на рівні сервісу або за допомогою тригера.