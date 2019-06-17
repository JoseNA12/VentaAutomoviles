USE [BranchOfficeDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_SalesOrderSelect]    Script Date: 16/6/2019 09:24:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
alter PROC [dbo].[usp_SalesOrderSelectAll] 
    @office_id int = NULL,
	@carType int = NULL,
	@country int = NULL,
	@date1 date = NULL,
	@date2 date = NULL,
	@paymentMethod_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON 
	
	CREATE TABLE #LocalBOffice1 (marca varchar(50),modelo varchar(50),anio int,pasajeros int,precio money, tipo varchar(50),
								nombreCl varchar(50),apellidos varchar(50),telefonoCl varchar(50), metodoPago varchar(50),
								precioTotal money,fecha date, estatus int);
	CREATE TABLE #LocalBOffice2 (marca varchar(50),modelo varchar(50),anio int,pasajeros int,precio money, tipo varchar(50),
								nombreCl varchar(50),apellidos varchar(50),telefonoCl varchar(50), metodoPago varchar(50),
								precioTotal money,fecha date, estatus int);
	CREATE TABLE #LocalBOffice3 (marca varchar(50),modelo varchar(50),anio int,pasajeros int,precio money, tipo varchar(50),
								nombreCl varchar(50),apellidos varchar(50),telefonoCl varchar(50), metodoPago varchar(50),
								precioTotal money,fecha date, estatus int);
	begin tran 
	Insert into #LocalBOffice2
	EXEC ('EXECUTE [BranchOfficeDB].[dbo].[usp_SalesOrderSelect] 
		@office_id=?, @carType=?, @country=?, @date1=?, @date2=?, @paymentMethod_id=?', 
		@office_id, @carType, @country, @date1, @date2, @paymentMethod_id) AT [LAPTOP-CCS17DF7\BOFFICEINSTANCE2]

	Insert into #LocalBOffice3
	EXEC ('EXECUTE [BranchOfficeDB].[dbo].[usp_SalesOrderSelect] 
		@office_id=?, @carType=?, @country=?, @date1=?, @date2=?, @paymentMethod_id=?', 
		@office_id, @carType, @country, @date1, @date2, @paymentMethod_id) AT [LAPTOP-CCS17DF7\BOFFICEINSTANCE3]

	Insert into #LocalBOffice1
	exec usp_SalesOrderSelect @office_id, @carType, @country, @date1, @date2, @paymentMethod_id
	commit

	Select * from #LocalBOffice1
	UNION
	Select * from #LocalBOffice2
	UNION
	Select * from #LocalBOffice3

