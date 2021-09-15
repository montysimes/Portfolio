/*
*********************************************************************
Query execution of Sample Database
*********************************************************************
*/

/* Find product code, product name of those whose buy prices are not within the
ranges of 90 and 100.*/

SELECT productCode, productName
FROM products 
WHERE NOT buyPrice BETWEEN 90 AND 100;

/*List all product name and product vendor of those whose name contains the
substring ‘Ford’ or vendor is ‘Second Gear Diecast’.*/

SELECT productName, productVendor
FROM products
WHERE productName LIKE '%Ford%'
OR
productVendor = 'Second Gear Diecast';

/*List the name of unique countries where an office is located.*/

SELECT DISTINCT country 
FROM offices;

/*Find order number, order date and customer name of those whose customers are
based in Australia.*/

SELECT orderNumber, orderDate, customerName
FROM orders, customers
WHERE orders.customerNumber = customers.customerNumber
    AND customers.country = 'Australia';

/*Find product name, vendor name of products which are shipped in 2005.*/

SELECT p.productName, p.productVendor
FROM products AS p, orders AS o, orderdetails AS od
WHERE p.productCode = od.productCode
    AND od.orderNumber = o.orderNumber
    AND year(o.shippedDate) = 2005; 

/* List all ofices that dont opperate out of USA or France*/

SELECT *
FROM offices
WHERE country NOT IN ('USA', 'France');

/*Find order number, product code and the amount payable for that product*/

SELECT  orderNumber, productCode, (quantityOrdered*priceEach) AS 'payable'
FROM orderdetails;

/*Find order number and the total amount payable for that order (including all products)*/

SELECT orderNumber, sum(quantityOrdered*priceEach)
FROM orderdetails 
GROUP BY orderNumber;

/*Extent the result set of the previous query by including the customer name.*/

SELECT  c.customerName, od.orderNumber, sum(od.quantityOrdered*od.priceEach)
FROM orderdetails od, orders o, customers c 
WHERE od.orderNumber = o.orderNumber AND c.customerNumber = o.customerNumber
GROUP BY orderNumber;

/*Find order number, order date, customer name and the total amount payable of those whose customers 
are based in Australia. Include only the top five records based on total amount payable.*/

SELECT o.orderNumber, o.orderDate, c.customerName, sum(od.quantityOrdered*od.priceEach) AS 'Total'
FROM orderdetails od, orders o, customers c
WHERE od.orderNumber = o.orderNumber AND c.customerNumber = o.customerNumber AND c.country IN ('Australia')
GROUP BY orderNumber
ORDER BY Total DESC
LIMIT 5;

/*Find the total valuation/amount of all orders placed by each customer located in ‘USA’
and ‘Australia’. The result set must include customer name, customer country and the
total valuation of all orders by that customer. Sort the result set based on total valuation
of orders in descending order. You must use HAVING clause in your statement.*/

SELECT c.customerName, c.country,  sum(od.quantityOrdered*od.priceEach) AS 'Total Valuation of Orders'
FROM orderdetails od, customers c, orders o
WHERE c.customerNumber = o.customerNumber and o.orderNumber = od.orderNumber 
GROUP BY customerName, country
HAVING country IN ('Australia', 'USA')
ORDER BY sum(od.quantityOrdered*od.priceEach) DESC;

/*Find the order numbers and the number of items sold per order. Do not include
order numbers having less than 500 items sold. Write query without using HAVING*/

SELECT A.orderNumber, A.Quant
FROM (SELECT orderNumber, SUM(quantityOrdered) AS Quant FROM orderdetails GROUP BY orderNumber) A
WHERE A.Quant > 500;

/*Find the cities where both offices and customers are located. The result set must include
the name of the cities and their corresponding office codes.*/

SELECT officeCode, city 
FROM offices o
WHERE city in 
    (SELECT city
	FROM customers c);

/*Find the product name which has the lowest quantity in stock. You must not use ALL
keyword.*/

SELECT productName, quantityInStock
FROM products
WHERE quantityInStock = 
    (SELECT MIN(quantityInStock)
	FROM products);

/*Find the product code, product name and quantity in stock of those products which have
higher quantity in the stock than the average quantity in stock of all products. Only include
the top five products based on the quantity in stock and display them in descending order.*/

SELECT productCode, productName, quantityInStock
FROM products
WHERE quantityInStock > 
    (SELECT AVG(quantityInStock)
	FROM products)
ORDER BY quantityInStock DESC
LIMIT 5;

/*Find order number, customer name and country for those which are placed by
customers in Australia. Use NATURAL JOIN*/

SELECT ordernumber, customerName, country
FROM customers NATURAL JOIN orders
WHERE country = 'Australia';

/*Find the products which are never ordered. Use NOT EXISTS operator.*/

SELECT productCode, productName
FROM products p
WHERE  NOT EXISTS 
    (SELECT *
	FROM orderdetails od
    WHERE p.productCode = od.productCode); 

/*Find the employees (employee number, first name and last name) who supervises other
employees.*/

SELECT DISTINCT e2.employeenumber, e2.firstname, e2.lastname
FROM employees e1, employees e2
WHERE e1.reportsTo = e2.employeeNumber;
