package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardNativeRepository boardNativeRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @PostMapping("/board/save")
    public String save(String title, String content, String username) { // DTO 없이 받기
//        System.out.println("username : " + username); // 출력해서 name값 잘 들어오는 지 확인해봐야함
//        System.out.println("title : " + title); // 출력해서 name값 잘 들어오는 지 확인해봐야함
//        System.out.println("content : " + content); // 출력해서 name값 잘 들어오는 지 확인해봐야함
//        // 잘 들어옴
        boardNativeRepository.save(title, content, username);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) {
        return "board/detail";
    } // Integer 쓰면 안 들어올 때 Null, int는 0. 랩퍼클래스는 Null과 비교할 수 있어서 Integer를 쓴다.
}
