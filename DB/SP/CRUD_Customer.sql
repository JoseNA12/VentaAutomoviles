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

	BEGIN TRAN

	SELECT [customer_id], [name], [lastname], [phone], [zip_code], [location], [user_id], [birthDate], [identification_card] 
	FROM   [dbo].[Customer] 
	WHERE  ([customer_id] = @customer_id OR @customer_id IS NULL) 

	COMMIT
GO

IF OBJECT_ID('[dbo].[usp_CustomerInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CustomerInsert] 
END 
GO
CREATE PROC [dbo].[usp_CustomerInsert] 
    @name nvarchar(50),
	@lastname nvarchar(50),
	@birthDate date,
    @phone nvarchar(50),
    @email nvarchar(50),
    @zip_code int,
	@password nvarchar(50),
	@identificationCard nvarchar(50)
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	IF NOT EXISTS(SELECT 1 FROM [User] u WHERE u.email = @email)
		BEGIN TRAN
		DECLARE @new_user int
		EXEC dbo.usp_UserInsert @email, @password, 4, @new_user OUTPUT

		INSERT INTO [dbo].[Customer] ([name], lastname, [phone], [zip_code], [location], [user_id], birthDate, identification_card)
		SELECT @name, @lastname, @phone, @zip_code, NULL, @new_user, @birthDate, @identificationCard
	
		-- Begin Return Select <- do not remove
		SELECT [customer_id], [name], [lastname], [phone], [zip_code], [location], [user_id], [birthDate], [identification_card]
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
    @lastname nvarchar(50) = NULL,
    @phone nvarchar(50) = NULL,
    @zip_code int = NULL,
    @location geography = NULL,
    @user_id int = NULL,
    @birthDate date = NULL,
    @identification_card nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Customer]
	SET    [name] = @name, [lastname] = @lastname, [phone] = @phone, [zip_code] = @zip_code, [location] = @location, [user_id] = @user_id, [birthDate] = @birthDate, [identification_card] = @identification_card
	WHERE  [customer_id] = @customer_id
	
	-- Begin Return Select <- do not remove
	SELECT [customer_id], [name], [lastname], [phone], [zip_code], [location], [user_id], [birthDate], [identification_card]
	FROM   [dbo].[Customer]
	WHERE  [customer_id] = @customer_id	
	-- End Return Select <- do not remove

	COMMIT
GO
GO

IF OBJECT_ID('[dbo].[usp_CustomerDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CustomerDelete] 
END 
GO
CREATE PROC [dbo].[usp_CustomerDelete] 
	@email nvarchar(50)
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	EXEC [usp_UserDelete] @email
	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------


USE [HumanResourcesDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_SelectIDCustomerByMail]    Script Date: 16/06/2019 18:47:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[usp_SelectIDCustomerByMail] (@mail nvarchar(50))
AS
	
	SELECT [c].[customer_id]
	FROM   [dbo].[Customer] c
	inner join [dbo].[User] u on [c].[user_id] = [u].[user_id]
	WHERE  ([u].[email] = @mail)
