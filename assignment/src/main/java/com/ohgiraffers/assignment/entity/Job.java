package com.ohgiraffers.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Table(name = "job")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Job {

    @Id
    private String jobCode;
    private String jobName;

}
