package md.victordov.lab.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import md.victordov.lab.connection.ConnectionFactory;
import md.victordov.lab.vo.Curs;


public class CursDAO implements Serializable, GenericDAO<Curs> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -380776877416367670L;

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public CursDAO() {

	}

	
	public Collection<Curs> retrieve() {

		Collection<Curs> colCurs = new ArrayList<Curs>();
		try {
			String queryString = "SELECT * FROM curs";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				Curs tempCurs = new Curs();
				tempCurs.setCursId(resultSet.getLong("c_id"));
				tempCurs.setNumeCurs(resultSet.getString("nume_curs"));
				tempCurs.setUniversitateId(resultSet.getLong("u_id"));
				tempCurs.setProfesorId(resultSet.getLong("p_id"));

				colCurs.add(tempCurs);

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

		return colCurs;

	}

	
	public Curs retrieve(long id) {
		Curs tempCurs = new Curs();
		try {
			String queryString = "SELECT * FROM curs where c_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			resultSet = ptmt.executeQuery();
			resultSet.beforeFirst();
			if (resultSet.next()) {

				tempCurs.setCursId(resultSet.getLong("c_id"));
				tempCurs.setNumeCurs(resultSet.getString("nume_curs"));
				tempCurs.setUniversitateId(resultSet.getLong("u_id"));
				tempCurs.setProfesorId(resultSet.getLong("p_id"));
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
		return tempCurs;
	}

	
	public boolean create(Curs t) {
		boolean status = true;
		try {
			String queryString = "INSERT INTO curs(c_id, nume_curs, u_id, p_id) VALUES(?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, t.getCursId());
			ptmt.setString(2, t.getNumeCurs());
			ptmt.setLong(3, t.getUniversitateId());
			ptmt.setLong(4, t.getProfesorId());
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

	
	public boolean delete(Long id) {
		boolean succes = true;
		try {
			String queryString = "DELETE FROM curs WHERE c_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
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

	
	public boolean update(Curs t) {
		boolean succes = true;
		try {
			String queryString = "UPDATE curs SET nume_curs=?,u_id=?,p_id=? WHERE c_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, t.getNumeCurs());
			ptmt.setLong(2, t.getUniversitateId());
			ptmt.setLong(3, t.getProfesorId());
			ptmt.setLong(4, t.getCursId());
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

}
