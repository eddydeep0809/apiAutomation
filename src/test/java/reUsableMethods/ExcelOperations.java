package reUsableMethods;

import java.util.ArrayList;
import java.util.HashMap;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelOperations {
	
	String filePath			=	System.getProperty("user.dir")+"/testData/testData.xlsx";
	Connection connection 	= null;
	Recordset recordSet 	= null;
	Fillo fillo			=	new Fillo();

	public ArrayList<String> getTcIdToBeExecuted(String tableName){
		String query			=	"Select * FROM "+tableName+ " WHERE ExecuteTestCase='Y'";
		ArrayList<String> tcID	=	new ArrayList<String>();
		try {
			connection				= 	fillo.getConnection(filePath);
			recordSet 				=	connection.executeQuery(query);
			while(recordSet.next()) {
				tcID.add(recordSet.getField("TCID"));
			}
		} catch(Exception NullPointerException) {
			System.out.println("No results found matching the query: "+query);
			System.exit(0);
		} finally {
			recordSet.close();
			connection.close();
		}
		return tcID;
	}
	
	
	public HashMap<String, String> getDataForExecution(String tableName, String tcID) {
		String query							=	"Select * FROM "+tableName+ " WHERE TCID='"+tcID+"'";
		ArrayList <String> columnNames			= 	new ArrayList<String>();
		HashMap<String, String> inputParameter 	= new HashMap<String, String>();
		try {
			connection			= 	fillo.getConnection(filePath);
			recordSet 			=	connection.executeQuery(query);
			columnNames			= 	recordSet.getFieldNames();
			int noOfColumns		=	columnNames.size() ;
			while(recordSet.next()) {
				for(int inputParam = 0; inputParam < noOfColumns ; inputParam++ ) {
					inputParameter.put(columnNames.get(inputParam), recordSet.getField(columnNames.get(inputParam)));
					//System.out.println(columnNames.get(inputParam)+"........"+recordSet.getField(columnNames.get(inputParam)));
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			recordSet.close();
			connection.close();
		}
		return inputParameter;
	}

	public void updateServiceId(String tableName, String tcID, String serviceID) {
		String query	=	"Update "+tableName+ " SET serviceID="+serviceID+" WHERE TCID='"+tcID+"'";
		try {
			connection			= 	fillo.getConnection(filePath);
			connection.executeUpdate(query);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			recordSet.close();
			connection.close();
		}
	}
	
	public void updateServiceName(String tableName, String tcID, String serviceName) {
		String query	=	"Update "+tableName+ " SET serviceName="+serviceName+" WHERE TCID='"+tcID+"'";
		try {
			connection			= 	fillo.getConnection(filePath);
			connection.executeUpdate(query);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			recordSet.close();
			connection.close();
		}
	}
}
