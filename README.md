# Back-end developer assignment

Here is a solution of the task about implementing a 
system for processing batches of booking requests.

#How to run

According to the assignment, I shouldn't provide any
user interface. So, to interact with the following application
you need any simple HttpRequester. You can POST and GET data in
JSON format, but pay attention to JSON structure. To POST any
data, you should follow given form example:

     {
    "access":
	      {
             "opening":"0900",
             "closing": "1730"
          },
    "events": [
          {
             "requestSubmissionTime":"2011-03-17 10:17:06",
             "employeeId": "EMP001",
           	 "startTime": "2011-03-21 09:00",
           	 "duration": "2"
          },
	
          {
              "requestSubmissionTime":"2011-03-16 12:34:56",
              "employeeId": "EMP002",
              "startTime": "2011-03-21 09:00",
              "duration": "2"
          },
  
  	      {
             "requestSubmissionTime":"2011-03-16 09:28:23",
             "employeeId": "EMP003",
           	 "startTime": "2011-03-22 14:00",
           	 "duration": "2"
          },
           
          {
             "requestSubmissionTime":"2011-03-17 11:23:45",
             "employeeId": "EMP004",
           	 "startTime": "2011-03-22 16:00",
           	 "duration": "1"
          },
      
          {
             "requestSubmissionTime":"2011-03-15 17:29:12",
             "employeeId": "EMP005",
           	 "startTime": "2011-03-21 16:00",
           	 "duration": "3"
          }
       ]
    }
    
Screen of working programm:

![alt tag](https://github.com/shyrikol/dev-assignment/tree/master/dev-assignment/src/main/resources/screen1.png)