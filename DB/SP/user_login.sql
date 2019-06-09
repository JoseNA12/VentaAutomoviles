
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE user_login
	@username nvarchar(50),
	@password nvarchar(50)
AS
BEGIN
	SET NOCOUNT ON;

	IF EXISTS(SELECT userType_id FROM [DESKTOP-3N2P4FH\HR_INSTANCE].HumanResourcesDB.dbo.Employee WHERE username = @username and password = @password)
		SELECT 0 as exit_status, userType_id FROM [DESKTOP-3N2P4FH\HR_INSTANCE].HumanResourcesDB.dbo.Employee WHERE username = @username and password = @password
	ELSE
		SELECT 1 as exit_status, 'El usuario o contraseña son incorrectos' as result
END
GO

EXEC user_login @username = 'asuarez', @password = '123'
