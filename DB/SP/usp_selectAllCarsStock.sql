
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE usp_selectAllCarsStock
	@office_id int
AS
BEGIN
	SET NOCOUNT ON;

	IF EXISTS(SELECT branchOffice_id FROM BranchOffice WHERE branchOffice_id = @office_id)
		SELECT c.car_id, c.model, c.carBrand_id, cb.name, c.motorType_id, c.price, c.year, c.photo, c.passengers 
		FROM [Car-Stock] cs
		inner join Stock s on s.stock_id = cs.stock_id
		inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.Car c on c.car_id = cs.car_id
		inner join [DESKTOP-3N2P4FH\FACTORYINSTANCE].FactoryDB.dbo.CarBrands cb on cb.carBrand_id = c.carBrand_id
		WHERE s.office_id = @office_id
	ELSE
		SELECT 1 as exit_status, 'La sucursal ingresada no existe' as result
END
GO
