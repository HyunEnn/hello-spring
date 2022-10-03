package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0,1,2 키값 생성
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 임의로 시스템이 숫자를 지정
        store.put(member.getId(), member); // store 값에 member 숫자와 이름 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional을 사용하여 값이 비었는지 안비었는지 모르는데 값이 비었으면 store의 id 값을 넣는다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store의 value 값들을 루프를 돌면서 탐색한다.
                .filter(member -> member.getName().equals(name)) // filter 함수를 통해 member의 이름들 중 name과 같은게 있는지 탐색
                .findAny(); // 뭐든 필터에 걸리는 것들 다 검색.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 찾는 모든 것들을 새로운 ArrayList 를 만들어서 store의 값들을 저장한다.
    }

    public void clearStore() {
        store.clear();
    } // store 의 데이터 값을 전부 정리함.
}


