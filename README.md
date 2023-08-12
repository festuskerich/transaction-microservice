SpringBoot Assessment Coding Test

**Title: Spring Boot Assessment Coding Test Duration: 48 hours**

**Instructions:**

- You are required to develop a Spring Boot application based on the given requirements.
- Use Java and Spring Boot to complete the tasks.
- The assessment consists of multiple tasks, each focusing on different aspects of Spring Boot development.
- Make sure to write clean, well-structured code and follow best practices.
- You can use any online resources, documentation, and libraries as needed.
- Make sure to use a Postgres db
- In case you need any clarifications on this test, don’t hesitate to ask via the emails listed below.
- Kindly ensure the code is submitted on time.

Task 1: Entity and Repository

- Create a Spring Boot application.
- Create an entity named "Transaction" with the following attributes:
  - id (auto-generated),
  - senderName,
  - receiverName,
  - amount,
  - transactionDate.
- Create a JPA repository for the "Transaction" entity.

Task 2: Service and Controller

- Create a service named "TransactionService" with the following methods:
- List<Transaction> **getAllTransactions()**: Returns a list of all transactions.
- Transaction **getTransactionById(Long id)**: Returns a transaction based on the given ID.
- Transaction **createTransaction(Transaction transaction)**: Creates a new transaction and returns the saved transaction.
- Transaction **updateTransaction(Long id, Transaction transaction)**: Updates an existing transaction based on the given ID and new transaction data.
  - void deleteTransaction(Long id): Deletes a transaction based on the given ID.
- Create a REST controller named "TransactionController" with the following endpoints:
  - GET /transactions: Returns a JSON array of all transactions.
  - GET /transactions/{id}: Returns a JSON object of the transaction with the given ID.
  - POST /transactions: Creates a new transaction using the request body JSON.
  - PUT /transactions/{id}: Updates an existing transaction with the new data from the request body JSON.
  - DELETE /transactions/{id}: Deletes the transaction with the given ID.

Task 3: Exception Handling

- Implement exception handling for the controller. Create custom exception classes for the following scenarios:
- **TransactionNotFoundException** (HTTP status 404) for when a transaction with a specific ID is not found.
- **InvalidTransactionException** (HTTP status 400) for invalid input data when creating or updating a transaction.

Task 4: Unit Testing

1. Write unit tests for the TransactionService methods using JUnit and Mockito.
1. Cover scenarios for successful operations and exception scenarios.

Bonus Task (Optional):

Implement pagination for the */transactions* endpoint to return transactions in pages.

Task 5: Submission

- Create a public GitHub repository for your project.
- Push your code to the repository, excluding the build artifacts.

