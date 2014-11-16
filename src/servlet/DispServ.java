package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TiposServicios
 */
public class DispServ extends HttpServlet {
    static public String ROLE_GESTOR = "gestor";
    static public String ROLE_PUBLICO = "publico";
    private static final String ID_JSP = "/dispServ.jsp";
    
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispServ() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ( request.isUserInRole(ROLE_PUBLICO)){

			ServletContext sct = getServletContext();
			RequestDispatcher rd = sct.getRequestDispatcher(ID_JSP);
			rd.forward(request, response);

		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
