package md.victordov.lab.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import md.victordov.lab.common.other.ErrorStringConstants;
import md.victordov.lab.connection.ConnectionFactory;
import md.victordov.lab.vo.Student;

public class StudentDAO implements Serializable, GenericDAO<Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3319859307933397896L;
	private static Logger logger = LogManager.getLogger(StudentDAO.class);
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public StudentDAO() {
	}

	public Collection<Student> retrieve() {
		Collection<Student> colStud = new ArrayList<Student>();
		try {
			String queryString = "SELECT * FROM student";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			while (resultSet.next()) {
				Student tempStud = new Student();

				tempStud.setStudentId(resultSet.getLong("s_id"));
				tempStud.setNume(resultSet.getString("nume"));
				tempStud.setPrenume(resultSet.getString("prenume"));
				tempStud.setGrupa(resultSet.getString("grupa"));
				tempStud.setEmail(resultSet.getString("email"));
				tempStud.setTelFix(resultSet.getString("telFix"));

				colStud.add(tempStud);

			}
		} catch (SQLException e) {
			logger.error(ErrorStringConstants.STUDENT_NOT_FOUND, e);
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

		return colStud;

	}

	public Student retrieve(long id) {
		Student tempStud = new Student();
		try {
			String queryString = "SELECT * FROM student where s_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			resultSet = ptmt.executeQuery();
			resultSet.beforeFirst();
			if (resultSet.next()) {
				tempStud.setStudentId(resultSet.getLong("s_id"));
				tempStud.setNume(resultSet.getString("nume"));
				tempStud.setPrenume(resultSet.getString("prenume"));
				tempStud.setGrupa(resultSet.getString("grupa"));
				tempStud.setEmail(resultSet.getString("email"));
				tempStud.setTelFix(resultSet.getString("telFix"));

			} else {
			}

		} catch (SQLException e) {
			logger.error(ErrorStringConstants.STUDENT_NOT_FOUND, e);
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

		return tempStud;
	}

	public boolean create(Student t) {
		boolean status = true;
		try {
			String queryString = "INSERT INTO student(s_id, nume, prenume,grupa, email, telFix) VALUES(?,?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, t.getStudentId());
			ptmt.setString(2, t.getNume());
			ptmt.setString(3, t.getPrenume());
			ptmt.setString(4, t.getGrupa());
			ptmt.setString(5, t.getEmail());
			ptmt.setString(6, t.getTelFix());
			ptmt.executeUpdate();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;

		} catch (NumberFormatException e) {
			logger.error(ErrorStringConstants.STUDENT_NOT_CREATED, e);

			status = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				logger.error(ErrorStringConstants.PREP_OR_RS, e);
				status = false;
			} catch (Exception e) {
				logger.warn(ErrorStringConstants.ERR_CREATE, e);
				status = false;
			}

		}
		return status;
	}

	public boolean update(Student t) {
		boolean succes = true;
		try {
			String queryString = "UPDATE student SET nume=?,prenume=?,grupa=?,email=?, telFix=? WHERE s_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, t.getNume());
			ptmt.setString(2, t.getPrenume());
			ptmt.setString(3, t.getGrupa());
			ptmt.setString(4, t.getEmail());
			ptmt.setString(5, t.getTelFix());
			ptmt.setLong(6, t.getStudentId());
			ptmt.executeUpdate();
			succes = true;
		} catch (SQLException e) {
			logger.error(ErrorStringConstants.STUDENT_NOT_UPDATED, e);
			succes = false;
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				logger.error(ErrorStringConstants.PREP_OR_RS, e);
			} catch (Exception e) {
				logger.warn(ErrorStringConstants.ERR_UPDATE, e);
			}
		}
		return succes;
	}

	public boolean delete(Long id) {
		boolean succes = true;
		try {
			String queryString = "DELETE FROM  student WHERE s_id=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setLong(1, id);
			ptmt.executeUpdate();
			succes = true;
		} catch (SQLException e) {
			logger.error(ErrorStringConstants.STUDENT_NOT_DELETED, e);
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
