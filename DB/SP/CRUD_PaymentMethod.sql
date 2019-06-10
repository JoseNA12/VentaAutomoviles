USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_PaymentMethodSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_PaymentMethodSelect] 
END 
GO
CREATE PROC [dbo].[usp_PaymentMethodSelect] 
    @paymentMethod_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [paymentMethod_id], [name] 
	FROM   [dbo].[PaymentMethod] 
	WHERE  ([paymentMethod_id] = @paymentMethod_id OR @paymentMethod_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_PaymentMethodInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_PaymentMethodInsert] 
END 
GO
CREATE PROC [dbo].[usp_PaymentMethodInsert] 
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[PaymentMethod] ([name])
	SELECT @name
	
	-- Begin Return Select <- do not remove
	SELECT [paymentMethod_id], [name]
	FROM   [dbo].[PaymentMethod]
	WHERE  [paymentMethod_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_PaymentMethodUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_PaymentMethodUpdate] 
END 
GO
CREATE PROC [dbo].[usp_PaymentMethodUpdate] 
    @paymentMethod_id int,
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[PaymentMethod]
	SET    [name] = @name
	WHERE  [paymentMethod_id] = @paymentMethod_id
	
	-- Begin Return Select <- do not remove
	SELECT [paymentMethod_id], [name]
	FROM   [dbo].[PaymentMethod]
	WHERE  [paymentMethod_id] = @paymentMethod_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_PaymentMethodDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_PaymentMethodDelete] 
END 
GO
CREATE PROC [dbo].[usp_PaymentMethodDelete] 
    @paymentMethod_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[PaymentMethod]
	WHERE  [paymentMethod_id] = @paymentMethod_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

