package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // test 함수가 돌면 실행되도록 하는 Call-back Method
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring"); // member의 이름을 spring으로 설정한다.

        repository.save(member); // 기존 메인 함수에 작성한 repository 의 save 함수를 사용하여 member를 저장

        Member result = repository.findById(member.getId()).get(); // repository의 Id값을 가지고 오고 Optional에서 아이디를 꺼낼 때 get으로 꺼내고 result에 저장한다.
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, result); // (기대값, 결과값) 순서대로 Assertions 함수를 사용하여 true, false를 나타낸다.
        assertThat(member).isEqualTo(result); // assertions를 import static *로 받아서 Assertions 없이 언제든 기능 사용 가능하게 만들었고, 원하는 memeber 값이 result 값과 같냐를 판단한다.

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); // member1 , member2 값을 repository 메인 함수에 save 함수를 사용하여 저장

        List<Member> result = repository.findAll(); // repository.findAll() ALt+Enter를 해서 지역변수로 변환하면 자동으로 List<Member> 함수로 변환된다.
        assertThat(result.size()).isEqualTo(2); // result의 값이 member1 , member2 2개가 만들어지고 size값이 2개인지 체크한다.

    }

}
