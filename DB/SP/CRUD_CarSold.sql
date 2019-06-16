USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_CarSoldSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSoldSelect] 
END 
GO
CREATE PROC [dbo].[usp_CarSoldSelect] 
    @car_sold_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [car_sold_id], [car_id] 
	FROM   [dbo].[CarSold] 
	WHERE  ([car_sold_id] = @car_sold_id OR @car_sold_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSoldInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSoldInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarSoldInsert] 
    @car_id int,
	@office_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CarSold] ([car_id])
	SELECT @car_id

	EXEC usp_reduceCarStock @office_id, @car_id

	-- Begin Return Select <- do not remove
	SELECT [car_sold_id], [car_id]
	FROM   [dbo].[CarSold]
	WHERE  [car_sold_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSoldUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSoldUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CarSoldUpdate] 
    @car_sold_id int,
    @car_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CarSold]
	SET    [car_id] = @car_id
	WHERE  [car_sold_id] = @car_sold_id
	
	-- Begin Return Select <- do not remove
	SELECT [car_sold_id], [car_id]
	FROM   [dbo].[CarSold]
	WHERE  [car_sold_id] = @car_sold_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSoldDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSoldDelete] 
END 
GO
CREATE PROC [dbo].[usp_CarSoldDelete] 
    @car_sold_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CarSold]
	WHERE  [car_sold_id] = @car_sold_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

