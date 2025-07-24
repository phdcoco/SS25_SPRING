package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vip_table", indexes = {
        @Index(name = "idx_email", columnList = "email")
})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="display_name")
    private String name;
    private String email;
    private Integer age;

    // 일대다 관계 추가
    @ToString.Exclude
    @JsonIgnore
    @OneToMany (mappedBy = "member", fetch = FetchType.EAGER)
    private List<Article> articles;
}