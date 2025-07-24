package com.example.demo;


import com.example.demo.model.Article;
import com.example.demo.model.Member;
import com.example.demo.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.demo.repository.MemberRepository;

@Component //스프링 빈 객체로 등록한다.
@Slf4j
@RequiredArgsConstructor
@Transactional
public class JpaApplication implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var member1 = Member.builder()
                .name("유도훈")
                .email("ruru@rurang.com")
                .age(23).build();
        log.info("save 유도훈");
        memberRepository.save(member1);
        log.info("saved {}", member1);

        var article1 = Article.builder()
                .title("방학이 되었지만 나는 한 게 없다.")
                .description("에이 모르겠다. 그냥 놀자~")
                .member(member1).build();
        articleRepository.save(article1);

        var member2 = Member.builder()
                .name("윤쏘가리")
                .email("JamSso@soya.com")
                .age(23).build();
        log.info("save 윤쏘가리");
        memberRepository.save(member2);
        log.info("saved {}", member2);

        var article2 = Article.builder()
                .title("나는 한 게 있다.")
                .description("마음껏 그림을 그렸다. ㅎㅎㅎ")
                .member(member2).build();
        articleRepository.save(article2);

        log.info("read 유도훈");
        member1 = memberRepository.findById(member1.getId()).orElseThrow();

        log.info("update 유도훈");
        member1.setAge(24);
        memberRepository.save(member1);
        log.info("updated {}",  member1);

        log.info("update 윤쏘가리");
        memberRepository.save(member2); // 바뀐 게 없다.
        log.info("updated {}",  member2);

        var articles = articleRepository.findAll();
        for (Article article : articles) {
            log.info("{}", article);
        }

        log.info("애플리케이션 종료");
    }
}
