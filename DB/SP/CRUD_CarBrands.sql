USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_CarBrandsSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarBrandsSelect] 
END 
GO
CREATE PROC [dbo].[usp_CarBrandsSelect] 
    @carBrand_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	SELECT [carBrand_id], [name] 
	FROM   [dbo].[CarBrands] 
	WHERE  ([carBrand_id] = @carBrand_id OR @carBrand_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_CarBrandsInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarBrandsInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarBrandsInsert] 
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CarBrands] ([name])
	SELECT @name
	
	-- Begin Return Select <- do not remove
	SELECT [carBrand_id], [name]
	FROM   [dbo].[CarBrands]
	WHERE  [carBrand_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarBrandsUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarBrandsUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CarBrandsUpdate] 
    @carBrand_id int,
    @name nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CarBrands]
	SET    [name] = @name
	WHERE  [carBrand_id] = @carBrand_id
	
	-- Begin Return Select <- do not remove
	SELECT [carBrand_id], [name]
	FROM   [dbo].[CarBrands]
	WHERE  [carBrand_id] = @carBrand_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarBrandsDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarBrandsDelete] 
END 
GO
CREATE PROC [dbo].[usp_CarBrandsDelete] 
    @carBrand_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CarBrands]
	WHERE  [carBrand_id] = @carBrand_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

