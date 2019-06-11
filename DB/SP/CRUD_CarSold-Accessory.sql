USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_CarSold-AccesorySelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSold-AccesorySelect] 
END 
GO
CREATE PROC [dbo].[usp_CarSold-AccesorySelect] 
    @line int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [line], [car_sold_id], [accesory_id] 
	FROM   [dbo].[CarSold-Accesory] 
	WHERE  ([line] = @line OR @line IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSold-AccesoryInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSold-AccesoryInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarSold-AccesoryInsert] 
    @line int,
    @car_sold_id int = NULL,
    @accesory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CarSold-Accesory] ([line], [car_sold_id], [accesory_id])
	SELECT @line, @car_sold_id, @accesory_id
	
	-- Begin Return Select <- do not remove
	SELECT [line], [car_sold_id], [accesory_id]
	FROM   [dbo].[CarSold-Accesory]
	WHERE  [line] = @line
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSold-AccesoryUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSold-AccesoryUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CarSold-AccesoryUpdate] 
    @line int,
    @car_sold_id int = NULL,
    @accesory_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CarSold-Accesory]
	SET    [car_sold_id] = @car_sold_id, [accesory_id] = @accesory_id
	WHERE  [line] = @line
	
	-- Begin Return Select <- do not remove
	SELECT [line], [car_sold_id], [accesory_id]
	FROM   [dbo].[CarSold-Accesory]
	WHERE  [line] = @line	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarSold-AccesoryDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarSold-AccesoryDelete] 
END 
GO
CREATE PROC [dbo].[usp_CarSold-AccesoryDelete] 
    @line int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CarSold-Accesory]
	WHERE  [line] = @line

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

