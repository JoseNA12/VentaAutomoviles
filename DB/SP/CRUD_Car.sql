USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_CarSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSelect] 
END 
GO
CREATE PROC [dbo].[usp_CarSelect] 
    @car_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT c.[car_id], c.[model], c.[carBrand_id], cb.name, c.[motorType_id], mt.name, c.[price], c.[year], c.[photo], c.[passengers], c.[factory_id] 
	FROM   [dbo].[Car] c
	inner join CarBrands cb on cb.carBrand_id = c.carBrand_id
	inner join MotorType mt on mt.motorType_id = c.motorType_id
	WHERE  ([car_id] = @car_id OR @car_id IS NULL) 

GO

IF OBJECT_ID('[dbo].[usp_CarInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarInsert] 
    @model nvarchar(50) = NULL,
    @carBrand_id int = NULL,
    @motorType_id int = NULL,
    @price money = NULL,
    @year int = NULL,
    @photo image = NULL,
    @passengers int = NULL,
    @factory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	IF EXISTS(SELECT 1 FROM Car WHERE model = @model AND carBrand_id = @carBrand_id AND motorType_id = @motorType_id AND year = @year AND factory_id = @factory_id )
		BEGIN
		SELECT 1 as exit_status, 'El auto ya existe en la fábrica' as result
		END
	ELSE
		BEGIN TRAN
		INSERT INTO [dbo].[Car] ([model], [carBrand_id], [motorType_id], [price], [year], [photo], [passengers], [factory_id])
		SELECT @model, @carBrand_id, @motorType_id, @price, @year, @photo, @passengers, @factory_id
	
		-- Begin Return Select <- do not remove
		SELECT [car_id], [model], [carBrand_id], [motorType_id], [price], [year], [photo], [passengers], [factory_id]
		FROM   [dbo].[Car]
		WHERE  [car_id] = SCOPE_IDENTITY()
		-- End Return Select <- do not remove
               
		COMMIT
GO

IF OBJECT_ID('[dbo].[usp_CarUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CarUpdate] 
    @car_id int,
    @model nvarchar(50) = NULL,
    @carBrand_id int = NULL,
    @motorType_id int = NULL,
    @price money = NULL,
    @year int = NULL,
    @photo image = NULL,
    @passengers int = NULL,
    @factory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Car]
	SET    [model] = @model, [carBrand_id] = @carBrand_id, [motorType_id] = @motorType_id, [price] = @price, [year] = @year, [photo] = @photo, [passengers] = @passengers, [factory_id] = @factory_id
	WHERE  [car_id] = @car_id
	
	-- Begin Return Select <- do not remove
	SELECT [car_id], [model], [carBrand_id], [motorType_id], [price], [year], [photo], [passengers], [factory_id]
	FROM   [dbo].[Car]
	WHERE  [car_id] = @car_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarDelete] 
END 
GO
CREATE PROC [dbo].[usp_CarDelete] 
    @car_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Car]
	WHERE  [car_id] = @car_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

