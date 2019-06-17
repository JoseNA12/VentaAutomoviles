/*Pedidos pendientes*/

create procedure usp_SelectPedidosPendientes(@fecha date = NULL)
as
	With customerTable(customer_id, name, lastname, phone, zip_code, user_id, birthDate)
	AS(SELECT * FROM OPENQUERY([LAPTOP-CCS17DF7\HR_INSTANCE],'SELECT customer_id, name, lastname, phone, zip_code, user_id, birthDate FROM HumanResourcesDB.dbo.Customer'))
	select cb.[name] as marca,ca.[model] as modelo,car.[price] as precio,cu.[name] as nombre, cu.[lastname] as apellido,u.[email] as correo
from Car ca
	inner join CarBrands cb on cb.carBrand_id = ca.carBrand_id
	inner join [Order] o on o.car_id = ca.car_id
	inner join [LAPTOP-CCS17DF7\FACTORYINSTANCE2].FactoryDB.dbo.Car car on car.car_id = ca.car_id
	inner join customerTable cu on cu.customer_id = o.customer_id
	inner join [LAPTOP-CCS17DF7\HR_INSTANCE].HumanResourcesDB.dbo.[User] u on u.[user_id] = cu.[user_id]
	inner join OrderStatus os on os.[orderStatus_id] = o.orderStatus
	inner join [LAPTOP-CCS17DF7\FACTORYINSTANCE2].FactoryDB.[Order] od on od.order_id = o.order_id
	where od.order_date = @fecha OR @fecha IS NULL
	AND os.[statusName]='Pendiende'


/*Pedidos atendidos*/
create procedure usp_SelectPedidosAtendidos
as
begin
	With customerTable(customer_id, name, lastname, phone, zip_code, user_id, birthDate)
	AS(SELECT * FROM OPENQUERY([LAPTOP-CCS17DF7\HR_INSTANCE],'SELECT customer_id, name, lastname, phone, zip_code, user_id, birthDate FROM HumanResourcesDB.dbo.Customer'))
	select cb.[name] as marca,ca.[model] as modelo,car.[price] as precio,cu.[name] as nombre, cu.[lastname] as apellido,u.[email] as correo
	from Car ca
	inner join CarBrands cb on cb.carBrand_id = ca.carBrand_id
	inner join [Order] o on o.car_id = ca.car_id
	inner join [LAPTOP-CCS17DF7\FACTORYINSTANCE2].FactoryDB.dbo.Car car on car.car_id = ca.car_id
	inner join customerTable cu on cu.customer_id = o.customer_id
	inner join [LAPTOP-CCS17DF7\HR_INSTANCE].HumanResourcesDB.dbo.[User] u on u.[user_id] = cu.[user_id]
	inner join OrderStatus os on os.[orderStatus_id] = o.orderStatus
	inner join [LAPTOP-CCS17DF7\FACTORYINSTANCE2].FactoryDB.[Order] od on od.order_id = o.order_id
	AND os.[statusName]='Entregado'
end