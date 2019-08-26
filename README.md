# ChatAppl

Notice: no Intergration tests, no Web client, no tests for Rest.

src\main\java\com\chatapp\app\Demo.java - contains demo of Dao
src\test\java\com\chatapp\entity\dao\impl\* - Unit tests for Dao
src\main\java\com\chatapp\controller\UserController.java - Web service 


# Wall

A user should be able to see a list of the messages they've posted, in reverse chronological order:
GET wall:       GET http://localhost:8080/chatapplication/wall/{id}


# Timeline

A user should be able to see a list of the messages posted by all the people they follow, in reverse chronological order.
GET timeline:   GET   http://localhost:8080/chatapplication/timeline/{id}


# Following

A user should be able to follow another user. Following doesn't have to be reciprocal: Alice can follow Bob without Bob having to follow Alice.
Following PUT http://localhost:8080/chatapplication/user/{id}/{followingId}

# Posting

A user should be able to post a 140 character message.

POST message:    POST http://localhost:8080/chatapplication/{id}/message

# Start Webservice:

mvn tomcat7:run

 
