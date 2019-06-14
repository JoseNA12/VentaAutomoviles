USE [BranchOfficeDB]
GO

/****** Object:  Table [dbo].[CreditPlan]    Script Date: 14/6/2019 14:09:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CreditPlan](
	[creditPlan_id] [int] IDENTITY(1,1) NOT NULL,
	[prima] [float] NULL,
	[interest] [float] NULL,
	[anualTerm] [float] NULL,
	[planName] [nvarchar](50) NULL,
 CONSTRAINT [PK_CreditPlan] PRIMARY KEY CLUSTERED 
(
	[creditPlan_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


