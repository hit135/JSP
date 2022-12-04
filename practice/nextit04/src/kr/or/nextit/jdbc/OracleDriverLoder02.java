package kr.or.nextit.jdbc;


import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;



public class OracleDriverLoder02 extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Notice : success to load OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Notice : fail to load OracleDriver");
			e.printStackTrace();
		}
	}
	
	private void initConnectionPool() {
		
		try {
			String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String userName = "jsp";
			String pw = "oracle";
			/* import : org.apache.commons.dbcp2 */
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(
					jdbcUrl , userName, pw);
			
			// 커넥션이 유효한지 확인하기 위한 쿼리
			PoolableConnectionFactory poolableConnFactory 
		    	= new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1 from dual");
			
			// 커넥션 풀의 설정하기 (유휴 커넥션 검사주기, 검사여부, 커넥션 최소, 최대 갯수)
			GenericObjectPoolConfig poolConofig = new GenericObjectPoolConfig();
			poolConofig.setTimeBetweenEvictionRunsMillis(1000L *60L * 10L);	// 10분
			poolConofig.setTestWhileIdle(true); 
			poolConofig.setMinIdle(4);
			poolConofig.setMaxTotal(10);	 
			
			//커넥션 풀 생성 
			GenericObjectPool<PoolableConnection> connectionPool 
			   = new GenericObjectPool<>(poolableConnFactory, poolConofig);
			
			//poolableConnFactory 에도 connectionPoold을 연결한다.
			poolableConnFactory.setPool(connectionPool);
			
			
			// 커넥션 풀을 제공하는 JDBC 드라이버를 등록한다. 
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager
										.getDriver("jdbc:apache:commons:dbcp:");
			
			/*생성한 connectionPoold을 study라는 이름으로 커넥션 풀 드라이버에 등록한다.
			이름은 나중에 커넥션 가져올때 사용함*/
			driver.registerPool("study",connectionPool);
			System.out.println("Notice : success to load DBCP");
					
		} catch (Exception e) {
			System.out.println("Notice : fail to load ConnectionPool");
			e.printStackTrace();
		}
	
	}
	
}