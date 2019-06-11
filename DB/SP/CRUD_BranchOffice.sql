USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_BranchOfficeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_BranchOfficeSelect] 
END 
GO
CREATE PROC [dbo].[usp_BranchOfficeSelect] 
    @branchOffice_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [branchOffice_id], [name], [location], [country_id] 
	FROM   [dbo].[BranchOffice] 
	WHERE  ([branchOffice_id] = @branchOffice_id OR @branchOffice_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_BranchOfficeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_BranchOfficeInsert] 
END 
GO
CREATE PROC [dbo].[usp_BranchOfficeInsert] 
    @name nvarchar(50) = NULL,
    @location geography = NULL,
    @country_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[BranchOffice] ([name], [location], [country_id])
	SELECT @name, @location, @country_id
	
	-- Begin Return Select <- do not remove
	SELECT [branchOffice_id], [name], [location], [country_id]
	FROM   [dbo].[BranchOffice]
	WHERE  [branchOffice_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_BranchOfficeUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_BranchOfficeUpdate] 
END 
GO
CREATE PROC [dbo].[usp_BranchOfficeUpdate] 
    @branchOffice_id int,
    @name nvarchar(50) = NULL,
    @location geography = NULL,
    @country_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[BranchOffice]
	SET    [name] = @name, [location] = @location, [country_id] = @country_id
	WHERE  [branchOffice_id] = @branchOffice_id
	
	-- Begin Return Select <- do not remove
	SELECT [branchOffice_id], [name], [location], [country_id]
	FROM   [dbo].[BranchOffice]
	WHERE  [branchOffice_id] = @branchOffice_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_BranchOfficeDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_BranchOfficeDelete] 
END 
GO
CREATE PROC [dbo].[usp_BranchOfficeDelete] 
    @branchOffice_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[BranchOffice]
	WHERE  [branchOffice_id] = @branchOffice_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

