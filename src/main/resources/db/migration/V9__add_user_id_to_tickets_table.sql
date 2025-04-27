ALTER TABLE project.tickets
    ADD user_id UUID;

ALTER TABLE project.tickets
    ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE project.tickets
    DROP COLUMN "user";