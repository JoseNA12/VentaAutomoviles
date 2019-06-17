USE [FactoryDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_OrderUpdateEnviado]    Script Date: 17/6/2019 16:06:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROC [dbo].[usp_OrderUpdateEnviado] 
    @order_id int,
	@delivery_date date = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	update [Order]
	set orderStatus = 1
	where order_id = @order_id
	EXEC [LAPTOP-CCS17DF7\FACTORYINSTANCE2].FactoryDB.dbo.[usp_OrderUpdate] @order_id, @delivery_date