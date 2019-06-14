USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_CreditPlanSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditPlanSelect] 
END 
GO
CREATE PROC [dbo].[usp_CreditPlanSelect] 
    @creditPlan_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [creditPlan_id], [prima], [interest], [anualTerm], [planName] 
	FROM   [dbo].[CreditPlan] 
	WHERE  ([creditPlan_id] = @creditPlan_id OR @creditPlan_id IS NULL) 

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CreditPlanInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditPlanInsert] 
END 
GO
CREATE PROC [dbo].[usp_CreditPlanInsert] 
    @prima float = NULL,
    @interest float = NULL,
    @anualTerm float = NULL,
    @planName nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CreditPlan] ([prima], [interest], [anualTerm], [planName])
	SELECT @prima, @interest, @anualTerm, @planName
	
	-- Begin Return Select <- do not remove
	SELECT [creditPlan_id], [prima], [interest], [anualTerm], [planName]
	FROM   [dbo].[CreditPlan]
	WHERE  [creditPlan_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CreditPlanUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditPlanUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CreditPlanUpdate] 
    @creditPlan_id int,
    @prima float = NULL,
    @interest float = NULL,
    @anualTerm float = NULL,
    @planName nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	UPDATE [dbo].[CreditPlan]
	SET    [prima] = @prima, [interest] = @interest, [anualTerm] = @anualTerm, [planName] = @planName
	WHERE  [creditPlan_id] = @creditPlan_id
	
	-- Begin Return Select <- do not remove
	SELECT [creditPlan_id], [prima], [interest], [anualTerm], [planName]
	FROM   [dbo].[CreditPlan]
	WHERE  [creditPlan_id] = @creditPlan_id	
	-- End Return Select <- do not remove

	COMMIT
GO
IF OBJECT_ID('[dbo].[usp_CreditPlanDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditPlanDelete] 
END 
GO
CREATE PROC [dbo].[usp_CreditPlanDelete] 
    @creditPlan_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CreditPlan]
	WHERE  [creditPlan_id] = @creditPlan_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

