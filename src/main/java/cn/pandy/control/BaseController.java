package cn.pandy.control;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目: horde
 * 作者: 潘清剑(qingjian.pan@tongdun.cn)
 * 时间: 16/10/9 上午10:23
 * 描述:
 */
public class BaseController {

    protected void print(HttpServletResponse response, String result) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(result);
    }

    protected void printJson(HttpServletResponse response, String result) throws IOException {
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(result);
    }
}
