USE [HumanResourcesDB];
GO

IF OBJECT_ID('[dbo].[usp_UserSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserSelect] 
END 
GO
CREATE PROC [dbo].[usp_UserSelect] 
    @user_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [user_id], [username], [password], [userType_id], [isActive] 
	FROM   [dbo].[User] 
	WHERE  ([user_id] = @user_id OR @user_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_UserInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserInsert] 
END 
GO
CREATE PROC [dbo].[usp_UserInsert] 
    @username nvarchar(50) = NULL,
    @password nvarchar(50) = NULL,
    @userType_id int = NULL,
	@output int OUTPUT
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[User] ([username], [password], [userType_id], [isActive])
	SELECT @username, @password, @userType_id, 1
	
	-- Begin Return Select <- do not remove
	SET @output = (SELECT [user_id] FROM   [dbo].[User] WHERE  [user_id] = SCOPE_IDENTITY())
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_UserUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserUpdate] 
END 
GO
CREATE PROC [dbo].[usp_UserUpdate] 
    @user_id int,
    @username nvarchar(50) = NULL,
    @password nvarchar(50) = NULL,
    @userType_id int = NULL,
    @isActive tinyint = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[User]
	SET    [username] = @username, [password] = @password, [userType_id] = @userType_id, [isActive] = @isActive
	WHERE  [user_id] = @user_id
	
	-- Begin Return Select <- do not remove
	SELECT [user_id], [username], [password], [userType_id], [isActive]
	FROM   [dbo].[User]
	WHERE  [user_id] = @user_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_UserDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserDelete] 
END 
GO
CREATE PROC [dbo].[usp_UserDelete] 
	@username nvarchar(50)
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[User]
	SET    [isActive] = 0
	WHERE  [username] = @username

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

