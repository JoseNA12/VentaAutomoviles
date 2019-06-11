USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_FuelTypeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_FuelTypeSelect] 
END 
GO
CREATE PROC [dbo].[usp_FuelTypeSelect] 
    @fuelType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [fuelType_id], [name] 
	FROM   [dbo].[FuelType] 
	WHERE  ([fuelType_id] = @fuelType_id OR @fuelType_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_FuelTypeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_FuelTypeInsert] 
END 
GO
CREATE PROC [dbo].[usp_FuelTypeInsert] 
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[FuelType] ([name])
	SELECT @name
	
	-- Begin Return Select <- do not remove
	SELECT [fuelType_id], [name]
	FROM   [dbo].[FuelType]
	WHERE  [fuelType_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_FuelTypeUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_FuelTypeUpdate] 
END 
GO
CREATE PROC [dbo].[usp_FuelTypeUpdate] 
    @fuelType_id int,
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[FuelType]
	SET    [name] = @name
	WHERE  [fuelType_id] = @fuelType_id
	
	-- Begin Return Select <- do not remove
	SELECT [fuelType_id], [name]
	FROM   [dbo].[FuelType]
	WHERE  [fuelType_id] = @fuelType_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_FuelTypeDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_FuelTypeDelete] 
END 
GO
CREATE PROC [dbo].[usp_FuelTypeDelete] 
    @fuelType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[FuelType]
	WHERE  [fuelType_id] = @fuelType_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

