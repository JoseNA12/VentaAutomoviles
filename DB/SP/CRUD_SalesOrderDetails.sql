USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_SalesOrderDetailsSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_SalesOrderDetailsSelect] 
END 
GO
CREATE PROC [dbo].[usp_SalesOrderDetailsSelect] 
    @orderDetails_id bigint
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [orderDetails_id], [salesOrder_id], [car_sold_id], [quantity], [price] 
	FROM   [dbo].[SalesOrderDetails] 
	WHERE  ([orderDetails_id] = @orderDetails_id OR @orderDetails_id IS NULL) 
GO
IF OBJECT_ID('[dbo].[usp_SalesOrderDetailsInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_SalesOrderDetailsInsert] 
END 
GO
CREATE PROC [dbo].[usp_SalesOrderDetailsInsert] 
    @salesOrder_id bigint = NULL,
    @car_sold_id int = NULL,
    @quantity int = NULL,
    @price money = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[SalesOrderDetails] ([salesOrder_id], [car_sold_id], [quantity], [price])
	SELECT @salesOrder_id, @car_sold_id, @quantity, @price
	
	-- Begin Return Select <- do not remove
	SELECT [orderDetails_id], [salesOrder_id], [car_sold_id], [quantity], [price]
	FROM   [dbo].[SalesOrderDetails]
	WHERE  [orderDetails_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_SalesOrderDetailsUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_SalesOrderDetailsUpdate] 
END 
GO
CREATE PROC [dbo].[usp_SalesOrderDetailsUpdate] 
    @orderDetails_id bigint,
    @salesOrder_id bigint = NULL,
    @car_sold_id int = NULL,
    @quantity int = NULL,
    @price money = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[SalesOrderDetails]
	SET    [salesOrder_id] = @salesOrder_id, [car_sold_id] = @car_sold_id, [quantity] = @quantity, [price] = @price
	WHERE  [orderDetails_id] = @orderDetails_id
	
	-- Begin Return Select <- do not remove
	SELECT [orderDetails_id], [salesOrder_id], [car_sold_id], [quantity], [price]
	FROM   [dbo].[SalesOrderDetails]
	WHERE  [orderDetails_id] = @orderDetails_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_SalesOrderDetailsDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_SalesOrderDetailsDelete] 
END 
GO
CREATE PROC [dbo].[usp_SalesOrderDetailsDelete] 
    @orderDetails_id bigint
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[SalesOrderDetails]
	WHERE  [orderDetails_id] = @orderDetails_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

