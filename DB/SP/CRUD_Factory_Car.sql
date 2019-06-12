USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_Factory-CarSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarSelect] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarSelect] 
    @factory_car_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [factory_car_id], [car_id], [factory_id] 
	FROM   [dbo].[Factory-Car] 
	WHERE  ([factory_car_id] = @factory_car_id OR @factory_car_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_Factory-CarInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarInsert] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarInsert] 
    @car_id int = NULL,
    @factory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Factory-Car] ([car_id], [factory_id])
	SELECT @car_id, @factory_id
	
	-- Begin Return Select <- do not remove
	SELECT [factory_car_id], [car_id], [factory_id]
	FROM   [dbo].[Factory-Car]
	WHERE  [factory_car_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_Factory-CarUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarUpdate] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarUpdate] 
    @factory_car_id int,
    @car_id int = NULL,
    @factory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Factory-Car]
	SET    [car_id] = @car_id, [factory_id] = @factory_id
	WHERE  [factory_car_id] = @factory_car_id
	
	-- Begin Return Select <- do not remove
	SELECT [factory_car_id], [car_id], [factory_id]
	FROM   [dbo].[Factory-Car]
	WHERE  [factory_car_id] = @factory_car_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_Factory-CarDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarDelete] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarDelete] 
    @factory_car_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Factory-Car]
	WHERE  [factory_car_id] = @factory_car_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

