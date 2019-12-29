public class GetNameservlet extends HttpServlet {

    @Override
    public void doGet(Request req, Response resp) {
        String currentUser = "未登录";
        String cookie = req.headers.get("Cookie");
        if(cookie!=null){
        currentUser = cookie.split("=")[1];
        }
        resp.setHeader("Content-Type","text/plain; charset=UTF-8");
        resp.setHeader("X-Teacher","woawo");
        resp.println("Hello"+req.parameters.get("target"));
        resp.println("当前用户是"+currentUser);
        }
        }
