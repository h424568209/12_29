import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Task implements Runnable {
    private final Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            Request request = Request.parse(is);
            Response response = new Response();
            HttpServlet servlet = new JokeJSServlet();

            if (request.path.equals("/joke.js")) {
                servlet.doGet(request, response);
            }else if(request.path.equals("/login")){
                servlet = new LoginServerlet();
            }else if(request.path.equals("/hello")){
                servlet = new GetNameservlet();
            }
            else {
                response.setStatus("404 Not Found");
                response.setHeader("Content-Type", "application/javascript; charset=utf-8");
                response.println("<h1>没有找到页面</h1>");
            }
            servlet.doGet(request,response);
            response.writeAndFlush(os);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
