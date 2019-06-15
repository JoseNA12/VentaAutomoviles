USE [BranchOfficeDB];
GO

IF OBJECT_ID('[dbo].[usp_CreditGivenSelect]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenSelect] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenSelect] 
    @credit_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT [credit_id], [order_id], nextPayment_date, [creditPlan_id], [balance], [mensualPayment], [creditStatus] 
	FROM   [dbo].[CreditGiven] 
	WHERE  ([credit_id] = @credit_id OR @credit_id IS NULL) 

GO

IF OBJECT_ID('[dbo].[usp_CreditGivenSelectByPlan]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].usp_CreditGivenSelectByPlan 
END 
GO
CREATE PROC [dbo].usp_CreditGivenSelectByPlan 
	@creditPlan_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	SELECT cg.[credit_id], cg.[balance], DATEDIFF(month, (DATEADD(month, -1, cg.nextPayment_date)), (DATEADD(month, cp.anualTerm * 12,so.order_date))) as remainingMonths, cp.anualTerm
	FROM CreditGiven cg
	inner join SalesOrder so on so.salesOrder_id = cg.order_id
	inner join CreditPlan cp on cp.creditPlan_id = cg.creditPlan_id
	WHERE  (cg.creditPlan_id = @creditPlan_id OR @creditPlan_id IS NULL) AND cg.creditStatus = 1

GO
--exec usp_CreditGivenSelectByPlan 1
--SELECT DATEDIFF(month, (DATEADD(month, -1, '2019-07-14')), (DATEADD(month, 3.5 * 12, '2019-06-14'))) as remainingMonths
--SELECT (DATEADD(month, 3.5 * 12, '2019-06-14'));
--SELECT (DATEADD(month, -1, '2019-07-14'))
IF OBJECT_ID('[dbo].[usp_CreditGivenInsert]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenInsert] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenInsert] 
    @order_id bigint = NULL,
    @creditPlan_id int = NULL,
    @balance money = NULL,
    @mensualPayment float = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	
	INSERT INTO [dbo].[CreditGiven] ([order_id], nextPayment_date, [creditPlan_id], [balance], [mensualPayment], [creditStatus])
	SELECT @order_id, dateadd(m, 1, getdate()), @creditPlan_id, @balance, @mensualPayment, 1
	
	-- Begin Return Select <- do not remove
	SELECT [credit_id], [order_id], nextPayment_date, [creditPlan_id], [balance], [mensualPayment], [creditStatus]
	FROM   [dbo].[CreditGiven]
	WHERE  [credit_id] = SCOPE_IDENTITY()
	-- End Return Select <- do not remove
               
	COMMIT
GO
--exec usp_CreditGivenUpdateMensualPayment 1, 110

IF OBJECT_ID('[dbo].[usp_CreditGivenUpdateMensualPayment]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].usp_CreditGivenUpdateMensualPayment 
END 
GO
CREATE PROC [dbo].usp_CreditGivenUpdateMensualPayment 
    @credit_id int,
	@mensualPayment float
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	UPDATE [dbo].[CreditGiven]
	SET    [mensualPayment] = @mensualPayment
	WHERE  [credit_id] = @credit_id
	
	-- Begin Return Select <- do not remove
	SELECT [credit_id], [order_id], nextPayment_date, [creditPlan_id], [balance], [mensualPayment], [creditStatus]
	FROM   [dbo].[CreditGiven]
	WHERE  [credit_id] = @credit_id	
	-- End Return Select <- do not remove

	COMMIT
GO

IF OBJECT_ID('[dbo].[usp_CreditGivenUpdate]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenUpdate] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenUpdate] 
    @credit_id int,
	@mensualPayment float = NULL,
    @order_id bigint = NULL,
    @nextPayment_date date = NULL,
    @creditPlan_id int = NULL,
    @balance money = NULL,
    @creditStatus int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	UPDATE [dbo].[CreditGiven]
	SET    [order_id] = ISNULL(@order_id, [order_id]), nextPayment_date = ISNULL(@nextPayment_date, nextPayment_date), [creditPlan_id] = ISNULL(@creditPlan_id, [creditPlan_id]), [balance] = ([balance] - @mensualPayment), [mensualPayment] = ISNULL(@mensualPayment,[mensualPayment]), [creditStatus] = ISNULL(@creditStatus, [creditStatus])
	WHERE  [credit_id] = @credit_id
	
	-- Begin Return Select <- do not remove
	SELECT [credit_id], [order_id], nextPayment_date, [creditPlan_id], [balance], [mensualPayment], [creditStatus]
	FROM   [dbo].[CreditGiven]
	WHERE  [credit_id] = @credit_id	
	-- End Return Select <- do not remove

	COMMIT
GO

IF OBJECT_ID('[dbo].[usp_CreditGivenUpdateNextPayment]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenUpdateNextPayment] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenUpdateNextPayment] 
    @credit_id int,
    @nextPayment_date date
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN
	UPDATE [dbo].[CreditGiven]
	SET    nextPayment_date = @nextPayment_date, [balance] = ([balance] - [mensualPayment])
	WHERE  [credit_id] = @credit_id
	
	-- Begin Return Select <- do not remove
	SELECT [credit_id], [order_id], nextPayment_date, [creditPlan_id], [balance], [mensualPayment], [creditStatus]
	FROM   [dbo].[CreditGiven]
	WHERE  [credit_id] = @credit_id	
	-- End Return Select <- do not remove

	COMMIT
GO

IF OBJECT_ID('[dbo].[usp_CreditGivenDelete]') IS NOT NULL
BEGIN 
    DROP PROC [dbo].[usp_CreditGivenDelete] 
END 
GO
CREATE PROC [dbo].[usp_CreditGivenDelete] 
    @credit_id int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	
	BEGIN TRAN

	DELETE
	FROM   [dbo].[CreditGiven]
	WHERE  [credit_id] = @credit_id

	COMMIT
GO
----------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------

