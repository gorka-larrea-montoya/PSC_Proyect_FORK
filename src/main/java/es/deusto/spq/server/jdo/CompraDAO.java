package es.deusto.spq.server.jdo;

import java.util.List;

public class CompraDAO extends DataAccessObjectBase implements IDataAccessObject<Compra>  {

	private static CompraDAO instance;
	public static CompraDAO getInstance() {
		if (instance == null) {
			instance = new CompraDAO();
		}
		return instance;
	}
	@Override
	public boolean Save(Compra object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(Compra object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Compra> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compra find(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Compra object) {
		// TODO Auto-generated method stub
		
	}

}
