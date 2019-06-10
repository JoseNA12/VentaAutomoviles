USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_OrderStatusSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderStatusSelect] 
END 
GO
CREATE PROC [dbo].[usp_OrderStatusSelect] 
    @status_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [status_id], [name], [details] 
	FROM   [dbo].[OrderStatus] 
	WHERE  ([status_id] = @status_id OR @status_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_OrderStatusInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderStatusInsert] 
END 
GO
CREATE PROC [dbo].[usp_OrderStatusInsert] 
    @name nvarchar(50) = NULL,
    @details nvarchar(300) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[OrderStatus] ([name], [details])
	SELECT @name, @details
	
	-- Begin Return Select <- do not remove
	SELECT [status_id], [name], [details]
	FROM   [dbo].[OrderStatus]
	WHERE  [status_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_OrderStatusUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderStatusUpdate] 
END 
GO
CREATE PROC [dbo].[usp_OrderStatusUpdate] 
    @status_id int,
    @name nvarchar(50) = NULL,
    @details nvarchar(300) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[OrderStatus]
	SET    [name] = @name, [details] = @details
	WHERE  [status_id] = @status_id
	
	-- Begin Return Select <- do not remove
	SELECT [status_id], [name], [details]
	FROM   [dbo].[OrderStatus]
	WHERE  [status_id] = @status_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_OrderStatusDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderStatusDelete] 
END 
GO
CREATE PROC [dbo].[usp_OrderStatusDelete] 
    @status_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[OrderStatus]
	WHERE  [status_id] = @status_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

