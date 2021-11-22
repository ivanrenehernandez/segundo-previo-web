package co.web.elecciones.dao;

import co.web.elecciones.model.Voto;
import co.web.elecciones.util.Conexion;

public class VotoDao extends Conexion<Voto> implements GenericDao<Voto> {

	private static final long serialVersionUID = 1L;

	public VotoDao() {
		super(Voto.class);
	}

}
