package com.taskflow.api.domain.task;

import com.taskflow.api.domain.enterprise.Enterprise;
import com.taskflow.api.domain.user.User;
import com.taskflow.api.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    public UUID id;

    public String title;
    public String description;
    public Date start_date;
    public Date end_date;

    @Enumerated(EnumType.ORDINAL)
    public State state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;
}
