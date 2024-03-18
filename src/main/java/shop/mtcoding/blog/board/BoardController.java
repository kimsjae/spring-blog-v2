package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.core.errors.exception.Exception403;
import shop.mtcoding.blog.core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;


    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardRepository.save(reqDTO.toEntity(sessionUser));
        return "redirect:/";
    }


    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable (name = "id") Integer id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);

        if (board == null) {
            throw new Exception404("해당 게시글을 찾을 수 없습니다.");
        }

        request.setAttribute("board", board);
        return "board/update-form";
    }


    @PostMapping("/board/{id}/update")
    public String update(@PathVariable(name = "id") Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);

        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        boardRepository.updateById(id, reqDTO.getTitle(), reqDTO.getContent());

        return "redirect:/board/" + id;
    }

    // Post는 write요청 (action)
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);

        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }
        
        boardRepository.deleteById(id);
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
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findByIdJoinUser(id);

        boolean isOwner = false;
        if (sessionUser != null) {
            if (sessionUser.getId() == board.getUser().getId()) {
                isOwner = true;
            }
        }

        request.setAttribute("isOwner", isOwner);
        request.setAttribute("board", board);
        return "board/detail";
    } // Integer 쓰면 안 들어올 때 Null, int는 0. 랩퍼클래스는 Null과 비교할 수 있어서 Integer를 쓴다.
}
