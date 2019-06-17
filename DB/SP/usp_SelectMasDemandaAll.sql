USE [BranchOfficeDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_SelectMasDemandaAll]    Script Date: 17/6/2019 01:08:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[usp_SelectMasDemandaAll] 
    --@office_id int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON;

	CREATE TABLE #VentasBO1 (marca varchar(50),modelo varchar(50),pasajeros int,anio int,cantidad int,precio money)
	CREATE TABLE #VentasBO2 (marca varchar(50),modelo varchar(50),pasajeros int,anio int,cantidad int,precio money)
	CREATE TABLE #VentasBO3 (marca varchar(50),modelo varchar(50),pasajeros int,anio int,cantidad int,precio money)
	begin tran 

	Insert into #VentasBO1
	EXEC ('EXECUTE [BranchOfficeDB].[dbo].[usp_SelectMasDemanda]') AT [LAPTOP-CCS17DF7\BOFFICEINSTANCE2]

	Insert into #VentasBO2
	EXEC ('EXECUTE [BranchOfficeDB].[dbo].[usp_SelectMasDemanda]') AT [LAPTOP-CCS17DF7\BOFFICEINSTANCE3]

	Insert into #VentasBO3
	exec usp_SelectMasDemanda
	commit

	Select * From #VentasBO1
	UNION
	Select * From #VentasBO2
	UNION
	Select * From #VentasBO3
