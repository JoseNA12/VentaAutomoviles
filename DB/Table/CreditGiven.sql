USE [BranchOfficeDB]
GO

/****** Object:  Table [dbo].[CreditGiven]    Script Date: 14/6/2019 14:09:27 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CreditGiven](
	[credit_id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [bigint] NULL,
	[date] [date] NULL,
	[creditPlan_id] [int] NULL,
	[balance] [money] NULL,
	[mensualPayment] [float] NULL,
	[creditStatus] [int] NULL,
 CONSTRAINT [PK_Credit] PRIMARY KEY CLUSTERED 
(
	[credit_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[CreditGiven]  WITH CHECK ADD  CONSTRAINT [FK_Credit_SalesOrder] FOREIGN KEY([order_id])
REFERENCES [dbo].[SalesOrder] ([salesOrder_id])
GO

ALTER TABLE [dbo].[CreditGiven] CHECK CONSTRAINT [FK_Credit_SalesOrder]
GO


