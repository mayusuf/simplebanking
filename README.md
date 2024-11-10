Project Requirements

1) Design the class diagram to a banking system with the following Use cases:
• Create a personal account
• Create a Company account (checking or savings)
• Deposit money
• Withdraw money
• Generate a report of accounts


The operations for each action are:
◦ When a deposit or withdrawal is done to a company account, the system logs the transaction.
◦ When a deposit or withdrawal is done to a personal account, and the amount was larger than $500
or the resulting amount is negative, the bank shows the warning message to UI.
◦ Customers can have more than one account. The bank system needs to keep track of the history of
deposits and withdrawals.

2) Design the class diagram for a Credit-Card processing system with the following use cases:
• Create a credit card account
• Deposit money
• Charge the account
• Generate monthly billing report


Based on those client specified interfaces, you should provide 3 types of credit cards, with different interest
rates for each:
 gold | silver | bronze

monthly interest

(MI)
6% 8% 10%

minimum payment
(MP)
10% 12% 14%

The operations for each action are:
◦ When a card is charged for more than $400, the system shows notification in cardholders profile notification about the
transaction.
◦ Customers can have more than one credit card (account)
◦ The system needs to keep track of the history of charges and payments. For every payment or
charge, the system needs to store the date, name, and amount of the transaction. 


◦ The monthly billing report (generated from the monthly report button) should show:
- previous balance: balance from last month
- total charges: total of all charges for this month
- total credits: total of all payments for this month
- new balance = previous balance – total credits + total charges + MI * (previous balance
– total credits)
- total due = MP * new balance
◦ When a deposit or withdrawal is done to a company account, the system shows notification about the transaction.
◦ When a deposit or withdrawal is done to a personal account, and the amount was larger than $400
or the resulting amount is negative, the bank shows notification about the transaction.
◦ Customers can have more than one account. The bank system needs to keep track of the history of
deposits and withdrawals. 
