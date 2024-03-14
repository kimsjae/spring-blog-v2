package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;


    @PostMapping("/board/save")
    public String save() {
        return "redirect:/";
    }


    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable (name = "id") Integer id, HttpServletRequest request) {
        return "board/update-form";
    }


    @PostMapping("/board/{id}/update")
    public String update(@PathVariable(name = "id") Integer id) {
        return "redirect:/board/" + id;
    }

    // Post는 write요청 (action)
    @PostMapping("/board/{id}/delete")
    public String deleteById(@PathVariable (name = "id") Integer id) {
        return "redirect:/";
    }



    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }


    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        Board board = boardRepository.findByIdJoinUser(id);
        request.setAttribute("board", board);
        return "board/detail";
    } // Integer 쓰면 안 들어올 때 Null, int는 0. 랩퍼클래스는 Null과 비교할 수 있어서 Integer를 쓴다.
}
