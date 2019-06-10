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

	SELECT [employee_id], [username], [password], [name], [position_id], [employeeUserType_id] 
	FROM   [dbo].[Employee] 
	WHERE  ([employee_id] = @employee_id OR @employee_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_EmployeeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeInsert] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeInsert] 
    @username nvarchar(50),
    @password nvarchar(50) = NULL,
    @name nvarchar(100) = NULL,
    @position_id int = NULL,
    @employeeUserType_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	IF EXISTS(SELECT employee_id FROM Employee WHERE username = @username)
		BEGIN
		SELECT 1 as exit_status, 'El usuario ingresado ya existe en la base de datos' as result
		END
	ELSE
		BEGIN TRAN
		INSERT INTO [dbo].[Employee] ([username], [password], [name], [position_id], [employeeUserType_id])
		SELECT @username, @password, @name, @position_id, @employeeUserType_id
	
		-- Begin Return Select <- do not remove
		SELECT [employee_id], [username], [password], [name], [position_id], [employeeUserType_id]
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
    @username nvarchar(50),
    @password nvarchar(50) = NULL,
    @name nvarchar(100) = NULL,
    @position_id int = NULL,
    @employeeUserType_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	IF EXISTS(SELECT employee_id FROM Employee WHERE username = @username OR [employee_id] = @employee_id)
		BEGIN
		BEGIN TRAN
		UPDATE [dbo].[Employee]
		SET    [password] = ISNULL(password, @password), [name] = ISNULL(name,@name), [position_id] = ISNULL(position_id,@position_id), [employeeUserType_id] = ISNULL(employeeUserType_id,@employeeUserType_id)
		WHERE  [employee_id] = @employee_id OR [username] = @username
	
		-- Begin Return Select <- do not remove
		SELECT [employee_id], [username], [password], [name], [position_id], [employeeUserType_id]
		FROM   [dbo].[Employee]
		WHERE  [employee_id] = @employee_id	
		-- End Return Select <- do not remove
		COMMIT
		END
	ELSE
		BEGIN
		SELECT 1 as exit_status, 'El usuario ingresado ya existe en la base de datos' as result
		END
GO
IF OBJECT_ID('[dbo].[usp_EmployeeDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeDelete] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeDelete] 
    @employee_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Employee]
	WHERE  [employee_id] = @employee_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

