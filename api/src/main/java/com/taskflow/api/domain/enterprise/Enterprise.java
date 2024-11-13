package com.taskflow.api.domain.enterprise;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "enterprise")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {

    @Id
    @GeneratedValue
    public UUID id;

    public String name;
}
