Student and courses app with workflow with docker image database test. 
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
http://localhost:8083/courses
Body:
{
    "courseName": "Java"
}

Get course
http://localhost:8083/courses

Put courses
http://localhost:8083/courses
body example:
{
"id":2,
"courseName": "Java2"
}

Delete course
http://localhost:8083/courses/{courseId}


The tests are run automatically when the app is pushed and pulled to/in github. 


