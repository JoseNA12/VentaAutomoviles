USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_StockSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_StockSelect] 
END 
GO
CREATE PROC [dbo].[usp_StockSelect] 
    @stock_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [stock_id], [office_id] 
	FROM   [dbo].[Stock] 
	WHERE  ([stock_id] = @stock_id OR @stock_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_StockInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_StockInsert] 
END 
GO
CREATE PROC [dbo].[usp_StockInsert] 
    @office_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Stock] ([office_id])
	SELECT @office_id
	
	-- Begin Return Select <- do not remove
	SELECT [stock_id], [office_id]
	FROM   [dbo].[Stock]
	WHERE  [stock_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_StockUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_StockUpdate] 
END 
GO
CREATE PROC [dbo].[usp_StockUpdate] 
    @stock_id int,
    @office_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Stock]
	SET    [office_id] = @office_id
	WHERE  [stock_id] = @stock_id
	
	-- Begin Return Select <- do not remove
	SELECT [stock_id], [office_id]
	FROM   [dbo].[Stock]
	WHERE  [stock_id] = @stock_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_StockDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_StockDelete] 
END 
GO
CREATE PROC [dbo].[usp_StockDelete] 
    @stock_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Stock]
	WHERE  [stock_id] = @stock_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

