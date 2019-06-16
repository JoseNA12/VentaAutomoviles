USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_OrderSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderSelect] 
END 
GO
CREATE PROC [dbo].[usp_OrderSelect] 
    @order_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [order_id], [branchOffice], [factory_id], [customer_id], [car_id], [quantity], [deliveryDate], [dateSent], [details] 
	FROM   [dbo].[Order] 
	WHERE  ([order_id] = @order_id OR @order_id IS NULL) 

	COMMIT
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
    @deliveryDate date = NULL,
    @details nvarchar(500) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Order] ([branchOffice], [factory_id], [customer_id], [car_id], [quantity], [deliveryDate], [dateSent], [details])
	SELECT @branchOffice, @factory_id, @customer_id, @car_id, @quantity, @deliveryDate, GETDATE(), @details
	
	-- Begin Return Select <- do not remove
	SELECT [order_id], [branchOffice], [factory_id], [customer_id], [car_id], [quantity], [deliveryDate], [dateSent], [details]
	FROM   [dbo].[Order]
	WHERE  [order_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_OrderUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_OrderUpdate] 
END 
GO
CREATE PROC [dbo].[usp_OrderUpdate] 
    @order_id int,
    @branchOffice int = NULL,
    @factory_id int = NULL,
    @customer_id bigint = NULL,
    @car_id int = NULL,
    @quantity int = NULL,
    @deliveryDate date = NULL,
    @dateSent date = NULL,
    @details nvarchar(500) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Order]
	SET    [branchOffice] = @branchOffice, [factory_id] = @factory_id, [customer_id] = @customer_id, [car_id] = @car_id, [quantity] = @quantity, [deliveryDate] = @deliveryDate, [dateSent] = @dateSent, [details] = @details
	WHERE  [order_id] = @order_id
	
	-- Begin Return Select <- do not remove
	SELECT [order_id], [branchOffice], [factory_id], [customer_id], [car_id], [quantity], [deliveryDate], [dateSent], [details]
	FROM   [dbo].[Order]
	WHERE  [order_id] = @order_id	
	-- End Return Select <- do not remove

	COMMIT
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

