USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_CreditGiven-PaymentSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGiven-PaymentSelect] 
END 
GO
CREATE PROC [dbo].[usp_CreditGiven-PaymentSelect] 
    @customer_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	SELECT cgp.[payment_id], cgp.[credit_id], cgp.[payment], cgp.[date], cgp.paymentMethod_id, pm.name, cg.nextPayment_date
	FROM   [dbo].[CreditGiven-Payment]  cgp
	inner join PaymentMethod pm on pm.paymentMethod_id = cgp.paymentMethod_id
	inner join CreditGiven cg on cg.credit_id = cgp.credit_id
	inner join SalesOrder so on so.salesOrder_id = cg.order_id
	--inner join [DESKTOP-3N2P4FH\HR_INSTANCE].HumanResourcesDB.dbo.Customer c on c.customer_id = so.customer_id
	WHERE  (so.customer_id = @customer_id OR @customer_id IS NULL) 
GO

IF OBJECT_ID('[dbo].[usp_CreditGiven-PaymentInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGiven-PaymentInsert] 
END 
GO
CREATE PROC [dbo].[usp_CreditGiven-PaymentInsert] 
    @credit_id int = NULL,
    @payment money = NULL,
    @paymentMethod_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CreditGiven-Payment] ([credit_id], [payment], [date], [paymentMethod_id])
	SELECT @credit_id, @payment, GETDATE(), @paymentMethod_id
	
	DECLARE @next_payment date;
	SET @next_payment = (SELECT dateadd(m, 1, getdate()));

	EXEC [usp_CreditGivenUpdateNextPayment] @credit_id = 1, @nextPayment_date = @next_payment;

	-- Begin Return Select <- do not remove
	SELECT [payment_id], [credit_id], [payment], [date], [paymentMethod_id]
	FROM   [dbo].[CreditGiven-Payment]
	WHERE  [payment_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CreditGiven-PaymentUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGiven-PaymentUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CreditGiven-PaymentUpdate] 
    @payment_id int,
    @credit_id int = NULL,
    @payment money = NULL,
    @date date = NULL,
    @paymentMethod_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CreditGiven-Payment]
	SET    [credit_id] = @credit_id, [payment] = @payment, [date] = @date, [paymentMethod_id] = @paymentMethod_id
	WHERE  [payment_id] = @payment_id
	
	-- Begin Return Select <- do not remove
	SELECT [payment_id], [credit_id], [payment], [date], [paymentMethod_id]
	FROM   [dbo].[CreditGiven-Payment]
	WHERE  [payment_id] = @payment_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CreditGiven-PaymentDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGiven-PaymentDelete] 
END 
GO
CREATE PROC [dbo].[usp_CreditGiven-PaymentDelete] 
    @payment_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CreditGiven-Payment]
	WHERE  [payment_id] = @payment_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

