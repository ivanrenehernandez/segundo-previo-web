package co.web.elecciones.controller;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.web.elecciones.dao.EleccionDao;
import co.web.elecciones.model.Eleccion;

@WebServlet({ "/elecciones", "/elecciones/", "/elecciones/agregar", "/elecciones/registrar", "/elecciones/editar",
		"/elecciones/actualizar", "/elecciones/eliminar", "/elecciones/listar" })
public class EleccionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EleccionDao eleccionDao;
	private SimpleDateFormat sdfTime;
	private SimpleDateFormat sdf;
	
	public EleccionController() {
		super();
		sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		eleccionDao = new EleccionDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			if (action == null) {
				listar(request, response);
			} else {
				switch (action) {
				case "/elecciones/agregar":
					showAgregar(request, response);
					break;
				case "/elecciones/registrar":
					agregar(request, response);
					break;
				case "/elecciones/editar":
					showEditar(request, response);
					break;
				case "/elecciones/actualizar":
					editar(request, response);
					break;
				case "/elecciones/eliminar":
					eliminar(request, response);
				default:
					listar(request, response);
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void showAgregar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("gestion.jsp");
		dispatcher.forward(request, response);
	}

	private void agregar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException, ParseException {
		String nombre = request.getParameter("nombre");
		Date dateI = sdf.parse(request.getParameter("fechainicio"));
		Date dateF = sdf.parse(request.getParameter("fechafin"));
		String strI = sdfTime.format(dateI);
		String strF = sdfTime.format(dateF);
		Timestamp fechainicio = Timestamp.valueOf(strI);
		Timestamp fechafin = Timestamp.valueOf(strF);
		String cargo = request.getParameter("cargo");
		Eleccion eleccion = new Eleccion(cargo, fechafin, fechainicio, nombre);
		eleccionDao.insert(eleccion);
		response.sendRedirect("listar");
	}

	private void showEditar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Eleccion eleccion = eleccionDao.find(id);
		request.setAttribute("eleccion", eleccion);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gestion.jsp");
		dispatcher.forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException, ParseException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		Date dateI = sdf.parse(request.getParameter("fechainicio"));
		Date dateF = sdf.parse(request.getParameter("fechafin"));
		String strI = sdfTime.format(dateI);
		String strF = sdfTime.format(dateF);
		Timestamp fechainicio = Timestamp.valueOf(strI);
		Timestamp fechafin = Timestamp.valueOf(strF);
		String cargo = request.getParameter("cargo");
		Eleccion eleccion = new Eleccion(cargo, fechafin, fechainicio, nombre);
		eleccion.setId(id);
		eleccionDao.update(eleccion);
		response.sendRedirect("listar");
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Eleccion eleccion = eleccionDao.find(id);
		eleccionDao.delete(eleccion);
		listar(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		List<Eleccion> listaEleccion = eleccionDao.list();
		request.setAttribute("listaEleccion", listaEleccion);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
