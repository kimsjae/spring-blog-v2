package shop.mtcoding.blog.user;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.core.errors.exception.Exception400;
import shop.mtcoding.blog.core.errors.exception.Exception401;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        try {
            User sessionUser = null;
            sessionUser = userRepository.save(reqDTO.toEntity());
            session.setAttribute("sessionUser", sessionUser); // 회원가입과 동시에 로그인
        } catch (NoResultException e) {
            throw new Exception400("동일한 유저네임이 존재합니다.");
        }

        return "redirect:/";
    }



    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO) {
        try {
            User sessionUser = userRepository.findByUsernameAndPassword(requestDTO);

            if (sessionUser == null) {
                return "redirect:/login-form";
            }

            session.setAttribute("sessionUser", sessionUser);
        } catch (Exception e) {
            throw new Exception401("");
        }
        

        return "redirect:/";
    }

    @GetMapping("/user/update-form") // id를 안 들고가도 되는 이유: session에서 찾으면 되니까.
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

//        if (sessionUser == null) {
//            throw new Exception401("인증되지 않았어요. 로그인해주세요");
//        }

        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        userRepository.updateById(sessionUser.getId(), reqDTO);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
