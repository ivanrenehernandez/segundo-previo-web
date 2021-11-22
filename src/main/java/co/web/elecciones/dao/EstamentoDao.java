package co.web.elecciones.dao;

import co.web.elecciones.model.Estamento;
import co.web.elecciones.util.Conexion;

public class EstamentoDao extends Conexion<Estamento> implements GenericDao<Estamento> {

	private static final long serialVersionUID = 1L;

	public EstamentoDao() {
		super(Estamento.class);
	}

}
