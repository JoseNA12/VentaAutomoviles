USE [BranchOfficeDB]
GO

/****** Object:  Table [dbo].[CreditGiven-Payment]    Script Date: 14/6/2019 14:09:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CreditGiven-Payment](
	[payment_id] [int] IDENTITY(1,1) NOT NULL,
	[credit_id] [int] NULL,
	[payment] [money] NULL,
	[date] [date] NULL,
	[paymentMethod_id] [int] NULL,
 CONSTRAINT [PK_Credit-Payment] PRIMARY KEY CLUSTERED 
(
	[payment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[CreditGiven-Payment]  WITH CHECK ADD  CONSTRAINT [FK_Credit-Payment_Credit] FOREIGN KEY([credit_id])
REFERENCES [dbo].[CreditGiven] ([credit_id])
GO

ALTER TABLE [dbo].[CreditGiven-Payment] CHECK CONSTRAINT [FK_Credit-Payment_Credit]
GO


