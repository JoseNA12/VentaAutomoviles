USE [HumanResourcesDB];
GO

IF OBJECT_ID('[dbo].[usp_PositionSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_PositionSelect] 
END 
GO
CREATE PROC [dbo].[usp_PositionSelect] 
    @position_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [position_id], [name], [details] 
	FROM   [dbo].[Position] 
	WHERE  ([position_id] = @position_id OR @position_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_PositionInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_PositionInsert] 
END 
GO
CREATE PROC [dbo].[usp_PositionInsert] 
    @name nvarchar(50) = NULL,
    @details nvarchar(300) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Position] ([name], [details])
	SELECT @name, @details
	
	-- Begin Return Select <- do not remove
	SELECT [position_id], [name], [details]
	FROM   [dbo].[Position]
	WHERE  [position_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_PositionUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_PositionUpdate] 
END 
GO
CREATE PROC [dbo].[usp_PositionUpdate] 
    @position_id int,
    @name nvarchar(50) = NULL,
    @details nvarchar(300) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Position]
	SET    [name] = @name, [details] = @details
	WHERE  [position_id] = @position_id
	
	-- Begin Return Select <- do not remove
	SELECT [position_id], [name], [details]
	FROM   [dbo].[Position]
	WHERE  [position_id] = @position_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_PositionDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_PositionDelete] 
END 
GO
CREATE PROC [dbo].[usp_PositionDelete] 
    @position_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Position]
	WHERE  [position_id] = @position_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

