=========================================================================================================================
The project was created with MySQL / Maria DB.
The following schema was designed to serve the purpose of the application.

1. Database Used : Mysql
2. Database Name : transactions
3. Table Name : transaction_history
4. Schema used  in Table : transaction_number | query | parameter1 | parameter2 | result | transactime 
     transaction_number : Primary Key Stores Value of transaction.
     query : Operation that has been performed.
     parameter1 : First input to the operation.
     parameter2 : Second input to the operation.
     result : Result of the operation.
     transactime : The time at which transaction happened. 
5. Database URL : "jdbc:mysql://<IP>:<Port>/transactions" /* "jdbc:mysql://localhost:3306/transactions" */
6. Database username = <username> /* Username = "root" */
7. Database Password = <password> /* password = "harshad" */     

=========================================================================================================================
