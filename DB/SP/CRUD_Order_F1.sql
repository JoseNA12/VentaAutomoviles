USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_OrderSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderSelect] 
END 
GO
CREATE PROC [dbo].[usp_OrderSelect] 
    @order_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON;

	WITH customerTable (customer_id, name, lastname, phone, zip_code, user_id, birthDate)
	AS(SELECT * FROM OPENQUERY([DESKTOP-3N2P4FH\HR_INSTANCE], 'SELECT customer_id, name, lastname, phone, zip_code, user_id, birthDate FROM HumanResourcesDB.dbo.Customer'))

	SELECT o.[order_id], o.[branchOffice], o.[factory_id], f.name as factoryName, o.[customer_id], cu.name as customerName, cu.lastname, cu.phone, u.email, o.[car_id], c.carBrand_id, 
	cb.name as brandName, c.model,o.[quantity], o2.order_date, o2.delivery_date, o2.details
	FROM   [dbo].[Order] o
	inner join Car c on o.car_id = c.car_id
	inner join CarBrands cb on cb.carBrand_id = c.carBrand_id
	inner join Factory f on f.factory_id = o.factory_id
	inner join customerTable cu on cu.customer_id = o.customer_id
	inner join [DESKTOP-3N2P4FH\HR_INSTANCE].HumanResourcesDB.dbo.[User] u on u.user_id = cu.user_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.[Order] o2 on o2.order_id = o.order_id
	WHERE  (o.[order_id] = @order_id OR @order_id IS NULL) 
GO

IF OBJECT_ID('[dbo].[usp_OrderInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderInsert] 
END 
GO
CREATE PROC [dbo].[usp_OrderInsert] 
    @branchOffice int = NULL,
    @factory_id int = NULL,
    @customer_id bigint = NULL,
    @car_id int = NULL,
    @quantity int = NULL,
	@delivery_date date = NULL,
	@details nvarchar(500) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	INSERT INTO [dbo].[Order] ([branchOffice], [factory_id], [customer_id], [car_id], [quantity])
	SELECT @branchOffice, @factory_id, @customer_id, @car_id, @quantity
	COMMIT

	DECLARE @lastID int;
	SET @lastID = (SELECT [order_id] FROM [dbo].[Order] WHERE  [order_id] = SCOPE_IDENTITY())

	EXEC [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.[usp_OrderInsert] @lastID, @delivery_date, @details

	-- Begin Return Select <- do not remove
	SELECT o.[order_id], o.[branchOffice], o.[factory_id], o.[customer_id], o.[car_id], o.[quantity], o2.order_date, o2.delivery_date, o2.details
	FROM   [dbo].[Order] o
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.[Order] o2 on o2.order_id = o.order_id
	WHERE  o.[order_id] = @lastID
	-- End Return Select <- do not remove             
GO

IF OBJECT_ID('[dbo].[usp_OrderUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderUpdate] 
END 
GO
CREATE PROC [dbo].[usp_OrderUpdate] 
    @order_id int,
	@delivery_date date = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	EXEC [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.[usp_OrderUpdate] @order_id, @delivery_date
	EXEC [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.[usp_OrderUpdate] @order_id, @delivery_date
GO
IF OBJECT_ID('[dbo].[usp_OrderDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderDelete] 
END 
GO
CREATE PROC [dbo].[usp_OrderDelete] 
    @order_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[Order]
	WHERE  [order_id] = @order_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

