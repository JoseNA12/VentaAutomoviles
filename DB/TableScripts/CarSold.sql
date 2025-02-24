USE [BranchOfficeDB]
GO

/****** Object:  Table [dbo].[CarSold]    Script Date: 17/6/2019 12:01:23 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CarSold](
	[car_sold_id] [int] IDENTITY(1,1) NOT NULL,
	[car_id] [int] NOT NULL,
 CONSTRAINT [PK_CarSold] PRIMARY KEY CLUSTERED 
(
	[car_sold_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


