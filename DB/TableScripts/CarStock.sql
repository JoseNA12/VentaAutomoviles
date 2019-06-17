USE [BranchOfficeDB]
GO

/****** Object:  Table [dbo].[Car-Stock]    Script Date: 17/6/2019 12:01:37 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Car-Stock](
	[car_stock_id] [int] IDENTITY(1,1) NOT NULL,
	[car_id] [int] NULL,
	[stock_id] [int] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_Car-Stock] PRIMARY KEY CLUSTERED 
(
	[car_stock_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Car-Stock]  WITH CHECK ADD  CONSTRAINT [FK_Car-Stock_Stock] FOREIGN KEY([stock_id])
REFERENCES [dbo].[Stock] ([stock_id])
GO

ALTER TABLE [dbo].[Car-Stock] CHECK CONSTRAINT [FK_Car-Stock_Stock]
GO


