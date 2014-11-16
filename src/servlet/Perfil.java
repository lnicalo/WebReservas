package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.InfoPerfil;

public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ID_BEAN_PERFIL = "bean_perfil";
    static public String ROLE_GESTOR = "gestor";
    static public String ROLE_PUBLICO = "publico";
    private static final String ID_JSP_PUBLICO = "/perfil_publico.jsp";
    private static final String ID_JSP_GESTOR = "/perfil_gestor.jsp";
   
    public Perfil() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ( request.isUserInRole(ROLE_GESTOR)){
			
			String login = request.getRemoteUser();
			InfoPerfil ip = new InfoPerfil();
			
			ip.setDataGestor(login);
			
			request.setAttribute(ID_BEAN_PERFIL, ip);
			ServletContext sct = getServletContext();
			RequestDispatcher rd = sct.getRequestDispatcher(ID_JSP_GESTOR);
			rd.forward(request, response);
			
		}
		
		else if (request.isUserInRole(ROLE_PUBLICO)) {
			
			String login = request.getRemoteUser();
			InfoPerfil ip = new InfoPerfil();
			
			ip.setDataPublico(login);
			
			request.setAttribute(ID_BEAN_PERFIL, ip);
			ServletContext sct = getServletContext();
			RequestDispatcher rd = sct.getRequestDispatcher(ID_JSP_PUBLICO);
			rd.forward(request, response);
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
