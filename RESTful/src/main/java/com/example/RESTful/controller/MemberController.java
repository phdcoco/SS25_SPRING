package com.example.RESTful.controller;

import lombok.RequiredArgsConstructor;
import com.example.RESTful.model.Member;
import org.springframework.web.bind.annotation.*;
import com.example.RESTful.repository.MemberRepository;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @PostMapping // 회원 생성 (POST 요청을 처리하겠다.)
    public Member post(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @GetMapping // 전체 회원 목록 조회
    public List<Member> getAll() { // 아무런 파라미터를 받을 필요가 없다.
        return  memberRepository.findAll();
    }

    @GetMapping("/{id}")
    public Member get(@PathVariable("id") Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Member put(@PathVariable("id") Long id, @RequestBody Member member) {
        member.setId(id);
        return memberRepository.save(member);
    }

    @PatchMapping("/{id}")
    public Member patch(@PathVariable("id") Long id, @RequestBody Member patch) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member != null) {
            if (patch.getName() != null) member.setName(patch.getName());
            if (patch.getEmail() != null) member.setEmail(patch.getEmail());
            if (patch.getAge() != null) member.setAge(patch.getAge());
            memberRepository.save(member);
        }
        return member;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        memberRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        memberRepository.deleteAll();
    }
}
