USE [BranchOfficeDB];
GO

/*
Consultar ventas x sucursal x tipo de automóvil x país y/o por fechas. Ventas por tipo de pago x sucursal y por fechas.
*/
IF OBJECT_ID('[dbo].[usp_SalesOrderSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_SalesOrderSelect] 
END 
GO
CREATE PROC [dbo].[usp_SalesOrderSelect] 
    @office_id int = NULL,
	@carType int = NULL,
	@country int = NULL,
	@date1 date = NULL,
	@date2 date = NULL,
	@paymentMethod_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	-- [DESKTOP-3N2P4FH\HR_INSTANCE].HumanResourcesDB.dbo.Employee
	SELECT so.[salesOrder_id], so.[customer_id], so.[order_status], so.[order_date], so.[paymentMethod_id], so.[office_id], so.totalPrice, so.discount
	FROM   [dbo].[SalesOrder] so
	inner join SalesOrderDetails sod on sod.salesOrder_id = so.salesOrder_id
	inner join CarSold cso on cso.car_sold_id = sod.car_sold_id
	inner join [Car-Stock] cst on cst.car_stock_id = cso.car_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.Car c on c.car_id = cst.car_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarType ct on ct.carType_id = c.carType_id
	inner join BranchOffice bo on bo.branchOffice_id = so.office_id
	inner join PaymentMethod pm on pm.paymentMethod_id = so.paymentMethod_id
	WHERE  ([office_id] = @office_id OR @office_id IS NULL) OR (c.carType_id = @carType OR @carType IS NULL) OR
		(bo.country_id = @country OR @country IS NULL) OR ((so.[order_date] < @date1 AND so.[order_date] > @date2) OR (@date1 IS NULL AND @date2 IS NULL)) OR
		(so.[paymentMethod_id] = @paymentMethod_id OR @paymentMethod_id IS NULL)

GO

/*
Otra parte importante a tomar en cuenta es que la sucursal puede otorgar crédito al comprador, 
por lo que cuando alguien desea que se les financie un automóvil se debe entonces cobrar un 20% de prima y 
luego llevar control de los diferentes pagos que debe realizar a una tasa de interés que puede ser variable. 
Un usuario facturador es el encargado de realizar la factura a cada cliente. Un cliente recibe un 10% de descuento
 en la compra del automóvil si es un cliente que ha realizado más de 3 compras de automóviles en los últimos 5 años, 
 el descuento puede ser variable. Deben tomar en cuenta que, si cada pago que se realiza es con tarjeta de crédito, 
 se le retiene al establecimiento el 10% del monto de la compra como parte del impuesto de ventas que se deben 
 entregar a hacienda cada final de mes. La compra se puede hacer en línea también, por lo que el usuario 
 debe poder enviar y guardar su identificación para validar que es una persona real y mayor a 18 años, 
 no se puede vender un automóvil a personas menores de 18 años.

*/
IF OBJECT_ID('[dbo].[usp_SalesOrderInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_SalesOrderInsert] 
END 
GO
CREATE PROC [dbo].[usp_SalesOrderInsert] 
    @customer_id bigint,
    @paymentMethod_id int,
    @office_id int,
	@totalPrice money,
	@payment money,
	@orderStatus int
AS 
	BEGIN
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	DECLARE @amountPurchases int
	SET @amountPurchases = (SELECT SUM(salesOrder_id) FROM SalesOrder WHERE customer_id = @customer_id AND (year(getdate()) - 5 <= year(order_date)))
	IF(@amountPurchases > 3 AND @orderStatus = 2) -- Discount and without credit
		BEGIN 
		BEGIN TRAN
		INSERT INTO [dbo].[SalesOrder] ([customer_id], [order_status],  [order_date], [paymentMethod_id], [office_id], [totalPrice], totalPayment, discount)
		SELECT @customer_id, 1, GETDATE(), @paymentMethod_id, @office_id, @totalPrice, @payment - (@payment * 0.10), 0.10
	
		-- Begin Return Select <- do not remove
		SELECT [salesOrder_id], [customer_id], [order_status], [order_date], [paymentMethod_id], [office_id], [totalPrice], totalPayment, discount
		FROM   [dbo].[SalesOrder]
		WHERE  [salesOrder_id] = SCOPE_IDENTITY()
		-- End Return Select <- do not remove
		COMMIT
		END
	ELSE
		BEGIN -- Credit or Without discount
		BEGIN TRAN
		INSERT INTO [dbo].[SalesOrder] ([customer_id], [order_status],  [order_date], [paymentMethod_id], [office_id], [totalPrice], totalPayment, discount)
		SELECT @customer_id, @orderStatus, GETDATE(), @paymentMethod_id, @office_id, @totalPrice, @payment, 0
	
		-- Begin Return Select <- do not remove
		SELECT [salesOrder_id], [customer_id], [order_status], [order_date], [paymentMethod_id], [office_id], [totalPrice], totalPayment, discount
		FROM   [dbo].[SalesOrder]
		WHERE  [salesOrder_id] = SCOPE_IDENTITY()
		-- End Return Select <- do not remove      
		COMMIT
		END		
	END
GO

IF OBJECT_ID('[dbo].[usp_SalesOrderUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_SalesOrderUpdate] 
END 
GO
CREATE PROC [dbo].[usp_SalesOrderUpdate] 
    @salesOrder_id bigint,
    @customer_id bigint = NULL,
    @order_status int = NULL,
    @order_date datetime = NULL,
    @paymentMethod_id int = NULL,
    @office_id int = NULL,
    @totalPrice money = NULL,
    @discount float = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[SalesOrder]
	SET    [customer_id] = @customer_id, [order_status] = @order_status, [order_date] = @order_date, [paymentMethod_id] = @paymentMethod_id, [office_id] = @office_id, [totalPrice] = @totalPrice, [discount] = @discount
	WHERE  [salesOrder_id] = @salesOrder_id
	
	-- Begin Return Select <- do not remove
	SELECT [salesOrder_id], [customer_id], [order_status], [order_date], [paymentMethod_id], [office_id], [totalPrice], [discount]
	FROM   [dbo].[SalesOrder]
	WHERE  [salesOrder_id] = @salesOrder_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_SalesOrderDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_SalesOrderDelete] 
END 
GO
CREATE PROC [dbo].[usp_SalesOrderDelete] 
    @salesOrder_id bigint
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[SalesOrder]
	WHERE  [salesOrder_id] = @salesOrder_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

