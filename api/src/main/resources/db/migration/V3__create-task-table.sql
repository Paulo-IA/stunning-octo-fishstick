CREATE TYPE task_state AS ENUM ('STOPPED', 'NOT_STARTED', 'COMPLETED', 'UNDER_APPROVAL', 'IN_PROGRESS');

CREATE TABLE task (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    state task_state,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    enterprise_id UUID,
    FOREIGN KEY (enterprise_id) REFERENCES enterprise(id) ON DELETE CASCADE
);