USE [BranchOfficeDB];
GO


IF OBJECT_ID('[dbo].[usp_Car-StockSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Car-StockSelect] 
END 
GO
CREATE PROC [dbo].[usp_Car-StockSelect] 
	@office_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	IF EXISTS(SELECT branchOffice_id FROM BranchOffice WHERE branchOffice_id = @office_id)
		SELECT cs.[car_stock_id], cs.[stock_id], cs.[quantity], c.car_id, c.carBrand_id, cb.name as "brand", c.carType_id, ct.name as typeName, c.model, 
		c.engine, c.year, c.seats, c2.doors, c2.fuelType_id, cf.name as "fuel", c2.acceleration, c2.maximum_speed, c2.price, c2.photo, c2.production_date, fc.factory_id
		FROM [Car-Stock] cs 
		inner join Stock s on s.stock_id = cs.stock_id
		inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.Car c on c.car_id = cs.car_id
		inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.Car c2 on c2.car_id = c.car_id
		inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarBrands cb on cb.carBrand_id = c.carBrand_id
		inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarType ct on ct.carType_id = c.carType_id
		inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.FuelType cf on cf.fuelType_id = c2.fuelType_id
		inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.[Factory-Car] fc on fc.car_id = c.car_id
		WHERE (s.office_id = @office_id OR @office_id IS NULL)
	ELSE
		SELECT 1 as exit_status, 'La sucursal ingresada no existe' as result

GO

exec [usp_Car-StockInsert] 1,1,1,1

IF OBJECT_ID('[dbo].[usp_Car-StockInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Car-StockInsert] 
END 
GO

CREATE PROC [dbo].[usp_Car-StockInsert] 
    @car_id int = NULL,
    @stock_id int = NULL,
    @quantity int = NULL,
	@idFabrica int = NULL
AS 
	BEGIN
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	EXEC [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.usp_reduceCarsInFactory @car_id, @idFabrica, @quantity

	IF EXISTS(SELECT car_id FROM [dbo].[Car-Stock] WHERE car_id = @car_id)
		BEGIN
		DECLARE @currentQuantity int;
		SET @currentQuantity = (SELECT [quantity] FROM [dbo].[Car-Stock] WHERE car_id = @car_id)

		BEGIN TRAN
		UPDATE [dbo].[Car-Stock]
		SET [quantity] = (SELECT @currentQuantity + @quantity)
		WHERE [car_id] = @car_id
		COMMIT

		-- Begin Return Select <- do not remove
		SELECT 0 as exit_status, car_stock_id, [car_id], [stock_id], [quantity]
		FROM   [dbo].[Car-Stock]
		WHERE  [car_id] = @car_id
		-- End Return Select <- do not remove
		
		END
	ELSE
		BEGIN
		BEGIN TRAN
		
		INSERT INTO [dbo].[Car-Stock] ([car_id], [stock_id], [quantity])
		SELECT @car_id, @stock_id, @quantity

		COMMIT

		-- Begin Return Select <- do not remove
		SELECT 0 as exit_status, car_stock_id, [car_id], [stock_id], [quantity]
		FROM   [dbo].[Car-Stock]
		WHERE  car_stock_id = SCOPE_IDENTITY()
		-- End Return Select <- do not remove
		
		END
	END
	GO

IF OBJECT_ID('[dbo].[usp_Car-StockUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Car-StockUpdate] 
END 
GO
CREATE PROC [dbo].[usp_Car-StockUpdate] 
    @line_id int,
    @car_id int = NULL,
    @stock_id int = NULL,
    @quantity int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Car-Stock]
	SET    [car_id] = @car_id, [stock_id] = @stock_id, [quantity] = @quantity
	WHERE  car_stock_id = @line_id
	
	-- Begin Return Select <- do not remove
	SELECT car_stock_id, [car_id], [stock_id], [quantity]
	FROM   [dbo].[Car-Stock]
	WHERE  car_stock_id = @line_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_Car-StockDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Car-StockDelete] 
END 
GO
CREATE PROC [dbo].[usp_Car-StockDelete] 
    @line_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Car-Stock]
	WHERE  car_stock_id = @line_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
exec usp_reduceCarStock 1, 1

IF OBJECT_ID('[dbo].usp_reduceCarStock') IS NOT NULL
BEGIN 
    DROP PROC [dbo].usp_reduceCarStock 
END 
GO
CREATE PROC [dbo].usp_reduceCarStock 
    @office_id int,
    @car_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	DECLARE @stockID int;
	SET @stockID = (SELECT stock_id FROM Stock WHERE office_id = @office_id);
	BEGIN TRAN
	UPDATE [dbo].[Car-Stock]
	SET    [quantity] = [quantity] - 1
	WHERE  (car_id = @car_id) AND (stock_id = @stockID)
	COMMIT
GO
