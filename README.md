# Library_Management
<b>Author - Nikhil Singhal</b>
<hr>

<h3>Description</h3>
The Library Management System is a Java-based console application that allows students, teachers, and librarians to interact with a library database. The system uses Object-Oriented Programming (OOP) principles for functionality and JDBC (Java Database Connectivity) for seamless interaction with a MySQL database. It supports the following core functionalities:<br>
<ul>
<li><strong>Student & Teacher Login/Signup:</strong> Users (students and teachers) can sign up, log in, and manage their book-related activities.</li>
<li><strong>Book Search and Management:</strong> Students and teachers can search for available books and view their issued books.</li>
<li><strong>Librarian Portal:</strong> Librarians can manage the library’s inventory, add and update books, issue books to students and teachers, and mark books as returned after being issued.</li>
</ul>
<hr>

<h3>Features</h3>
<ul>
<li><strong>User Authentication:</strong>
<ul>
<li>Students and teachers can sign up and log in to view available books and their issued books.</li>
<li>Librarians have a separate login to manage library records.</li>
</ul>
<li><strong>Book Management:</strong>
<ul>
<li>Students and teachers can search for available books by genre.</li>
<li>Librarians can add new books and update the details of existing books in the library database.</li>
</ul>
<li><strong>Book Issuance:</strong>
<ul>
<li>Librarians can issue books to students and teachers, and keep track of issued books.</li>
</ul>  
<li><strong>Book Return:</strong>
<ul>
<li>After issuing, the librarian can submit the returned books and update the records accordingly.</li>
</ul>
<li><strong>Database Integration:</strong>
<ul>
<li>Uses JDBC to connect and interact with a MySQL database to store and manage information regarding students, teachers, books, and book transactions (issues and returns).</li>
</ul>
</ul>
<hr>

<h3>Technologies Used</h3>
<ul>
<li><strong>Programming Language:</strong> Java</li>
<li><strong>Concepts:</strong> Object-Oriented Programming (OOP)</li>
<li><strong>Database:</strong> MySQL</li>
<li><strong>Database Connectivity:</strong> JDBC (Java Database Connectivity)</li>
</ul>
<hr>

<h3>Prerequisites</h3>
<ol>
<li>Java Development Kit (JDK) installed.</li>
<li>A MySQL database server running</li>
<li>JDBC Driver for your database.</li>
</ol>
<hr>

<h3>Database Setup</h3>
<ol>
<li>Create a MySQL database and import the provided SQL schema to set up the following tables:</li>
<ul>
<li>student (Student details)</li>
<li>teacher (Teacher details)</li>
<li>book (Book information)</li>
<li>studentBookIssue, teacherBookIssue (Records of issued books)</li>
<li>studentBookSubmit, teacherBookSubmit (Records of returned books)</li>
</ul>
<li>Update the JDBC connection details (like database URL, username, and password) in the code to match your MySQL setup.</li>
</ol>
<hr>

<h3>Usage</h3>
<ol>
<li>Sign up as a student or teacher to create an account.</li>
<li>Log in with your credentials to search for available books, view issued books, and manage your account.</li>
<li>Librarian login allows for managing the library’s book inventory, issuing books, and handling returns.</li>
<li>All book and user information is stored and managed using a MySQL database.</li>
</ol>
<hr>

<h3>Future Enhancements</h3>
<ul>
<li>Add support for overdue book tracking and fine calculation.<li>
<li>Implement a graphical user interface (GUI) for a more user-friendly experience.<li>
<li>Add a feature to search for books by multiple filters (e.g., genre, publication year).<li>
</ul>
<hr>

<h3>Contributing</h3>
<p>Contributions are welcome! If you'd like to contribute to the project, feel free to fork the repository and submit a pull request.</p>
<hr>

<h1>Thank You</h1>

