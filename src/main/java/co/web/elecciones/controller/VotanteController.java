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

import co.web.elecciones.dao.EleccionDao;
import co.web.elecciones.dao.TipoDocumentoDao;
import co.web.elecciones.dao.VotanteDao;
import co.web.elecciones.model.Eleccion;
import co.web.elecciones.model.Tipodocumento;
import co.web.elecciones.model.Votante;

@WebServlet({ "/votantes", "/votantes/", "/votantes/agregar", "/votantes/registrar", "/votantes/editar",
		"/votantes/actualizar", "/votantes/eliminar", "/votantes/listar" })
public class VotanteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EleccionDao eleccionDao;
	private TipoDocumentoDao tipoDocumentoDao;
	private VotanteDao votanteDao;

	public VotanteController() {
		super();
		eleccionDao = new EleccionDao();
		tipoDocumentoDao = new TipoDocumentoDao();
		votanteDao = new VotanteDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			if (action == null) {
				listar(request, response);
			} else {
				switch (action) {
				case "/votantes/agregar":
					showAgregar(request, response);
					break;
				case "/votantes/registrar":
					agregar(request, response);
					break;
				case "/votantes/editar":
					showEditar(request, response);
					break;
				case "/votantes/actualizar":
					editar(request, response);
					break;
				case "/votantes/eliminar":
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
		List<Tipodocumento> listaTipoDocumento = tipoDocumentoDao.list();
		for (Tipodocumento tipodocumento : listaTipoDocumento) {
			System.out.println(tipodocumento.getDescripcion());
		}
		request.setAttribute("listaEleccion", listaEleccion);
		request.setAttribute("listaTipoDocumento", listaTipoDocumento);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gestion.jsp");
		dispatcher.forward(request, response);
	}

	private void agregar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String documento = request.getParameter("documento");
		Eleccion eleccion = eleccionDao.find(Integer.parseInt(request.getParameter("eleccion")));
		Tipodocumento tipoDocumento = tipoDocumentoDao.find(Integer.parseInt(request.getParameter("tipodocumento")));
		Votante votante = new Votante(documento, email, nombre, eleccion, tipoDocumento);
		votanteDao.insert(votante);
		response.sendRedirect("listar");
	}

	private void showEditar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Votante votante = votanteDao.find(id);
		List<Eleccion> listaEleccion = eleccionDao.list();
		List<Tipodocumento> listaTipoDocumento = tipoDocumentoDao.list();
		request.setAttribute("votante", votante);
		request.setAttribute("listaEleccion", listaEleccion);
		request.setAttribute("listaTipoDocumento", listaTipoDocumento);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gestion.jsp");
		dispatcher.forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String documento = request.getParameter("documento");
		Eleccion eleccion = eleccionDao.find(Integer.parseInt(request.getParameter("eleccion")));
		Tipodocumento tipoDocumento = tipoDocumentoDao.find(Integer.parseInt(request.getParameter("tipodocumento")));
		Votante votante = new Votante(documento, email, nombre, eleccion, tipoDocumento);
		votante.setId(id);
		votanteDao.update(votante);
		response.sendRedirect("listar");
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Votante votante = votanteDao.find(id);
		votanteDao.delete(votante);
		listar(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		List<Votante> listaVotante = votanteDao.list();
		request.setAttribute("listaVotante", listaVotante);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}