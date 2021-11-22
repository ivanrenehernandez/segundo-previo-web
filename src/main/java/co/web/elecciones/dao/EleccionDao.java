package co.web.elecciones.dao;

import co.web.elecciones.model.Eleccion;
import co.web.elecciones.util.Conexion;

public class EleccionDao extends Conexion<Eleccion> implements GenericDao<Eleccion> {

	private static final long serialVersionUID = 1L;

	public EleccionDao() {
		super(Eleccion.class);
	}

}
