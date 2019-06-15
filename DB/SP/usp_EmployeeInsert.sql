USE [HumanResourcesDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_EmployeeInsert]    Script Date: 15/6/2019 10:46:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROC [dbo].[usp_EmployeeInsert] 
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
