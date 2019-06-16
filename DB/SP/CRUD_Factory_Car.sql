USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_Factory-CarSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarSelect] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarSelect] 
    @factory_car_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT fc.[factory_car_id], fc.[car_id], fc.[factory_id], fc.[quantity], c.[carBrand_id], cb.name as Brandname, c.[carType_id], ct.name as typeName, 
	c.[model], c.[engine], c.[year], c.[seats], c2.[doors], c2.[fuelType_id], cf.name as fuelName, c2.[acceleration], c2.[maximum_speed], c2.[price], 
	c2.[photo], c2.production_date
	FROM   [dbo].[Factory-Car] fc 
	inner join [dbo].[Car] c on c.car_id = fc.car_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.Car c2 on c2.car_id = c.car_id
	inner join CarBrands cb on cb.carBrand_id = c.car_id
	inner join CarType ct on ct.carType_id = c.carType_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.FuelType cf on cf.fuelType_id = c2.fuelType_id
	WHERE  (fc.[factory_car_id] = @factory_car_id OR @factory_car_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_Factory-CarInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarInsert] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarInsert] 
    @car_id int = NULL,
    @factory_id int = NULL,
    @quantity int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Factory-Car] ([car_id], [factory_id], [quantity])
	SELECT @car_id, @factory_id, @quantity
	
/*	-- Begin Return Select <- do not remove
	SELECT [factory_car_id], [car_id], [factory_id], [quantity]
	FROM   [dbo].[Factory-Car]
	WHERE  [factory_car_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove*/
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_Factory-CarUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarUpdate] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarUpdate] 
    @factory_car_id int,
    @car_id int = NULL,
    @factory_id int = NULL,
    @quantity int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Factory-Car]
	SET    [car_id] = @car_id, [factory_id] = @factory_id, [quantity] = @quantity
	WHERE  [factory_car_id] = @factory_car_id
	
	-- Begin Return Select <- do not remove
	SELECT [factory_car_id], [car_id], [factory_id], [quantity]
	FROM   [dbo].[Factory-Car]
	WHERE  [factory_car_id] = @factory_car_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_Factory-CarDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarDelete] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarDelete] 
    @factory_car_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Factory-Car]
	WHERE  [factory_car_id] = @factory_car_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
IF OBJECT_ID('[dbo].[usp_Factory-CarInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Factory-CarInsert] 
END 
GO
CREATE PROC [dbo].[usp_Factory-CarInsert] 
    @car_id int = NULL,
    @factory_id int = NULL,
    @quantity int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Factory-Car] ([car_id], [factory_id], [quantity])
	SELECT @car_id, @factory_id, @quantity
	
	/*-- Begin Return Select <- do not remove
	SELECT [factory_car_id], [car_id], [factory_id], [quantity]
	FROM   [dbo].[Factory-Car]
	WHERE  [factory_car_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove*/
               
	COMMIT
GO
