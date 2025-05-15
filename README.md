Student and courses app. 
Many to many relationsship - A student can take several courses and courses can have several students. 

To run the project: 

Post student: 
http://localhost:8083/students
  Body: {
    
    "firstName": "Anna",
    "lastName": "Andersson"
}

Delete student:
http://localhost:8083/students/{studentId}

Get student:
http://localhost:8083/students

Put student
http://localhost:8083/students/{studentId}
  Body: {
    
    "firstName": "Anna",
    "lastName": "Andersson"
}

Post Course

