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

	BEGIN TRAN

	SELECT [user_id], [email], [password], [userType_id], [isActive] 
	FROM   [dbo].[User] 
	WHERE  ([user_id] = @user_id OR @user_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_UserInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_UserInsert] 
END 
GO
CREATE PROC [dbo].[usp_UserInsert] 
    @email nvarchar(50) = NULL,
    @password nvarchar(50) = NULL,
    @userType_id int = NULL,
    @output int OUTPUT
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[User] ([email], [password], [userType_id], [isActive])
	SELECT @email, @password, @userType_id, 1
	
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
    @email nvarchar(50) = NULL,
    @password nvarchar(50) = NULL,
    @userType_id int = NULL,
    @isActive tinyint = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[User]
	SET    [email] = @email, [password] = @password, [userType_id] = @userType_id, [isActive] = @isActive
	WHERE  [user_id] = @user_id
	
	-- Begin Return Select <- do not remove
	SELECT [user_id], [email], [password], [userType_id], [isActive]
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
    @email int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[User]
	SET    [isActive] = 0
	WHERE  email = @email

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

