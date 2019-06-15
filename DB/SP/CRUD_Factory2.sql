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

	BEGIN TRAN

	SELECT [car_id], [doors], [fuelType_id], [acceleration], [maximum_speed], [price], [photo], [production_date] 
	FROM   [dbo].[Car] 
	WHERE  ([car_id] = @car_id OR @car_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarInsert] 
    @car_id int,
    @doors int = NULL,
    @fuelType_id int = NULL,
    @acceleration float = NULL,
    @maximum_speed float = NULL,
    @price money = NULL,
    @photo image = NULL,
    @production_date date = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[Car] ([car_id], [doors], [fuelType_id], [acceleration], [maximum_speed], [price], [photo], [production_date])
	SELECT @car_id, @doors, @fuelType_id, @acceleration, @maximum_speed, @price, @photo, @production_date
	
	-- Begin Return Select <- do not remove
	SELECT [car_id], [doors], [fuelType_id], [acceleration], [maximum_speed], [price], [photo], [production_date]
	FROM   [dbo].[Car]
	WHERE  [car_id] = @car_id
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
    @doors int = NULL,
    @fuelType_id int = NULL,
    @acceleration float = NULL,
    @maximum_speed float = NULL,
    @price money = NULL,
    @photo image = NULL,
    @production_date date = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[Car]
	SET    [doors] = ISNULL(@doors,[doors]), [fuelType_id] = ISNULL(@fuelType_id,[fuelType_id]), [acceleration] = ISNULL(@acceleration,[acceleration]), 
		   [maximum_speed] = ISNULL(@maximum_speed,[maximum_speed]), [price] = ISNULL(@price,[price]), [photo] = ISNULL(@photo,[photo]), [production_date] = ISNULL(@production_date,[production_date])
	WHERE  [car_id] = @car_id
	
	-- Begin Return Select <- do not remove
	SELECT [car_id], [doors], [fuelType_id], [acceleration], [maximum_speed], [price], [photo], [production_date]
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

