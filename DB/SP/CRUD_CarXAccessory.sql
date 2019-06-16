USE [FactoryDB];
GO

IF OBJECT_ID('[dbo].[usp_CarXAccessorySelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarXAccessorySelect] 
END 
GO
CREATE PROC [dbo].[usp_CarXAccessorySelect] 
    @car_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT ca.[carXAccessory_id], ca.[accessorie_id], ca.[price], a.name
	FROM   [dbo].[CarXAccessory] ca
	inner join Accessory a on a.accessory_id = ca.accessorie_id
	WHERE  [car_id] = @car_id

GO
IF OBJECT_ID('[dbo].[usp_CarXAccessoryInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarXAccessoryInsert] 
END 
GO
CREATE PROC [dbo].[usp_CarXAccessoryInsert] 
    @car_id int = NULL,
    @accessorie_id int = NULL,
    @price money = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	IF NOT EXISTS(SELECT 1 FROM [dbo].[CarXAccessory] WHERE ([car_id] = @car_id) AND ([accessorie_id] = @accessorie_id))
		BEGIN TRAN
		INSERT INTO [dbo].[CarXAccessory] ([car_id], [accessorie_id], [price])
		SELECT @car_id, @accessorie_id, @price
	
		-- Begin Return Select <- do not remove
		SELECT [carXAccessory_id], [car_id], [accessorie_id], [price]
		FROM   [dbo].[CarXAccessory]
		WHERE  [carXAccessory_id] = SCOPE_IDENTITY()
		-- End Return Select <- do not remove          
		COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarXAccessoryUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarXAccessoryUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CarXAccessoryUpdate] 
    @carXAccessory_id int,
    @car_id int = NULL,
    @accessorie_id int = NULL,
    @price money = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CarXAccessory]
	SET    [car_id] = @car_id, [accessorie_id] = @accessorie_id, [price] = @price
	WHERE  [carXAccessory_id] = @carXAccessory_id
	
	-- Begin Return Select <- do not remove
	SELECT [carXAccessory_id], [car_id], [accessorie_id], [price]
	FROM   [dbo].[CarXAccessory]
	WHERE  [carXAccessory_id] = @carXAccessory_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CarXAccessoryDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CarXAccessoryDelete] 
END 
GO
CREATE PROC [dbo].[usp_CarXAccessoryDelete] 
    @carXAccessory_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CarXAccessory]
	WHERE  [carXAccessory_id] = @carXAccessory_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

