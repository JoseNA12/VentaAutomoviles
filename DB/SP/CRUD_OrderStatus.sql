USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_OrderStatusSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderStatusSelect] 
END 
GO
CREATE PROC [dbo].[usp_OrderStatusSelect] 
    @orderStatus_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [orderStatus_id], [statusName], [details] 
	FROM   [dbo].[OrderStatus] 
	WHERE  ([orderStatus_id] = @orderStatus_id OR @orderStatus_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_OrderStatusInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderStatusInsert] 
END 
GO
CREATE PROC [dbo].[usp_OrderStatusInsert] 
    @statusName nvarchar(50) = NULL,
    @details nvarchar(500) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[OrderStatus] ([statusName], [details])
	SELECT @statusName, @details
	
	-- Begin Return Select <- do not remove
	SELECT [orderStatus_id], [statusName], [details]
	FROM   [dbo].[OrderStatus]
	WHERE  [orderStatus_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_OrderStatusUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderStatusUpdate] 
END 
GO
CREATE PROC [dbo].[usp_OrderStatusUpdate] 
    @orderStatus_id int,
    @statusName nvarchar(50) = NULL,
    @details nvarchar(500) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[OrderStatus]
	SET    [statusName] = @statusName, [details] = @details
	WHERE  [orderStatus_id] = @orderStatus_id
	
	-- Begin Return Select <- do not remove
	SELECT [orderStatus_id], [statusName], [details]
	FROM   [dbo].[OrderStatus]
	WHERE  [orderStatus_id] = @orderStatus_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_OrderStatusDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderStatusDelete] 
END 
GO
CREATE PROC [dbo].[usp_OrderStatusDelete] 
    @orderStatus_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[OrderStatus]
	WHERE  [orderStatus_id] = @orderStatus_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

