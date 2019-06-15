USE [HumanResourcesDB];
GO

IF OBJECT_ID('[dbo].[usp_EmployeeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeSelect] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeSelect] 
    @office_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [employee_id], [name], [lastname], [position_id], [office_id], [phone], [entryDate], [user_id], [zip_code], [birthDate], [identification_card] 
	FROM   [dbo].[Employee] 
	WHERE  ([employee_id] = @employee_id OR @employee_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_EmployeeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeInsert] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeInsert] 
    @name nvarchar(100) = NULL,
    @lastname nvarchar(50) = NULL,
    @position_id int = NULL,
    @office_id int = NULL,
    @phone int = NULL,
    @entryDate date = NULL,
    @zip_code nvarchar(50) = NULL,
    @birthDate date = NULL,
    @identification_card nvarchar(50) = NULL,
	@email nvarchar(50) = NULL,
	@password nvarchar(50) = NULL,
	@userType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	IF EXISTS(SELECT 1 FROM [User] u WHERE u.email = @email)
		BEGIN
		SELECT 1 as exit_status, 'Error, el usuario ingresado ya existe en la base de datos' as result
		END
	ELSE
		BEGIN TRAN
		DECLARE @user_id int
		EXEC dbo.usp_UserInsert @email, @password, @userType_id, @user_id OUTPUT

		INSERT INTO [dbo].[Employee] ([name], [lastname], [position_id], [office_id], [phone], [entryDate], [user_id], [zip_code], [birthDate], [identification_card])
		SELECT @name, @lastname, @position_id, @office_id, @phone, @entryDate, @user_id, @zip_code, @birthDate, @identification_card
	
		-- Begin Return Select <- do not remove
		SELECT [employee_id], [name], [lastname], [position_id], [office_id], [phone], [entryDate], [user_id], [zip_code], [birthDate], [identification_card]
		FROM   [dbo].[Employee]
		WHERE  [employee_id] = SCOPE_IDENTITY()
		-- End Return Select <- do not remove
               
		COMMIT
GO
IF OBJECT_ID('[dbo].[usp_EmployeeUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeUpdate] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeUpdate] 
    @employee_id int,
    @name nvarchar(100) = NULL,
    @lastname nvarchar(50) = NULL,
    @position_id int = NULL,
    @office_id int = NULL,
    @phone int = NULL,
    @entryDate date = NULL,
    @user_id int = NULL,
    @zip_code nvarchar(50) = NULL,
    @birthDate date = NULL,
    @identification_card nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Employee]
	SET    [name] = @name, [lastname] = @lastname, [position_id] = @position_id, [office_id] = @office_id, [phone] = @phone, [entryDate] = @entryDate, [user_id] = @user_id, [zip_code] = @zip_code, [birthDate] = @birthDate, [identification_card] = @identification_card
	WHERE  [employee_id] = @employee_id
	
	-- Begin Return Select <- do not remove
	SELECT [employee_id], [name], [lastname], [position_id], [office_id], [phone], [entryDate], [user_id], [zip_code], [birthDate], [identification_card]
	FROM   [dbo].[Employee]
	WHERE  [employee_id] = @employee_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_EmployeeDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeDelete] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeDelete] 
    @correo int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	EXEC [usp_UserDelete] @correo

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

