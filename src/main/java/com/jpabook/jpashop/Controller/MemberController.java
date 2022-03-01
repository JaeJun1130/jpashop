package com.jpabook.jpashop.Controller;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm",new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if(result.hasErrors()) {
            return "/members/createMemberForm";
        }

        try {
            Member member = new Member();
            member.setName(form.getName());
            member.setAddress(new Address(form.getCity(), form.getStreet(), form.getZipcode()));

            memberService.join(member);
        }catch (IllegalStateException e) {
            e.printStackTrace();
            result.addError(new FieldError("memberForm","name",e.getMessage()));
            return "/members/createMemberForm";
        }

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){

        /**
         * 간단하게 Entity 를 반환해서 화면의 뿌려줬지만 웬만하며는 DTO를 따로 만들어서 넘겨주는것이 좋다.
         * API에서는 절때로 Entity 를 반환해서 사용하면 안된다.
         */
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "/members/memberList";
    }
}
