CREATE TABLE t_role (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE t_user (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        username VARCHAR(255) NOT NULL,
                        email VARCHAR(255),
                        password VARCHAR(255) NOT NULL,
                        version BIGINT,
                        CONSTRAINT UC_User_Email UNIQUE (email)
);

CREATE TABLE t_user_roles (
                              user_id BIGINT,
                              roles_id BIGINT,
                              PRIMARY KEY (user_id, roles_id),
                              FOREIGN KEY (user_id) REFERENCES t_user (id),
                              FOREIGN KEY (roles_id) REFERENCES t_role (id)
);

CREATE TABLE t_cart (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        version BIGINT,
                        user_id BIGINT,
                        product_id BIGINT,
                        quality BIGINT,
                        FOREIGN KEY (user_id) REFERENCES t_user (id),
                        FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE product (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255),
                         amount DOUBLE,
                         price DOUBLE,
                         version BIGINT,
                         product_info_id BIGINT,
                         FOREIGN KEY (product_info_id) REFERENCES product_info (id)
);

CREATE TABLE product_info (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              type VARCHAR(255),
                              description TEXT,
                              type_animal VARCHAR(255),
                              company VARCHAR(255)
);

INSERT INTO t_role (id, name) VALUES (1, 'User');

INSERT INTO t_role (id, name) VALUES (2, 'Admin');