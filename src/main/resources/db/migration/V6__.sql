ALTER TABLE project.tickets
    DROP CONSTRAINT tickets_user_id_fkey;

ALTER TABLE project.matches
    ADD nr_seats INTEGER;

ALTER TABLE project.matches
    ADD seat_price DOUBLE PRECISION;

ALTER TABLE project.matches
    ALTER COLUMN nr_seats SET NOT NULL;

ALTER TABLE project.tickets
    ADD price DOUBLE PRECISION;

ALTER TABLE project.tickets
    ADD "user" UUID;

ALTER TABLE project.tickets
    ALTER COLUMN price SET NOT NULL;

ALTER TABLE project.matches
    ALTER COLUMN seat_price SET NOT NULL;

ALTER TABLE project.tickets
    ALTER COLUMN "user" SET NOT NULL;

DROP TABLE project.users CASCADE;

ALTER TABLE project.tickets
    DROP COLUMN user_id;

ALTER TABLE project.tickets
    ALTER COLUMN reservation_date SET NOT NULL;