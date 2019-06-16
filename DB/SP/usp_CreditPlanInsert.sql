USE [BranchOfficeDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_CreditPlanInsert]    Script Date: 15/6/2019 21:55:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROC [dbo].[usp_CreditPlanInsert] 
    @prima float = NULL,
    @interest float = NULL,
    @anualTerm float = NULL,
    @planName nvarchar(50) = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CreditPlan] ([prima], [interest], [anualTerm])
	VALUES (@prima,@interest,@anualTerm)
	--SELECT @prima, @interest, @anualTerm, @planName
	
	-- Begin Return Select <- do not remove
	SELECT [creditPlan_id], [prima], [interest], [anualTerm], [planName]
	FROM   [dbo].[CreditPlan]
	WHERE  [creditPlan_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT