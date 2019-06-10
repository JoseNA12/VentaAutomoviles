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

	SELECT [customer_id], [username], [password], [name], [phone], [email], [zip_code], [location] 
	FROM   [dbo].[Customer] 
	WHERE  ([customer_id] = @customer_id OR @customer_id IS NULL) 

GO

IF OBJECT_ID('[dbo].[usp_CustomerInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CustomerInsert] 
END 
GO
CREATE PROC [dbo].[usp_CustomerInsert] 
    @username nvarchar(50) = NULL,
    @password nvarchar(50) = NULL,
    @name nvarchar(50) = NULL,
    @phone int = NULL,
    @email nvarchar(50) = NULL,
    @zip_code int = NULL,
    @location geography = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	IF EXISTS(SELECT customer_id FROM Customer WHERE username = @username OR email = @email)
		BEGIN
		SELECT 1 as exit_status, 'El usuario o correo ingresado ya existe en la base de datos' as result
		END
	ELSE
		BEGIN TRAN
		INSERT INTO [dbo].[Customer] ([username], [password], [name], [phone], [email], [zip_code], [location])
		SELECT @username, @password, @name, @phone, @email, @zip_code, @location
	
		-- Begin Return Select <- do not remove
		SELECT [customer_id], [username], [password], [name], [phone], [email], [zip_code], [location]
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
    --@username nvarchar(50),
    @password nvarchar(50) = NULL,
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
	SET    [password] = ISNULL(password, @password), [name] = ISNULL(name, @name), [phone] = ISNULL(phone, @phone), [email] = ISNULL(email, @email), [zip_code] = ISNULL(zip_code, @zip_code)
	WHERE  [customer_id] = @customer_id
	
	-- Begin Return Select <- do not remove
	SELECT [customer_id], [username], [password], [name], [phone], [email], [zip_code], [location]
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
    @customer_id bigint
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Customer]
	WHERE  [customer_id] = @customer_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

