package life.majiang.community.advice;

import life.majiang.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.Customizer;

/**
 * 全局异常处理类
 * Created by hp on 2019/8/23 12:02
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    //拦截异常
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model){
        if (ex instanceof CustomizeException) {
            model.addAttribute("message",ex.getMessage());
        }else {
            model.addAttribute("message","你还不能休息哟,系统又出BUG了哟!!!");
        }
        return new ModelAndView("error");
    }
}
