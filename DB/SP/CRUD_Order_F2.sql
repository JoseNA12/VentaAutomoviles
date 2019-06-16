USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_OrderSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderSelect] 
END 
GO
CREATE PROC [dbo].[usp_OrderSelect] 
    @order_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  


	SELECT [order_id], [order_date], [delivery_date], [details] 
	FROM   [dbo].[Order] 
	WHERE  ([order_id] = @order_id OR @order_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_OrderInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderInsert] 
END 
GO
CREATE PROC [dbo].[usp_OrderInsert] 
	@order_id int = NULL,
    @delivery_date date = NULL,
    @details nvarchar(500) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Order] ([order_id], [order_date], [delivery_date], [details])
	SELECT @order_id, GETDATE(), @delivery_date, @details

	COMMIT
	
	/*-- Begin Return Select <- do not remove
	SELECT [order_id], [order_date], [delivery_date], [details]
	FROM   [dbo].[Order]
	WHERE  [order_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove*/
               
GO
IF OBJECT_ID('[dbo].[usp_OrderUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderUpdate] 
END 
GO
CREATE PROC [dbo].[usp_OrderUpdate] 
    @order_id int,
    @delivery_date datetime = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Order]
	SET    [delivery_date] = ISNULL(@delivery_date, [delivery_date])
	WHERE  [order_id] = @order_id
	
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_OrderDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderDelete] 
END 
GO
CREATE PROC [dbo].[usp_OrderDelete] 
    @order_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Order]
	WHERE  [order_id] = @order_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

