	--https://docs.microsoft.com/en-us/sql/t-sql/functions/openquery-transact-sql?view=sql-server-2017

	SELECT * FROM OPENQUERY([DESKTOP-3N2P4FH\HR_INSTANCE], 'select * from HumanResourcesDB.dbo.Customer') 

	--https://stackoverflow.com/questions/54334202/how-to-execute-a-stored-procedure-against-linked-server
	EXEC ('EXECUTE CRM.dbo.GetCustomer @Customer=?', 619) AT Screwdriver

	SELECT userType_id FROM [DESKTOP-3N2P4FH\HR_INSTANCE].HumanResourcesDB.dbo.Employee

