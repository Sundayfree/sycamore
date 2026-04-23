CREATE TABLE IF NOT EXISTS employee (
    id BIGINT NOT NULL AUTO_INCREMENT,
    shop_id BIGINT NOT NULL,
    emp_no VARCHAR(20) NOT NULL,
    fullname VARCHAR(50),
    gender TINYINT NOT NULL DEFAULT 1,
    phone VARCHAR(16) NOT NULL,
    id_card VARCHAR(256),
    role_id BIGINT NOT NULL,
    password_hash VARCHAR(128) NOT NULL,
    avatar_url VARCHAR(500),
    status TINYINT NOT NULL DEFAULT 1,
    hire_date DATE NOT NULL,
    leave_date DATE DEFAULT NULL,
    deleted TINYINT NOT NULL DEFAULT 0,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_shop_emp (shop_id, emp_no),
    KEY idx_role (role_id),
    KEY idx_phone (shop_id, phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS user_token (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT NOT NULL,
    shop_id BIGINT NOT NULL,
    token VARCHAR(512) NOT NULL,
    expire_time DATETIME NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,

    UNIQUE KEY uk_token (token),
    KEY idx_employee_id (employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS role (
    id bigint NOT NULL AUTO_INCREMENT,
    shop_id bigint NOT NULL,
    role_code varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    role_name varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    description varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    is_system tinyint NOT NULL DEFAULT 0,
    status tinyint NOT NULL DEFAULT 1,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;