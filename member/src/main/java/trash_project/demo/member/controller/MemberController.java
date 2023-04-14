package trash_project.demo.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import trash_project.demo.member.dto.MemberDTO;
import trash_project.demo.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "index";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request) {
        MemberDTO loginResult = memberService.login(memberDTO);
//        System.out.println(memberDTO);
//        System.out.println(session.getAttribute("Id"));

        if (loginResult != null) {
            // login 성공
//            session.setAttribute("loginNo", loginResult.getNo());
            HttpSession session = request.getSession();
            session.setAttribute("Id", loginResult.getMemberId());
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }
}

