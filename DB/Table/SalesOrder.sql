USE [BranchOfficeDB]
GO

/****** Object:  Table [dbo].[SalesOrder]    Script Date: 14/6/2019 14:11:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[SalesOrder](
	[salesOrder_id] [bigint] IDENTITY(1,1) NOT NULL,
	[customer_id] [bigint] NULL,
	[order_status] [int] NULL,
	[order_date] [datetime] NULL,
	[paymentMethod_id] [int] NULL,
	[office_id] [int] NULL,
	[totalPrice] [money] NULL,
	[totalPayment] [money] NULL,
	[discount] [float] NULL,
 CONSTRAINT [PK_SalesOrder] PRIMARY KEY CLUSTERED 
(
	[salesOrder_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[SalesOrder]  WITH CHECK ADD  CONSTRAINT [FK_SalesOrder_BranchOffice] FOREIGN KEY([office_id])
REFERENCES [dbo].[BranchOffice] ([branchOffice_id])
GO

ALTER TABLE [dbo].[SalesOrder] CHECK CONSTRAINT [FK_SalesOrder_BranchOffice]
GO

ALTER TABLE [dbo].[SalesOrder]  WITH CHECK ADD  CONSTRAINT [FK_SalesOrder_OrderStatus] FOREIGN KEY([order_status])
REFERENCES [dbo].[OrderStatus] ([status_id])
GO

ALTER TABLE [dbo].[SalesOrder] CHECK CONSTRAINT [FK_SalesOrder_OrderStatus]
GO

ALTER TABLE [dbo].[SalesOrder]  WITH CHECK ADD  CONSTRAINT [FK_SalesOrder_PaymentMethod] FOREIGN KEY([paymentMethod_id])
REFERENCES [dbo].[PaymentMethod] ([paymentMethod_id])
GO

ALTER TABLE [dbo].[SalesOrder] CHECK CONSTRAINT [FK_SalesOrder_PaymentMethod]
GO


