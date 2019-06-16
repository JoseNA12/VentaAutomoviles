USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_AccessorySelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_AccessorySelect] 
END 
GO
CREATE PROC [dbo].[usp_AccessorySelect] 
    @accessory_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [accessory_id], [name] 
	FROM   [dbo].[Accessory] 
	WHERE  ([accessory_id] = @accessory_id OR @accessory_id IS NULL) 
GO
IF OBJECT_ID('[dbo].[usp_AccessoryInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_AccessoryInsert] 
END 
GO
CREATE PROC [dbo].[usp_AccessoryInsert] 
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	IF NOT EXISTS(SELECT name FROM Accessory WHERE name = @name)
		BEGIN
		BEGIN TRAN
		INSERT INTO [dbo].[Accessory] ([name])
		SELECT @name
		COMMIT
		END
	-- Begin Return Select <- do not remove
	SELECT [accessory_id], [name]
	FROM   [dbo].[Accessory]
	WHERE  ([accessory_id] = SCOPE_IDENTITY()) OR (name = @name)
	-- End Return Select <- do not remove	
GO

IF OBJECT_ID('[dbo].[usp_AccessoryUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_AccessoryUpdate] 
END 
GO
CREATE PROC [dbo].[usp_AccessoryUpdate] 
    @accessory_id int,
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Accessory]
	SET    [name] = @name
	WHERE  [accessory_id] = @accessory_id
	
	-- Begin Return Select <- do not remove
	SELECT [accessory_id], [name]
	FROM   [dbo].[Accessory]
	WHERE  [accessory_id] = @accessory_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_AccessoryDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_AccessoryDelete] 
END 
GO
CREATE PROC [dbo].[usp_AccessoryDelete] 
    @accessory_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Accessory]
	WHERE  [accessory_id] = @accessory_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

