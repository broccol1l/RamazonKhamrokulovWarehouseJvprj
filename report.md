# Course Project: "Warehouse Search System"

**Student:** `Ramazon Khamrokulov`
**Subject Area:** Printed Products Warehouse

***

### 1. Project Overview

The goal of this project was to develop a Java application for searching products in a warehouse. The application is implemented as a console utility that reads data from `.csv` files and allows the user to perform searches based on various criteria.

The main focus was on creating a **Layered Architecture** while adhering to OOP principles such as SOLID, low coupling, and high cohesion. The final implementation includes **advanced features** such as a dynamic criteria-based search system, generic range support, and centralized dependency injection.

***

### 2. Work Progress (Progress Report)

The project was developed in accordance with the 7 stages recommended in the guidelines.

#### Stage 1: Task Clarification
* **Status:** **Completed**
* **Actions:**
    * Project requirements were analyzed.
    * This `report.md` file was created to track progress.
    * A Git repository was initialized for the project.

#### Stage 2: Analysis
* **Status:** **Completed**
* **Actions:**
    * Decomposed the "Printed Products Warehouse" subject area.
    * Defined the main entities: `Product` (base), `Book`, `Magazine` (descendants).

#### Stage 3: Use Cases
* **Status:** **Completed**
* **Actions:**
    * Defined the main user stories:
        * Search for all products (`find`).
        * Search by specific parameters (`find key=value`).
        * Search by parameters with spaces (`find key="value with spaces"`).
        * **[Advanced]** Search by range (`find price=20;60`).
        * **[Advanced]** Sort results by any field (`find sort=price`).
        * Receive feedback on errors (invalid command, invalid format, not found).
        * Exit the application (`exit`).

#### Stage 4: Search for Solutions
* **Status:** **Completed**
* **Actions:**
    * A **Layered Architecture** was chosen as the primary architecture.
    * The **"Command" pattern** was chosen for implementing the Controller layer.
    * The **"Singleton" pattern** was implemented via a single `AppFactory.java` to manage all layer dependencies and **avoid copy-paste code**.
    * An **advanced criteria/parameter system** (using `Parameter.java` interface) was designed to make the search logic flexible and extensible.

#### Stage 5: Software Development
* **Status:** **Completed**
* **Actions:** All application layers were implemented:
    * **Entity Layer:** (`Product.java`, `Book.java`, `Magazine.java`).
    * **Criteria Sub-layer (Advanced):** Implemented the `Parameter.java`, `Range.java`, `SearchCriteria.java`, `ProductSearchCriteria.java` files, along with specific parameter classes (`AuthorParameter.java`, `PriceParameter.java`, etc.).
    * **DAO (Data Access) Layer:** (`ProductDao.java`, `CsvProductDaoImpl.java`). Implemented reading from `.csv` (with `StandardCharsets.UTF_8`) and filtering logic using the advanced `criteria.test()` method.
    * **Service Layer:** (`ProductService.java`, `ProductServiceImpl.java`). Implemented logic to extract the `SortParameter` from the criteria list *before* passing the filtering criteria to the DAO, and then sorting the results.
    * **Controller Layer:** (`Controller.java`, `ControllerImpl.java`). Implemented parsing of user commands into **Parameter objects** using specialized helper methods (`parsePriceParameter`, `parseIntegerRange`). This ensures the **Controller creates behavior**, not just data.
    * **View Layer:** (`View.java`, `ConsoleViewImpl.java`). Implemented the main application loop, including a detailed startup menu.
    * **Startup Layer:** (`Main.java` & `AppFactory.java`). Implemented the entry point using the single `AppFactory` to initialize all services.
    * **Unit Testing:** Wrote JUnit tests (`CsvProductDaoImplTest.java`, `ProductServiceImplTest.java`, `FindCommandTest.java`) that cover the **advanced** filtering and error handling logic.

#### Stage 6: Development Completion
* **Status:** **Completed**
* **Actions:**
    * Conducted final manual testing for all commands, including **combined commands** (`find price=20;60 sort=quantity`) and **all sorting types** (`sort=author`, `sort=pageCount`, etc.).
    * All known bugs (parsing, sorting, `ClassCastException`) were debugged and fixed.

#### Stage 7: Presentation
* **Status:** **Ready for Presentation**
* **Actions:**
    * The application is fully functional and ready for demonstration.

***

### 3. Bonus Features Implemented (K2)

This project implements "Ideas Worth Implementing" to earn bonus points:

1.  **Advanced Criteria System (`Parameter` Interface & OOD):**
    Implementation uses the `Parameter<P>` interface to fully encapsulate search logic, ensuring low coupling and adherence to the **Open-Closed Principle (OCP)**.
2.  **Generic Range Search Support (`Range<T>` Record):**
    Developed a generic `Range<T>` record to support interval searching for any comparable field (e.g., `price=10;50`, `pagecount=500;1000`). This demonstrates advanced use of **Generics** and **Java Records**.
3.  **Centralized Factory (Singleton/Manual DI):**
    The application uses a single, centralized `AppFactory.java` (Singleton pattern) to manage all layer dependencies. This ensures **Manual Dependency Injection** and successfully avoids the penalty associated with using multiple copy-pasted factory files.
4.  **Full Sorting Feature:**
    Implemented a `sort=key` feature that correctly sorts all products by any common or specific field.

***

### 4. Final Results (Deliverables)

1.  **Git Repository:** `https://github.com/broccol1l/RamazonKhamrokulovWarehouseJvprj`
2.  **Progress Report:** This file (`report.md`)
