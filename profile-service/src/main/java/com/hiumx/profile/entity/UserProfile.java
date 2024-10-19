package com.hiumx.profile.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Node("user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;

    @Property("userId")
    private String userId;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String city;
}
