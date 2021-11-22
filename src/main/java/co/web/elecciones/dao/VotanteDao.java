package co.web.elecciones.dao;

import co.web.elecciones.model.Votante;
import co.web.elecciones.util.Conexion;

public class VotanteDao extends Conexion<Votante> implements GenericDao<Votante> {

	private static final long serialVersionUID = 1L;

	public VotanteDao() {
		super(Votante.class);
	}

}