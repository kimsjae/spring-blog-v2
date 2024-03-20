package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.core.utils.ApiUtil;
import shop.mtcoding.blog.user.User;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/api/replies")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Reply reply = replyService.댓글쓰기(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(reply));
    }

    @DeleteMapping("/api/replies/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글삭제(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }
}
