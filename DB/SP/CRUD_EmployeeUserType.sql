USE [HumanResourcesDB];
GO

IF OBJECT_ID('[dbo].[usp_EmployeeUserTypeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeUserTypeSelect] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeUserTypeSelect] 
    @employeeUserType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [employeeUserType_id], [name], [details] 
	FROM   [dbo].[EmployeeUserType] 
	WHERE  ([employeeUserType_id] = @employeeUserType_id OR @employeeUserType_id IS NULL) 

GO

IF OBJECT_ID('[dbo].[usp_EmployeeUserTypeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeUserTypeInsert] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeUserTypeInsert] 
    @name nvarchar(50) = NULL,
    @details nvarchar(300) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[EmployeeUserType] ([name], [details])
	SELECT @name, @details
	
	-- Begin Return Select <- do not remove
	SELECT [employeeUserType_id], [name], [details]
	FROM   [dbo].[EmployeeUserType]
	WHERE  [employeeUserType_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_EmployeeUserTypeUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeUserTypeUpdate] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeUserTypeUpdate] 
    @employeeUserType_id int,
    @name nvarchar(50) = NULL,
    @details nvarchar(300) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[EmployeeUserType]
	SET    [name] = @name, [details] = @details
	WHERE  [employeeUserType_id] = @employeeUserType_id
	
	-- Begin Return Select <- do not remove
	SELECT [employeeUserType_id], [name], [details]
	FROM   [dbo].[EmployeeUserType]
	WHERE  [employeeUserType_id] = @employeeUserType_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_EmployeeUserTypeDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_EmployeeUserTypeDelete] 
END 
GO
CREATE PROC [dbo].[usp_EmployeeUserTypeDelete] 
    @employeeUserType_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[EmployeeUserType]
	WHERE  [employeeUserType_id] = @employeeUserType_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

