-- Enable trigram extension
CREATE EXTENSION IF NOT EXISTS pg_trgm;

-- 1. Main table
CREATE TABLE cvs
(
    id           BIGSERIAL PRIMARY KEY,
    default_lang VARCHAR(10) NOT NULL DEFAULT 'vi',
    is_active    BOOLEAN              DEFAULT true,
    created_at   TIMESTAMPTZ          DEFAULT NOW(),
    updated_at   TIMESTAMPTZ          DEFAULT NOW()
);

-- 2. Translations table
CREATE TABLE cv_translations
(
    id        BIGSERIAL PRIMARY KEY,
    cv_id     BIGINT       NOT NULL REFERENCES cvs (id) ON DELETE CASCADE,
    lang_code VARCHAR(10)  NOT NULL,

    name      VARCHAR(255) NOT NULL,
    title     VARCHAR(255),
    summary   TEXT,
    email     VARCHAR(255),
    phone     VARCHAR(50),
    location  VARCHAR(255),
    website   VARCHAR(255),

    UNIQUE (cv_id, lang_code)
);

-- Proper PostgreSQL indexes
CREATE INDEX idx_lang_code ON cv_translations (lang_code);

-- GIN TRGM index (FULLTEXT LIKE, ILIKE)
CREATE INDEX idx_name_trgm ON cv_translations USING gin (name gin_trgm_ops);

-- 3. Experiences
CREATE TABLE cv_experiences
(
    id             BIGSERIAL PRIMARY KEY,
    translation_id BIGINT NOT NULL REFERENCES cv_translations (id) ON DELETE CASCADE,
    sort_order     INT DEFAULT 0,
    company        VARCHAR(255),
    position       VARCHAR(255),
    duration       VARCHAR(100),
    description    TEXT
);

-- 4. Educations
CREATE TABLE cv_educations
(
    id             BIGSERIAL PRIMARY KEY,
    translation_id BIGINT NOT NULL REFERENCES cv_translations (id) ON DELETE CASCADE,
    sort_order     INT DEFAULT 0,
    school         VARCHAR(255),
    major          VARCHAR(255),
    duration       VARCHAR(100),
    gpa            VARCHAR(50)
);

-- 5. Skills
CREATE TABLE cv_skills
(
    id             BIGSERIAL PRIMARY KEY,
    translation_id BIGINT NOT NULL REFERENCES cv_translations (id) ON DELETE CASCADE,
    skill          VARCHAR(100) NOT NULL
);

-- 6. Languages
CREATE TABLE cv_languages
(
    id             BIGSERIAL PRIMARY KEY,
    translation_id BIGINT NOT NULL REFERENCES cv_translations (id) ON DELETE CASCADE,
    name           VARCHAR(100),
    level          VARCHAR(100)
);

-- 7. Projects
CREATE TABLE cv_projects
(
    id             BIGSERIAL PRIMARY KEY,
    translation_id BIGINT NOT NULL REFERENCES cv_translations (id) ON DELETE CASCADE,
    project_id     VARCHAR(100),
    title          VARCHAR(255),
    short_desc     TEXT,
    full_desc      TEXT,
    tech           TEXT[],
    link           VARCHAR(255),
    year           VARCHAR(10),
    sort_order     INT DEFAULT 0
);
