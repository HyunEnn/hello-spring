package hello.hellospring.domain;

public class Member {

    private Long id; // 시스템 id
    private String name; // 이름 저장

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
