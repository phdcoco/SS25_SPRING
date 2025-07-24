package com.example.demo.repository;

import com.example.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    // 파라미터에 따라 이름으로 검색하거나 이름과 이메일 둘 다 사용해 검색
    // m은 JPQL에서 사용되는 엔티티의 별칭이다. 꼭 별칭을 사용해야 한다.
    // Member라는 엔티티에서 m이라는 별칭을 붙이고 m.name이 파라미터 :name과 같은 데이터를 찾아라.
    @Query("SELECT m FROM Member m WHERE m.name = :name")
    List<Member> findMember(@Param("name") String name);

    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.email = :email")
    List<Member> findMember(@Param("name") String name, @Param("email") String email);



    }
