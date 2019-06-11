USE [HumanResourcesDB];
GO

IF OBJECT_ID('[dbo].[usp_CustomerSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CustomerSelect] 
END 
GO
CREATE PROC [dbo].[usp_CustomerSelect] 
    @customer_id bigint
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [customer_id], [name], [phone], [email], [zip_code], [location], [user_id]
	FROM   [dbo].[Customer] 
	WHERE  ([customer_id] = @customer_id OR @customer_id IS NULL)

GO

IF OBJECT_ID('[dbo].[usp_CustomerInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CustomerInsert] 
END 
GO
CREATE PROC [dbo].[usp_CustomerInsert] 
    @name nvarchar(50) = NULL,
    @phone int = NULL,
    @email nvarchar(50) = NULL,
    @zip_code int = NULL,
    @location geography = NULL,
	@username nvarchar(50),
	@password nvarchar(50),
	@userType int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	IF EXISTS(SELECT 1 FROM [User] u WHERE u.username = @username)
		BEGIN
		SELECT 1 as exit_status, 'Error, el usuario ingresado ya existe en la base de datos' as result
		END
	ELSE
		BEGIN TRAN
		DECLARE @new_user int
		EXEC dbo.usp_UserInsert @username, @password, @userType, @new_user OUTPUT

		INSERT INTO [dbo].[Customer] ([name], [phone], [email], [zip_code], [location], [user_id])
		SELECT @name, @phone, @email, @zip_code, @location, @new_user
	
		-- Begin Return Select <- do not remove
		SELECT [customer_id], [name], [phone], [email], [zip_code], [location], [user_id]
		FROM   [dbo].[Customer]
		WHERE  [customer_id] = SCOPE_IDENTITY()
		-- End Return Select <- do not remove
		COMMIT        
GO

IF OBJECT_ID('[dbo].[usp_CustomerUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CustomerUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CustomerUpdate] 
    @customer_id bigint,
    @name nvarchar(50) = NULL,
    @phone int = NULL,
    @email nvarchar(50) = NULL,
    @zip_code int = NULL
   -- @location geography = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Customer]
	SET    [name] = ISNULL(name, @name), [phone] = ISNULL(phone, @phone), [email] = ISNULL(email, @email), [zip_code] = ISNULL(zip_code, @zip_code)
	WHERE  [customer_id] = @customer_id
	
	-- Begin Return Select <- do not remove
	SELECT [customer_id], [name], [phone], [email], [zip_code], [location]
	FROM   [dbo].[Customer]
	WHERE  [customer_id] = @customer_id	
	-- End Return Select <- do not remove

	COMMIT
GO

IF OBJECT_ID('[dbo].[usp_CustomerDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CustomerDelete] 
END 
GO
CREATE PROC [dbo].[usp_CustomerDelete] 
	@username nvarchar(50)
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	EXEC [usp_UserDelete] @username
	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

