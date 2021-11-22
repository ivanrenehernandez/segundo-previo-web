package co.web.elecciones.dao;

import co.web.elecciones.model.Tipodocumento;
import co.web.elecciones.util.Conexion;

public class TipoDocumentoDao extends Conexion<Tipodocumento> implements GenericDao<Tipodocumento> {

	private static final long serialVersionUID = 1L;

	public TipoDocumentoDao() {
		super(Tipodocumento.class);
	}

}
