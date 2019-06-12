USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_CarSold-AccessorySelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSold-AccessorySelect] 
END 
GO
CREATE PROC [dbo].[usp_CarSold-AccessorySelect] 
    @line int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [line], [car_sold_id], [accessory_id] 
	FROM   [dbo].[CarSold-Accessory] 
	WHERE  ([line] = @line OR @line IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSold-AccessoryInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSold-AccessoryInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarSold-AccessoryInsert] 
    @car_sold_id int = NULL,
    @accessory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CarSold-Accessory] ([car_sold_id], [accessory_id])
	SELECT @car_sold_id, @accessory_id
	
	-- Begin Return Select <- do not remove
	SELECT [line], [car_sold_id], [accessory_id]
	FROM   [dbo].[CarSold-Accessory]
	WHERE  [line] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSold-AccessoryUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSold-AccessoryUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CarSold-AccessoryUpdate] 
    @line int,
    @car_sold_id int = NULL,
    @accessory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CarSold-Accessory]
	SET    [car_sold_id] = @car_sold_id, [accessory_id] = @accessory_id
	WHERE  [line] = @line
	
	-- Begin Return Select <- do not remove
	SELECT [line], [car_sold_id], [accessory_id]
	FROM   [dbo].[CarSold-Accessory]
	WHERE  [line] = @line	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSold-AccessoryDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSold-AccessoryDelete] 
END 
GO
CREATE PROC [dbo].[usp_CarSold-AccessoryDelete] 
    @line int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CarSold-Accessory]
	WHERE  [line] = @line

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

