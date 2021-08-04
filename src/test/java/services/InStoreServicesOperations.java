package services;

import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.Test;
import reUsableMethods.ExcelOperations;

public class InStoreServicesOperations extends Services{

	static ExcelOperations excelOperations	=	new ExcelOperations();
	HashMap<String, String> inputParameter	=	new HashMap<String, String>();	
	@Test
	public void servicesOperations( ) {
		ArrayList<String> tcID	=	excelOperations.getTcIdToBeExecuted("Services");
		if(!tcID.isEmpty()) {
			for(int testCase = 0; testCase < tcID.size(); testCase++) {
				inputParameter		=	excelOperations.getDataForExecution("Services", tcID.get(testCase));
				String methodName	=	inputParameter.get("Method");
				String currentTCID	=	tcID.get(testCase);
				switch (methodName) {
				case "getAllServices":
					getAllServices(inputParameter.get("requestURL"), inputParameter.get("statusCode"));
					break;
				case "createNewService":
					String serviceID = createNewService(inputParameter.get("requestURL"), inputParameter.get("serviceName"), inputParameter.get("statusCode"));
					excelOperations.updateServiceId("Services", currentTCID, serviceID);
					break;
				case "getService":
					getService(inputParameter.get("requestURL"), inputParameter.get("serviceID"), inputParameter.get("serviceName"), inputParameter.get("statusCode"));
					break;
				case "updateService":
					updateService(inputParameter.get("requestURL"), inputParameter.get("serviceID"), inputParameter.get("serviceName"), inputParameter.get("statusCode"));
					break;
				case "deleteService":
					deleteService(inputParameter.get("requestURL"), inputParameter.get("serviceID"), inputParameter.get("statusCode"));
					break;
				}
			}
		} else {
			System.out.println("No test case to be implmented");
		}
	}

}
