USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_CountrySelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CountrySelect] 
END 
GO
CREATE PROC [dbo].[usp_CountrySelect] 
    @country_id int = NULL 
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [country_id], [name] 
	FROM   [dbo].[Country] 
	WHERE  ([country_id] = @country_id OR @country_id IS NULL) 
	UNION
	SELECT * FROM OPENQUERY([DESKTOP-3N2P4FH\BOFFICE_INST_2], 
	'SELECT c.[country_id], c.[name] 
	FROM BranchOfficeDB.dbo.Country c')
	UNION
	SELECT * FROM OPENQUERY([DESKTOP-3N2P4FH\BOFFICE_INST_3], 
	'SELECT c.[country_id], c.[name] 
	FROM BranchOfficeDB.dbo.Country c')
GO
IF OBJECT_ID('[dbo].[usp_CountryInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CountryInsert] 
END 
GO
CREATE PROC [dbo].[usp_CountryInsert] 
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	IF NOT EXISTS(SELECT name FROM Country WHERE name = @name)
		BEGIN
		BEGIN TRAN
		INSERT INTO [dbo].[Country] ([name])
		SELECT @name
		COMMIT
		END
	-- Begin Return Select <- do not remove
	SELECT [country_id], [name]
	FROM   [dbo].[Country]
	WHERE  ([country_id] = SCOPE_IDENTITY()) OR (name = @name)
	-- End Return Select <- do not remove	
GO

IF OBJECT_ID('[dbo].[usp_CountryUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CountryUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CountryUpdate] 
    @Country_id int,
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	IF NOT EXISTS(SELECT name FROM Country WHERE name = @name)
		BEGIN
		BEGIN TRAN
		UPDATE [dbo].[Country]
		SET    [name] = @name
		WHERE  [country_id] = @Country_id
		COMMIT	
		END
	-- Begin Return Select <- do not remove
	SELECT [country_id], [name]
	FROM   [dbo].[Country]
	WHERE  [country_id] = @Country_id	
	-- End Return Select <- do not remove

GO
IF OBJECT_ID('[dbo].[usp_CountryDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CountryDelete] 
END 
GO
CREATE PROC [dbo].[usp_CountryDelete] 
    @Country_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Country]
	WHERE  [country_id] = @Country_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

