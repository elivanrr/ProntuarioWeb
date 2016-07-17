package br.med.nader.UTILS;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class testeMysql {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = (Connection) new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta!");
		connection.close();
	}

}
