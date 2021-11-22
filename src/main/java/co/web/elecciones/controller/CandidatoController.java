package co.web.elecciones.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.web.elecciones.dao.CandidatoDao;
import co.web.elecciones.dao.EleccionDao;
import co.web.elecciones.model.Candidato;
import co.web.elecciones.model.Eleccion;

@WebServlet({ "/candidatos", "/candidatos/", "/candidatos/agregar", "/candidatos/registrar", "/candidatos/editar",
	"/candidatos/actualizar", "/candidatos/eliminar", "/candidatos/listar" })
public class CandidatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CandidatoDao candidatoDao;
	private EleccionDao eleccionDao;
	
	public CandidatoController() {
		super();
		candidatoDao = new CandidatoDao();
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
				case "/candidatos/agregar":
					showAgregar(request, response);
					break;
				case "/candidatos/registrar":
					agregar(request, response);
					break;
				case "/candidatos/editar":
					showEditar(request, response);
					break;
				case "/candidatos/actualizar":
					editar(request, response);
					break;
				case "/candidatos/eliminar":
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
		List<Eleccion> listaEleccion = eleccionDao.list();
		request.setAttribute("listaEleccion", listaEleccion);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gestion.jsp");
		dispatcher.forward(request, response);
	}

	private void agregar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String documento = request.getParameter("documento");
		int numero = Integer.parseInt(request.getParameter("numero"));
		Eleccion eleccion = eleccionDao.find(Integer.parseInt(request.getParameter("eleccion")));
		Candidato candidato = new Candidato(apellido, documento, nombre, numero, eleccion);
		candidatoDao.insert(candidato);
		response.sendRedirect("listar");
	}

	private void showEditar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Candidato candidato = candidatoDao.find(id);
		List<Eleccion> listaEleccion = eleccionDao.list();
		request.setAttribute("candidato", candidato);
		request.setAttribute("listaEleccion", listaEleccion);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gestion.jsp");
		dispatcher.forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String documento = request.getParameter("documento");
		int numero = Integer.parseInt(request.getParameter("numero"));
		Eleccion eleccion = eleccionDao.find(Integer.parseInt(request.getParameter("eleccion")));
		Candidato candidato = new Candidato(apellido, documento, nombre, numero, eleccion);
		candidato.setId(id);
		candidatoDao.update(candidato);
		response.sendRedirect("listar");
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Candidato candidato = candidatoDao.find(id);
		candidatoDao.delete(candidato);
		listar(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		List<Candidato> listaCandidato = candidatoDao.list();
		request.setAttribute("listaCandidato", listaCandidato);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}