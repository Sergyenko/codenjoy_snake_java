import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SnakeServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -382882400499243566L;
	private SnakeSolver solver = new SnakeSolver();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String board = req.getParameter("board");
        System.out.println(String.format("glass: %s", board));

        resp.getWriter().write(solver.answer(board));
    }

    public static void main(String[] args) throws Exception {
        new JettyServletRunner().run();
    }
}
