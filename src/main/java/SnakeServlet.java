import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SnakeServlet extends HttpServlet {
    private SnakeSolver solver = new SnakeSolver();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(solver.answer());
    }

    public static void main(String[] args) throws Exception {
        new JettyServletRunner().run();
    }
}
