USE [HumanResourcesDB];
GO

IF OBJECT_ID('[dbo].[usp_UserTypeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserTypeSelect] 
END 
GO
CREATE PROC [dbo].[usp_UserTypeSelect] 
    @userType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [userType_id], [name], [details] 
	FROM   [dbo].[UserType] 
	WHERE  ([userType_id] = @userType_id OR @userType_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_UserTypeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserTypeInsert] 
END 
GO
CREATE PROC [dbo].[usp_UserTypeInsert] 
    @name nvarchar(50) = NULL,
    @details nvarchar(300) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[UserType] ([name], [details])
	SELECT @name, @details
	
	-- Begin Return Select <- do not remove
	SELECT [userType_id], [name], [details]
	FROM   [dbo].[UserType]
	WHERE  [userType_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_UserTypeUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserTypeUpdate] 
END 
GO
CREATE PROC [dbo].[usp_UserTypeUpdate] 
    @userType_id int,
    @name nvarchar(50) = NULL,
    @details nvarchar(300) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[UserType]
	SET    [name] = @name, [details] = @details
	WHERE  [userType_id] = @userType_id
	
	-- Begin Return Select <- do not remove
	SELECT [userType_id], [name], [details]
	FROM   [dbo].[UserType]
	WHERE  [userType_id] = @userType_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_UserTypeDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserTypeDelete] 
END 
GO
CREATE PROC [dbo].[usp_UserTypeDelete] 
    @userType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[UserType]
	WHERE  [userType_id] = @userType_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

