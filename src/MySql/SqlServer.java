package MySql;

import java.sql.*;

public class SqlServer {
	/*用户登录*/
	public static boolean UserIslawful(String name,String password)throws SQLException,ClassNotFoundException{
		boolean flag;
		//Load the JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
				
		//Establish a connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userInformationOfDictionary","scott","tiger");
		System.out.println("Database connected");
				
		PreparedStatement preparedStatement = connection.prepareStatement("select password from Information " + "where userName = ?");
		preparedStatement.setString(1, name);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			if(password.equals(resultSet.getString(1))){
				flag = true;
			}
			else flag = false;
		}
		else flag = false;
				
		//Close the connection
		connection.close();
		return flag;
	}
	
	/*用户注册*/
	public static boolean SetAccount(String name,String password)throws SQLException,ClassNotFoundException{
		boolean flag;
		//Load the JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
				
		//Establish a connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userInformationOfDictionary","scott","tiger");
		System.out.println("Database connected");
				
		PreparedStatement preparedStatement = connection.prepareStatement("select password from Information " + "where userName = ?");
		preparedStatement.setString(1, name);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			flag = false;
		}
		else {
			flag = true;
			preparedStatement = connection.prepareStatement("insert into Information (userName,password) " + "value (?,?)");
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,password);
			preparedStatement.executeUpdate();
		}
				
		//Close the connection
		connection.close();
		return flag;
	}
	
	public static void Inclination(String word, int type)throws SQLException,ClassNotFoundException{
		//Load the JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
						
		//Establish a connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/userInformationOfDictionary","scott","tiger");
		System.out.println("Database connected");

		PreparedStatement preparedStatement = connection.prepareStatement("select numOfInclination from Inclination " + "where word = ?");
		preparedStatement.setString(1, word);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			preparedStatement = connection.prepareStatement("update Inclination set numOfInclination = ? " + "where word = ?");
			int num = resultSet.getInt(1);
			if(type==0){
				num --;
				preparedStatement.setInt(1, num);
				preparedStatement.setString(2, word);
				preparedStatement.executeUpdate();
			}
			else{
				num ++;
				preparedStatement.setInt(1, num);
				preparedStatement.setString(2, word);
				preparedStatement.executeUpdate();
			}
		}
		else{
			preparedStatement = connection.prepareStatement("insert into Inclination (word,numOfInclination) " + "value (?,?)");
			preparedStatement.setString(1, word);
			preparedStatement.setInt(2, 1);
			preparedStatement.executeUpdate();
		}
		connection.close();
	}
	
	public static void main(String[] args)throws SQLException,ClassNotFoundException{
		Inclination("happy",0);
	}

}
