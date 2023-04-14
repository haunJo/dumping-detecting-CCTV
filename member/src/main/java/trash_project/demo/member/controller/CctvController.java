package trash_project.demo.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import trash_project.demo.member.dto.CctvDTO;
import trash_project.demo.member.dto.MemberDTO;
import trash_project.demo.member.entity.MemberEntity;
import trash_project.demo.member.repository.MemberRepository;
import trash_project.demo.member.service.CctvService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class CctvController {
    // 생성자 주입
    private final CctvService cctvService;

    // cctv 등록 페이지 출력 요청
    @GetMapping("/cctv/register")
    public String registerForm() { return "cctv_register"; }

    @PostMapping("/cctv/register")
    public String register(@ModelAttribute CctvDTO cctvDTO, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        String loginId = (String) httpSession.getAttribute("Id");

        cctvService.register(cctvDTO, loginId);

        return "main";
    }

    @GetMapping("/cctv/list")
    public String findAll(Model model) {
        List<CctvDTO> cctvDTOList = cctvService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("cctvList", cctvDTOList);
        return "cctv_list";
    }

}
