package tests;

import java.util.List;

import co.web.elecciones.dao.TipoDocumentoDao;
import co.web.elecciones.model.Tipodocumento;

public class Test {
	
	public static void main(String[] args) {
		TipoDocumentoDao tidoDao = new TipoDocumentoDao();
		
		List<Tipodocumento> listaTipoDocumento = tidoDao.list();
		for (Tipodocumento tipodocumento : listaTipoDocumento) {
			System.out.println(tipodocumento.getDescripcion());
		}
	}

}
