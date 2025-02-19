package manage;

public class QueryManage {


	private String croneSchedulesQuery = "SELECT name FROM cron_schedules LIMIT 2;";







	// ************ GETTERS **************
	public String getCroneSchedulesQuery() {
		return croneSchedulesQuery;
	}
}
