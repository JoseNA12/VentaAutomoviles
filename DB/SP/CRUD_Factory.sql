USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_FactorySelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_FactorySelect] 
END 
GO
CREATE PROC [dbo].[usp_FactorySelect] 
    @factory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [factory_id], [name], [location] 
	FROM   [dbo].[Factory] 
	WHERE  ([factory_id] = @factory_id OR @factory_id IS NULL) 
GO
IF OBJECT_ID('[dbo].[usp_FactoryInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_FactoryInsert] 
END 
GO
CREATE PROC [dbo].[usp_FactoryInsert] 
    @name nvarchar(50) = NULL,
    @location geography = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Factory] ([name], [location])
	SELECT @name, @location
	
	-- Begin Return Select <- do not remove
	SELECT [factory_id], [name], [location]
	FROM   [dbo].[Factory]
	WHERE  [factory_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_FactoryUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_FactoryUpdate] 
END 
GO
CREATE PROC [dbo].[usp_FactoryUpdate] 
    @factory_id int,
    @name nvarchar(50) = NULL,
    @location geography = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Factory]
	SET    [name] = @name, [location] = @location
	WHERE  [factory_id] = @factory_id
	
	-- Begin Return Select <- do not remove
	SELECT [factory_id], [name], [location]
	FROM   [dbo].[Factory]
	WHERE  [factory_id] = @factory_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_FactoryDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_FactoryDelete] 
END 
GO
CREATE PROC [dbo].[usp_FactoryDelete] 
    @factory_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Factory]
	WHERE  [factory_id] = @factory_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

