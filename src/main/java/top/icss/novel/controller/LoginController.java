package top.icss.novel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import top.icss.novel.dto.User;
import top.icss.novel.utils.Result;

import java.util.Objects;

/**
 * @author cd.wang
 * @create 2021-01-19 15:46
 * @since 1.0.0
 */
@Controller
public class LoginController {

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}
