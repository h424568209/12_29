public class LoginServerlet extends HttpServlet {

    @Override
    public void doGet(Request req, Response resp) {

        String username= req.parameters.get("username");
        if(username==null){
            resp.setStatus("401 Unauthorized");
            resp.setHeader("Content-Type", "text/html; charset=utf-8");
            resp.println("<h1>请登录</h1>");
            return;
        }
        //过期时间
        resp.setHeader("set-Cookie","username="+username+"; expires=Tue, 07-Apr-2020 08:46:16 GMT; Max-Age=8640000");
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.print("<h1>欢迎"+username+"光临</h1>");

    }
}
