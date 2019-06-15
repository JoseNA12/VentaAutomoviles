USE [HumanResourcesDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_EmployeeSelect]    Script Date: 15/6/2019 13:26:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROC [dbo].[usp_EmployeeSelect] 
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
	WHERE  ([employee_id] = @employee_id OR @employee_id IS NULL) 
