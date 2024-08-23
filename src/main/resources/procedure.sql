
DELIMITER $$
CREATE PROCEDURE proc_increase_salary_ten_per_cent(IN percent INT)
BEGIN
    UPDATE funcionario
    SET salario = salario + salario * percent;
END$$
DELIMITER;