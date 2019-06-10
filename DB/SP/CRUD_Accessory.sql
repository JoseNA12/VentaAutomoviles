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

	SELECT [accessory_id], [name], [price] 
	FROM   [dbo].[Accessory] 
	WHERE  ([accessory_id] = @accessory_id OR @accessory_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_AccessoryInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_AccessoryInsert] 
END 
GO
CREATE PROC [dbo].[usp_AccessoryInsert] 
    @name nvarchar(50) = NULL,
    @price money = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Accessory] ([name], [price])
	SELECT @name, @price
	
	-- Begin Return Select <- do not remove
	SELECT [accessory_id], [name], [price]
	FROM   [dbo].[Accessory]
	WHERE  [accessory_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_AccessoryUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_AccessoryUpdate] 
END 
GO
CREATE PROC [dbo].[usp_AccessoryUpdate] 
    @accessory_id int,
    @name nvarchar(50) = NULL,
    @price money = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Accessory]
	SET    [name] = @name, [price] = @price
	WHERE  [accessory_id] = @accessory_id
	
	-- Begin Return Select <- do not remove
	SELECT [accessory_id], [name], [price]
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

