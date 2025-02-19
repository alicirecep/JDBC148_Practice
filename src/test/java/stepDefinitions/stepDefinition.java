package stepDefinitions;

import io.cucumber.java.en.Given;
import manage.QueryManage;
import utilities.ConfigReader;
import utilities.JDBCReusableMethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class stepDefinition {

	Connection connection;
	Statement statement;
	ResultSet resultSet;

	String QUERY;
	QueryManage queryManage = new QueryManage();

	@Given("Database baglantisi kurulur.")
	public void database_baglantisi_kurulur() throws SQLException {

	connection = DriverManager.getConnection(ConfigReader.getProperty("URL"),
									ConfigReader.getProperty("USERNAME"),
									ConfigReader.getProperty("PASSWORD"));

	// JDBCReusableMethods.createConnection();

	}
	@Given("SQL Query'si hazirla ve calistir.")
	public void sql_query_si_hazirla_ve_calistir() throws SQLException {

		String QUERY = "SELECT DISTINCT user_id FROM deposits WHERE amount BETWEEN 100 AND 500;";
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		resultSet = statement.executeQuery(QUERY);

		// resultSet = JDBCReusableMethods.executeQuery(QUERY);

	}
	@Given("Sonuclari dogrula.")
	public void sonuclari_dogrula() throws SQLException {

		/*
		    1
			9
			10
			12
			64
		*/
		List<Integer> expectedResult = new ArrayList<>();

			expectedResult.add(1);
			expectedResult.add(9);
			expectedResult.add(10);
			expectedResult.add(12); //12
		    expectedResult.add(64);

		List<Integer> actualResult = new ArrayList<>();

		while(resultSet.next()){
		actualResult.add(resultSet.getInt(1));

		}

		assertEqualsNoOrder(actualResult,expectedResult);


	}
	@Given("Database baglantisini sonlandir.")
	public void database_baglantisini_sonlandir() throws SQLException {
		/*
		resultSet.close();
		statement.close();
		connection.close();
       */

		JDBCReusableMethods.closeConnection();
	}

// ******************* cron_schedules query **********************

	@Given("\\(cron_schedules) SQL query'si  calistirilir")
	public void cron_schedules_sql_query_si_calistirilir() {

		QUERY = queryManage.getCroneSchedulesQuery();
		resultSet = JDBCReusableMethods.executeQuery(QUERY);

	}
	@Given("\\(cron_schedules) sonuclari  dogrulanir.")
	public void cron_schedules_sonuclari_dogrulanir() throws SQLException {

		List<String> expectedResultList = new ArrayList<>();
		expectedResultList.add("5 Minutes");
		expectedResultList.add("10 Minutes");

		List<String> actualResultList = new ArrayList<>();
		while(resultSet.next()){
			actualResultList.add(resultSet.getString(1));
		}

		assertEqualsNoOrder(actualResultList, expectedResultList);

	}







}
