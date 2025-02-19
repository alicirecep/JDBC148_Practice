import java.sql.*;

public class JDBC {

	/*
type				jdbc:mysql
host/ip				195.35.59.18
port				3306
database_name		u201212290_qaloantec


username			u201212290_qaloanuser
password			HPo?+7r$
	 */

	// Ham haldeki bu bilgiler ile JDBC baglantisi kurmak zordur.
	// O yuzden bu datalari analsilir bir URL haline getirmek zorundayiz.

	/*
	URL: jdbc:mysql://195.35.59.18/u201212290_qaloantec
	USERNAME: u201212290_qaloanuser
	PASSWORD: HPo?+7r$
	 */

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// 1. ADIM: JDBC Sürücüsünü Yükleme

		Class.forName("com.mysql.cj.jdbc.Driver");  //Type 4


		// 2. ADIM Veritabanı Bağlantısı Kurma (Connection)

		String URL = "jdbc:mysql://195.35.59.18/u201212290_qaloantec";
		String USERNAME = "u201212290_qaloanuser";
		String PASSWORD = "HPo?+7r$";

		Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

		// 3. ADIM SQL Sorguları Oluşturma (Query Hazirlama)

		String QUERY = "SELECT * FROM users";

		// 4. ADIM SQL Sorguları Çalıştırma

		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery(QUERY);

		// 5. ADIM Sonuçları İşleme

		// Database'den gelen sonuclar resultSet icerisinde
		// ResultSet icerisinde "ITERATOR" ile islem yapabilirim.

		resultSet.next();
		System.out.println(resultSet.getString("firstname"));

		// Beklenti = Elf

		resultSet.next();
		System.out.println(resultSet.getString(4));

		// Beklenti = Elf / tester



		resultSet.next();
		resultSet.next();
		System.out.println(resultSet.getInt(8));
		// Beklenti = Elf / tester / 85462


		resultSet.absolute(15);
		System.out.println(resultSet.getString(6));
		// Beklenti = Elf / tester / 85462 / bidasa9700@xcmexico.com


		resultSet.previous();
		System.out.println(resultSet.getString(2));
		// Beklenti = Elf / tester / 85462 / bidasa9700@xcmexico.com / Loan


		resultSet.first();
		System.out.println(resultSet.getString(3));
		// Beklenti = Elf / tester / 85462 / bidasa9700@xcmexico.com / Loan / Ferid
		System.out.println(resultSet.getString(4));
		// Beklenti = Elf / tester / 85462 / bidasa9700@xcmexico.com / Loan / Ferid / acenk


		resultSet.beforeFirst();
		resultSet.isBeforeFirst();
		resultSet.afterLast();
		resultSet.isAfterLast();
		int columnIndex = resultSet.findColumn("ref_by");





	}

}
