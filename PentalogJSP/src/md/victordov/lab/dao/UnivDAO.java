package md.victordov.lab.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import md.victordov.lab.common.other.ErrorStringConstants;
import md.victordov.lab.connection.ConnectionFactory;
import md.victordov.lab.vo.Universitate;

public class UnivDAO implements GenericDAO<Universitate>, Serializable {

	/**
	 * @author victor Clasa UnivDAO - Universitate Data Access Object, classa
	 *         destinata sa faca legatura dintre baza de date si utilizator
	 *         gestionind clasa Universitate Executa 4 tipuri de functii CRUD
	 */
	private static final long serialVersionUID = -5068467300824574257L;
	private static Logger logger = LogManager.getLogger(UnivDAO.class);

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
			logger.error(ErrorStringConstants.UNIVERSITATE_NOT_FOUND, e);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				logger.error(ErrorStringConstants.PREP_OR_RS, e);
			} catch (Exception e) {
				logger.warn(ErrorStringConstants.ERR_RETRIEVE, e);
			}

		}
		if (colUniv != null) {
			return colUniv;
		} else
			return null;

	}

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
			logger.error(ErrorStringConstants.UNIVERSITATE_NOT_FOUND, e);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();

			} catch (SQLException e) {
				logger.error(ErrorStringConstants.PREP_OR_RS, e);
			} catch (Exception e) {
				logger.warn(ErrorStringConstants.ERR_RETRIEVE, e);
			}

		}

		return tempUniv;

	}

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
			state = true;
		} catch (SQLException e) {
			logger.error(ErrorStringConstants.UNIVERSITATE_NOT_CREATED, e);
			state = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				state = false;
				logger.error(ErrorStringConstants.PREP_OR_RS, e);
			} catch (Exception e) {
				logger.warn(ErrorStringConstants.ERR_CREATE, e);
				state = false;
			}

		}
		return state;

	}

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
			state = true;
		} catch (SQLException e) {
			logger.error(ErrorStringConstants.UNIVERSITATE_NOT_UPDATED, e);
			state = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}

			catch (SQLException e) {
				logger.error(ErrorStringConstants.PREP_OR_RS, e);
			} catch (Exception e) {
				logger.warn(ErrorStringConstants.ERR_UPDATE, e);

			}
		}
		return state;

	}

	public boolean delete(Long id) {
		boolean succes = true;
		try {
			String queryString = "DELETE FROM  universitate WHERE u_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			ptmt.executeUpdate();
			succes = true;
		} catch (SQLException e) {
			logger.error(ErrorStringConstants.UNIVERSITATE_NOT_DELETED, e);
			succes = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}

			catch (SQLException e) {
				logger.error(ErrorStringConstants.PREP_OR_RS, e);
			} catch (Exception e) {
				logger.warn(ErrorStringConstants.ERR_DELETE, e);

			}
		}
		return succes;
	}

}
