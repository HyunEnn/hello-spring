package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberCOntroller {
    private MemberService memberService;

    @Autowired // 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌
    public MemberCOntroller(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 조회할 때 get
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 데이터 등록할 때 post
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}
