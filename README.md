# bestBuyAPIAutomation
The bestBuyAPIAutomation project can be used to test the services endpoint wherein various operations like getAllServices, createNewService, getService, updateService, 
and deleteService

Getting Started
Make sure you have NodeJS installed (we require version 4 or newer).
git clone https://github.com/bestbuy/api-playground/
cd api-playground
npm install
npm start


#Framework setup
Import the project in an IDE like eclipse, intellij
Open the testData.xlsx file present in testData folder inside the project and update the input parameters for the respetive test case that you want to execute
For example:
1) ExecuteTestCase: Y (set Y corresponding to the test case you want to execute) 
2) requestURL: request URL of the endpoint
3) statusCode: status code to be validated as part of respone
4) Method: method name to be called in the automation script 
5) serviceID: service id needed for execution of create, get, update and delete requests
6) serviceName: service name needed for create, and update requests

For test run:
Right click on the project and run "Maven test" or can be triggered via cmd 
