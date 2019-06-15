USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_CarTypeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarTypeSelect] 
END 
GO
CREATE PROC [dbo].[usp_CarTypeSelect] 
    @carType_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [carType_id], [name], [detailts] 
	FROM   [dbo].[CarType] 
	WHERE  ([carType_id] = @carType_id OR @carType_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarTypeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarTypeInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarTypeInsert] 
    @name nvarchar(50) = NULL,
    @detailts nvarchar(200) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CarType] ([name], [detailts])
	SELECT @name, @detailts
	
	-- Begin Return Select <- do not remove
	SELECT [carType_id], [name], [detailts]
	FROM   [dbo].[CarType]
	WHERE  [carType_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarTypeUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarTypeUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CarTypeUpdate] 
    @carType_id int,
    @name nvarchar(50) = NULL,
    @detailts nvarchar(200) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CarType]
	SET    [name] = @name, [detailts] = @detailts
	WHERE  [carType_id] = @carType_id
	
	-- Begin Return Select <- do not remove
	SELECT [carType_id], [name], [detailts]
	FROM   [dbo].[CarType]
	WHERE  [carType_id] = @carType_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarTypeDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarTypeDelete] 
END 
GO
CREATE PROC [dbo].[usp_CarTypeDelete] 
    @carType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CarType]
	WHERE  [carType_id] = @carType_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

