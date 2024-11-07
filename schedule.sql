CREATE TABLE scheduler (

                           id BIGINT NOT NULL AUTO_INCREMENT,

                           user_id BIGINT NOT NULL,

                           password VARCHAR(255) NOT NULL,

                           user_name VARCHAR(255) NOT NULL,

                           contents VARCHAR(255) NOT NULL,

                           created_at TIMESTAMP NOT NULL,

                           updated_at TIMESTAMP NOT NULL,

                           PRIMARY KEY (id),

                           FOREIGN KEY (id) REFERENCES user(id)

);

CREATE TABLE user (

                      id BIGINT NOT NULL AUTO_INCREMENT,

                      name VARCHAR(255) NOT NULL,

                      email VARCHAR(255) NOT NULL,

                      created_at TIMESTAMP NOT NULL,

                      updated_at TIMESTAMP NOT NULL,

                      PRIMARY KEY (id)

);

SELECT * FROM scheduler ORDER BY updated_at DESC LIMIT ? OFFSET ?

SELECT COUNT(*) FROM scheduler

select * from scheduler where id = ?

update scheduler set user_name = ?, contents = ?, updated_at = NOW() where id = ?

delete from scheduler where id = ?