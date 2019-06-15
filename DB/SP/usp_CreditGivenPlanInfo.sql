SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure usp_CreditGivenPlanInfo (@creditGivenId int)
as
begin tran

select p.prima, cg.mensualPayment, p.anualTerm, p.interest, p.planName, cg.balance, p.creditPlan_id
From CreditGiven cg
inner join CreditPlan p on p.creditPlan_id = cg.creditPlan_id
where (cg.credit_id = @creditGivenId or @creditGivenId IS NULL)
commit