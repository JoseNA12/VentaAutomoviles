
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[usp_LoginUser]
	@username nvarchar(50),
	@password nvarchar(50),
	@userType int -- Cliente o Empleado
AS
BEGIN

	SET NOCOUNT ON;
	IF (@userType = 1 ) -- Employee
		BEGIN
		IF EXISTS(SELECT employeeUserType_id FROM Employee WHERE username = @username and password = @password)
			SELECT 0 as exit_status, e.employee_id, e.name, e.position_id, p.name, e.employeeUserType_id, eu.name
			FROM Employee e
			inner join Position p on p.position_id = e.position_id
			inner join EmployeeUserType eu on eu.employeeUserType_id = e.employeeUserType_id
			WHERE e.username = @username and e.password = @password
		ELSE
			SELECT 1 as exit_status, 'El usuario o contraseña son incorrectos' as result
		END
	ELSE -- Customer
		BEGIN
		IF EXISTS(SELECT customer_id FROM Customer WHERE username = @username and password = @password)
			SELECT 0 as exit_status, c.customer_id, c.name, c.phone, c.email, c.zip_code
			FROM Customer c
			WHERE c.username = @username and c.password = @password
		ELSE
			SELECT 1 as exit_status, 'El usuario o contraseña son incorrectos' as result
		END
		
END
