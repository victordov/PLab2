package md.victordov.lab.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.connection.ConnectionFactory;
import md.victordov.lab.vo.Profesor;

public class ProfesorDAO implements Serializable, GenericDAO<Profesor> {

    /**
	 * 
	 */
    private static final long serialVersionUID = -4147876920484596173L;

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
	Connection conn;
	conn = ConnectionFactory.getInstance().getConnection();
	return conn;
    }

    @Override
    public Collection<Profesor> retrieve() throws MyDaoException {
	Collection<Profesor> colProf = new ArrayList<Profesor>();
	try {
	    String queryString = "SELECT * FROM profesor";
	    connection = getConnection();
	    ptmt = connection.prepareStatement(queryString);
	    resultSet = ptmt.executeQuery();

	    while (resultSet.next()) {
		Profesor tempProf = new Profesor();

		tempProf.setProfesorId(resultSet.getLong("p_id"));
		tempProf.setNume(resultSet.getString("nume"));
		tempProf.setPrenume(resultSet.getString("prenume"));
		tempProf.setAdresa(resultSet.getString("adresa"));
		colProf.add(tempProf);

	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new MyDaoException("numar.mare",e);
	} finally {
	    try {
		if (resultSet != null)
		    resultSet.close();
		if (ptmt != null)
		    ptmt.close();
		if (connection != null)
		    connection.close();

	    } catch (SQLException e) {
		e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return colProf;
    }

    @Override
    public Profesor retrieve(long id) {
	Profesor tempProf = new Profesor();
	try {
	    String queryString = "SELECT * FROM profesor where p_id=?";
	    connection = getConnection();
	    ptmt = connection.prepareStatement(queryString);
	    ptmt.setLong(1, id);
	    resultSet = ptmt.executeQuery();
	    resultSet.beforeFirst();
	    if (resultSet.next()) {
		tempProf.setProfesorId(resultSet.getLong("p_id"));
		tempProf.setNume(resultSet.getString("nume"));
		tempProf.setPrenume(resultSet.getString("prenume"));
		tempProf.setAdresa(resultSet.getString("adresa"));
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (resultSet != null)
		    resultSet.close();
		if (ptmt != null)
		    ptmt.close();
		if (connection != null)
		    connection.close();

	    } catch (SQLException e) {
		e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return tempProf;
    }

    @Override
    public boolean create(Profesor t) {
	boolean status = true;
	try {
	    String queryString = "INSERT INTO profesor(p_id, nume, prenume, adresa) VALUES(?,?,?,?)";
	    connection = getConnection();
	    ptmt = connection.prepareStatement(queryString);
	    ptmt.setLong(1, t.getProfesorId());
	    ptmt.setString(2, t.getNume());
	    ptmt.setString(3, t.getPrenume());
	    ptmt.setString(4, t.getAdresa());
	    ptmt.executeUpdate();
	    status = true;
	} catch (SQLException e) {
	    e.printStackTrace();
	    status = false;
	} finally {
	    try {
		if (ptmt != null)
		    ptmt.close();
		if (connection != null)
		    connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
		status = false;
	    } catch (Exception e) {
		e.printStackTrace();
		status = false;
	    }
	}
	return status;
    }

    @Override
    public boolean update(Profesor t) {
	boolean succes = true;
	try {
	    String queryString = "UPDATE profesor SET nume=?,prenume=?,adresa=? WHERE p_id=?";
	    connection = getConnection();
	    ptmt = connection.prepareStatement(queryString);
	    ptmt.setString(1, t.getNume());
	    ptmt.setString(2, t.getPrenume());
	    ptmt.setString(3, t.getAdresa());
	    ptmt.setLong(4, t.getProfesorId());
	    ptmt.executeUpdate();
	    succes = true;
	} catch (SQLException e) {
	    e.printStackTrace();
	    succes = false;
	} finally {
	    try {
		if (ptmt != null)
		    ptmt.close();
		if (connection != null)
		    connection.close();
	    }

	    catch (SQLException e) {
		e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();

	    }
	}
	return succes;
    }

    @Override
    public boolean delete(Long id) {
	boolean status = true;
	try {
	    String queryString = "DELETE FROM profesor WHERE p_id=?";
	    connection = getConnection();
	    ptmt = connection.prepareStatement(queryString);
	    ptmt.setLong(1, id);
	    ptmt.executeUpdate();
	    status = true;
	} catch (SQLException e) {
	     e.printStackTrace();
	    status = false;
	}  finally {
	    try {
		if (ptmt != null)
		    ptmt.close();
		if (connection != null)
		    connection.close();
	    }

	    catch (SQLException e) {
		e.printStackTrace();
		status = false;
	    } catch (Exception e) {
		e.printStackTrace();
		status = false;

	    }
	}
	return status;
    }

}
