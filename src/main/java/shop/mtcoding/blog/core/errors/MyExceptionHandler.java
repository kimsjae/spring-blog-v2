package shop.mtcoding.blog.core.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.mtcoding.blog.core.errors.exception.*;

@ControllerAdvice // 런타입익셉션이 터지면 해당 파일로 오류가 모인다.
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public String ex400(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/400";
    }

    @ExceptionHandler(Exception401.class)
    public String ex401(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/401";
    }

    @ExceptionHandler(Exception403.class)
    public String ex403(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/403";
    }

    @ExceptionHandler(Exception404.class)
    public String ex404(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/404";
    }

    @ExceptionHandler(Exception500.class)
    public String ex500(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/500";
    }

    @ExceptionHandler(Exception.class)
    public String exUnknown(Exception e, HttpServletRequest request) {
        // DB에러 로그 남기고
        // 관리자 문자 날려주고
        // 이메일도 보내고
        // 해야한다 원래는.
        return "err/500";
    }
}
