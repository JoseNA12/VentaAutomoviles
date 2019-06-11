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

	SELECT [car_id], [carBrand_id], [carType_id], [model], [engine], [year], [seats], [doors], [fuelType_id], [acceleration], [maximum_speed], [price], [photo] 
	FROM   [dbo].[Car] 
	WHERE  ([car_id] = @car_id OR @car_id IS NULL) 

GO
IF OBJECT_ID('[dbo].[usp_CarInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarInsert] 
    @carBrand_id int = NULL,
    @carType_id int = NULL,
    @model nvarchar(50) = NULL,
    @engine nvarchar(50) = NULL,
    @year int = NULL,
    @seats int = NULL,
    @doors int = NULL,
    @fuelType_id int = NULL,
    @acceleration float = NULL,
    @maximum_speed float = NULL,
    @price money = NULL,
    @photo image = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Car] ([carBrand_id], [carType_id], [model], [engine], [year], [seats], [doors], [fuelType_id], [acceleration], [maximum_speed], [price], [photo])
	SELECT @carBrand_id, @carType_id, @model, @engine, @year, @seats, @doors, @fuelType_id, @acceleration, @maximum_speed, @price, @photo
	
	-- Begin Return Select <- do not remove
	SELECT [car_id], [carBrand_id], [carType_id], [model], [engine], [year], [seats], [doors], [fuelType_id], [acceleration], [maximum_speed], [price], [photo]
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
    @carBrand_id int = NULL,
    @carType_id int = NULL,
    @model nvarchar(50) = NULL,
    @engine nvarchar(50) = NULL,
    @year int = NULL,
    @seats int = NULL,
    @doors int = NULL,
    @fuelType_id int = NULL,
    @acceleration float = NULL,
    @maximum_speed float = NULL,
    @price money = NULL,
    @photo image = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Car]
	SET    [carBrand_id] = @carBrand_id, [carType_id] = @carType_id, [model] = @model, [engine] = @engine, [year] = @year, [seats] = @seats, [doors] = @doors, [fuelType_id] = @fuelType_id, [acceleration] = @acceleration, [maximum_speed] = @maximum_speed, [price] = @price, [photo] = @photo
	WHERE  [car_id] = @car_id
	
	-- Begin Return Select <- do not remove
	SELECT [car_id], [carBrand_id], [carType_id], [model], [engine], [year], [seats], [doors], [fuelType_id], [acceleration], [maximum_speed], [price], [photo]
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

