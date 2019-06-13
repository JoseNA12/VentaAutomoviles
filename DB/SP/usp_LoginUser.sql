
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[usp_LoginUser]
	@email nvarchar(50),
	@password nvarchar(50)
AS
BEGIN
	SET NOCOUNT ON;
	IF EXISTS(SELECT user_id FROM [User] WHERE email = @email and password = @password and isActive = 1)
		BEGIN
		IF EXISTS(SELECT user_id FROM [User] WHERE email = @email and userType_id = 4) -- Cliente
			BEGIN
			SELECT 0 as exit_status, c.customer_id, c.name, c.lastname, c.birthDate, c.identification_card, c.phone, u.email, c.zip_code, u.user_id, ut.userType_id, ut.[name] as userTypeName
			FROM Customer c 
			inner join [User] u on u.user_id = c.user_id 
			inner join UserType ut on ut.userType_id = u.userType_id
			WHERE  u.email = @email and u.isActive = 1
			END
		ELSE	-- Empleado
			BEGIN
			SELECT 0 as exit_status, e.employee_id, e.[name], e.lastname, e.birthDate, e.identification_card, e.phone, u.email, e.zip_code, u.user_id, ut.userType_id, ut.[name] as userTypeName, e.position_id, p.[name] as positionName, e.office_id
			FROM Employee e
			inner join Position p on p.position_id = e.position_id
			inner join [User] u on u.user_id = e.user_id 
			inner join UserType ut on ut.userType_id = u.userType_id
			WHERE u.email = @email and u.password = @password and u.isActive = 1
			END
		END
	ELSE
		BEGIN
		SELECT 1 as exit_status, 'El usuario o contraseña son incorrectos' as result		
		END
END

exec usp_LoginUser 'cliente@correo.com', '123'