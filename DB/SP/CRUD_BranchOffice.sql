USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_BranchOfficeSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_BranchOfficeSelect] 
END 
GO
CREATE PROC [dbo].[usp_BranchOfficeSelect] 
	@branchOffice_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT bo.[branchOffice_id], bo.[name], bo.[country_id], c.name as countryName, bo.[horaApertura], bo.[horaCierre] 
	FROM   [dbo].[BranchOffice] bo
	inner join Country c on c.country_id = bo.country_id 
	WHERE ([branchOffice_id] = @branchOffice_id) OR (@branchOffice_id IS NULL)
GO

IF OBJECT_ID('[dbo].[usp_BranchOfficeSelectAll]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_BranchOfficeSelectAll] 
END 
GO
CREATE PROC [dbo].[usp_BranchOfficeSelectAll] 
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT bo.[branchOffice_id], bo.[name], bo.[country_id], c.name as countryName, bo.[horaApertura], bo.[horaCierre] 
	FROM   [dbo].[BranchOffice] bo
	inner join Country c on c.country_id = bo.country_id 
	UNION
	SELECT * FROM OPENQUERY([DESKTOP-3N2P4FH\BOFFICE_INST_2], 
	'SELECT bo.[branchOffice_id], bo.[name], bo.[country_id], c.name as countryName, bo.[horaApertura], bo.[horaCierre] 
	FROM BranchOfficeDB.dbo.BranchOffice bo
	inner join BranchOfficeDB.dbo.Country c on c.country_id = bo.country_id') 
	UNION
	SELECT * FROM OPENQUERY([DESKTOP-3N2P4FH\BOFFICE_INST_3], 
	'SELECT bo.[branchOffice_id], bo.[name], bo.[country_id], c.name as countryName, bo.[horaApertura], bo.[horaCierre] 
	FROM BranchOfficeDB.dbo.BranchOffice bo
	inner join BranchOfficeDB.dbo.Country c on c.country_id = bo.country_id') 
GO

IF OBJECT_ID('[dbo].[usp_BranchOfficeInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_BranchOfficeInsert] 
END 
GO
CREATE PROC [dbo].[usp_BranchOfficeInsert] 
    @name nvarchar(50) = NULL,
    @location geography = NULL,
    @country_id int = NULL,
    @horaApertura time(7) = NULL,
    @horaCierre time(7) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[BranchOffice] ([name], [location], [country_id], [horaApertura], [horaCierre])
	SELECT @name, @location, @country_id, @horaApertura, @horaCierre
	
	-- Begin Return Select <- do not remove
	SELECT [branchOffice_id], [name], [location], [country_id], [horaApertura], [horaCierre]
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
    @country_id int = NULL,
    @horaApertura time(7) = NULL,
    @horaCierre time(7) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[BranchOffice]
	SET    [name] = @name, [location] = @location, [country_id] = @country_id, [horaApertura] = @horaApertura, [horaCierre] = @horaCierre
	WHERE  [branchOffice_id] = @branchOffice_id
	
	-- Begin Return Select <- do not remove
	SELECT [branchOffice_id], [name], [location], [country_id], [horaApertura], [horaCierre]
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

