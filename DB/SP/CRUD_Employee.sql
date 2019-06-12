USE [HumanResourcesDB];
GO

IF OBJECT_ID('[dbo].[usp_EmployeeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeSelect] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeSelect] 
    @employee_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	SELECT [employee_id], [name], [position_id], [office_id], [email], [phone], [entryDate], [user_id] 
	FROM   [dbo].[Employee] 
	WHERE  ([employee_id] = @employee_id OR @employee_id IS NULL) 
GO

IF OBJECT_ID('[dbo].[usp_EmployeeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeInsert] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeInsert] 
    @name nvarchar(100) = NULL,
    @position_id int = NULL,
    @office_id int = NULL,
    @email nvarchar(50) = NULL,
    @phone int = NULL,
    @entryDate date = NULL,
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

		INSERT INTO [dbo].[Employee] ([name], [position_id], [office_id], [email], [phone], [entryDate], [user_id])
		SELECT @name, @position_id, @office_id, @email, @phone, @entryDate, @new_user
	
		-- Begin Return Select <- do not remove
		SELECT 0 as exit_status, [employee_id], [name], [position_id], [office_id], [email], [phone], [entryDate], [user_id]
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
    @position_id int = NULL,
    @office_id int = NULL,
    @email nvarchar(50) = NULL,
    @phone int = NULL,
    @entryDate date = NULL,
    @username int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	IF EXISTS(SELECT 1 FROM [User] u WHERE u.username = @username)
		BEGIN
		SELECT 1 as exit_status, 'Error, el usuario ingresado ya existe en la base de datos' as result
		END
	ELSE
		BEGIN TRAN

		UPDATE [dbo].[Employee]
		SET    [name] = @name, [position_id] = @position_id, [office_id] = @office_id, [email] = @email, [phone] = @phone, [entryDate] = @entryDate
		WHERE  [employee_id] = @employee_id
	
		-- Begin Return Select <- do not remove
		SELECT [employee_id], [name], [position_id], [office_id], [email], [phone], [entryDate]
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

