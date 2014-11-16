package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GestorReservas;

public class Ocupacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ID_BEAN_GESTOR_RESERVAS = "bean";
    static public String ROLE_GESTOR = "gestor";
    static public String ROLE_PUBLICO = "publico";
    private static final String ID_JSP_PUBLICO = "/ocupacion.jsp"; 

    public Ocupacion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.isUserInRole(ROLE_PUBLICO)) {
			String idServicio = request.getParameter("idServicio");
			String ano = request.getParameter("Ano");
			String mes = request.getParameter("Mes");
			
			try{
				Integer.parseInt(ano);
				Integer.parseInt(mes);
			}
			catch (NumberFormatException e) {
				Calendar cal = Calendar.getInstance();
				ano = cal.get(Calendar.YEAR) + "";
				mes = (cal.get(Calendar.MONTH) + 1) +"";
			}
			
			GestorReservas gr = new GestorReservas();
			if(idServicio != null) {
				if(idServicio.compareTo("null") != 0) {
					gr.setDataServicio(idServicio, ano, mes);
				}
				else {
					String idSubServicio = request.getParameter("idSubServicio");
					if(idSubServicio != null) {
						gr.setDataSubServicio(idSubServicio, ano, mes);
					}
				}
			} else {
				String idSubServicio = request.getParameter("idSubServicio");
				if(idSubServicio != null) {
					gr.setDataSubServicio(idSubServicio, ano, mes);
				}
			}
			
			request.setAttribute(ID_BEAN_GESTOR_RESERVAS, gr);
			ServletContext sct = getServletContext();
			RequestDispatcher rd = sct.getRequestDispatcher(ID_JSP_PUBLICO);
			rd.forward(request, response);
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
