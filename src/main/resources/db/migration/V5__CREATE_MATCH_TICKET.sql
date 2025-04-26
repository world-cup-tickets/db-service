CREATE TABLE project.matches (
                                 id uuid PRIMARY KEY,
                                 home_team VARCHAR(255),
                                 away_team VARCHAR(255),
                                 stadium VARCHAR(255),
                                 match_date TIMESTAMP,
                                 referee VARCHAR(255)
);

CREATE TABLE project.tickets (
                                 id uuid PRIMARY KEY,
                                 reservation_date TIMESTAMP,
                                 user_id uuid REFERENCES project.users(id),
                                 match_id uuid REFERENCES project.matches(id)
);