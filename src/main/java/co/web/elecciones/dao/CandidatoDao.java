package co.web.elecciones.dao;

import co.web.elecciones.model.Candidato;
import co.web.elecciones.util.Conexion;

public class CandidatoDao extends Conexion<Candidato> implements GenericDao<Candidato> {

	private static final long serialVersionUID = 1L;

	public CandidatoDao() {
		super(Candidato.class);
	}

}
