<h1> GUI Project CEN 215 - Object Oriented Programming<br/>2021 - 2022 Fall Semester</h1><br/><br/>



<h2> Description </h2>
The focus of this software is the management of all the important steps of a Bookstore / Library. The software keeps data about all
the books in the library, such us ISBN of the book, title, category of the book, supplier, purchased date, purchased price,
original price, selling price, author, stock.
The application has a three-level user system: Librarian, Manager and Administrator. Each of these users have different
views and usage of the software. Note that each of the users has a username and a password to enter in the software.<br/>

**Librarian** - has the right to check out books that a customer may need from the bookstore. This means that the
librarian can create a bill and enter the data of the bought books, such as ISBN of the book and its quantity. If the
book is out of stock or does not exist, an alert will be displayed. The software provides him the total amount
of the bill in a printable format ([BillNo].txt). The updates in the software file are done
automatically by adding the data into the respective files.<br/>

**Manager** - has the right to supply the bookstore with the needed books, meaning that they may enter in the
stock the new book category, and/or add books of the same category to the stock of the bookstore.
They can also check statistics, such as most sold book, user who has sold most books, etc.. Manager can perform all of the actions of a Librarian.<br>

**Admin** - has the right to manage the employees (other Admins, Librarians and Managers), by registering, modifying, and deleting their accounts in this software. They can also perform all of the actions of a Manager.

---

#### Requirements
- JDK 13+
- JavaFX 17+

#### Running the software (Windows)
- Open command prompt (press Windows Button and 'R' at the same time, type `cmd` and click OK).

- In the command prompt, type `cd ` followed by the directory of the bookstore.jar file and hit 'Enter'<br/>(eg `cd C:\--ENTER PATH HERE--\bookstore-software-main\bookstore` ).

- Copy the following code, edit the --ENTER JAVAFX PATH HERE-- part so that the path points to the JavaFX directory, paste the edited code and hit 'Enter'.<br/>`java --module-path "C:\--ENTER JAVAFX PATH HERE--\lib" --add-modules javafx.controls,javafx.fxml -jar bookstore.jar`

- Login using one of the accounts listed below, or those you have created.

#### List of usernames and passwords

- username: `admin`<br/>password: `12345678`

- username: `manager2`<br/>password: `manager2`

- username: `librarian1`<br/>password: `librarian1`

- username: `admin23`<br/>password: `admin23`

#### Bills
Bills will be printed in .txt files at the *\bookstore\bills* folder
