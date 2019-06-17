USE [HumanResourcesDB];
GO

IF OBJECT_ID('[dbo].[usp_EmployeeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeSelect] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeSelect] 
    @employee_id int = null
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	SELECT e.[employee_id], e.[name], e.[lastname], e.[position_id], p.[name] as "position", e.[office_id], 
			u.[email], e.[phone], e.[entryDate], e.[user_id], e.[identification_card], e.[birthDate], e.[zip_code], ut.[name] as "userType"
	FROM   [dbo].[Employee] e
	inner join [User] u on u.[user_id] = e.[user_id]
	inner join [Position] p on p.position_id = e.position_id
	inner join [UserType] ut on ut.userType_id = u.userType_id
	WHERE  ([employee_id] = @employee_id OR @employee_id IS NULL) AND (u.isActive = 1)
GO
IF OBJECT_ID('[dbo].[usp_EmployeeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeInsert] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeInsert] 
    @name nvarchar(100) = NULL,
	@lastname nvarchar(100) = NULL,
    @position_id int = NULL,
    @office_id int = NULL,
    @email nvarchar(50) = NULL,
    @phone int = NULL,
    @entryDate date = NULL,
	@password nvarchar(50),
	@userType int,
	@identification_card nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	IF EXISTS(SELECT 1 FROM [User] u WHERE u.email = @email)
		BEGIN
		SELECT 1 as exit_status, 'Error, el usuario ingresado ya existe en la base de datos' as result
		END
	ELSE
		BEGIN TRAN
		DECLARE @new_user int
		EXEC dbo.usp_UserInsert @email, @password, @userType, @new_user OUTPUT

		INSERT INTO [dbo].[Employee] ([name],[lastname],[position_id], [office_id], [phone], [entryDate], [user_id],[identification_card],[zip_code],[birthDate])
			VALUES (@name,@lastname,@position_id,@office_id,@phone,@entryDate,@new_user,@identification_card,30105,@entryDate)
		--INSERT INTO [dbo].[Employee] ([name], [position_id], [office_id], [email], [phone], [entryDate], [user_id])
		--SELECT @name, @position_id, @office_id, @email, @phone, @entryDate, @new_user
	
		-- Begin Return Select <- do not remove
		SELECT 0 as exit_status, [employee_id], [name], [lastname], [position_id], [office_id], [phone], [entryDate], [user_id], [identification_card]
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
	@lastname nvarchar(100) = NUll,
    @position_id int = NULL,
    @office_id int = NULL,
    @email nvarchar(50) = NULL,
    @phone int = NULL,
    @entryDate date = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	BEGIN TRAN

	UPDATE [dbo].[Employee]
	SET    [name] = ISNULL(@name, [name]), [lastname] = ISNULL(@lastname, [lastname]), [position_id] = ISNULL(@position_id,[position_id]), 
	[office_id] = ISNULL(@office_id,[office_id]), [phone] = ISNULL(@phone,[phone]), [entryDate] = ISNULL(@entryDate,[entryDate])
	WHERE  [employee_id] = @employee_id
	
	-- Begin Return Select <- do not remove
	SELECT [employee_id], [name], [position_id], [office_id], [phone], [entryDate]
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
    @correo nvarchar(50)
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	EXEC [usp_UserDelete] @correo

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

