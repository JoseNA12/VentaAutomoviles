USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_MotorTypeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_MotorTypeSelect] 
END 
GO
CREATE PROC [dbo].[usp_MotorTypeSelect] 
    @motorType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [motorType_id], [name] 
	FROM   [dbo].[MotorType] 
	WHERE  ([motorType_id] = @motorType_id OR @motorType_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_MotorTypeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_MotorTypeInsert] 
END 
GO
CREATE PROC [dbo].[usp_MotorTypeInsert] 
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[MotorType] ([name])
	SELECT @name
	
	-- Begin Return Select <- do not remove
	SELECT [motorType_id], [name]
	FROM   [dbo].[MotorType]
	WHERE  [motorType_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_MotorTypeUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_MotorTypeUpdate] 
END 
GO
CREATE PROC [dbo].[usp_MotorTypeUpdate] 
    @motorType_id int,
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[MotorType]
	SET    [name] = @name
	WHERE  [motorType_id] = @motorType_id
	
	-- Begin Return Select <- do not remove
	SELECT [motorType_id], [name]
	FROM   [dbo].[MotorType]
	WHERE  [motorType_id] = @motorType_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_MotorTypeDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_MotorTypeDelete] 
END 
GO
CREATE PROC [dbo].[usp_MotorTypeDelete] 
    @motorType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[MotorType]
	WHERE  [motorType_id] = @motorType_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

