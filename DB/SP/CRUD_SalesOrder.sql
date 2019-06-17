USE [BranchOfficeDB];
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

	DECLARE @taxAmount money
	IF(@paymentMethod_id = 1) -- Credit card
		BEGIN
		SET @taxAmount = (@totalPrice * 0.10)
		END
	ELSE 
		BEGIN
		SET @taxAmount = 0
		END
	
	DECLARE @amountPurchases int
	SET @amountPurchases = (SELECT SUM(salesOrder_id) FROM SalesOrder WHERE customer_id = @customer_id AND (year(getdate()) - 5 <= year(order_date)))
	IF(@amountPurchases > 3 AND @orderStatus = 2) -- Discount and without loan
		BEGIN 
		BEGIN TRAN
		INSERT INTO [dbo].[SalesOrder] ([customer_id], [order_status],  [order_date], [paymentMethod_id], [office_id], [totalPrice], totalPayment, discount, tax)
		SELECT @customer_id, 1, GETDATE(), @paymentMethod_id, @office_id, @totalPrice, @payment - (@payment * 0.10), 0.10, @taxAmount
	
		-- Begin Return Select <- do not remove
		SELECT 1 as isDiscount, [salesOrder_id], [customer_id], [order_status], [order_date], [paymentMethod_id], [office_id], [totalPrice], totalPayment, discount
		FROM   [dbo].[SalesOrder]
		WHERE  [salesOrder_id] = SCOPE_IDENTITY()
		-- End Return Select <- do not remove
		COMMIT
		END
	ELSE
		BEGIN -- loan or Without discount
		BEGIN TRAN
		INSERT INTO [dbo].[SalesOrder] ([customer_id], [order_status],  [order_date], [paymentMethod_id], [office_id], [totalPrice], totalPayment, discount, tax)
		SELECT @customer_id, @orderStatus, GETDATE(), @paymentMethod_id, @office_id, @totalPrice, @payment, 0, @taxAmount
	
		-- Begin Return Select <- do not remove
		SELECT 0 as isDiscount, [salesOrder_id], [customer_id], [order_status], [order_date], [paymentMethod_id], [office_id], [totalPrice], totalPayment, discount
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

