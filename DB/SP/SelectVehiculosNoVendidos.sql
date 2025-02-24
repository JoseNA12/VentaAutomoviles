USE [BranchOfficeDB]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER procedure [dbo].[SelectVehiculosNoVendidos] as
begin
SELECT cb.[name] as marca, c.[model] as modelo, c.[seats] as pasajeros, c.[year] as anio, 0 as cantidad, ca.[price] as precio 
    From [dbo].[Car-Stock] cs
    inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.Car c on c.car_id = cs.car_id
    inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarType ct on ct.carType_id = c.carType_id
    inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarBrands cb on cb.carBrand_id = c.carBrand_id
    inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE2].FactoryDB.dbo.Car ca on ca.car_id = c.car_id
	WHERE c.car_id NOT IN(Select cso.car_id from CarSold cso)
end

exec [SelectVehiculosNoVendidos] 