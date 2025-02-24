USE [HumanResourcesDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_EmployeeUpdate]    Script Date: 17/6/2019 00:13:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROC [dbo].[usp_EmployeeUpdate] 
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
	SET    [name] = @name, [position_id] = @position_id, [office_id] = @office_id, [phone] = @phone, [entryDate] = isNull(@entryDate,[entryDate])
	WHERE  [employee_id] = @employee_id
	
	-- Begin Return Select <- do not remove
	SELECT [employee_id], [name], [position_id], [office_id], [phone], [entryDate]
	FROM   [dbo].[Employee]
	WHERE  [employee_id] = @employee_id	
	-- End Return Select <- do not remove

	COMMIT
