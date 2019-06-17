USE [BranchOfficeDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_SelectMasDemanda]    Script Date: 17/6/2019 01:07:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROC [dbo].[usp_SelectMasDemanda] 
    --@office_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON;

	SELECT cb.[name] as "marca", c.[model] as "modelo", c.[seats] as "pasajeros", c.[year] as "anio", count(so.salesOrder_id) as "cantidad", ca.[price] as "precio"
	FROM   [dbo].[SalesOrder] so
	inner join SalesOrderDetails sod on sod.salesOrder_id = so.salesOrder_id
	inner join CarSold cso on cso.car_sold_id = sod.car_sold_id
	inner join [LAPTOP-CCS17DF7\FACTORYINSTANCE].FactoryDB.dbo.Car c on c.car_id = cso.car_id
	inner join [LAPTOP-CCS17DF7\FACTORYINSTANCE].FactoryDB.dbo.CarBrands cb on cb.carBrand_id = c.carBrand_id
	inner join [LAPTOP-CCS17DF7\FACTORYINSTANCE2].FactoryDB.dbo.Car ca on ca.car_id = c.car_id
	group by cb.[name], c.[model], c.[seats], c.[year], ca.[price]
	order by [cantidad] desc