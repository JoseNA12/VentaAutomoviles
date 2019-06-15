USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_CreditGivenSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenSelect] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenSelect] 
    @credit_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [credit_id], [order_id], [date], [creditPlan_id], [balance], [mensualPayment], [creditStatus] 
	FROM   [dbo].[CreditGiven] 
	WHERE  ([credit_id] = @credit_id OR @credit_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CreditGivenInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenInsert] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenInsert] 
    @order_id bigint = NULL,
    @creditPlan_id int = NULL,
    @balance money = NULL,
    @mensualPayment float = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CreditGiven] ([order_id], [date], [creditPlan_id], [balance], [mensualPayment], [creditStatus])
	SELECT @order_id, GETDATE(), @creditPlan_id, @balance, @mensualPayment, 1
	
	-- Begin Return Select <- do not remove
	SELECT [credit_id], [order_id], [date], [creditPlan_id], [balance], [mensualPayment], [creditStatus]
	FROM   [dbo].[CreditGiven]
	WHERE  [credit_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CreditGivenUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenUpdate] 
    @credit_id int,
    @order_id bigint = NULL,
    @date date = NULL,
    @creditPlan_id int = NULL,
    @balance money = NULL,
    @mensualPayment float = NULL,
    @creditStatus int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CreditGiven]
	SET    [order_id] = @order_id, [date] = @date, [creditPlan_id] = @creditPlan_id, [balance] = @balance, [mensualPayment] = @mensualPayment, [creditStatus] = @creditStatus
	WHERE  [credit_id] = @credit_id
	
	-- Begin Return Select <- do not remove
	SELECT [credit_id], [order_id], [date], [creditPlan_id], [balance], [mensualPayment], [creditStatus]
	FROM   [dbo].[CreditGiven]
	WHERE  [credit_id] = @credit_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CreditGivenDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenDelete] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenDelete] 
    @credit_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CreditGiven]
	WHERE  [credit_id] = @credit_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

