USE [BranchOfficeDB]
GO

/****** Object:  Table [dbo].[CreditStatus]    Script Date: 14/6/2019 14:10:48 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CreditStatus](
	[creditStatus_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[details] [nvarchar](500) NULL,
 CONSTRAINT [PK_CreditStatus] PRIMARY KEY CLUSTERED 
(
	[creditStatus_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


