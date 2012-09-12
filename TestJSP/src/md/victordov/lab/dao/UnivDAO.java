package md.victordov.lab.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import md.victordov.lab.connection.ConnectionFactory;
import md.victordov.lab.vo.Universitate;


public class UnivDAO implements GenericDAO<Universitate>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5068467300824574257L;

	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public UnivDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	@Override
	public Collection<Universitate> retrieve() {
		Collection<Universitate> colUniv = new ArrayList<Universitate>();
		try {
			String queryString = "SELECT * FROM universitate";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				Universitate tempUniv = new Universitate();
				tempUniv.setUniversitateId(resultSet.getLong("u_id"));
				tempUniv.setNumeUniversitate(resultSet.getString("nume_univer"));
				tempUniv.setAdresa(resultSet.getString("adresa"));
				tempUniv.setTelefon(resultSet.getString("telefon"));
				colUniv.add(tempUniv);

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
		if (colUniv != null) {
			return colUniv;
		} else
			return null;

	}

	@Override
	public Universitate retrieve(long id) {
		Universitate tempUniv = new Universitate();
		try {
			String queryString = "SELECT * FROM universitate where u_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			resultSet = ptmt.executeQuery();
			resultSet.beforeFirst();
			if (resultSet.next()) {
				tempUniv.setUniversitateId(resultSet.getLong("u_id"));
				tempUniv.setNumeUniversitate(resultSet.getString("nume_univer"));
				tempUniv.setAdresa(resultSet.getString("adresa"));
				tempUniv.setTelefon(resultSet.getString("telefon"));
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

		return tempUniv;

	}

	@Override
	public boolean create(Universitate t) {
		boolean state = true;
		try {
			String queryString = "INSERT INTO universitate(u_id, nume_univer, adresa, telefon) VALUES(?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, t.getUniversitateId());
			ptmt.setString(2, t.getNumeUniversitate());
			ptmt.setString(3, t.getAdresa());
			ptmt.setString(4, t.getTelefon());
			ptmt.executeUpdate();
			System.out.println("Data Added Successfully");
			state = true;
		} catch (SQLException e) {
			e.printStackTrace();
			state = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				state = false;
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				state = false;
			}

		}
		return state;

	}

	@Override
	public boolean update(Universitate t) {
		boolean state = true;
		try {
			String queryString = "UPDATE universitate SET nume_univer=?,adresa=?,telefon=? WHERE u_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, t.getNumeUniversitate());
			ptmt.setString(2, t.getAdresa());
			ptmt.setString(3, t.getTelefon());
			ptmt.setLong(4, t.getUniversitateId());
			ptmt.executeUpdate();
			System.out.println("Table Updated Successfully");
			state = true;
		} catch (SQLException e) {
			e.printStackTrace();
			state = false;
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
		return state;

	}

	@Override
	public boolean delete(Long id) {
		boolean succes = true;
		try {
			String queryString = "DELETE FROM  universitate WHERE u_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			ptmt.executeUpdate();
			System.out.println("Table Updated Successfully");
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
