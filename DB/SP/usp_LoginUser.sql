
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[usp_LoginUser]
	@username nvarchar(50),
	@password nvarchar(50)
AS
BEGIN

	SET NOCOUNT ON;
	IF EXISTS(SELECT user_id FROM [User] WHERE username = @username and password = @password and isActive = 1)
		BEGIN
		IF EXISTS(SELECT user_id FROM [User] WHERE username = @username and userType_id = 4) -- Cliente
			BEGIN
			SELECT 0 as exit_status, c.customer_id, c.name, c.phone, c.email, c.zip_code, c.user_id
			FROM Customer c 
			inner join [User] u on u.user_id = c.user_id 
			WHERE  u.username = @username and u.isActive = 1
			END
		ELSE	-- Empleado
			BEGIN
			SELECT 0 as exit_status, e.employee_id, e.[name], e.position_id, p.[name], e.office_id, u.user_id, ut.userType_id, ut.[name]
			FROM Employee e
			inner join Position p on p.position_id = e.position_id
			inner join [User] u on u.user_id = e.user_id 
			inner join UserType ut on ut.userType_id = u.userType_id
			WHERE u.username = @username and u.password = @password and u.isActive = 1
			END
		END
	ELSE
		BEGIN
		SELECT 1 as exit_status, 'El usuario o contraseña son incorrectos' as result		
		END
END
