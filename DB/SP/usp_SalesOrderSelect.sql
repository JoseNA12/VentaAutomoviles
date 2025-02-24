USE [BranchOfficeDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_SalesOrderSelect]    Script Date: 16/6/2019 17:43:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROC [dbo].[usp_SalesOrderSelect] 
    @office_id int = NULL,
	@carType int = NULL,
	@country int = NULL,
	@date1 date = NULL,
	@date2 date = NULL,
	@paymentMethod_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON;
	--Create table customerTable(customer_id int, name varchar(50),lastname varchar(50), phone varchar(50), zip_code int, user_id int, bithDate date)
	With customerTable(customer_id, name, lastname, phone, zip_code, user_id, birthDate)
	AS(SELECT * FROM OPENQUERY([DESKTOP-3N2P4FH\HR_INSTANCE],'SELECT customer_id, name, lastname, phone, zip_code, user_id, birthDate FROM HumanResourcesDB.dbo.Customer'))
	-- [DESKTOP-3N2P4FH\HR_INSTANCE].HumanResourcesDB.dbo.Employee

	SELECT cb.[name] as "marca", c.[model] as "modelo",c.[year] as "anio", c.[seats] as "pasajeros",
			 ca.[price] as "precio", ct.[name] as "tipo", cl.[name] as "nombreCl", cl.[lastname] as "apellidos",
			 cl.[phone] as "telefonoCl", pm.[name] as "metodoPago", so.[totalPrice] as "precioTotal", so.[order_date] as "fecha",
			 so.[order_status] as "estatus"
	--so.[salesOrder_id], so.[customer_id], so.[order_status], so.[order_date], so.[paymentMethod_id], so.[office_id], so.totalPrice, so.discount
	FROM   [dbo].[SalesOrder] so
	inner join SalesOrderDetails sod on sod.salesOrder_id = so.salesOrder_id
	inner join CarSold cso on cso.car_sold_id = sod.car_sold_id
	inner join [Car-Stock] cst on cst.car_stock_id = cso.car_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.Car c on c.car_id = cst.car_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarType ct on ct.carType_id = c.carType_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarBrands cb on cb.carBrand_id = c.carBrand_id
	inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.Car ca on ca.car_id = c.car_id
	inner join customerTable cl on cl.customer_id = so.customer_id
	inner join BranchOffice bo on bo.branchOffice_id = so.office_id
	inner join PaymentMethod pm on pm.paymentMethod_id = so.paymentMethod_id
	WHERE  ([office_id] = @office_id OR @office_id IS NULL) AND (c.carType_id = @carType OR @carType IS NULL) AND
		(bo.country_id = @country OR @country IS NULL) AND ((so.[order_date] >= @date1 OR @date1 IS NULL) AND (so.[order_date] < @date2 OR @date2 IS NULL)) AND
		(so.[paymentMethod_id] = @paymentMethod_id OR @paymentMethod_id IS NULL)

		--exec [dbo].[usp_SalesOrderSelect] null,null,null,null,null,null