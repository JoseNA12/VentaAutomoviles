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

	SELECT cs.[line_id], cs.[stock_id], cs.[quantity], c.[car_id], c.[model], c.[carBrand_id], cb.name, c.[motorType_id], mt.name, c.[price], c.[year], c.[photo], c.[passengers], c.[factory_id] 
	FROM   [dbo].[Car-Stock] cs
	inner join Stock s on s.stock_id = cs.stock_id
	inner join BranchOffice bo on bo.branchOffice_id = s.office_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.Car c on c.car_id = cs.car_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarBrands cb on cb.carBrand_id = c.carBrand_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.MotorType mt on mt.motorType_id = c.motorType_id
	WHERE  (bo.branchOffice_id = @office_id OR @office_id IS NULL) 

GO

IF OBJECT_ID('[dbo].[usp_Car-StockInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Car-StockInsert] 
END 
GO
CREATE PROC [dbo].[usp_Car-StockInsert] 
    @car_id int = NULL,
    @stock_id int = NULL,
    @quantity int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	IF EXISTS(SELECT car_id FROM [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.Car WHERE car_id = @car_id)
		BEGIN
		BEGIN TRAN
		INSERT INTO [dbo].[Car-Stock] ([car_id], [stock_id], [quantity])
		SELECT @car_id, @stock_id, @quantity
	
		-- Begin Return Select <- do not remove
		SELECT 0 as exit_status, [line_id], [car_id], [stock_id], [quantity]
		FROM   [dbo].[Car-Stock]
		WHERE  [line_id] = SCOPE_IDENTITY()
		-- End Return Select <- do not remove
               
		COMMIT
		END
	ELSE
		BEGIN
		SELECT 1 as exit_status, 'El auto ingresado no existe' as result
		END
	GO

IF OBJECT_ID('[dbo].[usp_Car-StockUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Car-StockUpdate] 
END 
GO
CREATE PROC [dbo].[usp_Car-StockUpdate] 
    @line_id bigint,
    @car_id int = NULL,
    @stock_id int = NULL,
    @quantity int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Car-Stock]
	SET    [car_id] = @car_id, [stock_id] = @stock_id, [quantity] = @quantity
	WHERE  [line_id] = @line_id
	
	-- Begin Return Select <- do not remove
	SELECT [line_id], [car_id], [stock_id], [quantity]
	FROM   [dbo].[Car-Stock]
	WHERE  [line_id] = @line_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_Car-StockDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_Car-StockDelete] 
END 
GO
CREATE PROC [dbo].[usp_Car-StockDelete] 
    @line_id bigint
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Car-Stock]
	WHERE  [line_id] = @line_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

